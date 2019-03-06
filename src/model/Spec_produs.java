package model;

public class Spec_produs {

	private int id_spec_produs;
	private int id_produs;
	private int id_specificatie;
	private String valoare_spec;

	
	public Spec_produs(int id_spec_produs, int id_produs, int id_specificatie, String valoare_spec) {
		super();
		this.id_spec_produs = id_spec_produs;
		this.id_produs = id_produs;
		this.id_specificatie = id_specificatie;
		this.valoare_spec = valoare_spec;
	}
	public int getId_spec_produs() {
		return id_spec_produs;
	}
	public void setId_spec_produs(int id_spec_produs) {
		this.id_spec_produs = id_spec_produs;
	}
	public int getId_produs() {
		return id_produs;
	}
	public void setId_produs(int id_produs) {
		this.id_produs = id_produs;
	}
	public int getId_specificatie() {
		return id_specificatie;
	}
	public void setId_specificatie(int id_specificatie) {
		this.id_specificatie = id_specificatie;
	}
	public String getValoare_spec() {
		return valoare_spec;
	}
	public void setValoare_spec(String valoare_spec) {
		this.valoare_spec = valoare_spec;
	}
	@Override
	public String toString() {
		return "Spec_produs [id_spec_produs=" + id_spec_produs + ", id_produs=" + id_produs + ", id_specificatie="
				+ id_specificatie + ", valoare_spec=" + valoare_spec + "]";
	}
	
	
	
}
