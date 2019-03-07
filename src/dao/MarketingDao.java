package dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.ServletConfig;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dao.mapper.Categorie_mapper;
import dao.mapper.Produs_mapper;
import model.Produs;

public class MarketingDao {
	
	private SqlSession session_categ;
	private SqlSession session_prod;
	private Categorie_mapper mapper_categ;
	private Produs_mapper mapper_prod;
	
	public MarketingDao() {}
	
	public MarketingDao(ServletConfig servletConfig) {
		
		try {
			FileInputStream fis = new FileInputStream(servletConfig.getServletContext().getRealPath("/config.xml"));
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader reader = new BufferedReader(isr);
			
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);	

			SqlSession session_categ = sqlSessionFactory.openSession();
			SqlSession session_prod = sqlSessionFactory.openSession();

			session_categ.getConfiguration().addMapper(Categorie_mapper.class);
			session_prod.getConfiguration().addMapper(Produs_mapper.class);
			
			setSession_categ(session_categ);
			setSession_prod(session_prod);
			
			Categorie_mapper mapper_categ = session_categ.getMapper(Categorie_mapper.class);
			Produs_mapper mapper_prod = session_prod.getMapper(Produs_mapper.class);
			
			setMapper_categ(mapper_categ);
			setMapper_prod(mapper_prod);
					
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public SqlSession getSession_categ() {
		return session_categ;
	}
	public void setSession_categ(SqlSession session_categ) {
		this.session_categ = session_categ;
	}
	public SqlSession getSession_prod() {
		return session_prod;
	}
	public void setSession_prod(SqlSession session_prod) {
		this.session_prod = session_prod;
	}
	public Categorie_mapper getMapper_categ() {
		return mapper_categ;
	}
	public void setMapper_categ(Categorie_mapper mapper_categ) {
		this.mapper_categ = mapper_categ;
	}
	public Produs_mapper getMapper_prod() {
		return mapper_prod;
	}
	public void setMapper_prod(Produs_mapper mapper_prod) {
		this.mapper_prod = mapper_prod;
	}

	public List<Produs> getProductByCategory(String category) {
		 List<Produs> p =  getMapper_prod().getProductByCategory(category);
		 return p;
	}
	
	
	
}
