package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB_AuthDataCrud.config;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB_AuthDataCrud.entity.User;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB_AuthDataCrud.service.UserServiceInterface;;

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    @Qualifier("userService")
    private UserServiceInterface userService;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		System.out.println("\n\nIn customAuthenticationSuccessHandler\n\n");
		String userName = authentication.getName();
		System.out.println("userName = " + userName);
		User user = userService.findByUserName(userName);
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		response.sendRedirect(request.getContextPath() + "/");
	}
}

/*
This is a basic event listener class definer, in which, when authentication succeeds, that catched event triggers
the method above to find the User at the DB, using its userName, and set that found User to the HttpSession, for
avoiding the same authenticated User to have to register multiple times. It also redirects from the login processing
url - "/controller/customLoginPageResult" - to the root "/" mapping url of the app (with the user saved at the session).
For custom login and custom login processing urls, see the class: MyWebSecurityConfigurerAdapter configurations.
*/
