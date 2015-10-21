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
 * The Class RetryException.
 */
@SuppressWarnings("serial")
public class RetryException extends RuntimeException{

	/**
	 * Instantiates a new connection exception.
	 */
	public RetryException() {
		super();
	}

	/**
	 * Instantiates a new connection exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	public RetryException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Instantiates a new connection exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public RetryException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new connection exception.
	 *
	 * @param message the message
	 */
	public RetryException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new connection exception.
	 *
	 * @param cause the cause
	 */
	public RetryException(Throwable cause) {
		super(cause);
	}

}
