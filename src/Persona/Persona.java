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
	public String getNombreCompleto() {return nombreCompleto;}
	public String getId() {return id;}
	public String getNumero() {return numero;}
	public String getCorreo() {return correo;}
	public String getDireccion() {return direccion;}
	public String getConsultorio() {return consultorio;}
	
	public void setId(String id) {
		if(id.isEmpty() || id.trim().length() < 11 || id.trim().length() > 11)
			throw new IllegalArgumentException("MAL");
		else
			this.id = id;
	}
	
	public void setNumero(String numero) {
		if(numero.trim().length() < 8)
			throw new IllegalArgumentException("MAL");
		else
			this.numero = numero;
	}
	
	public void setCorreo(String correo) {
		if(correo.length() <= 10 || !correo.contains("@gmail.com"))
			throw new IllegalArgumentException();
		else
			this.correo = correo;
	}
	
	public void setNombreCompleto(String nombre) {
		if(nombre.length() <= 8)
			throw new IllegalArgumentException();
		else
			this.nombreCompleto = nombre;
	}
	
	public void setDireccion(String direccion) {this.direccion = direccion;}
	public void setConsultorio(String consultorio) {this.consultorio = consultorio;}

}
