package model;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Comanda {

	private int id_comanda;
	private int id_comanda_prod;
	private int id_comanta_cont;
	private Date data;
	
	public Comanda() {}

	public Comanda(int id_comanda_prod, int id_comanta_cont, Date data) {
		this.id_comanda_prod = id_comanda_prod;
		this.id_comanta_cont = id_comanta_cont;
		this.data = data;
	}
	
	public int getId_comanda() {
		return id_comanda;
	}
	
	@XmlAttribute
	public void setId_comanda(int id_comanda) {
		this.id_comanda = id_comanda;
	}
	
	public int getId_comanda_prod() {
		return id_comanda_prod;
	}
	
	@XmlElement
	public void setId_comanda_prod(int id_comanda_prod) {
		this.id_comanda_prod = id_comanda_prod;
	}
	public int getId_comanta_cont() {
		return id_comanta_cont;
	}
	
	@XmlElement
	public void setId_comanta_cont(int id_comanta_cont) {
		this.id_comanta_cont = id_comanta_cont;
	}

	
	@XmlElement
	public void setData(Date data) {
		this.data = data;
	}
	
	public Date getData() {
		return data;
	}

	@Override
	public String toString() {
		return "Comanda [id_comanda=" + id_comanda + ", id_comanda_prod=" + id_comanda_prod + ", id_comanta_cont="
				+ id_comanta_cont + ", data=" + data + "]";
	}
	
	
	
	
}
