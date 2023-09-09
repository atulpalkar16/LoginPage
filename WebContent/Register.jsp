<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="register.css">

</head>
<body>
		<form action="Register" method="post" >
			<input type="text" placeholder="Enter your Name"  name="regiName" required="required"> <br>
			<input type="email" placeholder="Enter your Email"  name="regiEmail" required="required"> <br>
			<input type="password" placeholder="Enter your Password"  name="regiPassword" required="required"> <br>
			<input type="submit">
			<a href="index.html"> <input type="button" value="BackToLogin"> </a>
		</form>
</body>
</html>