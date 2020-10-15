package com.finadv.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets Category
 */
public enum ExpenseCategory {
  
  RENT("Rent"),
  
  CAR("Car"),
  
  EDUCATION_LOAN("Education Loan"),
  
  HOUSING_LOAN("Housing Loan"),
  
  PERSONAL_LOAN("Personal Loan"),
  
  GADGET_EMI("Gadget EMI"),
  
  FOOD("Food"),
  
  TRAVEL("Travel");

  private String value;

  ExpenseCategory(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static ExpenseCategory fromValue(String text) {
    for (ExpenseCategory b : ExpenseCategory.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

