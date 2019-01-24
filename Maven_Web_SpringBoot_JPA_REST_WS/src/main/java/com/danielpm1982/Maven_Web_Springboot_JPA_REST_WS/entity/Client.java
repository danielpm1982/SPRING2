package com.danielpm1982.Maven_Web_Springboot_JPA_REST_WS.entity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="client")
public class Client {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	@Column(name="name")
	private String name;
	@Column(name="email")
	private String email;
	@Column(name="birth_date")
	private LocalDateTime birthDate;
	@Transient
	private String birthDateStringified;
	public Client() {
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDateTime getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDateTime birthDate) {
		this.birthDate = birthDate;
		birthDateStringified = birthDate.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.MEDIUM));
	}
	public String getBirthDateStringified() {
		birthDateStringified = birthDate.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.MEDIUM));
		return birthDateStringified;
	}
	@Override
	public String toString() {
		return "Id: "+id+" Name: "+name+" Email: "+email+" BirthDate: "+birthDateStringified;
	}
}
