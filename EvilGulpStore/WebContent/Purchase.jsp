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
<title>Welcome to the Tennis Store</title>
</head>
<body background="Images\Tennis.jpg">
<nav class="navbar navbar-inverse">
<div class="jumbotron"> 
  <h1>Purchase Order</h1>
</div>
</nav>
${message}
</body>
<body>
<form action= "insertCart" method="post">
<input type="text" placeholder= "Quantity" name="quantity">
<input type=submit name=guest value="Add to Cart"> </input>
</form>
</body>
</html>