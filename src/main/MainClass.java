package main;
import java.io.IOException;
import java.io.Reader;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dao.mapper.Categorie_mapper;
import dao.mapper.Produs_mapper;
import model.Categorie;
import model.Cont_client;
import model.Produs;
import model.Rol;
import model.Utilizator;
import utils.EncryptService;
import utils.Function;
public class MainClass {



	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		
		
		
		//UtilizatorDAO utilizatorDAO = new UtilizatorDAO(MyBatisConnectionFactory.getSqlSessionFactory());
		//Utilizator user = new Utilizator();

		 Reader reader = Resources.getResourceAsReader("config.xml");
	     SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);		

	     SqlSession session = sqlSessionFactory.openSession();
	     SqlSession session_produs = sqlSessionFactory.openSession();
	     
	     session.getConfiguration().addMapper(Categorie_mapper.class);
	     session_produs.getConfiguration().addMapper(Produs_mapper.class);
	     
	     Categorie_mapper mapper_categorie = session.getMapper(Categorie_mapper.class); 		
	     Produs_mapper mapper_produs = session_produs.getMapper(Produs_mapper.class);
	     
	     Categorie c = mapper_categorie.getParentCategoryByName("electrocasnice");
	     System.out.println(c.toString());
			
/*
			Function f = new Function();
			Categorie c = mapper_categorie.getCategoryByName("lol");
			int id_cat = c.getId_categ();
	
			Produs produs = new Produs(9,2.3f,4,5,"asda","a","nume");
			
			session.commit();
			String pret = "123";
			String stoc = "50";
			String garantie = "32";
			String descriere_lunga = "asdafdafnfdsafndsfisanfdnfiuafbaidfiuas";
			String descriere_scurta = f.getShortDescription(descriere_lunga);
			String nume = "s10";
			Produs prod = new Produs(id_cat,Float.parseFloat(pret),f.convertStringToInt(stoc),f.convertStringToInt(garantie),descriere_scurta,descriere_lunga,nume);
			
			System.out.println("id_categ: "+ prod.getId_categ()+
					"\n pret: "+prod.getPret()+
					"\n stoc: "+prod.getStoc()+
					"\n garantie: "+prod.getGarantie()+
					"\n descriere_lunga: "+prod.getDescriere_lunga()+
					"\n descriere_scurta: "+prod.getDescriere_scurta()+
					"\n nume: "+prod.getNume());
			
			if(prod != null) {
				mapper_produs.insertProduct(prod);
				session_produs.commit();
				session_produs.close();
				session.close();
				System.out.println("done");
			}else {
				System.out.println("eroare insert");
			}
		*/	
	}
	
	
}
