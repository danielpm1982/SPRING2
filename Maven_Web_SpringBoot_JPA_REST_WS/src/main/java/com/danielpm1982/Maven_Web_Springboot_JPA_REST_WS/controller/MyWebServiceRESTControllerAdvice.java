package com.danielpm1982.Maven_Web_Springboot_JPA_REST_WS.controller;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.danielpm1982.Maven_Web_Springboot_JPA_REST_WS.error.ErrorModel;
import com.danielpm1982.Maven_Web_Springboot_JPA_REST_WS.error.MyRegistryNotFoundException;

@ControllerAdvice
public class MyWebServiceRESTControllerAdvice {
	@ExceptionHandler
	public ResponseEntity<ErrorModel> handleException(MyRegistryNotFoundException e){
		ErrorModel errorModel = new ErrorModel();
		errorModel.setStatus(HttpStatus.NOT_FOUND.value());
		errorModel.setMessage(e.getMessage());
		errorModel.setDateTimeString(ZonedDateTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)));
		return new ResponseEntity<>(errorModel, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ErrorModel> handleException(Exception e){
		ErrorModel errorModel = new ErrorModel();
		errorModel.setStatus(HttpStatus.BAD_REQUEST.value());
		errorModel.setMessage(e.getMessage());
		errorModel.setDateTimeString(ZonedDateTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)));
		return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
	}
}
