package Persona;

public abstract class Persona {

	private String id;
	private String nombreCompleto;
	private String numero;
	private String correo;
	private String direccion;
	private String consultorio;
	
	public Persona() {
	}
	
	public Persona(String id, String nombreCompleto, String numero,
			String correo, String direccion, String consultorio) {
		setId(id);
		setNombreCompleto(nombreCompleto);
		setDireccion(direccion);
		setNumero(numero);
		setCorreo(correo);
		setConsultorio(consultorio);
	}
	public Persona(String id, String nombreCompleto, String numero,
			String correo, String direccion) {
		setId(id);
		setNombreCompleto(nombreCompleto);
		setDireccion(direccion);
		setNumero(numero);
		setCorreo(correo);
	}

	//Getters y setters
		public String getId() {return id;}
		public void setId(String id) {this.id = id;}
		public String getNumero() {return numero;}
		public void setNumero(String numero) {this.numero = numero;}
		public String getCorreo() {return correo;}
		public void setCorreo(String correo) {this.correo = correo;}
		public String getNombreCompleto() {return nombreCompleto;}
		public void setNombreCompleto(String nombre) {this.nombreCompleto = nombre;}
		public String getDireccion() {return direccion;}
		public void setDireccion(String direccion) {this.direccion = direccion;}
		public String getConsultorio() {return consultorio;}
		public void setConsultorio(String consultorio) {this.consultorio = consultorio;}
	
}
