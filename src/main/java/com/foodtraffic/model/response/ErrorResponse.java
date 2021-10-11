package com.foodtraffic.model.response;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class ErrorResponse {

	private ZonedDateTime timestamp;
	private int status;
	private String error;
	private String message;
	private String path;

	public ErrorResponse() {
		setTimestamp(ZonedDateTime.now());
	}

}
