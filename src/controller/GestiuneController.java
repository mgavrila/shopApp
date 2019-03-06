package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dao.mapper.Categorie_mapper;
import dao.mapper.Produs_mapper;
import info.ProductInfo;
import model.Categorie;
import model.Produs;
import utils.Function;

/**
 * Servlet implementation class GestiuneController
 */
@WebServlet("/GestiuneController")
public class GestiuneController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestiuneController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);   
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		FileInputStream fis = new FileInputStream(this.getServletConfig().getServletContext().getRealPath("/config.xml"));
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader reader = new BufferedReader(isr);
		
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);	

		SqlSession session_categ = sqlSessionFactory.openSession();
		SqlSession session_prod = sqlSessionFactory.openSession();

		session_categ.getConfiguration().addMapper(Categorie_mapper.class);
		session_prod.getConfiguration().addMapper(Produs_mapper.class);


		Categorie_mapper mapper_categ = session_categ.getMapper(Categorie_mapper.class);
		Produs_mapper mapper_prod = session_prod.getMapper(Produs_mapper.class);
		
		String action = request.getParameter("action");
		
		switch(action) {
		
		case "insert_category":
			ProductInfo insert_c = new ProductInfo(request.getParameter("category_name"),request.getParameter("parent_category"));
			if(insert_c.getCategory_name() != null && insert_c.getParent_category() == null){
				Categorie cat  = new Categorie(insert_c.getCategory_name());
				mapper_categ.insertCategory(cat);
				session_categ.commit();
				session_categ.close();
				break;
			}else if(insert_c.getCategory_name() != null && insert_c.getParent_category() != null){
				Categorie c = mapper_categ.getParentCategoryByName(insert_c.getParent_category());
				mapper_categ.insertParentCategory(insert_c.getCategory_name(),c.getId_categ());
				session_categ.commit();
				session_categ.close();
				break;
			}else {
				System.out.println("eroare");
			}
			break;
			
		case "update_category":
			ProductInfo update_c = new ProductInfo(request.getParameter("category_name"),
											 request.getParameter("category"),	
											 request.getParameter("parent_category"));
			if(update_c.getCategory_name() != null && update_c.getCategory() != null && update_c.getParent_category() == null){
				Categorie c = mapper_categ.getCategoryByName(update_c.getCategory());
				c.setNume(update_c.getCategory_name());
				mapper_categ.updateCategory(c);
				session_categ.commit();
				session_categ.close();
				break;
			}else if(update_c.getCategory_name() != null && update_c.getCategory() != null && update_c.getParent_category() != null){
				Categorie c = mapper_categ.getCategoryByName(update_c.getCategory());
				c.setNume(update_c.getCategory_name());
				Categorie parent = mapper_categ.getCategoryByName(update_c.getParent_category());
				c.setId_parinte(parent.getId_categ());	
				mapper_categ.updateCategoryWithParent(c);
				session_categ.commit();
				session_categ.close();
				break;
			}else if(update_c.getCategory_name() != null && update_c.getCategory() == null && update_c.getParent_category() != null) {
				Categorie c = mapper_categ.getCategoryByName(update_c.getParent_category());
				c.setNume(update_c.getCategory_name());
				mapper_categ.updateCategory(c);
				session_categ.commit();
				session_categ.close();
			}
				break;			
			
		case "delete_category":
			ProductInfo delete_c = new ProductInfo(request.getParameter("category_name"),
					 request.getParameter("category"),	
					 request.getParameter("parent_category"));
			if(delete_c.getCategory() != null && delete_c.getParent_category() == null){
				mapper_categ.deleteCategory(delete_c.getCategory());
				session_categ.commit();
				session_categ.close();
				break;
			}else if(delete_c.getCategory() == null && delete_c.getParent_category() != null){
				mapper_categ.deleteCategory(delete_c.getParent_category());
				session_categ.commit();
				session_categ.close();
			}
				break;
				
		case "insert_product":
			Function f = new Function();
			ProductInfo insert_product = new ProductInfo(request.getParameter("product_name"),
														 request.getParameter("product_price"),
														 request.getParameter("product_category"),
														 request.getParameter("quantity"),
														 request.getParameter("guarantee"),
														 request.getParameter("product_description"));
			if(insert_product.getProduct_name() != null 
					&& insert_product.getProduct_price() != null 
					&& insert_product.getProduct_category() != null 
					&& insert_product.getQuantity() != null 
					&& insert_product.getGuarantee() != null 
					&& insert_product.getProduct_description() != null){
				Categorie c = mapper_categ.getCategoryByName(insert_product.getProduct_category());
				int id_cat = c.getId_categ();
				session_categ.commit();
				Produs prod = new Produs(id_cat,
										 Float.parseFloat(insert_product.getProduct_price()),
										 f.convertStringToInt(insert_product.getQuantity()),
										 f.convertStringToInt(insert_product.getGuarantee()),
										 f.getShortDescription(insert_product.getProduct_description()),
										 insert_product.getProduct_description(),
										 insert_product.getProduct_name());	
				mapper_prod.insertProduct(prod);
				System.out.println("produs added");
				session_prod.commit();
				session_prod.close();
				session_categ.close();
			}
			break;
		}
	}
}
