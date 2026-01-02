package Persona;

import java.util.ArrayList;
import java.util.LinkedList;

import Salud.ConsejoPopular;
import Salud.Enfermedad;
import Utiles.Sintomas;

public class Paciente {

	private String id;
	private String nombreCompleto;
	private long numero;
	private String correo;
	private ConsejoPopular direccion;
	private LinkedList<Sintomas> sintomas;
	private LinkedList<Enfermedad> enfermedades;
	
	public Paciente(String id, String nombre, long numero, String correo, ConsejoPopular direccion ) {
		setId(id);
		setNombreCompleto(nombre);
		setNumero(numero);
		setCorreo(correo);
		setDireccion(direccion);
		enfermedades = new LinkedList<Enfermedad>();
		sintomas = new LinkedList<Sintomas>();
	}
	public Paciente(){
		enfermedades = new LinkedList<Enfermedad>();
		sintomas = new LinkedList<Sintomas>();
	}
	
	//Getters y setters
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	public long getNumero() {return numero;}
	public void setNumero(long numero) {this.numero = numero;}
	public String getCorreo() {return correo;}
	public void setCorreo(String correo) {this.correo = correo;}
	public String getNombreCompleto() {return nombreCompleto;}
	public void setNombreCompleto(String nombre) {this.nombreCompleto = nombre;}
	public ConsejoPopular getDireccion() {return direccion;}
	public void setDireccion(ConsejoPopular direccion) {this.direccion = direccion;}
	public LinkedList<Enfermedad> getEnfermedades() {return enfermedades;}

	public void agregarEnfermedad(Enfermedad enfermedad) {
        if (!enfermedades.contains(enfermedad)) {
            enfermedades.add(enfermedad);
        }
    }
	public String getConsultorio() {
		return consultorio;
	}
	public void setConsultorio(String consultorio) {
		this.consultorio = consultorio;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}
