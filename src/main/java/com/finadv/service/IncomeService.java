package com.finadv.service;


import com.finadv.entities.Income;
import com.finadv.model.dto.IncomeDTO;

import java.util.List;

public interface IncomeService {
    Income addIncome(IncomeDTO incomeDTO);

    Income update(IncomeDTO incomeDTO);

    List<Income> listIncomeForCurrentUser();

    void deleteIncome(Long incomeId);

    Income getIncome(Long incomeId);
}
