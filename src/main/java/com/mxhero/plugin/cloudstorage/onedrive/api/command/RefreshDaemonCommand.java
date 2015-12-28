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

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mxhero.plugin.cloudstorage.onedrive.api.Application;
import com.mxhero.plugin.cloudstorage.onedrive.api.Credential;
import com.mxhero.plugin.cloudstorage.onedrive.api.DaemonApplication;
import com.mxhero.plugin.cloudstorage.onedrive.api.DaemonCredential;
import com.mxhero.plugin.cloudstorage.onedrive.api.OneDrive;
import com.mxhero.plugin.cloudstorage.onedrive.api.RedeemDaemonRequest;
import com.mxhero.plugin.cloudstorage.onedrive.api.command.validators.Validators;

// TODO: Auto-generated Javadoc
/**
 * The Class RefreshCommand.
 *
 * @param <T> the generic type
 */
public class RefreshDaemonCommand<T> extends RefreshCommand<T>{
	
	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(RefreshDaemonCommand.class);
	
	/**
	 * Instantiates a new command.
	 *
	 * @param clientBuilder the client builder
	 * @param application the application
	 * @param credential the credential
	 */
	public RefreshDaemonCommand(HttpClientBuilder clientBuilder, Application application, Credential credential){
		super(clientBuilder, application,credential);
		Validate.isTrue(credential instanceof DaemonCredential, "credential must be of type DaemonCredential");
	}
	
	/**
	 * Gets the credential.
	 *
	 * @return the credential
	 */
	public DaemonCredential getCredential() {
		return (DaemonCredential) credential;
	}
	
	/**
	 * Gets the application.
	 *
	 * @return the application
	 */
	public DaemonApplication getApplication(){
		return (DaemonApplication) application;
	}
	
	/**
	 * Refresh token.
	 */
	/* (non-Javadoc)
	 * @see com.mxhero.plugin.cloudstorage.onedrive.api.command.RefreshCommand#refreshToken()
	 */
	@Override
	public void refreshToken() {
		try {
			RedeemDaemonRequest redeemDaemonRequest = RedeemDaemonRequest.builder()
					.certificateSecret(getApplication().getCertificateSecret())
					.clientId(getApplication().getClientId())
					.fileUrlPkcs12Certificate(getApplication().getFileUrlPkcs12Certificate())
					.resourceSharepointId(getCredential().getSharepointResourceId())
					.tenantId(getCredential().getTenantId())
					.build();
			String accessToken = OneDrive.redeemDaemon(redeemDaemonRequest);
			if(StringUtils.isNotEmpty(accessToken)){				
				this.credential.setAccessToken(accessToken);
				HashMap<String, Object> result = new HashMap<String, Object>();
				result.put("access_token", accessToken);
				onSucess(result);
			}else{
				logger.warn("Could not obtain new App Only Token");
				onFailure(404);
			}
		} catch (AuthenticationException e) {
			onFailure(500);
			throw new IllegalStateException("Error obtaining new App Only Token in Refresh token",e);
		}
	}
	
	/**
	 * Can refresh.
	 *
	 * @return true, if successful
	 */
	/* (non-Javadoc)
	 * @see com.mxhero.plugin.cloudstorage.onedrive.api.command.RefreshCommand#mustRefresh()
	 */
	@Override
	public boolean canRefresh() {
		return true;
	}
	
	/**
	 * Base url.
	 *
	 * @return the string
	 */
	/* (non-Javadoc)
	 * @see com.mxhero.plugin.cloudstorage.onedrive.api.command.Command#baseUrl()
	 */
	@Override
	public String baseUrl() {
		return getCredential().getSharepointEndpointUri()+DRIVES+getCredential().getUser();
	}
	
	/**
	 * Validate.
	 *
	 * @param toBeValidated the to be validated
	 */
	/* (non-Javadoc)
	 * @see com.mxhero.plugin.cloudstorage.onedrive.api.command.RefreshCommand#validate(com.mxhero.plugin.cloudstorage.onedrive.api.command.Validator)
	 */
	@Override
	public void validate(Validator toBeValidated) {
		Validators.get(toBeValidated.getType()).validate(toBeValidated.getName());
	}

}
