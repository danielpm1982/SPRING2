package com.danielpm1982.Maven_Web_Spring_NoXML_REST_WS.error;

public class ErrorModel {
	private int status;
	private String message;
	private String dateTimeString;
	public ErrorModel() {
	}
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
	public String getDateTimeString() {
		return dateTimeString;
	}
	public void setDateTimeString(String dateTimeString) {
		this.dateTimeString = dateTimeString;
	}
	@Override
	public String toString() {
		return "status: "+status+" message: "+message+" dateTime: "+dateTimeString;
	}
}
