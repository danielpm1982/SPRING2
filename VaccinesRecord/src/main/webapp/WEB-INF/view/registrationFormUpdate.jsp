<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Registration Update Custom Page</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" type="text/css" media="screen and (min-width:360px) and (max-width:999px)" href="${pageContext.request.contextPath}/css/css2.css" />
		<link rel="stylesheet" type="text/css" media="screen and (min-width:1000px)" href="${pageContext.request.contextPath}/css/css.css" />
	</head>
	<body>
		<c:if test="${userModelAttribute.userName == null}">
			<form:form id="register" action="${pageContext.request.contextPath}/register/registrationFormQueryResult" modelAttribute="userModelAttribute">
				<p>Search for an existing User profile</p>
				<p>(type your userName OR userId AND your password).</p>
				<c:if test="${registrationQueryError != null}">
					<h3 class=errors>${registrationQueryError}</h3>
				</c:if>
	       		<form:errors path="userName" cssClass="errors" />
	       		<form:input path="userName" placeholder="username" title="type your username" /><br>
	       		<form:errors path="id" cssClass="errors" />
	       		<form:input path="id" type="number" class="inputId" placeholder="userId (11-digit-max)" title="type an unique 11-digit-max id (SSN, CPF, etc)" onfocus="if(this.value==0){this.value='';}" onblur="if(this.value==''){this.value=0;}" onkeypress="enterFunction(event);" /><br>
       			<form:errors path="password" cssClass="errors" />
	       		<form:input path="password" type="password" placeholder="password" title="type your password" required="true"/><br>
       			<input type="submit" value="Search">
       		</form:form>
		</c:if>
		<form:form id="register" action="${pageContext.request.contextPath}/register/registrationFormUpdateResult" modelAttribute="userModelAttribute">
       		<p>Fill out the data for an existing User profile to be updated.</p>
       		<p>(or use the search at the form above)</p>
       		<p>Confirm with your password to proceed.</p>
       		<c:if test="${registrationUpdateError != null}">
				<h3 class=errors>${registrationUpdateError}</h3>
			</c:if>
			<c:if test="${tempPhotoFileName != null}">
				<img id="userPhoto" src="${pageContext.request.contextPath}/temp/${tempPhotoFileName}" class="img9" id="img"/><br>
			</c:if>
			<form:errors path="id" cssClass="errors" />
       		<form:input path="id" type="number" class="inputId" placeholder="userId (11-digit-max)" title="type an unique 11-digit-max id (SSN, CPF, etc)" onfocus="if(this.value==0){this.value='';}" onblur="if(this.value==''){this.value=0;}" onkeypress="enterFunction(event);" /><br>
       		<c:if test="${userNameUpdateError != null}">
				<h3 class=errors>${userNameUpdateError}</h3>
			</c:if>
       		<form:errors path="userName" cssClass="errors" />
       		<form:input path="userName" placeholder="username" title="type your username" oninput="this.value=this.value.toLowerCase();" /><br>
       		<c:if test="${oldPasswordUpdateError != null}">
				<h3 class=errors>${oldPasswordUpdateError}</h3>
			</c:if>
       		<form:errors path="oldPassword" cssClass="errors" />
       		<form:input type="password" path="oldPassword" placeholder="old password" title="type your old password" /><br>
       		<form:errors path="password" cssClass="errors" />
       		<form:input type="password" path="password" placeholder="new password" title="type your new password" /><br>
       		<form:errors path="matchingPassword" cssClass="errors" />
       		<form:input type="password" path="matchingPassword" placeholder="confirm password" title="type your password again" /><br>
       		<form:errors path="firstName" cssClass="errors" />
       		<form:input path="firstName" placeholder="first name" title="type your first name" /><br>
       		<form:errors path="lastName" cssClass="errors" />
       		<form:input path="lastName" placeholder="last name" title="type your last name" /><br>
       		<form:errors path="email" cssClass="errors" />
       		<form:input path="email" placeholder="email" title="type your email" oninput="this.value=this.value.toLowerCase();" /><br>
       		<form:select path="roles" items="${roleOptions}" multiple="true" />
       		<input type="submit" value="Update">
       		<div class="link">
				<a href="${pageContext.request.contextPath}/register/registrationFormUpdate">Update Another Existing User</a>
			</div>
			<div class="link">
				<a href="${pageContext.request.contextPath}/security/login">Go back to login page</a>
			</div>
       	</form:form>
       	<form:form id="uploadPhoto" action="${pageContext.request.contextPath}/register/registrationFormUpdatePhotoUpload" enctype="multipart/form-data">
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
					var inputList = document.getElementsByClassName('inputId');
					if(inputList[0].value===""||inputList[1].value===""){
						inputList[0].value=0;
						inputList[1].value=0;
					}
				}
			}
		</script>
	</body>       	
</html>
