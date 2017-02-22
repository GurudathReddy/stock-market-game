package com.vsm.stockmarket.achievements;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.vsm.stockmarket.domain.entity.UserMaster;
import com.vsm.stockmarket.exception.service.AchievementExecutionServiceException;
import com.vsm.stockmarket.services.AchievementExecutionService;

@Service("differentCompanies")
@Repository
public class DifferentCompanies extends AbstractAchievement implements
		IAchievement {

	private static final Log LOG = LogFactory
			.getLog(DifferentCompanies.class);
	
	@Inject
	AchievementExecutionService achievementExecutionService;

	@Override
	public int getCompleted(UserMaster userMaster) {
		try 
		{
			return achievementExecutionService.findDistinctCompanyOrders(userMaster);
		} 
		catch (AchievementExecutionServiceException e) 
		{
			LOG.error(e);
		}
		return 0;
	}
}
