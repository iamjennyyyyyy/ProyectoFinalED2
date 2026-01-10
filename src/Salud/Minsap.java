package Salud;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Auxiliar.Inicializar;
import Auxiliar.Sistema;
import Utiles.Enfermedad;
import Utiles.Sintomas;
import Utiles.Enfermedad.Categoria;
import Utiles.Enfermedad.DiagnosticoEnfermedad;
import Utiles.Enfermedad.Gravedad;
import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.general.GeneralTree;
import cu.edu.cujae.ceis.tree.iterators.general.InBreadthIterator;

public class Minsap extends NodoSalud{


	
	private ArrayList<Enfermedad> enfermedadesActuales;
	

	public Minsap(String codigo, String nombre){
		super(codigo, nombre );
		enfermedadesActuales = new ArrayList<Enfermedad>();

	private static ArrayList<Enfermedad> enfermedadesActuales = Inicializar.inicializarEnfermedadesPredefinidas();
	
	public Minsap(String codigo, String nombre){
		super(codigo, nombre );

	}

	public static ArrayList<Enfermedad> getEnfermedadesActuales(){
		return enfermedadesActuales;
	}
	
	public static ArrayList<Enfermedad> getEnfermedadesPorCategoria(Categoria categoria) {
		ArrayList<Enfermedad> resultado = new ArrayList<>();
		for (Enfermedad e : enfermedadesActuales) {
			if (e.getCategoria() == categoria) {
				resultado.add(e);
			}
		}
		return resultado;
	}

	/**
	 * Obtiene enfermedades por gravedad
	 */
	public static ArrayList<Enfermedad> getEnfermedadesPorGravedad(Gravedad gravedad) {
		ArrayList<Enfermedad> resultado = new ArrayList<>();
		for (Enfermedad e : enfermedadesActuales) {
			if (e.getGravedadTipica() == gravedad) {
				resultado.add(e);
			}
		}
		return resultado;
	}

	public static List<Enfermedad> buscarPorNombre(String texto) {
		String textoBusqueda = texto.toLowerCase();
		List<Enfermedad> resultado = new ArrayList<>();
		for (Enfermedad e : enfermedadesActuales) {
			if (e.getNombre().toLowerCase().contains(textoBusqueda)) {
				resultado.add(e);
			}
		}
		return resultado;
	}

	/**
	 * Obtiene enfermedades epid�micas comunes
	 */
	public static List<Enfermedad> getEnfermedadesEpidemicasComunes() {
		List<String> nombresComunes = Arrays.asList(
				"COVID-19", "Influenza (Gripe)", "Dengue", 
				"Gastroenteritis", "Varicela", "Resfriado com�n"
				);

		List<Enfermedad> resultado = new ArrayList<>();
		for (Enfermedad e : enfermedadesActuales) {
			if (nombresComunes.contains(e.getNombre())) {
				resultado.add(e);
			}
		}
		return resultado;
	}

	/**
	 * Obtiene enfermedades por �ndices (para JList)
	 */
	public static ArrayList<Enfermedad> obtenerEnfermedadesPorIndices(int[] pos, ArrayList<Enfermedad> enf) {
		ArrayList<Enfermedad> resultado = new ArrayList<>();
		for (int i = 0; i < pos.length; i++) {
			if (pos[i] >= 0 && pos[i] < enf.size()) {
				resultado.add(enf.get(pos[i]));
			}
		}
		return resultado;
	}

	/**
	 * M�todo para diagnosticar enfermedades basadas en s�ntomas
	 */
	public static List<DiagnosticoEnfermedad> diagnosticar(List<Sintomas> sintomasPaciente) {
		List<DiagnosticoEnfermedad> diagnosticos = new ArrayList<>();

		if (sintomasPaciente == null || sintomasPaciente.isEmpty()) {
			return diagnosticos;
		}

		for (Enfermedad enfermedad : enfermedadesActuales) {
			double probabilidad = enfermedad.calcularProbabilidad(sintomasPaciente);
			if (probabilidad > 30.0) { // Umbral m�nimo del 30%
				diagnosticos.add(new DiagnosticoEnfermedad(enfermedad, probabilidad));
			}
		}

		return diagnosticos;
	}
	
	public static ArrayList<Enfermedad> obtenerEnfermedadesConTantosSintomasComunes(ArrayList<Sintomas> sintomasPaciente, int cant){
		ArrayList<Enfermedad> enfermedades = new ArrayList<Enfermedad>();
		
		for(Enfermedad e : enfermedadesActuales){
			if(e.enfermedadConCantidadDeSintomas(sintomasPaciente, cant))
				enfermedades.add(e);
		}
		return enfermedades;
	}
}
