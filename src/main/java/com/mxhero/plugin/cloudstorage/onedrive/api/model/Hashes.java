package com.mxhero.plugin.cloudstorage.onedrive.api.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * The Class Hashes.
 */
public class Hashes {
	
	/** The crc32 hash. */
	private String crc32Hash;
	
	/** The sha1 hash. */
	private String sha1Hash;

	/**
	 * Gets the crc32 hash.
	 *
	 * @return the crc32 hash
	 */
	public String getCrc32Hash() {
		return crc32Hash;
	}

	/**
	 * Sets the crc32 hash.
	 *
	 * @param crc32Hash the new crc32 hash
	 */
	public void setCrc32Hash(String crc32Hash) {
		this.crc32Hash = crc32Hash;
	}

	/**
	 * Gets the sha1 hash.
	 *
	 * @return the sha1 hash
	 */
	public String getSha1Hash() {
		return sha1Hash;
	}

	/**
	 * Sets the sha1 hash.
	 *
	 * @param sha1Hash the new sha1 hash
	 */
	public void setSha1Hash(String sha1Hash) {
		this.sha1Hash = sha1Hash;
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
