<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSP SpringBoot Maven app</title>
		<link rel="stylesheet" type="text/css" href="css/css.css">
	</head>
	<body>
		<div class="mainText">
			<div id="body1">
				<img alt=springBoot src="img/springBoot.png" title="springBoot" />
				<p class="bigP">${welcomeMessage}</p>
			</div>
			<div id="body2">
				<p class=mediumP>${localDateTimeMessage} ${localDateTime}</p>
			</div>
		</div>
	</body>
</html>
