package Salud;

public class AreaDeSalud extends NodoSalud {
	

	private String policlinico;
	public AreaDeSalud(String codigo, String nombre, String pol) {
		super(codigo, nombre);
		policlinico = pol;
	}
	

}
