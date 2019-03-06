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
User:		<input type="text" name="username"><br>  
Password:	<input type="password" name="password"><br>  
<input type="hidden" name="action" value="login">
<input type="hidden" name="controller" value="client">
<input type="submit" value="login">  
</form>


</body>
</html>