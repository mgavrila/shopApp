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

import model.Cont_client;

public interface Client_mapper {
	
	final String getClientByUser = "SELECT user_client,parola_client FROM cont_client where user_client = #{user_client}";
	final String getAllClients = "SELECT * FROM cont_client";
	final String getClientUserById = "SELECT * FROM cont_client WHERE id_client = #{id_client}";
	final String deleteClientUserById = "DELETE from cont_client WHERE id_client = #{id_client}";
	final String insertClient = "INSERT INTO cont_client (user_client,parola_client,email_client,nume_client,prenume_client) VALUES (#{user_client},#{parola_client},#{email_client},#{nume_client},#{prenume_client})";
	final String updateClient = "UPDATE utilizator SET nume = #{nume} WHERE id_utilizator = #{id_utilizator}";
	final String insertTipClient = "INSERT into tip_cont_client (tip_client,client_id_client) VALUES (#{tip_client},#{client_id_client})";

	
	//**********************************SELECT MAPPINGS******************************

		
		@Select(getClientByUser)
		@Results(value= {
				@Result(property = "user_client", column = "user_client"),
				@Result(property = "parola_client", column = "parola_client")
		})
		
		Cont_client getClientByUser(String user_client);
		
		@Select(getAllClients)
		@Results(value= {
				@Result(property = "id_client", column = "id_client"),
			    @Result(property = "user_client", column = "user_client"),	    
				@Result(property = "email_client", column = "email_client"),
				@Result(property = "nume_client", column = "nume_client"),
				@Result(property = "prenume_client", column = "prenume_client")
		})
		
		Set<Cont_client> getAllClients();
		
		
		@Select(getClientUserById)
		   @Results(value = {
		      @Result(property = "id_client", column = "id_client"),
		      @Result(property = "nume_client", column = "nume_client")
		   })
		
		Cont_client getClientUserById(int id_client);
		
		//**********************************UPDATE MAPPINGS******************************
		@Update(updateClient)
		   void update(Cont_client client);

		//**********************************DELETE MAPPINGS******************************
		@Delete(deleteClientUserById)
		   void delete(int id_client);
		  
		//**********************************INSERT MAPPINGS******************************
		@Insert(insertClient)
		   @Options(useGeneratedKeys = true, keyProperty = "id_client", keyColumn = "id_client")
		   void insertClient(Cont_client client);
		
		@Insert(insertTipClient)
		void insertTipClient(@Param("tip_client") String tip_client, @Param("client_id_client") int client_id_client);
	
	
}
