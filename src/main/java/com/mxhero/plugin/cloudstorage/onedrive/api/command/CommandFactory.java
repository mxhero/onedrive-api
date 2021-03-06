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
import java.util.Map;

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

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.Validate;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import com.mxhero.plugin.cloudstorage.onedrive.api.ApiEnviroment;
import com.mxhero.plugin.cloudstorage.onedrive.api.Application;
import com.mxhero.plugin.cloudstorage.onedrive.api.BusinessCredential;
import com.mxhero.plugin.cloudstorage.onedrive.api.Credential;
import com.mxhero.plugin.cloudstorage.onedrive.api.DaemonApplication;
import com.mxhero.plugin.cloudstorage.onedrive.api.DaemonCredential;
import com.mxhero.plugin.cloudstorage.onedrive.api.OneDriveType;

/**
 * A factory for creating RefreshCommand objects.
 */
public class CommandFactory {

	/** The conn manager. */
	private HttpClientConnectionManager connManager;
	
	/** The credential. */
	private Credential credential;
	
	/** The application. */
	private Application application;

	/** The type. */
	private final OneDriveType type;
	
	/** The builders. */
	@SuppressWarnings("all")
	private Map<OneDriveType, Class> builders = new HashMap<OneDriveType, Class>(){{
		put(OneDriveType.onedrive, RefreshCommand.class);
		put(OneDriveType.ondrive_business, RefreshBusinessCommand.class);
		put(OneDriveType.onedrive_daemon, RefreshDaemonCommand.class);
	}};
	
	/**
	 * Instantiates a new command factory.
	 *
	 * @param application the application
	 * @param credential the credential
	 */
	public CommandFactory(Application application, Credential credential){
		Validate.notNull(credential, "credential may not be null");
		this.credential=credential;
		this.application=application;
		this.connManager=httpClientConnectionManager();
		this.type = delegateTyep();
	}
	
	/**
	 * Delegate tyep.
	 *
	 * @return the one drive type
	 */
	private OneDriveType delegateTyep() {
		if(credential instanceof DaemonCredential && application instanceof DaemonApplication) return OneDriveType.onedrive_daemon;
		if(credential instanceof BusinessCredential) return OneDriveType.ondrive_business;
		return OneDriveType.onedrive;
	}

	/**
	 * Creates the.
	 *
	 * @param <T> the generic type
	 * @return the command
	 */
	@SuppressWarnings("unchecked")
	public <T> Command<T> create(){
		try {
			return (Command<T>) builders.get(type).getConstructor(HttpClientBuilder.class, Application.class,Credential.class).newInstance(httpClientBuilder(),application,credential);
		} catch (Exception e) {
			throw new IllegalStateException("Error creating factory for builder", e);
		}
	}
	
	/**
	 * Http client connection manager.
	 *
	 * @return the http client connection manager
	 */
	protected HttpClientConnectionManager httpClientConnectionManager() {
		PoolingHttpClientConnectionManager  connectionManager = new PoolingHttpClientConnectionManager ();
		return connectionManager;
	}
	
	/**
	 * Http client builder.
	 *
	 * @return the closeable http client
	 */
	public HttpClientBuilder httpClientBuilder() {
		RequestConfig requestConfig = RequestConfig
				.custom()
				.setConnectionRequestTimeout(
						Integer.parseInt(ApiEnviroment.connectionRequestTimeout
								.getValue()))
				.setConnectTimeout(
						Integer.parseInt(ApiEnviroment.connectionTimeout
								.getValue()))
				.setSocketTimeout(
						Integer.parseInt(ApiEnviroment.socketTimeout.getValue()))
				.build();
		return HttpClientBuilder.create()
				.setRetryHandler(new HttpRequestRetryHandler())
				.setServiceUnavailableRetryStrategy(new ServiceUnavailableRetryStrategy())
				.setDefaultRequestConfig(requestConfig)
				.setConnectionManager(connManager);
	}
	
	/**
	 * Shutdown.
	 */
	public void shutdown(){
		connManager.closeExpiredConnections();
		connManager.closeIdleConnections(5, TimeUnit.SECONDS);
		connManager.shutdown();
	}

	
}
