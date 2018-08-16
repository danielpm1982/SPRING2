<%@page isELIgnored="false" contentType="text/html" pageEncoding="UTF-8" %>
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
				<p>Welcome! User authenticated and authorized!</p>
				<p>Username: ${sessionScope.username}</p>
				<p>Role(s): ${sessionScope.roles}</p>
			</c:if>
		</div>
		<div id="roleConditionedLinks">
			<security:authorize access="hasAnyRole('ADMIN','MANAGER')">
				<h2><a href="${pageContext.request.contextPath}/controller/administration" title="only admin and manager roles can see this link">Administration-only Page Link</a></h2>
			</security:authorize>
			<security:authorize access="hasRole('DIRECTOR')">
				<h2><a href="${pageContext.request.contextPath}/controller/directory" title="only director role can see this link">Directory-only Page Link</a></h2>
			</security:authorize>
			<security:authorize access="hasRole('CEO')">
				<h2><a href="${pageContext.request.contextPath}/controller/ceo" title="only CEO role can see this link">CEO-only Page Link</a></h2>
			</security:authorize>
		</div>
       	<form:form action="formResult" method="get" modelAttribute="entityModelClass">
       		<form:errors path="entityDescription" cssClass="errors" />
       		<br>
       		<form:label path="entityDescription">Fill Out Description:</form:label>
       		<br>
       		<form:input id="entityDescription" path="entityDescription" title="Fill Out Description" placeholder="entity description..." autofocus="true" />
       		<br>
       		<input type="submit" value="Submit">
       	</form:form>
       	<form:form action="${pageContext.request.contextPath}/logout" class="logout" method="post">
   			<input type="submit" class="logout" value="Logout">
   		</form:form>
    </body>
</html>

<!-- 
For using Spring Security Taglibs, first set the dependency at the POM (spring-security-taglibs), then
import to the jsp page (<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>), then
use, for instance, <security:authentication property="principal.username" /> to get the username property of
the principal bean. You can similarly get the principal.authorities property (user roles), as shown above.
The tag <security:authentication property="authenticated" /> returns a boolean indicating if the user is really
authenticated, which can be exported to a session scope attribute for later programatic selection - only try to 
get the principle properties if the user is really authenticated (if not, tell him so), for example. 
-->

<!-- 
For authorizing only specific roles to see part of the page, the security taglib is used again, this time with
the tag 'security:authorize' followed by the access attribute with the authorized roles as values. This authorization
is completely independent from the authorization at the config MyWebSecurityConfigurerAdapter class, which defines
the authorized roles to the request mapping paths at the SecurityController class, and, from there, to the respective
jsp pages. This authorization here merely defines the authorization for viewing part of this page, which is, therefore,
protected. Although it could be possible, it wouldn't make much sense for a user to see a link whose path he would be 
forbidden to access.
-->
