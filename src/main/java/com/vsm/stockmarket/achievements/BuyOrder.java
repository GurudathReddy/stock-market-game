package com.vsm.stockmarket.achievements;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.vsm.stockmarket.domain.entity.OrderMaster;
import com.vsm.stockmarket.domain.entity.UserMaster;
import com.vsm.stockmarket.exception.service.AchievementExecutionServiceException;
import com.vsm.stockmarket.services.AchievementExecutionService;

@Service("buyOrder")
@Repository
public class BuyOrder extends AbstractAchievement implements IAchievement {

	@Inject
	AchievementExecutionService achievementExecutionService;

	@Override
	public int getCompleted(UserMaster userMaster) {
		int completed = 0;
		List<OrderMaster> orderList = new ArrayList<OrderMaster>();
		try {
			orderList = achievementExecutionService
					.findAllCompletedBuyOrders(userMaster);
			completed = orderList.size();
		} catch (AchievementExecutionServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return completed;
	}
}
