package com.danielpm1982.vaccinesRecord.controller;
import java.io.IOException;
import java.nio.file.Path;
import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.danielpm1982.vaccinesRecord.entity.Patient;
import com.danielpm1982.vaccinesRecord.entity.User;
import com.danielpm1982.vaccinesRecord.entity.Vaccine;
import com.danielpm1982.vaccinesRecord.entity.VaccineAdministration;
import com.danielpm1982.vaccinesRecord.entity.VaccineAdministrationModelAttribute;
import com.danielpm1982.vaccinesRecord.helper.TempFileManager;
import com.danielpm1982.vaccinesRecord.service.PatientServiceInterface;
import com.danielpm1982.vaccinesRecord.service.UserServiceInterface;
import com.danielpm1982.vaccinesRecord.service.VaccineAdministrationServiceInterface;
import com.danielpm1982.vaccinesRecord.service.VaccineServiceInterface;

@Controller
@RequestMapping("/mis")
public class VaccineMISController {
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
	@Value("${maximumTempFileSizeInBytes}")
	private long MAX_PHOTO_SIZE;
	@Value("${minimumTempFileSizeInBytes}")
	private long MIN_PHOTO_SIZE;
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
		return "mis";
	}
	@RequestMapping("/profileByPatientId")
	public String showProfileByPatientId(HttpServletRequest request, Model model){
		TempFileManager.checkIfTempDirectoryIsFull(MAX_NUMBER_TEMP_PHOTOS, targetStaticResourceAbsolutePath); //prepare temp directory - clean if full
		if(request.getParameter("patientId")==null&&request.getParameter("patientId")=="") {
			request.setAttribute("inputPatientIdError", "Patient Id invalid. Patient can not be found.");
			return "mis";
		} else {
			long patientId = Long.parseLong(request.getParameter("patientId"));
			Patient patient = patientServiceInterface.getPatientByPatientId(patientId);
			if(patient==null) {
				request.setAttribute("inputPatientIdError", "Patient Id does not exist! Patient can not be found.");
				return "mis";
			} else {
				User user = userServiceInterface.findByUserId(patientId);
				if(user!=null&&user.getPhoto()!=null&&user.getPhoto().length!=0) {
					Path tempPhotoPath = TempFileManager.createTempFileFromByteArray(user.getPhoto(), user.getUserName()+".png", targetStaticResourceAbsolutePath, MAX_NUMBER_TEMP_PHOTOS);
	    			model.addAttribute("tempPhotoFileName", tempPhotoPath.getFileName());
	    		}
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
				Map<Long, List<VaccineAdministration>> vaccineAdministrationMap = new HashMap<>(); //vaccineId - vaccineAdministrationList Map
				vaccineList.forEach(x->vaccineAdministrationMap.put(x.getVaccineId(), vaccineAdministrationServiceInterface.getVaccineAdministrationByPatientIdVaccineId(patientId, x.getVaccineId())));
				model.addAttribute("vaccineAdministrationMap", vaccineAdministrationMap);
				return "profileByPatientId";
			}
		}
	}
	@RequestMapping("/addNewPatient")
	public String addNewPatient(Model model){
		model.addAttribute("patientModel", new Patient());
		return "addNewPatientForm";
	}
	@RequestMapping("/addNewPatientResult")
	public String addNewPatientResult(Model model, @Valid @ModelAttribute("patientModel") Patient patientModel, BindingResult bindingResult){
		if(bindingResult!=null&&bindingResult.hasErrors()) {
			model.addAttribute("patientModel", patientModel);
			return "addNewPatientForm";
		}
		if(patientServiceInterface.getPatientByPatientId(patientModel.getPatientId())!=null) {
			model.addAttribute("inputPatientIdError", "Patient Id already exists! New Patient can not be added.");
			model.addAttribute("patientModel", patientModel);
			return "addNewPatientForm";
		} else {
			patientModel.setAddress(patientModel.getAddress().stream().filter(x->x.getStreet()!=null).collect(Collectors.toList())); //eliminating null addresses before persisting...
			patientModel.setPhoneNo(patientModel.getPhoneNo().stream().filter(x->x!=null).collect(Collectors.toList())); //eliminating null phone numbers before persisting...
			if(patientModel.getAddress().isEmpty()||patientModel.getPhoneNo().isEmpty()||patientModel.getPatientId()==0) {
				if(patientModel.getAddress().isEmpty()) {
					model.addAttribute("addressError","At least one address must be given!");
				}
				if(patientModel.getPhoneNo().isEmpty()) {
					model.addAttribute("phoneError","At least one phone number must be given!");
				}
				if(patientModel.getPatientId()==0) {
					model.addAttribute("idError","An unique Id must be given, for ex, SSN or CPF!");
				}
				model.addAttribute("patientModel", patientModel);
				return "addNewPatientForm";
			}
			Patient addedPatient = patientServiceInterface.addNewPatient(patientModel);
			model.addAttribute("addedPatient", addedPatient);
			return "addNewPatientFormResult";
		}
	}
	@RequestMapping("/profileByVaccineId")
	public String showVaccineByVaccineId(HttpServletRequest request, Model model){
		if(request.getParameter("vaccineId")==null&&request.getParameter("vaccineId")=="") {
			request.setAttribute("inputVaccineIdError", "Vaccine Id invalid. Vaccine can not be found.");
			return "mis";
		} else {
			long vaccineId = Long.parseLong(request.getParameter("vaccineId"));
			Vaccine vaccine = vaccineServiceInterface.getVaccineByVaccineId(vaccineId);
			if(vaccine==null) {
				request.setAttribute("inputVaccineIdError", "Vaccine Id does not exist! Vaccine can not be found.");
				return "mis";
			} else {
				model.addAttribute("vaccine", vaccine);
				if(vaccine.getPhoto()!=null&&vaccine.getPhoto().length!=0) {
	    			Path tempPhotoPath = TempFileManager.createTempFileFromByteArray(vaccine.getPhoto(), vaccine.getName()+".png", targetStaticResourceAbsolutePath, MAX_NUMBER_TEMP_PHOTOS);
	    			model.addAttribute("tempPhotoFileName", tempPhotoPath.getFileName());
	    		}
				return "profileByVaccineId";
			}
		}
	}
	@RequestMapping("/addNewVaccine")
	public String addNewVaccine(Model model){
		model.addAttribute("vaccineModel", new Vaccine());
		return "addNewVaccineForm";
	}
	@RequestMapping("/addNewVaccineResult")
	public String addNewVaccineResult(Model model, @Valid @ModelAttribute("vaccineModel") Vaccine vaccineModel, BindingResult bindingResult){
		if(bindingResult!=null&&bindingResult.hasErrors()) {
			model.addAttribute("vaccineModel", vaccineModel);
			return "addNewVaccineForm";
		}
		Vaccine addedVaccine = vaccineServiceInterface.addNewVaccine(vaccineModel);
		model.addAttribute("addedVaccine", addedVaccine);
		return "addNewVaccineFormResult";
	}
	@RequestMapping("addNewVaccineAdministration")
	public String addNewVaccineAdministration(Model model) {
		model.addAttribute("vaccineAdministrationModel", new VaccineAdministrationModelAttribute());
		return "addNewVaccineAdministrationForm";
	}
	@RequestMapping("addNewVaccineAdministrationResult")
	public String addNewVaccineAdministrationResult(Model model, @Valid @ModelAttribute("vaccineAdministrationModel") VaccineAdministrationModelAttribute vaccineAdministrationModelAttribute, BindingResult bindingResult) {
		if(bindingResult!=null&&bindingResult.hasErrors()) {
			model.addAttribute("vaccineAdministrationModel", vaccineAdministrationModelAttribute);
			return "addNewVaccineAdministrationForm";
		}
		if(patientServiceInterface.getPatientByPatientId(vaccineAdministrationModelAttribute.getPatientId())==null) {
			model.addAttribute("inputPatientIdError", "Patient Id does not exist! Patient can not be found.");
			model.addAttribute("vaccineAdministrationModel", vaccineAdministrationModelAttribute);
			return "addNewVaccineAdministrationForm";
		}
		if(vaccineServiceInterface.getVaccineByVaccineId(vaccineAdministrationModelAttribute.getVaccineId())==null) {
			model.addAttribute("inputVaccineIdError", "Vaccine Id does not exist! Vaccine can not be found.");
			model.addAttribute("vaccineAdministrationModel", vaccineAdministrationModelAttribute);
			return "addNewVaccineAdministrationForm";
		}
		vaccineAdministrationServiceInterface.administerVaccineOnPatient(vaccineAdministrationModelAttribute);
		model.addAttribute("addedVaccineAdministration", vaccineAdministrationModelAttribute);
		return "addNewVaccineAdministrationFormResult";
	}
	@RequestMapping("vaccineAdministrations")
	public String vaccineAdministrations(Model model) {
		List<VaccineAdministration> vaccineAdministrationsList = vaccineAdministrationServiceInterface.getVaccineAdministrationList();
		model.addAttribute("vaccineAdministrationsList", vaccineAdministrationsList);
		return "vaccineAdministrations";
	}
	@RequestMapping("/addNewVaccinePhotoUpload")
	public String addNewVaccinePhotoUpload(@RequestParam("photo") MultipartFile uploadingPhoto, HttpServletRequest request, Model model) throws IOException {
		newVaccinePhotoUpload(uploadingPhoto, request, model);
		model.addAttribute("vaccineModel", new Vaccine());
		return "addNewVaccineForm";
	}
	private void newVaccinePhotoUpload(@RequestParam("photo") MultipartFile uploadingPhoto, HttpServletRequest request, Model model) throws IOException {
		Vaccine vaccine = vaccineServiceInterface.getVaccineByVaccineId(Long.parseLong(request.getParameter("id")));
		if(vaccine!=null) {
			if(uploadingPhoto!=null&&uploadingPhoto.getBytes().length>(MIN_PHOTO_SIZE)&&uploadingPhoto.getBytes().length<(MAX_PHOTO_SIZE)) {
				vaccine.setPhoto(uploadingPhoto.getBytes());
				vaccineServiceInterface.addNewVaccine(vaccine);
				vaccine = vaccineServiceInterface.getVaccineByVaccineId(Long.parseLong(request.getParameter("id"))); //finding again for confirmation and tempPhoto generation from the DB LOB data retrieved
				if(vaccine.getPhoto()!=null) {
					Path tempPhotoPath = TempFileManager.createTempFileFromByteArray(vaccine.getPhoto(), uploadingPhoto.getOriginalFilename(), targetStaticResourceAbsolutePath, MAX_NUMBER_TEMP_PHOTOS);
					model.addAttribute("uploadConfirmation", true);
					model.addAttribute("uploadConfirmationMessage", "Photo successfully uploaded!");
					model.addAttribute("tempPhotoFileName", tempPhotoPath.getFileName());
				} else {
					model.addAttribute("uploadConfirmation", false);
					model.addAttribute("uploadConfirmationMessage", "No photo available.");
				}
			} else {
				model.addAttribute("uploadConfirmation", false);
				model.addAttribute("uploadConfirmationMessage", "Image too small, or too big, or not selected. Size must be at least "+MIN_PHOTO_SIZE+" bytes and at most "+MAX_PHOTO_SIZE+" bytes. Upload failed.");
			}
		} else {
			model.addAttribute("uploadConfirmation", false);
			model.addAttribute("uploadConfirmationMessage", "Vaccine Id does not exist. Vaccine not found. Upload failed.");
		}
	}
	@InitBinder
	public void initBinder(WebDataBinder wdb) {
		StringTrimmerEditor trimmer = new StringTrimmerEditor(true);
		wdb.registerCustomEditor(String.class, trimmer);
	}
}
