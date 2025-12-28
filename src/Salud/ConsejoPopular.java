package Salud;

import java.util.LinkedList;

import Persona.Paciente;

public class ConsejoPopular {

	private String nombre;
	private String codigo;
	private String municipio;
	private LinkedList<Paciente> casos;
	
	public ConsejoPopular(String codigo, String nombre, String municipio) {
		setNombre(nombre);
		setCodigo(codigo);
		setMunicipio(municipio);
		this.casos = new LinkedList<Paciente>();
	}
	
	public int getCasosEnfermedad(String enfermedad){
		int cont = 0;
		for(Paciente p : casos){
			if(pacienteEnfermoDe(enfermedad, p))
				cont++;
		}
		return cont;
	}
	
	public boolean pacienteEnfermoDe(String enfermedad, Paciente p){
		boolean enfermo = false;
		for(int i = 0; i < p.getEnfermedades().size() && !enfermo; i++){
			if(p.getEnfermedades().get(i).getNombre().equalsIgnoreCase(enfermedad))
				enfermo = true;
		}
		return enfermo;
	}
	
	//Getters y setters
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	public String getCodigo() {return codigo;}
	public void setCodigo(String codigo) {this.codigo = codigo;}
	public String getMunicipio() {return municipio;}
	public void setMunicipio(String municipio) {this.municipio = municipio;}
	public LinkedList<Paciente> getCasos() {return casos;}
	public void setCasos(LinkedList<Paciente> casos) {this.casos = casos;}
	
	
	
}
