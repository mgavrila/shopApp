package utils;
import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dao.mapper.Rol_mapper;
import dao.mapper.Utilizator_mapper;
import model.Cont_client;
import model.Rol;
import model.Utilizator;

import java.util.List;

public class Function {
	
	Reader reader = Resources.getResourceAsReader("config.xml");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);		
    SqlSession session = sqlSessionFactory.openSession();
    
	public Function() throws IOException {
		
	    
	    
	}
//	static int id = 0;
	
/**
 * Method for validating if all the fields are filled.	
 * @param user = username of the account
 * @param password = password of the account
 * @param email = email of the account
 * @param nume = your lastname
 * @param prenume = your firstname
 * @return
 */
	
	public boolean checkFields(String user, String password, String email, String nume, String prenume) {
		if((user == null || user.isEmpty())
				&& (password == null || password.isEmpty())
				&& (email == null || email.isEmpty())
				&& (nume == null || nume.isEmpty())
				&& (prenume == null || prenume.isEmpty())) {
			return false;
		}
				
		return true;
	}
	
	
	
	public boolean stringIsNullOrEmpty(String s){
		if(s == null){
			return true;
		}else{
			if(s.equals("")){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method for checking if the desired password if valid
	 * @param user = the username is necessary for checking if it's the same with the password
	 * @param password = the password for going through all the verifications
	 * @return
	 */
	public boolean passwordValidation(String user, String password)
    {
            if (password.length() > 15 || password.length() < 8)
            {
                    System.out.println("Password should be less than 15 and more than 8 characters in length.");
                    return false;
            }
            if (password.indexOf(user) > -1)
            {
                    System.out.println("Password Should not be same as user name");
                    return false;
            }
            String upperCaseChars = "(.*[A-Z].*)";
            if (!password.matches(upperCaseChars ))
            {
                    System.out.println("Password should contain atleast one upper case alphabet");
                    return false;
            }
            String lowerCaseChars = "(.*[a-z].*)";
            if (!password.matches(lowerCaseChars ))
            {
                    System.out.println("Password should contain atleast one lower case alphabet");
                    return false;
            }
            String numbers = "(.*[0-9].*)";
            if (!password.matches(numbers ))
            {
                    System.out.println("Password should contain atleast one number.");
                    return false;
            }
            String specialChars = "(.*[,~,!,@,#,$,%,^,&,*,(,),-,_,=,+,[,{,],},|,;,:,<,>,/,?].*$)";
            if (!password.matches(specialChars ))
            {
                    System.out.println("Password should contain atleast one special character");
                    return false;
            }
          
            return true;
    }
	
	/**
	 * Method for validating the email
	 * @param email
	 * @return
	 */
	public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
 }
	
	
	public boolean existRol(String rol) {
		session.getConfiguration().addMapper(Rol_mapper.class);
		Rol_mapper mapper = session.getMapper(Rol_mapper.class);
		List<Rol> lr = mapper.getRolByName(rol);
		boolean ok = false;
		for(Rol r : lr) {
			if(r.getNume().equals(rol)) {
				ok = true;
			}
		}
			return ok;
	}	
	
	public boolean existUser(String user) {
		session.getConfiguration().addMapper(Utilizator.class);
		Utilizator_mapper mapper = session.getMapper(Utilizator_mapper.class);
		return true;
	}
	
	
	public int convertStringToInt(String str) {
		int number = 0;
		try {
			number = Integer.parseInt(str);
		}
		catch (NumberFormatException e)
		{
			System.out.println("error converting string to int");
			e.printStackTrace();
		}
		return number;
	}
	
	public String getShortDescription(String str) {
		String sub = "";
		for(int i = 0; i < str.length()/2; i++ ) {
			sub += str.charAt(i);
		}		
		sub += "...";
		return sub;
	}
	
	
	
}