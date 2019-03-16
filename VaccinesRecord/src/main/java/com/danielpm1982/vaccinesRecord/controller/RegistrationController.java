package com.danielpm1982.vaccinesRecord.controller;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.danielpm1982.vaccinesRecord.entity.User;
import com.danielpm1982.vaccinesRecord.entity.UserModelAttribute;
import com.danielpm1982.vaccinesRecord.entity.UserModelAttribute2;
import com.danielpm1982.vaccinesRecord.helper.TempFileManager;
import com.danielpm1982.vaccinesRecord.service.UserServiceInterface;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	@Autowired
    @Qualifier("userService")
    private UserServiceInterface userServiceInterface;
	private final List<String> ROLE_OPTIONS = Arrays.asList("ROLE_PATIENT","ROLE_MANAGER","ROLE_DIRECTOR"); //"ROLE_USER" is set for all Users by default
	@Value("${targetStaticResourceAbsolutePath}")
	private String targetStaticResourceAbsolutePath;
	@Value("${maxNumberOfTempFiles}")
	private int MAX_NUMBER_TEMP_PHOTOS; 
	@Value("${maximumTempFileSizeInBytes}")
	private long MAX_PHOTO_SIZE;
	@Value("${minimumTempFileSizeInBytes}")
	private long MIN_PHOTO_SIZE;
	public RegistrationController() {
	}
	@RequestMapping("/registrationForm")
	public String showRegistrationForm(Model model) {
		model.addAttribute("userModelAttribute", new UserModelAttribute());
		model.addAttribute("roleOptions", ROLE_OPTIONS);
		return "registrationForm";
	}
	@RequestMapping("/registrationFormResult")
	public String showRegistrationFormResult(@Valid @ModelAttribute("userModelAttribute") UserModelAttribute userModelAttribute, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()){
			model.addAttribute("roleOptions", ROLE_OPTIONS);
			return "registrationForm";
        }
		String userName = userModelAttribute.getUserName();
		User user = userServiceInterface.findByUserName(userName);
        if (user != null){
        	model.addAttribute("roleOptions", ROLE_OPTIONS);
        	model.addAttribute("registrationError", "Username already exists.");
        	return "registrationForm";
        } else {
        	userServiceInterface.save(userModelAttribute);
        	User userSaveCheck = userServiceInterface.findByUserName(userName); //finding again, from what has just been saved, for checking if saving really occurred.
        	if(userSaveCheck!=null) {
        		model.addAttribute("user", user);
            	return "registrationFormResult";
        	} else {
        		model.addAttribute("roleOptions", ROLE_OPTIONS);
    			model.addAttribute("registrationError", "Registration failed.");
            	return "registrationForm";
        	}
        }
	}
	@RequestMapping("/registrationFormUpdate")
	public String showRegistrationFormUpdate(Model model) {
		model.addAttribute("userModelAttribute", new UserModelAttribute());
		model.addAttribute("roleOptions", ROLE_OPTIONS);
		return "registrationFormUpdate";
	}
	@RequestMapping("/registrationFormUpdateResult")
	public String showRegistrationFormUpdateResult(@Valid @ModelAttribute("userModelAttribute") UserModelAttribute userModelAttribute, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()){
			model.addAttribute("roleOptions", ROLE_OPTIONS);
			return "registrationFormUpdate";
        }
		Long userId = userModelAttribute.getId();
		User oldUser = userServiceInterface.findByUserId(userId); //finding existing user
		if (oldUser == null){
        	model.addAttribute("userModelAttribute", new UserModelAttribute());
			model.addAttribute("registrationUpdateError", "User id does not exist. User cannot be updated.");
			model.addAttribute("roleOptions", ROLE_OPTIONS);
			return "registrationFormUpdate";
        } else {
        	if(!BCrypt.checkpw(userModelAttribute.getOldPassword(), oldUser.getPassword())) {
        		userModelAttribute.setOldPassword("");
        		model.addAttribute("userModelAttribute", userModelAttribute);
    			model.addAttribute("oldPasswordUpdateError", "Old password does not match the User password at the system. User cannot be updated. Try retyping it.");
    			model.addAttribute("roleOptions", ROLE_OPTIONS);
    			return "registrationFormUpdate";
        	} else {
	        	if(userModelAttribute.getUserName().compareTo(oldUser.getUserName())!=0) {
	        		userModelAttribute.setUserName(oldUser.getUserName());
	        		model.addAttribute("userModelAttribute", userModelAttribute);
	    			model.addAttribute("userNameUpdateError", "You can't update your userName or userId once User is created. You must delete and then create another User if you want a different userName for one same userId.");
	    			model.addAttribute("roleOptions", ROLE_OPTIONS);
	    			return "registrationFormUpdate";
	        	}
        		model.addAttribute("oldUser", new User(oldUser)); //using a new and unmapped User instance, for avoiding returning both old and new User as the same - both updated - when the transaction finally occurs. 
	        	userServiceInterface.save(userModelAttribute);
	        	User updatedUser = userServiceInterface.findByUserId(userId); //finding again the User with the updated data
	        	if(updatedUser!=null) {
	            	model.addAttribute("updatedUser", updatedUser);
	        	}
	        	return "registrationFormUpdateResult";
        	}
        }
	}
	@RequestMapping("/registrationFormQueryResult")
	public String showRegistrationFormQueryResult(UserModelAttribute userModelAttribute, BindingResult bindingResult, Model model) {
		showRegistrationFormQueryHelperMethod(userModelAttribute, bindingResult, model);
		return "registrationFormUpdate";
	}
	@RequestMapping("/registrationFormQueryResult2")
	public String showRegistrationFormQueryResult2(UserModelAttribute userModelAttribute, BindingResult bindingResult, Model model) {
		showRegistrationFormQueryHelperMethod(userModelAttribute, bindingResult, model);
		return "registrationFormDelete";
	}
	private void showRegistrationFormQueryHelperMethod(UserModelAttribute userModelAttribute, BindingResult bindingResult, Model model) {
		User user = null;
		if(userModelAttribute.getId()>0) {
			user = userServiceInterface.findByUserId(userModelAttribute.getId()); //finding existing user by id
		} else if(userModelAttribute.getUserName()!=null&&userModelAttribute.getUserName()!=""){
			user = userServiceInterface.findByUserName(userModelAttribute.getUserName()); //finding existing user by name
		}
        if (user == null || !BCrypt.checkpw(userModelAttribute.getPassword(), user.getPassword())){
        	model.addAttribute("userModelAttribute", new UserModelAttribute());
			model.addAttribute("registrationQueryError", "Neither UserId nor UserName exists or password is wrong. User not found.");
			model.addAttribute("roleOptions", ROLE_OPTIONS);
        } else {
        	userModelAttribute.setId(user.getId());
        	userModelAttribute.setUserName(user.getUserName());
        	userModelAttribute.setPassword("");
        	userModelAttribute.setMatchingPassword("");
        	userModelAttribute.setFirstName(user.getFirstName());
        	userModelAttribute.setLastName(user.getLastName());
        	userModelAttribute.setEmail(user.getEmail());
        	if(user.getPhoto()!=null&&user.getPhoto().length!=0) {
        		TempFileManager.checkIfTempDirectoryIsFull(MAX_NUMBER_TEMP_PHOTOS, targetStaticResourceAbsolutePath); //prepare temp directory - clean if full
        		Path tempPhotoPath = TempFileManager.createTempFileFromByteArray(user.getPhoto(), user.getUserName()+".png", targetStaticResourceAbsolutePath, MAX_NUMBER_TEMP_PHOTOS);
    			model.addAttribute("tempPhotoFileName", tempPhotoPath.getFileName());
    		}
        	model.addAttribute("roleOptions", ROLE_OPTIONS);
        	model.addAttribute("userModelAttribute", userModelAttribute);
        }
	}
	@RequestMapping("/registrationFormDelete")
	public String showRegistrationFormDelete(Model model) {
		model.addAttribute("userModelAttribute", new UserModelAttribute());
		return "registrationFormDelete";
	}
	@RequestMapping("/registrationFormDeleteResult")
	public String showRegistrationFormDeleteResult(UserModelAttribute userModelAttribute, Model model) {
		Long userId = userModelAttribute.getId();
		User user = userServiceInterface.findByUserId(userId); //finding existing user
        if (user == null){
			model.addAttribute("registrationDeleteError", "Username does not exist and User cannot be deleted.");
        	return "registrationFormDelete";
        } else {
        	if(!BCrypt.checkpw(userModelAttribute.getPassword(), user.getPassword())) {
        		userModelAttribute.setPassword("");
        		model.addAttribute("userModelAttribute", userModelAttribute);
    			model.addAttribute("passwordDeleteError", "Password does not match the User password at the system. User cannot be deleted. Try retyping it.");
    			model.addAttribute("roleOptions", ROLE_OPTIONS);
    			return "registrationFormDelete";
        	} else {
        		model.addAttribute("deletingUser", user); //the user that is supposed to be deleted, and will be shown at the view after deletion.
            	userServiceInterface.deleteUserById(user.getId()); //delete through the userId (PK)
            	User deletedUser = userServiceInterface.findByUserName(userModelAttribute.getUserName()); //finding again the deleted User only for confirming it is null (not for showing at the view)
            	if(deletedUser==null) {
            		return "registrationFormDeleteResult";
            	} else {
            		model.addAttribute("registrationDeleteError", "Deletion failed.");
            		return "registrationFormDelete";
            	}
        	}
        }
	}
	@RequestMapping("/registrationFormPhotoUpload")
	public String registrationFormPhotoUpload(@RequestParam("photo") MultipartFile uploadingPhoto, HttpServletRequest request, Model model) throws IOException {
		registrationPhotoUpload(uploadingPhoto, request, model);
		model.addAttribute("roleOptions", ROLE_OPTIONS);
    	model.addAttribute("userModelAttribute", new UserModelAttribute());
    	return "registrationForm";
	}
	@RequestMapping("/registrationFormUpdatePhotoUpload")
	public String registrationFormUpdatePhotoUpload(@RequestParam("photo") MultipartFile uploadingPhoto, HttpServletRequest request, Model model) throws IOException {
		UserModelAttribute userModelAttribute = new UserModelAttribute();
		userModelAttribute.setId(Long.parseLong(request.getParameter("id")));
		userModelAttribute.setPassword(request.getParameter("password"));
		showRegistrationFormQueryHelperMethod(userModelAttribute, null, model);
		registrationPhotoUpload(uploadingPhoto, request, model);
    	return "registrationFormUpdate";
	}
	private void registrationPhotoUpload(@RequestParam("photo") MultipartFile uploadingPhoto, HttpServletRequest request, Model model) throws IOException {
		User user = userServiceInterface.findByUserId(Long.parseLong(request.getParameter("id")));
		if(user!=null) {
			if(BCrypt.checkpw(request.getParameter("password"), user.getPassword())) {
				if(uploadingPhoto!=null&&uploadingPhoto.getBytes().length>(MIN_PHOTO_SIZE)&&uploadingPhoto.getBytes().length<(MAX_PHOTO_SIZE)) {
					UserModelAttribute2 userModelAttribute2 = new UserModelAttribute2(user);
					userModelAttribute2.setPhoto(uploadingPhoto.getBytes());
					userServiceInterface.save(userModelAttribute2);
					user = userServiceInterface.findByUserId(Long.parseLong(request.getParameter("id"))); //finding again for confirmation and tempPhoto generation from the DB LOB data retrieved
					TempFileManager.checkIfTempDirectoryIsFull(MAX_NUMBER_TEMP_PHOTOS, targetStaticResourceAbsolutePath); //prepare temp directory - clean if full
					Path tempPhotoPath = TempFileManager.createTempFileFromByteArray(user.getPhoto(), uploadingPhoto.getOriginalFilename(), targetStaticResourceAbsolutePath, MAX_NUMBER_TEMP_PHOTOS);
					model.addAttribute("uploadConfirmation", true);
					model.addAttribute("uploadConfirmationMessage", "Photo successfully uploaded!");
					model.addAttribute("tempPhotoFileName", tempPhotoPath.getFileName());
				} else {
					model.addAttribute("uploadConfirmation", false);
					model.addAttribute("uploadConfirmationMessage", "Image too small, or too big, or not selected. Size must be at least "+MIN_PHOTO_SIZE+" bytes and at most "+MAX_PHOTO_SIZE+" bytes. Upload failed.");
				}
			} else {
				model.addAttribute("uploadConfirmation", false);
				model.addAttribute("uploadConfirmationMessage", "User Password is wrong. Upload failed.");
			}
		} else {
			model.addAttribute("uploadConfirmation", false);
			model.addAttribute("uploadConfirmationMessage", "User Id does not exist. User not found. Upload failed.");
		}
	}
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
}
