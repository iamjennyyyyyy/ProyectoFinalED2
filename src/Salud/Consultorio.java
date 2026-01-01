package Salud;

public class Consultorio extends NodoSalud {

	private int numero;
	private String consejoPopular;
	//Importar y poner grafo	
	
	public String getConsejoPopular() {
		return consejoPopular;
	}

	public void setConsejoPopular(String consejoPopular) {
		this.consejoPopular = consejoPopular;
	}

	public Consultorio(String codigo, String nombre, int numero, String consejo) {
		super(codigo, nombre);
		setNumero(numero);
		setConsejoPopular(consejo);
	}
	
	public int getNumero(){ return numero;}
	public void setNumero(int numero){ this.numero = numero;}
}
