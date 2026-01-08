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

import Utiles.Colores;
import Utiles.Enfermedades;
import Utiles.Sintomas;

public class ListaEnfermedades extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
	private JLabel lblListaDeEnfermedades;
	private JList list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListaEnfermedades dialog = new ListaEnfermedades();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListaEnfermedades() {
		setBounds(100, 100, 418, 440);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getScrollPane());
		contentPanel.add(getList());
		contentPanel.add(getLblListaDeEnfermedades());
		{
			JPanel buttonPane = new JPanel();
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
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			DefaultListModel<String> modeloEnfermedades = new DefaultListModel<>();
			for(Enfermedades s : Enfermedades.) {
				modeloEnfermedades.addElement(s.getDescripcion());
			}

			list = new JList<>(modeloEnfermedades);
			list.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent arg0) {
					int pos = list.getSelectedIndex();
					
				}
			});
			list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			list.setFont(new Font("Sylfaen", Font.PLAIN, 17));
			list.setVisibleRowCount(8);
			list.setSelectionBackground(Colores.getAzulLogin());
			scrollPane = new JScrollPane(listSint);
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
			lblListaDeEnfermedades.setFont(new Font("Sylfaen", Font.PLAIN, 19));
			lblListaDeEnfermedades.setBounds(46, 24, 223, 28);
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
