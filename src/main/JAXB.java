package main;
import java.io.File;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import model.Comanda;


public class JAXB {

	
	public static void main(String[] args) {
		Date firstDate1 = new Date(2019,06,20);
		Comanda comanda = new Comanda(1,2,firstDate1);
		
		try {

			File file = new File("C:\\Users\\mihai.gavrila\\file2.xml");
			JAXBContext  jaxbContext = JAXBContext .newInstance(Comanda.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(comanda, file);
			jaxbMarshaller.marshal(comanda, System.out);

		      } catch (JAXBException e) {
			e.printStackTrace();
		      }
//		
//		 try {
//
//				File file = new File("C:\\Users\\mihai.gavrila\\file.xml");
//				JAXBContext jaxbContext = JAXBContext.newInstance(Comanda.class);
//
//				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//				Comanda comanda = (Comanda) jaxbUnmarshaller.unmarshal(file);
//				System.out.println(comanda.toString());
//			  } catch (JAXBException e) {
//				e.printStackTrace();
//			  }

		}
	
}
