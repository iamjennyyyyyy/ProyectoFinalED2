package Sistema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import cu.edu.cujae.ceis.graph.LinkedGraph;
import cu.edu.cujae.ceis.graph.interfaces.ILinkedNotDirectedGraph;
import Salud.ConsejoPopular;
import Salud.DireccionMunicipal;
import Salud.Enfermedad;
import Auxiliar.Estado;
import Auxiliar.Mes;
import Auxiliar.Registro;


public class Inicializar {
	public static DireccionMunicipal inicializarPlazaRevolucion(){
	    // Crear enfermedad base para todos los registros
	    Enfermedad enfermedadBase = new Enfermedad("COVID-19");

	    // 1. RAMPA
	    ArrayList<Mes> mesesRampa = new ArrayList<>();
	    mesesRampa.add(new Mes("Enero", 40, Estado.Normal));
	    mesesRampa.add(new Mes("Febrero", 55, Estado.Normal));
	    mesesRampa.add(new Mes("Marzo", 80, Estado.Alerta_Epidemica));
	    mesesRampa.add(new Mes("Abril", 115, Estado.Epidemia));
	    mesesRampa.add(new Mes("Mayo", 90, Estado.Alerta_Epidemica));
	    mesesRampa.add(new Mes("Junio", 65, Estado.Normal));
	    mesesRampa.add(new Mes("Julio", 50, Estado.Normal));
	    mesesRampa.add(new Mes("Agosto", 45, Estado.Normal));
	    mesesRampa.add(new Mes("Septiembre", 70, Estado.Normal));
	    mesesRampa.add(new Mes("Octubre", 95, Estado.Alerta_Epidemica));
	    mesesRampa.add(new Mes("Noviembre", 120, Estado.Epidemia));
	    mesesRampa.add(new Mes("Diciembre", 85, Estado.Alerta_Epidemica));

	    Registro registroRampa2025 = new Registro(2025, mesesRampa);
	    Map<Enfermedad, ArrayList<Registro>> registrosRampa = new HashMap<>();
	    ArrayList<Registro> listaRegistrosRampa = new ArrayList<>();
	    listaRegistrosRampa.add(registroRampa2025);
	    registrosRampa.put(enfermedadBase, listaRegistrosRampa);

	    Map<Estado, ArrayList<Enfermedad>> estadosRampa = new HashMap<>();
	    ArrayList<Enfermedad> enfermedadesAlertaRampa = new ArrayList<>();
	    enfermedadesAlertaRampa.add(enfermedadBase);
	    estadosRampa.put(Estado.Alerta_Epidemica, enfermedadesAlertaRampa);

	    ConsejoPopular rampa = new ConsejoPopular(
	        "Rampa",
	        "CP101",
	        "Plaza de la Revolución",
	        18000,
	        Estado.Alerta_Epidemica,
	        estadosRampa,
	        registrosRampa
	    );

	    // 2. VEDADO MALECON
	    ArrayList<Mes> mesesVedadoMalecon = new ArrayList<>();
	    mesesVedadoMalecon.add(new Mes("Enero", 30, Estado.Normal));
	    mesesVedadoMalecon.add(new Mes("Febrero", 45, Estado.Normal));
	    mesesVedadoMalecon.add(new Mes("Marzo", 70, Estado.Normal));
	    mesesVedadoMalecon.add(new Mes("Abril", 95, Estado.Alerta_Epidemica));
	    mesesVedadoMalecon.add(new Mes("Mayo", 125, Estado.Epidemia));
	    mesesVedadoMalecon.add(new Mes("Junio", 90, Estado.Alerta_Epidemica));
	    mesesVedadoMalecon.add(new Mes("Julio", 65, Estado.Normal));
	    mesesVedadoMalecon.add(new Mes("Agosto", 50, Estado.Normal));
	    mesesVedadoMalecon.add(new Mes("Septiembre", 75, Estado.Normal));
	    mesesVedadoMalecon.add(new Mes("Octubre", 100, Estado.Alerta_Epidemica));
	    mesesVedadoMalecon.add(new Mes("Noviembre", 85, Estado.Alerta_Epidemica));
	    mesesVedadoMalecon.add(new Mes("Diciembre", 60, Estado.Normal));

	    Registro registroVedadoMalecon2025 = new Registro(2025, mesesVedadoMalecon);
	    Map<Enfermedad, ArrayList<Registro>> registrosVedadoMalecon = new HashMap<>();
	    ArrayList<Registro> listaRegistrosVedadoMalecon = new ArrayList<>();
	    listaRegistrosVedadoMalecon.add(registroVedadoMalecon2025);
	    registrosVedadoMalecon.put(enfermedadBase, listaRegistrosVedadoMalecon);

	    Map<Estado, ArrayList<Enfermedad>> estadosVedadoMalecon = new HashMap<>();
	    ArrayList<Enfermedad> enfermedadesNormalVM = new ArrayList<>();
	    enfermedadesNormalVM.add(enfermedadBase);
	    estadosVedadoMalecon.put(Estado.Normal, enfermedadesNormalVM);

	    ConsejoPopular vedadoMalecon = new ConsejoPopular(
	        "Vedado Malecon",
	        "CP102",
	        "Plaza de la Revolución",
	        15000,
	        Estado.Normal,
	        estadosVedadoMalecon,
	        registrosVedadoMalecon
	    );

	    // 3. VEDADO
	    ArrayList<Mes> mesesVedado = new ArrayList<>();
	    mesesVedado.add(new Mes("Enero", 75, Estado.Normal));
	    mesesVedado.add(new Mes("Febrero", 105, Estado.Alerta_Epidemica));
	    mesesVedado.add(new Mes("Marzo", 145, Estado.Epidemia));
	    mesesVedado.add(new Mes("Abril", 175, Estado.Epidemia));
	    mesesVedado.add(new Mes("Mayo", 135, Estado.Epidemia));
	    mesesVedado.add(new Mes("Junio", 90, Estado.Alerta_Epidemica));
	    mesesVedado.add(new Mes("Julio", 65, Estado.Normal));
	    mesesVedado.add(new Mes("Agosto", 80, Estado.Normal));
	    mesesVedado.add(new Mes("Septiembre", 115, Estado.Alerta_Epidemica));
	    mesesVedado.add(new Mes("Octubre", 155, Estado.Epidemia));
	    mesesVedado.add(new Mes("Noviembre", 130, Estado.Epidemia));
	    mesesVedado.add(new Mes("Diciembre", 95, Estado.Alerta_Epidemica));

	    Registro registroVedado2025 = new Registro(2025, mesesVedado);
	    Map<Enfermedad, ArrayList<Registro>> registrosVedado = new HashMap<>();
	    ArrayList<Registro> listaRegistrosVedado = new ArrayList<>();
	    listaRegistrosVedado.add(registroVedado2025);
	    registrosVedado.put(enfermedadBase, listaRegistrosVedado);

	    Map<Estado, ArrayList<Enfermedad>> estadosVedado = new HashMap<>();
	    ArrayList<Enfermedad> enfermedadesEpidemiaVedado = new ArrayList<>();
	    enfermedadesEpidemiaVedado.add(enfermedadBase);
	    estadosVedado.put(Estado.Epidemia, enfermedadesEpidemiaVedado);

	    ConsejoPopular vedado = new ConsejoPopular(
	        "Vedado",
	        "CP103",
	        "Plaza de la Revolución",
	        22000,
	        Estado.Epidemia,
	        estadosVedado,
	        registrosVedado
	    );

	    // 4. PRINCIPE
	    ArrayList<Mes> mesesPrincipe = new ArrayList<>();
	    mesesPrincipe.add(new Mes("Enero", 25, Estado.Normal));
	    mesesPrincipe.add(new Mes("Febrero", 40, Estado.Normal));
	    mesesPrincipe.add(new Mes("Marzo", 60, Estado.Normal));
	    mesesPrincipe.add(new Mes("Abril", 85, Estado.Normal));
	    mesesPrincipe.add(new Mes("Mayo", 110, Estado.Alerta_Epidemica));
	    mesesPrincipe.add(new Mes("Junio", 135, Estado.Epidemia));
	    mesesPrincipe.add(new Mes("Julio", 100, Estado.Alerta_Epidemica));
	    mesesPrincipe.add(new Mes("Agosto", 75, Estado.Normal));
	    mesesPrincipe.add(new Mes("Septiembre", 50, Estado.Normal));
	    mesesPrincipe.add(new Mes("Octubre", 80, Estado.Normal));
	    mesesPrincipe.add(new Mes("Noviembre", 115, Estado.Alerta_Epidemica));
	    mesesPrincipe.add(new Mes("Diciembre", 90, Estado.Alerta_Epidemica));

	    Registro registroPrincipe2025 = new Registro(2025, mesesPrincipe);
	    Map<Enfermedad, ArrayList<Registro>> registrosPrincipe = new HashMap<>();
	    ArrayList<Registro> listaRegistrosPrincipe = new ArrayList<>();
	    listaRegistrosPrincipe.add(registroPrincipe2025);
	    registrosPrincipe.put(enfermedadBase, listaRegistrosPrincipe);

	    Map<Estado, ArrayList<Enfermedad>> estadosPrincipe = new HashMap<>();
	    ArrayList<Enfermedad> enfermedadesAlertaPrincipe = new ArrayList<>();
	    enfermedadesAlertaPrincipe.add(enfermedadBase);
	    estadosPrincipe.put(Estado.Alerta_Epidemica, enfermedadesAlertaPrincipe);

	    ConsejoPopular principe = new ConsejoPopular(
	        "Principe",
	        "CP104",
	        "Plaza de la Revolución",
	        12000,
	        Estado.Alerta_Epidemica,
	        estadosPrincipe,
	        registrosPrincipe
	    );

	    // 5. CARAMELO
	    ArrayList<Mes> mesesCaramelo = new ArrayList<>();
	    mesesCaramelo.add(new Mes("Enero", 50, Estado.Normal));
	    mesesCaramelo.add(new Mes("Febrero", 70, Estado.Normal));
	    mesesCaramelo.add(new Mes("Marzo", 95, Estado.Alerta_Epidemica));
	    mesesCaramelo.add(new Mes("Abril", 125, Estado.Epidemia));
	    mesesCaramelo.add(new Mes("Mayo", 95, Estado.Alerta_Epidemica));
	    mesesCaramelo.add(new Mes("Junio", 70, Estado.Normal));
	    mesesCaramelo.add(new Mes("Julio", 50, Estado.Normal));
	    mesesCaramelo.add(new Mes("Agosto", 65, Estado.Normal));
	    mesesCaramelo.add(new Mes("Septiembre", 90, Estado.Alerta_Epidemica));
	    mesesCaramelo.add(new Mes("Octubre", 120, Estado.Epidemia));
	    mesesCaramelo.add(new Mes("Noviembre", 100, Estado.Alerta_Epidemica));
	    mesesCaramelo.add(new Mes("Diciembre", 75, Estado.Normal));

	    Registro registroCaramelo2025 = new Registro(2025, mesesCaramelo);
	    Map<Enfermedad, ArrayList<Registro>> registrosCaramelo = new HashMap<>();
	    ArrayList<Registro> listaRegistrosCaramelo = new ArrayList<>();
	    listaRegistrosCaramelo.add(registroCaramelo2025);
	    registrosCaramelo.put(enfermedadBase, listaRegistrosCaramelo);

	    Map<Estado, ArrayList<Enfermedad>> estadosCaramelo = new HashMap<>();
	    ArrayList<Enfermedad> enfermedadesEpidemiaCaramelo = new ArrayList<>();
	    enfermedadesEpidemiaCaramelo.add(enfermedadBase);
	    estadosCaramelo.put(Estado.Epidemia, enfermedadesEpidemiaCaramelo);

	    ConsejoPopular caramelo = new ConsejoPopular(
	        "Caramelo",
	        "CP105",
	        "Plaza de la Revolución",
	        16000,
	        Estado.Epidemia,
	        estadosCaramelo,
	        registrosCaramelo
	    );

	    // 6. COLON
	    ArrayList<Mes> mesesColon = new ArrayList<>();
	    mesesColon.add(new Mes("Enero", 35, Estado.Normal));
	    mesesColon.add(new Mes("Febrero", 55, Estado.Normal));
	    mesesColon.add(new Mes("Marzo", 80, Estado.Alerta_Epidemica));
	    mesesColon.add(new Mes("Abril", 110, Estado.Epidemia));
	    mesesColon.add(new Mes("Mayo", 85, Estado.Alerta_Epidemica));
	    mesesColon.add(new Mes("Junio", 60, Estado.Normal));
	    mesesColon.add(new Mes("Julio", 45, Estado.Normal));
	    mesesColon.add(new Mes("Agosto", 50, Estado.Normal));
	    mesesColon.add(new Mes("Septiembre", 75, Estado.Normal));
	    mesesColon.add(new Mes("Octubre", 105, Estado.Alerta_Epidemica));
	    mesesColon.add(new Mes("Noviembre", 90, Estado.Alerta_Epidemica));
	    mesesColon.add(new Mes("Diciembre", 65, Estado.Normal));

	    Registro registroColon2025 = new Registro(2025, mesesColon);
	    Map<Enfermedad, ArrayList<Registro>> registrosColon = new HashMap<>();
	    ArrayList<Registro> listaRegistrosColon = new ArrayList<>();
	    listaRegistrosColon.add(registroColon2025);
	    registrosColon.put(enfermedadBase, listaRegistrosColon);

	    Map<Estado, ArrayList<Enfermedad>> estadosColon = new HashMap<>();
	    ArrayList<Enfermedad> enfermedadesNormalColon = new ArrayList<>();
	    enfermedadesNormalColon.add(enfermedadBase);
	    estadosColon.put(Estado.Normal, enfermedadesNormalColon);

	    ConsejoPopular colon = new ConsejoPopular(
	        "Colon",
	        "CP106",
	        "Plaza de la Revolución",
	        14000,
	        Estado.Normal,
	        estadosColon,
	        registrosColon
	    );

	    // 7. PLAZA
	    ArrayList<Mes> mesesPlaza = new ArrayList<>();
	    mesesPlaza.add(new Mes("Enero", 80, Estado.Normal));
	    mesesPlaza.add(new Mes("Febrero", 115, Estado.Alerta_Epidemica));
	    mesesPlaza.add(new Mes("Marzo", 155, Estado.Epidemia));
	    mesesPlaza.add(new Mes("Abril", 190, Estado.Epidemia));
	    mesesPlaza.add(new Mes("Mayo", 145, Estado.Epidemia));
	    mesesPlaza.add(new Mes("Junio", 100, Estado.Alerta_Epidemica));
	    mesesPlaza.add(new Mes("Julio", 75, Estado.Normal));
	    mesesPlaza.add(new Mes("Agosto", 90, Estado.Normal));
	    mesesPlaza.add(new Mes("Septiembre", 125, Estado.Alerta_Epidemica));
	    mesesPlaza.add(new Mes("Octubre", 165, Estado.Epidemia));
	    mesesPlaza.add(new Mes("Noviembre", 140, Estado.Epidemia));
	    mesesPlaza.add(new Mes("Diciembre", 105, Estado.Alerta_Epidemica));

	    Registro registroPlaza2025 = new Registro(2025, mesesPlaza);
	    Map<Enfermedad, ArrayList<Registro>> registrosPlaza = new HashMap<>();
	    ArrayList<Registro> listaRegistrosPlaza = new ArrayList<>();
	    listaRegistrosPlaza.add(registroPlaza2025);
	    registrosPlaza.put(enfermedadBase, listaRegistrosPlaza);

	    Map<Estado, ArrayList<Enfermedad>> estadosPlaza = new HashMap<>();
	    ArrayList<Enfermedad> enfermedadesEpidemiaPlaza = new ArrayList<>();
	    enfermedadesEpidemiaPlaza.add(enfermedadBase);
	    estadosPlaza.put(Estado.Epidemia, enfermedadesEpidemiaPlaza);

	    ConsejoPopular plaza = new ConsejoPopular(
	        "Plaza",
	        "CP107",
	        "Plaza de la Revolución",
	        20000,
	        Estado.Epidemia,
	        estadosPlaza,
	        registrosPlaza
	    );

	    // 8. PUENTES GRANDES
	    ArrayList<Mes> mesesPuentes = new ArrayList<>();
	    mesesPuentes.add(new Mes("Enero", 20, Estado.Normal));
	    mesesPuentes.add(new Mes("Febrero", 35, Estado.Normal));
	    mesesPuentes.add(new Mes("Marzo", 50, Estado.Normal));
	    mesesPuentes.add(new Mes("Abril", 75, Estado.Normal));
	    mesesPuentes.add(new Mes("Mayo", 100, Estado.Alerta_Epidemica));
	    mesesPuentes.add(new Mes("Junio", 125, Estado.Epidemia));
	    mesesPuentes.add(new Mes("Julio", 90, Estado.Alerta_Epidemica));
	    mesesPuentes.add(new Mes("Agosto", 65, Estado.Normal));
	    mesesPuentes.add(new Mes("Septiembre", 40, Estado.Normal));
	    mesesPuentes.add(new Mes("Octubre", 70, Estado.Normal));
	    mesesPuentes.add(new Mes("Noviembre", 95, Estado.Alerta_Epidemica));
	    mesesPuentes.add(new Mes("Diciembre", 75, Estado.Normal));

	    Registro registroPuentes2025 = new Registro(2025, mesesPuentes);
	    Map<Enfermedad, ArrayList<Registro>> registrosPuentes = new HashMap<>();
	    ArrayList<Registro> listaRegistrosPuentes = new ArrayList<>();
	    listaRegistrosPuentes.add(registroPuentes2025);
	    registrosPuentes.put(enfermedadBase, listaRegistrosPuentes);

	    Map<Estado, ArrayList<Enfermedad>> estadosPuentes = new HashMap<>();
	    ArrayList<Enfermedad> enfermedadesNormalPuentes = new ArrayList<>();
	    enfermedadesNormalPuentes.add(enfermedadBase);
	    estadosPuentes.put(Estado.Normal, enfermedadesNormalPuentes);

	    ConsejoPopular puentesGrandes = new ConsejoPopular(
	        "Puentes Grandes",
	        "CP108",
	        "Plaza de la Revolución",
	        11000,
	        Estado.Normal,
	        estadosPuentes,
	        registrosPuentes
	    );

	    // Crear Direccion Municipal
	    DireccionMunicipal municipioPlaza = new DireccionMunicipal(
	        "DM002",
	        "Dirección Municipal Plaza de la Revolución",
	        128000,
	        "Plaza de la Revolución"
	    );

	    // Obtener grafo y agregar vértices
	    GrafoConsejos grafoConsejos = municipioPlaza.getGrafo();
	    ILinkedNotDirectedGraph grafo = grafoConsejos.getGrafo();

	    // Insertar vértices en el orden especificado
	    grafo.insertVertex(rampa);
	    grafoConsejos.getPosiciones().put(rampa.getNombre(), 0);
	    grafo.insertVertex(vedadoMalecon);
	    grafoConsejos.getPosiciones().put(vedadoMalecon.getNombre(), 1);
	    grafo.insertVertex(vedado);
	    grafoConsejos.getPosiciones().put(vedado.getNombre(), 2);
	    grafo.insertVertex(principe);
	    grafoConsejos.getPosiciones().put(principe.getNombre(), 3);
	    grafo.insertVertex(caramelo);
	    grafoConsejos.getPosiciones().put(caramelo.getNombre(), 4);
	    grafo.insertVertex(colon);
	    grafoConsejos.getPosiciones().put(colon.getNombre(), 5);
	    grafo.insertVertex(plaza);
	    grafoConsejos.getPosiciones().put(plaza.getNombre(), 6);
	    grafo.insertVertex(puentesGrandes);
	    grafoConsejos.getPosiciones().put(puentesGrandes.getNombre(), 7);

	    // Crear conexiones (aristas no dirigidas) según las relaciones especificadas
	    grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(rampa.getNombre()), grafoConsejos.obtenerPosicion(vedadoMalecon.getNombre()));        // Rampa - Vedado Malecon
	    grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(rampa.getNombre()), grafoConsejos.obtenerPosicion(vedado.getNombre()));               // Rampa - Vedado
	    grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(rampa.getNombre()), grafoConsejos.obtenerPosicion(principe.getNombre()));             // Rampa - Principe
	    
	    grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(vedadoMalecon.getNombre()), grafoConsejos.obtenerPosicion(caramelo.getNombre()));     // Vedado Malecon - Caramelo
	    grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(vedadoMalecon.getNombre()), grafoConsejos.obtenerPosicion(vedado.getNombre()));       // Vedado Malecon - Vedado
	    
	    grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(vedado.getNombre()), grafoConsejos.obtenerPosicion(principe.getNombre()));            // Vedado - Principe
	    grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(vedado.getNombre()), grafoConsejos.obtenerPosicion(caramelo.getNombre()));            // Vedado - Caramelo
	    grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(vedado.getNombre()), grafoConsejos.obtenerPosicion(colon.getNombre()));               // Vedado - Colon
	    grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(vedado.getNombre()), grafoConsejos.obtenerPosicion(plaza.getNombre()));               // Vedado - Plaza
	    
	    grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(colon.getNombre()), grafoConsejos.obtenerPosicion(plaza.getNombre()));                // Colon - Plaza
	    grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(colon.getNombre()), grafoConsejos.obtenerPosicion(caramelo.getNombre()));             // Colon - Caramelo
	    
	    grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(plaza.getNombre()), grafoConsejos.obtenerPosicion(principe.getNombre()));             // Plaza - Principe
	    
	    grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(puentesGrandes.getNombre()), grafoConsejos.obtenerPosicion(colon.getNombre()));       // Puentes Grandes - Colon
	    grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(puentesGrandes.getNombre()), grafoConsejos.obtenerPosicion(plaza.getNombre()));       // Puentes Grandes - Plaza
	    
	    return municipioPlaza;
	}
	
	public static DireccionMunicipal inicializarCerro(){
		// Crear enfermedad base para todos los registros
		Enfermedad enfermedadBase = new Enfermedad("COVID-19");

		// 1. LATINOAMERICANO
		// Crear meses para Latinoamericano usando constructor directo
		ArrayList<Mes> mesesLatino = new ArrayList<>();
		mesesLatino.add(new Mes("Enero", 45, Estado.Normal));
		mesesLatino.add(new Mes("Febrero", 60, Estado.Normal));
		mesesLatino.add(new Mes("Marzo", 85, Estado.Alerta_Epidemica));
		mesesLatino.add(new Mes("Abril", 120, Estado.Epidemia));
		mesesLatino.add(new Mes("Mayo", 95, Estado.Alerta_Epidemica));
		mesesLatino.add(new Mes("Junio", 70, Estado.Normal));
		mesesLatino.add(new Mes("Julio", 55, Estado.Normal));
		mesesLatino.add(new Mes("Agosto", 40, Estado.Normal));
		mesesLatino.add(new Mes("Septiembre", 65, Estado.Normal));
		mesesLatino.add(new Mes("Octubre", 90, Estado.Alerta_Epidemica));
		mesesLatino.add(new Mes("Noviembre", 110, Estado.Epidemia));
		mesesLatino.add(new Mes("Diciembre", 80, Estado.Alerta_Epidemica));

		// Crear registro para 2025
		Registro registroLatino2025 = new Registro(2025, mesesLatino);

		// Crear mapa de registros para Latinoamericano
		Map<Enfermedad, ArrayList<Registro>> registrosLatino = new HashMap<>();
		ArrayList<Registro> listaRegistrosLatino = new ArrayList<>();
		listaRegistrosLatino.add(registroLatino2025);
		registrosLatino.put(enfermedadBase, listaRegistrosLatino);

		// Crear mapa de estados para Latinoamericano
		Map<Estado, ArrayList<Enfermedad>> estadosLatino = new HashMap<>();
		ArrayList<Enfermedad> enfermedadesNormalLatino = new ArrayList<>();
		enfermedadesNormalLatino.add(enfermedadBase);
		estadosLatino.put(Estado.Normal, enfermedadesNormalLatino);

		// Crear Consejo Popular Latinoamericano
		ConsejoPopular latinoamericano = new ConsejoPopular(
		    "Latinoamericano",
		    "CP001",
		    "Cerro",
		    12000,
		    Estado.Normal,
		    estadosLatino,
		    registrosLatino
		);

		// 2. PILAR ATARES
		ArrayList<Mes> mesesPilar = new ArrayList<>();
		mesesPilar.add(new Mes("Enero", 35, Estado.Normal));
		mesesPilar.add(new Mes("Febrero", 50, Estado.Normal));
		mesesPilar.add(new Mes("Marzo", 75, Estado.Normal));
		mesesPilar.add(new Mes("Abril", 100, Estado.Alerta_Epidemica));
		mesesPilar.add(new Mes("Mayo", 130, Estado.Epidemia));
		mesesPilar.add(new Mes("Junio", 95, Estado.Alerta_Epidemica));
		mesesPilar.add(new Mes("Julio", 70, Estado.Normal));
		mesesPilar.add(new Mes("Agosto", 55, Estado.Normal));
		mesesPilar.add(new Mes("Septiembre", 80, Estado.Normal));
		mesesPilar.add(new Mes("Octubre", 105, Estado.Alerta_Epidemica));
		mesesPilar.add(new Mes("Noviembre", 90, Estado.Alerta_Epidemica));
		mesesPilar.add(new Mes("Diciembre", 65, Estado.Normal));

		Registro registroPilar2025 = new Registro(2025, mesesPilar);
		Map<Enfermedad, ArrayList<Registro>> registrosPilar = new HashMap<>();
		ArrayList<Registro> listaRegistrosPilar = new ArrayList<>();
		listaRegistrosPilar.add(registroPilar2025);
		registrosPilar.put(enfermedadBase, listaRegistrosPilar);

		Map<Estado, ArrayList<Enfermedad>> estadosPilar = new HashMap<>();
		ArrayList<Enfermedad> enfermedadesNormalPilar = new ArrayList<>();
		enfermedadesNormalPilar.add(enfermedadBase);
		estadosPilar.put(Estado.Normal, enfermedadesNormalPilar);

		ConsejoPopular pilarAtares = new ConsejoPopular(
		    "Pilar Atares",
		    "CP002",
		    "Cerro",
		    15000,
		    Estado.Normal,
		    estadosPilar,
		    registrosPilar
		);

		// 3. CERRO
		ArrayList<Mes> mesesCerro = new ArrayList<>();
		mesesCerro.add(new Mes("Enero", 80, Estado.Normal));
		mesesCerro.add(new Mes("Febrero", 110, Estado.Alerta_Epidemica));
		mesesCerro.add(new Mes("Marzo", 150, Estado.Epidemia));
		mesesCerro.add(new Mes("Abril", 180, Estado.Epidemia));
		mesesCerro.add(new Mes("Mayo", 140, Estado.Epidemia));
		mesesCerro.add(new Mes("Junio", 95, Estado.Alerta_Epidemica));
		mesesCerro.add(new Mes("Julio", 70, Estado.Normal));
		mesesCerro.add(new Mes("Agosto", 85, Estado.Normal));
		mesesCerro.add(new Mes("Septiembre", 120, Estado.Alerta_Epidemica));
		mesesCerro.add(new Mes("Octubre", 160, Estado.Epidemia));
		mesesCerro.add(new Mes("Noviembre", 135, Estado.Epidemia));
		mesesCerro.add(new Mes("Diciembre", 100, Estado.Alerta_Epidemica));

		Registro registroCerro2025 = new Registro(2025, mesesCerro);
		Map<Enfermedad, ArrayList<Registro>> registrosCerro = new HashMap<>();
		ArrayList<Registro> listaRegistrosCerro = new ArrayList<>();
		listaRegistrosCerro.add(registroCerro2025);
		registrosCerro.put(enfermedadBase, listaRegistrosCerro);

		Map<Estado, ArrayList<Enfermedad>> estadosCerro = new HashMap<>();
		ArrayList<Enfermedad> enfermedadesEpidemiaCerro = new ArrayList<>();
		enfermedadesEpidemiaCerro.add(enfermedadBase);
		estadosCerro.put(Estado.Epidemia, enfermedadesEpidemiaCerro);

		ConsejoPopular cerro = new ConsejoPopular(
		    "Cerro",
		    "CP003",
		    "Cerro",
		    18000,
		    Estado.Epidemia,
		    estadosCerro,
		    registrosCerro
		);

		// 4. LAS CAÑAS
		ArrayList<Mes> mesesCanas = new ArrayList<>();
		mesesCanas.add(new Mes("Enero", 25, Estado.Normal));
		mesesCanas.add(new Mes("Febrero", 40, Estado.Normal));
		mesesCanas.add(new Mes("Marzo", 55, Estado.Normal));
		mesesCanas.add(new Mes("Abril", 75, Estado.Normal));
		mesesCanas.add(new Mes("Mayo", 90, Estado.Alerta_Epidemica));
		mesesCanas.add(new Mes("Junio", 110, Estado.Alerta_Epidemica));
		mesesCanas.add(new Mes("Julio", 85, Estado.Normal));
		mesesCanas.add(new Mes("Agosto", 60, Estado.Normal));
		mesesCanas.add(new Mes("Septiembre", 45, Estado.Normal));
		mesesCanas.add(new Mes("Octubre", 70, Estado.Normal));
		mesesCanas.add(new Mes("Noviembre", 95, Estado.Alerta_Epidemica));
		mesesCanas.add(new Mes("Diciembre", 65, Estado.Normal));

		Registro registroCanas2025 = new Registro(2025, mesesCanas);
		Map<Enfermedad, ArrayList<Registro>> registrosCanas = new HashMap<>();
		ArrayList<Registro> listaRegistrosCanas = new ArrayList<>();
		listaRegistrosCanas.add(registroCanas2025);
		registrosCanas.put(enfermedadBase, listaRegistrosCanas);

		Map<Estado, ArrayList<Enfermedad>> estadosCanas = new HashMap<>();
		ArrayList<Enfermedad> enfermedadesNormalCanas = new ArrayList<>();
		enfermedadesNormalCanas.add(enfermedadBase);
		estadosCanas.put(Estado.Normal, enfermedadesNormalCanas);

		ConsejoPopular lasCanas = new ConsejoPopular(
		    "Las Cañas",
		    "CP004",
		    "Cerro",
		    10000,
		    Estado.Normal,
		    estadosCanas,
		    registrosCanas
		);

		// 5. EL CANAL
		ArrayList<Mes> mesesCanal = new ArrayList<>();
		mesesCanal.add(new Mes("Enero", 50, Estado.Normal));
		mesesCanal.add(new Mes("Febrero", 75, Estado.Normal));
		mesesCanal.add(new Mes("Marzo", 100, Estado.Alerta_Epidemica));
		mesesCanal.add(new Mes("Abril", 125, Estado.Epidemia));
		mesesCanal.add(new Mes("Mayo", 90, Estado.Alerta_Epidemica));
		mesesCanal.add(new Mes("Junio", 65, Estado.Normal));
		mesesCanal.add(new Mes("Julio", 45, Estado.Normal));
		mesesCanal.add(new Mes("Agosto", 60, Estado.Normal));
		mesesCanal.add(new Mes("Septiembre", 85, Estado.Normal));
		mesesCanal.add(new Mes("Octubre", 110, Estado.Alerta_Epidemica));
		mesesCanal.add(new Mes("Noviembre", 140, Estado.Epidemia));
		mesesCanal.add(new Mes("Diciembre", 100, Estado.Alerta_Epidemica));

		Registro registroCanal2025 = new Registro(2025, mesesCanal);
		Map<Enfermedad, ArrayList<Registro>> registrosCanal = new HashMap<>();
		ArrayList<Registro> listaRegistrosCanal = new ArrayList<>();
		listaRegistrosCanal.add(registroCanal2025);
		registrosCanal.put(enfermedadBase, listaRegistrosCanal);

		Map<Estado, ArrayList<Enfermedad>> estadosCanal = new HashMap<>();
		ArrayList<Enfermedad> enfermedadesAlertaCanal = new ArrayList<>();
		enfermedadesAlertaCanal.add(enfermedadBase);
		estadosCanal.put(Estado.Alerta_Epidemica, enfermedadesAlertaCanal);

		ConsejoPopular elCanal = new ConsejoPopular(
		    "El Canal",
		    "CP005",
		    "Cerro",
		    13000,
		    Estado.Alerta_Epidemica,
		    estadosCanal,
		    registrosCanal
		);

		// 6. PALATINO
		ArrayList<Mes> mesesPalatino = new ArrayList<>();
		mesesPalatino.add(new Mes("Enero", 70, Estado.Normal));
		mesesPalatino.add(new Mes("Febrero", 95, Estado.Alerta_Epidemica));
		mesesPalatino.add(new Mes("Marzo", 130, Estado.Epidemia));
		mesesPalatino.add(new Mes("Abril", 160, Estado.Epidemia));
		mesesPalatino.add(new Mes("Mayo", 115, Estado.Alerta_Epidemica));
		mesesPalatino.add(new Mes("Junio", 80, Estado.Normal));
		mesesPalatino.add(new Mes("Julio", 55, Estado.Normal));
		mesesPalatino.add(new Mes("Agosto", 70, Estado.Normal));
		mesesPalatino.add(new Mes("Septiembre", 100, Estado.Alerta_Epidemica));
		mesesPalatino.add(new Mes("Octubre", 135, Estado.Epidemia));
		mesesPalatino.add(new Mes("Noviembre", 110, Estado.Alerta_Epidemica));
		mesesPalatino.add(new Mes("Diciembre", 85, Estado.Normal));

		Registro registroPalatino2025 = new Registro(2025, mesesPalatino);
		Map<Enfermedad, ArrayList<Registro>> registrosPalatino = new HashMap<>();
		ArrayList<Registro> listaRegistrosPalatino = new ArrayList<>();
		listaRegistrosPalatino.add(registroPalatino2025);
		registrosPalatino.put(enfermedadBase, listaRegistrosPalatino);

		Map<Estado, ArrayList<Enfermedad>> estadosPalatino = new HashMap<>();
		ArrayList<Enfermedad> enfermedadesEpidemiaPalatino = new ArrayList<>();
		enfermedadesEpidemiaPalatino.add(enfermedadBase);
		estadosPalatino.put(Estado.Epidemia, enfermedadesEpidemiaPalatino);

		ConsejoPopular palatino = new ConsejoPopular(
		    "Palatino",
		    "CP006",
		    "Cerro",
		    16000,
		    Estado.Epidemia,
		    estadosPalatino,
		    registrosPalatino
		);

		// 7. ARMADA
		ArrayList<Mes> mesesArmada = new ArrayList<>();
		mesesArmada.add(new Mes("Enero", 30, Estado.Normal));
		mesesArmada.add(new Mes("Febrero", 45, Estado.Normal));
		mesesArmada.add(new Mes("Marzo", 60, Estado.Normal));
		mesesArmada.add(new Mes("Abril", 85, Estado.Alerta_Epidemica));
		mesesArmada.add(new Mes("Mayo", 110, Estado.Epidemia));
		mesesArmada.add(new Mes("Junio", 75, Estado.Alerta_Epidemica));
		mesesArmada.add(new Mes("Julio", 50, Estado.Normal));
		mesesArmada.add(new Mes("Agosto", 35, Estado.Normal));
		mesesArmada.add(new Mes("Septiembre", 55, Estado.Normal));
		mesesArmada.add(new Mes("Octubre", 80, Estado.Alerta_Epidemica));
		mesesArmada.add(new Mes("Noviembre", 95, Estado.Alerta_Epidemica));
		mesesArmada.add(new Mes("Diciembre", 70, Estado.Normal));

		Registro registroArmada2025 = new Registro(2025, mesesArmada);
		Map<Enfermedad, ArrayList<Registro>> registrosArmada = new HashMap<>();
		ArrayList<Registro> listaRegistrosArmada = new ArrayList<>();
		listaRegistrosArmada.add(registroArmada2025);
		registrosArmada.put(enfermedadBase, listaRegistrosArmada);

		Map<Estado, ArrayList<Enfermedad>> estadosArmada = new HashMap<>();
		ArrayList<Enfermedad> enfermedadesNormalArmada = new ArrayList<>();
		enfermedadesNormalArmada.add(enfermedadBase);
		estadosArmada.put(Estado.Normal, enfermedadesNormalArmada);

		ConsejoPopular armada = new ConsejoPopular(
		    "Armada",
		    "CP007",
		    "Cerro",
		    14000,
		    Estado.Normal,
		    estadosArmada,
		    registrosArmada
		);

		// Crear Direccion Municipal
		DireccionMunicipal municipioCerro = new DireccionMunicipal(
		    "DM001",
		    "Dirección Municipal Cerro",
		    150000,
		    "Cerro"
		);

		// Obtener grafo y agregar vértices
		GrafoConsejos grafoConsejos = municipioCerro.getGrafo();
		ILinkedNotDirectedGraph grafo = grafoConsejos.getGrafo();

		// Insertar vértices en el orden especificado
		 grafo.insertVertex(latinoamericano);
		 grafoConsejos.getPosiciones().put(latinoamericano.getNombre(), 0);
		 grafo.insertVertex(pilarAtares);
		 grafoConsejos.getPosiciones().put(pilarAtares.getNombre(), 1);
		 grafo.insertVertex(cerro);
		 grafoConsejos.getPosiciones().put(cerro.getNombre(), 2);
		 grafo.insertVertex(lasCanas);
		 grafoConsejos.getPosiciones().put(lasCanas.getNombre(), 3);
		 grafo.insertVertex(elCanal);
		 grafoConsejos.getPosiciones().put(elCanal.getNombre(), 4);
		 grafo.insertVertex(palatino);
		 grafoConsejos.getPosiciones().put(palatino.getNombre(), 5);
		 grafo.insertVertex(armada);
		 grafoConsejos.getPosiciones().put(armada.getNombre(), 6);

		// Crear conexiones (aristas no dirigidas)
		grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(latinoamericano.getNombre()), grafoConsejos.obtenerPosicion(cerro.getNombre()));        // Latinoamericano - Cerro
		grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(latinoamericano.getNombre()), grafoConsejos.obtenerPosicion(pilarAtares.getNombre()));        // Latinoamericano - Pilar Atares
		grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(pilarAtares.getNombre()), grafoConsejos.obtenerPosicion(cerro.getNombre()));         // Pilar Atares - Cerro
		grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(cerro.getNombre()), grafoConsejos.obtenerPosicion(lasCanas.getNombre()));         // Cerro - Las Cañas
		grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(cerro.getNombre()), grafoConsejos.obtenerPosicion(elCanal.getNombre()));         // Cerro - El Canal
		grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(lasCanas.getNombre()), grafoConsejos.obtenerPosicion(palatino.getNombre()));      // Las Cañas - Palatino
		grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(elCanal.getNombre()), grafoConsejos.obtenerPosicion(palatino.getNombre()));      // El Canal - Palatino
		grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(palatino.getNombre()), grafoConsejos.obtenerPosicion(armada.getNombre()));     // Palatino - Armada
		
		return municipioCerro;
	}
	
	public static DireccionMunicipal inicializarCentroHabana() {
	    // Crear enfermedad base para todos los registros
	    Enfermedad enfermedadBase = new Enfermedad("COVID-19");

	    // 1. CAYO HUESO
	    ArrayList<Mes> mesesCayoHueso = new ArrayList<>();
	    mesesCayoHueso.add(new Mes("Enero", 65, Estado.Normal));
	    mesesCayoHueso.add(new Mes("Febrero", 90, Estado.Alerta_Epidemica));
	    mesesCayoHueso.add(new Mes("Marzo", 125, Estado.Epidemia));
	    mesesCayoHueso.add(new Mes("Abril", 160, Estado.Epidemia));
	    mesesCayoHueso.add(new Mes("Mayo", 130, Estado.Epidemia));
	    mesesCayoHueso.add(new Mes("Junio", 95, Estado.Alerta_Epidemica));
	    mesesCayoHueso.add(new Mes("Julio", 75, Estado.Normal));
	    mesesCayoHueso.add(new Mes("Agosto", 85, Estado.Normal));
	    mesesCayoHueso.add(new Mes("Septiembre", 115, Estado.Alerta_Epidemica));
	    mesesCayoHueso.add(new Mes("Octubre", 150, Estado.Epidemia));
	    mesesCayoHueso.add(new Mes("Noviembre", 120, Estado.Alerta_Epidemica));
	    mesesCayoHueso.add(new Mes("Diciembre", 90, Estado.Alerta_Epidemica));

	    Registro registroCayoHueso2025 = new Registro(2025, mesesCayoHueso);
	    Map<Enfermedad, ArrayList<Registro>> registrosCayoHueso = new HashMap<>();
	    ArrayList<Registro> listaRegistrosCayoHueso = new ArrayList<>();
	    listaRegistrosCayoHueso.add(registroCayoHueso2025);
	    registrosCayoHueso.put(enfermedadBase, listaRegistrosCayoHueso);

	    Map<Estado, ArrayList<Enfermedad>> estadosCayoHueso = new HashMap<>();
	    ArrayList<Enfermedad> enfermedadesAlertaCayoHueso = new ArrayList<>();
	    enfermedadesAlertaCayoHueso.add(enfermedadBase);
	    estadosCayoHueso.put(Estado.Alerta_Epidemica, enfermedadesAlertaCayoHueso);

	    ConsejoPopular cayoHueso = new ConsejoPopular(
	        "Cayo Hueso",
	        "CP201",
	        "Centro Habana",
	        22000,
	        Estado.Alerta_Epidemica,
	        estadosCayoHueso,
	        registrosCayoHueso
	    );

	    // 2. DRAGONES
	    ArrayList<Mes> mesesDragones = new ArrayList<>();
	    mesesDragones.add(new Mes("Enero", 55, Estado.Normal));
	    mesesDragones.add(new Mes("Febrero", 80, Estado.Normal));
	    mesesDragones.add(new Mes("Marzo", 110, Estado.Alerta_Epidemica));
	    mesesDragones.add(new Mes("Abril", 145, Estado.Epidemia));
	    mesesDragones.add(new Mes("Mayo", 175, Estado.Epidemia));
	    mesesDragones.add(new Mes("Junio", 125, Estado.Epidemia));
	    mesesDragones.add(new Mes("Julio", 90, Estado.Alerta_Epidemica));
	    mesesDragones.add(new Mes("Agosto", 70, Estado.Normal));
	    mesesDragones.add(new Mes("Septiembre", 100, Estado.Alerta_Epidemica));
	    mesesDragones.add(new Mes("Octubre", 135, Estado.Epidemia));
	    mesesDragones.add(new Mes("Noviembre", 105, Estado.Alerta_Epidemica));
	    mesesDragones.add(new Mes("Diciembre", 80, Estado.Normal));

	    Registro registroDragones2025 = new Registro(2025, mesesDragones);
	    Map<Enfermedad, ArrayList<Registro>> registrosDragones = new HashMap<>();
	    ArrayList<Registro> listaRegistrosDragones = new ArrayList<>();
	    listaRegistrosDragones.add(registroDragones2025);
	    registrosDragones.put(enfermedadBase, listaRegistrosDragones);

	    Map<Estado, ArrayList<Enfermedad>> estadosDragones = new HashMap<>();
	    ArrayList<Enfermedad> enfermedadesEpidemiaDragones = new ArrayList<>();
	    enfermedadesEpidemiaDragones.add(enfermedadBase);
	    estadosDragones.put(Estado.Epidemia, enfermedadesEpidemiaDragones);

	    ConsejoPopular dragones = new ConsejoPopular(
	        "Dragones",
	        "CP202",
	        "Centro Habana",
	        25000,
	        Estado.Epidemia,
	        estadosDragones,
	        registrosDragones
	    );

	    // 3. COLÓN
	    ArrayList<Mes> mesesColon = new ArrayList<>();
	    mesesColon.add(new Mes("Enero", 40, Estado.Normal));
	    mesesColon.add(new Mes("Febrero", 60, Estado.Normal));
	    mesesColon.add(new Mes("Marzo", 85, Estado.Normal));
	    mesesColon.add(new Mes("Abril", 115, Estado.Alerta_Epidemica));
	    mesesColon.add(new Mes("Mayo", 145, Estado.Epidemia));
	    mesesColon.add(new Mes("Junio", 105, Estado.Alerta_Epidemica));
	    mesesColon.add(new Mes("Julio", 75, Estado.Normal));
	    mesesColon.add(new Mes("Agosto", 55, Estado.Normal));
	    mesesColon.add(new Mes("Septiembre", 80, Estado.Normal));
	    mesesColon.add(new Mes("Octubre", 110, Estado.Alerta_Epidemica));
	    mesesColon.add(new Mes("Noviembre", 140, Estado.Epidemia));
	    mesesColon.add(new Mes("Diciembre", 95, Estado.Alerta_Epidemica));

	    Registro registroColon2025 = new Registro(2025, mesesColon);
	    Map<Enfermedad, ArrayList<Registro>> registrosColon = new HashMap<>();
	    ArrayList<Registro> listaRegistrosColon = new ArrayList<>();
	    listaRegistrosColon.add(registroColon2025);
	    registrosColon.put(enfermedadBase, listaRegistrosColon);

	    Map<Estado, ArrayList<Enfermedad>> estadosColon = new HashMap<>();
	    ArrayList<Enfermedad> enfermedadesNormalColon = new ArrayList<>();
	    enfermedadesNormalColon.add(enfermedadBase);
	    estadosColon.put(Estado.Normal, enfermedadesNormalColon);

	    ConsejoPopular colon = new ConsejoPopular(
	        "Colón",
	        "CP203",
	        "Centro Habana",
	        19000,
	        Estado.Normal,
	        estadosColon,
	        registrosColon
	    );

	    // 4. PUEBLO NUEVO
	    ArrayList<Mes> mesesPuebloNuevo = new ArrayList<>();
	    mesesPuebloNuevo.add(new Mes("Enero", 70, Estado.Normal));
	    mesesPuebloNuevo.add(new Mes("Febrero", 100, Estado.Alerta_Epidemica));
	    mesesPuebloNuevo.add(new Mes("Marzo", 135, Estado.Epidemia));
	    mesesPuebloNuevo.add(new Mes("Abril", 170, Estado.Epidemia));
	    mesesPuebloNuevo.add(new Mes("Mayo", 140, Estado.Epidemia));
	    mesesPuebloNuevo.add(new Mes("Junio", 105, Estado.Alerta_Epidemica));
	    mesesPuebloNuevo.add(new Mes("Julio", 80, Estado.Normal));
	    mesesPuebloNuevo.add(new Mes("Agosto", 90, Estado.Normal));
	    mesesPuebloNuevo.add(new Mes("Septiembre", 120, Estado.Alerta_Epidemica));
	    mesesPuebloNuevo.add(new Mes("Octubre", 155, Estado.Epidemia));
	    mesesPuebloNuevo.add(new Mes("Noviembre", 125, Estado.Epidemia));
	    mesesPuebloNuevo.add(new Mes("Diciembre", 95, Estado.Alerta_Epidemica));

	    Registro registroPuebloNuevo2025 = new Registro(2025, mesesPuebloNuevo);
	    Map<Enfermedad, ArrayList<Registro>> registrosPuebloNuevo = new HashMap<>();
	    ArrayList<Registro> listaRegistrosPuebloNuevo = new ArrayList<>();
	    listaRegistrosPuebloNuevo.add(registroPuebloNuevo2025);
	    registrosPuebloNuevo.put(enfermedadBase, listaRegistrosPuebloNuevo);

	    Map<Estado, ArrayList<Enfermedad>> estadosPuebloNuevo = new HashMap<>();
	    ArrayList<Enfermedad> enfermedadesEpidemiaPuebloNuevo = new ArrayList<>();
	    enfermedadesEpidemiaPuebloNuevo.add(enfermedadBase);
	    estadosPuebloNuevo.put(Estado.Epidemia, enfermedadesEpidemiaPuebloNuevo);

	    ConsejoPopular puebloNuevo = new ConsejoPopular(
	        "Pueblo Nuevo",
	        "CP204",
	        "Centro Habana",
	        21000,
	        Estado.Epidemia,
	        estadosPuebloNuevo,
	        registrosPuebloNuevo
	    );

	    // 5. LOS SITIOS
	    ArrayList<Mes> mesesLosSitios = new ArrayList<>();
	    mesesLosSitios.add(new Mes("Enero", 50, Estado.Normal));
	    mesesLosSitios.add(new Mes("Febrero", 75, Estado.Normal));
	    mesesLosSitios.add(new Mes("Marzo", 105, Estado.Alerta_Epidemica));
	    mesesLosSitios.add(new Mes("Abril", 140, Estado.Epidemia));
	    mesesLosSitios.add(new Mes("Mayo", 110, Estado.Alerta_Epidemica));
	    mesesLosSitios.add(new Mes("Junio", 80, Estado.Normal));
	    mesesLosSitios.add(new Mes("Julio", 60, Estado.Normal));
	    mesesLosSitios.add(new Mes("Agosto", 70, Estado.Normal));
	    mesesLosSitios.add(new Mes("Septiembre", 95, Estado.Alerta_Epidemica));
	    mesesLosSitios.add(new Mes("Octubre", 130, Estado.Epidemia));
	    mesesLosSitios.add(new Mes("Noviembre", 100, Estado.Alerta_Epidemica));
	    mesesLosSitios.add(new Mes("Diciembre", 75, Estado.Normal));

	    Registro registroLosSitios2025 = new Registro(2025, mesesLosSitios);
	    Map<Enfermedad, ArrayList<Registro>> registrosLosSitios = new HashMap<>();
	    ArrayList<Registro> listaRegistrosLosSitios = new ArrayList<>();
	    listaRegistrosLosSitios.add(registroLosSitios2025);
	    registrosLosSitios.put(enfermedadBase, listaRegistrosLosSitios);

	    Map<Estado, ArrayList<Enfermedad>> estadosLosSitios = new HashMap<>();
	    ArrayList<Enfermedad> enfermedadesNormalLosSitios = new ArrayList<>();
	    enfermedadesNormalLosSitios.add(enfermedadBase);
	    estadosLosSitios.put(Estado.Normal, enfermedadesNormalLosSitios);

	    ConsejoPopular losSitios = new ConsejoPopular(
	        "Los Sitios",
	        "CP205",
	        "Centro Habana",
	        18000,
	        Estado.Normal,
	        estadosLosSitios,
	        registrosLosSitios
	    );

	    // Crear Direccion Municipal para Centro Habana
	    DireccionMunicipal municipioCentroHabana = new DireccionMunicipal(
	        "DM003",
	        "Dirección Municipal Centro Habana",
	        105000,  // Suma de las poblaciones: 22k + 25k + 19k + 21k + 18k = 105k
	        "Centro Habana"
	    );

	    // Obtener grafo y agregar vértices
	    GrafoConsejos grafoConsejos = municipioCentroHabana.getGrafo();
	    ILinkedNotDirectedGraph grafo = grafoConsejos.getGrafo();

	    // Insertar vértices
	    grafo.insertVertex(cayoHueso);
	    grafoConsejos.getPosiciones().put(cayoHueso.getNombre(), 0);
	    grafo.insertVertex(dragones);
	    grafoConsejos.getPosiciones().put(dragones.getNombre(), 1);
	    grafo.insertVertex(colon);
	    grafoConsejos.getPosiciones().put(colon.getNombre(), 2);
	    grafo.insertVertex(puebloNuevo);
	    grafoConsejos.getPosiciones().put(puebloNuevo.getNombre(), 3);
	    grafo.insertVertex(losSitios);
	    grafoConsejos.getPosiciones().put(losSitios.getNombre(), 4);

	    // Crear conexiones (aristas no dirigidas) según las relaciones especificadas
	    // Cayo Hueso - Dragones
	    grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(cayoHueso.getNombre()), 
	                        grafoConsejos.obtenerPosicion(dragones.getNombre()));
	    
	    // Cayo Hueso - Los Sitios
	    grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(cayoHueso.getNombre()), 
	                        grafoConsejos.obtenerPosicion(losSitios.getNombre()));
	    
	    // Cayo Hueso - Pueblo Nuevo
	    grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(cayoHueso.getNombre()), 
	                        grafoConsejos.obtenerPosicion(puebloNuevo.getNombre()));
	    
	    // Pueblo Nuevo - Dragones
	    grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(puebloNuevo.getNombre()), 
	                        grafoConsejos.obtenerPosicion(dragones.getNombre()));
	    
	    // Pueblo Nuevo - Los Sitios
	    grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(puebloNuevo.getNombre()), 
	                        grafoConsejos.obtenerPosicion(losSitios.getNombre()));
	    
	    // Los Sitios - Colón
	    grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(losSitios.getNombre()), 
	                        grafoConsejos.obtenerPosicion(colon.getNombre()));
	    
	    // Colón - Dragones
	    grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(colon.getNombre()), 
	                        grafoConsejos.obtenerPosicion(dragones.getNombre()));

	    return municipioCentroHabana;
	}
	
	public static DireccionMunicipal inicializarRegla() {
	    // Crear enfermedad base para todos los registros
	    Enfermedad enfermedadBase = new Enfermedad("COVID-19");

	    // 1. CASABLANCA
	    ArrayList<Mes> mesesCasablanca = new ArrayList<>();
	    mesesCasablanca.add(new Mes("Enero", 40, Estado.Normal));
	    mesesCasablanca.add(new Mes("Febrero", 55, Estado.Normal));
	    mesesCasablanca.add(new Mes("Marzo", 80, Estado.Normal));
	    mesesCasablanca.add(new Mes("Abril", 110, Estado.Alerta_Epidemica));
	    mesesCasablanca.add(new Mes("Mayo", 140, Estado.Epidemia));
	    mesesCasablanca.add(new Mes("Junio", 100, Estado.Alerta_Epidemica));
	    mesesCasablanca.add(new Mes("Julio", 70, Estado.Normal));
	    mesesCasablanca.add(new Mes("Agosto", 50, Estado.Normal));
	    mesesCasablanca.add(new Mes("Septiembre", 75, Estado.Normal));
	    mesesCasablanca.add(new Mes("Octubre", 95, Estado.Alerta_Epidemica));
	    mesesCasablanca.add(new Mes("Noviembre", 120, Estado.Epidemia));
	    mesesCasablanca.add(new Mes("Diciembre", 85, Estado.Normal));

	    Registro registroCasablanca2025 = new Registro(2025, mesesCasablanca);
	    Map<Enfermedad, ArrayList<Registro>> registrosCasablanca = new HashMap<>();
	    ArrayList<Registro> listaRegistrosCasablanca = new ArrayList<>();
	    listaRegistrosCasablanca.add(registroCasablanca2025);
	    registrosCasablanca.put(enfermedadBase, listaRegistrosCasablanca);

	    Map<Estado, ArrayList<Enfermedad>> estadosCasablanca = new HashMap<>();
	    ArrayList<Enfermedad> enfermedadesNormalCasablanca = new ArrayList<>();
	    enfermedadesNormalCasablanca.add(enfermedadBase);
	    estadosCasablanca.put(Estado.Normal, enfermedadesNormalCasablanca);

	    ConsejoPopular casablanca = new ConsejoPopular(
	        "Casablanca",
	        "CP101",
	        "Regla",
	        8500,
	        Estado.Normal,
	        estadosCasablanca,
	        registrosCasablanca
	    );

	    // 2. GUAICANAMAR
	    ArrayList<Mes> mesesGuaicanamar = new ArrayList<>();
	    mesesGuaicanamar.add(new Mes("Enero", 60, Estado.Normal));
	    mesesGuaicanamar.add(new Mes("Febrero", 85, Estado.Normal));
	    mesesGuaicanamar.add(new Mes("Marzo", 115, Estado.Alerta_Epidemica));
	    mesesGuaicanamar.add(new Mes("Abril", 150, Estado.Epidemia));
	    mesesGuaicanamar.add(new Mes("Mayo", 180, Estado.Epidemia));
	    mesesGuaicanamar.add(new Mes("Junio", 135, Estado.Epidemia));
	    mesesGuaicanamar.add(new Mes("Julio", 95, Estado.Alerta_Epidemica));
	    mesesGuaicanamar.add(new Mes("Agosto", 70, Estado.Normal));
	    mesesGuaicanamar.add(new Mes("Septiembre", 100, Estado.Alerta_Epidemica));
	    mesesGuaicanamar.add(new Mes("Octubre", 140, Estado.Epidemia));
	    mesesGuaicanamar.add(new Mes("Noviembre", 110, Estado.Alerta_Epidemica));
	    mesesGuaicanamar.add(new Mes("Diciembre", 80, Estado.Normal));

	    Registro registroGuaicanamar2025 = new Registro(2025, mesesGuaicanamar);
	    Map<Enfermedad, ArrayList<Registro>> registrosGuaicanamar = new HashMap<>();
	    ArrayList<Registro> listaRegistrosGuaicanamar = new ArrayList<>();
	    listaRegistrosGuaicanamar.add(registroGuaicanamar2025);
	    registrosGuaicanamar.put(enfermedadBase, listaRegistrosGuaicanamar);

	    Map<Estado, ArrayList<Enfermedad>> estadosGuaicanamar = new HashMap<>();
	    ArrayList<Enfermedad> enfermedadesEpidemiaGuaicanamar = new ArrayList<>();
	    enfermedadesEpidemiaGuaicanamar.add(enfermedadBase);
	    estadosGuaicanamar.put(Estado.Epidemia, enfermedadesEpidemiaGuaicanamar);

	    ConsejoPopular guaicanamar = new ConsejoPopular(
	        "Guaicanamar",
	        "CP102",
	        "Regla",
	        12000,
	        Estado.Epidemia,
	        estadosGuaicanamar,
	        registrosGuaicanamar
	    );

	    // 3. LOMA MODELO
	    ArrayList<Mes> mesesLomaModelo = new ArrayList<>();
	    mesesLomaModelo.add(new Mes("Enero", 35, Estado.Normal));
	    mesesLomaModelo.add(new Mes("Febrero", 50, Estado.Normal));
	    mesesLomaModelo.add(new Mes("Marzo", 70, Estado.Normal));
	    mesesLomaModelo.add(new Mes("Abril", 90, Estado.Alerta_Epidemica));
	    mesesLomaModelo.add(new Mes("Mayo", 120, Estado.Epidemia));
	    mesesLomaModelo.add(new Mes("Junio", 85, Estado.Normal));
	    mesesLomaModelo.add(new Mes("Julio", 60, Estado.Normal));
	    mesesLomaModelo.add(new Mes("Agosto", 45, Estado.Normal));
	    mesesLomaModelo.add(new Mes("Septiembre", 65, Estado.Normal));
	    mesesLomaModelo.add(new Mes("Octubre", 85, Estado.Normal));
	    mesesLomaModelo.add(new Mes("Noviembre", 105, Estado.Alerta_Epidemica));
	    mesesLomaModelo.add(new Mes("Diciembre", 75, Estado.Normal));

	    Registro registroLomaModelo2025 = new Registro(2025, mesesLomaModelo);
	    Map<Enfermedad, ArrayList<Registro>> registrosLomaModelo = new HashMap<>();
	    ArrayList<Registro> listaRegistrosLomaModelo = new ArrayList<>();
	    listaRegistrosLomaModelo.add(registroLomaModelo2025);
	    registrosLomaModelo.put(enfermedadBase, listaRegistrosLomaModelo);

	    Map<Estado, ArrayList<Enfermedad>> estadosLomaModelo = new HashMap<>();
	    ArrayList<Enfermedad> enfermedadesAlertaLomaModelo = new ArrayList<>();
	    enfermedadesAlertaLomaModelo.add(enfermedadBase);
	    estadosLomaModelo.put(Estado.Alerta_Epidemica, enfermedadesAlertaLomaModelo);

	    ConsejoPopular lomaModelo = new ConsejoPopular(
	        "Loma Modelo",
	        "CP103",
	        "Regla",
	        9500,
	        Estado.Alerta_Epidemica,
	        estadosLomaModelo,
	        registrosLomaModelo
	    );

	    // Crear Direccion Municipal para Regla
	    DireccionMunicipal municipioRegla = new DireccionMunicipal(
	        "DM002",
	        "Dirección Municipal Regla",
	        30000,
	        "Regla"
	    );

	    // Obtener grafo y agregar vértices
	    GrafoConsejos grafoConsejos = municipioRegla.getGrafo();
	    ILinkedNotDirectedGraph grafo = grafoConsejos.getGrafo();

	    // Insertar vértices
	    grafo.insertVertex(casablanca);
	    grafoConsejos.getPosiciones().put(casablanca.getNombre(), 0);
	    grafo.insertVertex(guaicanamar);
	    grafoConsejos.getPosiciones().put(guaicanamar.getNombre(), 1);
	    grafo.insertVertex(lomaModelo);
	    grafoConsejos.getPosiciones().put(lomaModelo.getNombre(), 2);

	    // Crear conexiones (aristas no dirigidas)
	    // Casablanca - Guaicanamar
	    grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(casablanca.getNombre()), 
	                        grafoConsejos.obtenerPosicion(guaicanamar.getNombre()));
	    
	    // Guaicanamar - Loma Modelo
	    grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(guaicanamar.getNombre()), 
	                        grafoConsejos.obtenerPosicion(lomaModelo.getNombre()));

	    return municipioRegla;
	}
	
	

}
