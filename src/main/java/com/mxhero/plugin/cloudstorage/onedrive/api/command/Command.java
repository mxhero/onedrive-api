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
package com.mxhero.plugin.cloudstorage.onedrive.api.command;

// TODO: Auto-generated Javadoc
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

/**
 * The Interface Command.
 *
 * @param <T> the generic type
 */
public interface Command<T> {
	
	/** The Constant DRIVE. */
	static final String DRIVE = "/drive";
	
	/** The Constant DRIVES. */
	static final String DRIVES = "/drives/";
	
	/**
	 * Excecute.
	 *
	 * @param handler the handler
	 * @return the t
	 */
	public T excecute(CommandHandler<T> handler);
	
	/**
	 * Base url.
	 *
	 * @return the string
	 */
	public String baseUrl();
	
	/**
	 * Root url.
	 *
	 * @return the string
	 */
	public String rootUrl();

	/**
	 * Validate.
	 *
	 * @param toBeValidated the to be validated
	 */
	public void validate(Validator toBeValidated);

}
