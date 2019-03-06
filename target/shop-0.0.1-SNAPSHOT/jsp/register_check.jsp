<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="database.*" %>
<%@ page import="utils.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="entity.Utilizator" %>
<%@ page import="entity.Cont_client" %>
<%@ page import="entity.Rol" %>
<%@ page import="mapper.Utilizator_mapper" %>
<%@ page import="mapper.Client_mapper" %>
<%@ page import="mapper.Rol_mapper" %>
<%@ page import="org.apache.ibatis.io.Resources"%>
<%@ page import="org.apache.ibatis.session.SqlSession"%>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory"%>
<%@ page import="org.apache.ibatis.session.SqlSessionFactoryBuilder"%>  

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<% 

String username = request.getParameter("username");
String password = request.getParameter("password");
String email = request.getParameter("email");
String nume = request.getParameter("nume");
String prenume = request.getParameter("prenume");

FileInputStream fis = new FileInputStream(this.getServletConfig().getServletContext().getRealPath("/config.xml"));
InputStreamReader isr = new InputStreamReader(fis);
BufferedReader reader = new BufferedReader(isr);

SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);	

//SqlSession session_rol = sqlSessionFactory.openSession();
SqlSession session_client = sqlSessionFactory.openSession();

//session_rol.getConfiguration().addMapper(Rol_mapper.class);
session_client.getConfiguration().addMapper(Client_mapper.class);

//Rol_mapper mapper_rol = session_rol.getMapper(Rol_mapper.class);
Client_mapper mapper_client = session_client.getMapper(Client_mapper.class);

Function f = new Function();

if(f.checkFields(username, password, email, nume, prenume) == true){
	if(f.passwordValidation(username, password) == true){
		if(f.isValidEmailAddress(email) == true){
			Cont_client client = new Cont_client(username,EncryptService.getHashOfString(password),email,nume,prenume);
			mapper_client.insertClient(client);
			mapper_client.insertTipClient("active", client.getId_client());
			session_client.commit();
			session_client.close();
			out.print("user registered");
		    String redirectURL = "login.jsp";
		    response.sendRedirect(redirectURL);
		     
			}else{
				out.print("email invalid");
			}
		}else{
			out.print("password invalid");		
		}	
	}else{
		out.print("complete all fields");
	}


%>




</body>
</html>