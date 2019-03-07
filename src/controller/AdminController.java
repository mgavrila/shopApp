package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RolDao;
import dao.RolUtilizatorDao;
import info.UserInfo;
import model.Rol;
import model.Utilizator;
import utils.EncryptService;
import utils.Function;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		RolDao rd = new RolDao(this.getServletConfig());
		String action = request.getParameter("action");

		UserInfo ui = new UserInfo(request.getParameter("username"),
								   request.getParameter("password"),
								   request.getParameter("email"),
								   request.getParameter("nume"), 
								   request.getParameter("prenume"),
								   request.getParameter("rol"), 
								   request.getParameter("rol_name"));
		Rol newRol = new Rol(ui.getRol_name());
		Function f = new Function();
		switch (action) {

		case "Add role":
			if (ui.getRol_name() != "") {
				if (f.existRol(ui.getRol_name()) == true) {
					System.out.println("rol existent");
				} else {
					rd.insertRol(newRol);
					String redirectURL = "view/admin.jsp";
    				response.sendRedirect(redirectURL);
				}
			} else {
				System.out.println("campul rol nu poate sa fie gol");
			}
			break;

		case "Remove role":
			if (ui.getRol_name() != "") {
				 rd.deleteRol(ui.getRol_name());
				 String redirectURL = "view/admin.jsp";
 				 response.sendRedirect(redirectURL);
				System.out.println("rol removed: " + ui.getRol_name());
			} else {
				System.out.println("remove rol error");
			}
			break;

		case "Create account":
			if (f.checkFields(ui.getUser(), ui.getPassword(), ui.getEmail(), ui.getNume(), ui.getPrenume()) == true) {
				if (f.passwordValidation(ui.getUser(), ui.getPassword()) == true) {
					if (f.isValidEmailAddress(ui.getEmail()) == true) {
						try {
							Utilizator user = new Utilizator(ui.getUser(),EncryptService.getHashOfString(ui.getPassword()),ui.getEmail(),ui.getNume(),ui.getPrenume());
							RolUtilizatorDao rud = new RolUtilizatorDao(this.getServletConfig());
							rud.insertStaffAccount(ui.getRol(), user);	
							String redirectURL = "view/admin.jsp";
		    				response.sendRedirect(redirectURL);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						System.out.println("email invalid");
					}
				} else {
					System.out.println("password invalid");
				}
			} else {
				System.out.println("invalid");
			}
			break;
		}

	}

}
