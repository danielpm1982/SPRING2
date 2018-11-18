package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB_AuthDataCrud.entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB_AuthDataCrud.validation.FieldMatch;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB_AuthDataCrud.validation.ValidEmail;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})
public class UserModelAttribute {
	@NotNull(message = "userName is required")
	@Size(min = 1, message = "userName is required")
	private String userName;
	@NotNull(message = "password is required")
	@Size(min = 1, message = "password is required")
	private String password;
	@NotNull(message = "confirming password is required")
	@Size(min = 1, message = "confirming password is required")
	private String matchingPassword;
	@NotNull(message = "first name is required")
	@Size(min = 1, message = "first name is required")
	private String firstName;
	@NotNull(message = "last name is required")
	@Size(min = 1, message = "last name is required")
	private String lastName;
	@ValidEmail
	@NotNull(message = "email is required")
	@Size(min = 1, message = "email is required")
	private String email;
	@NotNull(message = "role is required")
	@Size(min = 1, message = "role is required")
	private String[] roles;
	public UserModelAttribute() {
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
	public String getMatchingPassword() {
		return matchingPassword;
	}
	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String[] getRoles() {
		return roles;
	}
	public void setRoles(String... roles) {
		this.roles = roles;
	}
}

/*
Although put in the entity package, this is not really a persistence class, but only a modelAttribute class that
receives the view attribute values in one single object and then distributes them to the real persistence entities -
that is, the other classes at this package, except this one.  
*/