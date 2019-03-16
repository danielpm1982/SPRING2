<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Login</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style type="text/css" media="screen and (min-width:360px) and (max-width:999px)">
        	body{
				font-family: sans-serif;
				text-align: center;
				background-color: black;
				color: blanchedalmond;
			}
			form.login {
				font-size: 1em;
				border: thin white dashed;
				width: 85%;
				margin: auto;
				margin-top: 1em;
				margin-bottom: 1em;
				padding: 1em;
				padding-top: 0.5em;
			}
			input[type="text"], input[type="password"]{
				font-family: monospace;
				font-size: 1.1em;
				padding: 0.4em;
				width: 15em;
				margin-top: 1em;
				margin-bottom: 1em;
			}
			input[type="submit"], input[type="button"]{
				font-family: monospace;
				font-size: 1.1em;
				font-weight: bolder;
				padding: 0.4em;
				width: 7em;
				margin-top: 2em;
				margin-bottom: 1em;
				margin-left: 1em;
				margin-right: 1em;
			}
			input[type="button"]{
				margin-top: 0em;
			}
			h3.errors{
				font-family: monospace;
				color: red;
				background-color: yellow;
				margin-bottom: 1.5em;
			}
			h3.logout{
				font-family: monospace;
				color: black;
				background-color: lightGreen;
				margin-bottom: 1.5em;
			}
			div#title{
				margin-bottom: 1.5em;
			}
			img{
				width: 13%;
				position: relative;
				top: 0.5em;
				right: 1em;
				margin-left: 1em;
			}
			img.img2{
				width: 15%;
				position: relative;
				top: -3.5em;
				right: -28%;
			}
			div.imgInput{
				width: 70%;
				margin: auto;
			}
			hr{
				margin-top: -2.5em;
			}
			div.link{
				margin-top: 1em;
			}
			a:link{
				color: green;
			}
			a:visited{
				color: teal;
			}
			a:hover{
				color: lime;
			}
			a:active{
				color: red;
			}
        </style>
        <style type="text/css" media="screen and (min-width:1000px)">
        	body{
				font-family: sans-serif;
				text-align: center;
				background-color: black;
				color: blanchedalmond;
			}
			form.login {
				font-size: 2em;
				border: thin white dashed;
				width: 50%;
				margin: auto;
				margin-top: 2em;
				margin-bottom: 1em;
				padding: 1em;
				padding-top: 0.5em;
			}
			input[type="text"], input[type="password"]{
				font-family: monospace;
				font-size: 1.1em;
				padding: 0.4em;
				width: 15em;
				margin-top: 1em;
				margin-bottom: 1em;
			}
			input[type="submit"], input[type="button"]{
				font-family: monospace;
				font-size: 1.1em;
				font-weight: bolder;
				padding: 0.4em;
				width: 7em;
				margin-top: 2em;
				margin-bottom: 1em;
				margin-left: 1em;
				margin-right: 1em;
			}
			input[type="button"]{
				margin-top: 0em;
			}
			h3.errors{
				font-family: monospace;
				color: red;
				background-color: yellow;
				margin-bottom: 1.5em;
			}
			h3.logout{
				font-family: monospace;
				color: black;
				background-color: lightGreen;
				margin-bottom: 1.5em;
			}
			div#title{
				margin-bottom: 1.5em;
			}
			img{
				width: 13%;
				position: relative;
				top: 0.5em;
				right: 1em;
				margin-left: 1em;
			}
			img.img2{
				width: 15%;
				position: relative;
				top: -3.5em;
				right: -28%;
			}
			div.imgInput{
				width: 70%;
				margin: auto;
			}
			hr{
				margin-top: -2.5em;
			}
			div.link{
				margin-top: 1em;
			}
			a:link{
				color: green;
			}
			a:visited{
				color: teal;
			}
			a:hover{
				color: lime;
			}
			a:active{
				color: red;
			}
        </style>
	</head>
	<body>
		<form:form class="login" action="${pageContext.request.contextPath}/security/loginResult">
       		<div id=title>
	       		<h2>Login Page</h2>
				<img class="img2" src="${pageContext.request.contextPath}/img/login.png" alt="login" title="login" />
				<hr>
			</div>
       		<c:if test="${param.error ne null}">
       			<h3 class=errors>Authentication failed! Invalid username/password!</h3>
       		</c:if>
       		<c:if test="${param.logout ne null}">
       			<h3 class=logout>You have logged out sucessfully!</h3>
       		</c:if>
       		<label for="userName">Username:</label>
       		<div class="imgInput">
	       		<img src="${pageContext.request.contextPath}/img/user.png" alt="user" title="user" />
	       		<input type="text" name="username" id="userName" title="type your userName" />
       		</div>
       		<label for="userPassword">Password:</label>
       		<div class="imgInput">
	       		<img src="${pageContext.request.contextPath}/img/password.png" alt="password" title="password" />
	       		<input type="password" name="password" id="userPassword" title="type your userPassword" />
       		</div>
       		<input type="submit" value="Login"><br>
       		<input type="button" onclick="window.location.href='${pageContext.request.contextPath}/'" value="Home" />
       		<div class="link">
				<a href="${pageContext.request.contextPath}/register/registrationForm">Add New User</a>
			</div>
			<div class="link">
				<a href="${pageContext.request.contextPath}/register/registrationFormUpdate">Search/Update Existing User</a>
			</div>
			<div class="link">
				<a href="${pageContext.request.contextPath}/register/registrationFormDelete">Delete Existing User</a>
			</div>
			<div class="link">
				<a href="${pageContext.request.contextPath}/">Home</a>
			</div>
       	</form:form>
	</body>
</html>
