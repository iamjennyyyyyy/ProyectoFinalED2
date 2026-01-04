package Auxiliar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cu.edu.cujae.ceis.graph.interfaces.ILinkedNotDirectedGraph;
import Salud.ConsejoPopular;
import Salud.DireccionMunicipal;
<<<<<<< HEAD
<<<<<<<< HEAD:src/Auxiliar/Inicializar.java
import Salud.Enfermedad;
import Sistema.GrafoConsejos;
========
import Utiles.Enfermedades;
>>>>>>>> origin/Jenny:src/Sistema/Inicializar.java
=======
import Utiles.Enfermedades;
import Sistema.GrafoConsejos;
>>>>>>> origin/Jenny
import Auxiliar.Estado;
import Auxiliar.Mes;
import Auxiliar.Registro;


public class Inicializar {
    public static DireccionMunicipal inicializarPlazaRevolucion(){
        // Crear enfermedad base para todos los registros
        Enfermedades enfermedadBase = new Enfermedades("COVID-19", null, null, null, null, null);

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
        Map<Enfermedades, ArrayList<Registro>> registrosRampa = new HashMap<>();
        ArrayList<Registro> listaRegistrosRampa = new ArrayList<>();
        listaRegistrosRampa.add(registroRampa2025);
        registrosRampa.put(enfermedadBase, listaRegistrosRampa);

        Map<Estado, ArrayList<Enfermedades>> estadosRampa = new HashMap<>();
        ArrayList<Enfermedades> enfermedadesAlertaRampa = new ArrayList<>();
        enfermedadesAlertaRampa.add(enfermedadBase);
        estadosRampa.put(Estado.Alerta_Epidemica, enfermedadesAlertaRampa);

        ConsejoPopular rampa = new ConsejoPopular(
            "Rampa",
            "CP101",
<<<<<<< HEAD
            "Plaza de la Revoluci髇",
=======
            "Plaza de la Revoluci贸n",
>>>>>>> origin/Jenny
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
        Map<Enfermedades, ArrayList<Registro>> registrosVedadoMalecon = new HashMap<>();
        ArrayList<Registro> listaRegistrosVedadoMalecon = new ArrayList<>();
        listaRegistrosVedadoMalecon.add(registroVedadoMalecon2025);
        registrosVedadoMalecon.put(enfermedadBase, listaRegistrosVedadoMalecon);

        Map<Estado, ArrayList<Enfermedades>> estadosVedadoMalecon = new HashMap<>();
        ArrayList<Enfermedades> enfermedadesNormalVM = new ArrayList<>();
        enfermedadesNormalVM.add(enfermedadBase);
        estadosVedadoMalecon.put(Estado.Normal, enfermedadesNormalVM);

        ConsejoPopular vedadoMalecon = new ConsejoPopular(
            "Vedado Malecon",
            "CP102",
<<<<<<< HEAD
            "Plaza de la Revoluci髇",
=======
            "Plaza de la Revoluci贸n",
>>>>>>> origin/Jenny
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
        Map<Enfermedades, ArrayList<Registro>> registrosVedado = new HashMap<>();
        ArrayList<Registro> listaRegistrosVedado = new ArrayList<>();
        listaRegistrosVedado.add(registroVedado2025);
        registrosVedado.put(enfermedadBase, listaRegistrosVedado);

        Map<Estado, ArrayList<Enfermedades>> estadosVedado = new HashMap<>();
        ArrayList<Enfermedades> enfermedadesEpidemiaVedado = new ArrayList<>();
        enfermedadesEpidemiaVedado.add(enfermedadBase);
        estadosVedado.put(Estado.Epidemia, enfermedadesEpidemiaVedado);

        ConsejoPopular vedado = new ConsejoPopular(
            "Vedado",
            "CP103",
<<<<<<< HEAD
            "Plaza de la Revoluci髇",
=======
            "Plaza de la Revoluci贸n",
>>>>>>> origin/Jenny
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
        Map<Enfermedades, ArrayList<Registro>> registrosPrincipe = new HashMap<>();
        ArrayList<Registro> listaRegistrosPrincipe = new ArrayList<>();
        listaRegistrosPrincipe.add(registroPrincipe2025);
        registrosPrincipe.put(enfermedadBase, listaRegistrosPrincipe);

        Map<Estado, ArrayList<Enfermedades>> estadosPrincipe = new HashMap<>();
        ArrayList<Enfermedades> enfermedadesAlertaPrincipe = new ArrayList<>();
        enfermedadesAlertaPrincipe.add(enfermedadBase);
        estadosPrincipe.put(Estado.Alerta_Epidemica, enfermedadesAlertaPrincipe);

        ConsejoPopular principe = new ConsejoPopular(
            "Principe",
            "CP104",
<<<<<<< HEAD
            "Plaza de la Revoluci髇",
=======
            "Plaza de la Revoluci贸n",
>>>>>>> origin/Jenny
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
        Map<Enfermedades, ArrayList<Registro>> registrosCaramelo = new HashMap<>();
        ArrayList<Registro> listaRegistrosCaramelo = new ArrayList<>();
        listaRegistrosCaramelo.add(registroCaramelo2025);
        registrosCaramelo.put(enfermedadBase, listaRegistrosCaramelo);

        Map<Estado, ArrayList<Enfermedades>> estadosCaramelo = new HashMap<>();
        ArrayList<Enfermedades> enfermedadesEpidemiaCaramelo = new ArrayList<>();
        enfermedadesEpidemiaCaramelo.add(enfermedadBase);
        estadosCaramelo.put(Estado.Epidemia, enfermedadesEpidemiaCaramelo);

        ConsejoPopular caramelo = new ConsejoPopular(
            "Caramelo",
            "CP105",
<<<<<<< HEAD
            "Plaza de la Revoluci髇",
=======
            "Plaza de la Revoluci贸n",
>>>>>>> origin/Jenny
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
        Map<Enfermedades, ArrayList<Registro>> registrosColon = new HashMap<>();
        ArrayList<Registro> listaRegistrosColon = new ArrayList<>();
        listaRegistrosColon.add(registroColon2025);
        registrosColon.put(enfermedadBase, listaRegistrosColon);

        Map<Estado, ArrayList<Enfermedades>> estadosColon = new HashMap<>();
        ArrayList<Enfermedades> enfermedadesNormalColon = new ArrayList<>();
        enfermedadesNormalColon.add(enfermedadBase);
        estadosColon.put(Estado.Normal, enfermedadesNormalColon);

        ConsejoPopular colon = new ConsejoPopular(
            "Colon",
            "CP106",
<<<<<<< HEAD
            "Plaza de la Revoluci髇",
=======
            "Plaza de la Revoluci贸n",
>>>>>>> origin/Jenny
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
        Map<Enfermedades, ArrayList<Registro>> registrosPlaza = new HashMap<>();
        ArrayList<Registro> listaRegistrosPlaza = new ArrayList<>();
        listaRegistrosPlaza.add(registroPlaza2025);
        registrosPlaza.put(enfermedadBase, listaRegistrosPlaza);

        Map<Estado, ArrayList<Enfermedades>> estadosPlaza = new HashMap<>();
        ArrayList<Enfermedades> enfermedadesEpidemiaPlaza = new ArrayList<>();
        enfermedadesEpidemiaPlaza.add(enfermedadBase);
        estadosPlaza.put(Estado.Epidemia, enfermedadesEpidemiaPlaza);

        ConsejoPopular plaza = new ConsejoPopular(
            "Plaza",
            "CP107",
<<<<<<< HEAD
            "Plaza de la Revoluci髇",
=======
            "Plaza de la Revoluci贸n",
>>>>>>> origin/Jenny
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
        Map<Enfermedades, ArrayList<Registro>> registrosPuentes = new HashMap<>();
        ArrayList<Registro> listaRegistrosPuentes = new ArrayList<>();
        listaRegistrosPuentes.add(registroPuentes2025);
        registrosPuentes.put(enfermedadBase, listaRegistrosPuentes);

        Map<Estado, ArrayList<Enfermedades>> estadosPuentes = new HashMap<>();
        ArrayList<Enfermedades> enfermedadesNormalPuentes = new ArrayList<>();
        enfermedadesNormalPuentes.add(enfermedadBase);
        estadosPuentes.put(Estado.Normal, enfermedadesNormalPuentes);

        ConsejoPopular puentesGrandes = new ConsejoPopular(
            "Puentes Grandes",
            "CP108",
<<<<<<< HEAD
            "Plaza de la Revoluci髇",
=======
            "Plaza de la Revoluci贸n",
>>>>>>> origin/Jenny
            11000,
            Estado.Normal,
            estadosPuentes,
            registrosPuentes
        );

        // Crear Direccion Municipal
        DireccionMunicipal municipioPlaza = new DireccionMunicipal(
            "DM002",
<<<<<<< HEAD
            "Direcci髇 Municipal Plaza de la Revoluci髇",
            128000,
            "Plaza de la Revoluci髇"
        );

        // Obtener grafo y agregar v閞tices
        GrafoConsejos grafoConsejos = municipioPlaza.getGrafo();
        ILinkedNotDirectedGraph grafo = grafoConsejos.getGrafo();

        // Insertar v閞tices en el orden especificado
=======
            "Direcci贸n Municipal Plaza de la Revoluci贸n",
            128000,
            "Plaza de la Revoluci贸n"
        );

        // Obtener grafo y agregar v茅rtices
        GrafoConsejos grafoConsejos = municipioPlaza.getGrafo();
        ILinkedNotDirectedGraph grafo = grafoConsejos.getGrafo();

        // Insertar v茅rtices en el orden especificado
>>>>>>> origin/Jenny
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

<<<<<<< HEAD
        // Crear conexiones (aristas no dirigidas) seg鷑 las relaciones especificadas
=======
        // Crear conexiones (aristas no dirigidas) seg煤n las relaciones especificadas
>>>>>>> origin/Jenny
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
        Enfermedades enfermedadBase = new Enfermedades("COVID-19", null, null, null, null, null);

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
        Map<Enfermedades, ArrayList<Registro>> registrosLatino = new HashMap<>();
        ArrayList<Registro> listaRegistrosLatino = new ArrayList<>();
        listaRegistrosLatino.add(registroLatino2025);
        registrosLatino.put(enfermedadBase, listaRegistrosLatino);

        // Crear mapa de estados para Latinoamericano
        Map<Estado, ArrayList<Enfermedades>> estadosLatino = new HashMap<>();
        ArrayList<Enfermedades> enfermedadesNormalLatino = new ArrayList<>();
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
        Map<Enfermedades, ArrayList<Registro>> registrosPilar = new HashMap<>();
        ArrayList<Registro> listaRegistrosPilar = new ArrayList<>();
        listaRegistrosPilar.add(registroPilar2025);
        registrosPilar.put(enfermedadBase, listaRegistrosPilar);

        Map<Estado, ArrayList<Enfermedades>> estadosPilar = new HashMap<>();
        ArrayList<Enfermedades> enfermedadesNormalPilar = new ArrayList<>();
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
        Map<Enfermedades, ArrayList<Registro>> registrosCerro = new HashMap<>();
        ArrayList<Registro> listaRegistrosCerro = new ArrayList<>();
        listaRegistrosCerro.add(registroCerro2025);
        registrosCerro.put(enfermedadBase, listaRegistrosCerro);

        Map<Estado, ArrayList<Enfermedades>> estadosCerro = new HashMap<>();
        ArrayList<Enfermedades> enfermedadesEpidemiaCerro = new ArrayList<>();
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

<<<<<<< HEAD
        // 4. LAS CA袮S
=======
        // 4. LAS CA脩AS
>>>>>>> origin/Jenny
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
        Map<Enfermedades, ArrayList<Registro>> registrosCanas = new HashMap<>();
        ArrayList<Registro> listaRegistrosCanas = new ArrayList<>();
        listaRegistrosCanas.add(registroCanas2025);
        registrosCanas.put(enfermedadBase, listaRegistrosCanas);

        Map<Estado, ArrayList<Enfermedades>> estadosCanas = new HashMap<>();
        ArrayList<Enfermedades> enfermedadesNormalCanas = new ArrayList<>();
        enfermedadesNormalCanas.add(enfermedadBase);
        estadosCanas.put(Estado.Normal, enfermedadesNormalCanas);

        ConsejoPopular lasCanas = new ConsejoPopular(
<<<<<<< HEAD
            "Las Ca馻s",
=======
            "Las Ca帽as",
>>>>>>> origin/Jenny
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
        Map<Enfermedades, ArrayList<Registro>> registrosCanal = new HashMap<>();
        ArrayList<Registro> listaRegistrosCanal = new ArrayList<>();
        listaRegistrosCanal.add(registroCanal2025);
        registrosCanal.put(enfermedadBase, listaRegistrosCanal);

        Map<Estado, ArrayList<Enfermedades>> estadosCanal = new HashMap<>();
        ArrayList<Enfermedades> enfermedadesAlertaCanal = new ArrayList<>();
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
        Map<Enfermedades, ArrayList<Registro>> registrosPalatino = new HashMap<>();
        ArrayList<Registro> listaRegistrosPalatino = new ArrayList<>();
        listaRegistrosPalatino.add(registroPalatino2025);
        registrosPalatino.put(enfermedadBase, listaRegistrosPalatino);

        Map<Estado, ArrayList<Enfermedades>> estadosPalatino = new HashMap<>();
        ArrayList<Enfermedades> enfermedadesEpidemiaPalatino = new ArrayList<>();
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
        Map<Enfermedades, ArrayList<Registro>> registrosArmada = new HashMap<>();
        ArrayList<Registro> listaRegistrosArmada = new ArrayList<>();
        listaRegistrosArmada.add(registroArmada2025);
        registrosArmada.put(enfermedadBase, listaRegistrosArmada);

        Map<Estado, ArrayList<Enfermedades>> estadosArmada = new HashMap<>();
        ArrayList<Enfermedades> enfermedadesNormalArmada = new ArrayList<>();
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
<<<<<<< HEAD
            "Direcci髇 Municipal Cerro",
=======
            "Direcci贸n Municipal Cerro",
>>>>>>> origin/Jenny
            150000,
            "Cerro"
        );

<<<<<<< HEAD
        // Obtener grafo y agregar v閞tices
        GrafoConsejos grafoConsejos = municipioCerro.getGrafo();
        ILinkedNotDirectedGraph grafo = grafoConsejos.getGrafo();

        // Insertar v閞tices en el orden especificado
=======
        // Obtener grafo y agregar v茅rtices
        GrafoConsejos grafoConsejos = municipioCerro.getGrafo();
        ILinkedNotDirectedGraph grafo = grafoConsejos.getGrafo();

        // Insertar v茅rtices en el orden especificado
>>>>>>> origin/Jenny
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
<<<<<<< HEAD
        grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(cerro.getNombre()), grafoConsejos.obtenerPosicion(lasCanas.getNombre()));         // Cerro - Las Ca馻s
        grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(cerro.getNombre()), grafoConsejos.obtenerPosicion(elCanal.getNombre()));         // Cerro - El Canal
        grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(lasCanas.getNombre()), grafoConsejos.obtenerPosicion(palatino.getNombre()));      // Las Ca馻s - Palatino
=======
        grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(cerro.getNombre()), grafoConsejos.obtenerPosicion(lasCanas.getNombre()));         // Cerro - Las Ca帽as
        grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(cerro.getNombre()), grafoConsejos.obtenerPosicion(elCanal.getNombre()));         // Cerro - El Canal
        grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(lasCanas.getNombre()), grafoConsejos.obtenerPosicion(palatino.getNombre()));      // Las Ca帽as - Palatino
>>>>>>> origin/Jenny
        grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(elCanal.getNombre()), grafoConsejos.obtenerPosicion(palatino.getNombre()));      // El Canal - Palatino
        grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(palatino.getNombre()), grafoConsejos.obtenerPosicion(armada.getNombre()));     // Palatino - Armada
        
        return municipioCerro;
    }
    
    public static DireccionMunicipal inicializarCentroHabana() {
        // Crear enfermedad base para todos los registros
        Enfermedades enfermedadBase = new Enfermedades("COVID-19", null, null, null, null, null);

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
        Map<Enfermedades, ArrayList<Registro>> registrosCayoHueso = new HashMap<>();
        ArrayList<Registro> listaRegistrosCayoHueso = new ArrayList<>();
        listaRegistrosCayoHueso.add(registroCayoHueso2025);
        registrosCayoHueso.put(enfermedadBase, listaRegistrosCayoHueso);

        Map<Estado, ArrayList<Enfermedades>> estadosCayoHueso = new HashMap<>();
        ArrayList<Enfermedades> enfermedadesAlertaCayoHueso = new ArrayList<>();
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
        Map<Enfermedades, ArrayList<Registro>> registrosDragones = new HashMap<>();
        ArrayList<Registro> listaRegistrosDragones = new ArrayList<>();
        listaRegistrosDragones.add(registroDragones2025);
        registrosDragones.put(enfermedadBase, listaRegistrosDragones);

        Map<Estado, ArrayList<Enfermedades>> estadosDragones = new HashMap<>();
        ArrayList<Enfermedades> enfermedadesEpidemiaDragones = new ArrayList<>();
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

<<<<<<< HEAD
        // 3. COL覰
=======
        // 3. COL脫N
>>>>>>> origin/Jenny
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
        Map<Enfermedades, ArrayList<Registro>> registrosColon = new HashMap<>();
        ArrayList<Registro> listaRegistrosColon = new ArrayList<>();
        listaRegistrosColon.add(registroColon2025);
        registrosColon.put(enfermedadBase, listaRegistrosColon);

        Map<Estado, ArrayList<Enfermedades>> estadosColon = new HashMap<>();
        ArrayList<Enfermedades> enfermedadesNormalColon = new ArrayList<>();
        enfermedadesNormalColon.add(enfermedadBase);
        estadosColon.put(Estado.Normal, enfermedadesNormalColon);

        ConsejoPopular colon = new ConsejoPopular(
<<<<<<< HEAD
            "Col髇",
=======
            "Col贸n",
>>>>>>> origin/Jenny
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
        Map<Enfermedades, ArrayList<Registro>> registrosPuebloNuevo = new HashMap<>();
        ArrayList<Registro> listaRegistrosPuebloNuevo = new ArrayList<>();
        listaRegistrosPuebloNuevo.add(registroPuebloNuevo2025);
        registrosPuebloNuevo.put(enfermedadBase, listaRegistrosPuebloNuevo);

        Map<Estado, ArrayList<Enfermedades>> estadosPuebloNuevo = new HashMap<>();
        ArrayList<Enfermedades> enfermedadesEpidemiaPuebloNuevo = new ArrayList<>();
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
        Map<Enfermedades, ArrayList<Registro>> registrosLosSitios = new HashMap<>();
        ArrayList<Registro> listaRegistrosLosSitios = new ArrayList<>();
        listaRegistrosLosSitios.add(registroLosSitios2025);
        registrosLosSitios.put(enfermedadBase, listaRegistrosLosSitios);

        Map<Estado, ArrayList<Enfermedades>> estadosLosSitios = new HashMap<>();
        ArrayList<Enfermedades> enfermedadesNormalLosSitios = new ArrayList<>();
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
<<<<<<< HEAD
            "Direcci髇 Municipal Centro Habana",
=======
            "Direcci贸n Municipal Centro Habana",
>>>>>>> origin/Jenny
            105000,  // Suma de las poblaciones: 22k + 25k + 19k + 21k + 18k = 105k
            "Centro Habana"
        );

<<<<<<< HEAD
        // Obtener grafo y agregar v閞tices
        GrafoConsejos grafoConsejos = municipioCentroHabana.getGrafo();
        ILinkedNotDirectedGraph grafo = grafoConsejos.getGrafo();

        // Insertar v閞tices
=======
        // Obtener grafo y agregar v茅rtices
        GrafoConsejos grafoConsejos = municipioCentroHabana.getGrafo();
        ILinkedNotDirectedGraph grafo = grafoConsejos.getGrafo();

        // Insertar v茅rtices
>>>>>>> origin/Jenny
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

<<<<<<< HEAD
        // Crear conexiones (aristas no dirigidas) seg鷑 las relaciones especificadas
=======
        // Crear conexiones (aristas no dirigidas) seg煤n las relaciones especificadas
>>>>>>> origin/Jenny
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
        
<<<<<<< HEAD
        // Los Sitios - Col髇
        grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(losSitios.getNombre()), 
                            grafoConsejos.obtenerPosicion(colon.getNombre()));
        
        // Col髇 - Dragones
=======
        // Los Sitios - Col贸n
        grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(losSitios.getNombre()), 
                            grafoConsejos.obtenerPosicion(colon.getNombre()));
        
        // Col贸n - Dragones
>>>>>>> origin/Jenny
        grafo.insertEdgeNDG(grafoConsejos.obtenerPosicion(colon.getNombre()), 
                            grafoConsejos.obtenerPosicion(dragones.getNombre()));

        return municipioCentroHabana;
    }
    
    public static DireccionMunicipal inicializarRegla() {
        // Crear enfermedad base para todos los registros
        Enfermedades enfermedadBase = new Enfermedades("COVID-19", null, null, null, null, null);

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
        Map<Enfermedades, ArrayList<Registro>> registrosCasablanca = new HashMap<>();
        ArrayList<Registro> listaRegistrosCasablanca = new ArrayList<>();
        listaRegistrosCasablanca.add(registroCasablanca2025);
        registrosCasablanca.put(enfermedadBase, listaRegistrosCasablanca);

        Map<Estado, ArrayList<Enfermedades>> estadosCasablanca = new HashMap<>();
        ArrayList<Enfermedades> enfermedadesNormalCasablanca = new ArrayList<>();
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
        Map<Enfermedades, ArrayList<Registro>> registrosGuaicanamar = new HashMap<>();
        ArrayList<Registro> listaRegistrosGuaicanamar = new ArrayList<>();
        listaRegistrosGuaicanamar.add(registroGuaicanamar2025);
        registrosGuaicanamar.put(enfermedadBase, listaRegistrosGuaicanamar);

        Map<Estado, ArrayList<Enfermedades>> estadosGuaicanamar = new HashMap<>();
        ArrayList<Enfermedades> enfermedadesEpidemiaGuaicanamar = new ArrayList<>();
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
        Map<Enfermedades, ArrayList<Registro>> registrosLomaModelo = new HashMap<>();
        ArrayList<Registro> listaRegistrosLomaModelo = new ArrayList<>();
        listaRegistrosLomaModelo.add(registroLomaModelo2025);
        registrosLomaModelo.put(enfermedadBase, listaRegistrosLomaModelo);

        Map<Estado, ArrayList<Enfermedades>> estadosLomaModelo = new HashMap<>();
        ArrayList<Enfermedades> enfermedadesAlertaLomaModelo = new ArrayList<>();
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
<<<<<<< HEAD
            "Direcci髇 Municipal Regla",
=======
            "Direcci贸n Municipal Regla",
>>>>>>> origin/Jenny
            30000,
            "Regla"
        );

<<<<<<< HEAD
        // Obtener grafo y agregar v閞tices
        GrafoConsejos grafoConsejos = municipioRegla.getGrafo();
        ILinkedNotDirectedGraph grafo = grafoConsejos.getGrafo();

        // Insertar v閞tices
=======
        // Obtener grafo y agregar v茅rtices
        GrafoConsejos grafoConsejos = municipioRegla.getGrafo();
        ILinkedNotDirectedGraph grafo = grafoConsejos.getGrafo();

        // Insertar v茅rtices
>>>>>>> origin/Jenny
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