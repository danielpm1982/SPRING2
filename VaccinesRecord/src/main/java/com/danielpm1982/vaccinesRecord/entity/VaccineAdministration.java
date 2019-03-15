package com.danielpm1982.vaccinesRecord.entity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.stereotype.Component;

@Component
@Entity
public class VaccineAdministration{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="VACCINE_ADMINISTRATION_ID")
	private long vaccineAdministrationId;
	private long patientId;
	private long vaccineId;
	private LocalDateTime localDateTime;
	private String administrator;
	private String place;
	private boolean administeredSuccessfully;
	public VaccineAdministration() {
	}
	public long getVaccineAdministrationId() {
		return vaccineAdministrationId;
	}
	public void setVaccineAdministrationId(long vaccineAdministrationId) {
		this.vaccineAdministrationId = vaccineAdministrationId;
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
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
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
	public boolean isAdministeredSuccessfully() {
		return administeredSuccessfully;
	}
	public void setAdministeredSuccessfully(boolean administeredSuccessfully) {
		this.administeredSuccessfully = administeredSuccessfully;
	}
	@Override
	public String toString() {
		return "vaccineAdministrationId: "+vaccineAdministrationId+" patientId: "+patientId+" vaccineId: "+vaccineId+" localDateTime: "+localDateTime+" administrator: "+administrator+" place: "+place+" administeredSuccessfully: "+administeredSuccessfully;
	}
}
