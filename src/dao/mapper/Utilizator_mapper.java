package dao.mapper;

import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import model.Utilizator;

public interface Utilizator_mapper {

	final String getByUser = "SELECT user,parola FROM utilizator where user = #{user}";
	final String getAllUsers = "SELECT * FROM utilizator";
	final String getUserById = "SELECT * FROM utilizator WHERE id_utilizator = #{id_utilizator}";
	final String deleteUserById = "DELETE from utilizator WHERE id_utilizator = #{id_utilizator}";
	final String insertUser = "INSERT INTO utilizator (user,parola,email,nume,prenume) VALUES (#{user},#{parola},#{email},#{nume},#{prenume})";
	final String updateUser = "UPDATE utilizator SET nume = #{nume} WHERE id_utilizator = #{id_utilizator}";
	final String insertRolUtilzator = "INSERT INTO rol_utilizator (roles_id_rol,users_id_utilizator) VALUES (#{roles_id_rol},#{users_id_utilizator})";
	final String getRolByUser = "SELECT r.nume FROM rol r, utilizator u, rol_utilizator ru where r.id_rol = ru.roles_id_rol "
			+ " and u.id_utilizator = ru.users_id_utilizator and u.user = #{user}";
	final String getClientByUser = "SELECT c.user_client FROM cont_client c, tip_cont_client t WHERE c.id_client = t.client_id_client AND c.user = #{user_client}";
	final String insertTip = "INSERT into tip_cont (tip,tip_id_utilizator) VALUES (#{tip},#{tip_id_utilizator})";
	
	//**********************************SELECT MAPPINGS******************************
	
	@Select(getRolByUser)
	@Results(value= {
			@Result(property = "nume", column = "nume")
	})
	
	String getRolByUser(String user);
	
	@Select(getByUser)
	@Results(value= {
			@Result(property = "user", column = "user"),
			@Result(property = "parola", column = "parola")
	})
	
	Utilizator getByUser(String user);
	
	@Select(getAllUsers)
	@Results(value= {
			@Result(property = "id_utilizator", column = "id_utilizator"),
		    @Result(property = "user", column = "user"),	    
			@Result(property = "email", column = "email"),
			@Result(property = "nume", column = "nume"),
			@Result(property = "prenume", column = "prenume")
	})
	
	Set<Utilizator> getAll();
	
	
	@Select(getUserById)
	   @Results(value = {
	      @Result(property = "id_utilizator", column = "id_utilizator"),
	      @Result(property = "nume", column = "nume")
	   })
	
	Utilizator getUserById(int id_utilizator);
	
	//**********************************UPDATE MAPPINGS******************************
	@Update(updateUser)
	   void update(Utilizator spec);

	//**********************************DELETE MAPPINGS******************************
	@Delete(deleteUserById)
	   void delete(int id_utilizator);
	  
	//**********************************INSERT MAPPINGS******************************
	@Insert(insertUser)
	   @Options(useGeneratedKeys = true, keyProperty = "id_utilizator", keyColumn = "id_utilizator")
	   void insert(Utilizator user);
	
	@Insert(insertRolUtilzator)
		void insertRolUtilizator(@Param("roles_id_rol") int roles_id_rol, @Param("users_id_utilizator") int users_id_utilizator);
	
	@Insert(insertTip)
	void insertTip(@Param("tip") String tip, @Param("tip_id_utilizator") int tip_id_utilizator);
}
