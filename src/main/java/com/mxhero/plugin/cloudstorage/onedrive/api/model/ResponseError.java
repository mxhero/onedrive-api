package com.mxhero.plugin.cloudstorage.onedrive.api.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * The Class ResponseError.
 */
public class ResponseError {

	/** The code. */
	private String code;
	
	/** The message. */
	private String message;
	
	/** The innererror. */
	private ResponseError innererror;
	
	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the innererror.
	 *
	 * @return the innererror
	 */
	public ResponseError getInnererror() {
		return innererror;
	}

	/**
	 * Sets the innererror.
	 *
	 * @param innererror the new innererror
	 */
	public void setInnererror(ResponseError innererror) {
		this.innererror = innererror;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
