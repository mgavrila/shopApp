package model;

public class Tip_cont_client {
	
	private int id_tip_cont_client;
	private String tip_client;
	private int client_id_client;
	
	public Tip_cont_client(String tip_client, int client_id_client) {
		super();
		this.tip_client = tip_client;
		this.client_id_client = client_id_client;
	}
	public int getId_tip_cont_client() {
		return id_tip_cont_client;
	}
	public void setId_tip_cont_client(int id_tip_cont_client) {
		this.id_tip_cont_client = id_tip_cont_client;
	}
	public String getTip_client() {
		return tip_client;
	}
	public void setTip_client(String tip_client) {
		this.tip_client = tip_client;
	}
	public int getClient_id_client() {
		return client_id_client;
	}
	public void setClient_id_client(int client_id_client) {
		this.client_id_client = client_id_client;
	}
	
	

}
