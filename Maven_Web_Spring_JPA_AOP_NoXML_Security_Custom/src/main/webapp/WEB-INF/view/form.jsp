<%@page isELIgnored="false" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Maven_Web_Spring_JPA_AOP</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/css.css" />
    </head>
    <body>
       	<form:form action="formResult" method="get" modelAttribute="entityModelClass">
       		<form:label path="entityDescription">Fill Out Description:</form:label>
       		<br>
       		<form:input id="entityDescription" path="entityDescription" title="Fill Out Description" placeholder="entity description..." autofocus="true" />
       		<br>
       		<form:errors path="entityDescription" cssClass="errors" />
       	</form:form>
    </body>
</html>
