package com.mxhero.plugin.cloudstorage.onedrive.api.command;

/*
 * #%L
 * com.mxhero.plugin.cloudstorage.sharefile
 * %%
 * Copyright (C) 2013 - 2014 mxHero Inc.
 * %%
 * MXHERO END USER LICENSE AGREEMENT
 * 
 * IMPORTANT-READ CAREFULLY:  BY DOWNLOADING, INSTALLING, OR USING THE SOFTWARE, YOU (THE INDIVIDUAL OR LEGAL ENTITY) AGREE TO BE BOUND BY THE TERMS OF THIS END USER LICENSE AGREEMENT (EULA).  IF YOU DO NOT AGREE TO THE TERMS OF THIS EULA, YOU MUST NOT DOWNLOAD, INSTALL, OR USE THE SOFTWARE, AND YOU MUST DELETE OR RETURN THE UNUSED SOFTWARE TO THE VENDOR FROM WHICH YOU ACQUIRED IT WITHIN THIRTY (30) DAYS AND REQUEST A REFUND OF THE LICENSE FEE, IF ANY, THAT YOU PAID FOR THE SOFTWARE.
 * 
 * READ LICENSE.txt file to see details of this agreement.
 * #L%
 */


/**
 * The Class AuthenticationException.
 */
public class AuthenticationException extends RuntimeException{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -359051396722043941L;

	/**
	 * Instantiates a new authentication exception.
	 */
	public AuthenticationException() {
		super();
	}

	/**
	 * Instantiates a new authentication exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	public AuthenticationException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Instantiates a new authentication exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public AuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new authentication exception.
	 *
	 * @param message the message
	 */
	public AuthenticationException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new authentication exception.
	 *
	 * @param cause the cause
	 */
	public AuthenticationException(Throwable cause) {
		super(cause);
	}

}
