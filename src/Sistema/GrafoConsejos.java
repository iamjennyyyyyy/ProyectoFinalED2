package Sistema;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import cu.edu.cujae.ceis.graph.LinkedGraph;
import cu.edu.cujae.ceis.graph.interfaces.ILinkedNotDirectedGraph;
import cu.edu.cujae.ceis.graph.vertex.Vertex;
import Salud.ConsejoPopular;
import Salud.DireccionMunicipal;

public class GrafoConsejos {
	private ILinkedNotDirectedGraph grafo;
	private Map<String, Integer> posiciones; // Codigo del Consejo -> Posición en el grafo
	private Map<Integer, ConsejoPopular> consejosPorPosicion; // Posición -> Consejo

	public GrafoConsejos() {
		this.grafo = new LinkedGraph();
		this.posiciones = new HashMap<String, Integer>();
		this.consejosPorPosicion = new HashMap<Integer, ConsejoPopular>();
		inicializarDatosPrueba();
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

	private void inicializarDatosPrueba() {
		// Crear consejos populares
		ConsejoPopular[] consejos = {
				new ConsejoPopular("CP-Mir", "Miramar", "Playa"),
				new ConsejoPopular("CP-Sib", "Siboney", "Playa"),
				new ConsejoPopular("CP-Nau", "Náutico", "Playa"),
				new ConsejoPopular("CP-Ved", "Vedado", "Plaza"),
				new ConsejoPopular("CP-Car", "El Carmelo", "Plaza"),
				new ConsejoPopular("CP-Cen", "Centro", "Centro Habana")
		};

		for (ConsejoPopular consejo : consejos) {
			agregarConsejo(consejo);
		}

		agregarColindancia("CP-Mir", "CP-Sib"); // Miramar colinda con Siboney
		agregarColindancia("CP-Mir", "CP-Nau"); // Miramar colinda con Náutico
		agregarColindancia("CP-Sib", "CP-Nau"); // Siboney colinda con Náutico
		agregarColindancia("CP-Ved", "CP-Car"); // Vedado colinda con El Carmelo
		agregarColindancia("CP-Car", "CP-Cen"); // El Carmelo colinda con Centro
	}

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