package dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletConfig;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dao.mapper.Rol_mapper;
import dao.mapper.Utilizator_mapper;
import model.Rol;
import model.Utilizator;

public class RolUtilizatorDao {
	
	private Utilizator_mapper mapper_user;
	private Rol_mapper mapper_rol;
	private SqlSession session_user;
	private SqlSession session_rol;	
	
	public RolUtilizatorDao() {}
	
	public RolUtilizatorDao(ServletConfig servletConfig) {
	
		try {
			FileInputStream  fis = new FileInputStream(servletConfig.getServletContext().getRealPath("/config.xml"));
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader reader = new BufferedReader(isr);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			
			SqlSession session_user = sqlSessionFactory.openSession();
			SqlSession session_rol = sqlSessionFactory.openSession();
			
			session_rol.getConfiguration().addMapper(Rol_mapper.class);
			session_user.getConfiguration().addMapper(Utilizator_mapper.class);
			
			Rol_mapper mapper_rol = session_rol.getMapper(Rol_mapper.class);
			Utilizator_mapper mapper_user = session_user.getMapper(Utilizator_mapper.class);
			
			setMapper_rol(mapper_rol);
			setSession_rol(session_rol);
			
			setMapper_user(mapper_user);
			setSession_user(session_user);
			
		} catch (Exception  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Utilizator_mapper getMapper_user() {
		return mapper_user;
	}

	public void setMapper_user(Utilizator_mapper mapper_user) {
		this.mapper_user = mapper_user;
	}

	public Rol_mapper getMapper_rol() {
		return mapper_rol;
	}

	public void setMapper_rol(Rol_mapper mapper_rol) {
		this.mapper_rol = mapper_rol;
	}

	public SqlSession getSession_user() {
		return session_user;
	}

	public void setSession_user(SqlSession session_user) {
		this.session_user = session_user;
	}

	public SqlSession getSession_rol() {
		return session_rol;
	}

	public void setSession_rol(SqlSession session_rol) {
		this.session_rol = session_rol;
	}
	
	
	public void insertStaffAccount(String rol,Utilizator user) {
		// TODO Auto-generated method stub
		Rol r = getMapper_rol().getRolId(rol);
		getSession_rol().commit();
		getSession_rol().close();
		System.out.println(r.toString());
		getMapper_user().insert(user);
		System.out.println("mapper user " +getMapper_user());
		getMapper_user().insertRolUtilizator(r.getId_rol(), user.getId_utilizator());
		getMapper_user().insertTip("active",user.getId_utilizator());
		getSession_user().commit();
		getSession_user().close();						
	}

}
