package com.mxhero.plugin.cloudstorage.onedrive.api;

/*
 * #%L
 * org.mxhero.plugin.configurationoverwrite
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
 * The Enum ApiEnviroment.
 */
public enum ApiEnviroment {
	
	/** The bucket. */
	baseUrl("ONEDRIVE_BASE_URL", "onedrive.base.url","https://api.onedrive.com/v1.0"),
	
	/** The live user info url. */
	liveUserInfoUrl("LIVE_USER_INFO_URL","livie.user.info.url","https://apis.live.net/v5.0/me"),
	
	/** The token base url. */
	tokenBaseUrl("ONEDRIVE_BASE_URL", "onedrive.base.url","https://login.live.com/oauth20_token.srf"),
		
	/** The connection request timeout. */
	connectionRequestTimeout("ONEDRIVE_REQUEST_TIMEOUT", "onedrive.request.timeout","30000"),
	
	/** The connection timeout. */
	connectionTimeout("ONEDRIVE_CONNECTION_TIMEOUT", "onedrive.connection.timeout","30000"),
	
	/** The socket timeout. */
	socketTimeout("ONEDRIVE_SOCKET_TIMEOUT", "onedrive.socket.timeout","30000"),
	
	/** The retries. */
	retries("ONEDRIVE_RETRIES", "onedrive.retries","5"),
	
	/** The app id. */
	appId("ONEDRIVE_MXHERO_APP_ID", "onedrive.mxhero.app.id", "6e83b8b826dd345c4b53cc56f2e9812d");

	/** The enviroment. */
	private String enviroment;
	
	/** The property. */
	private String property;
	
	/** The default value. */
	private String defaultValue;
	
	/**
	 * Instantiates a new enviroment.
	 *
	 * @param enviroment the enviroment
	 * @param property the property
	 * @param defaultValue the default value
	 */
	ApiEnviroment(String enviroment, String property, String defaultValue){
		this.enviroment = enviroment;
		this.property = property;
		this.defaultValue = defaultValue;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue(){
		if(System.getProperties().containsKey(property)){
			return System.getProperty(property);
		}
		if(System.getenv().containsKey(enviroment)){
			return System.getenv(enviroment);
		}
		return defaultValue;
	}

	/**
	 * Gets the enviroment.
	 *
	 * @return the enviroment
	 */
	public String getEnviroment() {
		return enviroment;
	}

	/**
	 * Gets the property.
	 *
	 * @return the property
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * Gets the default value.
	 *
	 * @return the default value
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

}
