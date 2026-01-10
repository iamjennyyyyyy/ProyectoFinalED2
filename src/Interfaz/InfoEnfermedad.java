package Interfaz;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Utiles.Colores;
import Utiles.Enfermedad;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;

import java.awt.SystemColor;
import java.awt.CardLayout;

import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.JSeparator;

public class InfoEnfermedad extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel labelImagen;
	private JTextPane txtpnTtulo;
	private JTextPane textPaneId;
	private JTextPane txtpnMateria;
	private JTextPane txtpnPginas;
	private JTextPane txtpnEjemplares;
	private JTextPane txtPaneDuracion;
	private JTextPane textPaneSintomas;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JSeparator separator_3;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public InfoEnfermedad(Enfermedad e) {
		setTitle("Informaci\u00F3n Enfermedad");
		setModal(true);
		setBounds(350, 190, 799, 494);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Colores.getAzulLogin());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getLabelImagen());
		labelImagen.setIcon(e.getImage());
		contentPanel.add(getTxtpnTtulo());
		contentPanel.add(getTextPaneId());
		contentPanel.add(getTxtpnMateria());
		contentPanel.add(getTextPane_2_1());
		contentPanel.add(getTextPane_3_1());
		contentPanel.add(getTxtPaneDuracion());
		contentPanel.add(getTextPaneSintomas());
		txtpnTtulo.setText("Enfermedad: " + e.getNombre());
		txtpnMateria.setText("Categoría: " + e.getCategoria().getNombre());
		txtpnPginas.setText("Gravedad: " + e.getGravedadTipica().getValor());
		txtpnEjemplares.setText("Agente: " + e.getAgenteEtiologico());
		txtPaneDuracion.setText("Duracion: " + e.getDuracion());
		contentPanel.add(getLabel_1());
		contentPanel.add(getLabel_2());
		contentPanel.add(getLabel_3());
		contentPanel.add(getLabel_4());
		contentPanel.add(getLabel_5());
		contentPanel.add(getLabel_6());
		contentPanel.add(getLabel_7());
		contentPanel.add(getSeparator());
		contentPanel.add(getSeparator_1());
		contentPanel.add(getSeparator_2());
		contentPanel.add(getSeparator_3());
	}
	private JLabel getLabelImagen() {
		if (labelImagen == null) {
			labelImagen = new JLabel("");
			labelImagen.setBorder(new LineBorder(Colores.getAzulMedio()));
			labelImagen.setBounds(463, 29, 294, 401);
		}
		return labelImagen;
	}
	private JTextPane getTxtpnTtulo() {
		if (txtpnTtulo == null) {
			txtpnTtulo = new JTextPane();
			txtpnTtulo.setText("T\u00EDtulo:");
			txtpnTtulo.setFocusable(false);
			txtpnTtulo.setBackground(Colores.getAzulLogin());
			txtpnTtulo.setEditable(false);
			txtpnTtulo.setFont(new Font("Sylfaen", Font.PLAIN, 20));
			txtpnTtulo.setBounds(50, 41, 379, 37);
		}
		return txtpnTtulo;
	}
	private JTextPane getTextPaneId() {
		if (textPaneId == null) {
			textPaneId = new JTextPane();
			textPaneId.setText("Identificador:");
			textPaneId.setFont(new Font("Sylfaen", Font.PLAIN, 20));
			textPaneId.setFocusable(false);
			textPaneId.setEditable(false);
			textPaneId.setBackground(Colores.getAzulLogin());
			textPaneId.setBounds(50, 100, 379, 37);
		}
		return textPaneId;
	}
	private JTextPane getTxtpnMateria() {
		if (txtpnMateria == null) {
			txtpnMateria = new JTextPane();
			txtpnMateria.setText("Materia:");
			txtpnMateria.setFont(new Font("Sylfaen", Font.PLAIN, 20));
			txtpnMateria.setFocusable(false);
			txtpnMateria.setEditable(false);
			txtpnMateria.setBackground(Colores.getAzulLogin());
			txtpnMateria.setBounds(50, 162, 379, 37);
		}
		return txtpnMateria;
	}
	private JTextPane getTextPane_2_1() {
		if (txtpnPginas == null) {
			txtpnPginas = new JTextPane();
			txtpnPginas.setText("P\u00E1ginas:");
			txtpnPginas.setFont(new Font("Sylfaen", Font.PLAIN, 20));
			txtpnPginas.setFocusable(false);
			txtpnPginas.setEditable(false);
			txtpnPginas.setBackground(Colores.getAzulLogin());
			txtpnPginas.setBounds(50, 222, 131, 37);
		}
		return txtpnPginas;
	}
	private JTextPane getTextPane_3_1() {
		if (txtpnEjemplares == null) {
			txtpnEjemplares = new JTextPane();
			txtpnEjemplares.setText("Ejemplares:");
			txtpnEjemplares.setFont(new Font("Sylfaen", Font.PLAIN, 20));
			txtpnEjemplares.setFocusable(false);
			txtpnEjemplares.setEditable(false);
			txtpnEjemplares.setBackground(Colores.getAzulLogin());
			txtpnEjemplares.setBounds(50, 280, 379, 37);
		}
		return txtpnEjemplares;
	}
	private JTextPane getTxtPaneDuracion() {
		if (txtPaneDuracion == null) {
			txtPaneDuracion = new JTextPane();
			txtPaneDuracion.setFont(new Font("Sylfaen", Font.PLAIN, 20));
			txtPaneDuracion.setFocusable(false);
			txtPaneDuracion.setEditable(false);
			txtPaneDuracion.setBackground(Colores.getAzulLogin());
			txtPaneDuracion.setBounds(191, 222, 238, 37);
		}
		return txtPaneDuracion;
	}
	private JTextPane getTextPaneSintomas() {
		if (textPaneSintomas == null) {
			textPaneSintomas = new JTextPane();
			textPaneSintomas.setFont(new Font("Sylfaen", Font.PLAIN, 18));
			textPaneSintomas.setFocusable(false);
			textPaneSintomas.setEditable(false);
			textPaneSintomas.setBackground(Colores.getAzulLogin());
			textPaneSintomas.setBounds(50, 343, 379, 87);
		}
		return textPaneSintomas;
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("");
			label_1.setBounds(10, 66, 30, 30);
		}
		return label_1;
	}
	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("");
			label_2.setBounds(10, 114, 30, 30);
		}
		return label_2;
	}
	private JLabel getLabel_3() {
		if (label_3 == null) {
			label_3 = new JLabel("");
			label_3.setBounds(10, 162, 30, 30);
		}
		return label_3;
	}
	private JLabel getLabel_4() {
		if (label_4 == null) {
			label_4 = new JLabel("");
			label_4.setBounds(10, 210, 30, 30);
		}
		return label_4;
	}
	private JLabel getLabel_5() {
		if (label_5 == null) {
			label_5 = new JLabel("");
			label_5.setBounds(236, 210, 30, 30);
		}
		return label_5;
	}
	private JLabel getLabel_6() {
		if (label_6 == null) {
			label_6 = new JLabel("");
			label_6.setBounds(10, 258, 30, 30);
		}
		return label_6;
	}
	private JLabel getLabel_7() {
		if (label_7 == null) {
			label_7 = new JLabel("");
			label_7.setBounds(10, 306, 30, 30);
		}
		return label_7;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setBorder(new LineBorder(Colores.getAzulMedio()));
			separator.setBounds(169, 42, 260, 37);
		}
		return separator;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
			separator_1.setBorder(new LineBorder(Colores.getAzulMedio()));
			separator_1.setBounds(178, 101, 251, 37);
		}
		return separator_1;
	}
	private JSeparator getSeparator_2() {
		if (separator_2 == null) {
			separator_2 = new JSeparator();
			separator_2.setBorder(new LineBorder(Colores.getAzulMedio()));
			separator_2.setBounds(135, 163, 294, 37);
		}
		return separator_2;
	}
	private JSeparator getSeparator_3() {
		if (separator_3 == null) {
			separator_3 = new JSeparator();
			separator_3.setBorder(new LineBorder(Colores.getAzulMedio()));
			separator_3.setBounds(113, 281, 316, 37);
		}
		return separator_3;
	}
}
