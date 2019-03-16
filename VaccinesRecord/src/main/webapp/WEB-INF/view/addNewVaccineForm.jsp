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
				<p class="misP">User NOT logged in! User NOT authenticated!</p>
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
			<form:form action="addNewVaccineResult" modelAttribute="vaccineModel">
				<p class="misP">Add new Vaccine:</p>
				<form:errors path="name" cssClass="errors" />
				<form:input type="text" path="name" placeholder="Vaccine Name" /><br>
				<form:errors path="tradeName" cssClass="errors" />
				<form:input type="text" path="tradeName" placeholder="Vaccine Trade Name" /><br>
				<form:errors path="abbreviation" cssClass="errors" />
				<form:input type="text" path="abbreviation" placeholder="Vaccine Name Abbreviation" /><br>
				<form:errors path="manufacturer" cssClass="errors" />
				<form:input type="text" path="manufacturer" placeholder="Vaccine Manufacturer" /><br>
				<form:errors path="typeRoute" cssClass="errors" />
				<form:input type="text" path="typeRoute" placeholder="Vaccine Route Type" /><br>
				<form:errors path="lotNumber" cssClass="errors" />
				<form:input type="text" path="lotNumber" placeholder="Vaccine Lot Number" /><br>
				<form:errors path="comments" cssClass="errors" />
				<form:input type="text" path="comments" placeholder="Vaccine Comments" /><br>
				<p class="misP">Vaccine Approval Date:</p>
				<form:errors path="approvedDate" cssClass="errors" />
				<form:input type="date" path="approvedDate" placeholder="Vaccine Approved Date" /><br>
				<p class="misP">Vaccine Expiration Date:</p>
				<form:errors path="expirationDate" cssClass="errors" />
				<form:input type="date" path="expirationDate" placeholder="Vaccine Expiration Date" /><br><br>
				<input type="submit" title="Add new Vaccine" value="Add" />
			</form:form>
		</div>
		<form:form id="uploadPhotoVaccine" action="addNewVaccinePhotoUpload" enctype="multipart/form-data">
       		<hr>
       		<p class="misP">Select a photo for an existing Vaccine profile.</p>
       		<c:if test="${uploadConfirmation}">
				<h3 class=errors id="uploadConfirmationMessage1">${uploadConfirmationMessage}</h3><br>
				<img src="${pageContext.request.contextPath}/temp/${tempPhotoFileName}" class="img10" id="img"/><br>
			</c:if>
			<c:if test="${not uploadConfirmation}">
				<h3 class=errors id="uploadConfirmationMessage2">${uploadConfirmationMessage}</h3><br>
			</c:if>
       		<input type="file" name="photo" title="upload photo" onchange="clearPhotoAndUploadConfirmationMessage()" /><br>
       		<h6 class="registrationForm"><c:out value="(photo size must be > 10KiB (10240 bytes) and < 10MiB (10485760 bytes)" /></h6>
       		<input type="number" name="id" id="inputId" placeholder="vaccineId" title="type the vaccine Id" onfocus="if(this.value==0){this.value='';}" onblur="if(this.value==''){this.value=0;}" onkeypress="enterFunction(event);" required/><br>
       		<input type="submit" value="Upload">
       		<hr>
  		</form:form>
  		<br><br>
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
