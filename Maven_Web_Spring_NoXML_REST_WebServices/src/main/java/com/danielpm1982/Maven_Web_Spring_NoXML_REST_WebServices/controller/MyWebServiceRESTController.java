package com.danielpm1982.Maven_Web_Spring_NoXML_REST_WebServices.controller;
import java.io.IOException;
//import java.time.ZoneId;
//import java.time.ZonedDateTime;
//import java.time.format.DateTimeFormatter;
//import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import com.danielpm1982.Maven_Web_Spring_NoXML_REST_WebServices.error.ErrorModel;
import com.danielpm1982.Maven_Web_Spring_NoXML_REST_WebServices.error.MyIndexOutOfBoundsException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/controller")
public class MyWebServiceRESTController {
	private List<String> resultMessageList;
	@RequestMapping("/getLocalRESTWebServiceStringResult")
	public String getLocalRESTWebServiceStringResult() throws JsonParseException, JsonMappingException, IOException {
		String resultMessage = "The REST webService responded: Hello World !";
		return resultMessage;
	}
	@PostConstruct
	private List<String> loadStringListValues(){
		resultMessageList = Arrays.asList("message1","message2","message3","message4","message5");
		return resultMessageList;
	}
	@RequestMapping("/getLocalRESTWebServiceStringAllListResult")
	public List<String> getLocalRESTWebServiceAllListResult() throws JsonParseException, JsonMappingException, IOException {
		return resultMessageList;
	}
	@RequestMapping("/getLocalRESTWebServiceStringPartListResult/{index}")
	public String getLocalRESTWebServicePartListResult(@PathVariable int index) throws JsonParseException, JsonMappingException, IOException {
		if(index>=resultMessageList.size()||index<0) {
			throw new MyIndexOutOfBoundsException("Index is invalid for list of size: "+resultMessageList.size()+". Index (path variable) must be between 0 and "+(resultMessageList.size()-1));
		}
		return resultMessageList.get(index);
	}
	@RequestMapping(value= {"/getLocalRESTWebServiceStringPartListResult/","/getLocalRESTWebServiceStringPartListResult"})
	public String getLocalRESTWebServicePartListResult() throws JsonParseException, JsonMappingException, IOException {
		throw new RuntimeException("You didn't specify any path variable or index in your request... returning the whole data structure: "+resultMessageList);
	}
//	@ExceptionHandler
//	public ResponseEntity<ErrorModel> handleException(MyIndexOutOfBoundsException e){
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

/*
This RestController class deals with requests for String and StringLists (with or without pathVariables). "PartList" is only a part of the list, only the 
specified index element. It also throws some exceptions that will be caught at the methods declared at the ControllerAdvice class. The conversion of
the returned objects to .json format is automatic... for all returning scenarios, including for errors. 
*/

/*
The ExceptionHandlers above were refactored and moved to the @ControllerAdvice class, so that they can be reused for other Controller classes instead 
of only for this one.
*/
