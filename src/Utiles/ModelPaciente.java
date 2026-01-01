package Utiles;
import java.util.ArrayList;

import javax.swing.AbstractListModel;

import Persona.Paciente;


public class ModelPaciente extends AbstractListModel {
	
	private ArrayList <Paciente> lstPacientes = new ArrayList<Paciente>();

	// establece la lista de usuarios al modelo
	public void setlstPacientes(ArrayList<Paciente> lstPacientes) {
		this.lstPacientes = lstPacientes;
		this.fireIntervalAdded(this, 0, getSize());
	}

	// devuelve el tamaño de la lista del modelo
	public int getSize() {
		return lstPacientes.size();
	}

	// devuelve el elemento de la posición index dentro del modelo
	public Object getElementAt(int indice) {
		Paciente x = null;
		if (indice > -1 && indice < this.getSize())
			x = lstPacientes.get(indice);
		return x;
	}

	// agrega un usuario al final de la lista del modelo
	public void addPaciente(Paciente p) {
		lstPacientes.add(p);
		this.fireIntervalAdded(this, getSize(), getSize());
	}

	// agrega un usuario al modelo en la posición indice
	public Paciente getPacienteAt(int indice) {
		Paciente x = null;
		if (indice > -1 && indice < this.getSize())
			x = lstPacientes.get(indice);
		return x;
	}

	// elimina un usuario del modelo que ocupa la posición indice
	public void removePaciente (int indice) {
		if (indice > -1 && indice < this.getSize()) {
			lstPacientes.remove(indice);
			this.fireIntervalRemoved(this, indice, indice);
		}
	}
	// modifica en el modelo los valores del usuario en la posición indice
	public void updatePaciente (int indice, Paciente c) {
		if (indice > -1 && indice < this.getSize()) {
			lstPacientes.set(indice, c);
			this.fireContentsChanged(this, indice, indice);
		}
	}
}
