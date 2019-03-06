package model;

public class Imagine {

	private int id_imag;
	private String nume;
	private String cale;
	private int id_prod;
	
	public Imagine(int id_imag, String nume, String cale, int id_prod) {
		super();
		this.id_imag = id_imag;
		this.nume = nume;
		this.cale = cale;
		this.id_prod = id_prod;
	}
	public int getId_imag() {
		return id_imag;
	}
	public void setId_imag(int id_imag) {
		this.id_imag = id_imag;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public String getCale() {
		return cale;
	}
	public void setCale(String cale) {
		this.cale = cale;
	}
	public int getId_prod() {
		return id_prod;
	}
	public void setId_prod(int id_prod) {
		this.id_prod = id_prod;
	}
	@Override
	public String toString() {
		return "Imagine [id_imag=" + id_imag + ", nume=" + nume + ", cale=" + cale + ", id_prod=" + id_prod + "]";
	}

	
}
