package com.finadv.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets Category
 */
public enum IncomeCategory {
  
  SALARY("Salary"),
  
  RENT("Rent"),
  
  INTEREST("Interest"),
  
  OTHER("Other");

  private String value;

  IncomeCategory(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

}

