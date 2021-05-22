package com.medeiros.apiNumerosAleatorios.resources.exceptions;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StandardError {

	/**
	 * "timestamp": "2021-02-02T00:21:24.555+00:00",
	 * "status": 500, 
	 * "error":"Internal Server Error",
	 * "message": "",
	 * "path": "/users/7"
	 **/
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'Z'", timezone = "GMT")
	private Instant timestamp;
	private int status;
	private String error;
	private String message;
	private String path;
	
	public StandardError() {
		
	}

	public StandardError(Instant timestamp, Integer status, String error, String message, String path) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}


	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	
	

}
