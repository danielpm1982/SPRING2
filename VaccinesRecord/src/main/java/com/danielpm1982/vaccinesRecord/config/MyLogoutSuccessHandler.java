package com.danielpm1982.vaccinesRecord.config;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import com.danielpm1982.vaccinesRecord.entity.User;
import com.danielpm1982.vaccinesRecord.service.UserServiceInterface;;

@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    @Qualifier("userService")
    private UserServiceInterface userService;
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		System.out.println("\nIn LogoutSuccessHandler. User successfully logged out:");
		String userName = authentication.getName();
		User user = userService.findByUserName(userName);
		System.out.println(user+"\n");
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		response.sendRedirect(request.getContextPath() + "/security/logout");
	}
}
