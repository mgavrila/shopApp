<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>   
<%@ page import="java.util.*" %> 
<%@ page import="java.io.*" %>  
<%@ page import="entity.*"%>
<%@ page import="database.*" %>
<%@ page import="utils.*" %>
<%@ page import="entity.Categorie" %>
<%@ page import="mapper.Categorie_mapper" %>
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
String product_name = request.getParameter("product_name");
String product_price = request.getParameter("product_price");
String product_category = request.getParameter("product_category");
String quantity = request.getParameter("quantity");
String product_description = request.getParameter("product_description");
String guarantee = request.getParameter("guarantee");
String image1 = request.getParameter("filebutton");
String image2 = request.getParameter("filebutton2");
String image3 = request.getParameter("filebutton3");

String category = request.getParameter("category_name");
String parent_category = request.getParameter("parent_category");

FileInputStream fis = new FileInputStream(this.getServletConfig().getServletContext().getRealPath("/config.xml"));
InputStreamReader isr = new InputStreamReader(fis);
BufferedReader reader = new BufferedReader(isr);

SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);	

SqlSession session_categ = sqlSessionFactory.openSession();

session_categ.getConfiguration().addMapper(Categorie_mapper.class);

Categorie_mapper mapper_categ = session_categ.getMapper(Categorie_mapper.class);
Categorie cat  = new Categorie(category);

System.out.println(category);

System.out.println(parent_category);

if(category != null && parent_category == null){
	System.out.println(parent_category);
	mapper_categ.insertCategory(cat);
	session_categ.commit();
	session_categ.close();
	System.out.println("successfull");
}else if(category != null && parent_category != null){
	Categorie c = mapper_categ.getParentCategoryByName(parent_category);
	System.out.println("categorie: "+category);
	System.out.println("id categ: "+c.getId_categ());
	mapper_categ.insertParentCategory(category,c.getId_categ());
	session_categ.commit();
	session_categ.close();
}






%>
</body>
</html>