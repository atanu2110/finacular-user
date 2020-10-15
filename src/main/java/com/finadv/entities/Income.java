package com.finadv.entities;

import com.finadv.model.dto.IncomeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "user_income")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Income extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    private String category;
    private String name;
    private Double amount;
    private String period;

    public Income(IncomeDTO incomeDTO) {
        this.category = incomeDTO.getCategory().name();
        this.name = incomeDTO.getName();
        this.amount = incomeDTO.getAmount();
        this.period = incomeDTO.getPeriod().name();
    }
}
