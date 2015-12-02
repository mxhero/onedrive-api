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

public class DiscoveryService {
	
	/** The capability. */
	private String capability;
	
	/** The service api version. */
	private String serviceApiVersion;
	
	/** The service name. */
	private String serviceName; 
	
	/** The service id. */
	private String serviceId;
	
	/** The service endpoint uri. */
	private String serviceEndpointUri;
	
	/** The service resource id. */
	private String serviceResourceId;
	

	
	/**
	 * Gets the capability.
	 *
	 * @return the capability
	 */
	public String getCapability() {
		return capability;
	}



	/**
	 * Sets the capability.
	 *
	 * @param capability the new capability
	 */
	public void setCapability(String capability) {
		this.capability = capability;
	}



	/**
	 * Gets the service api version.
	 *
	 * @return the service api version
	 */
	public String getServiceApiVersion() {
		return serviceApiVersion;
	}



	/**
	 * Sets the service api version.
	 *
	 * @param serviceApiVersion the new service api version
	 */
	public void setServiceApiVersion(String serviceApiVersion) {
		this.serviceApiVersion = serviceApiVersion;
	}



	/**
	 * Gets the service name.
	 *
	 * @return the service name
	 */
	public String getServiceName() {
		return serviceName;
	}



	/**
	 * Sets the service name.
	 *
	 * @param serviceName the new service name
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}



	/**
	 * Gets the service id.
	 *
	 * @return the service id
	 */
	public String getServiceId() {
		return serviceId;
	}



	/**
	 * Sets the service id.
	 *
	 * @param serviceId the new service id
	 */
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}



	/**
	 * Gets the service endpoint uri.
	 *
	 * @return the service endpoint uri
	 */
	public String getServiceEndpointUri() {
		return serviceEndpointUri;
	}



	/**
	 * Sets the service endpoint uri.
	 *
	 * @param serviceEndpointUri the new service endpoint uri
	 */
	public void setServiceEndpointUri(String serviceEndpointUri) {
		this.serviceEndpointUri = serviceEndpointUri;
	}



	/**
	 * Gets the service resource id.
	 *
	 * @return the service resource id
	 */
	public String getServiceResourceId() {
		return serviceResourceId;
	}



	/**
	 * Sets the service resource id.
	 *
	 * @param serviceResourceId the new service resource id
	 */
	public void setServiceResourceId(String serviceResourceId) {
		this.serviceResourceId = serviceResourceId;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
