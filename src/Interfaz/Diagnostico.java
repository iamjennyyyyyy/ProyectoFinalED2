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

import Auxiliar.Sistema;
import Persona.Medico;
import Persona.Paciente;
import Salud.Consultorio;
import Salud.Minsap;
import Salud.NodoSalud;
import Utiles.Colores;
import Utiles.Enfermedad;
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

import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Diagnostico extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblNombre;
	private JLabel lblEdad;
	private JLabel lblCi;
	private JLabel lblSexo;
	private ArrayList<Sintomas> sintomasSel = new ArrayList<Sintomas>();
	private ArrayList<Enfermedad> enfermedadesSel = new ArrayList<Enfermedad>();
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
	private JLabel lblSntomaspresioneCtrl;
	private JLabel lblDatosPersonales;
	private JButton btnSalir;
	private JTextPane textPaneSint;
	private JList listSint;
	private JList listEnf;
	private JButton btnRegistrar;
	private JTextField textDireccion;
	private JTextField textCorreo;
	private JTextField textField_1;
	private JButton btnReiniciar;
	private JScrollPane scrollEnfermedadess;
	private JButton btnDiagnostico;
	private Paciente pac;
	private JTextPane textPaneEnf;
	private JSpinner spinner;
	private Medico m;


	public static void main(String[] args) {
		try {
			Diagnostico dialog = new Diagnostico(Sistema.getInstancia().getMedicos().get(0));
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Diagnostico(Medico medico) {
		setBounds(296, 164, 1070, 558);
		setUndecorated(true);
		setModal(true);
		getContentPane().setLayout(null);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBounds(0, 0, 1071, 559);
		contentPanel.setLayout(null);
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
		contentPanel.add(getTextPaneSint());
		contentPanel.add(getBtnRegistrar());
		contentPanel.add(getTextDireccion());
		contentPanel.add(getTextCorreo());
		contentPanel.add(getTextField_1());
		contentPanel.add(getBtnReiniciar());
		contentPanel.add(getScrollEnfermedadess());
		contentPanel.add(getBtnDiagnostico());
		contentPanel.add(getTextPaneEnf());
		contentPanel.add(getSpinner());
		m = medico;
	}

	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(58, 170, 85, 27);
			lblNombre.setFont(new Font("Sylfaen", Font.PLAIN, 19));
		}
		return lblNombre;
	}

	private JLabel getLblEdad() {
		if (lblEdad == null) {
			lblEdad = new JLabel("Edad:");
			lblEdad.setBounds(90, 409, 114, 27);
			lblEdad.setFont(new Font("Sylfaen", Font.PLAIN, 19));
		}
		return lblEdad;
	}

	private JLabel getLblCi() {
		if (lblCi == null) {
			lblCi = new JLabel("CI:");
			lblCi.setBounds(108, 114, 40, 27);
			lblCi.setFont(new Font("Sylfaen", Font.PLAIN, 19));
		}
		return lblCi;
	}

	private JLabel getLblSexo() {
		if (lblSexo == null) {
			lblSexo = new JLabel("Sexo:");
			lblSexo.setBounds(90, 463, 114, 27);
			lblSexo.setFont(new Font("Sylfaen", Font.PLAIN, 19));
		}
		return lblSexo;
	}

	private boolean todosLosCamposLlenos(){
		return !textNombre.getText().isEmpty() && !textId.getText().isEmpty() && !textCorreo.getText().isEmpty() && !textTelefono.getText().isEmpty() && !textDireccion.getText().isEmpty() && !sintomasSel.isEmpty();
	}

	private JTextFieldMejorado getTextNombre() {
		if (textNombre == null) {
			textNombre = new JTextFieldMejorado();
			textNombre.setBounds(168, 158, 315, 30);
			textNombre.setBackground(Color.WHITE);
			textNombre.setFont(new Font("Sylfaen", Font.PLAIN, 17));
			textNombre.setBorder(null);
			textNombre.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void insertUpdate(DocumentEvent e) {
					if(todosLosCamposLlenos())
						btnRegistrar.setEnabled(true);
				}

				@Override
				public void removeUpdate(DocumentEvent e) {
					if(todosLosCamposLlenos())
						btnRegistrar.setEnabled(true);
				}

				@Override
				public void changedUpdate(DocumentEvent e) {
					if(todosLosCamposLlenos())
						btnRegistrar.setEnabled(true);
				}
			});
			textNombre.putClientProperty("JTextField.placeholderText", "Ingrese su nombre");
		}
		return textNombre;
	}
	private JTextFieldCarnet getTextId() {
		if (textId == null) {
			textId = new JTextFieldCarnet();
			textId.setBounds(168, 104, 315, 30);
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
					if(todosLosCamposLlenos())
						btnRegistrar.setEnabled(true);
				}
			});
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

		if(textNombre.getText().isEmpty() || textId.getText().isEmpty() || textCorreo.getText().isEmpty() || textTelefono.getText().isEmpty() || textDireccion.getText().isEmpty())
			agregado = false;
		if(agregado){
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
			try{
				lblDireccin.setForeground(Color.BLACK);
				u.setDireccion(direccion);
			}catch(IllegalArgumentException e){
				lblDireccin.setForeground(Color.RED);
				agregado = false;
				textDireccion.setText("");
			}

			if(sintomasSel == null){
				agregado = false;
				JOptionPane.showMessageDialog(Diagnostico.this, "No ha seleccionado síntomas", "Alerta", JOptionPane.WARNING_MESSAGE);
			}
		}
		else
			JOptionPane.showMessageDialog(Diagnostico.this, "Rellene todos los campos, por favor", "Alerta", JOptionPane.WARNING_MESSAGE);
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
			separator.setBounds(168, 188, 315, 27);
			separator.setForeground(Color.BLACK);
		}
		return separator;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
			separator_1.setBounds(168, 134, 315, 27);
			separator_1.setForeground(Color.BLACK);
		}
		return separator_1;
	}
	private JLabel getLblCorreo() {
		if (lblCorreo == null) {
			lblCorreo = new JLabel("Correo:");
			lblCorreo.setBounds(76, 347, 83, 27);
			lblCorreo.setFont(new Font("Sylfaen", Font.PLAIN, 19));
		}
		return lblCorreo;
	}
	private JSeparator getSeparator_2() {
		if (separator_2 == null) {
			separator_2 = new JSeparator();
			separator_2.setBounds(168, 371, 317, 27);
			separator_2.setForeground(Color.BLACK);
		}
		return separator_2;
	}
	private JTextFieldCarnet getTextTelefono() {
		if (textTelefono == null) {
			textTelefono = new JTextFieldCarnet();
			textTelefono.setBounds(168, 278, 317, 30);
			textTelefono.setBackground(Color.WHITE);
			textTelefono.setLimite(11);
			textTelefono.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void insertUpdate(DocumentEvent e) {
					if(todosLosCamposLlenos())
						btnRegistrar.setEnabled(true);
				}

				@Override
				public void removeUpdate(DocumentEvent e) {
					if(todosLosCamposLlenos())
						btnRegistrar.setEnabled(true);
				}

				@Override
				public void changedUpdate(DocumentEvent e) {
					if(todosLosCamposLlenos())
						btnRegistrar.setEnabled(true);
				}
			});
			textTelefono.setFont(new Font("Sylfaen", Font.PLAIN, 17));
			textTelefono.setBorder(null);
		}
		return textTelefono;
	}
	private JLabel getLblTelfono() {
		if (lblTelfono == null) {
			lblTelfono = new JLabel("Tel\u00E9fono:");
			lblTelfono.setBounds(53, 289, 83, 27);
			lblTelfono.setFont(new Font("Sylfaen", Font.PLAIN, 19));
		}
		return lblTelfono;
	}
	private JSeparator getSeparator_3() {
		if (separator_3 == null) {
			separator_3 = new JSeparator();
			separator_3.setBounds(168, 308, 317, 27);
			separator_3.setForeground(Color.BLACK);
		}
		return separator_3;
	}

	private JLabel getLblDireccin() {
		if (lblDireccin == null) {
			lblDireccin = new JLabel("Direcci\u00F3n:");
			lblDireccin.setBounds(47, 228, 96, 27);
			lblDireccin.setFont(new Font("Sylfaen", Font.PLAIN, 19));
		}
		return lblDireccin;
	}
	private JSeparator getSeparator_4() {
		if (separator_4 == null) {
			separator_4 = new JSeparator();
			separator_4.setBounds(168, 245, 315, 27);
			separator_4.setForeground(Color.BLACK);
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

			listSint = new JList<>(modeloSintomas);
			listSint.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent arg0) {

					if (arg0.getValueIsAdjusting()) {
						return; // El usuario aún está seleccionando
					}

					String mensaje = "";
					int[] pos = listSint.getSelectedIndices();
					ArrayList<Sintomas> arr = Sintomas.obtenerSintomasPorIndices(pos);

					sintomasSel.clear();
					for(int i = 0; i < arr.size(); i++){
						if(i != arr.size()-1)
							mensaje += arr.get(i).getDescripcion() + ", ";
						else
							mensaje += arr.get(i).getDescripcion() + ".";
						sintomasSel.add(arr.get(i));
					}
					textPaneSint.setText(mensaje);
					if(todosLosCamposLlenos())
						btnRegistrar.setEnabled(true);
				}
			});
			listSint.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			listSint.setFont(new Font("Sylfaen", Font.PLAIN, 17));
			listSint.setVisibleRowCount(8);
			listSint.setSelectionBackground(Colores.getAzulLogin());
			scrollSintomas = new JScrollPane(listSint);
			scrollSintomas.setBounds(600, 105, 371, 179);
			scrollSintomas.setFont(new Font("Segoe UI", Font.PLAIN, 25));
			scrollSintomas.setBorder(new LineBorder(new Color(0, 0, 0)));
		}
		return scrollSintomas;
	}

	private JLabel getLblSntomaspresioneCtrl() {
		if (lblSntomaspresioneCtrl == null) {
			lblSntomaspresioneCtrl = new JLabel("S\u00EDntomas:      (Ctrl + Clic para selecci\u00F3n m\u00FAltiple)");
			lblSntomaspresioneCtrl.setBounds(600, 53, 430, 27);
			lblSntomaspresioneCtrl.setFont(new Font("Sylfaen", Font.PLAIN, 19));
		}
		return lblSntomaspresioneCtrl;
	}
	private JLabel getLblDatosPersonales() {
		if (lblDatosPersonales == null) {
			lblDatosPersonales = new JLabel("Datos personales:");
			lblDatosPersonales.setBounds(43, 53, 192, 27);
			lblDatosPersonales.setFont(new Font("Sylfaen", Font.PLAIN, 19));
		}
		return lblDatosPersonales;
	}
	private JButton getBtnSalir() {
		if (btnSalir == null) {
			btnSalir = new JButton("");
			btnSalir.setBounds(1021, 0, 50, 50);
			btnSalir.setBorder(null);
			btnSalir.setHorizontalTextPosition(SwingConstants.CENTER);
			btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnSalir.setIcon(new ImageIcon("src/Images/Iconos/otroLogoBorrar50x50.png"));
			btnSalir.setBackground(Color.WHITE);
			btnSalir.setForeground(Color.WHITE);
			btnSalir.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 25));
			btnSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
		}
		return btnSalir;
	}

	private JTextPane getTextPaneSint() {
		if (textPaneSint == null) {
			textPaneSint = new JTextPane();
			textPaneSint.setEditable(false);
			textPaneSint.setBounds(600, 308, 371, 122);
			textPaneSint.setBorder(new LineBorder(new Color(0, 0, 0)));
			textPaneSint.setFont(new Font("Sylfaen", Font.PLAIN, 17));
		}
		return textPaneSint;
	}
	private JButton getBtnRegistrar() {
		if (btnRegistrar == null) {
			btnRegistrar = new JButton("Registrar");
			btnRegistrar.setEnabled(false);
			btnRegistrar.setBounds(706, 470, 186, 39);
			btnRegistrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					pac = agregarPaciente();
					if(pac != null){
						sintomasSel = pac.getSintomas();
						JOptionPane.showMessageDialog(Diagnostico.this, pac.getNombreCompleto() + " agregado con éxito", "Info", JOptionPane.WARNING_MESSAGE);
						//AGREGAR AL REGISTRO DEL CONSULTORIO
						scrollSintomas.setVisible(false);
						scrollEnfermedadess.setVisible(true);
						btnRegistrar.setVisible(false);
						btnDiagnostico.setVisible(true);
						textId.setEnabled(false);
						textNombre.setEnabled(false);
						textDireccion.setEnabled(false);
						textTelefono.setEnabled(false);
						textCorreo.setEnabled(false);
						btnReiniciar.setVisible(false);
						textPaneSint.setVisible(false);
						textPaneEnf.setVisible(true);
						spinner.setVisible(true);
						setLista();
						lblSntomaspresioneCtrl.setText("Enfermedades:   (Ctrl + Clic para selección múltiple)");
					}
				}
			});
			btnRegistrar.setFont(new Font("Sylfaen", Font.PLAIN, 18));
		}
		return btnRegistrar;
	}

	private void setLista(){
		DefaultListModel<String> modeloEnfermedades = new DefaultListModel<>();
		int valor = Integer.parseInt(spinner.getValue().toString());
		for(int i = 0; i < sintomasSel.size(); i++){
			System.out.println(sintomasSel.get(i));
		}
		for(Enfermedad s : Minsap.obtenerEnfermedadesConTantosSintomasComunes(sintomasSel, valor)) {
			modeloEnfermedades.addElement(s.getNombre());
			System.out.println(s.getNombre() + " " + valor);
		}
		listEnf.setModel(modeloEnfermedades);
	}

	private JTextField getTextDireccion() {
		if (textDireccion == null) {
			textDireccion = new JTextField();
			textDireccion.setBounds(168, 215, 315, 30);
			textDireccion.setFont(new Font("Sylfaen", Font.PLAIN, 17));
			textDireccion.setBorder(null);
			textDireccion.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void insertUpdate(DocumentEvent e) {
					if(todosLosCamposLlenos())
						btnRegistrar.setEnabled(true);
				}

				@Override
				public void removeUpdate(DocumentEvent e) {
					if(todosLosCamposLlenos())
						btnRegistrar.setEnabled(true);
				}

				@Override
				public void changedUpdate(DocumentEvent e) {
					if(todosLosCamposLlenos())
						btnRegistrar.setEnabled(true);
				}
			});
			textDireccion.setColumns(10);
		}
		return textDireccion;
	}
	private JTextField getTextCorreo() {
		if (textCorreo == null) {
			textCorreo = new JTextField();
			textCorreo.setBounds(168, 340, 315, 30);
			textCorreo.setFont(new Font("Sylfaen", Font.PLAIN, 17));
			textCorreo.setFocusTraversalKeysEnabled(false);
			textCorreo.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void insertUpdate(DocumentEvent e) {
					if(todosLosCamposLlenos())
						btnRegistrar.setEnabled(true);
				}

				@Override
				public void removeUpdate(DocumentEvent e) {
					if(todosLosCamposLlenos())
						btnRegistrar.setEnabled(true);
				}

				@Override
				public void changedUpdate(DocumentEvent e) {
					if(todosLosCamposLlenos())
						btnRegistrar.setEnabled(true);
				}
			});
			textCorreo.setColumns(10);
			textCorreo.setBorder(null);
		}
		return textCorreo;
	}
	private JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setBounds(170, 319, 315, 30);
			textField_1.setColumns(10);
			textField_1.setBorder(null);
		}
		return textField_1;
	}
	private JButton getBtnReiniciar() {
		if (btnReiniciar == null) {
			btnReiniciar = new JButton("Atras");
			btnReiniciar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					sintomasSel = null;
					textPaneSint.setText("");
					enfermedadesSel = null;
					btnRegistrar.setEnabled(false);
					textId.setText("");
					textNombre.setText("");
					textCorreo.setText("");
					textDireccion.setText("");
					textTelefono.setText("");
				}
			});
			btnReiniciar.setBounds(600, 470, 65, 39);
		}
		return btnReiniciar;
	}
	private JScrollPane getScrollEnfermedadess() {
		if (scrollEnfermedadess == null) {
			DefaultListModel<String> modeloEnfermedades = new DefaultListModel<>();
			for(Enfermedad s : Minsap.getEnfermedadesActuales()) {
				modeloEnfermedades.addElement(s.getNombre());
			}
			listEnf = new JList<>(modeloEnfermedades);
			listEnf.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent arg0) {
					String mensaje = "";
					int[] pos = listEnf.getSelectedIndices();
					ArrayList<Enfermedad> arr = Minsap.obtenerEnfermedadesPorIndices(pos, Minsap.obtenerEnfermedadesConTantosSintomasComunes(sintomasSel, Integer.parseInt(spinner.getValue().toString())));
					for(int i = 0; i < arr.size(); i++){
						if(i != arr.size()-1)
							mensaje += arr.get(i).getNombre() + ", ";
						else
							mensaje += arr.get(i).getNombre() + ".";
						enfermedadesSel.add(arr.get(i));
					}
					textPaneEnf.setText(mensaje);
					btnDiagnostico.setEnabled(true);
				}
			});
			listEnf.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			listEnf.setFont(new Font("Sylfaen", Font.PLAIN, 17));
			listEnf.setVisibleRowCount(8);

			scrollEnfermedadess = new JScrollPane(listEnf);
			scrollEnfermedadess.setBounds(600, 104, 371, 180);
			scrollEnfermedadess.setVisible(false);
			scrollEnfermedadess.setBorder(new LineBorder(new Color(0, 0, 0)));
			scrollEnfermedadess.setFont(new Font("Segoe UI", Font.PLAIN, 25));
			scrollEnfermedadess.setBounds(600, 104, 371, 168);
		}
		return scrollEnfermedadess;
	}
	private JButton getBtnDiagnostico() {
		if (btnDiagnostico == null) {
			btnDiagnostico = new JButton("Diagn\u00F3stico");
			btnDiagnostico.setEnabled(false);
			btnDiagnostico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int[] pos = listEnf.getSelectedIndices();
					enfermedadesSel = Minsap.obtenerEnfermedadesPorIndices(pos, Minsap.obtenerEnfermedadesConTantosSintomasComunes(sintomasSel, Integer.parseInt(spinner.getValue().toString())));
					if(enfermedadesSel != null){
						String enfermedadesDiagnosticadas = "";
						for(int i = 0; i < enfermedadesSel.size(); i++){
							enfermedadesDiagnosticadas += enfermedadesSel.get(i);
							if(i != enfermedadesSel.size()-1)
								enfermedadesDiagnosticadas += ", ";
						}
						pac.setEnfermedades(enfermedadesSel);
						Sistema.getInstancia().agregarEnfermo(m.getConsultorio(), pac);
						JOptionPane.showMessageDialog(Diagnostico.this, pac.getNombreCompleto() + " diagnosticado con " + enfermedadesDiagnosticadas, "Info", JOptionPane.WARNING_MESSAGE);
						dispose();
					}
					else{
						JOptionPane.showMessageDialog(Diagnostico.this, "No ha seleccionado enfermedades", "Alerta", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			btnDiagnostico.setVisible(false);
			btnDiagnostico.setFont(new Font("Sylfaen", Font.PLAIN, 18));
			btnDiagnostico.setBounds(706, 457, 186, 39);
		}
		return btnDiagnostico;
	}
	private JTextPane getTextPaneEnf() {
		if (textPaneEnf == null) {
			textPaneEnf = new JTextPane();
			textPaneEnf.setEditable(false);
			textPaneEnf.setVisible(false);
			textPaneEnf.setFont(new Font("Sylfaen", Font.PLAIN, 17));
			textPaneEnf.setBorder(new LineBorder(new Color(0, 0, 0)));
			textPaneEnf.setBounds(600, 308, 371, 122);
		}
		return textPaneEnf;
	}
	private JSpinner getSpinner() {
		if (spinner == null) {
			spinner = new JSpinner();
			spinner.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					setLista();
				}
			});
			spinner.setVisible(false);
			spinner.setFont(new Font("Sylfaen", Font.PLAIN, 16));
			spinner.setModel(new SpinnerNumberModel(new Integer(1), null, null, new Integer(1)));
			spinner.setToolTipText("Enfermedades con estos s\u00EDntomas en com\u00FAn");
			spinner.setBounds(999, 104, 50, 30);
		}
		return spinner;
	}
}
