<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.Set" %>
<%@ page import="entity.Categorie" %>
<%@ page import="mapper.Categorie_mapper" %>
<%@ page import="java.io.*" %>
<%@ page import="org.apache.ibatis.io.Resources"%>
<%@ page import="org.apache.ibatis.session.SqlSession"%>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory"%>
<%@ page import="org.apache.ibatis.session.SqlSessionFactoryBuilder"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="../javascript/expandGestion.js"></script>
<!------ Include the above in your HEAD tag ---------->
<link href="../css/gestionar.css" rel="stylesheet" type="text/css">

<form class="form-horizontal" action="gestionar_check.jsp" method="post">
<fieldset>

<button class="collapsible" onclick="openExpand();">INSERT CATEGORY</button>
<div class="content">
<!-- Form Name -->
<legend>CATEGORY</legend>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="category_name">CATEGORY NAME</label>  
  <div class="col-md-4">
  <input id="category_name" name="category_name" placeholder="CATEGORY NAME" class="form-control input-md" required="" type="text">
    
  </div>
</div>

<!-- Select Basic -->
<div class="form-group">
  <label class="col-md-4 control-label" for="parent_category">PARENT CATEGORY</label>
  <div class="col-md-4">
    <select id="parent_category" name="parent_category" class="form-control">
    <option disabled selected value > -- select an option -- </option>
    <%
    	FileInputStream fis = new FileInputStream(this.getServletConfig().getServletContext().getRealPath("/config.xml"));
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader reader = new BufferedReader(isr);

		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);		
		SqlSession session_category = sqlSessionFactory.openSession();
		session_category.getConfiguration().addMapper(Categorie_mapper.class);
		Categorie_mapper mapper = session_category.getMapper(Categorie_mapper.class); 		
		
	    Set<Categorie> categ = mapper.getParentCategory();
	    
	    for(Categorie c : categ){
	    	if(c != null){
	    		String result = c.getNume();
	    		System.out.println(result);
	    		%>
	    		<option value="<%= result%>"><%= result%></option>
	    		<%
	    	}
	    }  
    %>
    </select>
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="addCategory">INSERT CATEGORY</label>
  <div class="col-md-4">
    <button id="addCategory" name="addCategory" class="btn btn-primary">ADD</button>
  </div>
  </div>

</fieldset>
</form>
</div>

<form class="form-horizontal" action="gestionar_check.jsp" method="post">
<fieldset>

<button class="collapsible" onclick="openExpand();">INSERT PRODUCTS</button>
<div class="content">
<!-- Form Name -->
<legend>PRODUCTS</legend>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="product_name">PRODUCT NAME</label>  
  <div class="col-md-4">
  <input id="product_name" name="product_name" placeholder="PRODUCT NAME" class="form-control input-md" required="" type="text">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="product_name_fr">PRODUCT PRICE</label>  
  <div class="col-md-4">
  <input id="product_price" name="product_price" placeholder="PPRODUCT PRICE" class="form-control input-md" required="" type="text">
    
  </div>
</div>

<!-- Select Basic -->
<div class="form-group">
  <label class="col-md-4 control-label" for="product_category">PRODUCT CATEGORY</label>
  <div class="col-md-4">
    <select id="product_category" name="product_category" class="form-control">
    </select>
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="quantity">QUANTITY</label>  
  <div class="col-md-4">
  <input id="quantity" name="quantity" placeholder="QUANTITY" class="form-control input-md" required="" type="text">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="percentage_discount">GUARANTEE</label>  
  <div class="col-md-4">
  <input id="guarantee" name="guarantee" placeholder="GUARANTEE" class="form-control input-md" required="" type="text">
    
  </div>
</div>

<!-- Textarea -->
<div class="form-group">
  <label class="col-md-4 control-label" for="product_description">PRODUCT DESCRIPTION</label>
  <div class="col-md-4">                     
    <textarea class="form-control" id="product_description" name="product_description"></textarea>
  </div>
</div>


<!-- File Button --> 
<div class="form-group">
  <label class="col-md-4 control-label" for="filebutton">IMAGES</label>
  <div class="col-md-4">
    <input id="filebutton" name="filebutton" class="input-file" type="file" />
    <br/>
    <input id="filebutton" name="filebutton2" class="input-file" type="file" />
    <br/>
    <input id="filebutton" name="filebutton3" class="input-file" type="file" />
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="singlebutton">INSERT PRODUCT</label>
  <div class="col-md-4">
    <button id="singlebutton" name="singlebutton" class="btn btn-primary">ADD</button>
  </div>
  </div>

</fieldset>
</form>
</div>




<form class="form-horizontal" action="gestionar_check.jsp" method="post">
<fieldset>

<button class="collapsible" onclick="openExpand();">UPDATE CATEGORY</button>
<div class="content">
<!-- Form Name -->
<legend>CATEGORY</legend>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="update_category_name">CATEGORY NAME</label>  
  <div class="col-md-4">
  <input id="update_category_name" name="update_category_name" placeholder="CATEGORY NAME" class="form-control input-md" required="" type="text">
    
  </div>
</div>

<!-- Select Basic -->
<div class="form-group">
  <label class="col-md-4 control-label" for="update_parent_category">PARENT CATEGORY</label>
  <div class="col-md-4">
    <select id="update_parent_category" name="update_parent_category" class="form-control">
    <option disabled selected value > -- select an option -- </option>
    <%
	    
	    for(Categorie c : categ){
	    	if(c != null){
	    		String result = c.getNume();
	    		System.out.println(result);
	    		%>
	    		<option value="<%= result%>"><%= result%></option>
	    		<%
	    	}
	    }  
    %>
    </select>
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="updateCategory">UPDATE CATEGORY</label>
  <div class="col-md-4">
    <button id="updateCategory" name="updateCategory" class="btn btn-primary">UPDATE</button>
  </div>
  </div>

</fieldset>
</form>
</div>




</html>