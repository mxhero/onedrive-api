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

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mxhero.plugin.cloudstorage.onedrive.api.model.DiscoveryServices;


/**
 * The Class Drives.
 */
public class Discovery {

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(Discovery.class);
	
	/** The Constant SERVICES. */
	public static final String SERVICES = "v2.0/me/services";
	
	/**
	 * Instantiates a new drives.
	 *
	 * @param commandFactory the command factory
	 */
	public Discovery(){
	}
	
	/**
	 * User default.
	 *
	 * @return the drive
	 */
	public DiscoveryServices services(Credential credential){
		logger.debug("Discvery services for user");
		try {
			HttpGet request = new HttpGet(ApiEnviroment.discoveryResourceUri.getValue()+SERVICES);		
			request.setHeader("Authorization","Bearer " + credential.getAccessToken());
			CloseableHttpResponse response = HttpClientBuilder.create().build().execute(request);
			if(response.getStatusLine().getStatusCode()==404){
				return new DiscoveryServices();
			}else if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				return OneDrive.JACKSON.readValue(EntityUtils.toString(response.getEntity()), DiscoveryServices.class);
			}
			throw new RuntimeException("error reading response with code "+response.getStatusLine().getStatusCode());
		} catch (Exception e) {
			throw new IllegalArgumentException("error reading response",e);
		}		
	}
	
	

	/**
	 * Redeem an access token for future discovery service use.
	 *
	 * @param code the code
	 * @param clientId the client id
	 * @param clientSecret the client secret
	 * @param redirectUri the redirect uri
	 * @return the credential
	 */
	public Credential redeemDiscovery(String code, String clientId, String clientSecret, String redirectUri){
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("client_id", clientId));
		params.add(new BasicNameValuePair("redirect_uri", redirectUri));
		params.add(new BasicNameValuePair("client_secret",clientSecret));
		params.add(new BasicNameValuePair("code",code));
		params.add(new BasicNameValuePair("grant_type","authorization_code"));
		params.add(new BasicNameValuePair("resource",ApiEnviroment.discoveryResourceUri.getValue()));
		try{
			HttpPost httpPost = new HttpPost(ApiEnviroment.tokenBusinessBaseUrl.getValue());		
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse response = HttpClientBuilder.create().build().execute(httpPost);
			String responseString = EntityUtils.toString(response.getEntity());
			logger.info(responseString);
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				JsonNode mapResponse = OneDrive.JACKSON.readValue(responseString, JsonNode.class);
				return new Credential.Builder().accessToken(mapResponse.get("access_token").asText()).refreshToken(mapResponse.get("refresh_token").asText()).build();
			}
			throw new RuntimeException("error reading response with code "+response.getStatusLine().getStatusCode());
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
