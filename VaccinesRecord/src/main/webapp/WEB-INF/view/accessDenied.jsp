<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Access Denied!</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style type="text/css" media="screen and (min-width:360px) and (max-width:999px)">
        	body{
				font-family: sans-serif;
				text-align: center;
				background-color: black;
				color: blanchedalmond;
			}
			img{
				width: 60%;
				margin-top: 3%;
				margin-bottom: 5%;
			}
			div{
				width: 85%;
				margin: auto;
				margin-top: 15%;
			}
			input[type="submit"]{
				font-family: monospace;
				font-weight: bolder;
				padding: 0.4em;
				font-size: 15px;
				width: 7em;
				margin-top: 2em;
				margin-bottom: 1.5em;
				margin-left: 1em;
				margin-right: 1em;
			}
			h1{
				font-size: 1em;
			}
        </style>
        <style type="text/css" media="screen and (min-width:1000px)">
        	body{
				font-family: sans-serif;
				text-align: center;
				background-color: black;
				color: blanchedalmond;
			}
			img{
				width: 40%;
				margin-top: 3%;
				margin-bottom: 5%;
			}
			div{
				width: 50%;
				margin: auto;
				margin-top: 3.5%;
			}
			form {
				font-size: 1em;	
			}
			input[type="submit"]{
				font-family: monospace;
				font-weight: bolder;
				width: 7em;
				padding: 10px;
				font-size: 25px;
				margin-bottom: 2em;
			}
			h1{
				font-size: 2em;
			}
        </style>
	</head>
	<body>
       	<div>
	       	<hr>
	       	<h1>403 ERROR! ACCESS FORBIDDEN ! <br>YOU'RE NOT ALLOWED TO ACCESS THIS RESOURCE !</h1>
	       	<hr>
	       	<img src="${pageContext.request.contextPath}/img/accessDenied.png" alt="accessDenied">
	       	<form action="${pageContext.request.contextPath}/" class="login" method="get">
   				<input type="submit" class="login" value="Home">
   			</form>
	       	<hr>
	       	<h1>Sorry, you are not authorized to access this page. Check your credentials and try again. Or call the system admin.</h1>
	       	<hr>
       	</div>
	</body>
</html>
