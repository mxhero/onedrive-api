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

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;

/**
 * The Class FileValidator.
 */
public class FileValidator implements Validatable {

	/** The Constant EXTENSTIONS_NOT_ALLOWED. */
	private static final String EXTENSTIONS_NOT_ALLOWED = "ashx|asmx|json|soap|svc|xamlx|tmp|df_store";
	
	/** The Constant FILES_NOT_ALLOWED. */
	private static final List<String> FILES_NOT_ALLOWED = Arrays.asList("desktop.ini","thumbs.db","ehthumbs.db");
	
	/** The Constant PATTERN_EXTENSION_NOT_ALLOWED. */
	private static final Pattern PATTERN_EXTENSION_NOT_ALLOWED = Pattern.compile(EXTENSTIONS_NOT_ALLOWED);

	/** The Constant PATTERN_GUID_NOT_ALLOWED. */
	private static final Pattern PATTERN_GUID_NOT_ALLOWED = Pattern.compile("\\{[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}\\}");

	/* (non-Javadoc)
	 * @see com.mxhero.plugin.cloudstorage.onedrive.api.command.validators.Validatable#validate(java.lang.String)
	 */
	@Override
	public void validate(String name) {
		if(name == null || name.isEmpty()) throw new IllegalArgumentException("Empty file name are not allwed");
		if(PATTERN_EXTENSION_NOT_ALLOWED.matcher(FilenameUtils.getExtension(name)).find()) throw new IllegalArgumentException("The following extensions are not allowed to upload for Business API. Extensions not allowed: "+EXTENSTIONS_NOT_ALLOWED+" - Name provided: "+name);
		if(PATTERN_GUID_NOT_ALLOWED.matcher(name).find()) throw new IllegalArgumentException("GUIDs String are not allowed as file name. Name provided: "+name);
		if(FILES_NOT_ALLOWED.contains(name)) throw new IllegalArgumentException("File name could not be "+FILES_NOT_ALLOWED.toString()+". Name provided: "+name);
	}

}
