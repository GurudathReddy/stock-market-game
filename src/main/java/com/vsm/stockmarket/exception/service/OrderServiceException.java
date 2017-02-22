package com.vsm.stockmarket.exception.service;

import com.vsm.stockmarket.exception.ServiceException;

public class OrderServiceException extends ServiceException {

	public OrderServiceException(Exception e) {
		super(e);
	}

	public OrderServiceException() {
		super();
	}
}