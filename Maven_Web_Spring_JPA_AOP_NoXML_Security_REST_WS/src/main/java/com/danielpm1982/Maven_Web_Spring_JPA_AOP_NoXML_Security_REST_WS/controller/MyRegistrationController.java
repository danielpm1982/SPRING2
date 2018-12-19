package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_REST_WS.controller;
import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_REST_WS.entity.User;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_REST_WS.entity.UserModelAttribute;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_REST_WS.service.UserServiceInterface;

@Controller
@RequestMapping("/register")
public class MyRegistrationController {
	@Autowired
    @Qualifier("userService")
    private UserServiceInterface userService;
	private final List<String> ROLE_OPTIONS = Arrays.asList("ROLE_USER","ROLE_ADMIN","ROLE_MANAGER","ROLE_DIRECTOR","ROLE_CEO");
	@GetMapping("/registrationForm")
	public String showRegistrationForm(Model model) {
		model.addAttribute("userModelAttribute", new UserModelAttribute());
		model.addAttribute("roleOptions", ROLE_OPTIONS);
		return "registrationForm";
	}
	@PostMapping("/registrationFormResult")
	public String showRegistrationFormResult(@Valid @ModelAttribute("userModelAttribute") UserModelAttribute userModelAttribute, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()){
			model.addAttribute("roleOptions", ROLE_OPTIONS);
			return "registrationForm";
        }
		String userName = userModelAttribute.getUserName();
		User user = userService.findByUserName(userName);
        if (user != null){
        	model.addAttribute("roleOptions", ROLE_OPTIONS);
        	model.addAttribute("registrationError", "Username already exists.");
        	return "registrationForm";
        } else {
        	userService.save(userModelAttribute);
        	User userSaveCheck = userService.findByUserName(userName); //finding again, from what has just been saved, for checking if saving really occurred.
        	if(userSaveCheck!=null) {
        		model.addAttribute("user", user);
            	return "registrationFormResult";
        	} else {
        		model.addAttribute("roleOptions", ROLE_OPTIONS);
    			model.addAttribute("registrationError", "Registration failed. Try again or call support.");
            	return "registrationForm";
        	}
        }
	}
	@GetMapping("/registrationFormUpdate")
	public String showRegistrationFormUpdate(Model model) {
		model.addAttribute("userModelAttribute", new UserModelAttribute());
		return "registrationFormUpdate";
	}
	@PostMapping("/registrationFormUpdateResult")
	public String showRegistrationFormUpdateResult(@Valid @ModelAttribute("userModelAttribute") UserModelAttribute userModelAttribute, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()){
			return "registrationFormUpdate";
        }
		String userName = userModelAttribute.getUserName();
		User oldUser = userService.findByUserName(userName); //finding existing user
        if (oldUser == null){
        	model.addAttribute("userModelAttribute", new UserModelAttribute());
			model.addAttribute("registrationUpdateError", "Username does not exist, and cannot be updated.");
        	return "registrationFormUpdate";
        } else {
        	userService.save(userModelAttribute);
        	User newUser = userService.findByUserName(userName); //finding again the User with the updated data
        	model.addAttribute("oldUser", oldUser);
        	model.addAttribute("newUser", newUser);
        	return "registrationFormUpdateResult";
        }
	}
	@PostMapping("/registrationFormQueryResult")
	public String showRegistrationFormQueryResult(UserModelAttribute userModelAttribute, BindingResult bindingResult, Model model) {
		String userName = userModelAttribute.getUserName();
		User user = userService.findByUserName(userName); //finding existing user
        if (user == null){
        	model.addAttribute("userModelAttribute", new UserModelAttribute());
			model.addAttribute("registrationQueryError", "Username does not exist.");
        } else {
        	userModelAttribute.setPassword("");
        	userModelAttribute.setMatchingPassword("");
        	userModelAttribute.setFirstName(user.getFirstName());
        	userModelAttribute.setLastName(user.getLastName());
        	userModelAttribute.setEmail(user.getEmail());
        	model.addAttribute("userModelAttribute", userModelAttribute);
        }
        return "registrationFormUpdate";
	}
	@PostMapping("/registrationFormQueryResult2")
	public String showRegistrationFormQueryResult2(UserModelAttribute userModelAttribute, BindingResult bindingResult, Model model) {
		String userName = userModelAttribute.getUserName();
		User user = userService.findByUserName(userName); //finding existing user
        if (user == null){
        	model.addAttribute("userModelAttribute", new UserModelAttribute());
			model.addAttribute("registrationQueryError", "Username does not exist.");
        } else {
        	userModelAttribute.setPassword("");
        	userModelAttribute.setMatchingPassword("");
        	userModelAttribute.setFirstName(user.getFirstName());
        	userModelAttribute.setLastName(user.getLastName());
        	userModelAttribute.setEmail(user.getEmail());
        	model.addAttribute("userModelAttribute", userModelAttribute);
        }
        return "registrationFormDelete";
	}
	@GetMapping("/registrationFormDelete")
	public String showRegistrationFormDelete(Model model) {
		model.addAttribute("userModelAttribute", new UserModelAttribute());
		return "registrationFormDelete";
	}
	@PostMapping("/registrationFormDeleteResult")
	public String showRegistrationFormDeleteResult(UserModelAttribute userModelAttribute, Model model) {
		User user = userService.findByUserName(userModelAttribute.getUserName()); //finding existing user to delete
        if (user == null){
			model.addAttribute("registrationDeleteError", "Username does not exist and User cannot be deleted.");
        	return "registrationFormDelete";
        } else {
        	model.addAttribute("deletingUser", user); //the user that is supposed to be deleted, and will be shown at the view after deletion.
        	userService.deleteUserById(user.getId()); //delete through the userId (PK)
        	User deletedUser = userService.findByUserName(userModelAttribute.getUserName()); //finding again the deleted User only for confirming it is null (not for showing at the view)
        	if(deletedUser==null) {
        		return "registrationFormDeleteResult";
        	} else {
        		model.addAttribute("registrationDeleteError", "Deletion failed.");
        		return "registrationFormDelete";
        	}
        }
	}
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
}

/*
This Controller class basically defines the mapping urls and controls part of the validation logic for registering (inserting),
searching (querying), updating and deleting User entities - used for authentication and authorization. As the User entity has a 
many to many relationship to the Role, at the DB it is created and managed automatically a third mapping table for keeping the 
necessary joining data for that relationship to be mounted (at runtime).
Without having to use CascadeType.REMOVE at the User entity class, when the User is deleted, it is also deleted from the auxiliary
third table, ending also any relationships to the Role, but not deleting any role.
For making available multiRole selection at the registration form, roleOptions are loaded here and set at the model each time the flux
is redirected to the form, at which it is loaded at the selection form tag, for the user to choose. After, the roles selected for that user
are sent to this controller class, now to be sent, along with the model, to the userService, where these options are then set as
Roles at the User instance before it is persisted at the DB. While persisting the User, JPA also persists the many to many relation of
that user to the N roles it might be linked to at the Role table (using the users_roles auxiliary table). 
*/