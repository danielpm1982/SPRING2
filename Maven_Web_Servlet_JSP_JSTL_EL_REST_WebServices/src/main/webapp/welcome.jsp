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
            div{
                font-size: 2em;
                width: 8em;
                margin: auto;
                margin-top: 2em;
            }
        </style>
    </head>
    <body>
        <div id="webService 1">REST WebService 1 (from local file)</div>
        <div id="webService 2">REST WebService 2 (from remote service)</div>
        <script>
            document.getElementById("webService 1").onmouseover = function(){
                this.style.color="blue";
                this.style.background="blanchedalmond";
            };
            document.getElementById("webService 1").onmouseout = function(){
                this.style.color="";
                this.style.border="";
                this.style.background="";
            };
            document.getElementById("webService 1").onmousedown = function(){
                this.style.background="black";
            };
            document.getElementById("webService 1").onclick = function(){
                window.location="serv.do";
            };
            document.getElementById("webService 2").onmouseover = function(){
                this.style.color="blue";
                this.style.background="blanchedalmond";
            };
            document.getElementById("webService 2").onmouseout = function(){
                this.style.color="";
                this.style.border="";
                this.style.background="";
            };
            document.getElementById("webService 2").onmousedown = function(){
                this.style.background="black";
            };
            document.getElementById("webService 2").onclick = function(){
                window.location="servUser.do";
            };
        </script>
    </body>
</html>
