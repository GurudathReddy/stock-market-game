package com.vsm.stockmarket.exception.service;

import com.vsm.stockmarket.exception.ServiceException;

public class InvitationServiceException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public InvitationServiceException(Exception e) {
		super(e);
	}

	public InvitationServiceException() {
		super();
	}
}