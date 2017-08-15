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
