<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action= "CustSignUp" method="get">
First Name:
<input type="text"  style="width:200px; height:25px;" name="fName"><br><br>
Last Name:
<input type="text"  style="width:200px; height:25px;" name="lName"><br><br>
email:
<input type="text"  style="width:200px; height:25px;" placeholder= "ex: email@domain.com" name="email"><br><br>
password:
<input type="text" style="width:200px; height:25px;" placeholder= "located on the back" name="password"><br><br>
<input type=submit name=guest value="Sign Up"> </input>
</form>
</body>
</html>