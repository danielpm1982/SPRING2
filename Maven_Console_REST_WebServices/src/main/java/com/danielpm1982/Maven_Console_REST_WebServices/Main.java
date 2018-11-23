package com.danielpm1982.Maven_Console_REST_WebServices;
import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Main {
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("Hello World !");
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("\nReading from .json file to POJO User");
		Person person = mapper.readValue(new File("data/person.json"), Person.class);
		System.out.println(Person.class.getName()+":");
		System.out.println(person);
		System.out.println("\nWriting from POJO to another .json file");
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.writeValue(new File("data/person2.json"), person);
		System.out.println(new File("data/person2.json").exists()?"Written successfully!":"ERROR!");
	}
}

/*
This is the simplest possible example for showing data-binding between a local .json file and a
POJO java class (with respective setter properties and fields) - READING, and vice versa, 
data-binding between a POJO java class and a new local .json file - WRITING ... using Jackson Project
objects, in this case, ObjectMapper (readValue()/writeValue()).
The data types at the .json files are: integer literals (number), String, array and nested 
Object (Address). See file at: "data/person.json", at the project root. When Writing, a new file
called person2.json is created at the same folder. 
*/
