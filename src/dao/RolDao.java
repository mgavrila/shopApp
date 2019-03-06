package dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.ServletConfig;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dao.mapper.Rol_mapper;
import model.Rol;
import model.Utilizator;

public class RolDao implements Dao<Rol>{
	
	private List<Rol> roles = new ArrayList<>();	
	private Rol_mapper mapper_rol;
	private SqlSession session_rol;
	
	public RolDao() {}
	
	public RolDao(ServletConfig servletConfig) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(servletConfig.getServletContext().getRealPath("/config.xml"));
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader reader = new BufferedReader(isr);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			SqlSession session_rol = sqlSessionFactory.openSession();
			session_rol.getConfiguration().addMapper(Rol_mapper.class);
			Rol_mapper mapper_rol = session_rol.getMapper(Rol_mapper.class);
			setMapper_rol(mapper_rol);
			setSession_rol(session_rol);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	
	
	public Rol_mapper getMapper_rol() {
		return mapper_rol;
	}

	public void setMapper_rol(Rol_mapper mapper_rol) {
		this.mapper_rol = mapper_rol;
	}

	public SqlSession getSession_rol() {
		return session_rol;
	}

	public void setSession_rol(SqlSession session_rol) {
		this.session_rol = session_rol;
	}

	@Override
	public Optional<Rol> get(long id) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(roles.get((int) id));
	}

	@Override
	public List<Rol> getAll() {
		// TODO Auto-generated method stub
		return roles;
	}

	@Override
	public void save(Rol rol) {
		// TODO Auto-generated method stub
		roles.add(rol);
	}

	@Override
	public void update(Rol rol, String[] params) {
		// TODO Auto-generated method stub
		rol.setNume((Objects.requireNonNull(
		          params[0], "Username cannot be null")));	
		roles.add(rol);
	}

	@Override
	public void delete(Rol rol) {
		// TODO Auto-generated method stub
		roles.remove(rol);
	}
	
	public void insertRol(Rol newRol) {
		getMapper_rol().insert(newRol);
		getSession_rol().commit();
		getSession_rol().close();
	}
	
	public void deleteRol(String rol) {
		getMapper_rol().deleteRol(rol);
		getSession_rol().commit();
		getSession_rol().close();
	}

}
