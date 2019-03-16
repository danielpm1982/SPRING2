package com.danielpm1982.vaccinesRecord.controller;
//import java.time.ZoneId;
//import java.time.ZonedDateTime;
//import java.time.format.DateTimeFormatter;
//import java.time.format.FormatStyle;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import com.danielpm1982.vaccinesRecord.error.ErrorModel;
//import com.danielpm1982.vaccinesRecord.error.RegistryNotFoundException;

@ControllerAdvice
public class VaccineTPSRESTControllerAdvice {
//	@ExceptionHandler
//	public ResponseEntity<ErrorModel> handleException(RegistryNotFoundException e){
//		ErrorModel errorModel = new ErrorModel();
//		errorModel.setStatus(HttpStatus.NOT_FOUND.value());
//		errorModel.setMessage(e.getMessage());
//		errorModel.setDateTimeString(ZonedDateTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)));
//		return new ResponseEntity<>(errorModel, HttpStatus.NOT_FOUND);
//	}
//	@ExceptionHandler
//	public ResponseEntity<ErrorModel> handleException(Exception e){
//		ErrorModel errorModel = new ErrorModel();
//		errorModel.setStatus(HttpStatus.BAD_REQUEST.value());
//		errorModel.setMessage(e.getMessage());
//		errorModel.setDateTimeString(ZonedDateTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)));
//		return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
//	}
}

//One should rather use this rest exception handler class, returning json-type custom error messages, or use the error.jsp with html responses.
