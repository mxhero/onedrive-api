package com.mxhero.plugin.cloudstorage.onedrive.api.command.validators;

import com.mxhero.plugin.cloudstorage.onedrive.api.command.Validator.ValidatorType;

// TODO: Auto-generated Javadoc
/**
 * The Class Validators.
 */
public class Validators {

	/**
	 * Gets the.
	 *
	 * @param type the type
	 * @return the validatable
	 */
	public static Validatable get(ValidatorType type) {
		switch(type){
			case file: return new FileValidator();
			case folder: return new FolderValidator();
			default: return new VoidValidator();
		}
	}
	

}
