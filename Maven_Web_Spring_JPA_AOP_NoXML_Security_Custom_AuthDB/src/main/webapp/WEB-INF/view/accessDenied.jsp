<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Access Denied!</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style type="text/css">
        	body{
				font-family: sans-serif;
				text-align: center;
				background-color: black;
				color: blanchedalmond;
			}
			img{
				width: 40%;
				margin-top: 5%;
				margin-bottom: 5%;
			}
			div{
				width: 70%;
				margin: auto;
				margin-top: 5%;
			}
        </style>
	</head>
	<body>
       	<div>
	       	<hr>
	       	<h1>403 ERROR! ACCESS FORBIDDEN ! <br>YOU'RE NOT ALLOWED TO ACCESS THIS RESOURCE !</h1>
	       	<hr>
	       	<img src="${pageContext.request.contextPath}/resources/img/accessDenied.png" alt="accessDenied">
	       	<hr>
	       	<h1>Sorry, you are not authorized to access this page. Check your credentials and try again. Or call the system admin.</h1>
	       	<hr>
       	</div>
	</body>
</html>

<!-- 
This page substitutes the default 403 forbidden http page. After being defined its path at
MyWebSecurityConfigurerAdapter class and mapped to this jsp at the SecurityController class.
-->
