package model;

public class Categorie {

	private int id_categ;
	private String nume;
	private int id_parinte;
	
	public Categorie() {}
	
	public Categorie(String nume) {
		this.nume = nume;
	}
	
	public Categorie(String nume, int id_parinte) {
		this.nume = nume;
		this.id_parinte = id_parinte;
	}
	public int getId_categ() {
		return id_categ;
	}
	public void setId_categ(int id_categ) {
		this.id_categ = id_categ;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public int getId_parinte() {
		return id_parinte;
	}
	public void setId_parinte(int id_parinte) {
		this.id_parinte = id_parinte;
	}
	@Override
	public String toString() {
		return "Categorie [id_categ=" + id_categ + ", nume=" + nume + ", id_parinte=" + id_parinte + "]";
	}
	
}
