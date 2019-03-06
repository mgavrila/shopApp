package info;

public class UserInfo {
	
	private String user;
	private String password;
	private String email;
	private String nume;
	private String prenume;
	private String rol;
	private String rol_name;
	
	public UserInfo() {}
	
	public UserInfo(String user, String password) {
		this.user = user;
		this.password = password;
	}
	
	
	
	public UserInfo(String user, String password, String email, String nume, String prenume) {
		this.user = user;
		this.password = password;
		this.email = email;
		this.nume = nume;
		this.prenume = prenume;
	}

	public UserInfo(String user, String password, String email, String nume, String prenume,String rol,String rol_name) {
		this.user = user;
		this.password = password;
		this.email = email;
		this.nume = nume;
		this.prenume = prenume;
		this.rol = rol;
		this.rol_name = rol_name;
	}
	
	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getRol_name() {
		return rol_name;
	}

	public void setRol_name(String rol_name) {
		this.rol_name = rol_name;
	}

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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

	
	
}
