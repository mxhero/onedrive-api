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

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * The Class RedeemDaemonRequest.
 */
public class RedeemDaemonRequest {
	
	/** The client id. */
	private String clientId;
	
	/** The file url pkcs12 certificate. */
	private String fileUrlPkcs12Certificate;
	
	/** The certificate secret. */
	private String certificateSecret;
	
	/** The resource sharepoint id. */
	private String resourceSharepointId;
	
	/** The tenant id. */
	private String tenantId;


	/**
	 * Gets the client id.
	 *
	 * @return the client id
	 */
	public String getClientId() {
		return clientId;
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
	 * Gets the certificate secret.
	 *
	 * @return the certificate secret
	 */
	public String getCertificateSecret() {
		return certificateSecret;
	}

	/**
	 * Gets the resource sharepoint id.
	 *
	 * @return the resource sharepoint id
	 */
	public String getResourceSharepointId() {
		return resourceSharepointId;
	}

	/**
	 * Gets the tenant id.
	 *
	 * @return the tenant id
	 */
	public String getTenantId() {
		return tenantId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	/**
	 * Validate.
	 */
	public void validate(){
		Validate.notEmpty(clientId, "clientId field must be provided");
		Validate.notEmpty(tenantId, "tenantId field must be provided");
		Validate.notEmpty(resourceSharepointId, "resourceSharepointId field must be provided");
	}
	
	/**
	 * Builder.
	 *
	 * @return the bulider
	 */
	public static Builder builder(){
		return new Builder();
	}
	
	/**
	 * The Class Builder.
	 */
	public static class Builder {
		
		/** The instance. */
		private RedeemDaemonRequest instance = new RedeemDaemonRequest();
		
		/**
		 * Client id.
		 *
		 * @param clientId the client id
		 * @return the builder
		 */
		public Builder clientId(String clientId){
			this.instance.clientId = clientId;
			return this;
		}
		
		/**
		 * File url pkcs12 certificate.
		 *
		 * @param fileUrlPkcs12Certificate the file url pkcs12 certificate
		 * @return the builder
		 */
		public Builder fileUrlPkcs12Certificate(String fileUrlPkcs12Certificate){
			this.instance.fileUrlPkcs12Certificate = fileUrlPkcs12Certificate;
			return this;
		}

		/**
		 * Certificate secret.
		 *
		 * @param certificateSecret the certificate secret
		 * @return the builder
		 */
		public Builder certificateSecret(String certificateSecret){
			this.instance.certificateSecret = certificateSecret;
			return this;
		}

		/**
		 * Tenant id.
		 *
		 * @param tenantId the tenant id
		 * @return the builder
		 */
		public Builder tenantId(String tenantId){
			this.instance.tenantId = tenantId;
			return this;
		}

		/**
		 * Resource sharepoint id.
		 *
		 * @param sharepointId the sharepoint id
		 * @return the builder
		 */
		public Builder resourceSharepointId(String sharepointId){
			this.instance.resourceSharepointId = sharepointId;
			return this;
		}

		/**
		 * Builds the.
		 *
		 * @return the redeem daemon request
		 */
		public RedeemDaemonRequest build(){
			this.instance.validate();
			return this.instance;
		}
	}
 
}