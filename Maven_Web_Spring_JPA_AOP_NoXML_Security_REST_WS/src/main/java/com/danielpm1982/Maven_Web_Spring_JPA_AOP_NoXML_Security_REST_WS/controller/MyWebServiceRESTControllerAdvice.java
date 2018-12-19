package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_REST_WS.controller;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_REST_WS.error.ErrorModel;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_REST_WS.error.MyIndexOutOfBoundsException;

@ControllerAdvice
public class MyWebServiceRESTControllerAdvice {
	@ExceptionHandler
	public ResponseEntity<ErrorModel> handleException(MyIndexOutOfBoundsException e){
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

// Global Exception Handling for all Rest Services, instead of local ones at each Rest Service class. AOP supported.

/* This exception handling is also in effect for non REST Controllers. If not desired, just comment the more general one above,
or implement more specific Exceptions, exclusively thrown at REST Controllers only.*/

/*ErrorModel is a customized model class for the Error, which is returned as an argument of the ResponseEntity object, along
with the HttpStatus of that error. The exception handler methods are overloaded, so that for each specific Exception, a different
method and ResponseEntity object is returned to the framework to deal with, and ultimately show as a .json file to the client.
*/
