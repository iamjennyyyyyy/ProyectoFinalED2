package Salud;

public class Consultorio extends NodoSalud {

	private int numero;
	//Importar y poner grafo	
	
	public Consultorio(String codigo, String nombre, int numero) {
		super(codigo, nombre);
		setNumero(numero);
	}
	
	public int getNumero(){ return numero;}
	public void setNumero(int numero){ this.numero = numero;}
}
