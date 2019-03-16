<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Vaccines Record</title>
		<link rel="stylesheet" type="text/css" media="screen and (min-width:360px) and (max-width:999px)" href="${pageContext.request.contextPath}/css/css2.css" />
		<link rel="stylesheet" type="text/css" media="screen and (min-width:1000px)" href="${pageContext.request.contextPath}/css/css.css" />
	</head>
	<body>
		<div class="mainText">
			<div class="body">
				<a href="${pageContext.request.contextPath}/tps"><img class="img7" src="${pageContext.request.contextPath}/img/healthRecord.png" alt="Vaccines Record API" title="Vaccines Record API" /></a>
				<h1>${welcomeMessage}</h1>
			</div>
			<div class="body">
				<a href="mailto:${email}"><img class="img8" src="${pageContext.request.contextPath}/img/email.png" alt="email to: ${email}" title="email to: ${email}" /></a>
				<p>${credits}</p>
				<p>For contact, email to:</p>
				<p><a href="mailto:${email}">${email}</a></p>
			</div>
		</div>
	</body>
</html>
