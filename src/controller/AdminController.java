package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dao.RolDao;
import dao.UtilizatorDao;
import dao.mapper.Rol_mapper;
import dao.mapper.Utilizator_mapper;
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

		UtilizatorDao ud = new UtilizatorDao(this.getServletConfig());
		RolDao rd = new RolDao(this.getServletConfig());
		System.out.println(rd);
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
				System.out.println(ui.getRol_name());
				if (f.existRol(ui.getRol_name()) == true) {
					System.out.println("rol existent");
				} else {
					rd.insertRol(newRol);
				}
			} else {
				System.out.println("campul rol nu poate sa fie gol");
			}
			break;

		case "Remove role":
			if (ui.getRol_name() != "") {
				 rd.deleteRol(ui.getRol_name());
				System.out.println("rol removed: " + ui.getRol_name());
			} else {
				System.out.println("remove rol error");
			}
			break;

		case "Create account":
			if (f.checkFields(ui.getUser(), ui.getPassword(), ui.getEmail(), ui.getNume(), ui.getPrenume()) == true) {
				if (f.passwordValidation(ui.getUser(), ui.getPassword()) == true) {
					if (f.isValidEmailAddress(ui.getEmail()) == true) {
						Utilizator user;
						try {
							user = new Utilizator(ui.getUser(),EncryptService.getHashOfString(ui.getPassword()),ui.getEmail(),ui.getNume(),ui.getPrenume());
//							Rol r = mapper_rol.getRolId(ui.getRol());
//							session_rol.commit();
//							session_rol.close();
//							mapper_user.insert(user);
//							mapper_user.insertRolUtilizator(r.getId_rol(), user.getId_utilizator());
//							mapper_user.insertTip("active",user.getId_utilizator());
//							session_user.commit();
//							session_user.close();				
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
