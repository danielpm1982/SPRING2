package com.danielpm1982.vaccinesRecord.service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.danielpm1982.vaccinesRecord.dao.PatientDAOInterface;
import com.danielpm1982.vaccinesRecord.dao.VaccineAdministrationDAOInterface;
import com.danielpm1982.vaccinesRecord.dao.VaccineDAOInterface;
import com.danielpm1982.vaccinesRecord.entity.Patient;
import com.danielpm1982.vaccinesRecord.entity.Vaccine;
import com.danielpm1982.vaccinesRecord.entity.VaccineAdministration;
import com.danielpm1982.vaccinesRecord.entity.VaccineAdministrationModelAttribute;

@Service
public class VaccineAdministrationService implements VaccineAdministrationServiceInterface{
	@Autowired
	@Qualifier("vaccineAdministrationDAO")
	private VaccineAdministrationDAOInterface vaccineAdministrationDAOInterface;
	@Autowired
	private VaccineDAOInterface vaccineDAOInterface;
	@Autowired
	private PatientDAOInterface patientDAOInterface;
	@Override
	@Transactional
	public void administerVaccineOnPatient(VaccineAdministrationModelAttribute vaccineAdministrationModelAttribute) {
		vaccineAdministrationDAOInterface.administerVaccineOnPatient(vaccineAdministrationModelAttribute.getPatientId(), vaccineAdministrationModelAttribute.getVaccineId(), vaccineAdministrationModelAttribute.getAdministrator(), vaccineAdministrationModelAttribute.getPlace(), vaccineAdministrationModelAttribute.getLocalDate(), vaccineAdministrationModelAttribute.getLocalTime());
	}
	@Override
	@Transactional
	public List<VaccineAdministration> getVaccineAdministrationList() {
		return vaccineAdministrationDAOInterface.getVaccineAdministrationList();
	}
	@Override
	@Transactional
	public List<Vaccine> getVaccinesByPatientId(long patientId) {
		List<VaccineAdministration> vaccineAdministrationList = vaccineAdministrationDAOInterface.getVaccineAdministrationList();
		vaccineAdministrationList = vaccineAdministrationList.stream().parallel().filter(x->x.getPatientId()==patientId).collect(Collectors.toList());
		List<Vaccine> vaccineList = vaccineAdministrationList.stream().parallel().map(x->vaccineDAOInterface.findById(x.getVaccineId()).get()).collect(Collectors.toList());
		List<Vaccine> vaccineListNoDuplicates = new ArrayList<>();
		vaccineList.forEach(x->{if(!vaccineListNoDuplicates.contains(x)){vaccineListNoDuplicates.add(x);}});
		return vaccineListNoDuplicates;
	}
	@Override
	@Transactional
	public List<Patient> getPatientsByVaccineId(long vaccineId) {
		List<VaccineAdministration> vaccineAdministrationList = vaccineAdministrationDAOInterface.getVaccineAdministrationList();
		vaccineAdministrationList = vaccineAdministrationList.stream().parallel().filter(x->x.getVaccineId()==vaccineId).collect(Collectors.toList());
		List<Patient> patientList = new ArrayList<>();
		//after getting each Patient from an VaccineAdministrationList, its Address field value must be fetched from the DB in order to avoid lazy fetch errors. When the method patientDAOInterface.findById() method ends, so does its session... and Patient lazy fetched fields cannot be fetched anymore. It has to be fetched still inside the method, while the session is still open. ToString() method below does just that: 
		vaccineAdministrationList.forEach(x->{Patient patient = patientDAOInterface.findById(x.getPatientId()).get(); patient.toString(); patientList.add(patient);}); 
		List<Patient> patientListNoDuplicates = new ArrayList<>();
		patientList.forEach(x->{if(!patientListNoDuplicates.contains(x)){patientListNoDuplicates.add(x);}});
		return patientListNoDuplicates;
	}
	@Override
	public List<VaccineAdministration> getVaccineAdministrationByPatientIdVaccineId(long patientId, long vaccineId) {
		List<VaccineAdministration> vaccineAdministrationList = vaccineAdministrationDAOInterface.getVaccineAdministrationList().stream().parallel().filter(x->x.getPatientId()==patientId&&x.getVaccineId()==vaccineId).collect(Collectors.toList());
		return vaccineAdministrationList;
	}
}
