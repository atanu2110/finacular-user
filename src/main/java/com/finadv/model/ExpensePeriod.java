package com.finadv.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets Period
 */
public enum ExpensePeriod {
  
  WEEK("Week"),
  
  FORTNIGHT("FortNight"),
  
  MONTH("Month"),
  
  QUARTER("Quarter"),
  
  SEMIANNUAL("SemiAnnual"),
  
  ANNUAL("Annual"),
  
  ONETIME("OneTime");

  private String value;

  ExpensePeriod(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static ExpensePeriod fromValue(String text) {
    for (ExpensePeriod b : ExpensePeriod.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

