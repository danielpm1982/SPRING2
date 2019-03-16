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
		<div class="tpsFormItem">
			<c:if test="${profileError != null}">
				<h3 class=errors>${profileError}</h3><br>
			</c:if>
			<security:authorize access="hasRole('PATIENT')">
				<h2 class=""><a href="${pageContext.request.contextPath}/tps/profileByPatientId" title="View your Patient Profile">View your Patient Profile</a></h2>
			</security:authorize>
		</div>
		<div class="tpsFormItem">
			<security:authorize access="hasAnyRole('MANAGER', 'DIRECTOR')">
				<h2 class="tpsLinkItem"><a href="${pageContext.request.contextPath}/mis" title="Go to MIS">Go to MIS</a></h2>
			</security:authorize>
		</div>
		<div class="tpsFormItem">
			<security:authorize access="hasAnyRole('MANAGER', 'DIRECTOR')">
				<h2 class="tpsLinkItem"><a href="${pageContext.request.contextPath}/ws" title="Go to WebServices API">Go to WebServices API</a></h2>
			</security:authorize>
		</div>
		<div class="tpsFormItem">
			<security:authorize access="hasRole('USER')">
				<h2 class=""><a href="${pageContext.request.contextPath}/tps/vaccinesResources" title="Vaccine Resources and External Links for Parents and Professionals">Vaccine Resources and External Links for Parents and Professionals</a></h2>
			</security:authorize>
		</div>
		<br>
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
