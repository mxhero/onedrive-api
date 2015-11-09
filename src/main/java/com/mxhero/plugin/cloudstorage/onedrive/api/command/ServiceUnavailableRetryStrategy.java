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

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mxhero.plugin.cloudstorage.onedrive.api.ApiEnviroment;

/**
 * The Class ServiceUnavailableRetryStrategy.
 */
public class ServiceUnavailableRetryStrategy implements org.apache.http.client.ServiceUnavailableRetryStrategy {

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(ServiceUnavailableRetryStrategy.class);
	
	/** The Constant retryInterval. */
	private static final Integer retryInterval = 1000;
	
	/** The Constant retries. */
	private static final Integer retries  = Integer.parseInt(ApiEnviroment.retries.getValue());

	/* (non-Javadoc)
	 * @see org.apache.http.client.ServiceUnavailableRetryStrategy#getRetryInterval()
	 */
	@Override
	public long getRetryInterval() {
		return retryInterval;
	}

	/* (non-Javadoc)
	 * @see org.apache.http.client.ServiceUnavailableRetryStrategy#retryRequest(org.apache.http.HttpResponse, int, org.apache.http.protocol.HttpContext)
	 */
	@Override
	public boolean retryRequest(final HttpResponse response, final int executionCount, final HttpContext context) {
		boolean shouldRetry =  executionCount <= retries && isServiceUnavailable(response.getStatusLine().getStatusCode());
		logger.debug("shouldRetry serviceUnavailable: ()",shouldRetry);
		return shouldRetry;
	}

	/**
	 * Checks if is service unavailable.
	 *
	 * @param code the code
	 * @return true, if is service unavailable
	 */
	public static boolean isServiceUnavailable(final int code){
		switch(code){
			case HttpStatus.SC_SERVICE_UNAVAILABLE: return true;
			case HttpStatus.SC_REQUEST_TIMEOUT: return true;
			case 429: return true;
			case HttpStatus.SC_INTERNAL_SERVER_ERROR: return true;
			case HttpStatus.SC_BAD_GATEWAY: return true;
			case HttpStatus.SC_GATEWAY_TIMEOUT: return true;
		}
		return false;
	}

}
