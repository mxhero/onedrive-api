package com.mxhero.plugin.cloudstorage.onedrive.api.command.validators;

import static org.junit.Assert.*;

import org.junit.Test;

public class FolderValidatorTest {
	
	@Test
	public void testValidateNotThrowException() {
		try {
			new FolderValidator().validate("myFolder");
		} catch (Exception e) {
			fail();
		}
	}

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
