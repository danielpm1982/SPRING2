package com.danielpm1982.vaccinesRecord.entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

public class UserModelAttribute2 {
	@Min(value = 1, message = "userId is required")
	@Digits(integer = 11, fraction = 0, message = "userId number is required")
	private long id;
	private byte[] photo;
	public UserModelAttribute2(User user) {
		this.id=user.getId();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
}
