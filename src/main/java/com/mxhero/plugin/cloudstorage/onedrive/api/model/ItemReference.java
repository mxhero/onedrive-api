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
 * The Class ItemReference.
 */
public class ItemReference {
	 
 	/** The drive id. */
 	private String driveId;
	 
 	/** The id. */
 	private String id;
	 
 	/** The path. */
 	private String path;
	 
	/**
	 * Gets the drive id.
	 *
	 * @return the drive id
	 */
	public String getDriveId() {
		return driveId;
	}

	/**
	 * Sets the drive id.
	 *
	 * @param driveId the new drive id
	 */
	public void setDriveId(String driveId) {
		this.driveId = driveId;
	}

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
	 * Gets the path.
	 *
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Sets the path.
	 *
	 * @param path the new path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	/**
	 * The Class Builder.
	 */
	public static class Builder {
		
		/** The instance. */
		private ItemReference instance = new ItemReference();
		
		/**
		 * Drive id.
		 *
		 * @param driveId the drive id
		 * @return the builder
		 */
		public Builder driveId(String driveId){
			this.instance.driveId = driveId;
			return this;
		}
		 
		/**
		 * Id.
		 *
		 * @param id the id
		 * @return the builder
		 */
		public Builder id(String id){
			this.instance.id = id;
			return this;
		}
		
		/**
		 * Path.
		 *
		 * @param path the path
		 * @return the builder
		 */
		public Builder path(String path){
			this.instance.path = path;
			return this;
		}
		
		/**
		 * Builds the.
		 *
		 * @return the item reference
		 */
		public ItemReference build(){
			return instance;
		}
	}
}
