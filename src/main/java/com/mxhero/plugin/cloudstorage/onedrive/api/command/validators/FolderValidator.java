package com.mxhero.plugin.cloudstorage.onedrive.api.command.validators;

import java.util.regex.Pattern;

/**
 * The Class FolderValidator.
 */
public class FolderValidator implements Validatable {

	/** The Constant FOLDER_NAMES_NOT_ALLOWED. */
	private static final String FOLDER_NAMES_NOT_ALLOWED = ".*_(files?|Dateien|fichiers|bestanden||archivos|tiedostot|pliki|soubory|elemei|ficheiros|arquivos|dosyalar|datoteke|fitxers|failid|fails|bylos|fajlovi|fitxategiak|private)$";
	
	/** The Constant PATTERN_FOLDER_NAMES_NOT_ALLOWED. */
	private static final Pattern PATTERN_FOLDER_NAMES_NOT_ALLOWED = Pattern.compile(FOLDER_NAMES_NOT_ALLOWED);

	/* (non-Javadoc)
	 * @see com.mxhero.plugin.cloudstorage.onedrive.api.command.validators.Validatable#validate(java.lang.String)
	 */
	@Override
	public void validate(String name) {
		if(name == null || name.isEmpty()) throw new IllegalArgumentException("Empty folder name are not allwed");
		if(PATTERN_FOLDER_NAMES_NOT_ALLOWED.matcher(name).find()) throw new IllegalArgumentException("The following folder names are not allowed to upload for Business API. Folder not allowed: "+FOLDER_NAMES_NOT_ALLOWED+" - Name provided: "+name);
	}

}
