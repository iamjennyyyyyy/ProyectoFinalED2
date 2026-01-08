package Auxiliar;

public class PacientesPorEnfermedad {
	private int cantPacientes;
	private String enfermedad;
	
	
	public PacientesPorEnfermedad(String enfermedad) {
		super();
		this.enfermedad=enfermedad;
		cantPacientes=0;
	}
	
	public void aumentarCant(){
		cantPacientes++;
	}

	public int getCantPacientes() {
		return cantPacientes;
	}

	public String getEnfermedad() {
		return enfermedad;
	}

	

}
