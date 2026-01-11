package Auxiliar;

import java.util.ArrayList;

import Persona.Medico;
import Persona.Paciente;
import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.general.GeneralTree;
import cu.edu.cujae.ceis.tree.iterators.general.InBreadthIterator;
import Salud.Consultorio;
import Salud.DireccionMunicipal;
import Salud.DireccionProvincial;
import Salud.Minsap;
import Salud.NodoSalud;
import Salud.Policlinico;
import Sistema.Credencial;
import Utiles.Enfermedad;

public class Sistema {

	private static GeneralTree<NodoSalud> sistema;
	private static Sistema instancia;
	private static ArrayList<Credencial> credencialesMedicos;
	private static ArrayList<Medico> medicos;

	public static Sistema getInstancia(){
		if(instancia==null){
			instancia = new Sistema();
		}
		return instancia;
	}
	
	private Sistema(){
		sistema = Inicializar.inicializarArbol();
		credencialesMedicos = Inicializar.inicializarCredenciales();
		medicos = Inicializar.inicializarMedicos();
		
	}

	public static GeneralTree<NodoSalud> getSistema() {
		return sistema;
	}

	public static void setSistema(GeneralTree<NodoSalud> sistema) {
		Sistema.sistema = sistema;
	}

	public ArrayList<Credencial> getCredenciales() {
		return credencialesMedicos;
	}
	public ArrayList<Medico> getMedicos() {
		return medicos;
	}

	public void agregarMedico(Medico m){
		medicos.add(m);
	}
	public void agregarMedicoConCredencial(String id, String usuario, String contrasenna){
		credencialesMedicos.add(new Credencial(id, usuario, contrasenna));
	}

	public Medico buscarMedicoPorId(String id){
		Medico m = null;
		System.out.println(id);
		for(int i = 0; i < medicos.size() && m == null; i++){
			if(medicos.get(i).getId().equals(id))
				m = medicos.get(i);
			System.out.println(medicos.get(i).getId());
		}
		return m;
	}

	public BinaryTreeNode<NodoSalud> buscarConsultorio(String consultorio){
        InBreadthIterator<NodoSalud> it = sistema.inBreadthIterator();

		BinaryTreeNode<NodoSalud> con = null; 

		while(it.hasNext() && con == null){
			BinaryTreeNode<NodoSalud> nn = it.nextNode();
			NodoSalud nodo = nn.getInfo();

			if(nodo instanceof Consultorio){
				Consultorio c = (Consultorio)nodo;
				if(c.getCodigo().equalsIgnoreCase(consultorio)){
					con = nn;
				}
			}
		}

		

		return con;
	}

	public Consultorio buscarConsultorioPorId(String consultorio){


		InBreadthIterator<NodoSalud> it = sistema.inBreadthIterator();

		BinaryTreeNode<NodoSalud> con = null; 

		while(it.hasNext() && con == null){
			
			BinaryTreeNode<NodoSalud> nn = it.nextNode();
			NodoSalud nodo = nn.getInfo();
			
			if(nodo instanceof Consultorio){
				
				Consultorio c = (Consultorio)nodo;
				System.out.print(c.getNombre());
				if(c.getCodigo().equalsIgnoreCase(consultorio)){
					con = nn;
				}
			}
		}

		if(con == null) {
			return null;  // ← IMPORTANTE: retornar null si no se encuentra
		}

		return (Consultorio)con.getInfo();
	}

	public void agregarEnfermo(String consultorio, Paciente p ){
		
		BinaryTreeNode<NodoSalud> con = buscarConsultorio(consultorio);
		Consultorio c = buscarConsultorioPorId(consultorio);
		
		c.anadirPaciente(p);
		DireccionMunicipal mun = buscarMunicipioPorConsultorio(con);
		System.out.println(mun==null? "null": mun.getNombre());
		mun.anadirEnfermo(p, c);

	}

	public DireccionMunicipal buscarMunicipioPorConsultorio(BinaryTreeNode<NodoSalud> con){
		return (DireccionMunicipal)(sistema.getFather(sistema.getFather(con)).getInfo());

	}

	public ArrayList<DireccionMunicipal> getMunicipiosHabana(){
		ArrayList <DireccionMunicipal> lista = new ArrayList<DireccionMunicipal>();
		BinaryTreeNode <NodoSalud > habana = null;
		InBreadthIterator<NodoSalud> it = sistema.inBreadthIterator();
		while (it.hasNext() && habana ==null){

			BinaryTreeNode<NodoSalud> este = it.nextNode();
			if(este.getInfo() instanceof DireccionProvincial && ((DireccionProvincial)(este.getInfo())).getProvincia().equalsIgnoreCase("La Habana")){
				habana = este;

			}
		}

		for (NodoSalud s: sistema.getSonsInfo(habana)){
			lista.add((DireccionMunicipal)s);

		}
		return lista;
	}
	
	public ArrayList<Enfermedad> obtenerEnfermedades(){
		Minsap m = (Minsap)((BinaryTreeNode<NodoSalud>)sistema.getRoot()).getInfo();
		return m.getEnfermedadesActuales();
	}
}
