package Sistema;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.HashMap;

import cu.edu.cujae.ceis.graph.LinkedGraph;
import cu.edu.cujae.ceis.graph.interfaces.ILinkedNotDirectedGraph;
import cu.edu.cujae.ceis.graph.vertex.Vertex;
import Auxiliar.Estado;
import Auxiliar.Sistema;
import Persona.Paciente;
import Salud.ConsejoPopular;
import Salud.DireccionMunicipal;
import Salud.Enfermedad;
import Salud.Minsap;

public class GrafoConsejos {
	private ILinkedNotDirectedGraph grafo;
	private Map<String, Integer> posiciones; // Codigo del Consejo -> Posición en el grafo
	private Map<Integer, ConsejoPopular> consejosPorPosicion; // Posición -> Consejo

	public GrafoConsejos() {
		this.grafo = new LinkedGraph();
		this.posiciones = new HashMap<String, Integer>();
		this.consejosPorPosicion = new HashMap<Integer, ConsejoPopular>();
		//inicializarDatosPrueba();
	}

	// teniendo en cuenta el nombre del consejo 
	public void agregarEnfermoAlConsejo(Paciente paciente, String consejo){ // agregar enfermo al registro d las enfermedades que tenga 
				
		ConsejoPopular con = consejosPorPosicion.get(posiciones.get(consejo));
		ArrayList<Enfermedad> finDeEpidemis = new ArrayList<Enfermedad> ();		
		if(con.anadirPacienteEnfermo(paciente,finDeEpidemis) == Estado.Epidemia){
			ponerEnAlerta(consejo);
		}
		if(finDeEpidemis.size()!=0){
			finAlerta(finDeEpidemis, consejo);
		}



	}
	public int obtenerPosicion(String s){
		return posiciones.get(s);
	}
	
	private void finAlerta(ArrayList<Enfermedad> fin, String consejo){// quitar la alerta de epidemia a consejos populares cercanos al que actualmente se declaro libre de epidemia 
		Vertex enEpidemia=  grafo.getVerticesList().get(posiciones.get(consejo));
		ConsejoPopular c = (ConsejoPopular)enEpidemia.getInfo();
		for(Vertex o : enEpidemia.getAdjacents()){
			ConsejoPopular con = (ConsejoPopular)o.getInfo();
			for(Enfermedad e : fin)
				if(con.obtenerEnfermedadesEnAlerta().contains(e))// verificar que el consejo tenga esa enfermedad en alerta, es decir descartar q este en epidemia
					if(sePuedeEliminarDeAlerta(o, e)){ // verificar que los otros consejos adyacentes a este no tengan tambien epidemia de la misma enfermedad que la alerta tambien los afecta 
						con.declararNormalParaEnfermeda(e);
					}

		}


	}
	private void ponerEnAlerta(String consejo){ // poner en Alerta a los consejos populares cercanos 
		Vertex enEpidemia=  grafo.getVerticesList().get(posiciones.get(consejo));
		ConsejoPopular c = (ConsejoPopular)enEpidemia.getInfo();
		for(Vertex o : enEpidemia.getAdjacents()){
			ConsejoPopular con = (ConsejoPopular)o.getInfo();
			con.declararAlerta(c.obtenerEnfermedadesEnEpidemia());

		}

	}

	private boolean sePuedeEliminarDeAlerta(Vertex v, Enfermedad e){
		boolean si = true;
		ConsejoPopular c = (ConsejoPopular)v.getInfo();
		Iterator<Vertex> it = v.getAdjacents().iterator();

		while(it.hasNext() && si){
			Vertex ver = it.next();
			ConsejoPopular con = (ConsejoPopular)ver.getInfo();
			if(!c.getNombre().equalsIgnoreCase(con.getNombre())){
				if(con.obtenerEnfermedadesEnEpidemia().contains(e)){
					si = false;
				}

			}
		}		
		return si;
	}



	public boolean agregarConsejo(ConsejoPopular consejo) {

		boolean insertado = grafo.insertVertex(consejo);

		if (insertado) {
			LinkedList<Vertex> vertices = grafo.getVerticesList();
			int posicion = vertices.size() - 1; // última posición

			// actualizar mapeos
			posiciones.put(consejo.getCodigo(), posicion);
			consejosPorPosicion.put(posicion, consejo);
		}
		return insertado;
	}

	public boolean agregarColindancia(String codConsejo1, String codConsejo2) {

		boolean agregar = true;
		Integer pos1 = posiciones.get(codConsejo1);
		Integer pos2 = posiciones.get(codConsejo2);

		if (pos1 == null || pos2 == null) {
			agregar = false;
		}
		if (grafo.areAdjacents(pos1, pos2)) {
			agregar = false; // Ya están conectados
		}

		if(agregar)
			agregar = grafo.insertEdgeNDG(pos1, pos2);

		return agregar;
	}

	// Obtener consejo por codigo
	public ConsejoPopular obtenerConsejo(String codConsejo) {

		Integer pos = posiciones.get(codConsejo);
		ConsejoPopular p = null;
		if (pos != null) {
			p = consejosPorPosicion.get(pos);
		}
		return p;
	}

	// Obtener consejos adyacentes
	public List<ConsejoPopular> obtenerColindantes(String idConsejo) {

		List<ConsejoPopular> colindantes = new LinkedList<ConsejoPopular>();
		Integer pos = posiciones.get(idConsejo);
		if (pos != null) {
			LinkedList<Vertex> verticesAdyacentes = grafo.adjacentsG(pos);

			for (Vertex vertice : verticesAdyacentes) {

				int posAdyacente = ((LinkedGraph) grafo).getVertexIndex(vertice);
				ConsejoPopular consejoAdyacente = consejosPorPosicion.get(posAdyacente);

				if (consejoAdyacente != null) {
					colindantes.add(consejoAdyacente);
				}
			}
		}
		return colindantes;
	}

	public List<String> detectarPosiblesEpidemias(String enfermedad, int umbralCasos) {

		List<String> consejosEnAlerta = new LinkedList<String>();

		for (ConsejoPopular consejo : consejosPorPosicion.values()) {
			int casos = consejo.getCasosEnfermedad(enfermedad);

			if (casos >= umbralCasos) {

				List<ConsejoPopular> colindantes = obtenerColindantes(consejo.getCodigo());
				int colindantesAfectados = 0;

				for (ConsejoPopular colindante : colindantes) {
					if (colindante.getCasosEnfermedad(enfermedad) >= umbralCasos / 2) {
						colindantesAfectados++;
					}
				}

				if (colindantesAfectados >= 2) {
					consejosEnAlerta.add(consejo.getCodigo());
				}
			}
		}

		return consejosEnAlerta;
	}

	public Map<String, Integer> obtenerEstadisticasMunicipio(DireccionMunicipal municipio, String enfermedad) {

		Map<String, Integer> estadisticas = new HashMap<String, Integer>();
		int totalCasos = 0;
		int totalConsejosAfectados = 0;

		for (ConsejoPopular consejo : consejosPorPosicion.values()) {
			if (consejo.getMunicipio().equals(municipio)) {
				int casos = consejo.getCasosEnfermedad(enfermedad);
				if (casos > 0) {
					totalCasos += casos;
					totalConsejosAfectados++;
				}
			}
		}

		estadisticas.put("totalCasos", totalCasos);
		estadisticas.put("consejosAfectados", totalConsejosAfectados);
		return estadisticas;
	}

	//	private void inicializarDatosPrueba() {
	////		// Crear consejos populares
	////		ConsejoPopular[] consejos = {
	////				new ConsejoPopular("CP-Mir", "Miramar", "Playa"),
	////				new ConsejoPopular("CP-Sib", "Siboney", "Playa"),
	////				new ConsejoPopular("CP-Nau", "Náutico", "Playa"),
	////				new ConsejoPopular("CP-Ved", "Vedado", "Plaza"),
	////				new ConsejoPopular("CP-Car", "El Carmelo", "Plaza"),
	////				new ConsejoPopular("CP-Cen", "Centro", "Centro Habana")
	////		};
	//
	//		for (ConsejoPopular consejo : consejos) {
	//			agregarConsejo(consejo);
	//		}
	//
	//		agregarColindancia("CP-Mir", "CP-Sib"); // Miramar colinda con Siboney
	//		agregarColindancia("CP-Mir", "CP-Nau"); // Miramar colinda con Náutico
	//		agregarColindancia("CP-Sib", "CP-Nau"); // Siboney colinda con Náutico
	//		agregarColindancia("CP-Ved", "CP-Car"); // Vedado colinda con El Carmelo
	//		agregarColindancia("CP-Car", "CP-Cen"); // El Carmelo colinda con Centro
	//	}

	// Getters
	public ILinkedNotDirectedGraph getGrafo() { return grafo; }
	public Map<String, Integer> getPosiciones() { return posiciones; }

	// IMPRIMIR GRAFO (para debugging)
	//	public void imprimirGrafo() {
	//		System.out.println("=== GRAFO DE CONSEJOS POPULARES ===");
	//
	//		LinkedList<Vertex> vertices = grafo.getVerticesList();
	//		for (int i = 0; i < vertices.size(); i++) {
	//			Vertex vertice = vertices.get(i);
	//			ConsejoPopular consejo = (ConsejoPopular) vertice.getInfo();
	//
	//			System.out.println("\nConsejo: " + consejo.getNombre() + 
	//					" (Municipio: " + consejo.getMunicipio() + ")");
	//
	//			// Mostrar casos por enfermedad
	//			System.out.println("Casos: " + consejo.getCasosPorEnfermedad());
	//
	//			// Mostrar colindantes
	//			LinkedList<Vertex> adyacentes = grafo.adjacentsG(i);
	//			System.out.print("Colindantes: ");
	//			if (adyacentes.isEmpty()) {
	//				System.out.println("Ninguno");
	//			} else {
	//				for (Vertex adyacente : adyacentes) {
	//					ConsejoPopular consejoAdy = (ConsejoPopular) adyacente.getInfo();
	//					System.out.print(consejoAdy.getNombre() + " ");
	//				}
	//				System.out.println();
	//			}
	//		}
	//	}
}