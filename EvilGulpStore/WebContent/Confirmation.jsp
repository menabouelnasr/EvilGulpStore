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
  body {text: white; background-color: white;  text-align: center; }
   p {background-color: white;}
   h1{text:white; text-align-center;}
   </style>
<title>PaymentInformation</title>
</head>
<body background="Images\Tennis.jpg">
<nav class="navbar navbar-inverse">
<div class="jumbotron"> 
  <h1>Order Confirmation</h1>
<form action= "ProductList" method="post">
</nav>
<input type=submit name=guest value="End Session"> </input>
</form>
</div>
<br>
<br>
<div style="text-align: center">
<form>
Your order confirmation number is:  <%= (int) (Math.random() * 1000000) %>
${message}
</form>
</div>
<p></p>

${message2}
<% %>

</body>