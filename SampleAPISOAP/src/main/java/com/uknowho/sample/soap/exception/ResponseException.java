package com.uknowho.sample.soap.exception;

import com.uknowho.sample.soap.xmlmodel.ResponseModel;

/**
 * This ResponseException define customized response message exception.
 * 
 * Created date <26-Jul-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

public class ResponseException extends RuntimeException {

	private static final long serialVersionUID = -3349247914159337152L;
	
	private ResponseModel responseModel = null;
	
	public ResponseException(Throwable throwable) {
		super(throwable);
	}
	
	public ResponseException(String errorMessage) {
		super(errorMessage);
		this.responseModel = null;
	}
	
	public ResponseException(ResponseModel responseModel, String errorMessage) {
		super(errorMessage);
		this.responseModel = responseModel;
	}
	
	public ResponseException(ResponseModel responseModel, Throwable throwable) {
		super(throwable);
		this.responseModel = responseModel;
	}
	
	public ResponseModel getResponseModel() {
		return this.responseModel;
	}
}
