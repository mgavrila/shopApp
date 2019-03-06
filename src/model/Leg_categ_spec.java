package model;

public class Leg_categ_spec {

	private int id_leg;
	private int id_categ_spec;
	private int id_spec;
	private int id_categ;
	private int pozitie_spec;
	private int pozitie_categ_spec;

	public Leg_categ_spec(int id_leg, int id_categ_spec, int id_spec, int id_categ, int pozitie_spec,
			int pozitie_categ_spec) {
		super();
		this.id_leg = id_leg;
		this.id_categ_spec = id_categ_spec;
		this.id_spec = id_spec;
		this.id_categ = id_categ;
		this.pozitie_spec = pozitie_spec;
		this.pozitie_categ_spec = pozitie_categ_spec;
	}
	public int getId_leg() {
		return id_leg;
	}
	public void setId_leg(int id_leg) {
		this.id_leg = id_leg;
	}
	public int getId_categ_spec() {
		return id_categ_spec;
	}
	public void setId_categ_spec(int id_categ_spec) {
		this.id_categ_spec = id_categ_spec;
	}
	public int getId_spec() {
		return id_spec;
	}
	public void setId_spec(int id_spec) {
		this.id_spec = id_spec;
	}
	public int getId_categ() {
		return id_categ;
	}
	public void setId_categ(int id_categ) {
		this.id_categ = id_categ;
	}
	public int getPozitie_spec() {
		return pozitie_spec;
	}
	public void setPozitie_spec(int pozitie_spec) {
		this.pozitie_spec = pozitie_spec;
	}
	public int getPozitie_categ_spec() {
		return pozitie_categ_spec;
	}
	public void setPozitie_categ_spec(int pozitie_categ_spec) {
		this.pozitie_categ_spec = pozitie_categ_spec;
	}
	@Override
	public String toString() {
		return "Leg_categ_spec [id_leg=" + id_leg + ", id_categ_spec=" + id_categ_spec + ", id_spec=" + id_spec
				+ ", id_categ=" + id_categ + ", pozitie_spec=" + pozitie_spec + ", pozitie_categ_spec="
				+ pozitie_categ_spec + "]";
	}
	
	
}
