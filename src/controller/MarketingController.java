package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GestiuneDao;
import dao.MarketingDao;
import jaxb.ProductList;
import main.ListaProduse;

/**
 * Servlet implementation class MarketingController
 */
@WebServlet("/MarketingController")
public class MarketingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarketingController() {
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
		MarketingDao md = new MarketingDao(this.getServletConfig());			
		String action = request.getParameter("action");
	//	System.out.println(action);
		
		switch(action) {
			case "getProductListAsXml":
				PrintWriter out = response.getWriter();
				String category  = request.getParameter("category");
				md.getProductByCategory(category);
				response.setContentType("application/xml");
				ProductList pl = new ProductList();
				ListaProduse lp = new ListaProduse();
				lp.setProduse(md.getProductByCategory(category));
				String result = pl.generateXML(lp);			
				out.print(result);
				System.out.println("categorie: "+ category + " "+result);
				break;
			}
		}
}
