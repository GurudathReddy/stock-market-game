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

@Service("marketOrders")
@Repository
public class AchievementRiskOrders extends AbstractAchievement implements
		IAchievement {

	@Inject
	AchievementExecutionService achievementExecutionService;

	@Override
	public int getCompleted(UserMaster userMaster) {
		int completed = 0;
		List<OrderMaster> orderList = new ArrayList<OrderMaster>();
		try {
			orderList = achievementExecutionService
					.findAllCompletedRiskOrders(userMaster);
			completed = orderList.size();
		} catch (AchievementExecutionServiceException e) {
			e.printStackTrace();
		}
		return completed;
	}
}
