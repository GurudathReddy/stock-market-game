package com.vsm.stockmarket.exception.service;

import com.vsm.stockmarket.enums.STOCK_ERR_CODES;
import com.vsm.stockmarket.exception.ServiceException;

public class StockServiceException extends ServiceException {

	private STOCK_ERR_CODES errorCode;

	public StockServiceException(STOCK_ERR_CODES errorCode) {
		this.errorCode = errorCode;
	}

	public StockServiceException(STOCK_ERR_CODES errorCode, Exception e) {
		super(e);
		this.errorCode = errorCode;
	}

	public StockServiceException(Exception e) {
		super(e);
	}

	public StockServiceException() {
		super();
	}

	public STOCK_ERR_CODES getErrorCode() {
		return errorCode;
	}
}