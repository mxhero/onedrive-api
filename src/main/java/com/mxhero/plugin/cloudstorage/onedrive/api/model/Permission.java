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
	
	/** The role. */
	private List<String> role;
	
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
	 * Gets the role.
	 *
	 * @return the role
	 */
	public List<String> getRole() {
		return role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(List<String> role) {
		this.role = role;
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
