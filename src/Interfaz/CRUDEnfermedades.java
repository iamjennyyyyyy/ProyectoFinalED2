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

import javafx.scene.control.ComboBox;

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
import Persona.Paciente;
import Salud.Consultorio;
import Salud.Minsap;
import Salud.NodoSalud;
import Utiles.Colores;
import Utiles.Enfermedad;
import Utiles.Enfermedad.Categoria;
import Utiles.Enfermedad.Gravedad;
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
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SpinnerNumberModel;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class CRUDEnfermedades extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblNombre;
	private ArrayList<Sintomas> sintomasSel = new ArrayList<Sintomas>();
	private JScrollPane scrollSintomas;
	private JTextFieldMejorado textNombre;
	private JSeparator separator;
	private JLabel lblAgente;
	private JSeparator separator_4;
	private JLabel lblSntomaspresioneCtrl;
	private JLabel lblDatosPersonales;
	private JButton btnSalir;
	private DefaultListModel<String> modeloSintomas = new DefaultListModel<>();
	private JTextPane textPaneSint;
	private JList listSint;
	private JButton btnRegistrar;
	private JTextField textAgente;
	private JButton btnReiniciar;
	private Enfermedad enf;
	private JComboBox comboCat;
	private JComboBox comboGrav;
	private JSpinner spinner;
	private JSpinner spinner_1;
	private JComboBox comboBox_2;
	private JLabel lblMximo;
	private JLabel lblMximo_1;
	private JLabel lblDuracin;
	private JLabel lblCategora;
	private JLabel lblGravedad;
	private JButton btnEditar;
	private JComboBox comboBoxNombre;
	private JButton btnGuardar;


	public static void main(String[] args) {
		try {
			CRUDEnfermedades dialog = new CRUDEnfermedades();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CRUDEnfermedades() {
		setBounds(296, 164, 1070, 558);
		setUndecorated(true);
		setModal(true);
		getContentPane().setLayout(null);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBounds(0, 0, 1071, 559);
		contentPanel.setLayout(null);
		contentPanel.add(getLblNombre());
		contentPanel.add(getTextNombre());
		contentPanel.add(getSeparator());
		contentPanel.add(getLblAgente());
		contentPanel.add(getSeparator_4());
		getContentPane().add(contentPanel);
		contentPanel.add(getScrollSintomas());
		contentPanel.add(getLblSntomaspresioneCtrl());
		contentPanel.add(getLblDatosPersonales());
		contentPanel.add(getBtnSalir());
		contentPanel.add(getTextPaneSint());
		contentPanel.add(getBtnRegistrar());
		contentPanel.add(getTextAgente());
		contentPanel.add(getBtnReiniciar());
		contentPanel.add(getComboCat());
		contentPanel.add(getComboGrav());
		contentPanel.add(getSpinner());
		contentPanel.add(getSpinner_1());
		contentPanel.add(getComboBox_2());
		contentPanel.add(getLblMximo());
		contentPanel.add(getLblMximo_1());
		contentPanel.add(getLblDuracin());
		contentPanel.add(getLblCategora());
		contentPanel.add(getLblGravedad());
		contentPanel.add(getBtnEditar());
		contentPanel.add(getComboBoxNombre());
		contentPanel.add(getBtnGuardar());
	}

	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(58, 130, 85, 27);
			lblNombre.setFont(new Font("Sylfaen", Font.PLAIN, 19));
		}
		return lblNombre;
	}

	private boolean todosLosCamposLlenos(){
		return !textNombre.getText().isEmpty() && !textAgente.getText().isEmpty() && !sintomasSel.isEmpty();
	}

	private JTextFieldMejorado getTextNombre() {
		if (textNombre == null) {
			textNombre = new JTextFieldMejorado();
			textNombre.setBounds(168, 118, 346, 30);
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

	//Métodos CRUD
	public Enfermedad agregarEnfermedades(){

		boolean agregado = true;

		String nombre = textNombre.getText();
		String agente = textAgente.getText();
		int posCat = comboCat.getSelectedIndex();
		Categoria[] categorias = Enfermedad.Categoria.values();
		Categoria cat = categorias[posCat];
		int posGrav = comboGrav.getSelectedIndex();
		Gravedad[] gravedad = Enfermedad.Gravedad.values();
		Gravedad grav = gravedad[posGrav];
		int min = Integer.parseInt(spinner.getValue().toString());
		int max = Integer.parseInt(spinner_1.getValue().toString());
		int ind = comboBox_2.getSelectedIndex();
		String duracion = "";
		switch(ind){
		case 0: 
		    duracion += min + "-" + max + " " + "horas";
		    break;
		case 1: 
		    duracion += min + "-" + max + " " + "días";
		    break;
		case 2: 
		    duracion += min + "-" + max + " " + "semanas";
		    break;
		case 3: 
		    duracion += min + "-" + max + " " + "meses";
		    break;
		case 4: 
			duracion = "Crónica";
			break;
		}

		Enfermedad u = new Enfermedad();

		if(textNombre.getText().isEmpty() || textAgente.getText().isEmpty())
			agregado = false;
		if(agregado){
			try{
				lblNombre.setForeground(Color.BLACK);
				u.setNombre(nombre);
			}catch(IllegalArgumentException e){
				lblNombre.setForeground(Color.RED);
				agregado = false;
				textNombre.setText("");
			}
			try{
				lblAgente.setForeground(Color.BLACK);
				u.setAgenteEtiologico(agente);
			}catch(IllegalArgumentException e){
				lblAgente.setForeground(Color.RED);
				agregado = false;
				textAgente.setText("");
			}
			if(min > max || min < 0 || min > 1000 || max <= 0 || max > 1000){
				lblDuracin.setForeground(Color.RED);
				agregado = false;
			}
			if(sintomasSel == null){
				agregado = false;
				JOptionPane.showMessageDialog(CRUDEnfermedades.this, "No ha seleccionado síntomas", "Alerta", JOptionPane.WARNING_MESSAGE);
			}
		}
		else
			JOptionPane.showMessageDialog(CRUDEnfermedades.this, "Rellene todos los campos, por favor", "Alerta", JOptionPane.WARNING_MESSAGE);
		if(!agregado){
			u = null;
		}
		else{
			u.setNombre(nombre);
			u.setAgenteEtiologico(agente);
			u.setSintomasComunes(sintomasSel);
			u.setGravedadTipica(grav);
			u.setCategoria(cat);
			u.setDuracion(duracion);

		}
		return u;
	}

	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setBounds(168, 148, 346, 27);
			separator.setForeground(Color.BLACK);
		}
		return separator;
	}

	private JLabel getLblAgente() {
		if (lblAgente == null) {
			lblAgente = new JLabel("Agente:");
			lblAgente.setBounds(66, 185, 85, 27);
			lblAgente.setFont(new Font("Sylfaen", Font.PLAIN, 19));
		}
		return lblAgente;
	}
	private JSeparator getSeparator_4() {
		if (separator_4 == null) {
			separator_4 = new JSeparator();
			separator_4.setBounds(168, 205, 346, 27);
			separator_4.setForeground(Color.BLACK);
		}
		return separator_4;
	}

	private JScrollPane getScrollSintomas() {
		if (scrollSintomas == null) {
			// Crear lista de síntomas
			modeloSintomas.clear();
			for(Sintomas s : Sintomas.values()) {
				modeloSintomas.addElement(s.getDescripcion());
			}

			listSint = new JList<>(modeloSintomas);
			listSint.addListSelectionListener(new ListSelectionListener() {
			    public void valueChanged(ListSelectionEvent arg0) {
			        
			        if (arg0.getValueIsAdjusting()) {
			            return;
			        }
			        
			        String mensaje = "";
			        int[] pos = listSint.getSelectedIndices();
			        ArrayList<Sintomas> arr = new ArrayList<>();
			        
			        if (!btnGuardar.isVisible()) {
			            arr = Sintomas.obtenerSintomasPorIndices(pos);
			        } else {
			            for (int i = 0; i < pos.length; i++) {
			                String descripcion = modeloSintomas.getElementAt(pos[i]);
			                for (Sintomas s : Sintomas.values()) {
			                    if (s.getDescripcion().equals(descripcion)) {
			                        arr.add(s);
			                    }
			                }
			            }
			        }
			        sintomasSel.clear();
			        for(int i = 0; i < arr.size(); i++){
			            if(i != arr.size()-1)
			                mensaje += arr.get(i).getDescripcion() + ", ";
			            else
			                mensaje += arr.get(i).getDescripcion() + ".";
			            sintomasSel.add(arr.get(i));
			        }
			        textPaneSint.setText(mensaje);
			        
			        if(!btnGuardar.isVisible() && todosLosCamposLlenos())
			            btnRegistrar.setEnabled(true);
			        else if(btnGuardar.isVisible()){
			            btnGuardar.setEnabled(!sintomasSel.isEmpty());
			        }
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
			lblDatosPersonales = new JLabel("Datos:");
			lblDatosPersonales.setBounds(58, 65, 192, 27);
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
			btnRegistrar.setBounds(737, 470, 186, 39);
			btnRegistrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					enf = agregarEnfermedades();
					if(enf != null){
						Minsap.agregarEnfermedad(enf);
						JOptionPane.showMessageDialog(CRUDEnfermedades.this, "Enfermedad " + enf.getNombre() + " agregado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
						reiniciar();
					}
				}
			});
			btnRegistrar.setFont(new Font("Sylfaen", Font.PLAIN, 18));
		}
		return btnRegistrar;
	}
	private JTextField getTextAgente() {
		if (textAgente == null) {
			textAgente = new JTextField();
			textAgente.setBounds(168, 175, 346, 30);
			textAgente.setFont(new Font("Sylfaen", Font.PLAIN, 17));
			textAgente.setBorder(null);
			textAgente.getDocument().addDocumentListener(new DocumentListener() {
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
			textAgente.setColumns(10);
		}
		return textAgente;
	}

	private void reiniciar(){
		sintomasSel.clear();
		
		textPaneSint.setText("");
		textNombre.setVisible(true);
		separator.setVisible(true);
		comboBoxNombre.setVisible(false);
		textNombre.setText("");
		textAgente.setText("");
		spinner.setValue(0);
		spinner_1.setValue(0);
		comboBox_2.setSelectedIndex(0);
		comboCat.setSelectedIndex(0);
		comboGrav.setSelectedIndex(0);
		
		ArrayList<Enfermedad> enfermedades = Minsap.getEnfermedadesActuales();
	    String[] nombres = new String[enfermedades.size()];
	    for(int i = 0; i < nombres.length; i++){
	        nombres[i] = enfermedades.get(i).getNombre();
	    }
	    comboBoxNombre.setModel(new DefaultComboBoxModel(nombres));
		
		btnRegistrar.setEnabled(false);
		btnGuardar.setVisible(false);
		for(Sintomas s : Sintomas.values()) {
			modeloSintomas.addElement(s.getDescripcion());
		}
		listSint.setModel(modeloSintomas);
		listSint.clearSelection();
		lblSntomaspresioneCtrl.setText("Síntomas: (Ctrl + Clic para selección múltiple)");
	}

	private JButton getBtnReiniciar() {
		if (btnReiniciar == null) {
			btnReiniciar = new JButton("Atras");
			btnReiniciar.setFont(new Font("Sylfaen", Font.PLAIN, 18));
			btnReiniciar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					reiniciar();
				}
			});
			btnReiniciar.setBounds(611, 470, 92, 39);
		}
		return btnReiniciar;
	}
	private JComboBox getComboCat() {
		if (comboCat == null) {
			Categoria[] cat = Enfermedad.Categoria.values();
			String[] arr = new String[cat.length];
			for(int i = 0; i < cat.length; i++){
				arr[i] = cat[i].getNombre();
			}
			comboCat = new JComboBox(arr);
			comboCat.setBackground(Color.WHITE);
			comboCat.setFont(new Font("Sylfaen", Font.PLAIN, 17));
			comboCat.setBounds(168, 254, 346, 30);
		}
		return comboCat;
	}
	private JComboBox getComboGrav() {
		if (comboGrav == null) {
			Gravedad[] grav = Enfermedad.Gravedad.values();
			String[] arr = new String[grav.length];
			for(int i = 0; i < grav.length; i++){
				arr[i] = grav[i].getNivel();
			}
			comboGrav = new JComboBox(arr);
			comboGrav.setBackground(Color.WHITE);
			comboGrav.setFont(new Font("Sylfaen", Font.PLAIN, 17));
			comboGrav.setBounds(168, 333, 346, 30);
		}
		return comboGrav;
	}
	private JSpinner getSpinner() {
		if (spinner == null) {
			spinner = new JSpinner();
			spinner.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					lblDuracin.setForeground(Color.BLACK);
				}
			});
			spinner.setFont(new Font("Sylfaen", Font.PLAIN, 15));
			spinner.setBounds(168, 422, 60, 27);
		}
		return spinner;
	}
	private JSpinner getSpinner_1() {
		if (spinner_1 == null) {
			spinner_1 = new JSpinner();
			spinner_1.setFont(new Font("Sylfaen", Font.PLAIN, 15));
			spinner_1.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					lblDuracin.setForeground(Color.BLACK);
				}
			});
			spinner_1.setBounds(303, 422, 59, 27);
		}
		return spinner_1;
	}
	private JComboBox getComboBox_2() {
		if (comboBox_2 == null) {
			comboBox_2 = new JComboBox();
			comboBox_2.setFont(new Font("Sylfaen", Font.PLAIN, 16));
			comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"horas", "d\u00EDas", "semanas", "meses", "cr\u00F3nica"}));
			comboBox_2.setBounds(435, 422, 79, 27);
			comboBox_2.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent arg0) {
					if(comboBox_2.getSelectedItem().equals("Crónica")){
						spinner.setEnabled(false);
						spinner_1.setEnabled(false);
					}
					else{
						spinner.setEnabled(true);
						spinner_1.setEnabled(true);
					}
				}
			});
		}
		return comboBox_2;
	}
	private JLabel getLblMximo() {
		if (lblMximo == null) {
			lblMximo = new JLabel("m\u00EDn");
			lblMximo.setFont(new Font("Sylfaen", Font.PLAIN, 16));
			lblMximo.setBounds(238, 422, 40, 27);
		}
		return lblMximo;
	}
	private JLabel getLblMximo_1() {
		if (lblMximo_1 == null) {
			lblMximo_1 = new JLabel("m\u00E1x");
			lblMximo_1.setFont(new Font("Sylfaen", Font.PLAIN, 16));
			lblMximo_1.setBounds(372, 422, 50, 27);
		}
		return lblMximo_1;
	}
	private JLabel getLblDuracin() {
		if (lblDuracin == null) {
			lblDuracin = new JLabel("Duraci\u00F3n:");
			lblDuracin.setFont(new Font("Sylfaen", Font.PLAIN, 19));
			lblDuracin.setBounds(58, 422, 85, 27);
		}
		return lblDuracin;
	}
	private JLabel getLblCategora() {
		if (lblCategora == null) {
			lblCategora = new JLabel("Categor\u00EDa:");
			lblCategora.setFont(new Font("Sylfaen", Font.PLAIN, 19));
			lblCategora.setBounds(52, 256, 85, 27);
		}
		return lblCategora;
	}
	private JLabel getLblGravedad() {
		if (lblGravedad == null) {
			lblGravedad = new JLabel("Gravedad:");
			lblGravedad.setFont(new Font("Sylfaen", Font.PLAIN, 19));
			lblGravedad.setBounds(52, 336, 85, 27);
		}
		return lblGravedad;
	}
	private JButton getBtnEditar() {
		if (btnEditar == null) {
			btnEditar = new JButton("Editar");
			btnEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					activarEditar();
					cargarDatosEnfermedad();
				}
			});
			btnEditar.setBounds(996, 105, 50, 39);
		}
		return btnEditar;
	}

	public void activarEditar(){

		textNombre.setVisible(false);
		separator.setVisible(false);
		
		comboBoxNombre.setVisible(true);
		
		btnRegistrar.setVisible(false);
		btnGuardar.setVisible(true);
		
		textAgente.setEnabled(false);
		spinner.setEnabled(false);
		spinner_1.setEnabled(false);
		comboBox_2.setEnabled(false);
		comboCat.setEnabled(false);
		comboGrav.setEnabled(false);
		sintomasSel.clear();
		
		listSint.clearSelection();
		listSint.setSelectedIndex(-1);
	}


	private JComboBox getComboBoxNombre() {
		if (comboBoxNombre == null) {
			ArrayList<Enfermedad> enf = Minsap.getEnfermedadesActuales();
			String[] arr = new String[enf.size()];
			for(int i = 0; i < arr.length; i++){
				arr[i] = enf.get(i).getNombre();
			}
			comboBoxNombre = new JComboBox(arr);
			comboBoxNombre.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					cargarDatosEnfermedad();
				}
			});
			comboBoxNombre.setBackground(Color.WHITE);
			comboBoxNombre.setFont(new Font("Sylfaen", Font.PLAIN, 17));
			comboBoxNombre.setVisible(false);			
			comboBoxNombre.setBounds(168, 118, 346, 31);
		}
		return comboBoxNombre;
	}

	public void cargarDatosEnfermedad(){
	    
	    int pos = comboBoxNombre.getSelectedIndex();
	    Enfermedad e = Minsap.getEnfermedadesActuales().get(pos);
	    textAgente.setText(e.getAgenteEtiologico());
	    
	    // Categoría
	    int posCat = -1;
	    Categoria[] cat = Enfermedad.Categoria.values();
	    for(int i = 0; i < cat.length && posCat == -1; i++){
	        if(cat[i].equals(e.getCategoria()))
	            posCat = i;
	    }
	    comboCat.setSelectedIndex(posCat);
	    
	    // Gravedad
	    int posGrav = -1;
	    Gravedad[] grav = Enfermedad.Gravedad.values();
	    for(int i = 0; i < grav.length && posGrav == -1; i++){
	        if(grav[i].equals(e.getGravedadTipica()))
	            posGrav = i;
	    }
	    comboGrav.setSelectedIndex(posGrav);
	    
	    // Duración
	    String duracion = e.getDuracion();
	    if(!duracion.toLowerCase().contains("crón")){
	        String[] partes = duracion.split("-|\\s+");
	        int inicio = Integer.parseInt(partes[0]);
	        int ultimo = Integer.parseInt(partes[1]);
	        String unidad = partes[2];
	        spinner.setValue(inicio);
	        spinner_1.setValue(ultimo);
	        comboBox_2.setSelectedItem(unidad);
	    }
	    else{
	        spinner.setEnabled(false);
	        spinner_1.setEnabled(false);
	        comboBox_2.setSelectedItem("crónica");
	    }
	    
	    // ModoEditar
	    if (btnGuardar.isVisible()) {
	        modeloSintomas.clear();
	        for(Sintomas s : Sintomas.values()) {
	            if(!e.getSintomasComunes().contains(s))
	                modeloSintomas.addElement(s.getDescripcion());
	        }
	        lblSntomaspresioneCtrl.setText("Agregar síntomas: (Ctrl + Clic para selección múltiple)");
	    } //ModoAgregar
	    else {
	        modeloSintomas.clear();
	        for(Sintomas s : Sintomas.values()) {
	            modeloSintomas.addElement(s.getDescripcion());
	        }
	        lblSntomaspresioneCtrl.setText("Síntomas: (Ctrl + Clic para selección múltiple)");
	    }
	    
	    listSint.setModel(modeloSintomas);
	    
	    sintomasSel.clear();
	    textPaneSint.setText("");
	    listSint.clearSelection();
	    
	    if (btnGuardar.isVisible()) {
	        btnGuardar.setEnabled(false);
	    }
	}
	
	public String toStringSintomas(){
		String mensaje = "";
		for(int i = 0; i < sintomasSel.size(); i++){
			if (i == sintomasSel.size()-1)
				mensaje += sintomasSel.get(i).getDescripcion() + ".";
			else
				mensaje += sintomasSel.get(i).getDescripcion() + ", ";
		}
		return mensaje;
	}
	
	private JButton getBtnGuardar() {
		if (btnGuardar == null) {
			btnGuardar = new JButton("Guardar");
			btnGuardar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Minsap.getEnfermedadesActuales().get(comboBoxNombre.getSelectedIndex()).agregarSintomas(sintomasSel);;
					JOptionPane.showMessageDialog(CRUDEnfermedades.this, comboBoxNombre.getSelectedItem().toString() + " editada con éxito.\n" + "Síntomas agregados: "+ toStringSintomas(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
					reiniciar();
				}
			});
			btnGuardar.setEnabled(false);
			btnGuardar.setVisible(false);
			btnGuardar.setFont(new Font("Sylfaen", Font.PLAIN, 18));
			btnGuardar.setBounds(737, 470, 186, 39);
		}
		return btnGuardar;
	}
}
