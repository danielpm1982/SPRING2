package com.danielpm1982.vaccinesRecord.entity;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

public class VaccineAdministrationModelAttribute {
	@NotNull(message = "patientId is required")
	@Min(value = 1, message = "patientId is required")
	private long patientId;
	@NotNull(message = "vaccineId is required")
	@Min(value = 1, message = "vaccineId is required")
	private long vaccineId;
	@NotNull(message = "administrator is required")
	@Size(min = 1, message = "administrator is required")
	private String administrator;
	@NotNull(message = "place is required")
	@Size(min = 1, message = "place is required")
	private String place;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate localDate;
	@DateTimeFormat(pattern="HH:mm:ss")
	private LocalTime localTime;
	public VaccineAdministrationModelAttribute() {
	}
	public VaccineAdministrationModelAttribute(long patientId, long vaccineId, String administrator, String place, LocalDate localDate, LocalTime localTime) {
		this.patientId=patientId;
		this.vaccineId=vaccineId;
		this.administrator=administrator;
		this.place=place;
		this.localDate=localDate;
		this.localTime=localTime;
	}
	public long getPatientId() {
		return patientId;
	}
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	public long getVaccineId() {
		return vaccineId;
	}
	public void setVaccineId(long vaccineId) {
		this.vaccineId = vaccineId;
	}
	public String getAdministrator() {
		return administrator;
	}
	public void setAdministrator(String administrator) {
		this.administrator = administrator;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public LocalDate getLocalDate() {
		return localDate;
	}
	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}
	public LocalTime getLocalTime() {
		return localTime;
	}
	public void setLocalTime(LocalTime localTime) {
		this.localTime = localTime;
	}
	@Override
	public String toString() {
		return "patientId: "+patientId+" vaccineId: "+vaccineId+" administrator: "+administrator+" place: "+place+(localDate!=null?(" date: "+localDate):"")+(localTime!=null?(" time: "+localTime):"");
	}
}
