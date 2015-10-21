package com.mxhero.plugin.cloudstorage.onedrive.api;

/*
 * #%L
 * com.mxhero.plugin.cloudstorage.onedrive
 * %%
 * Copyright (C) 2013 - 2015 mxHero Inc.
 * %%
 * MXHERO END USER LICENSE AGREEMENT
 * 
 * IMPORTANT-READ CAREFULLY:  BY DOWNLOADING, INSTALLING, OR USING THE SOFTWARE, YOU (THE INDIVIDUAL OR LEGAL ENTITY) AGREE TO BE BOUND BY THE TERMS OF THIS END USER LICENSE AGREEMENT (EULA).  IF YOU DO NOT AGREE TO THE TERMS OF THIS EULA, YOU MUST NOT DOWNLOAD, INSTALL, OR USE THE SOFTWARE, AND YOU MUST DELETE OR RETURN THE UNUSED SOFTWARE TO THE VENDOR FROM WHICH YOU ACQUIRED IT WITHIN THIRTY (30) DAYS AND REQUEST A REFUND OF THE LICENSE FEE, IF ANY, THAT YOU PAID FOR THE SOFTWARE.
 * 
 * READ LICENSE.txt file to see details of this agreement.
 * #L%
 */

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mxhero.plugin.cloudstorage.onedrive.api.command.Command;
import com.mxhero.plugin.cloudstorage.onedrive.api.command.CommandFactory;
import com.mxhero.plugin.cloudstorage.onedrive.api.command.CommandHandler;
import com.mxhero.plugin.cloudstorage.onedrive.api.model.Drive;


/**
 * The Class Drives.
 */
public class Drives {

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(Drives.class);
	
	/** The Constant DRIVE. */
	public static final String DRIVE = "/drive";
	
	/** The Constant DRIVES. */
	public static final String DRIVES = "/drives/";
	
	/** The command factory. */
	private CommandFactory commandFactory;
	
	/**
	 * Instantiates a new drives.
	 *
	 * @param commandFactory the command factory
	 */
	public Drives(CommandFactory commandFactory){
		this.commandFactory = commandFactory;
	}
	
	/**
	 * User default.
	 *
	 * @return the drive
	 */
	public Drive userDefault(){
		final Command<Drive> command = commandFactory.create();
		logger.debug("called user default drive");
		return command.excecute(new CommandHandler<Drive>() {
			
			@Override
			public Drive response(HttpResponse response) {
				try {
					if(response.getStatusLine().getStatusCode()==404){
						return null;
					}else if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
						return OneDrive.JACKSON.readValue(EntityUtils.toString(response.getEntity()), Drive.class);
					}
					throw new RuntimeException("error reading response with code "+response.getStatusLine().getStatusCode());
				} catch (Exception e) {
					throw new IllegalArgumentException("error reading response",e);
				}
			}
			
			@Override
			public HttpUriRequest request() {
				return new HttpGet(command.baseUrl()+DRIVE);
			}
		});
		
	}
	
	/**
	 * Gets the.
	 *
	 * @param id the id
	 * @return the drive
	 */
	public Drive get(final String id){
		final Command<Drive> command = commandFactory.create();
		logger.debug("called user default drive");
		return command.excecute(new CommandHandler<Drive>() {
			
			@Override
			public Drive response(HttpResponse response) {
				try {
					if(response.getStatusLine().getStatusCode()==404){
						return null;
					}else if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
						return OneDrive.JACKSON.readValue(EntityUtils.toString(response.getEntity()), Drive.class);
					}
					throw new RuntimeException("error reading response with code "+response.getStatusLine().getStatusCode());
				} catch (Exception e) {
					throw new IllegalArgumentException("error reading response",e);
				}
			}
			
			@Override
			public HttpUriRequest request() {
				return new HttpGet(command.baseUrl()+DRIVES+id);
			}
		});
		
	}
	
}
