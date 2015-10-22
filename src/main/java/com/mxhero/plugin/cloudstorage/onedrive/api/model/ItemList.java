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
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class ItemList.
 */
public class ItemList {

	/** The value. */
	private List<Item> value;
	
	/** The next link. */
	@JsonProperty("@odata.nextLink")
	private String nextLink;
	
	/** The approximate count. */
	@JsonProperty("@search.approximateCount")
	private Integer approximateCount;

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public List<Item> getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(List<Item> value) {
		this.value = value;
	}

	/**
	 * Gets the next link.
	 *
	 * @return the next link
	 */
	public String getNextLink() {
		return nextLink;
	}

	/**
	 * Sets the next link.
	 *
	 * @param nextLink the new next link
	 */
	public void setNextLink(String nextLink) {
		this.nextLink = nextLink;
	}

	/**
	 * Gets the approximate count.
	 *
	 * @return the approximate count
	 */
	public Integer getApproximateCount() {
		return approximateCount;
	}

	/**
	 * Sets the approximate count.
	 *
	 * @param approximateCount the new approximate count
	 */
	public void setApproximateCount(Integer approximateCount) {
		this.approximateCount = approximateCount;
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
