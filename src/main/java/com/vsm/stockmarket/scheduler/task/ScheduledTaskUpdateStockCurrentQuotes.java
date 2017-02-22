package com.vsm.stockmarket.scheduler.task;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vsm.stockmarket.exception.FinancialServiceException;
import com.vsm.stockmarket.services.AlertsService;
import com.vsm.stockmarket.services.OrderExecutionService;
import com.vsm.stockmarket.services.StockService;

@Component
@EnableScheduling
public class ScheduledTaskUpdateStockCurrentQuotes {
	private static final Log LOG = LogFactory
			.getLog(ScheduledTaskUpdateStockCurrentQuotes.class);

	@Inject
	private StockService stockService;

	@Inject
	private OrderExecutionService orderExecutionService;

	@Inject
	private AlertsService alertsService;

	@Scheduled(fixedRateString = "${scheduler.task.updateStockCurrentQuotes.frequency}")
	public void updateStockCurrentQuotes() {
		LOG.info("SCHEDULED TASK: UPDATE STOCK CURRENT QUOTES STARTED");
		List<String> stockList = stockService.listAllStockSymbols();
		try {
			stockService.updateStockCurrentQuotes(stockList);
			LOG.info("SCHEDULED TASK: UPDATE STOCK CURRENT QUOTES ENDED");
			LOG.info("SCHEDULED TASK: TRIGGER WATCH ALERTS STARTED");
			alertsService.triggerWatchStockAlerts();
			LOG.info("SCHEDULED TASK: TRIGGER WATCH ALERTS ENDED");
		} catch (FinancialServiceException e) {
			LOG.error(
					"Technical Error while updating stock current quotes for stock: ",
					e);
		}
	}
	
	@Scheduled(fixedRateString = "${scheduler.task.executeOrders.frequency}")
	public void executeOrders() {
		LOG.info("SCHEDULED TASK: EXECUTE ORDERS STARTED");
		orderExecutionService.executeOrders();
		LOG.info("SCHEDULED TASK: EXECUTE ORDERS ENDED");
	}
}
