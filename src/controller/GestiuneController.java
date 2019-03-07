package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GestiuneDao;
import info.ProductInfo;
import jaxb.ProductList;
import main.ListaProduse;

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
		String redirectURL = "view/gestiune.jsp";
		
		switch(action) {
		
		case "insert_category":
			ProductInfo insert_c = new ProductInfo(request.getParameter("category_name"),request.getParameter("parent_category"));
				gd.insertCategory(insert_c.getCategory_name(), insert_c.getParent_category());
				response.sendRedirect(redirectURL);
				break;
			
		case "update_category":
			ProductInfo update_c = new ProductInfo(request.getParameter("category_name"),
											 request.getParameter("category"),	
											 request.getParameter("parent_category"));
			gd.updateCategory(update_c.getCategory(), update_c.getCategory_name(),update_c.getParent_category());
			response.sendRedirect(redirectURL);
				break;			
			
		case "delete_category":
			ProductInfo delete_c = new ProductInfo(request.getParameter("category_name"),
					 request.getParameter("category"),	
					 request.getParameter("parent_category"));
			gd.deleteCategory(delete_c.getCategory(), delete_c.getParent_category());
			response.sendRedirect(redirectURL);
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
			response.sendRedirect(redirectURL);
			
	        response.setContentType("text/html;charset=UTF-8");
	     
			break;
			
			
		case "getProductListAsXml":
			PrintWriter out = response.getWriter();
			String categorie  = request.getParameter("categorie");
			gd.getProductByCategory(categorie);
			response.setContentType("application/xml");
			ProductList pl = new ProductList();
			ListaProduse lp = new ListaProduse();
			lp.setProduse(gd.getProductByCategory(categorie));
			String result = pl.generateXML(lp);	
			out.print(result);
			System.out.println("categorie: "+ categorie + " "+result);
			break;
			
		case "delete_product":
			ProductInfo delete_product = new ProductInfo(request.getParameter("product_categorie"));
			gd.deleteProduct(delete_product.getProduct_name());
			response.sendRedirect(redirectURL);
			break;
			
		}
	}
}
