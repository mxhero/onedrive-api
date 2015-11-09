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
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLException;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpCoreContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mxhero.plugin.cloudstorage.onedrive.api.ApiEnviroment;

/**
 * The Class HttpRequestRetryHandler.
 */
public class HttpRequestRetryHandler implements org.apache.http.client.HttpRequestRetryHandler{

	private static Logger logger = LoggerFactory.getLogger(HttpRequestRetryHandler.class);
	
	/** The Constant retries. */
	private static final Integer retries  = Integer.parseInt(ApiEnviroment.retries.getValue());
	
	/* (non-Javadoc)
	 * @see org.apache.http.client.HttpRequestRetryHandler#retryRequest(java.io.IOException, int, org.apache.http.protocol.HttpContext)
	 */
	@Override
	public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
		if (executionCount >= retries) {
			logger.debug("no more retries, cancel");
			return false;
        }
        if (exception instanceof NoHttpResponseException) {
        	logger.debug("is NoHttpResponseException, retrying...");
            return true;
        }
        if(exception instanceof ConnectTimeoutException || exception instanceof ConnectionPoolTimeoutException){
        	logger.debug("is ConnectTimeoutException or ConnectionPoolTimeoutException, retrying...");
        	return true;
        }
        if (exception instanceof InterruptedIOException) {
        	logger.debug("is InterruptedIOException, retrying...");
            return true;
        }
        if (exception instanceof UnknownHostException) {
        	logger.debug("is UnknownHostException, cancel");
            return false;
        }
        if (exception instanceof ConnectException) {
        	logger.debug("is ConnectException, cancel");
            return false;
        }
        if (exception instanceof SSLException) {
        	logger.debug("is SSLException, cancel");
            return false;
        }
        HttpRequest request = (HttpRequest) context.getAttribute(HttpCoreContext.HTTP_REQUEST);
        boolean idempotent = !(request instanceof HttpEntityEnclosingRequest); 
        if (idempotent) {
        	logger.debug("is idempotent, retrying...");
            return true;
        }
        Boolean b = (Boolean)
        context.getAttribute(HttpCoreContext.HTTP_REQ_SENT);
        boolean sent = (b != null && b.booleanValue());
        if (!sent) {
        	logger.debug("is not sent, retrying...");
	        return true;
        }
        return false;
	}

}
