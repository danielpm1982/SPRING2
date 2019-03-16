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
		<div class="misFormDiv">
			<form:form action="addNewVaccineAdministrationResult" modelAttribute="vaccineAdministrationModel">
				<p class="misP">Add new Vaccine Administration:</p>
				<p class="misP">Patient Id</p>
				<c:if test="${inputPatientIdError!=null}">
   					<h3 class="errors">${inputPatientIdError}</h3>
   				</c:if>
				<form:errors path="patientId" cssClass="errors" />
				<form:input type="number" id="patientId" class="inputId" path="patientId" placeholder="Patient Id" onfocus="this.value=''" onblur="if(this.value===''){this.value=0;}" onkeypress="enterFunction(event)" /><br>
				<p class="misP">Vaccine Id</p>
				<c:if test="${inputVaccineIdError!=null}">
   					<h3 class="errors">${inputVaccineIdError}</h3>
   				</c:if>
				<form:errors path="vaccineId" cssClass="errors" />
				<form:input type="number" id="vaccineId" class="inputId" path="vaccineId" placeholder="Vaccine Id" onfocus="this.value=''" onblur="if(this.value===''){this.value=0;}" onkeypress="enterFunction(event)" /><br>
				<form:errors path="administrator" cssClass="errors" />
				<form:input type="text" path="administrator" placeholder="Administrator Name" /><br>
				<form:errors path="place" cssClass="errors" />
				<form:input type="text" path="place" placeholder="Place" /><br>
				<p class="misP">Local Date (optional)</p>
				<form:errors path="localDate" cssClass="errors" />
				<form:input type="date" path="localDate" placeholder="LocalDate" /><br>
				<p class="misP">Local Time (optional)</p>
				<form:errors path="localTime" cssClass="errors" />
				<form:input type="time" step="1" path="localTime" placeholder="LocalTime" /><br><br>
				<input type="submit" title="Add new Vaccine Administration" value="Add" />
			</form:form>
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
   		<script>
			function enterFunction(e){
				if(e.keyCode === 13){
					var input = document.getElementsByClassName('inputId');
					if(input[0].value===""){
						input[0].value=0;
					}
					if(input[1].value===""){
						input[1].value=0;
					}
				}
			}
		</script>
    </body>
</html>
