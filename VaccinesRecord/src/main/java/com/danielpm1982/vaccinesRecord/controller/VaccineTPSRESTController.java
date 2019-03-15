package com.danielpm1982.vaccinesRecord.controller;
//import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.danielpm1982.vaccinesRecord.entity.Patient;
import com.danielpm1982.vaccinesRecord.entity.Vaccine;
import com.danielpm1982.vaccinesRecord.entity.VaccineAdministration;
import com.danielpm1982.vaccinesRecord.entity.VaccineAdministrationModelAttribute;
import com.danielpm1982.vaccinesRecord.entity.VaccineIndividualPatientsModelAttribute;
import com.danielpm1982.vaccinesRecord.error.RegistryNotFoundException;
import com.danielpm1982.vaccinesRecord.service.PatientServiceInterface;
import com.danielpm1982.vaccinesRecord.service.VaccineAdministrationServiceInterface;
import com.danielpm1982.vaccinesRecord.service.VaccineServiceInterface;

@RestController
@RequestMapping("/api")
public class VaccineTPSRESTController {
	@Autowired
	@Qualifier("vaccineAdministrationService")
	private VaccineAdministrationServiceInterface vaccineAdministrationServiceInterface;
	@Autowired
	@Qualifier("patientService")
	private PatientServiceInterface patientServiceInterface;
	@Autowired
	@Qualifier("vaccineService")
	private VaccineServiceInterface vaccineServiceInterface;
	public VaccineTPSRESTController() {
	}
	@GetMapping("/vaccineAdministrations")
	public List<VaccineAdministration> getVaccineAdministrationList() {
		return vaccineAdministrationServiceInterface.getVaccineAdministrationList();
	}
	@PostMapping("/vaccineAdministrations")
	public String administerVaccineOnPatient(@RequestBody VaccineAdministrationModelAttribute vaccineAdministrationModelAttribute) {
		vaccineAdministrationServiceInterface.administerVaccineOnPatient(vaccineAdministrationModelAttribute);
		return vaccineAdministrationModelAttribute.toString(); 
	}
//	@GetMapping("vaccinesByPatientId/{patientId}")
//	public List<Vaccine> getVaccinesByPatientId(@PathVariable long patientId) {
//		return vaccineAdministrationServiceInterface.getVaccinesByPatientId(patientId);
//	}
	@GetMapping("/vaccineAdministrations/vaccinesByPatientId/{patientId}") //VaccineIndividualPatientsModelAttribute filters the vaccines from other patients and returns only that patient's vaccines. Differently from the commented method above that returns the Vaccine object along with all patients whom that vaccine has been administered to.
	public List<VaccineIndividualPatientsModelAttribute> getVaccinesByPatientId(@PathVariable long patientId) {
		Patient patient = patientServiceInterface.getPatientByPatientId(patientId);
		if (patient == null){
        	throw new RegistryNotFoundException("Patient not found for that Patient Id. Try retyping it or ask the system admin for help.");
        }
		List<Vaccine> vaccinePatientList = vaccineAdministrationServiceInterface.getVaccinesByPatientId(patientId);
		return vaccinePatientList.stream().parallel().map(x->new VaccineIndividualPatientsModelAttribute(x,patientId)).collect(Collectors.toList());
	}
	@GetMapping("/vaccineAdministrations/patientsByVaccineId/{vaccineId}")
	public List<Patient> getPatientsByVaccineId(@PathVariable long vaccineId) {
		Vaccine vaccine = vaccineServiceInterface.getVaccineByVaccineId(vaccineId);
		if (vaccine == null){
        	throw new RegistryNotFoundException("Vaccine not found for that Vaccine Id. Try retyping it or ask the system admin for help.");
        }
		return vaccineAdministrationServiceInterface.getPatientsByVaccineId(vaccineId);
	}
}
