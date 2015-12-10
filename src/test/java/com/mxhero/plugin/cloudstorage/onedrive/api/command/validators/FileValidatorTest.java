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

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

/**
 * The Class FileValidatorTest.
 */
public class FileValidatorTest {

	/**
	 * Test validate not throw exception.
	 */
	@Test
	public void testValidateNotThrowException() {
		try {
			new FileValidator().validate("myfile.txt");
		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * Test validate fail because empty file.
	 */
	@Test
	public void testValidateFailBecauseEmptyFile() {
		FileValidator validator = new FileValidator();
		try {
			validator.validate(null);
			fail();
		} catch (IllegalArgumentException e) {
		}
		try {
			validator.validate("");
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	
	/**
	 * Test validate fail because wrong extension.
	 */
	@Test
	public void testValidateFailBecauseWrongExtension() {
		FileValidator validator = new FileValidator();
		String[] split = "ashx|asmx|json|soap|svc|xamlx|tmp|df_store".split("\\|");
		for (String extension : split) {				
			try {
				validator.validate("myfile."+extension);
				fail();
			} catch (IllegalArgumentException e) {
			}
		}
	}
	
	/**
	 * Test validate fail because wrong name.
	 */
	@Test
	public void testValidateFailBecauseWrongName() {
		FileValidator validator = new FileValidator();
		List<String> asList = Arrays.asList("desktop.ini","thumbs.db","ehthumbs.db");
		for (String file : asList) {				
			try {
				validator.validate(file);
				fail();
			} catch (IllegalArgumentException e) {
			}
		}
	}
	
	/**
	 * Test validate fail because wrong guid.
	 */
	@Test
	public void testValidateFailBecauseWrongGuid() {
		FileValidator validator = new FileValidator();
		for (int i = 0; i < 10; i++) {
			try {
				validator.validate("{"+UUID.randomUUID().toString()+"}.txt");
				fail();
			} catch (IllegalArgumentException e) {
			}
		}
	}

}
