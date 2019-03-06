<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="model.Utilizator"%>  
 <%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

  
<p>You are successfully logged in!</p>  

<%  
Utilizator user=(Utilizator)request.getAttribute("user");  
out.print("Welcome, "+user.getUser());  
%>  

</body>
</html>