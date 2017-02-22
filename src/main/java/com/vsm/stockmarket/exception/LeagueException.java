package com.vsm.stockmarket.exception;

import com.vsm.stockmarket.enums.LEAGUE_ERR_CODES;

public class LeagueException extends ServiceException {

	public LeagueException(Exception e) {
		super(e);
	}

	private LEAGUE_ERR_CODES errorCode;

	public LeagueException(LEAGUE_ERR_CODES errorCode) {
		this.errorCode = errorCode;
	}

	public LEAGUE_ERR_CODES getErrorCode() {
		return errorCode;
	}

}
