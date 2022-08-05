package com.yourmoney.web.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Map;

@Builder
@Getter
@Setter
public class ErrorDetailsResponse {
	private OffsetDateTime timestamp;
	@JsonProperty(value = "status")
	private int httpStatusCode;
	private String error;
	private String message;
	private String path;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Map<String, String> fieldErrors;
}