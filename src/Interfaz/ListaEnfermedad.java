package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JList;

import Salud.Minsap;
import Utiles.Colores;
import Utiles.Enfermedad;
import Utiles.Sintomas;

public class ListaEnfermedad extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
	private JLabel lblListaDeEnfermedades;
	private JList list;
	private Enfermedad enf;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public ListaEnfermedad(int e) {
		setBounds(100, 100, 418, 440);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getScrollPane(e));
		contentPanel.add(getList());
		contentPanel.add(getLblListaDeEnfermedades());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	private JScrollPane getScrollPane(final int pos) {
		if (scrollPane == null) {
			DefaultListModel<String> modeloEnfermedades = new DefaultListModel<>();
			for(Enfermedad s : Minsap.getEnfermedadesActuales()) {
				modeloEnfermedades.addElement(s.getNombre());
			}

			list = new JList<>(modeloEnfermedades);
			list.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent arg0) {
					//pos = list.getSelectedIndex();
				}
			});
			list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			list.setFont(new Font("Sylfaen", Font.PLAIN, 17));
			list.setVisibleRowCount(8);
			list.setSelectionBackground(Colores.getAzulLogin());
			scrollPane = new JScrollPane(list);
			scrollPane.setBounds(46, 238, 266, -150);
			scrollPane.setBounds(600, 105, 371, 179);
			scrollPane.setFont(new Font("Segoe UI", Font.PLAIN, 25));
			scrollPane.setBorder(new LineBorder(new Color(0, 0, 0)));
			scrollPane.setBounds(46, 65, 313, 292);
		}
		return scrollPane;
	}
	private JLabel getLblListaDeEnfermedades() {
		if (lblListaDeEnfermedades == null) {
			lblListaDeEnfermedades = new JLabel("Lista de enfermedades:");
			lblListaDeEnfermedades.setBounds(46, 24, 223, 28);
			lblListaDeEnfermedades.setFont(new Font("Sylfaen", Font.PLAIN, 19));
		}
		return lblListaDeEnfermedades;
	}
	private JList getList() {
		if (list == null) {
			list = new JList();
			list.setBounds(0, 0, 1, 1);
		}
		return list;
	}
}
