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
<center>
<form action="register_check.jsp" method="post">
<h1>Register page</h1>
<table>
	
	<tr><td>User name</td>
		<td><input type="text" name="username" value="" placeholder="enter username"></td>		
	</tr>
	<tr><td>Password</td>
		<td><input type="password" name="password" value="" placeholder="enter password"></td>
	</tr>
		<tr><td>E-mail</td>
		<td><input type="text" name="email" value="" placeholder="enter email"></td>
		</tr>
		<tr><td>Nume</td>
		<td><input type="text" name="nume" value="" placeholder="enter firstname"></td>
		</tr>
		<tr><td>Prenume</td>
		<td><input type="text" name="prenume" value="" placeholder="enter last name"></td>
		</tr>
	</table>
	
	<input type="submit" value="submit">

</form>

</center>

</body>
</html>