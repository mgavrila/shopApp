<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<form action="../AuthController" method="post">  
user:		<input type="text" name="username"><br>  
password:	<input type="password" name="password"><br> 
email:	<input type="text" name="email"><br> 
nume:	<input type="text" name="nume"><br> 
prenume:	<input type="text" name="prenume"><br>  
<input type="hidden" name="action" value="register">
<input type="hidden" name="controller" value="client">
<input type="submit" value="register">  
</form>

</body>
</html>