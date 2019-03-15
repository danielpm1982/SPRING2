package com.danielpm1982.vaccinesRecord.dao;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import com.danielpm1982.vaccinesRecord.entity.VaccineAdministration;

public interface VaccineAdministrationDAOInterface{
	public void administerVaccineOnPatient(long patientId, long vaccineId, String administrator, String place, LocalDate localDate, LocalTime localTime);
	public List<VaccineAdministration> getVaccineAdministrationList();
}
