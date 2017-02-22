package com.vsm.stockmarket.exception.service;

import com.vsm.stockmarket.exception.ServiceException;

public class UserServiceException extends ServiceException {

	public UserServiceException(Exception e) {
		super(e);
	}

	public UserServiceException() {
		super();
	}
}