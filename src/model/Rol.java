package model;

import java.util.ArrayList;
import java.util.List;

public class Rol {


	private int id_rol;
	private String nume;
	private Utilizator user;
	private List<Utilizator> users = new ArrayList<>();
		
	public Utilizator getUser() {
		return user;
	}

	public void setUser(Utilizator user) {
		this.user = user;
	}

	public List<Utilizator> getUsers() {
		return users;
	}

	public void setUsers(List<Utilizator> users) {
		this.users = users;
	}

	public Rol() {
	
	}
	
	public Rol(String nume) {
		this.nume = nume;
	}
	public int getId_rol() {
		return id_rol;
	}
	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}
	
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	
	@Override
	public String toString() {
		return "Rol [id_rol=" + id_rol + ", nume=" + nume + "]";
	}
	
}
