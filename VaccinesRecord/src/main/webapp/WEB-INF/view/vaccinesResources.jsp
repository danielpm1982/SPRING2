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
		<c:if test="${sessionScope.authenticated}">
			<p class="resource">Local Resources for Health Care Professionals</p>
			<div class="resourceFormItem">
				<h2 class="resourceLinkItem"><a href="${pageContext.request.contextPath}/pdf/0-18yrs-child-combined-schedule.pdf" title="CDC child schedule for Professionals">0-18yrs-child-combined-schedule</a></h2>
			</div>
			<div class="resourceFormItem">
				<h2 class="resourceLinkItem"><a href="${pageContext.request.contextPath}/pdf/adult-combined-schedule.pdf" title="CDC adult schedule for Professionals">adult-combined-schedule</a></h2>
			</div>
			<div class="resourceFormItem">
				<h2 class="resourceLinkItem"><a href="${pageContext.request.contextPath}/pdf/general-recs.pdf" title="ACIP/CDC general best practices for Professionals">general-recs</a></h2>
			</div>
			<p class="resource">Local Resources for Parents</p>
			<div class="resourceFormItem">
				<h2 class="resourceLinkItem"><a href="${pageContext.request.contextPath}/pdf/parent-ver-sch-0-6yrs.pdf" title="CDC younger child schedule for Parents">parent-ver-sch-0-6yrs</a></h2>
			</div>
			<div class="resourceFormItem">
				<h2 class="resourceLinkItem"><a href="${pageContext.request.contextPath}/pdf/parent-version-schedule-7-18yrs.pdf" title="CDC older child schedule for Parents">parent-version-schedule-7-18yrs</a></h2>
			</div>
			<div class="resourceFormItem">
				<h2 class="resourceLinkItem"><a href="${pageContext.request.contextPath}/pdf/journey-of-child-vaccine-h.pdf" title="CDC journey of child schedule for Parents">journey-of-child-vaccine-h</a></h2>
			</div>
			<div class="resourceFormItem">
				<h2 class="resourceLinkItem"><a href="${pageContext.request.contextPath}/pdf/CDC-Growing-Up-with-Vaccines.pdf" title="CDC growing up with Vaccines for Parents">CDC-Growing-Up-with-Vaccines</a></h2>
			</div>
			<p class="resource">Remote Resources for Health Care Professionals and Parents</p>
			<div class="resourceFormItem">
				<h2 class="resourceLinkItem"><a href="https://www.cdc.gov/vaccines/index.html" title="CDC Vaccines">https://www.cdc.gov/vaccines/index.html</a></h2>
			</div>
			<div class="resourceFormItem">
				<h2 class="resourceLinkItem"><a href="https://www.cdc.gov/vaccines/hcp/acip-recs/index.html" title="ACIP/CDC Vaccines Recomendations">https://www.cdc.gov/vaccines/hcp/acip-recs/index.html</a></h2>
			</div>
			<div class="resourceFormItem">
				<h2 class="resourceLinkItem"><a href="https://www.cdc.gov/vaccines/parents/index.html" title="CDC Vaccines for Parents">https://www.cdc.gov/vaccines/parents/index.html</a></h2>
			</div>
			<div class="resourceFormItem">
				<h2 class="resourceLinkItem"><a href="https://www.cdc.gov/vaccines/vac-gen/why.htm" title="CDC Vaccines Why">https://www.cdc.gov/vaccines/vac-gen/why.htm</a></h2>
			</div>
			<div class="resourceFormItem">
				<h2 class="resourceLinkItem"><a href="https://www.cdc.gov/vaccinesafety/vaccines/index.html" title="CDC Vaccines Safety">https://www.cdc.gov/vaccinesafety/vaccines/index.html</a></h2>
			</div>
		</c:if>
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
    </body>
</html>
