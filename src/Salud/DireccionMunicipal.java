package Salud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cu.edu.cujae.ceis.graph.interfaces.ILinkedNotDirectedGraph;
import Auxiliar.Estado;
import Auxiliar.Mes;
import Auxiliar.Registro;
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
	public void anadirEnfermo(Paciente p, Consultorio c){
		
		grafo.agregarEnfermoAlConsejo(p, c.getConsejoPopular());
		
	}
	
	
	//Getters y setters
	public String getMunicipio() {return municipio;}	
	public void setMunicipio(String municipio) {this.municipio = municipio;}
	public int getPoblacion() {return poblacion;}	
	public void setPoblacion(int poblacion) {this.poblacion = poblacion;}

	

	public GrafoConsejos getGrafo() {
		// TODO Auto-generated method stub
		return grafo;
	}
	
	
	
}
