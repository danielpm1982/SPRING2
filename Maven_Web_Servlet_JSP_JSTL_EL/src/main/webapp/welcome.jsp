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
            div#click{
                font-size: 5em;
                width: 8em;
                margin: auto;
                margin-top: 2em;
            }
        </style>
    </head>
    <body>
        <div id="click">Click</div>
        <script>
            document.getElementById("click").onmouseover = function(){
                this.style.color="blue";
                this.style.background="blanchedalmond";
            };
            document.getElementById("click").onmouseout = function(){
                this.style.color="";
                this.style.border="";
                this.style.background="";
            };
            document.getElementById("click").onmousedown = function(){
                this.style.background="black";
            };
            document.getElementById("click").onclick = function(){
                window.location="serv.do";
            };
        </script>
    </body>
</html>
