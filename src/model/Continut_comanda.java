package model;

public class Continut_comanda {

	private int id_continut_comanda;
	private int id_produs_cont;
	private int id_util_cont;
	private int cantitate;
	
	public Continut_comanda(int id_continut_comanda, int id_produs_cont, int id_util_cont, int cantitate) {
		super();
		this.id_continut_comanda = id_continut_comanda;
		this.id_produs_cont = id_produs_cont;
		this.id_util_cont = id_util_cont;
		this.cantitate = cantitate;
	}
	public int getId_continut_comanda() {
		return id_continut_comanda;
	}
	public void setId_continut_comanda(int id_continut_comanda) {
		this.id_continut_comanda = id_continut_comanda;
	}
	public int getId_produs_cont() {
		return id_produs_cont;
	}
	public void setId_produs_cont(int id_produs_cont) {
		this.id_produs_cont = id_produs_cont;
	}
	public int getId_util_cont() {
		return id_util_cont;
	}
	public void setId_util_cont(int id_util_cont) {
		this.id_util_cont = id_util_cont;
	}
	public int getCantitate() {
		return cantitate;
	}
	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}
	@Override
	public String toString() {
		return "Continut_comanda [id_continut_comanda=" + id_continut_comanda + ", id_produs_cont=" + id_produs_cont
				+ ", id_util_cont=" + id_util_cont + ", cantitate=" + cantitate + "]";
	}

}
