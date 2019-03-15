package com.danielpm1982.vaccinesRecord.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.danielpm1982.vaccinesRecord.dao.VaccineDAOInterface;
import com.danielpm1982.vaccinesRecord.entity.Vaccine;

@Service
public class VaccineService implements VaccineServiceInterface{
	@Autowired
	private VaccineDAOInterface vaccineDAOInterface;
	@Transactional
	@Override
	public Vaccine getVaccineByVaccineId(long vaccineId) {
		return vaccineDAOInterface.findById(vaccineId).orElse(null);
	}
	@Transactional
	@Override
	public Vaccine addNewVaccine(Vaccine vaccine) {
		return vaccineDAOInterface.save(vaccine);
	}
}
