package com.finadv.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.finadv.entities.Notifications;
import com.finadv.entities.ReferralProgram;
import com.finadv.entities.User;
import com.finadv.entities.UserPoints;
import com.finadv.entities.UserPointsUpdate;
import com.finadv.repository.ReferralProgramRepository;
import com.finadv.repository.UserPointsRepository;
import com.finadv.repository.UserRepository;
import com.finadv.util.ReferralCode;
import com.finadv.util.ReferralCodeConfig;

/**
 * @author atanu
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	private ReferralProgramRepository referralProgramRepository;

	private UserPointsRepository userPointsRepository;
	
	private NotificationService notificationService;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	public void setReferralProgramRepository(ReferralProgramRepository referralProgramRepository) {
		this.referralProgramRepository = referralProgramRepository;
	}

	@Autowired
	public void setUserPointsRepository(UserPointsRepository userPointsRepository) {
		this.userPointsRepository = userPointsRepository;
	}
	
	
	@Autowired
	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	@Override
	public User getUserById(int id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User createUser(User user) {
		user.setCreatedAt(LocalDateTime.now());
		User userInDB = userRepository.getUserByEmail(user.getEmail());
		if (userInDB == null) {
			userRepository.save(user);
			
			// Give points for signup
			initiateUserPoints(user.getEmail());

		}
		return getUserByEmailId(user.getEmail());
	}

	@Override
	public User updateUser(User user) {
		User userInDB = userRepository.findById(user.getId()).orElse(null);
		if (userInDB != null) {
			user.setCreatedAt(userInDB.getCreatedAt());
			userRepository.save(user);
			return user;
		}

		return null;
	}

	@Override
	public User getUserByEmailId(String emailId) {
		User userInDB = userRepository.getUserByEmail(emailId);
		if (userInDB != null) {
			if (!StringUtils.isEmpty(userInDB.getDob())) {
				String[] arrOfDOB = userInDB.getDob().split("-");
				userInDB.setAge(getAge(Integer.parseInt(arrOfDOB[2]), Integer.parseInt(arrOfDOB[1]),
						Integer.parseInt(arrOfDOB[0])));
			}
			return userInDB;
		}

		return null;
	}

	@Override
	public void createUserReferralCode(ReferralProgram referralProgram) {
		// Generate referral code

		ReferralCodeConfig config = new ReferralCodeConfig(6, null, null, null, null);
		String code = ReferralCode.generate(config);

		referralProgram.setReferralCode(code);
		referralProgram.setCreatedDate(Date.from(java.time.ZonedDateTime.now().toInstant()));
		referralProgramRepository.save(referralProgram);

		// If referrerCode is present in request then add +100 points for the user as
		// referral reward
		// Get user by code
		/*
		if (!StringUtils.isEmpty(referralProgram.getReferrerCode())) {
			ReferralProgram referredByUser = referralProgramRepository
					.getCodeByReferralCode(referralProgram.getReferrerCode());

			// Add +100 points for the user
			UserPointsUpdate updateReferredByUserPoints = new UserPointsUpdate();
			updateReferredByUserPoints.setPoints(100l);
			updateReferredByUserPoints.setAction("ADD");
			updateReferredByUserPoints.setUserId(referredByUser.getUserId());
			
			updateUserPoints(updateReferredByUserPoints);
		}
	*/
	}

	/**
	 *
	 */
	@Override
	public ReferralProgram getCodeByUserId(long userId) {
		ReferralProgram referralProgram = referralProgramRepository.getCodeByUserId(userId);
		if (referralProgram == null) {
			ReferralProgram newReferralProgram = new ReferralProgram();
			newReferralProgram.setUserId(userId);
			newReferralProgram.setReferrerCode("");
			createUserReferralCode(newReferralProgram);
			referralProgram = referralProgramRepository.getCodeByUserId(userId);

		}

		return referralProgram;
	}

	private void initiateUserPoints(String email) {
		// Get userId from emailId while creation
		User user = getUserByEmailId(email);

		// Create points for user
		// Always add +50 points on signup and while record creation
		UserPoints userPoints = new UserPoints();
		userPoints.setUserId(user.getId());
		userPoints.setPoints(50);

		userPointsRepository.save(userPoints);

		// add notification to users
		Notifications notification = new Notifications();
		notification.setUserId(user.getId());
		notification.setTitle("WELCOME TO FINACULAR!");
		notification.setMessage(
				"You've taken your first step towards financial freedom.Add assets to find out how to make the most of visit today");
		notificationService.createUserNotification(notification);
	}

	@Override
	public UserPoints getUserPoints(long userId) {
		UserPoints userPoints = userPointsRepository.getPointsByUserId(userId);
		if (userPoints != null)
			return userPoints;

		return null;
	}

	@Override
	public UserPoints updateUserPoints(UserPointsUpdate userPointsUpdate) {
		UserPoints userPoints = userPointsRepository.getPointsByUserId(userPointsUpdate.getUserId());

		if (userPoints != null) {
			if ("ADD".equalsIgnoreCase(userPointsUpdate.getAction())) {
				userPoints.setPoints(userPoints.getPoints() + userPointsUpdate.getPoints());
			} else if ("REDEEM".equalsIgnoreCase(userPointsUpdate.getAction())) {
				// Check if points are enough
				// TODO
				userPoints.setPoints(userPoints.getPoints() - userPointsUpdate.getPoints());
			}

		} else {
			userPoints = new UserPoints();
			userPoints.setUserId(userPointsUpdate.getUserId());
			userPoints.setPoints(userPointsUpdate.getPoints());
		}
		userPointsRepository.save(userPoints);
		return userPoints;
	}
	
	private int getAge(int year, int month, int dayOfMonth) {
	    return Period.between(
	                LocalDate.of(year, month, dayOfMonth),
	                LocalDate.now()
	            ).getYears();
	}

}
