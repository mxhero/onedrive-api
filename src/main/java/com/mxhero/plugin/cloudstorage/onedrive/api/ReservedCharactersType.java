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
package com.mxhero.plugin.cloudstorage.onedrive.api;

/**
 * The Enum ReservedCharactersType.
 */
public enum ReservedCharactersType {
	
	/** The sharepoint 2013. */
	sharepoint_2013("[/\\*<>?:|#%\"\\$\\{\\}~\\&]"),
	
	/** The online. */
	online("[/\\*<>?:|\"]"),
	
	/** The online with office 2010. */
	online_with_office2010("[/\\*<>?:|\"\\&]");
	
	/** The reserved characters. */
	private String reservedCharacters;
	
	/**
	 * Instantiates a new reserved characters type.
	 *
	 * @param reservedCharacters the reserved characters
	 */
	private ReservedCharactersType(String reservedCharacters){
		this.reservedCharacters=reservedCharacters;
	}
	
	/**
	 * Reserved characters.
	 *
	 * @return the string
	 */
	public String reservedCharacters(){
		return this.reservedCharacters;
	}
}
