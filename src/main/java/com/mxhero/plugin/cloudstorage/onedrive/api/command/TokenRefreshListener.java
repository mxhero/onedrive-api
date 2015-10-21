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


import java.util.Map;

import com.mxhero.plugin.cloudstorage.onedrive.api.Credential;

/**
 * The listener interface for receiving tokenRefresh events.
 * The class that is interested in processing a tokenRefresh
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addTokenRefreshListener<code> method. When
 * the tokenRefresh event occurs, that object's appropriate
 * method is invoked.
 *
 * @see TokenRefreshEvent
 */
public interface TokenRefreshListener {

	/**
	 * On success.
	 *
	 * @param result the result
	 */
	public void onSuccess(Credential credential, Map<String, Object> result);
	
	/**
	 * On faliure.
	 *
	 * @param credential the credential
	 * @param statusCode the status code
	 */
	public void onFaliure(Credential credential, int statusCode);
	
}
