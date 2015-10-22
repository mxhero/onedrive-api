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
