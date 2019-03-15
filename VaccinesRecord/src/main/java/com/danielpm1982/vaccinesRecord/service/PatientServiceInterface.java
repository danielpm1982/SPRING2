package com.danielpm1982.vaccinesRecord.service;
import com.danielpm1982.vaccinesRecord.entity.Patient;

public interface PatientServiceInterface {
	public Patient getPatientByPatientId(long patientId);
	public Patient addNewPatient(Patient patient);
}
