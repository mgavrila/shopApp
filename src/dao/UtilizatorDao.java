package dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dao.mapper.Client_mapper;
import dao.mapper.Rol_mapper;
import dao.mapper.Utilizator_mapper;
import model.Cont_client;
import model.Utilizator;
import utils.Constant;
import utils.EncryptService;
import utils.Function;

public class UtilizatorDao implements Dao<Utilizator>{
	
	private List<Utilizator> users = new ArrayList<>();	
	private Utilizator_mapper mapper_user;
	private SqlSession session_user;
	private Client_mapper mapper_client;
	private SqlSession session_client;
	
	public UtilizatorDao() {}
	
	
	public UtilizatorDao(ServletConfig servletConfig) {
		try { 
			FileInputStream fis;
			fis = new FileInputStream(servletConfig.getServletContext().getRealPath("/config.xml"));
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader reader = new BufferedReader(isr);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			SqlSession session_user = sqlSessionFactory.openSession();
			SqlSession session_client = sqlSessionFactory.openSession();
			SqlSession session_rol = sqlSessionFactory.openSession();
			
			session_user.getConfiguration().addMapper(Utilizator_mapper.class);
			session_client.getConfiguration().addMapper(Client_mapper.class);
			session_rol.getConfiguration().addMapper(Rol_mapper.class);
			
			Utilizator_mapper mapper_user = session_user.getMapper(Utilizator_mapper.class);
			Client_mapper mapper_client = session_client.getMapper(Client_mapper.class);
			Rol_mapper mapper_rol = session_rol.getMapper(Rol_mapper.class);
		
			setMapper_user(mapper_user);
			setSession_user(session_user);
			
			
			setMapper_client(mapper_client);
			setSession_client(session_client);
			
			
			
			
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


	
	
	public Client_mapper getMapper_client() {
		return mapper_client;
	}


	public void setMapper_client(Client_mapper mapper_client) {
		this.mapper_client = mapper_client;
	}


	public SqlSession getSession_client() {
		return session_client;
	}


	public void setSession_client(SqlSession session_client) {
		this.session_client = session_client;
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
	

	public boolean loginClient(String user, String password) {
		Cont_client client = getMapper_client().getClientByUser(user);
	    boolean ok = false;
	    if(client.getUser_client() != null){
				try {
					if(client.getParola_client().equals(EncryptService.getHashOfString(password))){
							getSession_client().close();	
							ok = true;
}
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    }
	    return ok;
}

	    
	public boolean registerClient(String user, String password, String email, String nume, String prenume) {
		boolean ok = false;
		try {
			Function f = new Function();
   		 if(f.checkFields(user, password, email, nume, prenume) == true){
			if(f.passwordValidation(user, password) == true){
				if(f.isValidEmailAddress(email) == true){
					try {
						Cont_client cont = new Cont_client(user,EncryptService.getHashOfString(password),email,nume,prenume);
						getMapper_client().insertClient(cont);
						getMapper_client().insertTipClient("active", cont.getId_client());
						getSession_client().commit();
						getSession_client().close();
						ok = true;
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}			
				}				
			}				
		}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ok;
		
	}

	public String loginStaff(String user, String password) {
		Utilizator us = mapper_user.getByUser(user);
		String result = "";
		Constant value = new Constant();
		 if(us.getUser() != null && us.getParola() != null){
					try {
						if(us.getParola().equals(EncryptService.getHashOfString(password))){
							if(getMapper_user().getRolByUser(user).equals(value.getROL_ADMINISTRATOR())){
								getSession_user().close();
								result = "administrator";
							}else if(getMapper_user().getRolByUser(user).equals(value.getROL_GESTIUNE())){
								result = "gestionar";
							}		
						}
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}			
		 }
		 return result;
	}

}


