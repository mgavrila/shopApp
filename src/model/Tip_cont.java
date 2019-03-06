package model;

public class Tip_cont {
	

	private int id_tip;
	private Utilizator tip_id_utilizator;
	

	public Utilizator getUser() {
		return this.tip_id_utilizator;
		
	}

	public void setUser(Utilizator tip_id_utilizator) {
		this.tip_id_utilizator = tip_id_utilizator;
	}
	
	private String tip;
	
	public Tip_cont() {
		
	}
	
	public Tip_cont(Utilizator tip_id_utilizator,String tip) {
		this.tip_id_utilizator = tip_id_utilizator;
		this.tip = tip;
	}
	public int getId_cont() {
		return id_tip;
	}
	public void setId_cont(int id_tip) {
		this.id_tip = id_tip;
	}
	
	public String getTip() {
		return tip;
	}
	
	public void setTip(String tip) {
		this.tip = tip;
	}
	

}
