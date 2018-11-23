package com.danielpm1982.Maven_Console_REST_WebServices;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true) //in case there are data at the .json file with no mapping properties at the POJO class. Not the case here.
public class Person {
	private long id;
	private String userName;
	private String password;
	private boolean active;
	private String[] contact;
	private Address address;
	public Person() {
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String[] getContact() {
		return contact;
	}
	public void setContact(String[] contact) {
		this.contact = contact;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "id: "+id+" userName: "+userName+" password: "+password+" active: "+active+" contact: "+Arrays.asList(contact)+" address: "+address;
	}
}

// a simple POJO to be data-binded to a local .json file or an external interface-compatible REST Web Service.
