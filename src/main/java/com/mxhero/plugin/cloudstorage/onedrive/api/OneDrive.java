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

/*
 * #%L
 * com.mxhero.plugin.cloudstorage.onedrive
 * %%
 * Copyright (C) 2013 - 2015 mxHero Inc.
 * %%
 * MXHERO END USER LICENSE AGREEMENT
 * 
 * IMPORTANT-READ CAREFULLY:  BY DOWNLOADING, INSTALLING, OR USING THE SOFTWARE, YOU (THE INDIVIDUAL OR LEGAL ENTITY) AGREE TO BE BOUND BY THE TERMS OF THIS END USER LICENSE AGREEMENT (EULA).  IF YOU DO NOT AGREE TO THE TERMS OF THIS EULA, YOU MUST NOT DOWNLOAD, INSTALL, OR USE THE SOFTWARE, AND YOU MUST DELETE OR RETURN THE UNUSED SOFTWARE TO THE VENDOR FROM WHICH YOU ACQUIRED IT WITHIN THIRTY (30) DAYS AND REQUEST A REFUND OF THE LICENSE FEE, IF ANY, THAT YOU PAID FOR THE SOFTWARE.
 * 
 * READ LICENSE.txt file to see details of this agreement.
 * #L%
 */


import java.util.Arrays;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

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
	
	/**
	 * Redeem.
	 *
	 * @param code the code
	 * @param clientId the client id
	 * @param redirectUri the redirect uri
	 * @param clientSecret the client secret
	 * @return the map
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> redeem(String code, String clientId, String redirectUri, String clientSecret){
		try{
			HttpPost httpPost = new HttpPost(ApiEnviroment.tokenBaseUrl.getValue());		
			httpPost.setEntity(new UrlEncodedFormEntity(Arrays.asList(
					new BasicNameValuePair("client_id", clientId)
					,new BasicNameValuePair("redirect_uri", redirectUri)
					,new BasicNameValuePair("client_secret",clientSecret)
					,new BasicNameValuePair("code",code)
					,new BasicNameValuePair("grant_type","authorization_code"))));
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
		 * Builds the.
		 *
		 * @return the one drive
		 */
		public OneDrive build(){
			instance.validate();
			instance.commandFactory=new CommandFactory(this.instance.application, this.instance.credential);
			instance.drives = new Drives(instance.commandFactory);
			instance.items = new Items(instance.commandFactory);
			return instance;
		}
		
	}
	
}
