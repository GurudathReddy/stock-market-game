package com.vsm.stockmarket.achievements;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.vsm.stockmarket.domain.entity.FollowerMaster;
import com.vsm.stockmarket.domain.entity.UserMaster;
import com.vsm.stockmarket.exception.service.AchievementExecutionServiceException;
import com.vsm.stockmarket.services.AchievementExecutionService;

@Service("followers")
@Repository
public class Followers extends AbstractAchievement implements IAchievement {

	@Inject
	AchievementExecutionService achievementExecutionService;

	@Override
	public int getCompleted(UserMaster userMaster) {
		int completed = 0;
		List<FollowerMaster> list = new ArrayList<FollowerMaster>();
		try {
			list = achievementExecutionService.findAllFollowers(userMaster);
			completed = list.size();
		} catch (AchievementExecutionServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return completed;
	}

}
