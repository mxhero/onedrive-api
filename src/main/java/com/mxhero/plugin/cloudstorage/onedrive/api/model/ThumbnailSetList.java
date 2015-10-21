package com.mxhero.plugin.cloudstorage.onedrive.api.model;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * The Class ThumbnailSetList.
 */
public class ThumbnailSetList {

	/** The value. */
	private List<ThumbnailSet> value;

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public List<ThumbnailSet> getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(List<ThumbnailSet> value) {
		this.value = value;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}
