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
 * The Class ThumbnailSet.
 */
public class ThumbnailSet {

	/** The id. */
	private String id;
	
	/** The small. */
	private Thumbnail small;
	
	/** The medium. */
	private Thumbnail medium;
	
	/** The large. */
	private Thumbnail large;
	
	/** The source. */
	private Thumbnail source;

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
	 * Gets the small.
	 *
	 * @return the small
	 */
	public Thumbnail getSmall() {
		return small;
	}

	/**
	 * Sets the small.
	 *
	 * @param small the new small
	 */
	public void setSmall(Thumbnail small) {
		this.small = small;
	}

	/**
	 * Gets the medium.
	 *
	 * @return the medium
	 */
	public Thumbnail getMedium() {
		return medium;
	}

	/**
	 * Sets the medium.
	 *
	 * @param medium the new medium
	 */
	public void setMedium(Thumbnail medium) {
		this.medium = medium;
	}

	/**
	 * Gets the large.
	 *
	 * @return the large
	 */
	public Thumbnail getLarge() {
		return large;
	}

	/**
	 * Sets the large.
	 *
	 * @param large the new large
	 */
	public void setLarge(Thumbnail large) {
		this.large = large;
	}

	/**
	 * Gets the source.
	 *
	 * @return the source
	 */
	public Thumbnail getSource() {
		return source;
	}

	/**
	 * Sets the source.
	 *
	 * @param source the new source
	 */
	public void setSource(Thumbnail source) {
		this.source = source;
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
