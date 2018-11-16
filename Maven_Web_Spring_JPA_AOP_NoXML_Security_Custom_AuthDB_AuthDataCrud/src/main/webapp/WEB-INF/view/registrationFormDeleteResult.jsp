<%@page isELIgnored="false" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Registration Delete Custom Result</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/css.css" />
    </head>
    <body>
    	<div id="resultBody">
	    	<h1><c:out value="Registration Delete Successfull!" /></h1>
	    	<img class="img2" src="${pageContext.request.contextPath}/resources/img/db.png" alt="database">
       	</div>
       	<div id="resultBody">
       		<h2>User deleted:</h2>
       		<h3>${requestScope.deletingUser}</h3>
       	</div>
       	<div class="link">
			<a href="${pageContext.request.contextPath}/">Go back to login page</a>
		</div>
    </body>
</html>
