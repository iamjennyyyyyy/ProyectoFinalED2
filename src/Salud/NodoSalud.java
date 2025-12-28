package Salud;

public abstract class NodoSalud {

	private String codigo;
	private String nombre;
	
	public NodoSalud(String codigo, String nombre){
		setCodigo(codigo);
		setNombre(nombre);
	}
	//Getters y setters
	public String getCodigo() {return codigo;}
	public void setCodigo(String codigo) {this.codigo = codigo;}
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
}
