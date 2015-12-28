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

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.mxhero.plugin.cloudstorage.onedrive.api.command.TokenRefreshListener;

/**
 * The Class BusinessCredential.
 */
public class DaemonCredential extends BusinessCredential {
	
	/** The tenant id. */
	private String tenantId;
	
	
	/**
	 * Sets the tenant id.
	 *
	 * @param tenantId the new tenant id
	 */
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	/**
	 * Gets the tenant id.
	 *
	 * @return the tenant id
	 */
	public String getTenantId() {
		return tenantId;
	}
	
	/**
	 * Builder.
	 *
	 * @return the builder
	 */
	public static BuilderDaemon builderDaemon(){
		return new BuilderDaemon();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	/* (non-Javadoc)
	 * @see com.mxhero.plugin.cloudstorage.onedrive.api.Credential#validate()
	 */
	@Override
	protected void validate() {
		super.validate();
	}

	/**
	 * The Class BuilderBusiness.
	 */
	public static class BuilderDaemon{
		
		/** The instance. */
		private DaemonCredential instance = new DaemonCredential();
		
		
		/**
		 * Tenant id.
		 *
		 * @param tenantId the tenant id
		 * @return the builder daemon
		 */
		public BuilderDaemon tenantId(String tenantId){
			this.instance.tenantId = tenantId;
			return this;
		}

		/**
		 * Sharepoint endpoint uri.
		 *
		 * @param uri the uri
		 * @return the builder business
		 */
		public BuilderDaemon sharepointEndpointUri(String uri){
			this.instance.setSharepointEndpointUri(uri);
			return this;
		}
		
		/**
		 * Sharepoint resource id.
		 *
		 * @param sharepointResourceId the sharepoint resource id
		 * @return the builder business
		 */
		public BuilderDaemon sharepointResourceId(String sharepointResourceId){
			this.instance.setSharepointResourceId(sharepointResourceId);
			return this;
		}
		
		/**
		 * Access token.
		 *
		 * @param accessToken the access token
		 * @return the builder business
		 */
		public BuilderDaemon accessToken(String accessToken) {
			this.instance.setAccessToken(accessToken);
			return this;
		}

		/**
		 * Listener.
		 *
		 * @param listener the listener
		 * @return the builder business
		 */
		public BuilderDaemon listener(TokenRefreshListener listener) {
			this.instance.setListener(listener);
			return this;
		}

		/**
		 * Token type.
		 *
		 * @param tokenType the token type
		 * @return the builder business
		 */
		public BuilderDaemon tokenType(String tokenType) {
			this.instance.setTokenType(tokenType);
			return this;
		}
		
		/**
		 * User.
		 *
		 * @param user the user
		 * @return the builder business
		 */
		public BuilderDaemon user(String user) {
			this.instance.setUser(user);
			return this;
		}

		/**
		 * Builds the.
		 *
		 * @return the daemon credential
		 */
		public DaemonCredential build() {
			this.instance.validate();
			return this.instance;
		}
		
	}

}
