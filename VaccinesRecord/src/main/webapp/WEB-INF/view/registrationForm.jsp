<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Registration</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" type="text/css" media="screen and (min-width:360px) and (max-width:999px)" href="${pageContext.request.contextPath}/css/css2.css" />
		<link rel="stylesheet" type="text/css" media="screen and (min-width:1000px)" href="${pageContext.request.contextPath}/css/css.css" />
	</head>
	<body>
		<form:form id="register" action="${pageContext.request.contextPath}/register/registrationFormResult" modelAttribute="userModelAttribute">
       		<p>Add your data for a new User profile.</p>
       		<c:if test="${registrationError != null}">
				<h3 class=errors>${registrationError}</h3>
			</c:if>
       		<form:errors path="id" cssClass="errors" />
       		<form:input path="id" type="number" id="inputId" placeholder="userId (11-digit-max)" title="type an unique 11-digit-max id (SSN, CPF, etc)" onfocus="if(this.value==0){this.value='';}" onblur="if(this.value==''){this.value=0;}" onkeypress="enterFunction(event);" /><br>
       		<form:errors path="userName" cssClass="errors" />
       		<form:input path="userName" placeholder="username" title="type your username" oninput="this.value=this.value.toLowerCase();" /><br>
       		<form:errors path="password" cssClass="errors" />
       		<form:input type="password" path="password" placeholder="password" title="type your password" /><br>
       		<form:errors path="matchingPassword" cssClass="errors" />
       		<form:input type="password" path="matchingPassword" placeholder="confirm password" title="type your password again" /><br>
       		<form:errors path="firstName" cssClass="errors" />
       		<form:input path="firstName" placeholder="first name" title="type your first name" /><br>
       		<form:errors path="lastName" cssClass="errors" />
       		<form:input path="lastName" placeholder="last name" title="type your last name" /><br>
       		<form:errors path="email" cssClass="errors" />
       		<form:input path="email" placeholder="email" title="type your email" oninput="this.value=this.value.toLowerCase();" /><br>
       		<form:errors path="roles" cssClass="errors" />
       		<form:select path="roles" items="${roleOptions}" multiple="true" />
       		<input type="submit" value="Save">
       		<div class="link">
				<a href="${pageContext.request.contextPath}/register/registrationForm">Add Another New User</a>
			</div>
			<div class="link">
				<a href="${pageContext.request.contextPath}/security/login">Go back to login page</a>
			</div>
       	</form:form>
       	<form:form id="uploadPhoto" action="${pageContext.request.contextPath}/register/registrationFormPhotoUpload" enctype="multipart/form-data">
       		<p>Select a photo for an existing User profile.</p>
       		<c:if test="${uploadConfirmation}">
				<h3 class=errors id="uploadConfirmationMessage1">${uploadConfirmationMessage}</h3><br>
				<img src="${pageContext.request.contextPath}/temp/${tempPhotoFileName}" class="img9" id="img"/><br>
			</c:if>
			<c:if test="${not uploadConfirmation}">
				<h3 class=errors id="uploadConfirmationMessage2">${uploadConfirmationMessage}</h3><br>
			</c:if>
       		<input type="file" name="photo" title="upload photo" onchange="clearPhotoAndUploadConfirmationMessage()" /><br>
       		<h6 class="registrationForm"><c:out value="(photo size must be > 10KiB (10240 bytes) and < 10MiB (10485760 bytes)" /></h6>
       		<input type="number" name="id" id="inputId" placeholder="userId (11-digit-max)" title="type an unique 11-digit-max id (SSN, CPF, etc)" onfocus="if(this.value==0){this.value='';}" onblur="if(this.value==''){this.value=0;}" onkeypress="enterFunction(event);" required/><br>
       		<input type="password" name="password" placeholder="password" title="type your password" required /><br>
       		<input type="submit" value="Upload">
       		<div class="link">
				<a href="${pageContext.request.contextPath}/security/login">Go back to login page</a>
			</div>
  		</form:form>
       	<script>
			function enterFunction(e){
				if(e.keyCode === 13){
					var input = document.getElementById('inputId');
					if(input.value===""){
						input.value=0;
					}
				}
			}
			function clearPhotoAndUploadConfirmationMessage(){
				if(document.getElementById("img")){
					var imgElement = document.getElementById("img");
					imgElement.parentNode.removeChild(imgElement);
				}
				if(document.getElementById("uploadConfirmationMessage1")){
					var messageElement1 = document.getElementById("uploadConfirmationMessage1");
					messageElement1.parentNode.removeChild(messageElement1);
				}
				if(document.getElementById("uploadConfirmationMessage2")){
					var messageElement2 = document.getElementById("uploadConfirmationMessage2");
					messageElement2.parentNode.removeChild(messageElement2);
				}
				return false;
			}
		</script>
	</body>
</html>
