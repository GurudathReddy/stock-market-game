package com.vsm.stockmarket.exception;

public class ServiceException extends BaseException {

	public ServiceException(Exception e) {
		super(e);
	}

	public ServiceException() {
		super();
	}

}
