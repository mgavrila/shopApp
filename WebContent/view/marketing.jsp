<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="model.Categorie" %>
<%@ page import="dao.mapper.Categorie_mapper" %>
<%@ page import="model.Produs" %>
<%@ page import="dao.mapper.Produs_mapper" %>
<%@ page import="java.io.*" %>
<%@ page import="org.apache.ibatis.io.Resources"%>
<%@ page import="org.apache.ibatis.session.SqlSession"%>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory"%>
<%@ page import="org.apache.ibatis.session.SqlSessionFactoryBuilder"%>
<!DOCTYPE html>
<html>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<head>
<meta charset="ISO-8859-1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<%
    		FileInputStream fis = new FileInputStream(this.getServletConfig().getServletContext().getRealPath("/config.xml"));
    		InputStreamReader isr = new InputStreamReader(fis);
    		BufferedReader reader = new BufferedReader(isr);

    		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);		
    		SqlSession session_category = sqlSessionFactory.openSession();
    		SqlSession session_prod = sqlSessionFactory.openSession();
    		
    		session_category.getConfiguration().addMapper(Categorie_mapper.class);
    		session_prod.getConfiguration().addMapper(Produs_mapper.class);
    		
    		Categorie_mapper mapper = session_category.getMapper(Categorie_mapper.class); 		
    		Produs_mapper mapper_prod = session_prod.getMapper(Produs_mapper.class);
    		
    	    Set<Categorie> categ = mapper.getParentCategory();
    	    Set<Categorie> categories = mapper.getCategories();
    	    

%>

</head>
<body>
<script type="text/javascript" src="../javascript/marketing.js"></script>

<form class="form-horizontal" action="../MarketingController" method="post">

<div class="form-group">
    <label for="category">Category</label>
    <select class="form-control" id="category" name="category" onchange="myFunction()">

<option disabled selected value > -- select an option -- </option>
	<%
	    for(Categorie c : categories){
	    	if(c != null){
	    		String result = c.getNume();
	    		%>
	    		<option value="<%= result%>"><%= result%></option>
	    		<%
	    	}
	    }	    	  	    
    %>
    </select>
    
</div>
  
  <div class="form-group">
    <label for="product">Product</label>
    <select class="form-control" id="product" name="product">
	<option disabled selected value > -- select an option -- </option>
	
    </select>
    
  </div>
  
  <div class="form-group">
    <label for="price">Change price</label>
    <textarea class="form-control" id="price" rows="1"></textarea>
  </div>
   
 
</form>
</body>

</html>