<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entity.Utilizator"%>
<%@page import="entity.Cont_client"%>
<%@page import="mapper.Utilizator_mapper"%>
<%@page import="mapper.Rol_mapper"%>
<%@page import="mapper.Client_mapper"%>
<%@ page import="database.*" %>
<%@ page import="utils.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>

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

FileInputStream fis = new FileInputStream(this.getServletConfig().getServletContext().getRealPath("/config.xml"));
InputStreamReader isr = new InputStreamReader(fis);
BufferedReader reader = new BufferedReader(isr);

SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);		
SqlSession session_user = sqlSessionFactory.openSession();
SqlSession session_rol = sqlSessionFactory.openSession();

session_user.getConfiguration().addMapper(Utilizator_mapper.class);
session_rol.getConfiguration().addMapper(Rol_mapper.class);

Utilizator_mapper mapper_user = session_user.getMapper(Utilizator_mapper.class);

Utilizator user = mapper_user.getByUser(username);


if(user.getUser() != null && user.getParola() != null){
		if(user.getParola().equals(EncryptService.getHashOfString(password))){
			if(mapper_user.getRolByUser(username).equals("administrator")){
				String redirectURL = "admin.jsp";
				 response.sendRedirect(redirectURL);
				 session_user.close();
			}else if(mapper_user.getRolByUser(username).equals("gestionar")){
					String redirectURL = "gestionar.jsp";
					response.sendRedirect(redirectURL);
		}		
	}
}




%>



</body>
</html>