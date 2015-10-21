package com.mxhero.plugin.cloudstorage.onedrive.api.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * The Class Folder.
 */
public class Folder {

	/** The child count. */
	private Long childCount;
	
	/**
	 * Gets the child count.
	 *
	 * @return the child count
	 */
	public Long getChildCount() {
		return childCount;
	}

	/**
	 * Sets the child count.
	 *
	 * @param childCount the new child count
	 */
	public void setChildCount(Long childCount) {
		this.childCount = childCount;
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
