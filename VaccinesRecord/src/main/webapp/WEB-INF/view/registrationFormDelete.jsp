<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Registration Delete</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" type="text/css" media="screen and (min-width:360px) and (max-width:999px)" href="${pageContext.request.contextPath}/css/css2.css" />
		<link rel="stylesheet" type="text/css" media="screen and (min-width:1000px)" href="${pageContext.request.contextPath}/css/css.css" />
	</head>
	<body>
		<c:if test="${userModelAttribute.userName == null}">
			<form:form id="register" action="${pageContext.request.contextPath}/register/registrationFormQueryResult2" modelAttribute="userModelAttribute">
				<p>Search for an existing User profile.</p>
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
		<form:form id="register" action="${pageContext.request.contextPath}/register/registrationFormDeleteResult" modelAttribute="userModelAttribute">
       		<p>Fill out the data of the existing User profile to be deleted.</p>
       		<p>(or use the search at the form above)</p>
       		<p>Confirm with your password to proceed.</p>
       		<c:if test="${registrationDeleteError != null}">
				<h3 class=errors>${registrationDeleteError}</h3>
			</c:if>
       		<c:if test="${tempPhotoFileName != null}">
				<img id="userPhoto" src="${pageContext.request.contextPath}/temp/${tempPhotoFileName}" class="img9" id="img"/><br>
			</c:if>
       		<form:errors path="id" cssClass="errors" />
       		<form:input path="id" type="number" class="inputId" placeholder="userId (11-digit-max)" title="type an unique 11-digit-max id (SSN, CPF, etc)" onfocus="if(this.value==0){this.value='';}" onblur="if(this.value==''){this.value=0;}" onkeypress="enterFunction(event);" /><br>
       		<form:errors path="userName" cssClass="errors" />
       		<form:input path="userName" placeholder="username" title="your username" readonly="true" /><br>
       		<c:if test="${passwordDeleteError != null}">
				<h3 class=errors>${passwordDeleteError}</h3>
			</c:if>
       		<form:errors path="password" cssClass="errors" />
       		<form:input type="password" path="password" placeholder="password" title="type your password" /><br>
       		<form:errors path="firstName" cssClass="errors"/>
       		<form:input path="firstName" placeholder="first name" title="your first name" readonly="true" /><br>
       		<form:errors path="lastName" cssClass="errors" />
       		<form:input path="lastName" placeholder="last name" title="your last name" readonly="true" /><br>
       		<form:errors path="email" cssClass="errors" />
       		<form:input path="email" placeholder="email" title="your email" readonly="true" /><br>
       		<input type="submit" value="Delete">
       		<div class="link">
				<a href="${pageContext.request.contextPath}/register/registrationFormDelete">Delete Another Existing User</a>
			</div>
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
