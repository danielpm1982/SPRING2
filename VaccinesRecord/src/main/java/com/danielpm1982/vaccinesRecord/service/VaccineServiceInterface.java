package com.danielpm1982.vaccinesRecord.service;
import org.springframework.stereotype.Service;
import com.danielpm1982.vaccinesRecord.entity.Vaccine;

@Service
public interface VaccineServiceInterface{
	public Vaccine getVaccineByVaccineId(long vaccineId);
	public Vaccine addNewVaccine(Vaccine vaccine);
}
