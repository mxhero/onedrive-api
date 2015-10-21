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


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;

/**
 * The Interface CommandHandler.
 *
 * @param <T> the generic type
 */
public interface CommandHandler<T> {

	/**
	 * Request.
	 *
	 * @return the http uri request
	 */
	HttpUriRequest request();
	
	/**
	 * Response.
	 *
	 * @param response the response
	 * @return the t
	 */
	T response(HttpResponse response);

}
