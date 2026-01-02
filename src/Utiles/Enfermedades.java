package Utiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Enum que representa las principales enfermedades que pueden ser diagnosticadas
 * basándose en los síntomas presentados por el paciente.
 * Versión compatible con Java 1.8.0
 */
public enum Enfermedades {
    
    // Enfermedades respiratorias
    COVID_19("COVID-19", Categoria.RESPIRATORIA, Gravedad.VARIABLE, 
             Arrays.asList(Sintomas.FIEBRE, Sintomas.TOS, Sintomas.DIFICULTAD_RESPIRAR, 
                          Sintomas.CANSANCIO, Sintomas.DOLOR_MUSCULAR, Sintomas.DOLOR_CABEZA,
                          Sintomas.DOLOR_GARGANTA, Sintomas.PERDIDA_APETITO),
             "SARS-CoV-2"),
    
    INFLUENZA("Influenza (Gripe)", Categoria.RESPIRATORIA, Gravedad.MODERADA,
              Arrays.asList(Sintomas.FIEBRE, Sintomas.TOS, Sintomas.DOLOR_GARGANTA,
                           Sintomas.CONGESTION_NASAL, Sintomas.DOLOR_MUSCULAR,
                           Sintomas.DOLOR_CABEZA, Sintomas.CANSANCIO),
              "Virus de la influenza"),
    
    RESFRIADO_COMUN("Resfriado común", Categoria.RESPIRATORIA, Gravedad.LEVE,
                   Arrays.asList(Sintomas.CONGESTION_NASAL, Sintomas.ESTORNUDOS,
                                Sintomas.DOLOR_GARGANTA, Sintomas.TOS,
                                Sintomas.DOLOR_CABEZA),
                   "Rhinovirus/Coronavirus"),
    
    NEUMONIA("Neumonía", Categoria.RESPIRATORIA, Gravedad.GRAVE,
            Arrays.asList(Sintomas.FIEBRE, Sintomas.TOS, Sintomas.DIFICULTAD_RESPIRAR,
                         Sintomas.DOLOR_TORACICO, Sintomas.CANSANCIO,
                         Sintomas.ESCALOFRIOS),
            "Bacterias/Virus"),
    
    BRONQUITIS("Bronquitis aguda", Categoria.RESPIRATORIA, Gravedad.MODERADA,
               Arrays.asList(Sintomas.TOS, Sintomas.CONGESTION_NASAL, Sintomas.CANSANCIO,
                            Sintomas.DOLOR_TORACICO, Sintomas.DIFICULTAD_RESPIRAR),
               "Virus/Bacterias"),
    
    ASMA("Asma", Categoria.RESPIRATORIA, Gravedad.VARIABLE,
         Arrays.asList(Sintomas.DIFICULTAD_RESPIRAR, Sintomas.TOS, Sintomas.CONGESTION_NASAL),
         "Trastorno inflamatorio crónico"),
    
    // Enfermedades gastrointestinales
    GASTROENTERITIS("Gastroenteritis", Categoria.GASTROINTESTINAL, Gravedad.MODERADA,
                   Arrays.asList(Sintomas.DIARREA, Sintomas.VOMITOS, Sintomas.NAUSEAS,
                                Sintomas.DOLOR_ABDOMINAL, Sintomas.FIEBRE),
                   "Virus/Bacterias/Parásitos"),
    
    INTOXICACION_ALIMENTARIA("Intoxicación alimentaria", Categoria.GASTROINTESTINAL, Gravedad.MODERADA,
                            Arrays.asList(Sintomas.VOMITOS, Sintomas.DIARREA, Sintomas.DOLOR_ABDOMINAL,
                                         Sintomas.NAUSEAS, Sintomas.FIEBRE),
                            "Bacterias/Toxinas"),
    
    COLITIS("Colitis", Categoria.GASTROINTESTINAL, Gravedad.MODERADA,
           Arrays.asList(Sintomas.DOLOR_ABDOMINAL, Sintomas.DIARREA,
                        Sintomas.NAUSEAS, Sintomas.PERDIDA_APETITO),
           "Inflamación del colon"),
    
    APENDICITIS("Apendicitis", Categoria.GASTROINTESTINAL, Gravedad.GRAVE,
               Arrays.asList(Sintomas.DOLOR_ABDOMINAL, Sintomas.NAUSEAS, Sintomas.VOMITOS,
                            Sintomas.FIEBRE, Sintomas.PERDIDA_APETITO),
               "Inflamación del apéndice"),
    
    // Enfermedades transmitidas por vectores
    DENGUE("Dengue", Categoria.TRANSMITIDA_VECTOR, Gravedad.GRAVE,
          Arrays.asList(Sintomas.FIEBRE, Sintomas.DOLOR_MUSCULAR, Sintomas.DOLOR_ARTICULAR,
                       Sintomas.DOLOR_CABEZA, Sintomas.ERUPCION_CUTANEA,
                       Sintomas.NAUSEAS, Sintomas.VOMITOS, Sintomas.HEMORRAGIAS),
          "Virus del dengue (Aedes aegypti)"),
    
    ZIKA("Zika", Categoria.TRANSMITIDA_VECTOR, Gravedad.MODERADA,
        Arrays.asList(Sintomas.FIEBRE, Sintomas.ERUPCION_CUTANEA, Sintomas.DOLOR_ARTICULAR,
                     Sintomas.DOLOR_MUSCULAR, Sintomas.DOLOR_CABEZA, Sintomas.CONJUNTIVITIS),
        "Virus Zika (Aedes aegypti)"),
    
    CHIKUNGUNYA("Chikungunya", Categoria.TRANSMITIDA_VECTOR, Gravedad.MODERADA,
               Arrays.asList(Sintomas.FIEBRE, Sintomas.DOLOR_ARTICULAR, Sintomas.DOLOR_MUSCULAR,
                            Sintomas.DOLOR_CABEZA, Sintomas.ERUPCION_CUTANEA,
                            Sintomas.NAUSEAS, Sintomas.CANSANCIO),
               "Virus Chikungunya (Aedes aegypti)"),
    
    MALARIA("Malaria", Categoria.TRANSMITIDA_VECTOR, Gravedad.GRAVE,
           Arrays.asList(Sintomas.FIEBRE, Sintomas.ESCALOFRIOS, Sintomas.DOLOR_CABEZA,
                        Sintomas.NAUSEAS, Sintomas.VOMITOS, Sintomas.DOLOR_MUSCULAR,
                        Sintomas.CANSANCIO),
           "Plasmodium (Anopheles)"),
    
    // Enfermedades dermatológicas
    VARICELA("Varicela", Categoria.DERMATOLOGICA, Gravedad.MODERADA,
            Arrays.asList(Sintomas.FIEBRE, Sintomas.ERUPCION_CUTANEA, Sintomas.PICOR,
                         Sintomas.DOLOR_CABEZA, Sintomas.CANSANCIO, Sintomas.PERDIDA_APETITO),
            "Virus varicela-zóster"),
    
    SARAMPION("Sarampión", Categoria.DERMATOLOGICA, Gravedad.GRAVE,
             Arrays.asList(Sintomas.FIEBRE, Sintomas.ERUPCION_CUTANEA, Sintomas.TOS,
                          Sintomas.CONGESTION_NASAL, Sintomas.CONJUNTIVITIS),
             "Virus del sarampión"),
    
    RUBEOLA("Rubéola", Categoria.DERMATOLOGICA, Gravedad.MODERADA,
           Arrays.asList(Sintomas.FIEBRE, Sintomas.ERUPCION_CUTANEA, Sintomas.GANGLIOS_INFLAMADOS,
                        Sintomas.DOLOR_ARTICULAR, Sintomas.DOLOR_CABEZA),
           "Virus de la rubéola"),
    
    // Enfermedades neurológicas
    MENINGITIS("Meningitis", Categoria.NEUROLOGICA, Gravedad.GRAVE,
              Arrays.asList(Sintomas.FIEBRE, Sintomas.DOLOR_CABEZA, Sintomas.RIGIDEZ,
                           Sintomas.NAUSEAS, Sintomas.VOMITOS, Sintomas.CONFUSION,
                           Sintomas.FOTOSENSIBILIDAD),
              "Bacterias/Virus"),
    
    ENCEFALITIS("Encefalitis", Categoria.NEUROLOGICA, Gravedad.GRAVE,
               Arrays.asList(Sintomas.FIEBRE, Sintomas.DOLOR_CABEZA, Sintomas.CONFUSION,
                            Sintomas.CONVULSIONES, Sintomas.PERDIDA_CONOCIMIENTO,
                            Sintomas.MAREO),
               "Virus"),
    
    MIGRANA("Migraña", Categoria.NEUROLOGICA, Gravedad.MODERADA,
           Arrays.asList(Sintomas.DOLOR_CABEZA, Sintomas.NAUSEAS, Sintomas.VOMITOS,
                        Sintomas.FOTOSENSIBILIDAD, Sintomas.MAREO),
           "Trastorno neurológico"),
    
    // Enfermedades crónicas
    HIPERTENSION("Hipertensión arterial", Categoria.CRONICA, Gravedad.GRAVE,
                Arrays.asList(Sintomas.DOLOR_CABEZA, Sintomas.MAREO, Sintomas.CONFUSION,
                             Sintomas.DOLOR_TORACICO, Sintomas.DIFICULTAD_RESPIRAR),
                "Trastorno cardiovascular"),
    
    DIABETES("Diabetes mellitus", Categoria.CRONICA, Gravedad.GRAVE,
            Arrays.asList(Sintomas.CANSANCIO, Sintomas.PERDIDA_APETITO, Sintomas.NAUSEAS,
                         Sintomas.VOMITOS, Sintomas.CONFUSION, Sintomas.PERDIDA_CONOCIMIENTO),
            "Trastorno metabólico"),
    
    ARTRITIS("Artritis reumatoide", Categoria.CRONICA, Gravedad.MODERADA,
            Arrays.asList(Sintomas.DOLOR_ARTICULAR, Sintomas.RIGIDEZ, Sintomas.DOLOR_MUSCULAR,
                         Sintomas.CANSANCIO, Sintomas.FIEBRE),
            "Enfermedad autoinmune"),
    
    // Enfermedades infecciosas específicas
    TUBERCULOSIS("Tuberculosis", Categoria.INFECCIOSA, Gravedad.GRAVE,
                Arrays.asList(Sintomas.TOS, Sintomas.FIEBRE, Sintomas.CANSANCIO,
                             Sintomas.PERDIDA_APETITO, Sintomas.DOLOR_TORACICO,
                             Sintomas.DIFICULTAD_RESPIRAR),
                "Mycobacterium tuberculosis"),
    
    HEPATITIS("Hepatitis viral", Categoria.INFECCIOSA, Gravedad.GRAVE,
             Arrays.asList(Sintomas.ICTERICIA, Sintomas.CANSANCIO, Sintomas.NAUSEAS,
                          Sintomas.DOLOR_ABDOMINAL, Sintomas.PERDIDA_APETITO,
                          Sintomas.FIEBRE),
             "Virus de la hepatitis"),
    
    // Síndrome clínico
    SINDROME_GRIPAL("Síndrome gripal", Categoria.SINDROME, Gravedad.LEVE,
                   Arrays.asList(Sintomas.FIEBRE, Sintomas.TOS, Sintomas.DOLOR_GARGANTA,
                                Sintomas.CONGESTION_NASAL, Sintomas.DOLOR_MUSCULAR,
                                Sintomas.DOLOR_CABEZA, Sintomas.CANSANCIO),
                   "Conjunto de síntomas respiratorios");
    
    // Atributos del enum
    private final String nombre;
    private final Categoria categoria;
    private final Gravedad gravedadTipica;
    private final List<Sintomas> sintomasComunes;
    private final String agenteEtiologico;
    
    /**
     * Constructor del enum
     */
    Enfermedades(String nombre, Categoria categoria, Gravedad gravedadTipica, 
                 List<Sintomas> sintomasComunes, String agenteEtiologico) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.gravedadTipica = gravedadTipica;
        this.sintomasComunes = sintomasComunes;
        this.agenteEtiologico = agenteEtiologico;
    }
    
    // Getters
    public String getNombre() {
        return nombre;
    }
    
    public Categoria getCategoria() {
        return categoria;
    }
    
    public Gravedad getGravedadTipica() {
        return gravedadTipica;
    }
    
    public List<Sintomas> getSintomasComunes() {
        return new ArrayList<>(sintomasComunes); // Devuelve copia para evitar modificaciones
    }
    
    public String getAgenteEtiologico() {
        return agenteEtiologico;
    }
    
    /**
     * Calcula la probabilidad de esta enfermedad basada en síntomas presentes
     */
    public double calcularProbabilidad(List<Sintomas> sintomasPaciente) {
        if (sintomasPaciente == null || sintomasPaciente.isEmpty()) {
            return 0.0;
        }
        
        int coincidencias = 0;
        for (Sintomas sintoma : sintomasPaciente) {
            if (sintomasComunes.contains(sintoma)) {
                coincidencias++;
            }
        }
        
        // Cálculo simple de probabilidad
        double porcentaje = (double) coincidencias / sintomasComunes.size() * 100;
        
        // Ajuste por gravedad
        if (coincidencias >= sintomasComunes.size() * 0.7) { // Si coincide al menos 70%
            porcentaje += 10; // Aumentar probabilidad
        }
        
        return Math.min(porcentaje, 100.0); // Máximo 100%
    }
    
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
     * Método para obtener enfermedades por categoría
     */
    public static Enfermedades[] getEnfermedadesPorCategoria(Categoria categoria) {
        List<Enfermedades> resultado = new ArrayList<Enfermedades>();
        for (Enfermedades e : Enfermedades.values()) {
            if (e.getCategoria() == categoria) {
                resultado.add(e);
            }
        }
        return resultado.toArray(new Enfermedades[resultado.size()]);
    }
    
    /**
     * Método para obtener enfermedades por gravedad
     */
    public static Enfermedades[] getEnfermedadesPorGravedad(Gravedad gravedad) {
        List<Enfermedades> resultado = new ArrayList<Enfermedades>();
        for (Enfermedades e : Enfermedades.values()) {
            if (e.getGravedadTipica() == gravedad) {
                resultado.add(e);
            }
        }
        return resultado.toArray(new Enfermedades[resultado.size()]);
    }
    
    /**
     * Método para diagnosticar enfermedades basadas en síntomas
     */
    public static List<DiagnosticoEnfermedad> diagnosticar(List<Sintomas> sintomasPaciente) {
        List<DiagnosticoEnfermedad> diagnosticos = new ArrayList<DiagnosticoEnfermedad>();
        
        if (sintomasPaciente == null || sintomasPaciente.isEmpty()) {
            return diagnosticos;
        }
        
        for (Enfermedades enfermedad : Enfermedades.values()) {
            double probabilidad = enfermedad.calcularProbabilidad(sintomasPaciente);
            if (probabilidad > 30.0) { // Umbral mínimo del 30%
                diagnosticos.add(new DiagnosticoEnfermedad(enfermedad, probabilidad));
            }
        }
        
        // Ordenar por probabilidad descendente
        //diagnosticos.sort((d1, d2) -> Double.compare(d2.getProbabilidad(), d1.getProbabilidad()));
        
        return diagnosticos;
    }
    
    /**
     * Método para buscar enfermedades por nombre
     */
    public static List<Enfermedades> buscarPorNombre(String texto) {
        String textoBusqueda = texto.toLowerCase();
        List<Enfermedades> resultado = new ArrayList<Enfermedades>();
        for (Enfermedades e : Enfermedades.values()) {
            if (e.getNombre().toLowerCase().contains(textoBusqueda)) {
                resultado.add(e);
            }
        }
        return resultado;
    }
    
    /**
     * Método para obtener enfermedades epidémicas comunes
     */
    public static List<Enfermedades> getEnfermedadesEpidemicasComunes() {
        return Arrays.asList(
            COVID_19, INFLUENZA, DENGUE, GASTROENTERITIS,
            VARICELA, RESFRIADO_COMUN
        );
    }
    
    /**
     * Método para obtener enfermedades respiratorias epidémicas
     */
    public static List<Enfermedades> getEnfermedadesRespiratoriasEpidemicas() {
        return Arrays.asList(
            COVID_19, INFLUENZA, RESFRIADO_COMUN, NEUMONIA,
            BRONQUITIS
        );
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
    
    /**
     * Método para obtener coincidencia de síntomas
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
     * Método para ver si es una enfermedad urgente
     */
    public boolean esUrgente() {
        return gravedadTipica == Gravedad.GRAVE || gravedadTipica == Gravedad.MUY_GRAVE;
    }
    
    /**
     * Método para obtener recomendaciones básicas
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
    
    @Override
    public String toString() {
        return nombre + " [" + categoria.getNombre() + " - " + gravedadTipica.getNivel() + "]";
    }
    
    /**
     * Método para generar reporte completo
     */
    public String generarReporte() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("ENFERMEDAD: ").append(nombre).append("\n");
        reporte.append("Categoría: ").append(categoria.getNombre()).append("\n");
        reporte.append("Gravedad típica: ").append(gravedadTipica.getNivel()).append("\n");
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
}