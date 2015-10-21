package com.mxhero.plugin.cloudstorage.onedrive.api.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * The Class FileSystemInfo.
 */
public class FileSystemInfo {

	/** The created date time. */
	private String createdDateTime;
	
	/** The last modified date time. */
	private String lastModifiedDateTime;
	
	/**
	 * Gets the created date time.
	 *
	 * @return the created date time
	 */
	public String getCreatedDateTime() {
		return createdDateTime;
	}

	/**
	 * Sets the created date time.
	 *
	 * @param createdDateTime the new created date time
	 */
	public void setCreatedDateTime(String createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	/**
	 * Gets the last modified date time.
	 *
	 * @return the last modified date time
	 */
	public String getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}

	/**
	 * Sets the last modified date time.
	 *
	 * @param lastModifiedDateTime the new last modified date time
	 */
	public void setLastModifiedDateTime(String lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
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
