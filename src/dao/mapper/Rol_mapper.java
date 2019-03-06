package dao.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import model.Rol;

public interface Rol_mapper {

	final String getAllRoles = "SELECT * FROM rol";
	final String getRoles = "SELECT nume from rol";
	final String getRolById = "SELECT * FROM rol WHERE id_rol = #{id_rol}";
	//final String deleteRolById = "DELETE from rol WHERE id_rol = #{id_rol}";
	final String insertRol = "INSERT INTO rol (nume) VALUES (#{nume})";
	final String updateRol = "UPDATE rol SET nume = #{nume} WHERE id_rol = #{id_rol}";
	final String getRolByName = "SELECT nume from rol";
	final String getRolId = "SELECT id_rol from rol where nume = #{nume}";
	final String deleteRolByName = "DELETE from rol WHERE nume = #{nume}";
	final String insertRolUtilzator = "INSERT INTO rol_utilizator (roles_id_rol,users_id_utilizator) VALUES (#{roles_id_rol},#{users_id_utilizator})";
	
	
	
	//*****************SELECT MAPPINGS *****************************//
	
	
	@Select(getRolId)
	@Results(value= {
			@Result(property = "nume", column = "nume")
	})
	
	Rol getRolId(String rol);
	
	
	@Select(getRolByName)
	@Results(value= {
			@Result(property = "nume", column = "nume")
	})
	
	List<Rol> getRolByName(String nume_rol);
	
	@Select(getAllRoles)
	@Results(value= {
			@Result(property = "id_rol", column = "id_rol"),
		    @Result(property = "nume", column = "nume")	    
	})
	
	
	
	List<Rol> getAll();
	
	@Select(getRoles)
	@Results(value= {
			@Result(property = "nume", column = "nume")
	})
	
	Set<Rol> getRoles();
	
	
	@Select(getRolById)
	   @Results(value = {
	      @Result(property = "id_rol", column = "id_rol"),
	      @Result(property = "nume", column = "nume")
	   })
	
	Rol getById(int id_rol);
	
	//*****************UPDATE MAPPINGS *****************************//
	@Update(updateRol)
	   void update(Rol rol);

	
	//*****************DELETE MAPPINGS *****************************//
	
	
	@Delete(deleteRolByName)
	   void deleteRol(String nume_rol);
	
	//*****************INSERT MAPPINGS *****************************//
	@Insert(insertRol)
	   @Options(useGeneratedKeys = true, keyProperty = "id_rol")
	   void insert(Rol rol);
	
	@Insert(insertRolUtilzator)
	void insertRolUtilizator(@Param("roles_id_rol") int roles_id_rol, @Param("users_id_utilizator") int users_id_utilizator);
}
