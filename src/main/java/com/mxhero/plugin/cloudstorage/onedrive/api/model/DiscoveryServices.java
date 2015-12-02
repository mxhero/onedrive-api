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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * The Class DiscoveryServices.
 */
public class DiscoveryServices {
	
	/** The Constant CAPABILITY. */
	private static final String CAPABILITY = "MyFiles";
	
	/** The Constant VERSION. */
	private static final String VERSION = "v2.0";
	
	/** The value. */
	private List<DiscoveryService> value;
	
	/**
	 * Instantiates a new discovery services.
	 */
	public DiscoveryServices() {
		this.value = new ArrayList<DiscoveryService>();
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public List<DiscoveryService> getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(List<DiscoveryService> value) {
		this.value = value;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	/**
	 * One drive business.
	 *
	 * @return the discovery service
	 */
	public DiscoveryService oneDriveBusiness() {
		for (DiscoveryService discoveryService : value) {
			if(CAPABILITY.equals(discoveryService.getCapability()) && VERSION.equals(discoveryService.getServiceApiVersion())){
				return discoveryService;
			}
		}
		return null;
	}

}
