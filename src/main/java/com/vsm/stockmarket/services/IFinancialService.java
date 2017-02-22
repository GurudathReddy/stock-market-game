package com.vsm.stockmarket.services;

import java.util.Date;
import java.util.List;

import com.vsm.stockmarket.exception.FinancialServiceException;

public interface IFinancialService {

	public String getStockHistory(List<String> stockList, Date startDate,
			Date endDate) throws FinancialServiceException;

	public String getStockHistory(String tickerSymbol, Date startDate,
			Date endDate) throws FinancialServiceException;
	
	public String getStockCurrent(List<String> stockList) throws FinancialServiceException;

}
