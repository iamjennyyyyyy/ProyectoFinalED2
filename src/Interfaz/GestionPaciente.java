package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.ListSelectionModel;

import Persona.Paciente;
import Utiles.ModelPaciente;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.Component;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;

import Utiles.MiPersonalizacion;

public class GestionPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnSalir;
	private JList<Paciente> list = new JList<Paciente>();
	private ModelPaciente modelo = new ModelPaciente();
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	private int pos = -1;
	private JTextPane txtpnOperacion;
	private JButton btnAgregar;
	private JButton btnEditar;
	JPopupMenu menuEliminar;
	private JPopupMenu popupMenu;
	private JMenuItem mntmEliminar;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MiPersonalizacion.aplicarTema();
					//					Inicializar.Inicio();
					Login frame = new Login();
					frame.setVisible(true);
					GestionPaciente frame2 = new GestionPaciente();
					frame2.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public GestionPaciente() {
		setBounds(338, 159, 1026, 562);
		setUndecorated(true);
		setModal(true);
		contentPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPanel.setBackground(Color.WHITE);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getTxtpnOperacion());
		contentPanel.add(getBtnSalir());
		contentPanel.add(getLblNewLabel());
		contentPanel.add(getScrollPane());
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				 list.addMouseListener(new MouseAdapter() {
			            @Override
			            public void mousePressed(MouseEvent e) {
			            }
			            
			            @Override
			            public void mouseReleased(MouseEvent e) {
			            }
			        });
			}
		});
		//modelo.setlstUsuarios(Biblioteca.getInstancia().getUsuarios());
		list.setSelectedIndex(0);
		addPopup(list, getPopupMenu());
		contentPanel.add(getBtnAgregar());
		contentPanel.add(getBtnEditar());
	}
        
	//BOTONES
	private JButton getBtnSalir() {
		if (btnSalir == null) {
			btnSalir = new JButton("");
			btnSalir.setBorder(null);
			btnSalir.setHorizontalTextPosition(SwingConstants.CENTER);
			btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnSalir.setIcon(new ImageIcon("src/images/iconos/otroLogoBorrar50x50.png"));
			btnSalir.setBackground(Color.WHITE);
			btnSalir.setForeground(Color.WHITE);
			btnSalir.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 25));
			btnSalir.setBounds(966, 11, 50, 50);
			btnSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
		}
		return btnSalir;
	}

	//OTROS COMPONENTES
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Lista pacientes:");
			lblNewLabel.setBounds(90, 34, 138, 27);
			lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 19));
		}
		return lblNewLabel;
	}

	private JList getList() {
		if (list == null) {
			list = new JList();
			list.setBounds(89, 87, 478, 451);
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return list;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(90, 72, 628, 442);
			list.setSelectedIndices(new int[] {1});
			list.setToolTipText("Lista de pacientes actualmente registrados");
			list.setBorder(new LineBorder(new Color(0, 0, 0)));
			list.setFont(new Font("SansSerif", Font.PLAIN, 17));
			list.setModel(modelo);
			list.setSelectedIndex(0);
			scrollPane.setViewportView(list);
		}
		return scrollPane;
	}

	public void eliminarPaciente() {

		if(!list.isSelectionEmpty()){

			Paciente u = list.getSelectedValue();

			if(u != null){
				modelo.removePaciente(pos);

				JOptionPane.showMessageDialog(null, "Paciente " + u.getNombreCompleto() + " ha sido eliminado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				if(pos > 0){
					list.setSelectedIndex(pos - 1);
				}
				else{
					list.setSelectedIndex(pos);
				}
				pos = -1;
			}
		}
	}
	
	private JTextPane getTxtpnOperacion() {
		if (txtpnOperacion == null) {
			txtpnOperacion = new JTextPane();
			txtpnOperacion.setDisabledTextColor(Color.BLACK);
			txtpnOperacion.setFont(new Font("Sylfaen", Font.PLAIN, 19));
			txtpnOperacion.setDisabledTextColor(Color.BLACK);
			txtpnOperacion.setEnabled(false);
			txtpnOperacion.setBackground(Color.WHITE);
			txtpnOperacion.setVisible(false);
			txtpnOperacion.setBounds(633, 56, 85, 32);
		}
		return txtpnOperacion;
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
	private JButton getBtnAgregar() {
		if (btnAgregar == null) {
			btnAgregar = new JButton("Agregar");
			btnAgregar.setBounds(776, 78, 75, 63);
		}
		return btnAgregar;
	}
	private JButton getBtnEditar() {
		if (btnEditar == null) {
			btnEditar = new JButton("Editar");
			btnEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btnEditar.setBounds(896, 78, 75, 63);
		}
		return btnEditar;
	}
	private JPopupMenu getPopupMenu() {
		if (popupMenu == null) {
			popupMenu = new JPopupMenu();
			popupMenu.add(getMntmEliminar());
		}
		return popupMenu;
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	private JMenuItem getMntmEliminar() {
		if (mntmEliminar == null) {
			mntmEliminar = new JMenuItem("Eliminar");
		}
		return mntmEliminar;
	}
}
