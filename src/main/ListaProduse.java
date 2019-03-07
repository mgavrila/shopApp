package main;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import model.Produs;

@XmlRootElement
public class ListaProduse {

	List<Produs> produse = new ArrayList<>();

	@XmlElement
	public List<Produs> getProduse() {
		return produse;
	}

	public void setProduse(List<Produs> produse) {
		this.produse = produse;
	}

	public ListaProduse(List<Produs> produse) {
		super();
		this.produse = produse;
	}
	
	public ListaProduse() {}
	
	
}
