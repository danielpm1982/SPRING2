package com.danielpm1982.vaccinesRecord.service;
import java.util.List;
import com.danielpm1982.vaccinesRecord.entity.Patient;
import com.danielpm1982.vaccinesRecord.entity.Vaccine;
import com.danielpm1982.vaccinesRecord.entity.VaccineAdministration;
import com.danielpm1982.vaccinesRecord.entity.VaccineAdministrationModelAttribute;

public interface VaccineAdministrationServiceInterface {
	public void administerVaccineOnPatient(VaccineAdministrationModelAttribute vaccineAdministrationModelAttribute);
	public List<VaccineAdministration> getVaccineAdministrationList();
	public List<Vaccine> getVaccinesByPatientId(long patientId);
	public List<Patient> getPatientsByVaccineId(long vaccineId);
	public List<VaccineAdministration> getVaccineAdministrationByPatientIdVaccineId(long patientId, long vaccineId);
}
