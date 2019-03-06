package model;

public class Specificatie {

	private int id_specificatie;
	private String nume;
	
	public Specificatie() {}
	
	public Specificatie(String nume) {
		this.nume = nume;
	}
	public int getId_specificatie() {
		return id_specificatie;
	}
	public void setId_specificatie(int id_specificatie) {
		this.id_specificatie = id_specificatie;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	@Override
	public String toString() {
		return "Specificatie [id_specificatie=" + id_specificatie + ", nume=" + nume + "]";
	}
	
	
	
}
