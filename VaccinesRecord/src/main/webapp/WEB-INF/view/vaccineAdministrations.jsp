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
			<p>List of all Vaccine Administrations:</p>
			<c:forEach items="${vaccineAdministrationsList}" var="vaccineAdministration" varStatus="status">
				<p>
					<a href="${pageContext.request.contextPath}/mis/profileByPatientId?patientId=${vaccineAdministration.patientId}">
						<img class="img6" src="${pageContext.request.contextPath}/img/record.png" alt="patientProfile" title="patientProfile" />
					</a>
					${status.count}: ${vaccineAdministration}
				</p>
			</c:forEach>
			<c:if test="${empty vaccineAdministrationsList}">
				<p>There are no vaccines administered yet.</p>
			</c:if>
		</div>
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
