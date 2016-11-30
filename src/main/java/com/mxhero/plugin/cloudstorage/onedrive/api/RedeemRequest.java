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

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * The Class RedeemRequest.
 */
public class RedeemRequest {
	
	/** The code. */
	private String code;
	
	/** The client id. */
	private String clientId;
	
	/** The client secret. */
	private String clientSecret;
	
	/** The redirect uri. */
	private String redirectUri;
	
	/** The sharepoint host. */
	private String sharepointResourceId;
	
	/** The sharepoint endpoint uri. */
	private String sharepointEndpointUri;
	
	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Gets the client id.
	 *
	 * @return the client id
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * Gets the client secret.
	 *
	 * @return the client secret
	 */
	public String getClientSecret() {
		return clientSecret;
	}

	/**
	 * Gets the redirect uri.
	 *
	 * @return the redirect uri
	 */
	public String getRedirectUri() {
		return redirectUri;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	/**
	 * Validate.
	 */
	public void validate(){
		Validate.notEmpty(code, "code field must be provided");
		Validate.notEmpty(clientId, "clientId field must be provided");
		Validate.notEmpty(clientSecret, "clientSecret field must be provided");
		Validate.notEmpty(redirectUri, "redirectUri field must be provided");
		Validate.notEmpty(sharepointEndpointUri, "sharepointEndpointUri field must be provided");
		Validate.notEmpty(sharepointResourceId, "sharepointResourceId field must be provided");
	}
	
	/**
	 * Builder.
	 *
	 * @return the bulider
	 */
	public static Builder builder(){
		return new Builder();
	}


	/**
	 * Gets the sharepoint resource id.
	 *
	 * @return the sharepoint resource id
	 */
	public String getSharepointResourceId() {
		return sharepointResourceId;
	}

	/**
	 * Sets the sharepoint resource id.
	 *
	 * @param sharepointResourceId the new sharepoint resource id
	 */
	public void setSharepointResourceId(String sharepointResourceId) {
		this.sharepointResourceId = sharepointResourceId;
	}


	/**
	 * Gets the sharepoint endpoint uri.
	 *
	 * @return the sharepoint endpoint uri
	 */
	public String getSharepointEndpointUri() {
		return sharepointEndpointUri;
	}

	/**
	 * Sets the sharepoint endpoint uri.
	 *
	 * @param sharepointEndpointUri the new sharepoint endpoint uri
	 */
	public void setSharepointEndpointUri(String sharepointEndpointUri) {
		this.sharepointEndpointUri = sharepointEndpointUri;
	}


	/**
	 * The Class Builder.
	 */
	public static class Builder {
		
		/** The instance. */
		private RedeemRequest instance = new RedeemRequest();
		
		/**
		 * Code.
		 *
		 * @param code the code
		 * @return the builder
		 */
		public Builder code(String code){
			this.instance.code = code;
			return this;
		}

		/**
		 * Client id.
		 *
		 * @param clientId the client id
		 * @return the builder
		 */
		public Builder clientId(String clientId){
			this.instance.clientId = clientId;
			return this;
		}

		/**
		 * Client secret.
		 *
		 * @param clientSecret the client secret
		 * @return the builder
		 */
		public Builder clientSecret(String clientSecret){
			this.instance.clientSecret = clientSecret;
			return this;
		}

		/**
		 * Redirect uri.
		 *
		 * @param redirectUri the redirect uri
		 * @return the builder
		 */
		public Builder redirectUri(String redirectUri){
			this.instance.redirectUri = redirectUri;
			return this;
		}
		
		/**
		 * Sharepoint host.
		 *
		 * @param sharepointResourceId the sharepoint resource id
		 * @return the builder
		 */
		public Builder sharepointResourceId(String sharepointResourceId){
			this.instance.sharepointResourceId = sharepointResourceId;
			return this;
		}
		
		
		/**
		 * Sharepoint endpoint uri.
		 *
		 * @param sharepointEndpointUri the sharepoint endpoint uri
		 * @return the builder
		 */
		public Builder sharepointEndpointUri(String sharepointEndpointUri){
			this.instance.sharepointEndpointUri = sharepointEndpointUri;
			return this;
		}

		
		/**
		 * Builds the.
		 *
		 * @return the redeem daemon request
		 */
		public RedeemRequest build(){
			this.instance.validate();
			return this.instance;
		}
	}
}