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
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mxhero.plugin.cloudstorage.onedrive.api.OneDrive;
import com.mxhero.plugin.cloudstorage.onedrive.api.model.ResponseError;

/**
 * The Class ApiException.
 */
@SuppressWarnings("serial")
public class ApiException extends RuntimeException{

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(ApiException.class);
	
	/** The status code. */
	private int statusCode;
	
	/** The reason phrase. */
	private String reasonPhrase;
	
	/** The error. */
	private ResponseError error;

	/**
	 * Instantiates a new api exception.
	 *
	 * @param response the response
	 */
	public ApiException(HttpResponse response) {
		super(response.getStatusLine().toString());
		this.reasonPhrase=response.getStatusLine().toString();
		this.statusCode=response.getStatusLine().getStatusCode();
		try {
			JsonNode content = OneDrive.JACKSON.readTree(EntityUtils.toString(response.getEntity()));
			if(content.has("error")){
				this.error=OneDrive.JACKSON.treeToValue(content.get("error"), ResponseError.class);
			}
		} catch (Exception e) {
			logger.debug("error reading error response",e);
		}
	}

	/**
	 * Gets the status code.
	 *
	 * @return the status code
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * Sets the status code.
	 *
	 * @param statusCode the new status code
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * Gets the reason phrase.
	 *
	 * @return the reason phrase
	 */
	public String getReasonPhrase() {
		return reasonPhrase;
	}

	/**
	 * Sets the reason phrase.
	 *
	 * @param reasonPhrase the new reason phrase
	 */
	public void setReasonPhrase(String reasonPhrase) {
		this.reasonPhrase = reasonPhrase;
	}

	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	public ResponseError getError() {
		return error;
	}

	/**
	 * Sets the error.
	 *
	 * @param error the new error
	 */
	public void setError(ResponseError error) {
		this.error = error;
	}
	
}
