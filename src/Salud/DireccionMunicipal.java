package Salud;

import Persona.Paciente;
import Sistema.GrafoConsejos;


public class DireccionMunicipal extends NodoSalud {

	private String municipio;
	private int poblacion;
	private GrafoConsejos grafo ;

	
	public DireccionMunicipal(String codigo, String nombre, int poblacion, String municipio) {
		super(codigo, nombre);
		setMunicipio(municipio);
		setPoblacion(poblacion);
		grafo = new GrafoConsejos();
		
	}
	
	// anadir enfermo al consejo teniendo el nombre del consejo al que pertenece 
	public void anadirEnfermo(Paciente p){
		grafo.agregarEnfermoAlConsejo(p);
		
	}
	
	
	//Getters y setters
	public String getMunicipio() {return municipio;}	
	public void setMunicipio(String municipio) {this.municipio = municipio;}
	public int getPoblacion() {return poblacion;}	
	public void setPoblacion(int poblacion) {this.poblacion = poblacion;}
	
	
	
}
