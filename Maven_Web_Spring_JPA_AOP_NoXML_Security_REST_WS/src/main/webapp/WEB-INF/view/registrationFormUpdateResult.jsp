<%@page isELIgnored="false" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Registration Update Custom Result</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/css.css" />
    </head>
    <body>
    	<div id="resultBody">
	    	<h1><c:out value="Registration Update Successfull!" /></h1>
	    	<img class="img2" src="${pageContext.request.contextPath}/resources/img/db.png" alt="database">
       	</div>
       	<div id="resultBody">
       		<h2>Before update:</h2>
       		<h3>${requestScope.oldUser}</h3>
       	</div>
       	<div id="resultBody">
       		<h2>After update:</h2>
       		<h3>${requestScope.newUser}</h3>
       	</div>
       	<div class="link">
			<a href="${pageContext.request.contextPath}/">Go back to login page</a>
		</div>
    </body>
</html>
