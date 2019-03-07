package jaxb;
import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.logging.log4j.core.util.StringBuilderWriter;

import dao.GestiuneDao;
import main.ListaProduse;
public class ProductList {
	
	
	public ProductList() {};
	
	public String generateXML(ListaProduse produse) {
		File file = new File("C:\\Users\\mihai.gavrila\\product_list.xml");
		StringBuilderWriter stringWriter = new StringBuilderWriter();
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext .newInstance(ListaProduse.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(produse, stringWriter);
		//	jaxbMarshaller.marshal(produse, System.out);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String writer = stringWriter.toString();
		System.out.println(writer);
		return writer;
	}
	
	
	
	public static void main(String[] args) {
		ProductList pl = new ProductList();
		GestiuneDao gd = new GestiuneDao();
		ListaProduse lp = new ListaProduse();
		lp.setProduse(gd.getProductByCategory("d0111"));
		pl.generateXML(lp);			
	}
}

