package Salud;

import java.time.LocalDate;

public class Enfermedad {

	private String nombre;
	private LocalDate fechaDiagnostico;
	
	
	public Enfermedad(String nombre, LocalDate fechaDiagnostico) {
		setNombre(nombre);
		setFechaDiagnostico(fechaDiagnostico);
	}
	
	public Enfermedad(String string) {
		this.nombre = string;
	}
	//Getters y setters
	public String getNombre() {return nombre;}	
	public void setNombre(String nombre) {this.nombre = nombre;}
	public LocalDate getFechaDiagnostico() {return fechaDiagnostico;}
	public void setFechaDiagnostico(LocalDate fechaDiagnostico) {this.fechaDiagnostico = fechaDiagnostico;}
	
}
