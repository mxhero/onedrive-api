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
