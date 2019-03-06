package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dao.mapper.Specificatie_mapper;
import model.Specificatie;

/**
 * Servlet implementation class SpecificatieServlet
 */
@WebServlet("/SpecificatieServlet")
public class SpecificatieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private List<Specificatie> ls;
    /**
     * @throws IOException 
     * @see HttpServlet#HttpServlet()
     */
    public SpecificatieServlet() throws IOException {
        super();
        // TODO Auto-generated constructor stub
      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		FileInputStream fis =
              new FileInputStream(this.getServletConfig().getServletContext().getRealPath("/config.xml"));
              InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader reader = new BufferedReader(isr);
       // Reader reader = Resources.getResourceAsReader(isr);
        
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);		
	    SqlSession session = sqlSessionFactory.openSession();
	    session.getConfiguration().addMapper(Specificatie_mapper.class);
	      
	    Specificatie_mapper mapper = session.getMapper(Specificatie_mapper.class);
		
	    Specificatie spec = new Specificatie("testare5");
	  //    mapper.insert(spec);
	 //     mapper.delete(5);
	    ls = mapper.getAll();
	 //   System.out.println(mapper.getAll());
	     
	//     Specificatie spec2 = mapper.getById(6);
	 //    spec2.setNume("testareeee");
	//     mapper.update(spec2);
	//     System.out.println("record went in");
	     session.commit();
	     session.close();
		
		response.setContentType("text/html");

	      // Actual logic goes here.
	      PrintWriter out = response.getWriter();
	      out.println("<h1>" + ls + "</h1>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
