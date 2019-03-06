package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dao.mapper.Client_mapper;
import dao.mapper.Rol_mapper;
import dao.mapper.Utilizator_mapper;
import info.UserInfo;
import model.Cont_client;
import model.Utilizator;
import utils.EncryptService;
import utils.Function;

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
		
		 FileInputStream fis = new FileInputStream(this.getServletConfig().getServletContext().getRealPath("/config.xml"));
		 InputStreamReader isr = new InputStreamReader(fis);
		 BufferedReader reader = new BufferedReader(isr);

		 SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);		
		 SqlSession session_client = sqlSessionFactory.openSession();

		 session_client.getConfiguration().addMapper(Client_mapper.class);
	
		 Client_mapper mapper_client = session_client.getMapper(Client_mapper.class);
		 Function f = new Function();
		
		 response.setContentType("text/html");  
	     PrintWriter out=response.getWriter();  
	          
	     String controller = request.getParameter("controller");
	     String action = request.getParameter("action");
	   	     
	     
	     switch(controller) {
	     case "client":
	    	 if(action.equals("login")) {
	    		  UserInfo ui = new UserInfo(request.getParameter("username"),request.getParameter("password"));
	    		  Cont_client client = mapper_client.getClientByUser(ui.getUser());
	    		  Utilizator user = new Utilizator(ui.getUser(),ui.getPassword());  
	    		  request.setAttribute("user",user);  
	    		 if(client.getUser_client() != null){
	    			try {
	    				if(client.getParola_client().equals(EncryptService.getHashOfString(ui.getPassword()))){
	    				String redirectURL = "view/produs.jsp";
	    				response.sendRedirect(redirectURL);	
	    				session_client.close();
	    				} 		
	    			}catch (NoSuchAlgorithmException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    		 }
	    	 }else if(action.equals("register")) {
	    		 UserInfo ui = new UserInfo(request.getParameter("username"),
	    				 					request.getParameter("password"),
	    				 					request.getParameter("email"),
	    				 					request.getParameter("nume"), 
	    				 					request.getParameter("prenume")
	    				 					);   		 
	    		 if(f.checkFields(ui.getUser(), ui.getPassword(), ui.getEmail(), ui.getNume(), ui.getPrenume()) == true){
	    				if(f.passwordValidation(ui.getUser(), ui.getPassword()) == true){
	    					if(f.isValidEmailAddress(ui.getEmail()) == true){
	    						try {
	    							Cont_client cont = new Cont_client(ui.getUser(),EncryptService.getHashOfString(ui.getPassword()),ui.getEmail(),ui.getNume(),ui.getPrenume());
	    							mapper_client.insertClient(cont);
		    						mapper_client.insertTipClient("active", cont.getId_client());
		    						session_client.commit();
		    						session_client.close();
		    						out.print("user registered");
		    						String redirectURL = "view/login_client.jsp";
		    					    response.sendRedirect(redirectURL);	 
	    						} catch (NoSuchAlgorithmException e) {
	    							// TODO Auto-generated catch block
	    							e.printStackTrace();
	    						} 					
	    					}else{
	    						out.print("email invalid");
	    					}
	    				}else{
	    					out.print("password invalid");		
	    				}	
	    			}else{
	    				out.print("complete all fields");
	    			}
	    	 }
	    	 break;
	     case "staff":
	    	 if(action.equals("login")) {
	    		 UserInfo ui = new UserInfo(request.getParameter("username"),request.getParameter("password"));
	    		 SqlSession session_user = sqlSessionFactory.openSession();
	    		 SqlSession session_rol = sqlSessionFactory.openSession();
	    		 session_user.getConfiguration().addMapper(Utilizator_mapper.class);
	    		 session_rol.getConfiguration().addMapper(Rol_mapper.class);
	    		 Utilizator_mapper mapper_user = session_user.getMapper(Utilizator_mapper.class);
	    		 Utilizator user = mapper_user.getByUser(ui.getUser());
	    		 if(user.getUser() != null && user.getParola() != null){
	    				try {
	    					if(user.getParola().equals(EncryptService.getHashOfString(ui.getPassword()))){
	    						if(mapper_user.getRolByUser(ui.getUser()).equals("administrator")){
	    							String redirectURL = "view/admin.jsp";
	    							response.sendRedirect(redirectURL);
	    							session_user.close();
	    						}else if(mapper_user.getRolByUser(ui.getUser()).equals("gestionar")){
	    							String redirectURL = "view/gestiune.jsp";
	    							response.sendRedirect(redirectURL);
	    						}		
	    					}
	    					} catch (NoSuchAlgorithmException e) {
	    						// TODO Auto-generated catch block
	    						e.printStackTrace();
	    					}
	    			}
	    	 }
	     }
	}
}
