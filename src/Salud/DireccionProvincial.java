package Salud;

public class DireccionProvincial extends NodoSalud {

	private String provincia;

	public DireccionProvincial(String codigo, String nombre, String provincia) {
		super(codigo, nombre);
		setProvincia(provincia);
	}
	//Getters y setters
	public String getProvincia() {return provincia;}	
	public void setProvincia(String provincia) {this.provincia = provincia;}
	
}
