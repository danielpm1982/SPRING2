<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Registration Custom Page</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/css.css" />
	</head>
	<body>
		<form:form id="register" action="${pageContext.request.contextPath}/register/registrationFormResult" modelAttribute="userModelAttribute">
       		<c:if test="${registrationError != null}">
				<h3 class=errors>${registrationError}</h3>
			</c:if>
       		<form:errors path="userName" cssClass="errors" />
       		<form:input path="userName" placeholder="username" title="type your username" /><br>
       		<form:errors path="password" cssClass="errors" />
       		<form:input type="password" path="password" placeholder="password" title="type your password" /><br>
       		<form:errors path="matchingPassword" cssClass="errors" />
       		<form:input type="password" path="matchingPassword" placeholder="confirm password" title="type your password again" /><br>
       		<form:errors path="firstName" cssClass="errors" />
       		<form:input path="firstName" placeholder="first name" title="type your first name" /><br>
       		<form:errors path="lastName" cssClass="errors" />
       		<form:input path="lastName" placeholder="last name" title="type your last name" /><br>
       		<form:errors path="email" cssClass="errors" />
       		<form:input path="email" placeholder="email" title="type your email" /><br>
       		<form:select path="roles" items="${roleOptions}" multiple="true" />
       		<form:errors path="roles" cssClass="errors" />
       		<input type="submit" value="Save">
       		<div class="link">
				<a href="${pageContext.request.contextPath}/register/registrationForm">Add Another New User</a>
			</div>
			<div class="link">
				<a href="${pageContext.request.contextPath}/">Go back to login page</a>
			</div>
       	</form:form>
	</body>
</html>
