<%@page isELIgnored="false" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Maven_Web_Spring_JPA_AOP</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/css.css" />
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
				<p>Welcome! User logged in, authenticated and authorized!</p>
				<p>Username: ${sessionScope.username}</p>
				<p>Role(s): ${sessionScope.roles}</p>
			</c:if>
		</div>
		<div>
			<h1>Directory Page</h1>
			<h3>This content is available only to authorized personnel - i.e., director role.</h3>
			<h3>Users that do not possess this role, can't access the mapping path to this page.</h3>
			<img src="${pageContext.request.contextPath}/resources/img/directory.png" alt="directory">
		</div>
       	<form:form action="${pageContext.request.contextPath}/logout" class="logout" method="post">
   			<input type="submit" class="logout" value="Logout">
   		</form:form>
    </body>
</html>

<!-- 
Considering the configurations at the MyWebSecurityConfigurerAdapter for authorization of the request
mapping paths at MySecurityController, authenticated users that do not have the roles (credentials) 
necessary to access this resource are redirected to a Forbidden error page. Only authenticated AND
authorized users can access this page.  
-->
