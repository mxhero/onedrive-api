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
	 */
	public DaemonApplication() {
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
	
	/**
	 * Builder.
	 *
	 * @return the builder
	 */
	public static DaemonBuilder builderDaemon(){
		return new DaemonBuilder();
	}
	
	/**
	 * The Class Builder.
	 */
	public static class DaemonBuilder {
		
		/** The app. */
		private DaemonApplication app = new DaemonApplication();
		
		/**
		 * File url pkcs12 certificate.
		 *
		 * @param fileUrlPkcs12Certificate the file url pkcs12 certificate
		 * @return the daemon builder
		 */
		public DaemonBuilder fileUrlPkcs12Certificate(String fileUrlPkcs12Certificate){
			this.app.fileUrlPkcs12Certificate = fileUrlPkcs12Certificate;
			return this;
		}
		
		/**
		 * Certificate secret.
		 *
		 * @param certificateSecret the certificate secret
		 * @return the daemon builder
		 */
		public DaemonBuilder certificateSecret(String certificateSecret){
			this.app.certificateSecret = certificateSecret;
			return this;
		}
		
		/**
		 * Client id.
		 *
		 * @param clientId the client id
		 * @return the builder
		 */
		public DaemonBuilder clientId(String clientId){
			this.app.setClientId(clientId);
			return this;
		}

		/**
		 * Client secret.
		 *
		 * @param clientSecret the client secret
		 * @return the builder
		 */
		public DaemonBuilder clientSecret(String clientSecret){
			this.app.setClientSecret(clientSecret);
			return this;
		}

		/**
		 * Redirect uri.
		 *
		 * @param redirectUri the redirect uri
		 * @return the builder
		 */
		public DaemonBuilder redirectUri(String redirectUri){
			this.app.setRedirectUri(redirectUri);
			return this;
		}

		/**
		 * Builds the.
		 *
		 * @return the application
		 */
		public DaemonApplication build(){
			this.app.validate();
			return this.app;
		}
		
	
	}

	/**
	 * Validate.
	 */
	public void validate() {
		super.validate();
	}
	
}
