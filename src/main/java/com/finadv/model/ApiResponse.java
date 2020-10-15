package com.finadv.model;

import lombok.Builder;
import lombok.Data;

/**
 * ApiResponse
 */
@Data
@Builder
public class ApiResponse {

  private Integer code;

  private String data;

  private String message;
}

