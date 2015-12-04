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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.mxhero.plugin.cloudstorage.onedrive.api.command.TokenRefreshListener;

/**
 * The Class BusinessCredential.
 */
public class BusinessCredential extends Credential {
	
	/** The sharepoint url. */
	private String sharepointEndpointUri;
	
	/** The sharepoint resource id. */
	private String sharepointResourceId;

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
	 * Builder.
	 *
	 * @return the builder
	 */
	public static BuilderBusiness builder(){
		return new BuilderBusiness();
	}
	
	/**
	 * Gets the sharepoint resource id.
	 *
	 * @return the sharepoint resource id
	 */
	public String getSharepointResourceId() {
		return sharepointResourceId;
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
		Validate.isTrue(!StringUtils.isBlank(sharepointEndpointUri),
				"sharepointEndpointUri may not be blank in business Credential");
		Validate.isTrue(!StringUtils.isBlank(sharepointResourceId),
				"sharepointResourceId may not be blank in business Credential");
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
	 * The Class BuilderBusiness.
	 */
	public static class BuilderBusiness{
		
		private BusinessCredential instance = new BusinessCredential();
		
		/**
		 * Sharepoint endpoint uri.
		 *
		 * @param uri the uri
		 * @return the builder business
		 */
		public BuilderBusiness sharepointEndpointUri(String uri){
			this.instance.sharepointEndpointUri = uri;
			return this;
		}
		
		/**
		 * Sharepoint resource id.
		 *
		 * @param sharepointResourceId the sharepoint resource id
		 * @return the builder business
		 */
		public BuilderBusiness sharepointResourceId(String sharepointResourceId){
			this.instance.sharepointResourceId = sharepointResourceId;
			return this;
		}
		
		public BuilderBusiness accessToken(String accessToken) {
			this.instance.setAccessToken(accessToken);
			return this;
		}
		
		public BuilderBusiness refreshToken(String refreshToken) {
			this.instance.setRefreshToken(refreshToken);
			return this;
		}

		public BuilderBusiness listener(TokenRefreshListener listener) {
			this.instance.setListener(listener);
			return this;
		}

		public BuilderBusiness scope(String scope) {
			this.instance.setScope(scope);
			return this;
		}

		public BuilderBusiness tokenType(String tokenType) {
			this.instance.setTokenType(tokenType);
			return this;
		}
		
		public BuilderBusiness user(String user) {
			this.instance.setUser(user);
			return this;
		}

		public BuilderBusiness userId(String userId) {
			this.instance.setUserId(userId);
			return this;
		}

		public BusinessCredential build() {
			this.instance.validate();
			return this.instance;
		}
		
	}

}
