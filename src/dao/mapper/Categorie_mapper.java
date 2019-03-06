package dao.mapper;

import org.apache.ibatis.annotations.Results;

import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import model.Categorie;
import model.Produs;

public interface Categorie_mapper {

	final String getCategoryByName = "SELECT * FROM categorie where nume = #{nume}";
	final String insertCategory = "INSERT INTO categorie (nume) VALUES (#{nume})";
	final String insertParentCategory = "INSERT INTO categorie (nume, id_parinte) VALUES (#{nume},#{id_parinte})";
	final String getParentCategory = "SELECT * FROM categorie where id_parinte is null";
	final String getParentCategoryByName = "SELECT * FROM categorie where id_parinte is null and nume = #{nume}";
	final String getParentId = "SELECT p.id_parinte FROM categorie c, categorie p WHERE c.id_categ = p.id_parinte and p.nume = #{nume}";
	final String getCategories = "SELECT * FROM categorie where id_parinte is not null";
	final String updateCategoryWithParent = "UPDATE categorie set nume = #{nume}, id_parinte = #{id_parinte} WHERE id_categ = #{id_categ}";
	final String updateCategory = "UPDATE categorie set nume = #{nume} where id_categ= #{id_categ}";
	final String deleteCategory = "DELETE FROM categorie WHERE nume= #{nume}";

	

	//***************************SELECT MAPPINGS******************************************//
	
	@Select(getCategories)
	@Results(value= {
			@Result(property="id_categ", column="id_categ"),
			@Result(property="nume", column="nume"),
			@Result(property="id_parinte", column= "id_parinte")
	})
	
	Set<Categorie> getCategories();
		
	
	@Select(getParentCategoryByName)
	@Results(value= {
			@Result(property="id_categ", column="id_categ"),
			@Result(property="nume", column= "nume")
	})
	
	Categorie getParentCategoryByName(String nume);
	
	@Select(getParentId)
	@Results(value= {
			@Result(property="id_parinte", column="id_parinte")
	})
	Categorie getParentId(String nume);
	
	@Select(getParentCategory)
	@Results(value= {
			@Result(property="id_categ", column="id_categ"),
			@Result(property="nume", column = "nume")	
	})
	Set<Categorie> getParentCategory();
	
	@Select(getCategoryByName)
	@Results(value= {
			@Result(property="id_categ", column="id_categ"),
			@Result(property="nume", column="nume"),
			@Result(property="id_parinte", column="id_parinte")
	})
	Categorie getCategoryByName(String nume);
	
	
	
	//***************************INSERT MAPPINGS******************************************//
	@Insert(insertParentCategory)
	    void insertParentCategory(@Param("nume") String nume, @Param("id_parinte") int id_parinte);
	
	@Insert(insertCategory)
	@Options(useGeneratedKeys = true, keyProperty = "id_categ", keyColumn = "id_categ")
		void insertCategory(Categorie categ);
	
	
	//***************************DELETE MAPPINGS******************************************//
	
	@Delete(deleteCategory)
	void deleteCategory(String categ);
	
	

	//***************************UPDATE MAPPINGS******************************************//
	@Update(updateCategory)	
	void updateCategory(Categorie c);
	
	@Update(updateCategoryWithParent)
	void updateCategoryWithParent(Categorie c);
}
