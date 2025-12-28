package Salud;

import cu.edu.cujae.ceis.tree.TreeNode;
import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.general.GeneralTree;

public class Minsap {

	private GeneralTree<NodoSalud> minsap;
	
	public Minsap(){
		
	}
	
	public void Inicializar(){
		BinaryTreeNode<NodoSalud> habana = new BinaryTreeNode<NodoSalud>(new DireccionProvincial("HAB-000", "Direccion_Provincial_La_Habana", "La Habana"));
		minsap.setRoot(habana);
		BinaryTreeNode<NodoSalud> laLisa = new BinaryTreeNode<NodoSalud>(new DireccionMunicipal("HAB-001", "Direccion_Municipal_La_Lisa", 132135, "La Lisa"));
		BinaryTreeNode<NodoSalud> cerro = new BinaryTreeNode<NodoSalud>(new DireccionMunicipal("HAB-002", "Direccion_Municipal_Cerro", 126851, "Cerro"));
		BinaryTreeNode<NodoSalud> plaza = new BinaryTreeNode<NodoSalud>(new DireccionMunicipal("HAB-003", "Direccion_Municipal_Plaza", 136634, "Plaza de la Revolución"));
		BinaryTreeNode<NodoSalud> playa = new BinaryTreeNode<NodoSalud>(new DireccionMunicipal("HAB-004", "Direccion_Municipal_Playa", 186148, "Playa"));
		minsap.insertNode(laLisa, habana);
		minsap.insertNode(cerro, habana);
		minsap.insertNode(plaza, habana);
		minsap.insertNode(playa, habana);
		BinaryTreeNode<NodoSalud> pCristL = new BinaryTreeNode<NodoSalud>(new Policlinico("HAB-LISA-001", "Policlinico_Cristobal_Labra", "Cristobal Labra"));
		BinaryTreeNode<NodoSalud> pAleidaF = new BinaryTreeNode<NodoSalud>(new Policlinico("HAB-LISA-002", "Policlinico_Aleida_Fernandez", "Aleida Fernandez"));
		BinaryTreeNode<NodoSalud> p19Abril = new BinaryTreeNode<NodoSalud>(new Policlinico("HAB-PLAZA-001", "Policlinico_19_de_Abril", "19 de Abril"));
		BinaryTreeNode<NodoSalud> p28Enero = new BinaryTreeNode<NodoSalud>(new Policlinico("HAB-PLAZA-002", "Policlinico_28_de_Enero", "28 de Enero"));
		minsap.insertNode(pCristL, laLisa);
		minsap.insertNode(pAleidaF, laLisa);
		minsap.insertNode(p19Abril, plaza);
		minsap.insertNode(p28Enero, plaza);
		BinaryTreeNode<NodoSalud> c40 = new BinaryTreeNode<NodoSalud>(new Consultorio("HAB-LISA-CRISTOBAL-001", "Consultorio_40", 40));
		BinaryTreeNode<NodoSalud> c41 = new BinaryTreeNode<NodoSalud>(new Consultorio("HAB-LISA-CRISTOBAL-002", "Consultorio_40", 41));
		BinaryTreeNode<NodoSalud> c42 = new BinaryTreeNode<NodoSalud>(new Consultorio("HAB-LISA-CRISTOBAL-003", "Consultorio_40", 42));
		minsap.insertNode(c40, pCristL);
		minsap.insertNode(c41, pCristL);
		minsap.insertNode(c42, pCristL);
	}
}
