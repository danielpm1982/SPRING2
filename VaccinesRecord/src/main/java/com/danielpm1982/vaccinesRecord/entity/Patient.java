package com.danielpm1982.vaccinesRecord.entity;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="PATIENT")
public class Patient {
	@Id
	@Column(name="PATIENT_ID")
	private long patientId;
	@NotNull(message = "name is required")
	@Size(min = 1, message = "name is required")
	@Column(name="NAME")
	private String name;
	@NotNull(message = "address is required")
	@Size(min = 1, message = "address is required")
	@ElementCollection
    @CollectionTable(name="ADDRESS", joinColumns=@JoinColumn(name="PATIENT_ID"))
    private List<Address> address;
	@NotNull(message = "email is required")
	@Size(min = 1, message = "email is required")
	@Column(name="EMAIL")
    private String email;
	@NotNull(message = "phoneNumber is required")
	@Size(min = 1, message = "phoneNumber is required")
	@Column(name="PHONE_NO")
    @ElementCollection
    @CollectionTable(name="PHONE_NUMBER", joinColumns=@JoinColumn(name="PATIENT_ID"))
    private List<String> phoneNo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="BIRTH_DATE")
	private LocalDate birthDate;
	@Transient
	private int age;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER, mappedBy="patientId")
	private List<VaccineAdministration> vaccineAdministrationList;
	public Patient() {
	}
	public long getPatientId() {
		return patientId;
	}
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Address> getAddress() {
		return address;
	}
	public void setAddress(List<Address> address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(List<String> phoneNo) {
		this.phoneNo = phoneNo;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public int getAge() {
		if(birthDate!=null) {
			return Period.between(birthDate, LocalDate.now()).getYears();
		}
		return 0;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public List<VaccineAdministration> getVaccineAdministrationList() {
		return vaccineAdministrationList;
	}
	public void setVaccineAdministrationList(List<VaccineAdministration> vaccineAdministrationList) {
		this.vaccineAdministrationList = vaccineAdministrationList;
	}
	@Override
	public String toString() {
		return "patientId: "+patientId+" patientName: "+name+" address: "+address+" email: "+email+" birthDate: "+birthDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL))+" age: "+getAge()+" contact: "+phoneNo+(vaccineAdministrationList==null||vaccineAdministrationList.isEmpty()?"":" vaccineAdministrationList: "+vaccineAdministrationList);
	}
	@Override
	public boolean equals(Object obj) {
		return ((Patient)obj).getPatientId()==this.getPatientId();
	}
}
