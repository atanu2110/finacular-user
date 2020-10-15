package com.finadv.entities;

import com.finadv.model.dto.ExpenseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "user_expenses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Expense extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    private String category;
    private String name;
    private Double amount;
    private String period;

    public Expense(ExpenseDTO expenseDTO) {
        this.category = expenseDTO.getCategory().name();
        this.name = expenseDTO.getName();
        this.amount = expenseDTO.getAmount();
        this.period = expenseDTO.getPeriod().name();
    }
}
