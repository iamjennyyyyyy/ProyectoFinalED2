package Salud;

public class Policlinico extends NodoSalud {

	private String policlinico;
	
	
	public Policlinico(String codigo, String nombre, String policlinico) {
		super(codigo, nombre);
		setPoliclinico(policlinico);
	}
	
	public String getPoliclinico(){ return policlinico;}
	public void setPoliclinico(String policlinico){ this.policlinico = policlinico;}
}
