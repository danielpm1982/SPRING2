package com.danielpm1982.vaccinesRecord.controller;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import com.danielpm1982.vaccinesRecord.service.PatientServiceInterface;
import com.danielpm1982.vaccinesRecord.service.UserServiceInterface;
import com.danielpm1982.vaccinesRecord.service.VaccineAdministrationServiceInterface;

@Controller
@RequestMapping("/")
public class VaccineWSController {
	@Autowired
	@Qualifier("userService")
	private UserServiceInterface userServiceInterface;
	@Autowired
	@Qualifier("patientService")
	private PatientServiceInterface patientServiceInterface;
	@Autowired
	@Qualifier("vaccineAdministrationService")
	private VaccineAdministrationServiceInterface vaccineAdministrationServiceInterface;
	@RequestMapping("/ws")
	public String showWs(Principal principal, HttpServletRequest request) {
		if(principal!=null) {
			request.getSession().setAttribute("user",userServiceInterface.findByUserName(principal.getName())); //redundant, because of the MyAuthenticationSuccessHandler class which does exactly the same.
			request.getSession().setAttribute("principal",principal);
		} else {
			request.getSession().setAttribute("principal", null);
		}
		return "ws";
	}
	@InitBinder
	public void initBinder(WebDataBinder wdb) {
		StringTrimmerEditor trimmer = new StringTrimmerEditor(true);
		wdb.registerCustomEditor(String.class, trimmer);
	}
}
