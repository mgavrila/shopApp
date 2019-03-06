package dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import model.Specificatie;

public interface Specificatie_mapper {

	final String getAll = "SELECT * FROM Specificatie";
	final String getById = "SELECT * FROM Specificatie WHERE id_specificatie = #{id_specificatie}";
	final String deleteById = "DELETE from Specificatie WHERE id_specificatie = #{id_specificatie}";
	final String insert = "INSERT INTO Specificatie (nume) VALUES (#{nume})";
	final String update = "UPDATE specificatie SET nume = #{nume} WHERE id_specificatie = #{id_specificatie}";
	
	
	@Select(getAll)
	@Results(value= {
			@Result(property = "id_specificatie", column = "id_specificatie"),
		    @Result(property = "nume", column = "nume")	    
	})
	
	List<Specificatie> getAll();
	
	
	@Select(getById)
	   @Results(value = {
	      @Result(property = "id_specificatie", column = "id_specificatie"),
	      @Result(property = "nume", column = "nume")
	   })
	
	Specificatie getById(int id_specificatie);
	
	@Update(update)
	   void update(Specificatie spec);

	@Delete(deleteById)
	   void delete(int id_specificatie);
	  
	@Insert(insert)
	   @Options(useGeneratedKeys = true, keyProperty = "id_specificatie")
	   void insert(Specificatie spec);
}
	