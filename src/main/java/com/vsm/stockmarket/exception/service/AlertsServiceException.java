package com.vsm.stockmarket.exception.service;

import com.vsm.stockmarket.exception.ServiceException;

public class AlertsServiceException extends ServiceException {

	public AlertsServiceException(Exception e) {
		super(e);
	}

	public AlertsServiceException() {
		super();
	}
}
