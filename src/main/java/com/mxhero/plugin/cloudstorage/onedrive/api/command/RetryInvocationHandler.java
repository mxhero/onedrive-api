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


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mxhero.plugin.cloudstorage.onedrive.api.ApiEnviroment;

/**
 * The Class RetryInvocationHandler.
 *
 * @param <T> the generic type
 */
public class RetryInvocationHandler <T> implements InvocationHandler{

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(RetryInvocationHandler.class);
	
	/** The obj. */
	private Integer retries;
	
	/** The retryCommand. */
	private RetryCommand<T> retryCommand;
	
	/**
	 * Instantiates a new retry invocation handler.
	 *
	 * @param retryCommand the retryCommand
	 */
	public RetryInvocationHandler(RetryCommand<T> command) {
		super();
		this.retryCommand = command;
		try{
			this.retries = Integer.parseInt(ApiEnviroment.retries.getValue());
		}catch(Exception e){}
		if(retries!=null && retries < 1){
			retries=1;
		}
	}
	

	/* (non-Javadoc)
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object result=null;
		if(method.isAnnotationPresent(RetryMethod.class)){
			for (int n = 0; n < retries; ++n) {
				try{
					result = method.invoke(retryCommand, args);
					break;
				}catch(RetryException | InvocationTargetException e){
					if(e instanceof InvocationTargetException){
						if(!(((InvocationTargetException) e).getTargetException() instanceof RetryException)){
							throw ((InvocationTargetException) e).getTargetException();
						}
					}
					if(n+1<retries){
						try {
							logger.warn("retry {} for credential {} and method {}",new Object[]{n,retryCommand.credential,method.getName()});
							Thread.sleep((1 << n) * 1000 + RandomUtils.nextInt(1001));
						} catch (InterruptedException ie) {}
					}else{
						logger.error("failed after {} for credential {} and method {}",new Object[]{n,retryCommand.credential,method.getName()});
						throw new RateLimitException("failed after "+n+" for credential "+retryCommand.credential+" and method "+method.getName(),e);
					}
				}catch(Exception e){
					logger.error("not a retry exception with app ",e);
					throw new RuntimeException("not retry exception",e);
				}
			}
			
		}else{
			result = method.invoke(retryCommand, args);
		}
		return result;
	}

}