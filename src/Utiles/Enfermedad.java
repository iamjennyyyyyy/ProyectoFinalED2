package Utiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Enfermedad {
	// Atributos
	private String nombre;
	private Categoria categoria;
	private Gravedad gravedadTipica;
	private ArrayList<Sintomas> sintomasComunes;
	private String agenteEtiologico;
	private String duracion;
	private String ruta;
	
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
			List<Sintomas> sintomasComunes, String agenteEtiologico, String duracion, String ruta) {
		setNombre(nombre);
		setCategoria(categoria);
		setGravedadTipica(gravedadTipica);
		this.sintomasComunes = new ArrayList<>(sintomasComunes);
		setAgenteEtiologico(agenteEtiologico);
		setDuracion(duracion);
		setRuta(ruta);
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
	
	public Enfermedad() {
		this.sintomasComunes = new ArrayList<>();
	}

	// ========== GETTERS Y SETTERS ==========

	public Enfermedad(String string) {
		nombre = string;
	}
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
	
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public ImageIcon getImage(){
		return new ImageIcon(ruta);
	}
	
	public String toStringSintomas(){
		String mensaje = "";
		for(int i = 0; i < sintomasComunes.size(); i++){
			if (i == sintomasComunes.size()-1)
				mensaje += sintomasComunes.get(i).getDescripcion() + ".";
			else
				mensaje += sintomasComunes.get(i).getDescripcion() + ", ";
		}
		return mensaje;
	}

	// ========== M�TODOS DE INSTANCIA ==========

	/**
	 * Calcula la probabilidad de esta enfermedad basada en s�ntomas presentes
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
	
	public boolean enfermedadConCantidadDeSintomas(List<Sintomas> sintomasPaciente, int cant) {
		
		int coincidencias = 0;
		for (int i = 0; i < sintomasPaciente.size(); i++) {
			if (sintomasComunes.contains(sintomasPaciente.get(i))) {
				coincidencias++;
			}
		}

		return coincidencias >= cant;
	}

	/**
	 * Obtiene coincidencias de s�ntomas
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
	 * Obtiene recomendaciones b�sicas
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
//	 * Genera reporte completo
//	 */
//	public String generarReporte() {
//		StringBuilder reporte = new StringBuilder();
//		reporte.append("ENFERMEDAD: ").append(nombre).append("\n");
//		reporte.append("Categoría: ").append(categoria.getNombre()).append("\n");
//		reporte.append("Gravedad típica: ").append(gravedadTipica.getNivel()).append("\n");
//		reporte.append("Duración estimada: ").append(duracion).append("\n");
//		reporte.append("Agente etiológico: ").append(agenteEtiologico).append("\n");
//		reporte.append("\nSíntomas comunes:\n");
//
//		for (Sintomas sintoma : sintomasComunes) {
//			reporte.append("� ").append(sintoma.getDescripcion())
//			.append(" (").append(sintoma.getCategoria().getNombre())
//			.append(" - ").append(sintoma.getGravedadBase().getNivel())
//			.append(")\n");
//		}
//
//		reporte.append("\nRecomendación: ").append(getRecomendacionBasica());
//
//		return reporte.toString();
//	}

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

//	@Override
//	public int hashCode() {
//		return nombre != null ? nombre.hashCode() : 0;
//	}

	// ========== ENUMS INTERNOS (igual que antes) ==========

	/**
	 * Enum interno para categor�as de enfermedades
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
	 * Enum interno para gravedad t�pica de enfermedades
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
	 * Clase auxiliar para diagn�sticos con probabilidad
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