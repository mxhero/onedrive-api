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


import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.mxhero.plugin.cloudstorage.onedrive.api.command.TokenRefreshListener;

/**
 * The Class Credential.
 */
public class Credential {

	/** The token type. */
	private String tokenType;
	
	/** The user. */
	private String scope;

	/** The access token. */
	private String accessToken;

	/** The refresh token. */
	private String refreshToken;

	/** The client id. */
	private String userId;
	
	/** The user. */
	private String user;
	
	/** The listener. */
	private TokenRefreshListener listener;
	
	/**
	 * Gets the scope.
	 *
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * Sets the scope.
	 *
	 * @param scope the new scope
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Gets the access token.
	 * 
	 * @return the access token
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * Sets the access token.
	 * 
	 * @param accessToken
	 *            the new access token
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * Gets the refresh token.
	 * 
	 * @return the refresh token
	 */
	public String getRefreshToken() {
		return refreshToken;
	}

	/**
	 * Sets the refresh token.
	 * 
	 * @param refreshToken
	 *            the new refresh token
	 */
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	/**
	 * Gets the listener.
	 *
	 * @return the listener
	 */
	public TokenRefreshListener getListener() {
		return listener;
	}

	/**
	 * Sets the listener.
	 *
	 * @param listener the new listener
	 */
	public void setListener(TokenRefreshListener listener) {
		this.listener = listener;
	}

	/**
	 * Gets the token type.
	 *
	 * @return the token type
	 */
	public String getTokenType() {
		return tokenType;
	}

	/**
	 * Sets the token type.
	 *
	 * @param tokenType the new token type
	 */
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	/**
	 * Validate.
	 */
	private void validate() {
		Validate.isTrue(!StringUtils.isBlank(userId),
				"userId may not be blank");
		Validate.isTrue(!StringUtils.isBlank(accessToken),
				"accessToken may not be blank");
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/**
	 * The Class Builder.
	 */
	public static class Builder {

		/** The instance. */
		private Credential instance = new Credential();

		/**
		 * User.
		 *
		 * @param user the user
		 * @return the builder
		 */
		public Builder user(String user) {
			this.instance.user = user;
			return this;
		}
		
		/**
		 * User id.
		 *
		 * @param userId the user id
		 * @return the builder
		 */
		public Builder userId(String userId) {
			this.instance.userId = userId;
			return this;
		}

		/**
		 * Access token.
		 * 
		 * @param accessToken
		 *            the access token
		 * @return the builder
		 */
		public Builder accessToken(String accessToken) {
			this.instance.accessToken = accessToken;
			return this;
		}

		/**
		 * Refresh token.
		 * 
		 * @param refreshToken
		 *            the refresh token
		 * @return the builder
		 */
		public Builder refreshToken(String refreshToken) {
			this.instance.refreshToken = refreshToken;
			return this;
		}

		/**
		 * Listener.
		 *
		 * @param listener the listener
		 * @return the builder
		 */
		public Builder listener(TokenRefreshListener listener) {
			this.instance.listener = listener;
			return this;
		}
		
		/**
		 * Scope.
		 *
		 * @param scope the scope
		 * @return the builder
		 */
		public Builder scope(String scope){
			this.instance.scope = scope;
			return this;
		}
		
		/**
		 * Token type.
		 *
		 * @param tokenType the token type
		 * @return the builder
		 */
		public Builder tokenType(String tokenType){
			this.instance.tokenType = tokenType;
			return this;
		}
		
		/**
		 * Builds the.
		 * 
		 * @return the credential
		 */
		public Credential build() {
			instance.validate();
			return instance;
		}

	}

}
