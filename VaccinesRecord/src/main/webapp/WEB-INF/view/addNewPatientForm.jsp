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
			<form:form action="addNewPatientResult" modelAttribute="patientModel">
				<p class="misP">Add new Patient:</p>
				<c:if test="${not empty inputPatientIdError}">
   					<p class="errors">${inputPatientIdError}</p>
   				</c:if>
				<form:errors path="patientId" cssClass="errors" />
				<form:input path="patientId" type="number" min="1" class="inputId" placeholder="PatientId (11-digit-max)" title="type an unique 11-digit-max id (SSN, CPF, etc)" onfocus="this.value=''" onblur="if(this.value===''){this.value=0;}" onkeypress="enterFunction(event)" /><br>
				<form:errors path="name" cssClass="errors" />
				<form:input type="text" path="name" placeholder="Patient Name" /><br>
				<form:errors path="email" cssClass="errors" />
				<form:input type="text" path="email" placeholder="Patient Email" /><br>
				<form:errors path="birthDate" cssClass="errors" />
				<form:input type="date" path="birthDate" required="true" /><br>
				<form:errors path="phoneNo" cssClass="errors" />
				<c:if test="${not empty phoneError}">
   					<p class="errors">${phoneError}</p>
   				</c:if>
				<form:input type="text" path="phoneNo[0]" placeholder="Phone 1 (required)" /><br>
				<form:input type="text" path="phoneNo[1]" placeholder="Phone 2 (optional)" /><br>
				<form:input type="text" path="phoneNo[2]" placeholder="Phone 3 (optional)" /><br>
				<p class="misP">Address 1 (required):</p>
				<c:if test="${not empty addressError}">
   					<p class="errors">${addressError}</p>
   				</c:if>
				<form:input type="text" path="address[0].street" placeholder="Street" /><br>
				<form:input type="number" path="address[0].number" min="0" class="inputId" placeholder="Number" onfocus="this.value=''" onblur="if(this.value===''){this.value=0;}" onkeypress="enterFunction(event)" /><br>
				<form:input type="text" path="address[0].postalCode" placeholder="Postal Code" /><br>
				<form:input type="text" path="address[0].city" placeholder="City" /><br>
				<form:input type="text" path="address[0].state" placeholder="State" /><br>
				<form:input type="text" path="address[0].country" placeholder="Country" /><br>
				<p class="misP">Address 2 (optional):</p>
				<form:input type="text" path="address[1].street" placeholder="Street" /><br>
				<form:input type="number" path="address[0].number" min="0" class="inputId" placeholder="Number" onfocus="this.value=''" onblur="if(this.value===''){this.value=0;}" onkeypress="enterFunction(event)" /><br>
				<form:input type="text" path="address[1].postalCode" placeholder="Postal Code" /><br>
				<form:input type="text" path="address[1].city" placeholder="City" /><br>
				<form:input type="text" path="address[1].state" placeholder="State" /><br>
				<form:input type="text" path="address[1].country" placeholder="Country" /><br><br>
				<input type="submit" title="Add new Patient" value="Add"/>
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
					if(input[1].value===""){
						input[2].value=0;
					}
				}
			}
		</script>
    </body>
</html>
