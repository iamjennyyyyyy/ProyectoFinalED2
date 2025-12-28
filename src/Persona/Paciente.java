package Persona;

import java.util.LinkedList;

import Salud.ConsejoPopular;
import Salud.Enfermedad;

public class Paciente {

	private String id;
	private String nombre;
	private ConsejoPopular direccion;
	private LinkedList<Enfermedad> enfermedades;
	
	public Paciente(String id, String nombre, ConsejoPopular direccion) {
		setId(id);
		setNombre(nombre);
		setDireccion(direccion);
		enfermedades = new LinkedList<Enfermedad>();
	}
	//Getters y setters
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	public ConsejoPopular getDireccion() {return direccion;}
	public void setDireccion(ConsejoPopular direccion) {this.direccion = direccion;}
	public LinkedList<Enfermedad> getEnfermedades() {return enfermedades;}

	public void agregarEnfermedad(Enfermedad enfermedad) {
        if (!enfermedades.contains(enfermedad)) {
            enfermedades.add(enfermedad);
        }
    }
}
