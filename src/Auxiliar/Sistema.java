package Auxiliar;

import java.util.ArrayList;

import Persona.Paciente;
import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.general.GeneralTree;
import cu.edu.cujae.ceis.tree.iterators.general.InBreadthIterator;
import Salud.Consultorio;
import Salud.DireccionMunicipal;
import Salud.DireccionProvincial;
import Salud.NodoSalud;
import Salud.Policlinico;

public class Sistema {
	
	private static GeneralTree<NodoSalud> sistema;
	private static Sistema instancia;
	
	public static Sistema getInstancia(){
		if(instancia==null){
			instancia = new Sistema();
			sistema = Inicializar.inicializarArbol();		}
		return instancia;
	}
	 
	public void Inicializar(){
		BinaryTreeNode<NodoSalud> habana = new BinaryTreeNode<NodoSalud>(new DireccionProvincial("HAB-000", "Direccion_Provincial_La_Habana", "La Habana"));
		sistema.setRoot(habana);
		BinaryTreeNode<NodoSalud> laLisa = new BinaryTreeNode<NodoSalud>(new DireccionMunicipal("HAB-001", "Direccion_Municipal_La_Lisa", 132135, "La Lisa"));
		BinaryTreeNode<NodoSalud> cerro = new BinaryTreeNode<NodoSalud>(new DireccionMunicipal("HAB-002", "Direccion_Municipal_Cerro", 126851, "Cerro"));
		BinaryTreeNode<NodoSalud> plaza = new BinaryTreeNode<NodoSalud>(new DireccionMunicipal("HAB-003", "Direccion_Municipal_Plaza", 136634, "Plaza de la Revoluci�n"));
		BinaryTreeNode<NodoSalud> playa = new BinaryTreeNode<NodoSalud>(new DireccionMunicipal("HAB-004", "Direccion_Municipal_Playa", 186148, "Playa"));
		sistema.insertNode(laLisa, habana);
		sistema.insertNode(cerro, habana);
		sistema.insertNode(plaza, habana);
		sistema.insertNode(playa, habana);
		BinaryTreeNode<NodoSalud> pCristL = new BinaryTreeNode<NodoSalud>(new Policlinico("HAB-LISA-001", "Policlinico_Cristobal_Labra", "Cristobal Labra"));
		BinaryTreeNode<NodoSalud> pAleidaF = new BinaryTreeNode<NodoSalud>(new Policlinico("HAB-LISA-002", "Policlinico_Aleida_Fernandez", "Aleida Fernandez"));
		BinaryTreeNode<NodoSalud> p19Abril = new BinaryTreeNode<NodoSalud>(new Policlinico("HAB-PLAZA-001", "Policlinico_19_de_Abril", "19 de Abril"));
		BinaryTreeNode<NodoSalud> p28Enero = new BinaryTreeNode<NodoSalud>(new Policlinico("HAB-PLAZA-002", "Policlinico_28_de_Enero", "28 de Enero"));
		sistema.insertNode(pCristL, laLisa);
		sistema.insertNode(pAleidaF, laLisa);
		sistema.insertNode(p19Abril, plaza);
		sistema.insertNode(p28Enero, plaza);
		BinaryTreeNode<NodoSalud> c40 = new BinaryTreeNode<NodoSalud>(new Consultorio("HAB-LISA-CRISTOBAL-001", "Consultorio_40", 40, "mmm"));
		BinaryTreeNode<NodoSalud> c41 = new BinaryTreeNode<NodoSalud>(new Consultorio("HAB-LISA-CRISTOBAL-002", "Consultorio_40", 41,"nnnn"));
		BinaryTreeNode<NodoSalud> c42 = new BinaryTreeNode<NodoSalud>(new Consultorio("HAB-LISA-CRISTOBAL-003", "Consultorio_40", 42,"gggg"));
		sistema.insertNode(c40, pCristL);
		sistema.insertNode(c41, pCristL);
		sistema.insertNode(c42, pCristL);
	}
	
	public BinaryTreeNode<NodoSalud> buscarConsultorio(String consultorio){
		InBreadthIterator<NodoSalud> it = sistema.inBreadthIterator();
		BinaryTreeNode<NodoSalud> con = null; 
		
		while(it.hasNext() && con == null){
			BinaryTreeNode<NodoSalud> nn = it.nextNode();
			NodoSalud nodo = nn.getInfo();
			if(nodo instanceof Consultorio && ((Consultorio)nodo).getNombre().equals(consultorio)){
				con = nn;
			}
		}
		
		return con;
	}
	
	public void agregarEnfermo(String consultorio, Paciente p ){
		BinaryTreeNode<NodoSalud> con = buscarConsultorio(consultorio);
		Consultorio c = (Consultorio)con.getInfo();
		c.anadirPaciente(p);
		DireccionMunicipal mun = buscarMunicipioPorConsultori(con);
		mun.anadirEnfermo(p, c);
		
		
		
	}
	public DireccionMunicipal buscarMunicipioPorConsultori(BinaryTreeNode<NodoSalud> con){
		return (DireccionMunicipal)(sistema.getFather(sistema.getFather(con)).getInfo());
		
	}
	
	public ArrayList<DireccionMunicipal> getMunicipiosHabana(){
		ArrayList <DireccionMunicipal> lista = new ArrayList<DireccionMunicipal>();
	    BinaryTreeNode <NodoSalud > nodo = null;
	    InBreadthIterator<NodoSalud> it = sistema.inBreadthIterator();
		 while (it.hasNext() && nodo ==null){
			 BinaryTreeNode<NodoSalud> este = it.nextNode();
			 if(este.getInfo() instanceof DireccionProvincial && ((DireccionProvincial)(este.getInfo())).getProvincia().equalsIgnoreCase("La Habana")){
				 nodo = este;
			 }
		 }
		 
		 for (NodoSalud s: sistema.getSonsInfo(nodo)){
			 lista.add((DireccionMunicipal)s);
		 }
		 return lista;
	}
}
