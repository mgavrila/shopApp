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
<h1>Staff login page</h1>
<form action="login_check.jsp" method="post">
<table border="0">
	
	<tr><td>User name</td>
		<td><input type="text" name="username" value="" placeholder="enter username"></td>		
	</tr>
	<tr><td>Password</td>
		<td><input type="password" name="password" value="" placeholder="enter password"></td>
	</tr>
	</table>
	
	<input type="submit" value="submit">

</form>

</center>
</body>
</html>