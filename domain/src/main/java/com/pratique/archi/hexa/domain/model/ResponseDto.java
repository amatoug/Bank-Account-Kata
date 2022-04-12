package com.pratique.archi.hexa.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {

	@JsonProperty("code")
	private Integer code = null;

	@JsonProperty("message")
	private String message = null;

}
