package Salud;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import Auxiliar.Estado;
import Auxiliar.Registro;
import Persona.Paciente;
import Utiles.Enfermedad;


public class ConsejoPopular { 

	private String nombre;
	private String codigo;
	private String municipio;
	private int totalPoblacion;
	private Estado estado;// necesito agregar para que enfermedad es la alerta
	private Map<Estado, ArrayList<Enfermedad>> estados;
	private LinkedList<Paciente> casos;
	private Map<Enfermedad, ArrayList<Registro> > registros; // Enfermedad -> Registro
	
	
	public ConsejoPopular(String codigo, String nombre, String municipio, int pob) {
		setNombre(nombre);
		setCodigo(codigo);
		setMunicipio(municipio);
		this.casos = new LinkedList<Paciente>();
		registros= new HashMap<Enfermedad, ArrayList<Registro>>();
		setTotalPoblacion(pob);
		estado = Estado.Normal;
		estados= new HashMap<Estado, ArrayList<Enfermedad>>();
		inicializarEstados();
		
	}
	public ConsejoPopular(String string, String string2, String string3, int i,
			Estado normal, Map<Estado, ArrayList<Enfermedad>> estadosLatino,
			Map<Enfermedad, ArrayList<Registro>> registrosLatino) {
		 nombre= string;
		codigo= string2;
		 municipio= string3;
		 totalPoblacion=i;
		estado= normal;// necesito agregar para que enfermedad es la alerta
		estados= estadosLatino;
		
		registros = registrosLatino; // Enfermedad -> Registr
		
		
	}
	public ConsejoPopular() {
		// TODO Auto-generated constructor stub
	}
	private void inicializarEstados(){
		for(Estado e: Estado.values()){
			estados.put(e, new ArrayList<Enfermedad>());
		}
		
	}
	public Estado getEstado() {
		return estado;
	}
	
	public ArrayList<Enfermedad> obtenerEnfermedadesEnEpidemia(){// obtener las enfermedades declaradas como epidemias 
		return estados.get(Estado.Epidemia);
	}
	public ArrayList<Enfermedad> obtenerEnfermedadesEnAlerta(){// obtener las enfermedades declaradas como epidemias 
		return estados.get(Estado.Alerta_Epidemica);
	}

	public void declararAlerta(ArrayList<Enfermedad>enf ){// declarar alerta por un consejo popular cercano infectado
		if(estado != Estado.Epidemia)
		estado = Estado.Alerta_Epidemica;
		
		for(Enfermedad e: enf){
			
			if(!estados.get(Estado.Epidemia).contains(e)){// no tener en cuenta la enfermedad si ya hay epidemia 
			registros.get(e).get(registros.size()-1).getMeses().get((LocalDate.now().getDayOfMonth())- 1).declararAlerta();
			}
		}
	}
	 public void declararNormalParaEnfermeda(Enfermedad enf){
		 estados.get(Estado.Alerta_Epidemica).remove(enf);
		 estados.get(Estado.Normal).add(enf);
			if(estados.get(Estado.Epidemia).size()==0){
				if(estados.get(Estado.Alerta_Epidemica).size()==0){
					estado = Estado.Normal;
				}
				else
					estado = Estado.Alerta_Epidemica;
			}
		 
	 }

	public ArrayList<Registro> obtenerRegistrosDeEnfermedad(String enfermedad){
		return registros.get(enfermedad);
	}
	
	public Estado anadirPacienteEnfermo(Paciente p, ArrayList<Enfermedad> finDeEpidemia){
		ArrayList<Enfermedad> e = p.getEnfermedades();
		for(Enfermedad enf: e){
			
			if(!registros.containsKey(enf)){ // si no existe esa enfermedad agregarla y crear un registro
				registros.put(enf, new ArrayList<Registro>());
				registros.get(enf).add(new Registro());
			}
			
			ArrayList<Registro> r = obtenerRegistrosDeEnfermedad(enf.getNombre());
			if(r.get(r.size()).getAnno() == LocalDate.now().getYear()){ // si ya existye un registro del anno annadir el caso
				r.get(r.size()).anadirCaso();
			}
			else { // si no existe crearlo y luego annadir el caso
				r.add(new Registro());
				r.get(r.size()).anadirCaso();
			}
			if(!estados.get(Estado.Epidemia).contains(e)) // si la enfermedad no esta declarada como epidemia 
			verificarEpidemia(enf, r, finDeEpidemia);// verificar si hay epidemia de esa enfermedad 
		}
		System.out.println(estado==null? "nullllll": "bieen");
			return estado ; 
	}
	
	
	private boolean verificarEpidemia(Enfermedad enf, ArrayList<Registro> reg, ArrayList<Enfermedad> finDeEpidemia){
		boolean focoEpidemico = false;
		int promedio = 0;
		int noIncluidas =0;
		 for(int i=0; i<reg.size()-1; i++){ // recorrer la lista d registros anuales d la enfermedad 
			 if(reg.get(i).getMeses().get((LocalDate.now().getDayOfMonth())- 1).getEstado() != Estado.Epidemia) // si en ese mes la enfermedad no fue declarada epidemia incluirla en el promedio
			 promedio += reg.get(i).getMeses().get((LocalDate.now().getDayOfMonth())- 1).getCantidadCasos();
			 else 
				 noIncluidas++;
		 }
		 promedio /= (reg.size()-noIncluidas);
		 if((reg.get(reg.size()-1).getMeses().get((LocalDate.now().getDayOfMonth())- 1).getCantidadCasos() - promedio)/promedio *100 >= 30){ // verificar que la cantidad de este anno no exceda el 30% de la media historica
			 reg.get(reg.size()-1).getMeses().get((LocalDate.now().getDayOfMonth())- 1).declararEpidemia();// esto lo debe hacer el registro, hay q cambiarlo y si por ejemplo el mes anterior habia alerta mantener el actual en alerta hasta q se elimine esa alerta 
			 focoEpidemico=true;
			 estado = Estado.Epidemia;
			 estados.get(Estado.Epidemia).add(enf);
			 if(estados.get(Estado.Alerta_Epidemica).contains(enf)){// si la enfermedad estaba en alerta removerla de ahi pues fue declarada epidemia 
				 estados.get(Estado.Alerta_Epidemica).remove(enf);
				 
			 }
			 else if(estados.get(Estado.Normal).contains(enf)){// si estaba como normal lo mismo 
				 estados.get(Estado.Normal).remove(enf);
			 }
		 }else if(reg.get(reg.size()-2).getMeses().get((LocalDate.now().getDayOfMonth())- 1).getEstado()!= Estado.Epidemia){	// si lleva 1 mes sin declararse epidemia nuevamente pues eliminarla de la lista de epidemias 
			finEpidemia(enf);
			finDeEpidemia.add(enf);
		 }
		 
		return focoEpidemico;
		
	}
	
	// anadir metodo de verificacion del fin d la epidemia 
	
	private void finEpidemia(Enfermedad enf){
		estados.get(Estado.Epidemia).remove(enf);
		 estados.get(Estado.Normal).add(enf);
		if(estados.get(Estado.Epidemia).size()==0){
			if(estados.get(Estado.Alerta_Epidemica).size()==0){
				estado = Estado.Normal;
			}
			else
				estado = Estado.Alerta_Epidemica;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	public int getCasosEnfermedad(String enfermedad){
		int cont = 0;
		for(Paciente p : casos){
			if(pacienteEnfermoDe(enfermedad, p))
				cont++;
		}
		return cont;
	}
	
	
	
	public boolean pacienteEnfermoDe(String enfermedad, Paciente p){
		boolean enfermo = false;
		for(int i = 0; i < p.getEnfermedades().size() && !enfermo; i++){
			if(p.getEnfermedades().get(i).getNombre().equalsIgnoreCase(enfermedad))
				enfermo = true;
		}
		return enfermo;
	}
	
	//Getters y setters
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	public String getCodigo() {return codigo;}
	public void setCodigo(String codigo) {this.codigo = codigo;}
	public String getMunicipio() {return municipio;}
	public void setMunicipio(String municipio) {this.municipio = municipio;}
	public LinkedList<Paciente> getCasos() {return casos;}
	public void setCasos(LinkedList<Paciente> casos) {this.casos = casos;}

	public int getTotalPoblacion() {
		return totalPoblacion;
	}

	public void setTotalPoblacion(int totalPoblacion) {
		this.totalPoblacion = totalPoblacion;
	}
	public void setEstado(Estado normal) {
		estado= normal;
		
	}

	


	
	
	
	
}
