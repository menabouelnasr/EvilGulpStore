<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <style>
a:link {    /* unvisited link */color: black; text-decoration: none}
a:visited {    /* visited link */color: black;}
a:hover {    /* mouse over link */color: red; text-decoration: underline;}
a:active {    /* active link */color: black;}
</style>
  <style> 
  body {text: red;  text-align: center; }
   p {background-color: white;}
   h1{text:white; text-align-center;}
   </style>
<title>Welcome to the Tennis Store</title>
</head>
<body background="Images\Tennis.jpg">
<nav class="navbar navbar-inverse">
<div  align= "right-center">
<form action="Login.jsp">
<input  type=submit name=guest value="Admin Login"> </input>
</form>
</div>
<div class="jumbotron"> 
  <h1>Tennis Gear & Equipment</h1>
</div> 
</nav>
<% if ((Boolean)session.getAttribute("List")==false) {%>

${message}
<br>
<form action="GetStore" method= "Post">
<input  type=submit name=guest value="Purchase"> </input>
</form> 
<form action="ProductList" method= "Get">
<input  type=submit name=guest value="Back"> </input>
</form>

<% }; %>
<% if ((Boolean)session.getAttribute("List")==true) {%>
${message}
<% }; %>

<br>
<br>
</body>
</html>