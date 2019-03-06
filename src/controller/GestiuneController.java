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

import dao.GestiuneDao;
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
		
		GestiuneDao gd = new GestiuneDao(this.getServletConfig());	
		String action = request.getParameter("action");
		
		switch(action) {
		
		case "insert_category":
			ProductInfo insert_c = new ProductInfo(request.getParameter("category_name"),request.getParameter("parent_category"));
				gd.insertCategory(insert_c.getCategory_name(), insert_c.getParent_category());
				break;
			
		case "update_category":
			ProductInfo update_c = new ProductInfo(request.getParameter("category_name"),
											 request.getParameter("category"),	
											 request.getParameter("parent_category"));
			gd.updateCategory(update_c.getCategory(), update_c.getCategory_name(),update_c.getParent_category());
				break;			
			
		case "delete_category":
			ProductInfo delete_c = new ProductInfo(request.getParameter("category_name"),
					 request.getParameter("category"),	
					 request.getParameter("parent_category"));
			gd.deleteCategory(delete_c.getCategory(), delete_c.getParent_category());
				break;
				
		case "insert_product":
			ProductInfo insert_product = new ProductInfo(request.getParameter("product_name"),
														 request.getParameter("product_price"),
														 request.getParameter("product_category"),
														 request.getParameter("quantity"),
														 request.getParameter("guarantee"),
														 request.getParameter("product_description")
														 );
			gd.insertProduct(insert_product.getProduct_name(), 
							 insert_product.getProduct_price(),
							 insert_product.getProduct_category(),
							 insert_product.getQuantity(),
							 insert_product.getGuarantee(),
							 insert_product.getProduct_description()
							 );
			break;
		}
	}
}
