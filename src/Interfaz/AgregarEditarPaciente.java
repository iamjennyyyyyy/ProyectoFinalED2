package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Persona.Paciente;
import Salud.Consultorio;
import Salud.Minsap;
import Salud.NodoSalud;
import Utiles.JTextFieldCarnet;
import Utiles.JTextFieldMejorado;
import Utiles.ModelPaciente;

import javax.swing.JComboBox;

import cu.edu.cujae.ceis.tree.TreeNode;
import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.general.GeneralTree;


public class AgregarEditarPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblNombre;
	private JLabel lblEdad;
	private JLabel lblCi;
	private JLabel lblSexo;
	private JButton btnSalir;
	private JLabel lblNewLabel;
	private JButton btnConfirmar;
	private JButton btnCancelar;
	private int pos = -1;
	private JButton btnGuardar;
	private JLabel label;
	private JTextPane txtpnOperacion;
	private JTextFieldMejorado textFieldNombreUsuario;
	private JTextFieldCarnet textFieldId;
	private JTextPane textPaneError;
	private JSeparator separator;
	private JSeparator separator_1;
	private JLabel lblCorreo;
	private JSeparator separator_2;
	private JTextFieldCarnet textFieldCarnet_1;
	private JLabel lblTelfono;
	private JSeparator separator_3;
	private JTextFieldMejorado textFieldMejorado;
	private JComboBox comboBox;
	private JLabel lblDireccin;
	private Minsap arbol = new Minsap();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AgregarEditarPaciente dialog = new AgregarEditarPaciente(null, null, true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AgregarEditarPaciente(ModelPaciente m, Paciente p, boolean agregar) {
		
		setBounds(338, 159, 778, 569);
		setModal(true);
		contentPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPanel.setBackground(Color.WHITE);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getTextPaneError());
		contentPanel.add(getLblNombre());
		contentPanel.add(getLblEdad());
		contentPanel.add(getLblSexo());
		contentPanel.add(getLblCi());
		contentPanel.add(getTextFieldNombreUsuario());
		contentPanel.add(getTextFieldId());
		contentPanel.add(getSeparator());
		contentPanel.add(getSeparator_1());
		contentPanel.add(getLblCorreo());
		contentPanel.add(getSeparator_2());
		contentPanel.add(getTextFieldCarnet_1());
		contentPanel.add(getLblTelfono());
		contentPanel.add(getSeparator_3());
		contentPanel.add(getTextFieldMejorado());
		contentPanel.add(getComboBox());
		contentPanel.add(getLblDireccin());
	}

	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setFont(new Font("Sylfaen", Font.PLAIN, 19));
			lblNombre.setBounds(50, 48, 85, 27);
		}
		return lblNombre;
	}

	private JLabel getLblEdad() {
		if (lblEdad == null) {
			lblEdad = new JLabel("Edad:");
			lblEdad.setFont(new Font("Sylfaen", Font.PLAIN, 19));
			lblEdad.setBounds(77, 349, 114, 27);
		}
		return lblEdad;
	}

	private JLabel getLblCi() {
		if (lblCi == null) {
			lblCi = new JLabel("CI:");
			lblCi.setFont(new Font("Sylfaen", Font.PLAIN, 19));
			lblCi.setBounds(95, 111, 40, 27);
		}
		return lblCi;
	}

	private JLabel getLblSexo() {
		if (lblSexo == null) {
			lblSexo = new JLabel("Sexo:");
			lblSexo.setFont(new Font("Sylfaen", Font.PLAIN, 19));
			lblSexo.setBounds(77, 403, 114, 27);
		}
		return lblSexo;
	}
	
	private JTextFieldMejorado getTextFieldNombreUsuario() {
		if (textFieldNombreUsuario == null) {
			textFieldNombreUsuario = new JTextFieldMejorado();
			textFieldNombreUsuario.setFont(new Font("Sylfaen", Font.PLAIN, 17));
			textFieldNombreUsuario.setBorder(null);
			textFieldNombreUsuario.setBounds(155, 42, 262, 30);
			textFieldNombreUsuario.putClientProperty("JTextField.placeholderText", "Ingrese su nombre");
			textFieldNombreUsuario.setEnabled(false);
		}
		return textFieldNombreUsuario;
	}
	private JTextFieldCarnet getTextFieldId() {
		if (textFieldId == null) {
			textFieldId = new JTextFieldCarnet();
			textFieldId.setFont(new Font("Sylfaen", Font.PLAIN, 17));
			textFieldId.setBorder(null);
			textFieldId.getDocument().addDocumentListener(new DocumentListener() {
				public void insertUpdate(DocumentEvent e) {
					handleTextChange();
				}
				public void removeUpdate(DocumentEvent e) {
					handleTextChange();
				}
				public void changedUpdate(DocumentEvent e) {
					// Vacío porque no lo uso
				}
				private void handleTextChange() {
					char[] cadena = textFieldId.getText().toCharArray();

					if(textFieldId.getText().length() >= 7){
						LocalDate fecha = obtenerFechaNacimiento(cadena);
						if(fecha != null){
							LocalDate hoy = LocalDate.now();
							int edad = Period.between(fecha, hoy).getYears();
							lblEdad.setText("Edad: " + edad);
							if(edad < 18 || edad > 110)
								lblEdad.setForeground(Color.RED);
							else
								lblEdad.setForeground(Color.BLACK);
							if(textFieldId.getText().length() >= 10){
								boolean esHombre = esHombre(cadena);
								if(esHombre){
									lblSexo.setText("Sexo: M");
								}
								else
									lblSexo.setText("Sexo: F");
							}
						}
					}
					else{
						textPaneError.setVisible(false);
					}

				}
			});
			textFieldId.setEnabled(false);
			textFieldId.setBounds(155, 101, 264, 30);
			textFieldId.setLimite(11);
			textFieldId.putClientProperty("JTextField.placeholderText", "Ingrese su carnet");
		}
		return textFieldId;
	}
	private JTextPane getTextPaneError() {
		if (textPaneError == null) {
			textPaneError = new JTextPane();
			textPaneError.setVisible(false);
			textPaneError.setForeground(Color.RED);
			textPaneError.setFont(new Font("Sylfaen", Font.PLAIN, 19));
			textPaneError.setText("Datos inv\u00E1lidos");
			textPaneError.setBounds(826, 493, 138, 34);
		}
		return textPaneError;
	}

	//Métodos CRUD
	public Paciente agregarPaciente(){
		
		boolean agregado = true;

		String nombre = textFieldNombreUsuario.getText();
		String idUsuario = textFieldId.getText();

		Paciente u = new Paciente();

		try{
			lblNombre.setForeground(Color.BLACK);
			u.setNombreCompleto(nombre);
			textPaneError.setVisible(false);
		}catch(IllegalArgumentException e){
			lblNombre.setForeground(Color.RED);
			textPaneError.setVisible(true);
			agregado = false;
			textFieldNombreUsuario.setText("");
		}
		try{
			textPaneError.setVisible(false);
			lblCi.setForeground(Color.BLACK);
			u.setId(idUsuario);
		}catch(IllegalArgumentException e){
			lblCi.setForeground(Color.RED);
			textPaneError.setVisible(true);
			textFieldId.setText("");
			agregado = false;
		}
		if(!agregado){
			u = null;
		}
		return u;
	}
	public Paciente editarPaciente(Paciente p){

		boolean editado = true;

		String nombre = textFieldNombreUsuario.getText();

		try{
			lblNombre.setForeground(Color.BLACK);
			p.setNombreCompleto(nombre);
			textPaneError.setVisible(false);
		}catch(IllegalArgumentException e){
			lblNombre.setForeground(Color.RED);
			textPaneError.setVisible(true);
			editado = false;
			textFieldNombreUsuario.setText("");
		}
		if(!editado){
			p = null;
		}
		return p;
	}

	public LocalDate obtenerFechaNacimiento(char[] cadena){

		boolean esCorrecta = true;
		// Extraer componentes de fecha
		int anio = (cadena[0] - '0') * 10 + (cadena[1] - '0'); 
		int mes = (cadena[2] - '0') * 10 + (cadena[3] - '0');
		int dia = (cadena[4] - '0') * 10 + (cadena[5] - '0');

		// Validar dígito del siglo (7mo dígito)
		int sigloDigito = cadena[6] - '0';
		if (sigloDigito < 0 || sigloDigito > 9) {
			esCorrecta = false;
		}

		// Ajustar año según siglo
		if (sigloDigito == 9) {
			anio += 1800; // Siglo XIX
		} else if (sigloDigito <= 5) {
			anio += 1900; // Siglo XX
		} else {
			anio += 2000; // Siglo XXI
		}

		// Validar rango de fecha (sin try-catch)
		if (mes < 1 || mes > 12) {
			esCorrecta = false;
		}
		if (dia < 1 || dia > 31) {
			esCorrecta = false;
		}
		if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia > 30) {
			esCorrecta = false;
		}
		if (mes == 2) {
			boolean esBisiesto = (anio % 4 == 0 && (anio % 100 != 0 || anio % 400 == 0));
			if (dia > (esBisiesto ? 29 : 28)) {
				esCorrecta = false;
			}
		}
		LocalDate fechaNacimiento = null;

		if(esCorrecta)
			fechaNacimiento = LocalDate.of(anio, mes, dia);

		return fechaNacimiento;
	}

	public boolean esHombre(char[] cadena){

		boolean esHombre = false;

		int digitoSexo = cadena[9] - '0';

		if(digitoSexo % 2 == 0)
			esHombre = true;

		return esHombre;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setForeground(Color.BLACK);
			separator.setBounds(155, 72, 262, 27);
		}
		return separator;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
			separator_1.setForeground(Color.BLACK);
			separator_1.setBounds(155, 131, 264, 27);
		}
		return separator_1;
	}
	private JLabel getLblCorreo() {
		if (lblCorreo == null) {
			lblCorreo = new JLabel("Correo:");
			lblCorreo.setFont(new Font("Sylfaen", Font.PLAIN, 19));
			lblCorreo.setBounds(63, 214, 83, 27);
		}
		return lblCorreo;
	}
	private JSeparator getSeparator_2() {
		if (separator_2 == null) {
			separator_2 = new JSeparator();
			separator_2.setForeground(Color.BLACK);
			separator_2.setBounds(153, 242, 264, 27);
		}
		return separator_2;
	}
	private JTextFieldCarnet getTextFieldCarnet_1() {
		if (textFieldCarnet_1 == null) {
			textFieldCarnet_1 = new JTextFieldCarnet();
			textFieldCarnet_1.setLimite(11);
			textFieldCarnet_1.setFont(new Font("Sylfaen", Font.PLAIN, 17));
			textFieldCarnet_1.setEnabled(false);
			textFieldCarnet_1.setBorder(null);
			textFieldCarnet_1.setBounds(153, 281, 264, 30);
		}
		return textFieldCarnet_1;
	}
	private JLabel getLblTelfono() {
		if (lblTelfono == null) {
			lblTelfono = new JLabel("Tel\u00E9fono:");
			lblTelfono.setFont(new Font("Sylfaen", Font.PLAIN, 19));
			lblTelfono.setBounds(45, 283, 83, 27);
		}
		return lblTelfono;
	}
	private JSeparator getSeparator_3() {
		if (separator_3 == null) {
			separator_3 = new JSeparator();
			separator_3.setForeground(Color.BLACK);
			separator_3.setBounds(153, 311, 264, 27);
		}
		return separator_3;
	}
	private JTextFieldMejorado getTextFieldMejorado() {
		if (textFieldMejorado == null) {
			textFieldMejorado = new JTextFieldMejorado();
			textFieldMejorado.setFont(new Font("Sylfaen", Font.PLAIN, 17));
			textFieldMejorado.setEnabled(false);
			textFieldMejorado.setBorder(null);
			textFieldMejorado.setBounds(153, 212, 264, 30);
		}
		return textFieldMejorado;
	}
	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
			ArrayList<Consultorio> c = obtenerListaConsultorios();
			model.addElement("Seleccione su consultorio");
			for(Consultorio con : c){
				model.addElement("" + con.getNumero());
			}
			comboBox.setBounds(153, 158, 264, 30);
		}
		return comboBox;
	}
	private ArrayList<Consultorio> obtenerListaConsultorios(){
		GeneralTree<NodoSalud> tree = arbol.getMinsap();
		List<TreeNode<NodoSalud>> c = tree.getLeaves();
		Iterator<TreeNode<NodoSalud>> it = c.iterator();
		ArrayList<Consultorio> consultorios = new ArrayList<Consultorio>();
		while(it.hasNext()){
			BinaryTreeNode<NodoSalud> nodo = (BinaryTreeNode<NodoSalud>)it.next();
			Consultorio con = (Consultorio)nodo.getInfo();
			consultorios.add(con);
		}
		return consultorios;
	}
	private JLabel getLblDireccin() {
		if (lblDireccin == null) {
			lblDireccin = new JLabel("Direcci\u00F3n:");
			lblDireccin.setFont(new Font("Sylfaen", Font.PLAIN, 19));
			lblDireccin.setBounds(34, 162, 96, 27);
		}
		return lblDireccin;
	}
}
