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

package com.mxhero.plugin.cloudstorage.onedrive.api.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.mxhero.plugin.cloudstorage.onedrive.api.Credential;

/**
 * The Class OneDriveBusiness.
 */
public class OneDriveBusiness {
	
	/** The credential. */
	private Credential credential;
	
	/** The sharepoint uri. */
	private String sharepointEndpointUri;
	
	/** The sharepoint resource id. */
	private String sharepointResourceId;

	/**
	 * Gets the credential.
	 *
	 * @return the credential
	 */
	public Credential getCredential() {
		return credential;
	}

	/**
	 * Sets the credential.
	 *
	 * @param credential the new credential
	 */
	public void setCredential(Credential credential) {
		this.credential = credential;
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
	 * Gets the sharepoint endpoint uri.
	 *
	 * @return the sharepoint endpoint uri
	 */
	public String getSharepointEndpointUri() {
		return sharepointEndpointUri;
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
	public static class Builder{
		
		/** The target. */
		private OneDriveBusiness target = new OneDriveBusiness();
		
		/**
		 * Credential.
		 *
		 * @param cred the cred
		 * @return the builder
		 */
		public Builder credential(Credential cred){
			this.target.credential = cred;
			return this;
		}
		
		/**
		 * Sharepoint uri.
		 *
		 * @param sharepointEndpointUri the sharepoint endpoint uri
		 * @return the builder
		 */
		public Builder sharepointEndpointUri(String sharepointEndpointUri){
			this.target.sharepointEndpointUri = sharepointEndpointUri;
			return this;
		}
		
		/**
		 * Sharepoint resource id.
		 *
		 * @param sharepointResourceId the sharepoint resource id
		 * @return the builder
		 */
		public Builder sharepointResourceId(String sharepointResourceId){
			this.target.sharepointResourceId = sharepointResourceId;
			return this;
		}
		
		/**
		 * Builds the.
		 *
		 * @return the one drive business
		 */
		public OneDriveBusiness build(){
			return this.target;
		}
	}

}
