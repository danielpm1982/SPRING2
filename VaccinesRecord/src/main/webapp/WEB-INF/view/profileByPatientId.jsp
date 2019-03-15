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
       	<security:authentication property="authenticated" var="authenticated" scope="session" />
		<security:authentication property="principal.username" var="username" scope="session" />
		<security:authentication property="principal.authorities" var="roles" scope="session" />
       	<div class="welcome">
			<c:if test="${not sessionScope.authenticated}">
				<p>User NOT logged in! User NOT authenticated!</p>
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
		<div class="welcome">
			<h2>Profile of Patient (ID = ${patient.patientId}):</h2>
			<c:if test="${tempPhotoFileName != null}">
				<img id="userPhoto" src="${pageContext.request.contextPath}/temp/${tempPhotoFileName}" class="img9" id="img"/><br>
			</c:if>
			<p>Patient Id: ${patient.patientId}</p>
			<p>Name: ${patient.name}</p>
			<p>Address:</p>
			<c:forEach items="${patient.address}" var="address" varStatus="status">
				<p>${status.count}: ${address.number}, ${address.street} ${address.postalCode} ${address.city} ${address.state} ${address.country}</p>	
			</c:forEach>
			<p>Email: ${patient.email}</p>
			<p>Phone Number:</p>
			<c:forEach items="${patient.phoneNo}" var="phone" varStatus="status">
				<p>${status.count}: ${phone}</p>	
			</c:forEach>
			<p>Birth Date: ${patientBirthDate}</p>
			<p>Age: ${patient.age}</p>
			<hr />
			<c:forEach items="${vaccineList}" var="vaccine" varStatus="status">
				<h2>Vaccine: ${vaccine.name} (lot ${vaccine.lotNumber})</h2>
				<c:if test="${tempPhotoFileNameMap[vaccine.vaccineId] != null}">
					<img id="userPhoto" src="${pageContext.request.contextPath}/temp/${tempPhotoFileNameMap[vaccine.vaccineId]}" class="img10" id="img"/><br>
				</c:if>
				<h4>Description:</h4>
				<p>${vaccine.vaccineStringWithoutClientsList}"</p>
				<h4>Vaccine Administrations:</h4>
				<c:forEach items="${vaccineAdministrationMap[vaccine.vaccineId]}" var="vaccineAdministration" varStatus="status">
					<p>${vaccineAdministration}"</p>
				</c:forEach>
				<hr>
			</c:forEach>
		</div>
		<form:form action="${pageContext.request.contextPath}/print/profileByPatientId" class="return">
   			<input type="hidden" name="patientId" value="${patient.patientId}" />
   			<input type="submit" class="return" value="Print">
   		</form:form>
		<form:form action="${pageContext.request.contextPath}/tps" class="return" method="get">
   			<input type="submit" class="return" value="Back TPS">
   		</form:form>
		<c:set var="misUser" scope="session" value="false"/>
   		<c:forEach items="${sessionScope.roles}" var="role" varStatus="status">
   			<c:if test="${role eq 'ROLE_MANAGER' or role eq 'ROLE_DIRECTOR'}">
   				<c:set var="misUser" scope="session" value="true"/>
   			</c:if>
   		</c:forEach>
   		<c:if test="${sessionScope.misUser}">
	   		<form:form action="${pageContext.request.contextPath}/mis" class="return" method="get">
	   			<input type="submit" class="return" value="Back MIS">
	   		</form:form>
   		</c:if>
       	<form:form action="${pageContext.request.contextPath}/logout" class="logout">
   			<input type="submit" class="logout" value="Logout">
   		</form:form>
   		<form:form action="${pageContext.request.contextPath}/" class="login" method="get">
   			<input type="submit" class="login" value="Home">
   		</form:form>
    </body>
</html>
