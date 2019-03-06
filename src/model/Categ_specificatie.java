package model;

public class Categ_specificatie {

	private int id_categ_spec;
	private String nume;

	public Categ_specificatie(int id_categ_spec, String nume) {
		this.id_categ_spec = id_categ_spec;
		this.nume = nume;
	}
	public int getId_categ_spec() {
		return id_categ_spec;
	}
	public void setId_categ_spec(int id_categ_spec) {
		this.id_categ_spec = id_categ_spec;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	@Override
	public String toString() {
		return "Categ_specificatie [id_categ_spec=" + id_categ_spec + ", nume=" + nume + "]";
	}
	
}
