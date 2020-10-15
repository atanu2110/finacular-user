package com.finadv.repository;

import com.finadv.entities.Income;
import com.finadv.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepo extends JpaRepository<Income, Long> {
    List<Income> findByUser(User user);
}
