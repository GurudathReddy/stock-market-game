package com.vsm.stockmarket.exception.service;

import com.vsm.stockmarket.exception.ServiceException;

public class AchievementServiceException extends ServiceException {

	public AchievementServiceException(Exception e) {
		super(e);
	}

	public AchievementServiceException() {
		super();
	}
}