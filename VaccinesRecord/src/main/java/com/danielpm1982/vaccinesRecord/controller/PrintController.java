package com.danielpm1982.vaccinesRecord.controller;
import java.nio.file.Path;
import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.danielpm1982.vaccinesRecord.entity.Patient;
import com.danielpm1982.vaccinesRecord.entity.User;
import com.danielpm1982.vaccinesRecord.entity.Vaccine;
import com.danielpm1982.vaccinesRecord.entity.VaccineAdministration;
import com.danielpm1982.vaccinesRecord.helper.TempFileManager;
import com.danielpm1982.vaccinesRecord.service.PatientServiceInterface;
import com.danielpm1982.vaccinesRecord.service.UserServiceInterface;
import com.danielpm1982.vaccinesRecord.service.VaccineAdministrationServiceInterface;
import com.danielpm1982.vaccinesRecord.service.VaccineServiceInterface;

@Controller
@RequestMapping("/print")
public class PrintController {
	@Autowired
	@Qualifier("userService")
	private UserServiceInterface userServiceInterface;
	@Autowired
	@Qualifier("patientService")
	private PatientServiceInterface patientServiceInterface;
	@Autowired
	@Qualifier("vaccineService")
	private VaccineServiceInterface vaccineServiceInterface;
	@Autowired
	@Qualifier("vaccineAdministrationService")
	private VaccineAdministrationServiceInterface vaccineAdministrationServiceInterface;
	@Value("${targetStaticResourceAbsolutePath}")
	private String targetStaticResourceAbsolutePath;
	@Value("${maxNumberOfTempFiles}")
	private int MAX_NUMBER_TEMP_PHOTOS; 
	@RequestMapping
	public String showMis(Principal principal, HttpServletRequest request) {
		if(principal!=null) {
			request.getSession().setAttribute("user",userServiceInterface.findByUserName(principal.getName())); //redundant, because of the MyAuthenticationSuccessHandler class which does exactly the same.
			request.getSession().setAttribute("principal",principal);
		} else {
			request.getSession().setAttribute("principal", null);
		}
		return "print";
	}
	@RequestMapping("/profileByPatientId")
	public String printProfileByPatientId(HttpServletRequest request, Model model){
		TempFileManager.checkIfTempDirectoryIsFull(MAX_NUMBER_TEMP_PHOTOS, targetStaticResourceAbsolutePath); //prepare temp directory - clean if full
		String patientIdString = request.getParameter("patientId");
		if(patientIdString!=null&&patientIdString!="") {
			long patientId = Long.parseLong(request.getParameter("patientId"));
			User user = userServiceInterface.findByUserId(patientId);
			if(user!=null&&user.getPhoto()!=null&&user.getPhoto().length!=0) {
				Path tempPhotoPath = TempFileManager.createTempFileFromByteArray(user.getPhoto(), user.getUserName()+".png", targetStaticResourceAbsolutePath, MAX_NUMBER_TEMP_PHOTOS);
	    		model.addAttribute("tempPhotoFileName", tempPhotoPath.getFileName());
	    		model.addAttribute("user",user);
			}
			Patient patient = patientServiceInterface.getPatientByPatientId(patientId);
			if(patient!=null) {
				model.addAttribute("patient", patient);
				model.addAttribute("patientBirthDate", patient.getBirthDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
				List<Vaccine> vaccineList = vaccineAdministrationServiceInterface.getVaccinesByPatientId(patientId);
				model.addAttribute("vaccineList", vaccineList);
				Map<Long, Path> tempPhotoFileNameMap = new HashMap<>(); //vaccineId - tempPhotoFileName Map
				vaccineList.forEach(x->tempPhotoFileNameMap.put(x.getVaccineId(), //fill Map items with photoFileName or with null if photo does not exist for that Vaccine item.
						(x.getPhoto()!=null&&x.getPhoto().length!=0)?
								TempFileManager.createTempFileFromByteArray(x.getPhoto(), x.getName()+".png", targetStaticResourceAbsolutePath, MAX_NUMBER_TEMP_PHOTOS).getFileName()
								:null));
				model.addAttribute("tempPhotoFileNameMap", tempPhotoFileNameMap);
				Map<Long, List<VaccineAdministration>> vaccineAdministrationMap = new HashMap<>();
				vaccineList.forEach(x->vaccineAdministrationMap.put(x.getVaccineId(), vaccineAdministrationServiceInterface.getVaccineAdministrationByPatientIdVaccineId(patientId, x.getVaccineId())));
				model.addAttribute("vaccineAdministrationMap", vaccineAdministrationMap);
				return "printByPatientId";
			}
		}
		throw new RuntimeException("Invalid Patient Id. Patient not found.");
	}
}
