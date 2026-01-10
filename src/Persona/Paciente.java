package Persona;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

import Utiles.Enfermedad;
import Utiles.Sintomas;

public class Paciente extends Persona {

	private LocalDate fechaDiagnostico;
	private ArrayList<Sintomas> sintomas;
	private ArrayList<Enfermedad> enfermedades;

	public LocalDate getFechaDiagnostico() {
		return fechaDiagnostico;
	}

	public void setFechaDiagnostico(LocalDate fechaDiagnostico) {
		this.fechaDiagnostico = fechaDiagnostico;
	}

	public Paciente(String id, String nombre, String numero, String correo, String direccion, String consultorio) {
		super(id,nombre,numero,correo,direccion, consultorio);
		fechaDiagnostico = LocalDate.now();
		enfermedades = new ArrayList<Enfermedad>();
		sintomas = new ArrayList<Sintomas>();
	}
	
	public ArrayList<Enfermedad> getEnfermedad(){
		return enfermedades;
	}
    
	public Paciente(){
		super();
		enfermedades = new ArrayList<Enfermedad>();
		sintomas = new ArrayList<Sintomas>();
	}

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
