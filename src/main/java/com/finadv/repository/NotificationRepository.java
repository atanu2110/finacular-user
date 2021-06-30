package com.finadv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.finadv.entities.Notifications;

/**
 * @author atanu
 *
 */
public interface NotificationRepository extends CrudRepository<Notifications, Integer> {

	@Query(value = "SELECT * FROM notifications u WHERE u.user_id = :userid", nativeQuery = true)
	List<Notifications> findUserNotificationsByUserId(@Param("userid") Long id);

	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM notifications u WHERE u.user_id = :userid", nativeQuery = true)
	void deleteAllUserNotification(@Param("userid") Long id);
}
