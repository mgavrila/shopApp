package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Constant;
import dao.UtilizatorDao;
import info.UserInfo;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/ControllerServlet")
public class AuthController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthController() {
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
	          
	     String controller = request.getParameter("controller");
	     String action = request.getParameter("action");
	   	     
	     UtilizatorDao ud = new UtilizatorDao(this.getServletConfig());
	     Constant value = new Constant();
	     
	     switch(controller) {
	     case "client":
	    	 if(action.equals("login")) {
	    		  UserInfo ui = new UserInfo(request.getParameter("username"),request.getParameter("password"));	    		  
	    		  if(ud.loginClient(ui.getUser(), ui.getPassword()) == true) {
	    			  String redirectURL = "view/login-success.jsp";
					  response.sendRedirect(redirectURL);	
	    		  } 	    		  
	    	 }else if(action.equals("register")) {
	    		 UserInfo ui = new UserInfo(request.getParameter("username"),
	    				 					request.getParameter("password"),
	    				 					request.getParameter("email"),
	    				 					request.getParameter("nume"), 
	    				 					request.getParameter("prenume")
	    				 					);   	
	    		 if(ud.registerClient(ui.getUser(), ui.getPassword(), ui.getEmail(), ui.getNume(), ui.getPrenume()) == true) {
	    			 String redirectURL = "view/login_client.jsp";
					 response.sendRedirect(redirectURL);
	    		 }

	    	 }
	    	 break;
	     case "staff":
	    	 if(action.equals("login")) {
	    		 UserInfo ui = new UserInfo(request.getParameter("username"),request.getParameter("password"));
	    		 if(ud.loginStaff(ui.getUser(), ui.getPassword()).equals(value.getROL_ADMINISTRATOR())){
	    			 String redirectURL = "view/admin.jsp";
	    			 response.sendRedirect(redirectURL);
	    		 }else if(ud.loginStaff(ui.getUser(), ui.getPassword()).equals(value.getROL_GESTIUNE())) {
	    			 String redirectURL = "view/gestiune.jsp";
	    			 response.sendRedirect(redirectURL);
	    		 }
	    	 }
	    	 break;
	     }
	}
}
