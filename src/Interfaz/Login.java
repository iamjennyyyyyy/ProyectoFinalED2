package Interfaz;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;

import Auxiliar.Sistema;
import Persona.Medico;
import Sistema.Credencial;
import Utiles.Colores;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;
	private JLabel lblIniciarSesion;
	private JLabel lblUsuario;
	private JLabel lblContrasea;
	private JTextField txtIngreseUnNombre;
	private JSeparator separator;
	private JSeparator separator_1;
	private JButton btnIniciarSesion;
	private JButton btnSalir;
	private JPasswordField passwordField;
	private JLabel lblNewLabel_1;
	Sistema s = Sistema.getInstancia();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Login() {

		setBackground(Color.WHITE);
		setAlwaysOnTop(true);
		setUndecorated(true);
		setTitle("Inicio Sesi\u00F3n");
		setBounds(0, 0, 1366, 768);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getPasswordField());
		contentPanel.add(getTxtIngreseUnNombre());
		contentPanel.add(getLblIniciarSesion());
		contentPanel.add(getLblUsuario());
		contentPanel.add(getLblContrasea());
		contentPanel.add(getSeparator());
		contentPanel.add(getBtnIniciarSesion());
		contentPanel.add(getBtnSalir());
		contentPanel.add(getSeparator_1());
		contentPanel.add(getLblNewLabel());
		contentPanel.add(getLblNewLabel_1());
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setBounds(0, 0, 1366, 768);
			lblNewLabel.setIcon(new ImageIcon("src/Images/Posters/loginN.png"));
		}
		return lblNewLabel;
	}
	private JLabel getLblIniciarSesion() {
		if (lblIniciarSesion == null) {
			lblIniciarSesion = new JLabel("INICIAR SESION");
			lblIniciarSesion.setBounds(683, 126, 571, 55);
			lblIniciarSesion.setHorizontalAlignment(SwingConstants.CENTER);
			lblIniciarSesion.setFont(new Font("Book Antiqua", Font.PLAIN, 35));
		}
		return lblIniciarSesion;
	}
	private JLabel getLblUsuario() {
		if (lblUsuario == null) {
			lblUsuario = new JLabel("Usuario:");
			lblUsuario.setBounds(656, 245, 571, 55);
			lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);
			lblUsuario.setFont(new Font("Book Antiqua", Font.PLAIN, 29));
		}
		return lblUsuario;
	}
	private JLabel getLblContrasea() {
		if (lblContrasea == null) {
			lblContrasea = new JLabel("Contrase\u00F1a:");
			lblContrasea.setHorizontalAlignment(SwingConstants.LEFT);
			lblContrasea.setFont(new Font("Book Antiqua", Font.PLAIN, 29));
			lblContrasea.setBounds(656, 437, 571, 55);
		}
		return lblContrasea;
	}
	private JTextField getTxtIngreseUnNombre() {
		if (txtIngreseUnNombre == null) {
			txtIngreseUnNombre = new JTextField();
			txtIngreseUnNombre.setFont(new Font("SansSerif", Font.PLAIN, 19));
			txtIngreseUnNombre.putClientProperty("JTextField.placeholderText", "Ingrese su usuario");
			txtIngreseUnNombre.setForeground(Color.BLACK);
			txtIngreseUnNombre.setBorder(new CompoundBorder());
			txtIngreseUnNombre.setBounds(656, 311, 576, 48);
			txtIngreseUnNombre.setColumns(10);
		}
		return txtIngreseUnNombre;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setBorder(new LineBorder(new Color(0, 0, 0)));
			separator.setForeground(Color.BLACK);
			separator.setOpaque(true);
			separator.setBounds(656, 311, 571, 49);
		}
		return separator;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
			separator_1.setOpaque(true);
			separator_1.setForeground(Color.BLACK);
			separator_1.setBorder(new LineBorder(new Color(0, 0, 0)));
			separator_1.setBounds(656, 520, 571, 49);
		}
		return separator_1;
	}
	
	private Medico obtenerCredencial(){
		String contrasenna = new String(passwordField.getPassword());
		String usuario = txtIngreseUnNombre.getText();
		Medico med = null;
		
		for(Credencial c : s.getCredenciales()){
			if(c.getUsuario().equals(usuario) && c.getContrasenna().equals(contrasenna)){
				med = s.buscarMedicoPorId(c.getIdMedico());
			}
		}
		return med;
	}
	
	private boolean isAdmin(){
		String contrasenna = new String(passwordField.getPassword());
		String usuario = txtIngreseUnNombre.getText();
		return contrasenna.equals("06071267912") && usuario.equals("jenn006");
	}
	
	private JButton getBtnIniciarSesion() {
		if (btnIniciarSesion == null) {
			btnIniciarSesion = new JButton("Entrar");
			btnIniciarSesion.setBackground(Colores.getAzulLogin());
			btnIniciarSesion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						Medico medicoLog = obtenerCredencial();
						if(medicoLog != null){
							dispose();
							Principal p = new Principal(medicoLog);
							p.setVisible(true);
						}
						else if(isAdmin()){
							dispose();
							PrincipalAdmin p = new PrincipalAdmin();
							p.setVisible(true);
						}
						else {
							JOptionPane.showMessageDialog(Login.this,"Credenciales incorrectas", "Error",JOptionPane.ERROR_MESSAGE);
						}
					} finally {
						txtIngreseUnNombre.setText("");
						passwordField.setText("");
					}
				}
			});
			btnIniciarSesion.setForeground(Color.BLACK);
			//btnIniciarSesion.setBackground(Colores.getBeigetabla());
			btnIniciarSesion.setFont(new Font("SansSerif", Font.PLAIN, 22));
			btnIniciarSesion.setBounds(744, 640, 138, 48);
		}
		return btnIniciarSesion;
	}
	private JButton getBtnSalir() {
		if (btnSalir == null) {
			btnSalir = new JButton("Salir");
			btnSalir.setBackground(Colores.getAzulLogin());
			btnSalir.setForeground(Color.BLACK);
			//btnSalir.setBackground(Colores.getBeigetabla());
			btnSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnSalir.setFont(new Font("SansSerif", Font.PLAIN, 22));
			btnSalir.setBounds(991, 640, 138, 48);
		}
		return btnSalir;
	}
	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
			passwordField.setFont(new Font("Tahoma", Font.PLAIN, 19));
			passwordField.setBorder(null);
			passwordField.setBounds(656, 520, 571, 48);
		}
		return passwordField;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setIcon(new ImageIcon("src/images/iconos/loginmejor3.jpg"));
			lblNewLabel_1.setIgnoreRepaint(true);
			lblNewLabel_1.setBounds(576, 11, 181, 121);
		}
		return lblNewLabel_1;
	}
}
