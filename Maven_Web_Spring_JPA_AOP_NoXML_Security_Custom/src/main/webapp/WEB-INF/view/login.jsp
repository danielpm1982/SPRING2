<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Login Custom Page</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style type="text/css">
        	body{
				font-family: sans-serif;
				text-align: center;
				background-color: black;
				color: blanchedalmond;
			}
			form {
				font-size: 2em;
				border: thin white dashed;
				width: 40%;
				margin: auto;
				margin-top: 4em;
				padding: 1em;
			}
			input[type="text"], input[type="password"]{
				font-family: monospace;
				font-size: 1.1em;
				padding: 0.4em;
				width: 15em;
				margin-top: 1em;
				margin-bottom: 1em;
			}
			input[type="submit"]{
				font-family: monospace;
				font-size: 1.1em;
				font-weight: bolder;
				padding: 0.4em;
				width: 7em;
				margin-top: 2em;
				margin-bottom: 1em;
			}
			.errors{
				font-family: monospace;
				color: red;
				background-color: yellow;
				margin-bottom: 1.5em;
			}
        </style>
	</head>
	<body>
		<form:form action="${pageContext.request.contextPath}/customLoginPageResult" method="post">
       		<c:if test="${param.error ne null}">
       			<h3 class=errors>Authentication failed! Invalid username/password passed! Try again!</h3>
       		</c:if>
       		<label for="userName">User Name:</label>
       		<br>
       		<input type="text" name="username" id="userName" title="type your userName" />
       		<br>
       		<label for="userPassword">User Password:</label>
       		<br>
       		<input type="password" name="password" id="userPassword" title="type your userPassword" />
       		<br>
       		<input type="submit" value="Login">
       	</form:form>
	</body>
</html>

<!-- 
Resources are not authorized unless user is authenticated, so the css of the login page must be 
set in the style and not link type fashion.  
-->
