package model;

import java.util.ArrayList;
import java.util.List;

public class Utilizator  {

	
	private int id_utilizator;
	private String user;
	private String parola;
	private String email;
	private String nume;
	private String prenume;
	private Utilizator utilizator;
	private List<Rol> roles = new ArrayList<>();

	public Utilizator() {
		
	}
	
	public Utilizator(String user, String parola, String email, String nume, String prenume) {
		this.user = user;
		this.parola = parola;
		this.email = email;
		this.nume = nume;
		this.prenume = prenume;
	}
	
	
	public Utilizator(String user, String parola) {
		this.user = user;
		this.parola = parola;
	}
	
	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public int getId_utilizator() {
		return id_utilizator;
	}
	public void setId_utilizator(int id_utilizator) {
		this.id_utilizator = id_utilizator;
	}
	public String getUser() {
		return user;
	}
	
	public Utilizator getUtilizator() {
		return utilizator;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	public String getParola() {
		return parola;
	}
	public void setParola(String parola) {
		this.parola = parola;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public String getPrenume() {
		return prenume;
	}
	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}
	
	public void setUtilizator(Utilizator utilizator) {
		this.utilizator = utilizator;
	}
	
	public boolean validate(){  
	    if(parola.equals("admin")){  
	        return true;  
	    }  
	    else{  
	        return false;  
	    }  
	}
	
	@Override
	public String toString() {
		return "Utilizator [id_utilizator=" + id_utilizator + ", user=" + user + ", parola=" + parola + ", email="
				+ email + ", nume=" + nume + ", prenume=" + prenume + "]";
	}	
	
}
