<%@page isELIgnored="false" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Maven_Web_Spring_REST_WebServices</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/css.css" />
    </head>
    <body>
    	<div id="resultBody">
	    	<p>This is the final Result !!</p>
	    	<div class="table">
        		<div class="row">
        			<div class="tableHeader">Id</div>
        			<div class="tableHeader">UserName</div>
        			<div class="tableHeader">Email</div>
        			<div class="tableHeader">Phone</div>
        			<div class="tableHeader">Website</div>
        		</div>
        		<c:forEach items="${userList}" var="user">
        			<div class="row">
	        			<div class="cell">${user.id}</div>
	        			<div class="cell">${user.username}</div>
	        			<div class="cell">${user.email}</div>
	        			<div class="cell">${user.phone}</div>
	        			<div class="cell">${user.website}</div>
        			</div>	
        		</c:forEach>
        	</div>
	    	<p><a href="showRestWebServiceUserInterface" class="a1">HOME</a></p>
    	</div>
    </body>
</html>
