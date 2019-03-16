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
        <link rel="stylesheet" type="text/css" media="screen and (min-width:1000px)" href="${pageContext.request.contextPath}/css/css4.css" />
        <link rel="stylesheet" type="text/css" media="screen and (min-width:360px) and (max-width:999px)" href="${pageContext.request.contextPath}/css/css3.css" />
        <link rel="stylesheet" type="text/css" media="print" href="${pageContext.request.contextPath}/css/css5.css" />
    </head>
    <body>
		<div class="welcome">
			<div id="img">
				<img class="img" src="${pageContext.request.contextPath}/img/healthRecord.png" alt="Vaccines Record API" title="Vaccines Record API" />
				<h1>Vaccines Record API</h1>
				<h3>Developed by: danielpm1982.com</h3>
				<h3>danielpm1982.com@domainsbyproxy.com</h3>
			</div>
			<h2>Profile of Patient (ID = ${patient.patientId}):</h2>
			<c:if test="${tempPhotoFileName != null}">
				<img src="${pageContext.request.contextPath}/temp/${tempPhotoFileName}" class="img9" id="img"/><br>
			</c:if>
			<p>Patient Id: ${patient.patientId}</p>
			<c:if test="${user != null}">
				<p>Patient Username: ${user.userName}</p>	
			</c:if>
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
					<img src="${pageContext.request.contextPath}/temp/${tempPhotoFileNameMap[vaccine.vaccineId]}" class="img10" id="img"/><br>
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
		<br><br><br>
		<div class="printButtonDiv">
			<input type="button" value="Print" onclick="window.print()"><br>
			<input type="button" value="Back TPS" onclick="location.href='${pageContext.request.contextPath}/tps'"><br>
			<input type="button" value="Home" onclick="location.href='${pageContext.request.contextPath}/'"><br>
		</div>
    </body>
</html>
