package com.finadv.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.finadv.model.ExpenseCategory;
import com.finadv.model.ExpensePeriod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Expense
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDTO {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("category")
  private ExpenseCategory category = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("amount")
  private Double amount = null;

  @JsonProperty("period")
  private ExpensePeriod period = null;
}

