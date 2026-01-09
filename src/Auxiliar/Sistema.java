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
import Salud.NodoSalud;
import Salud.Policlinico;
import Sistema.Credencial;

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

//	public void inicializar(){
//		BinaryTreeNode<NodoSalud> habana = new BinaryTreeNode<NodoSalud>(new DireccionProvincial("HAB-000", "Direccion_Provincial_La_Habana", "La Habana"));
//		sistema.setRoot(habana);
//		BinaryTreeNode<NodoSalud> laLisa = new BinaryTreeNode<NodoSalud>(new DireccionMunicipal("HAB-001", "Direccion_Municipal_La_Lisa", 132135, "La Lisa"));
//		BinaryTreeNode<NodoSalud> cerro = new BinaryTreeNode<NodoSalud>(new DireccionMunicipal("HAB-002", "Direccion_Municipal_Cerro", 126851, "Cerro"));
//		BinaryTreeNode<NodoSalud> plaza = new BinaryTreeNode<NodoSalud>(new DireccionMunicipal("HAB-003", "Direccion_Municipal_Plaza", 136634, "Plaza de la Revoluciï¿½n"));
//		BinaryTreeNode<NodoSalud> playa = new BinaryTreeNode<NodoSalud>(new DireccionMunicipal("HAB-004", "Direccion_Municipal_Playa", 186148, "Playa"));
//		sistema.insertNode(laLisa, habana);
//		sistema.insertNode(cerro, habana);
//		sistema.insertNode(plaza, habana);
//		sistema.insertNode(playa, habana);
//		BinaryTreeNode<NodoSalud> pCristL = new BinaryTreeNode<NodoSalud>(new Policlinico("HAB-LISA-001", "Policlinico_Cristobal_Labra", "Cristobal Labra"));
//		BinaryTreeNode<NodoSalud> pAleidaF = new BinaryTreeNode<NodoSalud>(new Policlinico("HAB-LISA-002", "Policlinico_Aleida_Fernandez", "Aleida Fernandez"));
//		BinaryTreeNode<NodoSalud> p19Abril = new BinaryTreeNode<NodoSalud>(new Policlinico("HAB-PLAZA-001", "Policlinico_19_de_Abril", "19 de Abril"));
//		BinaryTreeNode<NodoSalud> p28Enero = new BinaryTreeNode<NodoSalud>(new Policlinico("HAB-PLAZA-002", "Policlinico_28_de_Enero", "28 de Enero"));
//		sistema.insertNode(pCristL, laLisa);
//		sistema.insertNode(pAleidaF, laLisa);
//		sistema.insertNode(p19Abril, plaza);
//		sistema.insertNode(p28Enero, plaza);
//		BinaryTreeNode<NodoSalud> c40 = new BinaryTreeNode<NodoSalud>(new Consultorio("HAB-LISA-CRISTOBAL-001", "Consultorio_40", 40, "mmm"));
//		BinaryTreeNode<NodoSalud> c41 = new BinaryTreeNode<NodoSalud>(new Consultorio("HAB-LISA-CRISTOBAL-002", "Consultorio_41", 41,"nnnn"));
//		BinaryTreeNode<NodoSalud> c42 = new BinaryTreeNode<NodoSalud>(new Consultorio("HAB-LISA-CRISTOBAL-003", "Consultorio_42", 42,"gggg"));
//		sistema.insertNode(c40, pCristL);
//		sistema.insertNode(c41, pCristL);
//		sistema.insertNode(c42, pCristL);
//	}

	public BinaryTreeNode<NodoSalud> buscarConsultorio(String consultorio){
        InBreadthIterator<NodoSalud> it = sistema.inBreadthIterator();

		BinaryTreeNode<NodoSalud> con = null; 
		int contador = 0;

		while(it.hasNext() && con == null){
			contador++;
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
			System.out.println("jjj");
			BinaryTreeNode<NodoSalud> nn = it.nextNode();
			NodoSalud nodo = nn.getInfo();
			System.out.println(((Consultorio)nodo).getNombre());
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
}
