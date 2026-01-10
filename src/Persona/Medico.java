package Persona;

import Salud.Consultorio;

public class Medico extends Persona {
	
	public Medico(){
		super();
	}
	
	public Medico(String id, String nombre, String numero, String correo, String direccion, String consultorio){
		super(id, nombre, numero, correo, direccion, consultorio);
	}
}
