package Utiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Enum que representa los principales síntomas que puede presentar un paciente
 * para realizar el diagnóstico epidemiológico.
 * Versión compatible con Java 1.8.0
 */
public enum Sintomas {
    
    // Síntomas generales
    FIEBRE("Fiebre", Categoria.GENERAL, Gravedad.LEVE),
    CANSANCIO("Cansancio/Fatiga", Categoria.GENERAL, Gravedad.LEVE),
    DOLOR_CABEZA("Dolor de cabeza", Categoria.GENERAL, Gravedad.LEVE),
    ESCALOFRIOS("Escalofríos", Categoria.GENERAL, Gravedad.LEVE),
    MALESTAR_GENERAL("Malestar general", Categoria.GENERAL, Gravedad.LEVE),
    
    // Síntomas respiratorios
    TOS("Tos", Categoria.RESPIRATORIO, Gravedad.LEVE),
    DIFICULTAD_RESPIRAR("Dificultad para respirar", Categoria.RESPIRATORIO, Gravedad.GRAVE),
    DOLOR_GARGANTA("Dolor de garganta", Categoria.RESPIRATORIO, Gravedad.LEVE),
    CONGESTION_NASAL("Congestión nasal", Categoria.RESPIRATORIO, Gravedad.LEVE),
    ESTORNUDOS("Estornudos", Categoria.RESPIRATORIO, Gravedad.LEVE),
    
    // Síntomas gastrointestinales
    NAUSEAS("Náuseas", Categoria.GASTROINTESTINAL, Gravedad.LEVE),
    VOMITOS("Vómitos", Categoria.GASTROINTESTINAL, Gravedad.MODERADA),
    DIARREA("Diarrea", Categoria.GASTROINTESTINAL, Gravedad.MODERADA),
    DOLOR_ABDOMINAL("Dolor abdominal", Categoria.GASTROINTESTINAL, Gravedad.MODERADA),
    PERDIDA_APETITO("Pérdida de apetito", Categoria.GASTROINTESTINAL, Gravedad.LEVE),
    
    // Síntomas dermatológicos
    ERUPCION_CUTANEA("Erupción cutánea", Categoria.DERMATOLOGICO, Gravedad.LEVE),
    PICOR("Picor/Prurito", Categoria.DERMATOLOGICO, Gravedad.LEVE),
    URTICARIA("Urticaria", Categoria.DERMATOLOGICO, Gravedad.MODERADA),
    
    // Síntomas neurológicos
    MAREO("Mareo/Vértigo", Categoria.NEUROLOGICO, Gravedad.MODERADA),
    CONFUSION("Confusión/Desorientación", Categoria.NEUROLOGICO, Gravedad.GRAVE),
    PERDIDA_CONOCIMIENTO("Pérdida del conocimiento", Categoria.NEUROLOGICO, Gravedad.GRAVE),
    CONVULSIONES("Convulsiones", Categoria.NEUROLOGICO, Gravedad.GRAVE),
    
    // Síntomas musculoesqueléticos
    DOLOR_MUSCULAR("Dolor muscular (Mialgia)", Categoria.MUSCULOESQUELETICO, Gravedad.LEVE),
    DOLOR_ARTICULAR("Dolor articular (Artralgia)", Categoria.MUSCULOESQUELETICO, Gravedad.MODERADA),
    RIGIDEZ("Rigidez muscular", Categoria.MUSCULOESQUELETICO, Gravedad.LEVE),
    
    // Síntomas específicos de enfermedades epidémicas
    HEMORRAGIAS("Hemorragias/Sangrado", Categoria.ESPECIFICO, Gravedad.GRAVE),
    ICTERICIA("Ictericia (Coloración amarilla)", Categoria.ESPECIFICO, Gravedad.GRAVE),
    GANGLIOS_INFLAMADOS("Ganglios inflamados", Categoria.ESPECIFICO, Gravedad.MODERADA),
    FOTOSENSIBILIDAD("Fotosensibilidad", Categoria.ESPECIFICO, Gravedad.MODERADA),
    DOLOR_TORACICO("Dolor torácico", Categoria.ESPECIFICO, Gravedad.GRAVE);
    
    // Atributos del enum
    private final String descripcion;
    private final Categoria categoria;
    private final Gravedad gravedadBase;
    
    /**
     * Constructor del enum
     */
    Sintomas(String descripcion, Categoria categoria, Gravedad gravedadBase) {
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.gravedadBase = gravedadBase;
    }
    
    // Getters
    public String getDescripcion() {
        return descripcion;
    }
    
    public Categoria getCategoria() {
        return categoria;
    }
    
    public Gravedad getGravedadBase() {
        return gravedadBase;
    }
    
    /**
     * Enum interno para categorías de síntomas
     */
    public enum Categoria {
        GENERAL("General"),
        RESPIRATORIO("Respiratorio"),
        GASTROINTESTINAL("Gastrointestinal"),
        DERMATOLOGICO("Dermatológico"),
        NEUROLOGICO("Neurológico"),
        MUSCULOESQUELETICO("Musculoesquelético"),
        ESPECIFICO("Específico");
        
        private final String nombre;
        
        Categoria(String nombre) {
            this.nombre = nombre;
        }
        
        public String getNombre() {
            return nombre;
        }
    }
    
    /**
     * Enum interno para niveles de gravedad
     */
    public enum Gravedad {
        LEVE("Leve", 1),
        MODERADA("Moderada", 2),
        GRAVE("Grave", 3),
        MUY_GRAVE("Muy Grave", 4);
        
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
     * Método para obtener síntomas por categoría (Java 8 style)
     */
    public static Sintomas[] getSintomasPorCategoria(Categoria categoria) {
        List<Sintomas> resultado = new ArrayList<Sintomas>();
        for (Sintomas s : Sintomas.values()) {
            if (s.getCategoria() == categoria) {
                resultado.add(s);
            }
        }
        return resultado.toArray(new Sintomas[resultado.size()]);
    }
    
    /**
     * Método para obtener síntomas por gravedad (Java 8 style)
     */
    public static Sintomas[] getSintomasPorGravedad(Gravedad gravedad) {
        List<Sintomas> resultado = new ArrayList<Sintomas>();
        for (Sintomas s : Sintomas.values()) {
            if (s.getGravedadBase() == gravedad) {
                resultado.add(s);
            }
        }
        return resultado.toArray(new Sintomas[resultado.size()]);
    }
    
    /**
     * Método para buscar síntomas que contengan texto en su descripción (Java 8 style)
     */
    public static List<Sintomas> buscarPorTexto(String texto) {
        String textoBusqueda = texto.toLowerCase();
        List<Sintomas> resultado = new ArrayList<Sintomas>();
        for (Sintomas s : Sintomas.values()) {
            if (s.getDescripcion().toLowerCase().contains(textoBusqueda)) {
                resultado.add(s);
            }
        }
        return resultado;
    }
    
    /**
     * Método para obtener todos los síntomas de enfermedades respiratorias
     */
    public static List<Sintomas> getSintomasRespiratoriosEpidemicos() {
        return Arrays.asList(
            FIEBRE, TOS, DIFICULTAD_RESPIRAR, DOLOR_GARGANTA,
            CANSANCIO, DOLOR_MUSCULAR, DOLOR_CABEZA, PERDIDA_APETITO
        );
    }
    
    /**
     * Método para obtener síntomas de enfermedades gastrointestinales epidémicas
     */
    public static List<Sintomas> getSintomasGastrointestinalesEpidemicos() {
        return Arrays.asList(
            DIARREA, VOMITOS, NAUSEAS, DOLOR_ABDOMINAL,
            FIEBRE, ESCALOFRIOS, CANSANCIO
        );
    }
    
    /**
     * Método para obtener síntomas de enfermedades transmitidas por vectores
     */
    public static List<Sintomas> getSintomasArbovirosis() {
        return Arrays.asList(
            FIEBRE, DOLOR_MUSCULAR, DOLOR_ARTICULAR, DOLOR_CABEZA,
            ERUPCION_CUTANEA, NAUSEAS, VOMITOS, HEMORRAGIAS
        );
    }
    
    /**
     * Método auxiliar para verificar si un síntoma está en una lista
     */
    public static boolean contieneSintomaEnLista(Sintomas sintoma, List<Sintomas> lista) {
        return lista.contains(sintoma);
    }
    
    @Override
    public String toString() {
        return descripcion + " [" + categoria.getNombre() + " - " + gravedadBase.getNivel() + "]";
    }
}