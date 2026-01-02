package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Persona.Paciente;
import Salud.Consultorio;
import Salud.Minsap;
import Salud.NodoSalud;
import Utiles.JTextFieldCarnet;
import Utiles.JTextFieldMejorado;
import Utiles.Sintomas;
import cu.edu.cujae.ceis.tree.TreeNode;
import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.general.GeneralTree;

import javax.swing.JList;
import javax.swing.border.LineBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class Diagnostico extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblNombre;
	private JLabel lblEdad;
	private JLabel lblCi;
	private JLabel lblSexo;
	private int pos = -1;
	private Sintomas[] sintomasSel;
	private JScrollPane scrollSintomas;
	private JTextFieldMejorado textNombre;
	private JTextFieldCarnet textId;
	private JSeparator separator;
	private JSeparator separator_1;
	private JLabel lblCorreo;
	private JSeparator separator_2;
	private JTextFieldCarnet textTelefono;
	private JLabel lblTelfono;
	private JSeparator separator_3;
	private JLabel lblDireccin;
	private JSeparator separator_4;
	private JComboBox comboBox;
	private JLabel lblSntomaspresioneCtrl;
	private JLabel lblDatosPersonales;
	private JButton btnSalir;
	private JButton btnGuardar;
	private JTextPane textPaneSint;
	private JList list;
	private JButton btnRealizarDiagnstico;
	private JTextField textDireccion;
	private JTextField textCorreo;
	private JTextField textField_1;


	public static void main(String[] args) {
		try {
			Diagnostico dialog = new Diagnostico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Diagnostico() {
		setBounds(296, 164, 1070, 558);
		setUndecorated(true);
		getContentPane().setLayout(null);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		contentPanel.setBounds(0, 0, 1071, 559);
		contentPanel.add(getLblNombre());
		contentPanel.add(getLblEdad());
		contentPanel.add(getLblSexo());
		contentPanel.add(getLblCi());
		contentPanel.add(getTextNombre());
		contentPanel.add(getTextId());
		contentPanel.add(getSeparator());
		contentPanel.add(getSeparator_1());
		contentPanel.add(getLblCorreo());
		contentPanel.add(getSeparator_2());
		contentPanel.add(getTextTelefono());
		contentPanel.add(getLblTelfono());
		contentPanel.add(getSeparator_3());
		contentPanel.add(getLblDireccin());
		contentPanel.add(getSeparator_4());
		getContentPane().add(contentPanel);
		contentPanel.add(getScrollSintomas());
		contentPanel.add(getLblSntomaspresioneCtrl());
		contentPanel.add(getLblDatosPersonales());
		contentPanel.add(getBtnSalir());
		contentPanel.add(getBtnGuardar());
		contentPanel.add(getTextPaneSint());
		contentPanel.add(getBtnRealizarDiagnstico());
		contentPanel.add(getTextDireccion());
		contentPanel.add(getTextCorreo());
		contentPanel.add(getTextField_1());
	}

	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setFont(new Font("Sylfaen", Font.PLAIN, 19));
			lblNombre.setBounds(58, 170, 85, 27);
		}
		return lblNombre;
	}

	private JLabel getLblEdad() {
		if (lblEdad == null) {
			lblEdad = new JLabel("Edad:");
			lblEdad.setFont(new Font("Sylfaen", Font.PLAIN, 19));
			lblEdad.setBounds(90, 409, 114, 27);
		}
		return lblEdad;
	}

	private JLabel getLblCi() {
		if (lblCi == null) {
			lblCi = new JLabel("CI:");
			lblCi.setFont(new Font("Sylfaen", Font.PLAIN, 19));
			lblCi.setBounds(108, 114, 40, 27);
		}
		return lblCi;
	}

	private JLabel getLblSexo() {
		if (lblSexo == null) {
			lblSexo = new JLabel("Sexo:");
			lblSexo.setFont(new Font("Sylfaen", Font.PLAIN, 19));
			lblSexo.setBounds(90, 463, 114, 27);
		}
		return lblSexo;
	}

	private JTextFieldMejorado getTextNombre() {
		if (textNombre == null) {
			textNombre = new JTextFieldMejorado();
			textNombre.setBackground(Color.WHITE);
			textNombre.setFont(new Font("Sylfaen", Font.PLAIN, 17));
			textNombre.setBorder(null);
			textNombre.setBounds(168, 158, 315, 30);
			textNombre.putClientProperty("JTextField.placeholderText", "Ingrese su nombre");
		}
		return textNombre;
	}
	private JTextFieldCarnet getTextId() {
		if (textId == null) {
			textId = new JTextFieldCarnet();
			textId.setBackground(Color.WHITE);
			textId.setFont(new Font("Sylfaen", Font.PLAIN, 17));
			textId.setBorder(null);
			textId.getDocument().addDocumentListener(new DocumentListener() {
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
					char[] cadena = textId.getText().toCharArray();

					if(textId.getText().length() >= 7){
						LocalDate fecha = obtenerFechaNacimiento(cadena);
						if(fecha != null){
							LocalDate hoy = LocalDate.now();
							int edad = Period.between(fecha, hoy).getYears();
							lblEdad.setText("Edad: " + edad);
							if(edad < 18 || edad > 110)
								lblEdad.setForeground(Color.RED);
							else
								lblEdad.setForeground(Color.BLACK);
							if(textId.getText().length() >= 10){
								boolean esHombre = esHombre(cadena);
								if(esHombre){
									lblSexo.setText("Sexo: M");
								}
								else
									lblSexo.setText("Sexo: F");
							}
						}
					}

				}
			});
			textId.setBounds(168, 104, 315, 30);
			textId.setLimite(11);
			textId.putClientProperty("JTextField.placeholderText", "Ingrese su carnet");
		}
		return textId;
	}

	//Métodos CRUD
	public Paciente agregarPaciente(){

		boolean agregado = true;

		String nombre = textNombre.getText();
		String idUsuario = textId.getText();
		String correo = textCorreo.getText();
		String telefono = textTelefono.getText();
		String direccion = textDireccion.getText();

		Paciente u = new Paciente();

		try{
			lblNombre.setForeground(Color.BLACK);
			u.setNombreCompleto(nombre);
		}catch(IllegalArgumentException e){
			lblNombre.setForeground(Color.RED);
			agregado = false;
			textNombre.setText("");
		}
		try{
			lblCi.setForeground(Color.BLACK);
			u.setId(idUsuario);
		}catch(IllegalArgumentException e){
			lblCi.setForeground(Color.RED);
			textId.setText("");
			agregado = false;
		}
		try{
			lblCorreo.setForeground(Color.BLACK);
			u.setCorreo(correo);
		}catch(IllegalArgumentException e){
			lblCorreo.setForeground(Color.RED);
			agregado = false;
			textCorreo.setText("");
		}
		try{
			lblTelfono.setForeground(Color.BLACK);
			u.setNumero(telefono);
		}catch(IllegalArgumentException e){
			lblTelfono.setForeground(Color.RED);
			agregado = false;
			textTelefono.setText("");
		}
		if(sintomasSel == null){
			agregado = false;
			JOptionPane.showMessageDialog(Diagnostico.this, "No ha seleccionado síntomas", "Alerta", JOptionPane.WARNING_MESSAGE);
		}
		if(!agregado){
			u = null;
		}
		else{
			u.setNombreCompleto(nombre);
			u.setId(idUsuario);
			u.setCorreo(correo);
			u.setNumero(telefono);
			u.setDireccion(direccion);
			u.setSintomas(sintomasSel);
		}
		return u;
	}
	public Paciente editarPaciente(Paciente p){

		boolean editado = true;

		String nombre = textNombre.getText();

		try{
			lblNombre.setForeground(Color.BLACK);
			p.setNombreCompleto(nombre);
		}catch(IllegalArgumentException e){
			lblNombre.setForeground(Color.RED);
			editado = false;
			textNombre.setText("");
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
			separator.setBounds(168, 188, 315, 27);
		}
		return separator;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
			separator_1.setForeground(Color.BLACK);
			separator_1.setBounds(168, 134, 315, 27);
		}
		return separator_1;
	}
	private JLabel getLblCorreo() {
		if (lblCorreo == null) {
			lblCorreo = new JLabel("Correo:");
			lblCorreo.setFont(new Font("Sylfaen", Font.PLAIN, 19));
			lblCorreo.setBounds(76, 347, 83, 27);
		}
		return lblCorreo;
	}
	private JSeparator getSeparator_2() {
		if (separator_2 == null) {
			separator_2 = new JSeparator();
			separator_2.setForeground(Color.BLACK);
			separator_2.setBounds(168, 371, 317, 27);
		}
		return separator_2;
	}
	private JTextFieldCarnet getTextTelefono() {
		if (textTelefono == null) {
			textTelefono = new JTextFieldCarnet();
			textTelefono.setBackground(Color.WHITE);
			textTelefono.setLimite(11);
			textTelefono.setFont(new Font("Sylfaen", Font.PLAIN, 17));
			textTelefono.setBorder(null);
			textTelefono.setBounds(168, 278, 317, 30);
		}
		return textTelefono;
	}
	private JLabel getLblTelfono() {
		if (lblTelfono == null) {
			lblTelfono = new JLabel("Tel\u00E9fono:");
			lblTelfono.setFont(new Font("Sylfaen", Font.PLAIN, 19));
			lblTelfono.setBounds(53, 289, 83, 27);
		}
		return lblTelfono;
	}
	private JSeparator getSeparator_3() {
		if (separator_3 == null) {
			separator_3 = new JSeparator();
			separator_3.setForeground(Color.BLACK);
			separator_3.setBounds(168, 308, 317, 27);
		}
		return separator_3;
	}
	private ArrayList<Consultorio> obtenerListaConsultorios(){
		GeneralTree<NodoSalud> tree = Minsap.getInstancia().getArbol();
		List<TreeNode<NodoSalud>> c = tree.getLeaves();
		Iterator<TreeNode<NodoSalud>> it = c.iterator();
		ArrayList<Consultorio> consultorios = new ArrayList<Consultorio>();
		while(it.hasNext()){
			BinaryTreeNode<NodoSalud> nodo = (BinaryTreeNode<NodoSalud>)it.next();
			if(nodo.getInfo() instanceof Consultorio){
				Consultorio con = (Consultorio)nodo.getInfo();
				consultorios.add(con);
			}
		}
		return consultorios;
	}
	private JLabel getLblDireccin() {
		if (lblDireccin == null) {
			lblDireccin = new JLabel("Direcci\u00F3n:");
			lblDireccin.setFont(new Font("Sylfaen", Font.PLAIN, 19));
			lblDireccin.setBounds(47, 228, 96, 27);
		}
		return lblDireccin;
	}
	private JSeparator getSeparator_4() {
		if (separator_4 == null) {
			separator_4 = new JSeparator();
			separator_4.setForeground(Color.BLACK);
			separator_4.setBounds(168, 245, 315, 27);
		}
		return separator_4;
	}

	private JScrollPane getScrollSintomas() {
		if (scrollSintomas == null) {
			// Crear lista de síntomas
			DefaultListModel<String> modeloSintomas = new DefaultListModel<>();
			for(Sintomas s : Sintomas.values()) {
				modeloSintomas.addElement(s.getDescripcion());
			}

			list = new JList<>(modeloSintomas);
			list.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent arg0) {
					sintomasSel = null;
					textPaneSint.setText("");
				}
			});
			list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			list.setFont(new Font("Sylfaen", Font.PLAIN, 17));
			list.setVisibleRowCount(8);

			scrollSintomas = new JScrollPane(list);
			scrollSintomas.setBounds(600, 105, 371, 195);
			scrollSintomas.setFont(new Font("Segoe UI", Font.PLAIN, 25));
			scrollSintomas.setBorder(new LineBorder(new Color(0, 0, 0)));
		}
		return scrollSintomas;
	}
	private JLabel getLblSntomaspresioneCtrl() {
		if (lblSntomaspresioneCtrl == null) {
			lblSntomaspresioneCtrl = new JLabel("S\u00EDntomas:      (Ctrl + Clic para selecci\u00F3n m\u00FAltiple)");
			lblSntomaspresioneCtrl.setFont(new Font("Sylfaen", Font.PLAIN, 19));
			lblSntomaspresioneCtrl.setBounds(600, 53, 406, 27);
		}
		return lblSntomaspresioneCtrl;
	}
	private JLabel getLblDatosPersonales() {
		if (lblDatosPersonales == null) {
			lblDatosPersonales = new JLabel("Datos personales:");
			lblDatosPersonales.setFont(new Font("Sylfaen", Font.PLAIN, 19));
			lblDatosPersonales.setBounds(43, 53, 192, 27);
		}
		return lblDatosPersonales;
	}
	private JButton getBtnSalir() {
		if (btnSalir == null) {
			btnSalir = new JButton("");
			btnSalir.setBorder(null);
			btnSalir.setHorizontalTextPosition(SwingConstants.CENTER);
			btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnSalir.setIcon(new ImageIcon("src/Images/Iconos/otroLogoBorrar50x50.png"));
			btnSalir.setBackground(Color.WHITE);
			btnSalir.setForeground(Color.WHITE);
			btnSalir.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 25));
			btnSalir.setBounds(1021, 0, 50, 50);
			btnSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
		}
		return btnSalir;
	}
	private JButton getBtnGuardar() {
		if (btnGuardar == null) {
			btnGuardar = new JButton("Guardar");
			btnGuardar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					sintomasSel = guardarSintomasSeleccionados();
					String sintomasM = "";
					for(int i = 0; i < sintomasSel.length; i++){
						if (i == sintomasSel.length-1)
							sintomasM += sintomasSel[i].getDescripcion() + ".";
						else
							sintomasM += sintomasSel[i].getDescripcion() + ", ";
					}
					textPaneSint.setText(sintomasM);
				}
			});
			btnGuardar.setBounds(990, 102, 40, 39);
		}
		return btnGuardar;
	}

	public Sintomas[] guardarSintomasSeleccionados(){

		int[] posiciones = list.getSelectedIndices();
		Sintomas[] sintomasSeleccionados = new Sintomas[posiciones.length];
		Sintomas[] sintomas = Sintomas.values();
		for(int i = 0; i < posiciones.length; i++){
			sintomasSeleccionados[i] = sintomas[posiciones[i]];
		}
		return sintomasSeleccionados;
	}
	private JTextPane getTextPaneSint() {
		if (textPaneSint == null) {
			textPaneSint = new JTextPane();
			textPaneSint.setBorder(new LineBorder(new Color(0, 0, 0)));
			textPaneSint.setFont(new Font("Sylfaen", Font.PLAIN, 17));
			textPaneSint.setBounds(600, 320, 371, 130);
		}
		return textPaneSint;
	}
	private JButton getBtnRealizarDiagnstico() {
		if (btnRealizarDiagnstico == null) {
			btnRealizarDiagnstico = new JButton("Realizar diagn\u00F3stico");
			btnRealizarDiagnstico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Paciente p = agregarPaciente();
					if(p != null){
						JOptionPane.showMessageDialog(Diagnostico.this, p.getNombreCompleto() + " agregado con éxito", "Info", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			btnRealizarDiagnstico.setFont(new Font("Sylfaen", Font.PLAIN, 18));
			btnRealizarDiagnstico.setBounds(688, 483, 215, 39);
		}
		return btnRealizarDiagnstico;
	}
	private JTextField getTextDireccion() {
		if (textDireccion == null) {
			textDireccion = new JTextField();
			textDireccion.setFont(new Font("Sylfaen", Font.PLAIN, 17));
			textDireccion.setBorder(null);
			textDireccion.setBounds(168, 215, 315, 30);
			textDireccion.setColumns(10);
		}
		return textDireccion;
	}
	private JTextField getTextCorreo() {
		if (textCorreo == null) {
			textCorreo = new JTextField();
			textCorreo.setFont(new Font("Sylfaen", Font.PLAIN, 17));
			textCorreo.setFocusTraversalKeysEnabled(false);
			textCorreo.setColumns(10);
			textCorreo.setBorder(null);
			textCorreo.setBounds(168, 340, 315, 30);
		}
		return textCorreo;
	}
	private JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBorder(null);
			textField_1.setBounds(170, 319, 315, 30);
		}
		return textField_1;
	}
}
