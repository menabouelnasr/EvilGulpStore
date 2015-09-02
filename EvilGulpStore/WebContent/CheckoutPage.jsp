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
<body>
<nav class="navbar navbar-inverse">
<div class="jumbotron"> 
  <h1>Payment Information</h1>
</div>
</nav>
</body>
<body background="Images\Tennis.jpg">
<form action= "insertCart" method="get">
First Name on Card:
<input type="text"  style="width:200px; height:25px;" name="fName"><br><br>
Last Name on Card:
<input type="text"  style="width:200px; height:25px;" name="lName"><br><br>
Credit Card Number:
<input type="number"  style="width:200px; height:25px;" placeholder= "ex: 1234 5679 1011 1213" name="cardNum"><br><br>
3 Digit Security Code:
<input type="number" style="width:200px; height:25px;" placeholder= "located on the back" name="secCode"><br><br>
Expiration Date:
<input type="number" style="width:200px; height:25px;" placeholder= "ex. MM/YY" name="exp"><br><br>
Address:<br>
<input type="text" style="width:200px; height:25px;" placeholder= "Street Address" name="address"><br>
<input type="text" style="width:200px; height:25px;" placeholder= "City" name="city"><br>
<input type="text" style="width:200px; height:25px;" placeholder= "State" name="state"><br><br>
<input type=submit style="width:200px; height:25px;" name=guest value="Confirm"> </input>
</form>
</body>
</html>