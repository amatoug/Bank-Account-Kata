package com.pratique.archi.hexa.service.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {
  private int statusCode;
  private String message;
  private String description;

}