<%@page isELIgnored="false" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Form</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" media="screen and (min-width:360px) and (max-width:999px)" href="${pageContext.request.contextPath}/css/css2.css" />
		<link rel="stylesheet" type="text/css" media="screen and (min-width:1000px)" href="${pageContext.request.contextPath}/css/css.css" />
    </head>
    <body>
       	<c:if test="${not empty principal}">
	       	<security:authentication property="authenticated" var="authenticated" scope="session" />
			<security:authentication property="principal.username" var="username" scope="session" />
			<security:authentication property="principal.authorities" var="roles" scope="session" />
		</c:if>
       	<div class="welcome">
			<c:if test="${not sessionScope.authenticated}">
				<p>User NOT logged in! User NOT authenticated!</p>
				<img class="img5" src="${pageContext.request.contextPath}/img/accessDenied.png" alt="access denied" title="access denied" />
			</c:if>
			<c:if test="${sessionScope.authenticated}">
				<p>User authenticated and authorized!</p>
				<p>Welcome ${user.firstName} ${user.lastName}!!</p>
				<p>Database Id: ${user.id}</p>
				<p>Username: ${sessionScope.username}</p>
				<p>BCrypted Password: ${user.password}</p>
				<p>Email: ${user.email}</p>
				<p>Role(s): ${sessionScope.roles}</p>
			</c:if>
		</div>
		<div class="wsFormItem">
			<security:authorize access="hasAnyRole('MANAGER', 'DIRECTOR')">
				<h2 class="wsLinkItem"><a href="${pageContext.request.contextPath}/api" title="Go to WebServices API">Go to WebServices API (HAL browser)</a></h2>
			</security:authorize>
		</div>
		<div class="wsFormItem">
			<security:authorize access="hasAnyRole('MANAGER', 'DIRECTOR')">
				<h2 class="wsLinkItem"><a href="${pageContext.request.contextPath}/api/patients" title="GET api/patients">GET api/patients</a></h2>
			</security:authorize>
		</div>
		<div class="wsFormItem">
			<security:authorize access="hasAnyRole('MANAGER', 'DIRECTOR')">
				<h2 class="wsLinkItem"><a href="${pageContext.request.contextPath}/api/vaccines" title="GET api/vaccines">GET api/vaccines</a></h2>
			</security:authorize>
		</div>
		<div class="wsFormItem">
			<security:authorize access="hasAnyRole('MANAGER', 'DIRECTOR')">
				<h2 class="wsLinkItem"><a href="${pageContext.request.contextPath}/api/vaccineAdministrations" title="GET api/vaccineAdministrations">GET api/vaccineAdministrations</a></h2>
			</security:authorize>
		</div>
		<div class="wsFormItem">
			<security:authorize access="hasAnyRole('MANAGER', 'DIRECTOR')">
   				<h2 class="wsInputItem">GET api/vaccineAdministrations/vaccinesByPatientId/<input id="input1" type="number" onkeypress="enterFunction1(event)" placeholder="Patient Id <ENTER>" /></h2>
			</security:authorize>
		</div>
		<div class="wsFormItem">
			<security:authorize access="hasAnyRole('MANAGER', 'DIRECTOR')">
   				<h2 class="wsInputItem">GET api/vaccineAdministrations/patientsByVaccineId/<input id="input2" type="number" onkeypress="enterFunction2(event)" placeholder="Vaccine Id <ENTER>" /></h2>
			</security:authorize>
		</div>
<!-- 		* For other API http methods and respective web services, see the VaccineTPSRESTController.java at the source code. And use Postman or the HAL browser above to send different http methods along with the eventual requestBody and/or pathVariables content. The API is authorized only to Manager or Director users. -->
		<br>
       	<c:if test="${sessionScope.authenticated}">
	       	<form:form action="${pageContext.request.contextPath}/tps" class="return" method="get">
	   			<input type="submit" class="return" value="Back TPS">
	   		</form:form>
   		</c:if>
       	<c:if test="${sessionScope.authenticated}">
	       	<form:form action="${pageContext.request.contextPath}/logout" class="logout">
	   			<input type="submit" class="logout" value="Logout">
	   		</form:form>
   		</c:if>
   		<c:if test="${not sessionScope.authenticated}">
	       	<form:form action="${pageContext.request.contextPath}/security/login" class="login" method="get">
	   			<input type="submit" class="login" value="Login">
	   		</form:form>
   		</c:if>
		<form:form action="${pageContext.request.contextPath}/" class="login" method="get">
   			<input type="submit" class="login" value="Home">
   		</form:form>
   		<script>
			function enterFunction1(e){
				if(e.keyCode === 13){
					var input = document.getElementById('input1').value;
					if(input){
						window.location.href='api/vaccineAdministrations/vaccinesByPatientId/'+input;
					} else{
						alert("Patient Id cannot be empty nor have any non numeric values. Patient not found. Try retyping it using a valid User Id.");
					}
				}
			}
			function enterFunction2(e){
				if(e.keyCode === 13){
					var input = document.getElementById('input2').value;
					if(input){
						window.location.href='api/vaccineAdministrations/patientsByVaccineId/'+input;
					} else{
						alert("Vaccine Id cannot be empty nor have any non numeric values. Vaccine not found. Try retyping it using a valid Vaccine Id.");
					}
				}
			}
		</script>
    </body>
</html>
