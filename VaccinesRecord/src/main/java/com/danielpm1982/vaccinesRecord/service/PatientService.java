package com.danielpm1982.vaccinesRecord.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.danielpm1982.vaccinesRecord.dao.PatientDAOInterface;
import com.danielpm1982.vaccinesRecord.entity.Patient;

@Service
public class PatientService implements PatientServiceInterface{
	@Autowired
	private PatientDAOInterface patientDAOInterface;
	@Override
	@Transactional
	public Patient getPatientByPatientId(long patientId) {
		Patient patient = patientDAOInterface.findById(patientId).orElse(null);
		return patient;
	}
	@Override
	public Patient addNewPatient(Patient patient) {
		patient = patientDAOInterface.save(patient);
		return patient;
	}
}
