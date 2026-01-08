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
public class Enfermedad {

	// Atributos
	private String nombre;
	private Categoria categoria;
	private Gravedad gravedadTipica;
	private List<Sintomas> sintomasComunes;
	private String agenteEtiologico;
	private String duracion; // Nuevo atributo
	

	// Enfermedades predefinidas (simulando el enum)
	private static ArrayList<Enfermedad> enfermedadesPredefinidas;

	/**
	 * Constructor completo
	 */
	public Enfermedad(String nombre, Categoria categoria, Gravedad gravedadTipica, 
			List<Sintomas> sintomasComunes, String agenteEtiologico, String duracion, LocalDate fechaDiagnostico) {
		setNombre(nombre);
		setCategoria(categoria);
		setGravedadTipica(gravedadTipica);
		this.sintomasComunes = new ArrayList<>(sintomasComunes);
		setAgenteEtiologico(agenteEtiologico);
		setDuracion(duracion);
		
	}
	public Enfermedad(String nombre, Categoria categoria, Gravedad gravedadTipica, 
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
	public Enfermedad() {
		this.sintomasComunes = new ArrayList<>();
	}

<<<<<<< HEAD
<<<<<<< HEAD:src/Utiles/Enfermedad.java
=======
<<<<<<< HEAD:src/Utiles/Enfermedades.java
=======
>>>>>>> d0541c45525a050d78843615ca23629464ea58f1
	// ========== MÉTODO PARA INICIALIZAR ENFERMEDADES PREDEFINIDAS ==========

	/**
	 * Inicializa las enfermedades predefinidas (equivalente al enum)
	 */
	public static void inicializarEnfermedadesPredefinidas() {
		enfermedadesPredefinidas = new ArrayList<>();

		// Enfermedades respiratorias
		enfermedadesPredefinidas.add(new Enfermedad(
				"COVID-19", Categoria.RESPIRATORIA, Gravedad.VARIABLE,
				Arrays.asList(Sintomas.FIEBRE, Sintomas.TOS, Sintomas.DIFICULTAD_RESPIRAR,
						Sintomas.CANSANCIO, Sintomas.DOLOR_MUSCULAR, Sintomas.DOLOR_CABEZA,
						Sintomas.DOLOR_GARGANTA, Sintomas.PERDIDA_APETITO),
						"SARS-CoV-2", "7-14 días"
				));

		enfermedadesPredefinidas.add(new Enfermedad(
				"Influenza (Gripe)", Categoria.RESPIRATORIA, Gravedad.MODERADA,
				Arrays.asList(Sintomas.FIEBRE, Sintomas.TOS, Sintomas.DOLOR_GARGANTA,
						Sintomas.CONGESTION_NASAL, Sintomas.DOLOR_MUSCULAR,
						Sintomas.DOLOR_CABEZA, Sintomas.CANSANCIO),
						"Virus de la influenza", "5-7 días"
				));

		enfermedadesPredefinidas.add(new Enfermedad(
				"Resfriado común", Categoria.RESPIRATORIA, Gravedad.LEVE,
				Arrays.asList(Sintomas.CONGESTION_NASAL, Sintomas.ESTORNUDOS,
						Sintomas.DOLOR_GARGANTA, Sintomas.TOS,
						Sintomas.DOLOR_CABEZA),
						"Rhinovirus/Coronavirus", "3-10 días"
				));

		enfermedadesPredefinidas.add(new Enfermedad(
				"Neumonía", Categoria.RESPIRATORIA, Gravedad.GRAVE,
				Arrays.asList(Sintomas.FIEBRE, Sintomas.TOS, Sintomas.DIFICULTAD_RESPIRAR,
						Sintomas.DOLOR_TORACICO, Sintomas.CANSANCIO,
						Sintomas.ESCALOFRIOS),
						"Bacterias/Virus", "2-3 semanas"
				));

		enfermedadesPredefinidas.add(new Enfermedad(
				"Bronquitis aguda", Categoria.RESPIRATORIA, Gravedad.MODERADA,
				Arrays.asList(Sintomas.TOS, Sintomas.CONGESTION_NASAL, Sintomas.CANSANCIO,
						Sintomas.DOLOR_TORACICO, Sintomas.DIFICULTAD_RESPIRAR),
						"Virus/Bacterias", "1-3 semanas"
				));

		enfermedadesPredefinidas.add(new Enfermedad(
				"Asma", Categoria.RESPIRATORIA, Gravedad.VARIABLE,
				Arrays.asList(Sintomas.DIFICULTAD_RESPIRAR, Sintomas.TOS, Sintomas.CONGESTION_NASAL),
				"Trastorno inflamatorio crónico", "Crónica (controlable)"
				));

		// Enfermedades gastrointestinales
		enfermedadesPredefinidas.add(new Enfermedad(
				"Gastroenteritis", Categoria.GASTROINTESTINAL, Gravedad.MODERADA,
				Arrays.asList(Sintomas.DIARREA, Sintomas.VOMITOS, Sintomas.NAUSEAS,
						Sintomas.DOLOR_ABDOMINAL, Sintomas.FIEBRE),
						"Virus/Bacterias/Parásitos", "1-3 días"
				));

		enfermedadesPredefinidas.add(new Enfermedad(
				"Intoxicación alimentaria", Categoria.GASTROINTESTINAL, Gravedad.MODERADA,
				Arrays.asList(Sintomas.VOMITOS, Sintomas.DIARREA, Sintomas.DOLOR_ABDOMINAL,
						Sintomas.NAUSEAS, Sintomas.FIEBRE),
						"Bacterias/Toxinas", "24-48 horas"
				));

		enfermedadesPredefinidas.add(new Enfermedad(
				"Colitis", Categoria.GASTROINTESTINAL, Gravedad.MODERADA,
				Arrays.asList(Sintomas.DOLOR_ABDOMINAL, Sintomas.DIARREA,
						Sintomas.NAUSEAS, Sintomas.PERDIDA_APETITO),
						"Inflamación del colon", "Variable (aguda/crónica)"
				));

		enfermedadesPredefinidas.add(new Enfermedad(
				"Apendicitis", Categoria.GASTROINTESTINAL, Gravedad.GRAVE,
				Arrays.asList(Sintomas.DOLOR_ABDOMINAL, Sintomas.NAUSEAS, Sintomas.VOMITOS,
						Sintomas.FIEBRE, Sintomas.PERDIDA_APETITO),
						"Inflamación del apéndice", "Urgente (requiere cirugía)"
				));

		// Enfermedades transmitidas por vectores
		enfermedadesPredefinidas.add(new Enfermedad(
				"Dengue", Categoria.TRANSMITIDA_VECTOR, Gravedad.GRAVE,
				Arrays.asList(Sintomas.FIEBRE, Sintomas.DOLOR_MUSCULAR, Sintomas.DOLOR_ARTICULAR,
						Sintomas.DOLOR_CABEZA, Sintomas.ERUPCION_CUTANEA,
						Sintomas.NAUSEAS, Sintomas.VOMITOS, Sintomas.HEMORRAGIAS),
						"Virus del dengue (Aedes aegypti)", "7-10 días"
				));

		enfermedadesPredefinidas.add(new Enfermedad(
				"Zika", Categoria.TRANSMITIDA_VECTOR, Gravedad.MODERADA,
				Arrays.asList(Sintomas.FIEBRE, Sintomas.ERUPCION_CUTANEA, Sintomas.DOLOR_ARTICULAR,
						Sintomas.DOLOR_MUSCULAR, Sintomas.DOLOR_CABEZA, Sintomas.CONJUNTIVITIS),
						"Virus Zika (Aedes aegypti)", "2-7 días"
				));

		enfermedadesPredefinidas.add(new Enfermedad(
				"Chikungunya", Categoria.TRANSMITIDA_VECTOR, Gravedad.MODERADA,
				Arrays.asList(Sintomas.FIEBRE, Sintomas.DOLOR_ARTICULAR, Sintomas.DOLOR_MUSCULAR,
						Sintomas.DOLOR_CABEZA, Sintomas.ERUPCION_CUTANEA,
						Sintomas.NAUSEAS, Sintomas.CANSANCIO),
						"Virus Chikungunya (Aedes aegypti)", "3-10 días"
				));

		enfermedadesPredefinidas.add(new Enfermedad(
				"Malaria", Categoria.TRANSMITIDA_VECTOR, Gravedad.GRAVE,
				Arrays.asList(Sintomas.FIEBRE, Sintomas.ESCALOFRIOS, Sintomas.DOLOR_CABEZA,
						Sintomas.NAUSEAS, Sintomas.VOMITOS, Sintomas.DOLOR_MUSCULAR,
						Sintomas.CANSANCIO),
						"Plasmodium (Anopheles)", "Variable (con tratamiento)"
				));

		// Enfermedades dermatológicas
		enfermedadesPredefinidas.add(new Enfermedad(
				"Varicela", Categoria.DERMATOLOGICA, Gravedad.MODERADA,
				Arrays.asList(Sintomas.FIEBRE, Sintomas.ERUPCION_CUTANEA, Sintomas.PICOR,
						Sintomas.DOLOR_CABEZA, Sintomas.CANSANCIO, Sintomas.PERDIDA_APETITO),
						"Virus varicela-zóster", "10-21 días"
				));

		enfermedadesPredefinidas.add(new Enfermedad(
				"Sarampión", Categoria.DERMATOLOGICA, Gravedad.GRAVE,
				Arrays.asList(Sintomas.FIEBRE, Sintomas.ERUPCION_CUTANEA, Sintomas.TOS,
						Sintomas.CONGESTION_NASAL, Sintomas.CONJUNTIVITIS),
						"Virus del sarampión", "7-14 días"
				));

		enfermedadesPredefinidas.add(new Enfermedad(
				"Rubéola", Categoria.DERMATOLOGICA, Gravedad.MODERADA,
				Arrays.asList(Sintomas.FIEBRE, Sintomas.ERUPCION_CUTANEA, Sintomas.GANGLIOS_INFLAMADOS,
						Sintomas.DOLOR_ARTICULAR, Sintomas.DOLOR_CABEZA),
						"Virus de la rubéola", "3-7 días"
				));

		// Enfermedades neurológicas
		enfermedadesPredefinidas.add(new Enfermedad(
				"Meningitis", Categoria.NEUROLOGICA, Gravedad.GRAVE,
				Arrays.asList(Sintomas.FIEBRE, Sintomas.DOLOR_CABEZA, Sintomas.RIGIDEZ,
						Sintomas.NAUSEAS, Sintomas.VOMITOS, Sintomas.CONFUSION,
						Sintomas.FOTOSENSIBILIDAD),
						"Bacterias/Virus", "Variable (urgente)"
				));

		enfermedadesPredefinidas.add(new Enfermedad(
				"Encefalitis", Categoria.NEUROLOGICA, Gravedad.GRAVE,
				Arrays.asList(Sintomas.FIEBRE, Sintomas.DOLOR_CABEZA, Sintomas.CONFUSION,
						Sintomas.CONVULSIONES, Sintomas.PERDIDA_CONOCIMIENTO,
						Sintomas.MAREO),
						"Virus", "Variable (urgente)"
				));

		enfermedadesPredefinidas.add(new Enfermedad(
				"Migraña", Categoria.NEUROLOGICA, Gravedad.MODERADA,
				Arrays.asList(Sintomas.DOLOR_CABEZA, Sintomas.NAUSEAS, Sintomas.VOMITOS,
						Sintomas.FOTOSENSIBILIDAD, Sintomas.MAREO),
						"Trastorno neurológico", "4-72 horas"
				));

		// Enfermedades crónicas
		enfermedadesPredefinidas.add(new Enfermedad(
				"Hipertensión arterial", Categoria.CRONICA, Gravedad.GRAVE,
				Arrays.asList(Sintomas.DOLOR_CABEZA, Sintomas.MAREO, Sintomas.CONFUSION,
						Sintomas.DOLOR_TORACICO, Sintomas.DIFICULTAD_RESPIRAR),
						"Trastorno cardiovascular", "Crónica"
				));

		enfermedadesPredefinidas.add(new Enfermedad(
				"Diabetes mellitus", Categoria.CRONICA, Gravedad.GRAVE,
				Arrays.asList(Sintomas.CANSANCIO, Sintomas.PERDIDA_APETITO, Sintomas.NAUSEAS,
						Sintomas.VOMITOS, Sintomas.CONFUSION, Sintomas.PERDIDA_CONOCIMIENTO),
						"Trastorno metabólico", "Crónica"
				));

		enfermedadesPredefinidas.add(new Enfermedad(
				"Artritis reumatoide", Categoria.CRONICA, Gravedad.MODERADA,
				Arrays.asList(Sintomas.DOLOR_ARTICULAR, Sintomas.RIGIDEZ, Sintomas.DOLOR_MUSCULAR,
						Sintomas.CANSANCIO, Sintomas.FIEBRE),
						"Enfermedad autoinmune", "Crónica"
				));

		// Enfermedades infecciosas específicas
		enfermedadesPredefinidas.add(new Enfermedad(
				"Tuberculosis", Categoria.INFECCIOSA, Gravedad.GRAVE,
				Arrays.asList(Sintomas.TOS, Sintomas.FIEBRE, Sintomas.CANSANCIO,
						Sintomas.PERDIDA_APETITO, Sintomas.DOLOR_TORACICO,
						Sintomas.DIFICULTAD_RESPIRAR),
						"Mycobacterium tuberculosis", "6-9 meses (con tratamiento)"
				));

		enfermedadesPredefinidas.add(new Enfermedad(
				"Hepatitis viral", Categoria.INFECCIOSA, Gravedad.GRAVE,
				Arrays.asList(Sintomas.ICTERICIA, Sintomas.CANSANCIO, Sintomas.NAUSEAS,
						Sintomas.DOLOR_ABDOMINAL, Sintomas.PERDIDA_APETITO,
						Sintomas.FIEBRE),
						"Virus de la hepatitis", "Variable (aguda/crónica)"
				));

		// Síndrome clínico
		enfermedadesPredefinidas.add(new Enfermedad(
				"Síndrome gripal", Categoria.SINDROME, Gravedad.LEVE,
				Arrays.asList(Sintomas.FIEBRE, Sintomas.TOS, Sintomas.DOLOR_GARGANTA,
						Sintomas.CONGESTION_NASAL, Sintomas.DOLOR_MUSCULAR,
						Sintomas.DOLOR_CABEZA, Sintomas.CANSANCIO),
						"Conjunto de síntomas respiratorios", "5-7 días"
				));
	}

<<<<<<< HEAD
=======
>>>>>>> 2aa4cfb2812457fc62d8819a975ff2db2384ea01:src/Utiles/Enfermedades.java
=======
>>>>>>> nickyyyy:src/Utiles/Enfermedad.java
>>>>>>> d0541c45525a050d78843615ca23629464ea58f1
	// ========== MÉTODOS ESTÁTICOS PARA ACCEDER A LAS ENFERMEDADES ==========

	/**
	 * Obtiene todas las enfermedades predefinidas
	 */
	public static ArrayList<Enfermedad> getEnfermedadesPredefinidas() {
		if (enfermedadesPredefinidas == null) {
			Sistema.inicializarEnfermedadesPredefinidas();
		}
		return new ArrayList<>(enfermedadesPredefinidas);
	}

	/**
	 * Obtiene enfermedades por categoría
	 */
	public static ArrayList<Enfermedad> getEnfermedadesPorCategoria(Categoria categoria) {
		ArrayList<Enfermedad> resultado = new ArrayList<>();
		for (Enfermedad e : getEnfermedadesPredefinidas()) {
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
		for (Enfermedad e : getEnfermedadesPredefinidas()) {
			if (e.getGravedadTipica() == gravedad) {
				resultado.add(e);
			}
		}
		return resultado;
	}

	/**
	 * Busca enfermedades por nombre
	 */
	public static List<Enfermedad> buscarPorNombre(String texto) {
		String textoBusqueda = texto.toLowerCase();
		List<Enfermedad> resultado = new ArrayList<>();
		for (Enfermedad e : getEnfermedadesPredefinidas()) {
			if (e.getNombre().toLowerCase().contains(textoBusqueda)) {
				resultado.add(e);
			}
		}
		return resultado;
	}

	/**
	 * Obtiene enfermedades epidémicas comunes
	 */
	public static List<Enfermedad> getEnfermedadesEpidemicasComunes() {
		List<String> nombresComunes = Arrays.asList(
				"COVID-19", "Influenza (Gripe)", "Dengue", 
				"Gastroenteritis", "Varicela", "Resfriado común"
				);

		List<Enfermedad> resultado = new ArrayList<>();
		for (Enfermedad e : getEnfermedadesPredefinidas()) {
			if (nombresComunes.contains(e.getNombre())) {
				resultado.add(e);
			}
		}
		return resultado;
	}

	/**
	 * Obtiene enfermedades por índices (para JList)
	 */
	public static ArrayList<Enfermedad> obtenerEnfermedadesPorIndices(int[] pos) {
		ArrayList<Enfermedad> todas = getEnfermedadesPredefinidas();
		ArrayList<Enfermedad> resultado = new ArrayList<>();
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

		for (Enfermedad enfermedad : getEnfermedadesPredefinidas()) {
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
				 Enfermedad that = (Enfermedad) obj;
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
				 private final Enfermedad enfermedad;
				 private final double probabilidad;

				 public DiagnosticoEnfermedad(Enfermedad enfermedad, double probabilidad) {
					 this.enfermedad = enfermedad;
					 this.probabilidad = probabilidad;
				 }

				 public Enfermedad getEnfermedad() {
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