package com.finadv.service;

import java.util.List;

import com.finadv.entities.Notifications;

/**
 * @author atanu
 *
 */
public interface NotificationService {

	List<Notifications> getUserNotifications(long userId);

	void createUserNotification(Notifications notifications);
	
	Notifications updateUserNotification(Notifications notifications);
	
	void deleteUserNotification(long notificationId);
	
	void deleteAllUserNotification(long userId);
}
