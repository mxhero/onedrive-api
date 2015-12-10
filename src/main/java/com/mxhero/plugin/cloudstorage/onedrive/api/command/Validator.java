package com.mxhero.plugin.cloudstorage.onedrive.api.command;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

// TODO: Auto-generated Javadoc
/**
 * The Class Validator.
 */
public class Validator {
	
	/** The name. */
	private String name;
	
	/** The type. */
	private ValidatorType type = ValidatorType.file;
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public ValidatorType getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(ValidatorType type) {
		this.type = type;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	/**
	 * Builder.
	 *
	 * @return the builder
	 */
	public static Builder builder(){
		return new Builder();
	}
	
	/**
	 * The Class Builder.
	 */
	public static class Builder{
		
		/** The target. */
		private Validator target = new Validator();
		
		/**
		 * Name.
		 *
		 * @param name the name
		 * @return the builder
		 */
		public Builder name(String name){
			this.target.name = name;
			return this;
		}
		
		/**
		 * Type.
		 *
		 * @param type the type
		 * @return the builder
		 */
		public Builder type(ValidatorType type){
			this.target.type = type;
			return this;
		}
		
		/**
		 * Folder.
		 *
		 * @return the builder
		 */
		public Builder folder() {
			this.target.type = ValidatorType.folder;
			return this;
		}

		/**
		 * File.
		 *
		 * @return the builder
		 */
		public Builder file() {
			this.target.type = ValidatorType.file;
			return this;
		}

		/**
		 * Builds the.
		 *
		 * @return the validator
		 */
		public Validator build(){
			return this.target;
		}

	}

	/**
	 * The Enum ValidatorType.
	 */
	public enum ValidatorType {
		
		/** The folder. */
		folder, 
		 /** The file. */
		 file;
	}

}
