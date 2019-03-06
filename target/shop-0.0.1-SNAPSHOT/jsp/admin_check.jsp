<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="entity.*"%>
<%@ page import="database.*" %>
<%@ page import="utils.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="mapper.Rol_mapper" %>
<%@ page import="mapper.Utilizator_mapper" %>
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
String rol = request.getParameter("rol");
String rol_name = request.getParameter("rol_name");
String action = request.getParameter("action");

FileInputStream fis = new FileInputStream(this.getServletConfig().getServletContext().getRealPath("/config.xml"));
InputStreamReader isr = new InputStreamReader(fis);
BufferedReader reader = new BufferedReader(isr);

SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);	

SqlSession session_rol = sqlSessionFactory.openSession();
SqlSession session_user = sqlSessionFactory.openSession();

session_rol.getConfiguration().addMapper(Rol_mapper.class);
session_user.getConfiguration().addMapper(Utilizator_mapper.class);

Rol_mapper mapper_rol = session_rol.getMapper(Rol_mapper.class);
Utilizator_mapper mapper_user = session_user.getMapper(Utilizator_mapper.class);

Rol newRol = new Rol(rol_name);
Function f = new Function();


if ("Add role".equals(action)) {
	if(rol_name != ""){
		System.out.println(rol_name);
	if(f.existRol(rol_name) == true){
		System.out.println("rol existent");
		out.print("rolul este deja existent");
		}else{			
			mapper_rol.insert(newRol);
			session_rol.commit();
			session_rol.close();
		}
	}else{
		out.print("campul rol nu poate sa fie gol");
	}
} else if ("Remove role".equals(action)) {
	System.out.println("rmeove rol");
	if(rol_name != ""){
		System.out.println("rmeove rol 2");
		mapper_rol.deleteRol(rol_name);
		session_rol.commit();
		session_rol.close();
		out.print("rol removed: "+rol_name);
		System.out.println("rol removed");
	}	
} else if("Create account".equals(action)){
	if(f.checkFields(username, password, email, nume, prenume) == true){
		if(f.passwordValidation(username, password) == true){
			if(f.isValidEmailAddress(email) == true){
				Utilizator user = new Utilizator(username,EncryptService.getHashOfString(password),email,nume,prenume);
			      Rol r = mapper_rol.getRolId(rol);
			      session_rol.commit();
			      session_rol.close();
			      mapper_user.insert(user);
			      mapper_user.insertRolUtilizator(r.getId_rol(), user.getId_utilizator());
			      mapper_user.insertTip("active",user.getId_utilizator());
			      session_user.commit();
			      session_user.close();
				out.print(username + " "+EncryptService.getHashOfString(password)+" "+email+" "+nume+" "+prenume+" "+rol);
			}else{
				out.print("email invalid");
			}
			
		}else{
			out.print("password invalid");
		}
		
	//	out.print(username + " "+EncryptService.getHashOfString(password)+" "+email+" "+nume+" "+prenume+" "+rol);
	}else{
		out.print("invalid");
	}
} else if("Update Account".equals(action)){

}

%>

</body>
</html>