package com.vsm.stockmarket.exception;

public class DBRecordNotFoundException extends DAOException {

	public DBRecordNotFoundException(Exception e) {
		super(e);
	}

}
