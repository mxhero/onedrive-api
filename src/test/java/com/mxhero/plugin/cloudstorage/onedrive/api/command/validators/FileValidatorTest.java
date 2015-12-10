package com.mxhero.plugin.cloudstorage.onedrive.api.command.validators;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

public class FileValidatorTest {

	@Test
	public void testValidateNotThrowException() {
		try {
			new FileValidator().validate("myfile.txt");
		} catch (Exception e) {
			fail();
		}
	}

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
