package com.mxhero.plugin.cloudstorage.onedrive.api.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * The Class File.
 */
public class File {

	/** The mime type. */
	private String mimeType;
	
	/** The hashes. */
	private Hashes hashes;
	
	/**
	 * Gets the mime type.
	 *
	 * @return the mime type
	 */
	public String getMimeType() {
		return mimeType;
	}
	
	/**
	 * Sets the mime type.
	 *
	 * @param mimeType the new mime type
	 */
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	/**
	 * Gets the hashes.
	 *
	 * @return the hashes
	 */
	public Hashes getHashes() {
		return hashes;
	}

	/**
	 * Sets the hashes.
	 *
	 * @param hashes the new hashes
	 */
	public void setHashes(Hashes hashes) {
		this.hashes = hashes;
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
