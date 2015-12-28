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

/**
 * The Class DaemonApplication.
 */
public class DaemonApplication extends Application {
	
	/** The sharepoint url. */
	private String fileUrlPkcs12Certificate;
	
	/** The certificate secret. */
	private String certificateSecret;
	
	/**
	 * Instantiates a new daemon application.
	 *
	 * @param clientId the client id
	 * @param redirectUri the redirect uri
	 * @param clientSecret the client secret
	 * @param fileUrlPkcs12Certificate the file url pkcs12 certificate
	 * @param certificateSecret the certificate secret
	 */
	public DaemonApplication(String clientId, String redirectUri, String clientSecret, String fileUrlPkcs12Certificate, String certificateSecret) {
		super(clientId, redirectUri, clientSecret);
		this.fileUrlPkcs12Certificate = fileUrlPkcs12Certificate;
		this.certificateSecret = certificateSecret;
	}

	/**
	 * Gets the file url pkcs12 certificate.
	 *
	 * @return the file url pkcs12 certificate
	 */
	public String getFileUrlPkcs12Certificate() {
		return fileUrlPkcs12Certificate;
	}
	
	/**
	 * Sets the file url pkcs12 certificate.
	 *
	 * @param fileUrlPkcs12Certificate the new file url pkcs12 certificate
	 */
	public void setFileUrlPkcs12Certificate(String fileUrlPkcs12Certificate) {
		this.fileUrlPkcs12Certificate = fileUrlPkcs12Certificate;
	}
	
	/**
	 * Gets the certificate secret.
	 *
	 * @return the certificate secret
	 */
	public String getCertificateSecret() {
		return certificateSecret;
	}
	
	/**
	 * Sets the certificate secret.
	 *
	 * @param certificateSecret the new certificate secret
	 */
	public void setCertificateSecret(String certificateSecret) {
		this.certificateSecret = certificateSecret;
	}
	
}
