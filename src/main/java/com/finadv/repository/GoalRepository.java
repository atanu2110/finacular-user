package com.finadv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finadv.entities.Goals;

@Repository
public interface GoalRepository extends JpaRepository<Goals, Long> {

}
