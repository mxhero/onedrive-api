/*
 * mxHero is a platform that intends to provide a single point of development 
 * and single point of distribution for email solutions and enhancements. It does this
 * by providing an extensible framework for rapid development and deployment of
 * email solutions.
 * 
 * Copyright (C) 2012  mxHero Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
	
	/** The expires in. */
	private Integer expiresIn;
	
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
	 * Gets the expires in.
	 *
	 * @return the expires in
	 */
	public Integer getExpiresIn() {
		return expiresIn;
	}

	/**
	 * Sets the expires in.
	 *
	 * @param expiresIn the new expires in
	 */
	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
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
		Validate.isTrue(!StringUtils.isBlank(refreshToken),
				"refreshToken may not be blank");
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
		 * Expires in.
		 *
		 * @param expiresIn the expires in
		 * @return the builder
		 */
		public Builder expiresIn(Integer expiresIn){
			this.instance.expiresIn = expiresIn;
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
