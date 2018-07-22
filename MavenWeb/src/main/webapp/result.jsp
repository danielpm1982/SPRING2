<%@page isELIgnored="false" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>MavenWeb</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            body{
                font-family: sans-serif;
                text-align: center;
                background-color: black;
                color: blanchedalmond;
            }
            div#result{
                font-size: 5em;
                width: 8em;
                margin: auto;
                margin-top: 2em;
            }
        </style>
    </head>
    <body>
        <div id="result">
        	<c:out value="${result}"></c:out>
       	</div>
    </body>
</html>
