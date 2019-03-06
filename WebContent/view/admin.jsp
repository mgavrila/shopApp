<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.Set" %>
<%@ page import="model.Utilizator" %>
<%@ page import="model.Rol" %>
<%@ page import="dao.mapper.Rol_mapper" %>
<%@ page import="dao.mapper.Utilizator_mapper" %>
<%@ page import="java.io.*" %>
<%@ page import="org.apache.ibatis.io.Resources"%>
<%@ page import="org.apache.ibatis.session.SqlSession"%>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory"%>
<%@ page import="org.apache.ibatis.session.SqlSessionFactoryBuilder"%>
<%@ page import="java.util.List" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="row">
  <div class="column" onclick="openTab('b1');" style="background:green;">Add or Remove Roles</div>
  <div class="column" onclick="openTab('b2');" style="background:#034f84;">Create Accounts</div>
  <div class="column" onclick="openTab('b3');" style="background:#993366;">Account Management</div>
</div>

<!-- The expanding grid (hidden by default) -->
<div id="b1" class="containerTab" style="display:none;background:green">
  <!-- If you want the ability to close the container, add a close button -->
  <span onclick="this.parentElement.style.display='none'" class="closebtn">x</span>
  <form action="../AdminController" method="post">
  <h1>Add role</h2>
  <tr><td>Role name</td>
		<td><input type="text" name="rol_name" value="" placeholder="enter role"></td>		
	</tr>
	<input type="submit" name="action" value="Add role">
	<hr/>
	</form>
	<form action="../AdminController" method="post">
  <h1>Remove role</h2>
  <div class = "roles">
  <tr><h6>Role list</h6>
 		 <%
 		 	FileInputStream fis = new FileInputStream(this.getServletConfig().getServletContext().getRealPath("/config.xml"));
 		  		  		  		InputStreamReader isr = new InputStreamReader(fis);
 		  		  		  		BufferedReader reader = new BufferedReader(isr);
 		  		  		  		// Reader reader = Resources.getResourceAsReader(isr);

 		  		  		  		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);		
 		  		  		  		SqlSession session_1 = sqlSessionFactory.openSession();
 		  		  		  		session_1.getConfiguration().addMapper(Rol_mapper.class);
 		  		  		  		Rol_mapper mapper = session_1.getMapper(Rol_mapper.class); 		
 		  		  		  		
 		  		  		  		SqlSession session_2 = sqlSessionFactory.openSession();
 		  		  		  		session_2.getConfiguration().addMapper(Utilizator_mapper.class);
 		  		  		  		Utilizator_mapper user_map = session_2.getMapper(Utilizator_mapper.class);
 		  		  		  		
 		  		  		  		Set<Utilizator> us = user_map.getAll();
 		  		  		   		Set<Rol> ls = mapper.getRoles();
 		  		  		 		for(Rol x : ls) {
 		  		  		 	if(x != null){
 		  		  		 		String result = x.getNume();
 		 %> 	
  			<p class="roles" value="<%= result%>"><%= result%></p>
  			<%
  			}else{
  				out.print("null");
  			}
  		}
  			%> 	
		<td><input type="text" name="rol_name" value="" placeholder="enter role"></td>		
	</tr>
	</div>
	<input type="submit" name="action" value="Remove role">
	<hr/>
	</form>
</div>

<div id="b2" class="containerTab" style="display:none;background:#034f84">
  <span onclick="this.parentElement.style.display='none'" class="closebtn">x</span>
  <h2>Create Accounts</h2>
  <form action="../AdminController" method="post">
<table border="0">
	<tr><td>User name</td>
		<td><input type="text" name="username" value="" placeholder="enter username"></td>		
	</tr>
	<tr><td>Password</td>
		<td><input type="password" name="password" value="" placeholder="enter password"></td>
	</tr>
	<tr><td>Email</td>
		<td><input type="text" name="email" value="" placeholder="enter email"></td>
	</tr>
	<tr><td>Nume</td>
		<td><input type="text" name="nume" value="" placeholder="enter nume"></td>
	</tr>
	<tr><td>Prenume</td>
		<td><input type="text" name="prenume" value="" placeholder="enter prenume"></td>
	</tr>
	<label>Rol</label>
	<select name="rol">
 		
  		<% 		
  		for(Rol x : ls) {
			if(x != null){
				String result = x.getNume();		
  			%> 	
  			<option value="<%= result%>"><%= result%></option>
  			<%
  			}else{
  				out.print("null");
  			}
  		}
  			%> 		 		
	</select>
	</table>	
	<input type="submit" name="action" value="Create account">
</form>
</div>

<div id="b3" class="containerTab" style="display:none;background:#993366">
  <span onclick="this.parentElement.style.display='none'" class="closebtn">x</span>
  <h1>Update Account</h1>
  <%
  Set<Utilizator> users = user_map.getAll();
  
  	for(Utilizator x : users){
  		
  		%>

  		<label>Account number: <%=x.getId_utilizator()%></label>
  			<br/>
  		<label for="username">Username: <%= x.getUser()%> </label>
  		<input type="text" id="username" />
  			<br/>
  		<label for="rol">Rol: 
  		
  	    <%=user_map.getRolByUser(x.getUser()) %></label>
  						
		<br/>
  		<label for="email">E-mail: <%=x.getEmail()%></label>
  		<input type="text" id="email" />
  			<br/>
  		<label for="nume">Nume: <%=x.getNume() %></label>
  		<input type="text" id="nume" />
  			<br/>
  		<label for="prenume">Prenume: <%=x.getPrenume() %></label>
  		<input type="text" id="prenume" />
  		<br/>
  		<input type="submit" value="Update"/>
  			<br/><br/>
  			<hr/>
  						
  		<%				
  	}
  		%>
</div>

<script src="../javascript/script.js"></script>
</body>
</html>