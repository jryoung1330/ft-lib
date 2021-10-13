package com.foodtraffic.model.response;

import lombok.Data;

@Data
public class ErrorResponse {

	private String error;
	private String message;
	private int status;
	private String path;
}
