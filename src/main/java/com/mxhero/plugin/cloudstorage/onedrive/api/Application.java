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

/*
 * #%L
 * com.mxhero.plugin.cloudstorage.onedrive
 * %%
 * Copyright (C) 2013 - 2015 mxHero Inc.
 * %%
 * MXHERO END USER LICENSE AGREEMENT
 * 
 * IMPORTANT-READ CAREFULLY:  BY DOWNLOADING, INSTALLING, OR USING THE SOFTWARE, YOU (THE INDIVIDUAL OR LEGAL ENTITY) AGREE TO BE BOUND BY THE TERMS OF THIS END USER LICENSE AGREEMENT (EULA).  IF YOU DO NOT AGREE TO THE TERMS OF THIS EULA, YOU MUST NOT DOWNLOAD, INSTALL, OR USE THE SOFTWARE, AND YOU MUST DELETE OR RETURN THE UNUSED SOFTWARE TO THE VENDOR FROM WHICH YOU ACQUIRED IT WITHIN THIRTY (30) DAYS AND REQUEST A REFUND OF THE LICENSE FEE, IF ANY, THAT YOU PAID FOR THE SOFTWARE.
 * 
 * READ LICENSE.txt file to see details of this agreement.
 * #L%
 */


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * The Class Application.
 */
public class Application {

	/** The client id. */
	private String clientId;
	
	/** The redirect uri. */
	private String redirectUri;
	
	/** The client secret. */
	private String clientSecret;
	
	/**
	 * Instantiates a new application.
	 */
	public Application() {
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
	 * Sets the client id.
	 *
	 * @param clientId the new client id
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * Gets the redirect uri.
	 *
	 * @return the redirect uri
	 */
	public String getRedirectUri() {
		return redirectUri;
	}

	/**
	 * Sets the redirect uri.
	 *
	 * @param redirectUri the new redirect uri
	 */
	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
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
	 * Sets the client secret.
	 *
	 * @param clientSecret the new client secret
	 */
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	/**
	 * Builder.
	 *
	 * @return the builder
	 */
	public static Builder builder(){
		return new Builder();
	}
	
	/**
	 * The Class Builder.
	 */
	public static class Builder {
		
		/** The app. */
		private Application app = new Application();
		
		/**
		 * Client id.
		 *
		 * @param clientId the client id
		 * @return the builder
		 */
		public Builder clientId(String clientId){
			this.app.clientId = clientId;
			return this;
		}

		/**
		 * Client secret.
		 *
		 * @param clientSecret the client secret
		 * @return the builder
		 */
		public Builder clientSecret(String clientSecret){
			this.app.clientSecret = clientSecret;
			return this;
		}

		/**
		 * Redirect uri.
		 *
		 * @param redirectUri the redirect uri
		 * @return the builder
		 */
		public Builder redirectUri(String redirectUri){
			this.app.redirectUri = redirectUri;
			return this;
		}

		/**
		 * Builds the.
		 *
		 * @return the application
		 */
		public Application build(){
			this.app.validate();
			return this.app;
		}
		
	
	}

	/**
	 * Validate.
	 */
	public void validate() {
		Validate.notEmpty(clientId, "clientId at least must be present");
	}
	
}
