package Salud;

public class DireccionMunicipal extends NodoSalud {

	private String municipio;
	private int poblacion;

	public DireccionMunicipal(String codigo, String nombre, int poblacion, String municipio) {
		super(codigo, nombre);
		setMunicipio(municipio);
		setPoblacion(poblacion);
	}
	//Getters y setters
	public String getMunicipio() {return municipio;}	
	public void setMunicipio(String municipio) {this.municipio = municipio;}
	public int getPoblacion() {return poblacion;}	
	public void setPoblacion(int poblacion) {this.poblacion = poblacion;}
}
