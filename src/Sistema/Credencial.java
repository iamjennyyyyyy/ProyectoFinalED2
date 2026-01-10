package Sistema;

public class Credencial {

		private String idMedico;
		private String usuario;
		private String contrasenna;
		
		public Credencial(String idMedico, String usuario, String contrasenna){
			setIdMedico(idMedico);
			setUsuario(usuario);
			setContrasenna(contrasenna);
		}
		
		public String getIdMedico() {return idMedico;}
		public void setIdMedico(String idMedico) {this.idMedico = idMedico;}
		public String getContrasenna() {return contrasenna;}
		public void setContrasenna(String contrasenna) {this.contrasenna = contrasenna;}
		public String getUsuario() {return usuario;}
		public void setUsuario(String usuario) {this.usuario = usuario;}

}
