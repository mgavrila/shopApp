package dao;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletConfig;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dao.mapper.Categorie_mapper;
import dao.mapper.Produs_mapper;
import model.Categorie;
import model.Produs;
import utils.Function;
public class GestiuneDao {
	
	private SqlSession session_categ;
	private SqlSession session_prod;
	private Categorie_mapper mapper_categ;
	private Produs_mapper mapper_prod;
	
	public GestiuneDao() {}
	
	public GestiuneDao(ServletConfig servletConfig) {
		
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
	
	public void insertCategory(String category, String parent_category) {
		if(category != null && parent_category == null){
			Categorie cat  = new Categorie(category);
			getMapper_categ().insertCategory(cat);
			getSession_categ().commit();
			getSession_categ().close();
		}else if(category != null && parent_category != null) {
			Categorie parent = getMapper_categ().getParentCategoryByName(parent_category);
			getMapper_categ().insertParentCategory(category, parent.getId_categ());
			getSession_categ().commit();
			getSession_categ().close();
		}else {
			System.out.println("eroare");
		}
	}
	
	public void updateCategory(String category,String newName,String parent_category) {
		
		if(newName != null && category != null && parent_category == null) {
			Categorie cat = mapper_categ.getCategoryByName(category);
			cat.setNume(newName);
			getMapper_categ().updateCategory(cat);
			getSession_categ().commit();
			getSession_categ().close();
		}else if(newName != null && category != null && parent_category != null) {			
			Categorie cat = getMapper_categ().getCategoryByName(category);
			cat.setNume(newName);
			Categorie parent = mapper_categ.getCategoryByName(parent_category);
			cat.setId_parinte(parent.getId_categ());	
			getMapper_categ().updateCategoryWithParent(cat);
			getSession_categ().commit();
			getSession_categ().close();
		}else if(newName != null && category == null && parent_category != null) {
			Categorie cat = getMapper_categ().getCategoryByName(parent_category);
			cat.setNume(newName);
			getMapper_categ().updateCategory(cat);
			getSession_categ().commit();
			getSession_categ().close();
		}
	}
	
	public void deleteCategory(String category, String parent_category) {

		if(category != null && parent_category == null){
			getMapper_categ().deleteCategory(category);
			getSession_categ().commit();
			getSession_categ().close();
		}else if(category != null && parent_category != null) {
			getMapper_categ().deleteCategory(parent_category);
			getSession_categ().commit();
			getSession_categ().close();
		}
	}

	public void insertProduct(String product_name, String product_price, String product_category, String quantity, String guarantee, String product_description) {
		if(product_name != null	&& product_price != null && product_category != null && quantity != null && guarantee != null && product_description != null){
			Categorie cat = mapper_categ.getCategoryByName(product_category);
			try {
				Function f = new Function();
				int id_cat = cat.getId_categ();
				getSession_categ().commit();
				Produs prod = new Produs(id_cat,
										 Float.parseFloat(product_price),
										 f.convertStringToInt(quantity),
										 f.convertStringToInt(guarantee),
										 f.getShortDescription(product_description),
										 product_description,
										 product_name);	
				getMapper_prod().insertProduct(prod);
				getSession_prod().commit();
				getSession_prod().close();
				getSession_categ().close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
	}
}
