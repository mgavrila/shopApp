package dao.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import model.Produs;
import model.Utilizator;

public interface Produs_mapper {

	final String getAllProduct = "SELECT * FROM produs";
	final String getProductByName = "SELECT nume from produs where nume = #{nume}";
	final String getProductById = "SELECT * FROM produs WHERE id_prod = #{id_prod}";
	final String insertProduct = "INSERT into produs (id_categ,pret,stoc,garantie,descriere_scurta,descriere_lunga,nume)"
			+ " VALUES(#{id_categ}, #{pret}, #{stoc}, #{garantie}, #{descriere_scurta}, #{descriere_lunga}, #{nume})";

	final String getProductByCategory = "SELECT * FROM produs p LEFT JOIN categorie c ON p.id_categ = c.id_categ WHERE c.nume = #{nume} ";

	
	//*********************************SELECT MAPPERS**********************************************//
	
	@Select(getProductByCategory)
	@Results(value= {
			@Result(property = "nume", column = "nume")
	})
	
	List<Produs> getProductByCategory(String categorie);
	
	@Select(getAllProduct)
	@Results(value= {
			@Result(property = "nume", column = "nume"),
			@Result(property = "pret", column = "pret"),
			@Result(property = "garantie", column = "garantie"),
			@Result(property = "descriere_scurta", column = "descriere_scurta"),
			@Result(property = "descriere_lunga", column = "descriere_lunga"),
			@Result(property = "nume", column = "nume")
	})
	
	Set<Produs> getAllProducts();

	@Select(getProductByName)
	@Results(value= {
			@Result(property = "nume", column = "nume"),
			@Result(property = "pret", column = "pret"),
			@Result(property = "garantie", column = "garantie"),
			@Result(property = "descriere_scurta", column = "descriere_scurta"),
			@Result(property = "descriere_lunga", column = "descriere_lunga"),
			@Result(property = "nume", column = "nume")
	})
	
	Set<Produs> getProductByName();

	@Select(getProductById)
	@Results(value= {
			@Result(property = "nume", column = "nume"),
			@Result(property = "pret", column = "pret"),
			@Result(property = "garantie", column = "garantie"),
			@Result(property = "descriere_scurta", column = "descriere_scurta"),
			@Result(property = "descriere_lunga", column = "descriere_lunga"),
			@Result(property = "nume", column = "nume")
	})
	
	Set<Produs> getProductById();
	
	
	//*********************************INSERT MAPPERS**********************************************//
	@Insert(insertProduct)
	@Options(useGeneratedKeys = true, keyProperty = "id_prod", keyColumn = "id_prod")
		void insertProduct(Produs prod);
	
}
