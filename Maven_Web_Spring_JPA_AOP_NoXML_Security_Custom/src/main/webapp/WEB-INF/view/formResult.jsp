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
    	<div id="resultBody">
	    	<h1><c:out value="Insert Successfull:" /></h1>
	        <div id="result1">
	        	<c:if test="${inserted}">
	        		<c:out value="ID = ${entity.id}" /><br>
	        		<c:out value="ENTITY_DESCRIPTION = ${entity.entityDescription}" /><br>
	        		<c:out value="DATE_TIME = ${entity.dateTimeStringified}" />
	       		</c:if>
	       	</div>
	       	<br>
	       	<h1><c:out value="All items:" /></h1>
	       	<div id="result2">
	        	<c:forEach items="${entityList}" var="item" varStatus="varStatus">
	        		<c:out value="${varStatus.count}. ${item}" /><br>
	        	</c:forEach>
	       	</div>
       	</div>
       	<form:form action="${pageContext.request.contextPath}/logout" class="logout" method="post">
   			<input type="submit" class="logout" value="Logout">
   		</form:form>
    </body>
</html>
