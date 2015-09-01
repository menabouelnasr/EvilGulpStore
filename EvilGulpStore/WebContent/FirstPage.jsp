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
<title>Welcome</title>
</head>
<body>
<nav class="navbar navbar-inverse">
<div class="jumbotron"> 
  <h1>Log in or Sign up!</h1>
</div>
</nav>
Been with us before? Login below! <br>
<form action= "Login.jsp">
<input type=submit name=guest value="Login"> </input>
</form><br>
Not a user? Sign up below! <br>
<form action= "SignUp.jsp">
<input type=submit name=guest value="Sign Up"> </input>
</form><br>
Otherwise, you may checkout as a guest.<br>
<form action= "CheckoutPage.jsp" method="post">
<input type=submit name=guest value="Continue as Guest"> </input>
<% session.setAttribute("Guest",1); %>
</form>
<br>
<br>
</body>
</html>