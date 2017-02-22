package com.vsm.stockmarket.achievements;

import com.vsm.stockmarket.domain.entity.UserMaster;


public interface IAchievement {
	
	boolean evaluate(UserMaster userMaster, String category);
	
}
