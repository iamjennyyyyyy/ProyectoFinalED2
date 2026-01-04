package Auxiliar;
import Auxiliar.Mes;
public class Mes {
	
	private String mes;
	private int cantidadCasos;
	private Estado estado;
	public Mes(String mes) {
		
		this.mes = mes;
		cantidadCasos=0;
		estado = Estado.Normal;
	}
	public Mes(String mes, int cantidadCasos, Estado estado) {
		super();
		this.mes = mes;
		this.cantidadCasos = cantidadCasos;
		this.estado = estado;
	}
	public String getMes() {
		return mes;
	}
	
	public Estado getEstado() {
		return estado;
	}
	public int getCantidadCasos(){
		return cantidadCasos;
	}
	
	
	public void declararEpidemia(){
		estado = Estado.Epidemia;
	}
	public void declararAlerta(){
		estado=Estado.Alerta_Epidemica;
	}
	

public void aumentarCasos(){
	cantidadCasos+=cantidadCasos;
	
}
	
	

}
