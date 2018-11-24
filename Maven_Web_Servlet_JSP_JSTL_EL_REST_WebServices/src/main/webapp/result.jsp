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
                font-size: 1em;
                width: 80%;
                margin: auto;
                margin-top: 2em;
                margin-bottom: 2em;
            }
            div.table{
            	display: table;
            	border-collapse: collapse;
            	margin: auto;
            	background-color: navy;
            }
            div.tableHeader, div.cell{
            	display: table-cell;
            	padding: 0.5em;
            	border-color: green;
            	border-style: solid;
            	font-weight: bolder;
            }
            div.row{
            	display: table-row;
            }
            div.cell{
            	font-weight: normal; 
            }
        </style>
    </head>
    <body>
        <div id="result">
        	<p>${result}</p>
        	<div class="table">
        		<div class="row">
        			<div class="tableHeader">Id</div>
        			<div class="tableHeader">UserName</div>
        			<div class="tableHeader">Password</div>
        			<div class="tableHeader">Active</div>
        		</div>
        		<div class="row">
        			<div class="cell">${person.id}</div>
        			<div class="cell">${person.userName}</div>
        			<div class="cell">${person.password}</div>
        			<div class="cell">${person.active}</div>
        		</div>
        	</div>
        	<p><a href="home.do">HOME</a></p>
       	</div>
    </body>
</html>
