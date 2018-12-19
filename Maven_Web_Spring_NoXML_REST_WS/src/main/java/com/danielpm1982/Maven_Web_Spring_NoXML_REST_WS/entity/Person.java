package com.danielpm1982.Maven_Web_Spring_NoXML_REST_WS.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Person {
	private long id;
	private String userName;
	private String password;
	private boolean active;
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
	@Override
	public String toString() {
		return "id: "+id+" userName: "+userName+" password: "+password+" active: "+active;
	}
}

// a simple POJO to be data-binded to a local .json file or an external interface-compatible REST Web Service.
