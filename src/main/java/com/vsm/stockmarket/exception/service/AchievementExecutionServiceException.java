package com.vsm.stockmarket.exception.service;

import com.vsm.stockmarket.exception.ServiceException;

public class AchievementExecutionServiceException extends ServiceException {

	public AchievementExecutionServiceException(Exception e) {
		super(e);
	}

	public AchievementExecutionServiceException() {
		super();
	}
}