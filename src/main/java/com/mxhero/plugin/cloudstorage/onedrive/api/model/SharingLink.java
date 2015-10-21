package com.mxhero.plugin.cloudstorage.onedrive.api.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * The Class SharingLink.
 */
public class SharingLink {
	
	/** The token. */
	private String token;
	
	/** The web url. */
	private String webUrl;
	
	/** The type. */
	private String type;
	
	/** The application. */
	private Identity application;
	
	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Sets the token.
	 *
	 * @param token the new token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Gets the web url.
	 *
	 * @return the web url
	 */
	public String getWebUrl() {
		return webUrl;
	}

	/**
	 * Sets the web url.
	 *
	 * @param webUrl the new web url
	 */
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the application.
	 *
	 * @return the application
	 */
	public Identity getApplication() {
		return application;
	}

	/**
	 * Sets the application.
	 *
	 * @param application the new application
	 */
	public void setApplication(Identity application) {
		this.application = application;
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
