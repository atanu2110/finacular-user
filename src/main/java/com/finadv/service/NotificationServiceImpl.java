package com.finadv.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finadv.entities.Notifications;
import com.finadv.repository.NotificationRepository;

@Service
public class NotificationServiceImpl implements NotificationService {

	private static final Logger LOG = LoggerFactory.getLogger(NotificationServiceImpl.class);

	private NotificationRepository notificationRepository;

	@Autowired
	public void setNotificationRepository(NotificationRepository notificationRepository) {
		this.notificationRepository = notificationRepository;
	}

	@Override
	public List<Notifications> getUserNotifications(long userId) {
		LOG.info("Inside getUserNotifications for : " + userId);

		return notificationRepository.findUserNotificationsByUserId(userId);
	}

	@Override
	public void createUserNotification(Notifications notifications) {
		LOG.info("Inside createUserNotification for : " + notifications.getUserId());
		notifications.setCreatedAt(LocalDateTime.now());
		notificationRepository.save(notifications);
	}

	@Override
	public Notifications updateUserNotification(Notifications notifications) {
		LOG.info("Inside updateUserNotification for notificationId : " + notifications.getNotificationId());	
		Notifications notificationsInDB = notificationRepository.findById(notifications.getNotificationId()).orElse(null);
		if (notificationsInDB != null) {
			notificationRepository.save(notifications);
			return notifications;
		}
		
		return null;
	}

	@Override
	public void deleteUserNotification(long notificationId) {
		LOG.info("Inside deleteUserNotification for notificationId : " + notificationId);	
		notificationRepository.deleteById((int) notificationId);
		
	}

	@Override
	public void deleteAllUserNotification(long userId) {
		LOG.info("Inside deleteAllUserNotification for userId : " + userId);
		notificationRepository.deleteAllUserNotification(userId);

	}
}
