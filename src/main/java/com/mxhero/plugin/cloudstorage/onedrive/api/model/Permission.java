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

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * The Class Permission.
 */
public class Permission {

	/** The id. */
	private String id;
	
	/** The roles. */
	private List<String> roles;
	
	/** The link. */
	private SharingLink link;
	
	/** The inherited from. */
	private ItemReference inheritedFrom;
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the roles.
	 *
	 * @return the roles
	 */
	public List<String> getRoles() {
		return roles;
	}

	/**
	 * Sets the roles.
	 *
	 * @param roles the new roles
	 */
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	/**
	 * Gets the link.
	 *
	 * @return the link
	 */
	public SharingLink getLink() {
		return link;
	}

	/**
	 * Sets the link.
	 *
	 * @param link the new link
	 */
	public void setLink(SharingLink link) {
		this.link = link;
	}

	/**
	 * Gets the inherited from.
	 *
	 * @return the inherited from
	 */
	public ItemReference getInheritedFrom() {
		return inheritedFrom;
	}

	/**
	 * Sets the inherited from.
	 *
	 * @param inheritedFrom the new inherited from
	 */
	public void setInheritedFrom(ItemReference inheritedFrom) {
		this.inheritedFrom = inheritedFrom;
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
