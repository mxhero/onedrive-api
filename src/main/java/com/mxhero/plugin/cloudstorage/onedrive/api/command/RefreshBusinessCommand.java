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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.mxhero.plugin.cloudstorage.onedrive.api.ApiEnviroment;
import com.mxhero.plugin.cloudstorage.onedrive.api.Application;
import com.mxhero.plugin.cloudstorage.onedrive.api.BusinessCredential;
import com.mxhero.plugin.cloudstorage.onedrive.api.Credential;
import com.mxhero.plugin.cloudstorage.onedrive.api.command.validators.Validators;

// TODO: Auto-generated Javadoc
/**
 * The Class RefreshCommand.
 *
 * @param <T> the generic type
 */
public class RefreshBusinessCommand<T> extends RefreshCommand<T>{
	
	/**
	 * Instantiates a new command.
	 *
	 * @param clientBuilder the client builder
	 * @param application the application
	 * @param credential the credential
	 */
	public RefreshBusinessCommand(HttpClientBuilder clientBuilder, Application application, Credential credential){
		super(clientBuilder, application,credential);
		Validate.isTrue(credential instanceof BusinessCredential, "credential must be of type BusinessCredential");
	}

	
	/* (non-Javadoc)
	 * @see com.mxhero.plugin.cloudstorage.onedrive.api.command.RefreshCommand#callRefreshToken()
	 */
	@Override
	public CloseableHttpResponse callRefreshToken()
			throws UnsupportedEncodingException, IOException, ClientProtocolException {
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("client_id", getApplication().getClientId()));
		params.add(new BasicNameValuePair("redirect_uri", getApplication().getRedirectUri()));
		params.add(new BasicNameValuePair("client_secret",getApplication().getClientSecret()));
		params.add(new BasicNameValuePair("refresh_token",getCredential().getRefreshToken()));
		params.add(new BasicNameValuePair("grant_type","refresh_token"));
		params.add(new BasicNameValuePair("resource",getCredential().getSharepointResourceId()));
		HttpPost httpPost = new HttpPost(ApiEnviroment.tokenBusinessBaseUrl.getValue());		
		httpPost.setEntity(new UrlEncodedFormEntity(params));
		return HttpClientBuilder.create().build().execute(httpPost);
	}
	
	/**
	 * Gets the credential.
	 *
	 * @return the credential
	 */
	public BusinessCredential getCredential() {
		return (BusinessCredential) credential;
	}
	
	/* (non-Javadoc)
	 * @see com.mxhero.plugin.cloudstorage.onedrive.api.command.Command#baseUrl()
	 */
	@Override
	public String baseUrl() {
		return getCredential().getSharepointEndpointUri();
	}
	
	/* (non-Javadoc)
	 * @see com.mxhero.plugin.cloudstorage.onedrive.api.command.RefreshCommand#validate(com.mxhero.plugin.cloudstorage.onedrive.api.command.Validator)
	 */
	@Override
	public void validate(Validator toBeValidated) {
		Validators.get(toBeValidated.getType()).validate(toBeValidated.getName());
	}

}
