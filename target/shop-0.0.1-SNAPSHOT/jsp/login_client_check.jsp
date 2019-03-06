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
SqlSession session_client = sqlSessionFactory.openSession();

session_client.getConfiguration().addMapper(Client_mapper.class);

Client_mapper mapper_client = session_client.getMapper(Client_mapper.class);

Cont_client client = mapper_client.getClientByUser(username);



if(client.getUser_client() != null){
	System.out.println(client.getParola_client());
	System.out.println(EncryptService.getHashOfString(password));
	
		if(client.getParola_client().equals(EncryptService.getHashOfString(password))){
				String redirectURL = "produs.jsp";
				response.sendRedirect(redirectURL);	
				session_client.close();
	}else{
		out.print("eror");
	}
}else{
	out.print("asda");
}


%>

</body>
</html>