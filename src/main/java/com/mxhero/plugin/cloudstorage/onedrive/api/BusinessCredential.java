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

/**
 * The Class BusinessCredential.
 */
public class BusinessCredential extends Credential {
	
	/** The sharepoint url. */
	private String sharepointEndpointUri;

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
	 * The Class BuilderBusiness.
	 */
	public static class BuilderBusiness extends Builder{
		
		/**
		 * Instantiates a new builder business.
		 */
		public BuilderBusiness() {
			this.instance = new BusinessCredential();
		}
		
		/**
		 * Sharepoint endpoint uri.
		 *
		 * @param uri the uri
		 * @return the builder business
		 */
		public BuilderBusiness sharepointEndpointUri(String uri){
			((BusinessCredential)this.instance).sharepointEndpointUri = uri;
			return this;
		}
		
	}

}
