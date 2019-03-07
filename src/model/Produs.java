package model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Produs {

	private int id_prod;
	private int id_categ;
	private float pret;
	private int stoc;
	private int garantie;
	private String descriere_scurta;
	private String descriere_lunga;
	private String nume;
	
	public Produs() {};
	
	public Produs(int id_categ, float pret, int stoc, int garantie, String descriere_scurta,
			String descriere_lunga, String nume) {
		this.id_categ = id_categ;
		this.pret = pret;
		this.stoc = stoc;
		this.garantie = garantie;
		this.descriere_scurta = descriere_scurta;
		this.descriere_lunga = descriere_lunga;
		this.nume = nume;
	}
	
	public Produs(String nume) {
		this.nume = nume;
	}
	
	
	@XmlElement
	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public int getId_prod() {
		return id_prod;
	}
	public void setId_prod(int id_prod) {
		this.id_prod = id_prod;
	}
	public int getId_categ() {
		return id_categ;
	}
	public void setId_categ(int id_categ) {
		this.id_categ = id_categ;
	}
	public float getPret() {
		return pret;
	}
	public void setPret(float pret) {
		this.pret = pret;
	}
	public int getStoc() {
		return stoc;
	}
	public void setStoc(int stoc) {
		this.stoc = stoc;
	}
	public int getGarantie() {
		return garantie;
	}
	public void setGarantie(int garantie) {
		this.garantie = garantie;
	}
	public String getDescriere_scurta() {
		return descriere_scurta;
	}
	public void setDescriere_scurta(String descriere_scurta) {
		this.descriere_scurta = descriere_scurta;
	}
	public String getDescriere_lunga() {
		return descriere_lunga;
	}
	public void setDescriere_lunga(String descriere_lunga) {
		this.descriere_lunga = descriere_lunga;
	}
	@Override
	public String toString() {
		return "Produs [id_prod=" + id_prod + ", id_categ=" + id_categ + ", pret=" + pret + ", stoc=" + stoc
				+ ", garantie=" + garantie + ", descriere_scurta=" + descriere_scurta + ", descriere_lunga="
				+ descriere_lunga + "]";
	}

}
