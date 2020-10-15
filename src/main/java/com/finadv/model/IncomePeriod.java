package com.finadv.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets Period
 */
public enum IncomePeriod {
  
  WEEK("Week"),
  
  FORTNIGHT("FortNight"),
  
  MONTH("Month"),
  
  QUARTER("Quarter"),
  
  SEMIANNUAL("SemiAnnual"),
  
  ANNUAL("Annual"),
  
  ONETIME("OneTime");

  private String value;

  IncomePeriod(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }
}

