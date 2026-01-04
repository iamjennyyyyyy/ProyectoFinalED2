package Salud;
import java.util.ArrayList;

import Persona.Paciente;

public class Consultorio extends NodoSalud {

	private int numero;
	private String consejoPopular;
	private ArrayList<Paciente> pacientes ;
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
		pacientes = new ArrayList<Paciente>();
	}
	public void anadirPaciente(Paciente p ){
		pacientes.add(p);
	}
	public int getNumero(){ return numero;}
	public void setNumero(int numero){ this.numero = numero;}
}
