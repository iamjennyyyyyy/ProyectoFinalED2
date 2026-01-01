package Persona;

import java.util.ArrayList;
import java.util.LinkedList;

import Salud.ConsejoPopular;
import Salud.Enfermedad;

public class Paciente {

	private String id;
	private String nombre;
	private String direccion;
	

	private String consultorio;
	private ArrayList<Enfermedad> enfermedades;
	
	public Paciente(String id, String nombre, String direccion, String consultorio) {
		setId(id);
		setNombre(nombre);
		enfermedades = new ArrayList<Enfermedad>();
		setDireccion(direccion);
		setConsultorio(consultorio);
	}
	//Getters y setters
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	public ArrayList<Enfermedad> getEnfermedades() {return enfermedades;}

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
