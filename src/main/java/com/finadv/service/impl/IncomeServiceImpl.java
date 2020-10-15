package com.finadv.service.impl;

import com.finadv.entities.Income;
import com.finadv.entities.User;
import com.finadv.model.dto.IncomeDTO;
import com.finadv.repository.IncomeRepo;
import com.finadv.service.IncomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static java.sql.Timestamp.from;
import static java.time.Instant.now;

@Service
@Slf4j
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepo incomeRepo;

    @Autowired
    public IncomeServiceImpl(IncomeRepo incomeRepo) {
        this.incomeRepo = incomeRepo;
    }

    @Override
    public Income addIncome(IncomeDTO incomeDTO) {
        Income income = new Income(incomeDTO);
        income.setCreatedAt(from(now()));
        //add user from security
        return incomeRepo.save(income);
    }

    @Override
    public Income update(IncomeDTO incomeDTO) {
        Optional<Income> existingIncome =  incomeRepo.findById(incomeDTO.getId());
        return existingIncome.map(income -> {
            income.setAmount(incomeDTO.getAmount());
            income.setCategory(incomeDTO.getCategory().name());
            income.setName(incomeDTO.getName());
            income.setPeriod(incomeDTO.getPeriod().name());
            income.setModifiedAt(from(now()));
            return incomeRepo.save(income);
        }).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Income> listIncomeForCurrentUser() {
        //        User user =  authService.getUser();
        User user = new User();
        user.setId(1L);
        return incomeRepo.findByUser(user);
    }

    @Override
    public void deleteIncome(Long incomeId) {
        incomeRepo.deleteById(incomeId);
    }

    @Override
    public Income getIncome(Long incomeId) {
        Optional<Income> optionalIncome = incomeRepo.findById(incomeId);
        if (optionalIncome.isPresent()) {
            return optionalIncome.get();
        } else throw new NoSuchElementException();
    }
}
