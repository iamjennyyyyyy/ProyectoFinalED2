package Persona;

import java.util.ArrayList;
import java.util.LinkedList;

import Utiles.Enfermedad;
import Utiles.Sintomas;

public class Paciente {

	private String id;
	private String nombreCompleto;
	private String numero;
	private String correo;
	private String direccion;
	private String consultorio;
	private ArrayList<Sintomas> sintomas;
	private ArrayList<Enfermedad> enfermedades;

	public Paciente(String id, String nombre, String numero, String correo, String direccion ) {
		setId(id);
		setNombreCompleto(nombre);
		setNumero(numero);
		setCorreo(correo);
		setDireccion(direccion);
		enfermedades = new ArrayList<Enfermedad>();
		sintomas = new ArrayList<Sintomas>();
	}
	public ArrayList<Enfermedad> getEnfermedad(){
		return enfermedades;
	}
    
	public Paciente(){
		enfermedades = new ArrayList<Enfermedad>();
		sintomas = new ArrayList<Sintomas>();
	}

	//Getters y setters
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	public String getNumero() {return numero;}
	public void setNumero(String numero) {this.numero = numero;}
	public String getCorreo() {return correo;}
	public void setCorreo(String correo) {this.correo = correo;}
	public String getNombreCompleto() {return nombreCompleto;}
	public void setNombreCompleto(String nombre) {this.nombreCompleto = nombre;}
	public String getDireccion() {return direccion;}
	public void setDireccion(String direccion) {this.direccion = direccion;}
	public String getConsultorio() {return consultorio;}
	public void setConsultorio(String consultorio) {this.consultorio = consultorio;}
	public ArrayList<Enfermedad> getEnfermedades() {return enfermedades;}
	public ArrayList<Sintomas> getSintomas() {return sintomas;}
	public void setSintomas(ArrayList<Sintomas> sintomasN) {sintomas = sintomasN;}
	public void setEnfermedades(ArrayList<Enfermedad> enfermedadesN) {enfermedades = enfermedadesN;}
	public void agregarEnfermedad(Enfermedad enfermedad) {
		if (!enfermedades.contains(enfermedad)) {
			enfermedades.add(enfermedad);
		}
	}
	public void agregarSintoma(Sintomas sintoma) {
		if (!sintomas.contains(sintoma)) {
			sintomas.add(sintoma);
		}
	}
}
