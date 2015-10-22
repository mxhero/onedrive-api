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
 * The Class Video.
 */
public class Video {
	
	/** The bitrate. */
	private Double bitrate;
	
	/** The duration. */
	private Double duration;
	
	/** The height. */
	private Double height;
	
	/** The width. */
	private Double width;
	  
	/**
	 * Gets the bitrate.
	 *
	 * @return the bitrate
	 */
	public Double getBitrate() {
		return bitrate;
	}

	/**
	 * Sets the bitrate.
	 *
	 * @param bitrate the new bitrate
	 */
	public void setBitrate(Double bitrate) {
		this.bitrate = bitrate;
	}

	/**
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public Double getDuration() {
		return duration;
	}

	/**
	 * Sets the duration.
	 *
	 * @param duration the new duration
	 */
	public void setDuration(Double duration) {
		this.duration = duration;
	}

	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public Double getHeight() {
		return height;
	}

	/**
	 * Sets the height.
	 *
	 * @param height the new height
	 */
	public void setHeight(Double height) {
		this.height = height;
	}

	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public Double getWidth() {
		return width;
	}

	/**
	 * Sets the width.
	 *
	 * @param width the new width
	 */
	public void setWidth(Double width) {
		this.width = width;
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
