package Salud;
import java.time.LocalDate;
import java.util.ArrayList;

import Auxiliar.PacientesPorEnfermedad;
import Persona.Paciente;
import Utiles.Enfermedad;

public class Consultorio extends NodoSalud {

	private int numero;
	private String consejoPopular;
    private ArrayList<Paciente> pacientes ;
	
	//Importar y poner grafo	
	
	public String getConsejoPopular() {
		return consejoPopular;
	}

	public void setConsejoPopular(String consejoPopular) {
		this.consejoPopular = consejoPopular;
	}

	public Consultorio(String codigo, String nombre, int numero, String consejo) {
		super(codigo, nombre);
		setNumero(numero);
		setConsejoPopular(consejo);
		pacientes = new ArrayList<Paciente>();
	}
	public void anadirPaciente(Paciente p ){
		pacientes.add(p);
	}
	public int getNumero(){ return numero;}
	public void setNumero(int numero){ this.numero = numero;}
	
	public ArrayList<PacientesPorEnfermedad> pacientesMesActual(){ // pacientes por enfermedd este mes 
		ArrayList<PacientesPorEnfermedad> list = new ArrayList<>();
		ArrayList<String> enfAgreg = new ArrayList<>();
		int m = LocalDate.now().getMonthValue();
		boolean listo = false;
		
		for(int i=pacientes.size();i>0 && listo;i--){
			if(pacientes.get(i).getFechaDiagnostico().getDayOfMonth() == m){
				for(Enfermedad e : pacientes.get(i).getEnfermedades()){
                    if(!enfAgreg.contains(e.getNombre())){					
                    	enfAgreg.add(e.getNombre());
                    	list.add(new PacientesPorEnfermedad(e.getNombre()));
                    	list.get(list.size()-1).aumentarCant();
                    }
                    else {
                    	int h= enfAgreg.indexOf(e.getNombre());
                    	list.get(h).aumentarCant();
                    }
				}
			}
			else
				listo=true;
		}
		return list;
	}
	
	public ArrayList<PacientesPorEnfermedad> pacientesMesAnterior(){// pacientes por enfermedad mes anterior
		ArrayList<PacientesPorEnfermedad> list = new ArrayList<>();
		ArrayList<String> enfAgreg = new ArrayList<>();
		int m = LocalDate.now().getMonthValue()-1;
		boolean listo = false;
		
		for(int i=pacientes.size();i>0 && !listo;i--){
			if(pacientes.get(i).getFechaDiagnostico().getDayOfMonth() == m){
				for(Enfermedad e : pacientes.get(i).getEnfermedades()){
                    if(!enfAgreg.contains(e.getNombre())){					
                    	enfAgreg.add(e.getNombre());
                    	list.add(new PacientesPorEnfermedad(e.getNombre()));
                    	list.get(list.size()-1).aumentarCant();
                    }
                    else {
                    	int h= enfAgreg.indexOf(e.getNombre());
                    	list.get(h).aumentarCant();
                    }
				}
			}
			else if(pacientes.get(i).getFechaDiagnostico().getDayOfMonth() == m-1)
				listo=true;
		}
		return list;
	}
	
	public ArrayList<PacientesPorEnfermedad> pacientesAnno(){ // cant pacientes por enfermedad anno actual
		ArrayList<PacientesPorEnfermedad> list = new ArrayList<>();
		ArrayList<String> enfAgreg = new ArrayList<>();
		int m = LocalDate.now().getYear();
		boolean listo = false;
		
		for(int i=pacientes.size();i>0 && !listo;i--){
			if(pacientes.get(i).getFechaDiagnostico().getYear() == m){
				for(Enfermedad e : pacientes.get(i).getEnfermedades()){
                    if(!enfAgreg.contains(e.getNombre())){					
                    	enfAgreg.add(e.getNombre());
                    	list.add(new PacientesPorEnfermedad(e.getNombre()));
                    	list.get(list.size()-1).aumentarCant();
                    }
                    else {
                    	int h= enfAgreg.indexOf(e.getNombre());
                    	list.get(h).aumentarCant();
                    }
				}
			}
			else 
				listo=true;
		}
		return list;
	}
	
	public ArrayList<PacientesPorEnfermedad> pacientesEsteDia(){ // Pacientes Por enfermedad Dia Actual
		ArrayList<PacientesPorEnfermedad> list = new ArrayList<>();
		ArrayList<String> enfAgreg = new ArrayList<>();
		
		boolean listo = false;
		
		for(int i=pacientes.size();i>0 && !listo;i--){
			if(pacientes.get(i).getFechaDiagnostico()==LocalDate.now()){
				for(Enfermedad e : pacientes.get(i).getEnfermedades()){
                    if(!enfAgreg.contains(e.getNombre())){					
                    	enfAgreg.add(e.getNombre());
                    	list.add(new PacientesPorEnfermedad(e.getNombre()));
                    	list.get(list.size()-1).aumentarCant();
                    }
                    else {
                    	int h= enfAgreg.indexOf(e.getNombre());
                    	list.get(h).aumentarCant();
                    }
				}
			}
			else 
				listo=true;
		}
		return list;
	}
	
	public int [] pacientesPorMeses(String enfermedad){ // para el anno 
		int [] array = new int[12];
		boolean listo = false;
		for(int i=pacientes.size(); i>0 && !listo ; i--){
			if(pacientes.get(i).getFechaDiagnostico().getYear()==LocalDate.now().getYear()){
			for(Enfermedad e: pacientes.get(i).getEnfermedades()){
				if(e.getNombre().equalsIgnoreCase(enfermedad)){
					array[pacientes.get(i).getFechaDiagnostico().getDayOfMonth()]++;
					
				}
			}
		}
			else
				listo = true;
		}
		return array;
	}
}
