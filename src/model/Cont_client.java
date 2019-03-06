package model;

public class Cont_client {
	
	private int id_client;
	private String user_client;
	private String parola_client;
	private String email_client;
	private String nume_client;
	private String prenume_client;
	
	public Cont_client(){};
	
	public Cont_client(String user_client, String parola_client, String email_client, String nume_client,
			String prenume_client) {
		this.user_client = user_client;
		this.parola_client = parola_client;
		this.email_client = email_client;
		this.nume_client = nume_client;
		this.prenume_client = prenume_client;
	}


	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	public String getUser_client() {
		return user_client;
	}
	public void setUser_client(String user_client) {
		this.user_client = user_client;
	}
	public String getParola_client() {
		return parola_client;
	}
	public void setParola_client(String parola_client) {
		this.parola_client = parola_client;
	}
	public String getEmail_client() {
		return email_client;
	}
	public void setEmail_client(String email_client) {
		this.email_client = email_client;
	}
	public String getNume_client() {
		return nume_client;
	}
	public void setNume_client(String nume_client) {
		this.nume_client = nume_client;
	}
	public String getPrenume_client() {
		return prenume_client;
	}
	public void setPrenume_client(String prenume_client) {
		this.prenume_client = prenume_client;
	}	
	
}
