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

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * The Class FolderValidatorTest.
 */
public class FolderValidatorTest {
	
	/**
	 * Test validate not throw exception.
	 */
	@Test
	public void testValidateNotThrowException() {
		try {
			new FolderValidator().validate("myFolder");
		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * Test validate fail empty folder.
	 */
	@Test
	public void testValidateFailEmptyFolder() {
		try {
			new FolderValidator().validate(null);
			fail();
		} catch (Exception e) {
		}
		try {
			new FolderValidator().validate("");
			fail();
		} catch (Exception e) {
		}
	}
	
	/**
	 * Test validate fail forbidden names.
	 */
	@Test
	public void testValidateFailForbiddenNames() {
		String[] split = "files|file|Dateien|fichiers|bestanden||archivos|tiedostot|pliki|soubory|elemei|ficheiros|arquivos|dosyalar|datoteke|fitxers|failid|fails|bylos|fajlovi|fitxategiak|private".split("\\|");
		FolderValidator validator = new FolderValidator();
		for (String folder : split) {			
			try {
				validator.validate("something_"+folder);
				fail();
			} catch (Exception e) {
			}
		}
	}

}
