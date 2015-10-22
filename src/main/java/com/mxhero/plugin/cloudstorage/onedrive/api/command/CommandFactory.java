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


import java.lang.reflect.Proxy;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.Validate;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import com.mxhero.plugin.cloudstorage.onedrive.api.Credential;
import com.mxhero.plugin.cloudstorage.onedrive.api.ApiEnviroment;
import com.mxhero.plugin.cloudstorage.onedrive.api.Application;

/**
 * A factory for creating RetryCommand objects.
 */
public class CommandFactory {

	/** The conn manager. */
	private HttpClientConnectionManager connManager;
	
	/** The credential. */
	private Credential credential;
	
	/** The application. */
	private Application application;
	
	/**
	 * Instantiates a new command factory.
	 *
	 * @param credential the credential
	 */
	public CommandFactory(Application application, Credential credential){
		Validate.notNull(credential, "credential may not be null");
		this.credential=credential;
		this.application=application;
		this.connManager=httpClientConnectionManager();
	}
	
	/**
	 * Creates the.
	 *
	 * @param <T> the generic type
	 * @return the command
	 */
	@SuppressWarnings("unchecked")
	public <T> Command<T> create(){
		return (Command<T>) Proxy.newProxyInstance(
				Command.class.getClassLoader(),
                new Class[] { Command.class },
                new RetryInvocationHandler<T>(new RetryCommand<T>(httpClientBuilder(),application,credential)));
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
	protected HttpClientBuilder httpClientBuilder() {
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
