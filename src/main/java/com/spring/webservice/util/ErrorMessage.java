package com.spring.webservice.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Custom Error Message for Exceptions
 * 
 * @author Hemantha
 */
public class ErrorMessage {

	private int status;
	private String message;
	private String url;
	private String requestType;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	@Override
	public String toString() {
		return StringUtils.join("ERROR-> STATUS[", String.valueOf(getStatus()), "], MESSAGE[", getMessage(), "],URL[",
				getUrl(), "], REQUEST TYPE[", getRequestType(), "]");
	}
}
