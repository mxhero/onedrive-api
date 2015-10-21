package com.mxhero.plugin.cloudstorage.onedrive.api.command;

@SuppressWarnings("serial")
public class RateLimitException extends RuntimeException{

	public RateLimitException() {
		super();
	}

	public RateLimitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RateLimitException(String message, Throwable cause) {
		super(message, cause);
	}

	public RateLimitException(String message) {
		super(message);
	}

	public RateLimitException(Throwable cause) {
		super(cause);
	}
}
