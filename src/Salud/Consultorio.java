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
	public ArrayList<Paciente> getPacientes(){ return pacientes;}
	public int getNumero(){ return numero;}
	public void setNumero(int numero){ this.numero = numero;}
	
	public ArrayList<PacientesPorEnfermedad> pacientesMesActual(){ // pacientes por enfermedd este mes 
		ArrayList<PacientesPorEnfermedad> list = new ArrayList<>();
		ArrayList<String> enfAgreg = new ArrayList<>();
		int m = LocalDate.now().getMonthValue();
		boolean listo = false;
		
		for(int i = pacientes.size()-1 ; i >= 0 && !listo ; i--){
			if(pacientes.get(i).getFechaDiagnostico().getMonthValue() == m){
				System.out.println("entra al if de la fecha");
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
		    
		    // Calcular mes anterior considerando cambio de año
		    LocalDate hoy = LocalDate.now();
		    LocalDate mesAnteriorFecha = hoy.minusMonths(1);
		    int mesAnterior = mesAnteriorFecha.getMonthValue();
		    int anioMesAnterior = mesAnteriorFecha.getYear();
		    
		    boolean listo = false;
		    boolean encontradoMesAnterior = false;
		    
		    // Recorrer de más reciente a más antiguo
		    for (int i = pacientes.size() - 1; i >= 0 && !listo; i--) {
		        LocalDate fechaDiag = pacientes.get(i).getFechaDiagnostico();
		        
		        // Verificar si es del mes anterior
		        if (fechaDiag.getMonthValue() == mesAnterior && 
		            fechaDiag.getYear() == anioMesAnterior) {
		            
		            encontradoMesAnterior = true;
		            
		            for (Enfermedad e : pacientes.get(i).getEnfermedades()) {
		                if (!enfAgreg.contains(e.getNombre())) {                    
		                    enfAgreg.add(e.getNombre());
		                    list.add(new PacientesPorEnfermedad(e.getNombre()));
		                    list.get(list.size() - 1).aumentarCant();
		                } else {
		                    int h = enfAgreg.indexOf(e.getNombre());
		                    list.get(h).aumentarCant();
		                }
		            }
		        }
		        // Como están ordenados: si ya pasamos el mes anterior y encontramos
		        // una fecha más antigua, podemos detenernos
		        else if (encontradoMesAnterior && 
		                (fechaDiag.getYear() < anioMesAnterior || 
		                (fechaDiag.getYear() == anioMesAnterior && 
		                 fechaDiag.getMonthValue() < mesAnterior))) {
		            listo = true;
		        }
		        // Si encontramos una fecha del mes actual o futuro, seguimos buscando
		        // (esto maneja el caso donde hay fechas más recientes que el mes anterior)
		    }
		    
		    return list;
		}
	
	public ArrayList<PacientesPorEnfermedad> pacientesAnno(){ // cant pacientes por enfermedad anno actual
		ArrayList<PacientesPorEnfermedad> list = new ArrayList<>();
		ArrayList<String> enfAgreg = new ArrayList<>();
		int m = LocalDate.now().getYear();
		boolean listo = false;
		
		for(int i=pacientes.size()-1;i>=0 && !listo;i--){
			if(pacientes.get(i).getFechaDiagnostico().getYear() == m){
				System.out.println("entra al if de la fecha");
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
		
		for(int i=pacientes.size()-1;i>=0 && !listo;i--){
			System.out.println("entro");
			if(pacientes.get(i).getFechaDiagnostico().isEqual(LocalDate.now())){
				System.out.println("entra al if de la fecha");
				for(Enfermedad e : pacientes.get(i).getEnfermedades()){
					System.out.println("entra al for de enfermedades");
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
		for(int i=pacientes.size()-1; i>=0 && !listo ; i--){
			if(pacientes.get(i).getFechaDiagnostico().getYear()==LocalDate.now().getYear()){
			for(Enfermedad e: pacientes.get(i).getEnfermedades()){
				if(e.getNombre().equalsIgnoreCase(enfermedad)){
					array[pacientes.get(i).getFechaDiagnostico().getMonthValue()-1]++;
					
				}
			}
		}
			else
				listo = true;
		}
		return array;
	}
	
	public int [] pacientesPorMesesAnno(String enfermedad, int anno){ // para el anno 
		int [] array = new int[12];
		boolean listo = false;
		for(int i=pacientes.size()-1; i>=0 && !listo ; i--){
			if(pacientes.get(i).getFechaDiagnostico().getYear()==anno){
			for(Enfermedad e: pacientes.get(i).getEnfermedades()){
				if(e.getNombre().equalsIgnoreCase(enfermedad)){
					array[pacientes.get(i).getFechaDiagnostico().getMonthValue()-1]++;
					
				}
			}
		}
			else
				listo = true;
		}
		return array;
	}
}
