package com.vsm.stockmarket.exception;

import com.vsm.stockmarket.enums.REWARD_ERROR_CODES;


public class RewardException extends ServiceException {

	public RewardException(Exception e) {
		super(e);
	}

	private REWARD_ERROR_CODES errorCode;

	public RewardException(REWARD_ERROR_CODES errorCode) {
		this.errorCode = errorCode;
	}

	public REWARD_ERROR_CODES getErrorCode() {
		return errorCode;
	}

}
