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
			<p><a href="getLocalRESTWebServiceStringResult" class="a1">REST Web Service Local Service String</a></p>
			<p><a href="getLocalRESTWebServiceStringAllListResult" class="a1">REST Web Service Local Service String All List</a></p>
			<p><a href="getLocalRESTWebServiceStringPartListResult/0" class="a1">REST Web Service Local Service String Part List {index=0}</a></p>
			<p><a href="getLocalRESTWebServiceStringPartListResult/1" class="a1">REST Web Service Local Service String Part List {index=1}</a></p>
			<p><a href="getLocalRESTWebServiceStringPartListResult/2" class="a1">REST Web Service Local Service String Part List {index=2}</a></p>
			<p><a href="getLocalRESTWebServiceStringPartListResult/3" class="a1">REST Web Service Local Service String Part List {index=3}</a></p>
			<p><a href="getLocalRESTWebServiceStringPartListResult/4" class="a1">REST Web Service Local Service String Part List {index=4}</a></p>
			<p><a href="getLocalRESTWebServiceStringPartListResult/" class="a1">REST Web Service Local Service String Part List With No Index</a></p>
		</div>
    </body>
</html>
