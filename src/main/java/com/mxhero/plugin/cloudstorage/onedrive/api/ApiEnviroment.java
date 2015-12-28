/**
 * Copyright (C) 2015 mxHero Inc (mxhero@mxhero.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
	
	/** The token business base url. */
	tokenBusinessBaseUrl("ONEDRIVE_BUSINESS_BASE_URL", "onedrive.business.base.url","https://login.microsoftonline.com/common/oauth2/token"),
		
	/** The token daemon base url. */
	tokenDaemonBaseUrl("ONEDRIVE_DAEMON_BASE_URL", "onedrive.daemon.base.url","https://login.microsoftonline.com/%s/oauth2/token"),

	/** The connection request timeout. */
	connectionRequestTimeout("ONEDRIVE_REQUEST_TIMEOUT", "onedrive.request.timeout","30000"),
	
	/** The connection timeout. */
	connectionTimeout("ONEDRIVE_CONNECTION_TIMEOUT", "onedrive.connection.timeout","30000"),
	
	/** The socket timeout. */
	socketTimeout("ONEDRIVE_SOCKET_TIMEOUT", "onedrive.socket.timeout","30000"),
	
	/** The retries. */
	retries("ONEDRIVE_RETRIES", "onedrive.retries","5"),
	
	/** The max file and folder lenght. */
	maxFileAndFolderLenght("ONEDRIVE_MAX_FILE_AND_FOLDER_LENGHT", "onedrive.max.file.and.folder.lenght","128"),
	
	/** The temp upload folder. */
	tempUploadFolder("ONEDRIVE_TMP_UPLOAD_FOLDER", "onedrive.tmp.upload.folder", System.getProperty("java.io.tmpdir")),
	
	/** The discovery resource uri. */
	discoveryResourceUri("ONEDRIVE_DISCOVERY_URL", "onedrive.discovery.url","https://api.office.com/discovery/"),
	
	/** The file url pkcs12 certificate. */
	fileUrlPkcs12Certificate("ONEDRIVE_DAEMON_PKCS12_FILE_URL", "onedrive.daemon.pkcs12.file.url", null),
	
	/** The pkcs12 certificate secret. */
	pkcs12CertificateSecret("ONEDRIVE_DAEMON_PKCS12_FILE_SECRET", "onedrive.daemon.pkcs12.file.secret", null);
	

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
