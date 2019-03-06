package model;
import java.util.Date;

public class Log {

	private int id_log;
	private Date data;
	private String tip_operatie;
	private int id_log_utilizator;

	public Log(int id_log, Date data, String tip_operatie, int id_log_utilizator) {
		super();
		this.id_log = id_log;
		this.data = data;
		this.tip_operatie = tip_operatie;
		this.id_log_utilizator = id_log_utilizator;
	}
	
	public int getId_log() {
		return id_log;
	}
	public void setId_log(int id_log) {
		this.id_log = id_log;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getTip_operatie() {
		return tip_operatie;
	}
	public void setTip_operatie(String tip_operatie) {
		this.tip_operatie = tip_operatie;
	}
	public int getId_log_utilizator() {
		return id_log_utilizator;
	}
	public void setId_log_utilizator(int id_log_utilizator) {
		this.id_log_utilizator = id_log_utilizator;
	}

	@Override
	public String toString() {
		return "Log [id_log=" + id_log + ", data=" + data + ", tip_operatie=" + tip_operatie + ", id_log_utilizator="
				+ id_log_utilizator + "]";
	}
	
	
}
