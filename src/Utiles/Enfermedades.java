package Utiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Clase que representa las principales enfermedades que pueden ser diagnosticadas
 * basándose en los síntomas presentados por el paciente.
 * Versión compatible con Java 1.8.0
 */
public class Enfermedades {

	// Atributos
	private String nombre;
	private Categoria categoria;
	private Gravedad gravedadTipica;
	private List<Sintomas> sintomasComunes;
	private String agenteEtiologico;
	private String duracion; // Nuevo atributo
	private LocalDate fechaDiagnostico;

	// Enfermedades predefinidas (simulando el enum)
	private static ArrayList<Enfermedades> enfermedadesPredefinidas;

	/**
	 * Constructor completo
	 */
	public Enfermedades(String nombre, Categoria categoria, Gravedad gravedadTipica, 
			List<Sintomas> sintomasComunes, String agenteEtiologico, String duracion, LocalDate fechaDiagnostico) {
		setNombre(nombre);
		setCategoria(categoria);
		setGravedadTipica(gravedadTipica);
		this.sintomasComunes = new ArrayList<>(sintomasComunes);
		setAgenteEtiologico(agenteEtiologico);
		setDuracion(duracion);
		setFechaDiagnostico(fechaDiagnostico);
	}
	public Enfermedades(String nombre, Categoria categoria, Gravedad gravedadTipica, 
			List<Sintomas> sintomasComunes, String agenteEtiologico, String duracion) {
		setNombre(nombre);
		setCategoria(categoria);
		setGravedadTipica(gravedadTipica);
		this.sintomasComunes = new ArrayList<>(sintomasComunes);
		setAgenteEtiologico(agenteEtiologico);
		setDuracion(duracion);
	}

	/**
	 * Constructor vacío
	 */
	public Enfermedades() {
		this.sintomasComunes = new ArrayList<>();
	}

	// ========== MÉTODOS ESTÁTICOS PARA ACCEDER A LAS ENFERMEDADES ==========

	/**
	 * Obtiene todas las enfermedades predefinidas
	 */
	public static ArrayList<Enfermedades> getEnfermedadesPredefinidas() {
		if (enfermedadesPredefinidas == null) {
			Sistema.inicializarEnfermedadesPredefinidas();
		}
		return new ArrayList<>(enfermedadesPredefinidas);
	}

	/**
	 * Obtiene enfermedades por categoría
	 */
	public static ArrayList<Enfermedades> getEnfermedadesPorCategoria(Categoria categoria) {
		ArrayList<Enfermedades> resultado = new ArrayList<>();
		for (Enfermedades e : getEnfermedadesPredefinidas()) {
			if (e.getCategoria() == categoria) {
				resultado.add(e);
			}
		}
		return resultado;
	}

	/**
	 * Obtiene enfermedades por gravedad
	 */
	public static ArrayList<Enfermedades> getEnfermedadesPorGravedad(Gravedad gravedad) {
		ArrayList<Enfermedades> resultado = new ArrayList<>();
		for (Enfermedades e : getEnfermedadesPredefinidas()) {
			if (e.getGravedadTipica() == gravedad) {
				resultado.add(e);
			}
		}
		return resultado;
	}

	/**
	 * Busca enfermedades por nombre
	 */
	public static List<Enfermedades> buscarPorNombre(String texto) {
		String textoBusqueda = texto.toLowerCase();
		List<Enfermedades> resultado = new ArrayList<>();
		for (Enfermedades e : getEnfermedadesPredefinidas()) {
			if (e.getNombre().toLowerCase().contains(textoBusqueda)) {
				resultado.add(e);
			}
		}
		return resultado;
	}

	/**
	 * Obtiene enfermedades epidémicas comunes
	 */
	public static List<Enfermedades> getEnfermedadesEpidemicasComunes() {
		List<String> nombresComunes = Arrays.asList(
				"COVID-19", "Influenza (Gripe)", "Dengue", 
				"Gastroenteritis", "Varicela", "Resfriado común"
				);

		List<Enfermedades> resultado = new ArrayList<>();
		for (Enfermedades e : getEnfermedadesPredefinidas()) {
			if (nombresComunes.contains(e.getNombre())) {
				resultado.add(e);
			}
		}
		return resultado;
	}

	/**
	 * Obtiene enfermedades por índices (para JList)
	 */
	public static ArrayList<Enfermedades> obtenerEnfermedadesPorIndices(int[] pos) {
		ArrayList<Enfermedades> todas = getEnfermedadesPredefinidas();
		ArrayList<Enfermedades> resultado = new ArrayList<>();
		for (int i = 0; i < pos.length; i++) {
			if (pos[i] >= 0 && pos[i] < todas.size()) {
				resultado.add(todas.get(pos[i]));
			}
		}
		return resultado;
	}

	/**
	 * Método para diagnosticar enfermedades basadas en síntomas
	 */
	public static List<DiagnosticoEnfermedad> diagnosticar(List<Sintomas> sintomasPaciente) {
		List<DiagnosticoEnfermedad> diagnosticos = new ArrayList<>();

		if (sintomasPaciente == null || sintomasPaciente.isEmpty()) {
			return diagnosticos;
		}

		for (Enfermedades enfermedad : getEnfermedadesPredefinidas()) {
			double probabilidad = enfermedad.calcularProbabilidad(sintomasPaciente);
			if (probabilidad > 30.0) { // Umbral mínimo del 30%
				diagnosticos.add(new DiagnosticoEnfermedad(enfermedad, probabilidad));
			}
		}

		return diagnosticos;
	}

	// ========== GETTERS Y SETTERS ==========

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Gravedad getGravedadTipica() {
		return gravedadTipica;
	}

	public void setGravedadTipica(Gravedad gravedadTipica) {
		this.gravedadTipica = gravedadTipica;
	}

	public List<Sintomas> getSintomasComunes() {
		return new ArrayList<>(sintomasComunes);
	}

	public void setSintomasComunes(List<Sintomas> sintomasComunes) {
		this.sintomasComunes = new ArrayList<>(sintomasComunes);
	}

	public String getAgenteEtiologico() {
		return agenteEtiologico;
	}

	public void setAgenteEtiologico(String agenteEtiologico) {
		this.agenteEtiologico = agenteEtiologico;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	public void setFechaDiagnostico(LocalDate now){
		fechaDiagnostico = now;
	}

	// ========== MÉTODOS DE INSTANCIA ==========

			/**
			 * Calcula la probabilidad de esta enfermedad basada en síntomas presentes
			 */
			 public double calcularProbabilidad(List<Sintomas> sintomasPaciente) {
				 if (sintomasPaciente == null || sintomasPaciente.isEmpty() || sintomasComunes.isEmpty()) {
					 return 0.0;
				 }

				 int coincidencias = 0;
				 for (Sintomas sintoma : sintomasPaciente) {
					 if (sintomasComunes.contains(sintoma)) {
						 coincidencias++;
					 }
				 }

				 double porcentaje = (double) coincidencias / sintomasComunes.size() * 100;

				 if (coincidencias >= sintomasComunes.size() * 0.7) {
					 porcentaje += 10;
				 }

				 return Math.min(porcentaje, 100.0);
			 }

			 /**
			  * Obtiene coincidencias de síntomas
			  */
			 public int getCoincidenciasSintomas(List<Sintomas> sintomasPaciente) {
				 int coincidencias = 0;
				 for (Sintomas sintoma : sintomasPaciente) {
					 if (sintomasComunes.contains(sintoma)) {
						 coincidencias++;
					 }
				 }
				 return coincidencias;
			 }

			 /**
			  * Verifica si es una enfermedad urgente
			  */
			 public boolean esUrgente() {
				 return gravedadTipica == Gravedad.GRAVE || gravedadTipica == Gravedad.MUY_GRAVE;
			 }

			 /**
			  * Obtiene recomendaciones básicas
			  */
			 public String getRecomendacionBasica() {
				 if (esUrgente()) {
					 return "Acudir inmediatamente a un centro de salud";
				 } else if (gravedadTipica == Gravedad.MODERADA) {
					 return "Consultar con médico en las próximas 24-48 horas";
				 } else {
					 return "Descansar, hidratarse y monitorear síntomas";
				 }
			 }

			 /**
			  * Genera reporte completo
			  */
			 public String generarReporte() {
				 StringBuilder reporte = new StringBuilder();
				 reporte.append("ENFERMEDAD: ").append(nombre).append("\n");
				 reporte.append("Categoría: ").append(categoria.getNombre()).append("\n");
				 reporte.append("Gravedad típica: ").append(gravedadTipica.getNivel()).append("\n");
				 reporte.append("Duración estimada: ").append(duracion).append("\n");
				 reporte.append("Agente etiológico: ").append(agenteEtiologico).append("\n");
				 reporte.append("\nSíntomas comunes:\n");

				 for (Sintomas sintoma : sintomasComunes) {
					 reporte.append("• ").append(sintoma.getDescripcion())
					 .append(" (").append(sintoma.getCategoria().getNombre())
					 .append(" - ").append(sintoma.getGravedadBase().getNivel())
					 .append(")\n");
				 }

				 reporte.append("\nRecomendación: ").append(getRecomendacionBasica());

				 return reporte.toString();
			 }

			 @Override
			 public String toString() {
				 return nombre + " [" + categoria.getNombre() + " - " + gravedadTipica.getNivel() + "]";
			 }

			 @Override
			 public boolean equals(Object obj) {
				 if (this == obj) return true;
				 if (obj == null || getClass() != obj.getClass()) return false;
				 Enfermedades that = (Enfermedades) obj;
				 return nombre != null ? nombre.equals(that.nombre) : that.nombre == null;
			 }

			 @Override
			 public int hashCode() {
				 return nombre != null ? nombre.hashCode() : 0;
			 }

			 // ========== ENUMS INTERNOS (igual que antes) ==========

			 /**
			  * Enum interno para categorías de enfermedades
			  */
			 public enum Categoria {
				 RESPIRATORIA("Respiratoria"),
				 GASTROINTESTINAL("Gastrointestinal"),
				 DERMATOLOGICA("Dermatológica"),
				 NEUROLOGICA("Neurológica"),
				 TRANSMITIDA_VECTOR("Transmitida por vector"),
				 INFECCIOSA("Infecciosa"),
				 CRONICA("Crónica"),
				 SINDROME("Síndrome clínico");

				 private final String nombre;

				 Categoria(String nombre) {
					 this.nombre = nombre;
				 }

				 public String getNombre() {
					 return nombre;
				 }
			 }

			 /**
			  * Enum interno para gravedad típica de enfermedades
			  */
			 public enum Gravedad {
				 LEVE("Leve", 1),
				 MODERADA("Moderada", 2),
				 GRAVE("Grave", 3),
				 MUY_GRAVE("Muy Grave", 4),
				 VARIABLE("Variable", 0);

				 private final String nivel;
				 private final int valor;

				 Gravedad(String nivel, int valor) {
					 this.nivel = nivel;
					 this.valor = valor;
				 }

				 public String getNivel() {
					 return nivel;
				 }

				 public int getValor() {
					 return valor;
				 }
			 }

			 /**
			  * Clase auxiliar para diagnósticos con probabilidad
			  */
			 public static class DiagnosticoEnfermedad {
				 private final Enfermedades enfermedad;
				 private final double probabilidad;

				 public DiagnosticoEnfermedad(Enfermedades enfermedad, double probabilidad) {
					 this.enfermedad = enfermedad;
					 this.probabilidad = probabilidad;
				 }

				 public Enfermedades getEnfermedad() {
					 return enfermedad;
				 }

				 public double getProbabilidad() {
					 return probabilidad;
				 }

				 public String getProbabilidadFormateada() {
					 return String.format("%.1f%%", probabilidad);
				 }

				 @Override
				 public String toString() {
					 return String.format("%s (%.1f%%)", enfermedad.getNombre(), probabilidad);
				 }
			 }
}