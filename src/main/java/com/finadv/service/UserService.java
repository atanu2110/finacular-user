package com.finadv.service;

import org.springframework.web.bind.annotation.RequestBody;

import com.finadv.entities.ReferralProgram;
import com.finadv.entities.User;
import com.finadv.entities.UserPoints;
import com.finadv.entities.UserPointsUpdate;

public interface UserService {

	User getUserById(int id);
	
	User createUser(User user);
	
	User updateUser(User user);
	
	User getUserByEmailId(String emailId);
	
	void createUserReferralCode(ReferralProgram referralProgram);
	
	ReferralProgram getCodeByUserId(long userId);
	
	UserPoints getUserPoints(long userId);
	
	UserPoints updateUserPoints(@RequestBody UserPointsUpdate userPointsUpdate);
}
