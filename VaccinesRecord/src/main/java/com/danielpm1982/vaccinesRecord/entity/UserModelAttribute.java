package com.danielpm1982.vaccinesRecord.entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.danielpm1982.vaccinesRecord.validation.FieldMatch;
import com.danielpm1982.vaccinesRecord.validation.ValidEmail;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})
public class UserModelAttribute {
	@Min(value = 1, message = "userId is required")
	@Digits(integer = 11, fraction = 0, message = "userId number is required")
	private long id;
	@NotNull(message = "userName is required")
	@Size(min = 1, message = "userName is required")
	private String userName;
	private String oldPassword;
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
	private byte[] photo;
//	@NotNull(message = "role is required") //as it's been set ROLE_USER as default for all, therefore the user can let it null at the view and, at the service, it will be set the default minimum role - despite the user has selected it or not. 
//	@Size(min = 1, message = "role is required")
	private String[] roles;
	public UserModelAttribute() {
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
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
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
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	@Override
	public String toString() {
		return "id: "+id+" userName: "+userName+" password: "+password+" firstName: "+firstName+" lastName: "+lastName+" email: "+email+" roles: "+roles; 
	}
}
