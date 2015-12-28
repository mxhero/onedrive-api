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
package com.mxhero.plugin.cloudstorage.onedrive.api.command;

/*
 * #%L
 * com.mxhero.plugin.cloudstorage.sharefile
 * %%
 * Copyright (C) 2013 - 2014 mxHero Inc.
 * %%
 * MXHERO END USER LICENSE AGREEMENT
 * 
 * IMPORTANT-READ CAREFULLY:  BY DOWNLOADING, INSTALLING, OR USING THE SOFTWARE, YOU (THE INDIVIDUAL OR LEGAL ENTITY) AGREE TO BE BOUND BY THE TERMS OF THIS END USER LICENSE AGREEMENT (EULA).  IF YOU DO NOT AGREE TO THE TERMS OF THIS EULA, YOU MUST NOT DOWNLOAD, INSTALL, OR USE THE SOFTWARE, AND YOU MUST DELETE OR RETURN THE UNUSED SOFTWARE TO THE VENDOR FROM WHICH YOU ACQUIRED IT WITHIN THIRTY (30) DAYS AND REQUEST A REFUND OF THE LICENSE FEE, IF ANY, THAT YOU PAID FOR THE SOFTWARE.
 * 
 * READ LICENSE.txt file to see details of this agreement.
 * #L%
 */


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mxhero.plugin.cloudstorage.onedrive.api.ApiEnviroment;
import com.mxhero.plugin.cloudstorage.onedrive.api.Application;
import com.mxhero.plugin.cloudstorage.onedrive.api.Credential;
import com.mxhero.plugin.cloudstorage.onedrive.api.OneDrive;

/**
 * The Class RefreshCommand.
 *
 * @param <T> the generic type
 */
public class RefreshCommand<T> implements Command<T>{
	
	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(RefreshCommand.class);
	
	/** The credential. */
	protected Credential credential;
	
	/** The client builder. */
	protected HttpClientBuilder clientBuilder;
	
	/** The application. */
	protected Application application;
	
	/** The base url. */
	private String baseUrl = ApiEnviroment.baseUrl.getValue();
	
	/**
	 * Instantiates a new command.
	 *
	 * @param clientBuilder the client builder
	 * @param application the application
	 * @param credential the credential
	 */
	public RefreshCommand(HttpClientBuilder clientBuilder, Application application, Credential credential){
		Validate.notNull(credential, "credential may not be null");
		Validate.notNull(clientBuilder, "clientBuilder may not be null");
		this.credential=credential;
		this.clientBuilder=clientBuilder;
		this.application=application;
	}
	
	/* (non-Javadoc)
	 * @see com.mxhero.plugin.cloudstorage.onedrive.api.command.Command#excecute(com.mxhero.plugin.cloudstorage.onedrive.api.command.CommandHandler)
	 */
	public T excecute(CommandHandler<T> handler){
		T value = null;
		try{
			value =  handlerExecute(handler);
			logger.debug("command executed");
		}catch(AuthenticationException e){
			if(canRefresh()){
				refreshToken();
				logger.debug("refresh token executed");
				value =  handlerExecute(handler);
				logger.debug("command with refresh token executed");
			}
		}
		return value;
	}

	/**
	 * Must refresh.
	 *
	 * @return true, if successful
	 */
	public boolean canRefresh() {
		return StringUtils.isNotEmpty(credential.getRefreshToken());
	}
	
	/**
	 * Refresh token.
	 */
	public void refreshToken(){
		CloseableHttpResponse response = null;
		try {
			response = callRefreshToken();
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				@SuppressWarnings("unchecked")
				Map<String, Object> result = OneDrive.JACKSON.readValue(EntityUtils.toString(response.getEntity()), Map.class);
				this.credential.setAccessToken(result.get("access_token").toString());
				if(result.containsKey("refresh_token")){
					this.credential.setRefreshToken(result.get("refresh_token").toString());
				}
				onSucess(result);
			}else{
				onFailure(response.getStatusLine().getStatusCode());
				throw new ApiException(response);
			}
		} catch (IOException  e) {
			throw new IllegalArgumentException(e);
		}finally{
			if(response!=null){
				try{response.close();}catch(Exception e){};
			}
		}
	}

	/**
	 * On failure.
	 *
	 * @param statusCode the status code
	 */
	public void onFailure(int statusCode) {
		if(credential.getListener()!=null){
			try{
				credential.getListener().onFaliure(credential, statusCode);
			}catch(Exception e){
				logger.warn("error executing listener ON FALIURE for credential "+credential+", ignoring",e);
			}
		}
	}

	/**
	 * On sucess.
	 *
	 * @param result the result
	 */
	public void onSucess(Map<String, Object> result) {
		if(credential.getListener()!=null){
			try{
				credential.getListener().onSuccess(credential, result);
			}catch(Exception e){
				logger.warn("error executing listener ON SUCESS for credential "+credential+", ignoring",e);
			}
		}
	}

	/**
	 * Call refresh token.
	 *
	 * @return the closeable http response
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ClientProtocolException the client protocol exception
	 */
	public CloseableHttpResponse callRefreshToken()
			throws UnsupportedEncodingException, IOException, ClientProtocolException {
		CloseableHttpResponse response;
		CloseableHttpClient client;
		client = clientBuilder.build();
		
		HttpPost post = new HttpPost(ApiEnviroment.tokenBaseUrl.getValue());
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		List<NameValuePair> postParams = new ArrayList<>();
		postParams.add(new BasicNameValuePair("grant_type", "refresh_token"));
		postParams.add(new BasicNameValuePair("refresh_token", credential.getRefreshToken()));
		postParams.add(new BasicNameValuePair("redirect_uri", application.getRedirectUri()));
		postParams.add(new BasicNameValuePair("client_id", application.getClientId()));
		postParams.add(new BasicNameValuePair("client_secret", application.getClientSecret()));
		post.setEntity(new UrlEncodedFormEntity(postParams));
		response = client.execute(post);
		return response;
	}
	
	/**
	 * Handler execute.
	 *
	 * @param handler the handler
	 * @return the t
	 * @throws ApiException the api exception
	 */
	private T handlerExecute(CommandHandler<T> handler) throws ApiException{
		CloseableHttpResponse response = null;
		CloseableHttpClient client = null;
		try {
			HttpUriRequest request = handler.request();
			request.setHeader("Authorization","Bearer "+credential.getAccessToken());
			client = clientBuilder.build();
			response = client.execute(request);
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_UNAUTHORIZED){
				throw new AuthenticationException("401 for "+credential.getUserId());
			}else if(response.getStatusLine().getStatusCode()!=HttpStatus.SC_NOT_FOUND 
					&& response.getStatusLine().getStatusCode()>399
					){
				throw new ApiException(response);
			}else if(response.getStatusLine().getStatusCode()==HttpStatus.SC_NOT_FOUND
					&& response.getFirstHeader("WWW-Authenticate")!=null
					&& response.getFirstHeader("WWW-Authenticate").getValue()!=null
					&& response.getFirstHeader("WWW-Authenticate").getValue().contains("expired_token")){
				throw new AuthenticationException("401 for "+credential.getUserId());
			}
			return handler.response(response);
		}catch (IOException e){
			throw new IllegalArgumentException(e);
		}finally{
			if(response!=null){
				try{response.close();}catch(Exception e){};
			}
		}
	}
	
	/**
	 * Gets the application.
	 *
	 * @return the application
	 */
	public Application getApplication() {
		return application;
	}

	/* (non-Javadoc)
	 * @see com.mxhero.plugin.cloudstorage.onedrive.api.command.Command#baseUrl()
	 */
	@Override
	public String baseUrl() {
		return baseUrl+DRIVE;
	}
	
	/* (non-Javadoc)
	 * @see com.mxhero.plugin.cloudstorage.onedrive.api.command.Command#rootUrl()
	 */
	@Override
	public String rootUrl() {
		return baseUrl;
	}
	

	/* (non-Javadoc)
	 * @see com.mxhero.plugin.cloudstorage.onedrive.api.command.Command#validate(com.mxhero.plugin.cloudstorage.onedrive.api.command.Validator)
	 */
	@Override
	public void validate(Validator toBeValidated){}
}
