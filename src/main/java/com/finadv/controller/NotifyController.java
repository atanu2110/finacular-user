package com.finadv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finadv.entities.Notifications;
import com.finadv.service.NotificationService;

/**
 * @author atanu
 *
 */
@RestController
@RequestMapping(value = "/api/v1/notify")
@CrossOrigin(origins = "*")
public class NotifyController {

	private NotificationService notificationService;

	@Autowired
	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	@Autowired
	SimpMessagingTemplate template;

	/**
	 * @param userId
	 * @return list of user notifications
	 */
	@GetMapping("/{userId}")
	public ResponseEntity<List<Notifications>> getUserNotifications(@PathVariable long userId) {
		List<Notifications> notifications = notificationService.getUserNotifications(userId);
		// Put notficatiosn to user queue - websocket open
		template.convertAndSendToUser(Long.toString(userId), "/queue/notification", notifications);
		return new ResponseEntity<>(notifications, HttpStatus.OK);
	}

	/**
	 * @param notifications
	 * @return
	 */
	@PostMapping("/{userId}")
	public ResponseEntity<String> createUserNotification(@RequestBody Notifications notifications) {
		notificationService.createUserNotification(notifications);
		// Update notification in user queue - websocket open
		getUserNotifications(notifications.getUserId());
		return new ResponseEntity<>("User Notification successfully saved !!", HttpStatus.OK);
	}

	/**
	 * @param notification
	 * @param notificationId
	 * @return
	 */
	@PutMapping("/{notificationId}")
	public ResponseEntity<?> updateUserNotification(@RequestBody Notifications notification,
			@PathVariable int notificationId) {

		Notifications updatedNotifications = notificationService.updateUserNotification(notification);

		if (updatedNotifications != null) {
			// Update notification in user queue - websocket open
			getUserNotifications(notification.getUserId());
			return new ResponseEntity<>(updatedNotifications, HttpStatus.OK);
		}

		return new ResponseEntity<>("Notification id is not present", HttpStatus.NOT_FOUND);
	}

	/**
	 * @param userId
	 * @param notificationId
	 * @return
	 */
	@DeleteMapping("/{userId}/{notificationId}")
	public ResponseEntity<?> deleteUserNotification(@PathVariable long userId, @PathVariable long notificationId) {
		notificationService.deleteUserNotification(notificationId);
		// Update notification in user queue - websocket open
		getUserNotifications(userId);
		return new ResponseEntity<>("Notification deleted successfully !!", HttpStatus.OK);
	}

	/**
	 * @param userId
	 * @return
	 */
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteAllUserNotification(@PathVariable long userId) {
		notificationService.deleteAllUserNotification(userId);
		// Update notification in user queue - websocket open
		getUserNotifications(userId);
		return new ResponseEntity<>("Notifications deleted successfully !!", HttpStatus.OK);
	}

}
