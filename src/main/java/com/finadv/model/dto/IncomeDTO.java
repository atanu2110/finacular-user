package com.finadv.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.finadv.model.IncomeCategory;
import com.finadv.model.IncomePeriod;
import lombok.Data;

/**
 * Income
 */
@Data
public class IncomeDTO {

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("category")
  private IncomeCategory category = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("amount")
  private Double amount = null;

  @JsonProperty("period")
  private IncomePeriod period = null;
}

