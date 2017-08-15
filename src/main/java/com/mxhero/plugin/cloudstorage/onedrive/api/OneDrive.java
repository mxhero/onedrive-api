/**
 * Copyright (C) 2015 mxHero Inc (mxhero@mxhero.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mxhero.plugin.cloudstorage.onedrive.api;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.microsoft.aad.adal4j.AsymmetricKeyCredential;
import com.microsoft.aad.adal4j.AuthenticationContext;
import com.microsoft.aad.adal4j.AuthenticationResult;
import com.mxhero.plugin.cloudstorage.onedrive.api.command.CommandFactory;

/**
 * The Class OneDrive.
 */
public class OneDrive {
	
	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(OneDrive.class);
	
	/** The Constant JACKSON. */
	public static ObjectMapper JACKSON = new ObjectMapper();
	static {
		JACKSON.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		JACKSON.setSerializationInclusion(Inclusion.NON_NULL);
	}
	
	/** The application. */
	private Application application;
	
	/** The credential. */
	private Credential credential;
	
	/** The command factory. */
	private CommandFactory commandFactory;
	
	/** The drives. */
	private Drives drives;
	
	/** The items. */
	private Items items;
	
	/** The reserved characters type. */
	private ReservedCharactersType reservedCharactersType = ReservedCharactersType.sharepoint_2013;

	/**
	 * Redeem.
	 *
	 * @param redeemRequest the redeem request
	 * @return the map
	 */
	public static Map<String, Object> redeem(RedeemRequest redeemRequest){
		List<BasicNameValuePair> params = buildParams(redeemRequest.getClientId(), redeemRequest.getRedirectUri(), redeemRequest.getClientSecret());
		params.add(new BasicNameValuePair("code",redeemRequest.getCode()));
		params.add(new BasicNameValuePair("grant_type","authorization_code"));
		return redeemNow(ApiEnviroment.tokenBaseUrl.getValue(), params);
	}

	/**
	 * Perform entire 4 steps process of Redeem OneDrive for Business API according to documentation {@link https://dev.onedrive.com/auth/aad_oauth.htm}
	 * 
	 * Step 1: Redeem the authorization code for tokens
	 * Step 2: Discover the OneDrive for Business resource URI
	 * Step 3: Redeem refresh token for an access token to call OneDrive API
	 * Step 4: It is not documented but retriever Email address for user access token.
	 *
	 * @param redeemRequest the redeem request
	 * @return the one drive business object which encapsulate credential info, such as access and refresh token and sharepoint URL for further OneDrive for Business API calls
	 * @throws AuthenticationException the authentication exception
	 */
	public static BusinessCredential redeemBusiness(RedeemRequest redeemRequest) throws AuthenticationException{
		try {
			Map<String, Object> redeemBusinessApiResource = redeemBusinessApiResource(ApiEnviroment.graphApiUrl.getValue(), redeemRequest.getClientId(), redeemRequest.getClientSecret(), redeemRequest.getRedirectUri(), redeemRequest.getCode());
			Map<String, Object> redeemBusinessApi = redeemBusinessApiResource(redeemRequest.getSharepointResourceId(), redeemRequest.getClientId(), redeemRequest.getClientSecret(), redeemRequest.getRedirectUri(), redeemRequest.getCode());
			String userEmail = businessEmail((String)redeemBusinessApiResource.get("access_token"));
			logger.debug("Redeem for OneDrive Business API sharepoint specific URL {}", redeemBusinessApi);
			return BusinessCredential.builder()
					.sharepointEndpointUri(redeemRequest.getSharepointEndpointUri())
					.sharepointResourceId(redeemRequest.getSharepointResourceId())
					.accessToken((String)redeemBusinessApi.get("access_token"))
					.refreshToken((String)redeemBusinessApi.get("refresh_token"))
					.tokenType((String)redeemBusinessApi.get("token_type"))
					.user(userEmail)
					.build();
		} catch (Exception e) {
			throw new AuthenticationException("Could not redeem code "+redeemRequest.getCode()+" for OneDrive Business API");			
		}
	}
	
	/**
	 * Redeem daemon.
	 *
	 * @param redeemDaemonRequest the redeem daemon request
	 * @return the Access Token redeemed it
	 * @throws AuthenticationException the authentication exception
	 */
	public static String redeemDaemon(RedeemDaemonRequest redeemDaemonRequest) throws AuthenticationException{
		ExecutorService service = Executors.newCachedThreadPool();
		AuthenticationResult authenticationResult = null;
		String authority = String.format(ApiEnviroment.tokenDaemonBaseUrl.getValue(), redeemDaemonRequest.getTenantId());
		logger.debug("Trying to get App Only token for {}", redeemDaemonRequest);
		try {
			AuthenticationContext authenticationContext =  new AuthenticationContext(authority, false, service);
			String filePkcs12 = ApiEnviroment.fileUrlPkcs12Certificate.getValue();
			if(StringUtils.isNotEmpty(redeemDaemonRequest.getFileUrlPkcs12Certificate())){
				filePkcs12 = redeemDaemonRequest.getFileUrlPkcs12Certificate(); 
			}
			
			String filePkcs12Secret = ApiEnviroment.pkcs12CertificateSecret.getValue();
			if(StringUtils.isNotEmpty(redeemDaemonRequest.getCertificateSecret())){
				filePkcs12Secret = redeemDaemonRequest.getCertificateSecret(); 
			}
			
			Validate.notEmpty(filePkcs12, "Pkcs12 Key file path must be provided or configured. You can set it on environment var 'ONEDRIVE_DAEMON_PKCS12_FILE_URL' or through Java System Property 'onedrive.daemon.pkcs12.file.url'");
			Validate.notEmpty(filePkcs12Secret, "Pkcs12 Secret Key file must be provided or configured. You can set it on environment var 'ONEDRIVE_DAEMON_PKCS12_FILE_SECRET' or through Java System Property 'onedrive.daemon.pkcs12.file.secret'");
			
			InputStream pkcs12Certificate = new FileInputStream(filePkcs12);
			AsymmetricKeyCredential credential = AsymmetricKeyCredential.create(redeemDaemonRequest.getClientId(), pkcs12Certificate, filePkcs12Secret);
			
			Future<AuthenticationResult> future = authenticationContext.acquireToken(redeemDaemonRequest.getResourceSharepointId(), credential, null);
			authenticationResult = future.get(10, TimeUnit.SECONDS);
			logger.debug("Token retrieved {}", ToStringBuilder.reflectionToString(authenticationResult, ToStringStyle.SHORT_PREFIX_STYLE));
			return authenticationResult.getAccessToken();
		} catch (Exception e) {
			logger.error("Error trying to get new App Only Token", e);
			throw new AuthenticationException(String.format("Error trying to get new App Only Token for tenantId %s and sharepointUri %s", redeemDaemonRequest.getTenantId(), redeemDaemonRequest.getResourceSharepointId()));
		}finally{
			service.shutdown();
		}

	}


	/**
	 * Business email.
	 *
	 * @param accessToken the access token
	 * @return the string
	 */
	@SuppressWarnings("unchecked")
	private static String businessEmail(String accessToken) {
		try{
			
			URIBuilder builder = new URIBuilder(ApiEnviroment.graphApiUrl.getValue()+"me");
			builder.addParameter("api-version", "1.6");
			HttpGet httpGet = new HttpGet(builder.toString());
			httpGet.setHeader("Authorization","Bearer " + accessToken);
			
			HttpResponse response = HttpClientBuilder.create().build().execute(httpGet);
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				Map<String, Object> responseObject = (Map<String, Object>) OneDrive.JACKSON.readValue(EntityUtils.toString(response.getEntity()), Map.class);
				if(responseObject.containsKey("userPrincipalName")){
					return (String) responseObject.get("userPrincipalName");
				}else{
					return (String) responseObject.get("mail");
				}
			}
			throw new RuntimeException("error reading response with code "+response.getStatusLine().getStatusCode());
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * Redeem business api.
	 *
	 * @param resourceId the resource id
	 * @param clientId the client id
	 * @param clientSecret the client secret
	 * @param redirectUri the redirect uri
	 * @param code the code
	 * @return the map
	 */
	private static Map<String, Object> redeemBusinessApiResource(String resourceId, String clientId, String clientSecret, String redirectUri, String code){
		List<BasicNameValuePair> params = buildParams(clientId, redirectUri, clientSecret);
		params.add(new BasicNameValuePair("code", code));
		params.add(new BasicNameValuePair("grant_type","authorization_code"));
		params.add(new BasicNameValuePair("resource",resourceId));
		return redeemNow(ApiEnviroment.tokenBusinessBaseUrl.getValue(), params);
	}

	/**
	 * Builds the params.
	 *
	 * @param clientId the client id
	 * @param redirectUri the redirect uri
	 * @param clientSecret the client secret
	 * @return the list
	 */
	private static List<BasicNameValuePair> buildParams(String clientId, String redirectUri, String clientSecret) {
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("client_id", clientId));
		params.add(new BasicNameValuePair("redirect_uri", redirectUri));
		params.add(new BasicNameValuePair("client_secret",clientSecret));
		return params;
	}

	/**
	 * Redeem now.
	 *
	 * @param tokenUrl the token url
	 * @param params the params
	 * @return the map
	 */
	@SuppressWarnings("unchecked")
	private static Map<String, Object> redeemNow(String tokenUrl, List<BasicNameValuePair> params) {
		try{
			HttpPost httpPost = new HttpPost(tokenUrl);		
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse response = HttpClientBuilder.create().build().execute(httpPost);
			String responseString = EntityUtils.toString(response.getEntity());
			logger.info(responseString);
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				return OneDrive.JACKSON.readValue(responseString,Map.class);
			}
			throw new RuntimeException("error reading response with code "+response.getStatusLine().getStatusCode());
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Emails.
	 *
	 * @param accessToken the access token
	 * @return the map
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> emails(String accessToken){
		try{
			HttpGet httpGet = new HttpGet(new URIBuilder(ApiEnviroment.liveUserInfoUrl.getValue())
					.addParameter("access_token", accessToken)
					.build());
			HttpResponse response = HttpClientBuilder.create().build().execute(httpGet);
			String responseString = EntityUtils.toString(response.getEntity());
			logger.info(responseString);
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				return (Map<String, Object>) OneDrive.JACKSON.readValue(responseString,Map.class).get("emails");
			}
			throw new RuntimeException("error reading response with code "+response.getStatusLine().getStatusCode());
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Reserved characters type.
	 *
	 * @param reservedCharactersType the reserved characters type
	 * @return the one drive
	 */
	public OneDrive reservedCharactersType(ReservedCharactersType reservedCharactersType){
		this.items().reservedCharactersType(reservedCharactersType);
		return this;
	}
	
	/**
	 * Drives.
	 *
	 * @return the drives
	 */
	public Drives drives(){
		return drives;
	}
	
	/**
	 * Items.
	 *
	 * @return the items
	 */
	public Items items(){
		return items;
	}

	/**
	 * Credential.
	 *
	 * @return the credential
	 */
	public Credential credential(){
		return this.credential;
	}
	
	/**
	 * Release.
	 */
	public void release(){
		this.commandFactory.shutdown();
	}
	
	/**
	 * Validate.
	 */
	private void validate() {
		Validate.notNull(application, "application may not be null");
		Validate.notNull(credential, "credential may not be null");
	}
	
	/**
	 * The Class Builder.
	 */
	public static class Builder {
		
		/** The instance. */
		private OneDrive instance = new OneDrive();
		
		/**
		 * Application.
		 *
		 * @param application the application
		 * @return the builder
		 */
		public Builder application(Application application){
			this.instance.application = application;
			return this;
		}
		
		/**
		 * Daemon application.
		 *
		 * @param application the application
		 * @return the builder
		 */
		public Builder daemonApplication(DaemonApplication application){
			this.instance.application = application;
			return this;
		}
		
		/**
		 * Credential.
		 *
		 * @param credential the credential
		 * @return the builder
		 */
		public Builder credential(Credential credential){
			this.instance.credential = credential;
			return this;
		}
		
		/**
		 * Business url.
		 *
		 * @param credential the credential
		 * @return the builder
		 */
		public Builder businessCredential(BusinessCredential credential){
			this.instance.credential = credential;
			return this;
		}
		
		/**
		 * Business url.
		 *
		 * @param credential the credential
		 * @return the builder
		 */
		public Builder daemonCredential(DaemonCredential credential){
			this.instance.credential = credential;
			return this;
		}
		
		/**
		 * Reserved characters type.
		 *
		 * @param reservedCharactersType the reserved characters type
		 * @return the builder
		 */
		public Builder reservedCharactersType(ReservedCharactersType reservedCharactersType){
			if(reservedCharactersType!=null){
				this.instance.reservedCharactersType = reservedCharactersType;
			}
			return this;
		}
		
		/**
		 * Reserved characters type.
		 *
		 * @param reservedCharactersType the reserved characters type
		 * @return the builder
		 */
		public Builder reservedCharactersType(String reservedCharactersType){
			try{
				return reservedCharactersType(ReservedCharactersType.valueOf(reservedCharactersType));
			}catch(Exception e){
				logger.warn("set_reserved_characters_type_failed:"+reservedCharactersType,e);
			}
			return this;
		}
		
		/**
		 * Builds the.
		 *
		 * @return the one drive
		 */
		public OneDrive build(){
			instance.validate();
			instance.commandFactory=new CommandFactory(this.instance.application, this.instance.credential);
			instance.drives = new Drives(instance.commandFactory);
			instance.items = new Items(instance.commandFactory).reservedCharactersType(instance.reservedCharactersType);
			return instance;
		}
		
	}
	
}
