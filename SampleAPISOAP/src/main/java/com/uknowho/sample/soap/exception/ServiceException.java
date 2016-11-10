package com.uknowho.sample.soap.exception;

import com.uknowho.sample.soap.constant.ErrorCodeConstant;

/**
 * This ServiceException define customized exception.
 * 
 * Created date <21-Jul-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

public class ServiceException extends RuntimeException  {

	private static final long serialVersionUID = -3349247914159337152L;
	
	private int errorCode = ErrorCodeConstant.UNKNOWN_EXCEPTION;
	
	public ServiceException(Throwable throwable) {
		super(throwable);
	}
	
	public ServiceException(String errorMessage) {
		super(errorMessage);
		this.errorCode = ErrorCodeConstant.UNKNOWN_EXCEPTION;;
	}
	
	public ServiceException(int errorCode, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
	}
	
	public ServiceException(int errorCode, Throwable throwable) {
		super(throwable);
		this.errorCode = errorCode;
	}
	
	public int getErrorCode() {
		return this.errorCode;
	}
}
