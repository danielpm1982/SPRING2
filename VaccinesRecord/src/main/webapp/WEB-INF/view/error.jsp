<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Error!</title>
		<style type="text/css" media="screen and (min-width:360px) and (max-width:999px)">
        	body{
				font-family: sans-serif;
				text-align: center;
				background-color: black;
				color: blanchedalmond;
			}
			div#errorText{
				width: 95%;
				margin: auto;
				margin-top: 5em;
				margin-bottom: 5em;			
			}
			div#errorBody1{
				font-size: 1em;
				width: 65%;
				margin: auto;
			}
			div#errorBody2{
				margin-top: 5em;
				font-size: 1.5em;
				border: thin dashed blanchedalmond;
			}
			div#errorBody3{
				font-size: 0.6em;
				text-align: center;
				margin-top: 5em;
				margin-bottom: 5em;
				border: thin dashed blanchedalmond;
			}
			form {
				width: 100%;
				margin: auto;
			}
			input[type="submit"]{
				font-family: monospace;
				font-weight: bolder;
				width: 6.5em;
				padding: 10px;
				font-size: 45px;
				margin-bottom: 1em;
			}
			h1.errors{
				font-family: monospace;
				color: yellow;
				background-color: red;
				margin-bottom: 1.5em;
				font-size: 3em;
			}
			h1.errors2{
				font-family: monospace;
				color: red;
				background-color: yellow;
				margin-bottom: 1.5em;
				font-size: 3em;
			}
			h1.errors3{
				font-size: 25px;
			}
			img{
				width: 65%;
				position: relative;
				top: 0.5em;
				right: 1em;
				margin-left: 1em;
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
			div#errorText{
				width: 65%;
				margin: auto;
				margin-top: 5em;
				margin-bottom: 5em;			
			}
			div#errorBody1{
				font-size: 0.7em;
				width: 45%;
				margin: auto;
			}
			div#errorBody2{
				margin-top: 5em;
				font-size: 1em;
				border: thin dashed blanchedalmond;
			}
			div#errorBody3{
				font-size: 0.7em;
				text-align: center;
				margin-top: 5em;
				margin-bottom: 5em;
				border: thin dashed blanchedalmond;
			}
			form {
				width: 100%;
				margin: auto;
			}
			input[type="submit"]{
				font-family: monospace;
				font-weight: bolder;
				width: 7em;
				padding: 10px;
				font-size: 25px;
				margin-bottom: 1em;
			}
			h1.errors{
				font-family: monospace;
				color: yellow;
				background-color: red;
				margin-bottom: 1.5em;
				font-size: 3em;
			}
			h1.errors2{
				font-family: monospace;
				color: red;
				background-color: yellow;
				margin-bottom: 1.5em;
				font-size: 3em;
			}
			h1.errors3{
				font-size: 25px;
			}
			img{
				width: 75%;
				position: relative;
				top: 0.5em;
				right: 1em;
				margin-left: 1em;
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
		<div id="errorText">
			<div id="errorBody1">
				<a href="${pageContext.request.contextPath}/tps">
					<img src="${pageContext.request.contextPath}/img/healthRecord.png" alt="Vaccines Record API" title="Vaccines Record API" />
				</a>
				<h1 class="errors">ERROR PAGE !</h1>
				<h1 class="errors2">${pageContext.exception.cause.message}</h1>
				<form action="${pageContext.request.contextPath}/tps" class="return" method="get">
   					<input type="submit" class="return" value="Back TPS">
   				</form>
				<form action="${pageContext.request.contextPath}/" class="login" method="get">
	   				<input type="submit" value="Home">
	   			</form>
			</div>
			<div id="errorBody2">
				<h1 class="errors3">Error specifics:</h1>
				<h3>Timestamp: ${timestamp}</h3>
				<h3>Error: ${error}</h3>
<%-- 				<h3>Status: ${status}</h3> --%>
				<h3>Error cause: ${pageContext.exception.cause}</h3>
				<c:if test="${not empty pageContext.exception.cause.message}">
					<h3>Error message: ${pageContext.exception.cause.message}</h3>
				</c:if>
				<h3>ErrorData request uri: ${pageContext.errorData.requestURI}</h3>
				<h3>ErrorData status code: ${pageContext.errorData.statusCode}</h3>
			</div>
			<div id="errorBody3">
				<c:if test="${not empty pageContext.exception.stackTrace}">
					<h1 class="errors3">StackTrace:</h1><br>
					<c:forEach items="${pageContext.exception.stackTrace}" var="trace" >
						<h3>${trace}</h3>
					</c:forEach>
				</c:if>
			</div>
		</div>
	</body>
</html>
