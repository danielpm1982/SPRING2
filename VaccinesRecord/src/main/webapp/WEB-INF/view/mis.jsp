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
		<div class="misFormItem">
			<div class="misFormDiv">
				<c:if test="${inputPatientIdError != null}">
					<h3 class=errors>${inputPatientIdError}</h3><br>
				</c:if>
				<form action="${pageContext.request.contextPath}/mis/profileByPatientId" method="get">
					<label for="patientIdField">Search for Patient Profile</label><br>
					<input type="number" id="patientIdField" name="patientId" placeholder="patient Id" required />
					<input type="submit" title="Search for Patient profile by Patient Id" value="Search">
				</form>
			</div>
			<div class="misFormDiv">
				<form action="${pageContext.request.contextPath}/mis/addNewPatient" method="get">
					<label for="addNewPatientSubmit">Add new Patient</label><br>
					<input type="submit" id="addNewPatientSubmit" title="Add new Patient" value="Add Form">
				</form>
			</div>
		</div>
		<div class="misFormItem">
			<div class="misFormDiv">
				<c:if test="${inputVaccineIdError != null}">
					<h3 class=errors>${inputVaccineIdError}</h3><br>
				</c:if>
				<form action="${pageContext.request.contextPath}/mis/profileByVaccineId" method="get">
					<label for="vaccineIdField">Search for Vaccine Profile</label><br>
					<input type="number" id="vaccineIdField" name="vaccineId" placeholder="vaccine Id" required />
					<input type="submit" title="Search for Vaccine profile by Vaccine Id" value="Search">
				</form>
			</div>
			<div class="misFormDiv">
				<form action="${pageContext.request.contextPath}/mis/addNewVaccine" method="get">
					<label for="addNewVaccineSubmit">Add new Vaccine</label><br>
					<input type="submit" id="addNewVaccineSubmit" title="Add new Vaccine" value="Add Form">
				</form>
			</div>
		</div>
		<div class="misFormItem">
			<div class="misFormDiv">
				<form action="${pageContext.request.contextPath}/mis/vaccineAdministrations" method="get">
					<label for="vaccineAdministrations">List All Vaccine Administrations</label><br>
					<input type="submit" id="vaccineAdministrations" title="List All Vaccine Administrations" value="List">
				</form>
			</div>
			<div class="misFormDiv">
				<form action="${pageContext.request.contextPath}/mis/addNewVaccineAdministration" method="get">
					<label for="vaccineAdministration">Add new Vaccine Administration</label><br>
					<input type="submit" id="vaccineAdministration" title="Add new Vaccine Administration" value="Admin">
				</form>
			</div>
		</div>
		<form:form action="${pageContext.request.contextPath}/tps" class="return" method="get">
   			<input type="submit" class="return" value="Back TPS">
   		</form:form>
       	<form:form action="${pageContext.request.contextPath}/logout" class="logout">
   			<input type="submit" class="logout" value="Logout">
   		</form:form>
		<form:form action="${pageContext.request.contextPath}/" class="login" method="get">
   			<input type="submit" class="login" value="Home">
   		</form:form>
    </body>
</html>
