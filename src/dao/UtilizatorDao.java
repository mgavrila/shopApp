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

import dao.mapper.Utilizator_mapper;
import model.Rol;
import model.Utilizator;

public class UtilizatorDao implements Dao<Utilizator>{
	
	private List<Utilizator> users = new ArrayList<>();	
	private Utilizator_mapper mapper_user;
	private SqlSession session_user;
	
	public UtilizatorDao() {}
	
	
	public UtilizatorDao(ServletConfig servletConfig) {
		try { 
			FileInputStream fis;
			fis = new FileInputStream(servletConfig.getServletContext().getRealPath("/config.xml"));
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader reader = new BufferedReader(isr);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			SqlSession session_user = sqlSessionFactory.openSession();
			session_user.getConfiguration().addMapper(Utilizator_mapper.class);
			Utilizator_mapper mapper_user = session_user.getMapper(Utilizator_mapper.class);
			setMapper_user(mapper_user);
			setSession_user(session_user);
			
			
			
			
		} catch (FileNotFoundException e) {
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


	public SqlSession getSession_user() {
		return session_user;
	}


	public void setSession_user(SqlSession session_user) {
		this.session_user = session_user;
	}


	@Override
	public Optional<Utilizator> get(long id) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(users.get((int) id));
	}

	@Override
	public List<Utilizator> getAll() {
		// TODO Auto-generated method stub
		return users;
	}

	@Override
	public void save(Utilizator user) {
		// TODO Auto-generated method stub
		users.add(user);
		
	}

	@Override
	public void update(Utilizator user, String[] params) {
		// TODO Auto-generated method stub
		user.setUser(Objects.requireNonNull(
		          params[0], "Username cannot be null"));
		user.setEmail(Objects.requireNonNull(
		          params[1], "Email cannot be null"));
		users.add(user);
	}

	@Override
	public void delete(Utilizator user) {
		// TODO Auto-generated method stub
		users.remove(user);
		
	}
	
	public void insertUser(Utilizator user) {
		
		getMapper_user().insert(user);
		getSession_user().commit();
		getSession_user().close();
		
	}


	@Override
	public void getId(Utilizator user) {
		// TODO Auto-generated method stub
		
		
	}
	
	


}
