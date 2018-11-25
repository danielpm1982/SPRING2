<%@page isELIgnored="false" contentType="text/html" pageEncoding="UTF-8" %>
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
       	<div class="welcome">
			<p>Welcome to the REST WebServices User Interface !!</p>
			<p><a href="showRestWebServiceUserInterfaceResult" class="a1">REST Web Service Local File</a></p>
			<p><a href="showRestWebServiceRemoteUserInterfaceResult" class="a1">REST Web Service Remote</a></p>
			<p><a href="getLocalRESTWebServiceResult" class="a1">REST Web Service Local Service</a></p>
		</div>
    </body>
</html>
