package Interfaz;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.LocalDate;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import javax.swing.JButton;
import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Utiles.Colores;

public class PrincipalAdmin extends JFrame {

	private JPanel contentPane;
	private JPanel panelLateral;
	private JButton btnSesion;
	private JButton btnDiagnostico;
	private JButton btnEnfermedades;
	private JButton btnReporte;
	private JLabel lblGestin;
	private JLabel lblReportes;
	private JLabel lblAcercaDe;
	private JPanel panelSup;
	private JMenu mnNewMenu;
	private JMenuItem mntmUsuario_2;
	private JMenuItem mntmTrabajador_2;
	private JMenuItem mntmPublicacin_1;
	private JMenuItem mntmPrstamo_1;
	private JLabel lblNewLabel_2;
	private JPanel panelInicio;
	private JLabel labelFecha;
	private JLabel labelDia;
	private JLabel labelRuta;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_7;
	private JPopupMenu popupMenuSesion;
	private JMenuItem mntmCerrarSesin;
	private JMenuItem mntmSalir;
	private JLabel lblSistemaDeVigilancia;
	private JLabel label;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */

	public PrincipalAdmin() {
		setBackground(new Color(245, 245, 220));
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/icons8-literature-50.png"));
		setTitle("SAE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);
		contentPane.add(getPanelLateral());
		contentPane.add(getPanelSup());
		contentPane.add(getPanelInicio());
		setBounds(0, 0, 1382, 747);
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPanelLateral());
		contentPane.add(getPanelSup());
		contentPane.add(getMnNewMenu());
	}
	private JPanel getPanelLateral() {
		if (panelLateral == null) {
			panelLateral = new JPanel();
			panelLateral.setBorder(new LineBorder(new Color(0, 0, 0)));
			panelLateral.setBounds(0, 0, 295, 699);
			panelLateral.setBackground(Colores.getAzulMedio());
			panelLateral.setLayout(null);
			panelLateral.add(getLabel());
			panelLateral.add(getLblAcercaDe());
			panelLateral.add(getLblReportes());
			panelLateral.add(getLblGestin());
			panelLateral.add(getLabel_4());
			panelLateral.add(getLabel_7());
			panelLateral.add(getLabel_5());
			panelLateral.add(getLabel_3());
			panelLateral.add(getBtnSesion());
			panelLateral.add(getBtnDiagnostico());
			panelLateral.add(getBtnEnfermedades());
			panelLateral.add(getBtnReporte());
			panelLateral.add(getLblNewLabel_2());
		}
		return panelLateral;
	}

	private JButton getBtnSesion() {
		if (btnSesion == null) {
			btnSesion = new JButton("");
			btnSesion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int x = btnSesion.getWidth() + 1;
				    int y = 0;
					popupMenuSesion.show(btnSesion, x, y);
				}
			});
			btnSesion.setBackground(Colores.getAzulMedio());
			btnSesion.setBorder(null);
			btnSesion.setFont(new Font("SansSerif", Font.PLAIN, 20));
			btnSesion.setBounds(0, 215, 294, 70);
			addPopup(btnSesion, getPopupMenuSesion());
		}
		return btnSesion;
	}
	private JButton getBtnDiagnostico() {
		if (btnDiagnostico == null) {
			btnDiagnostico = new JButton("");
			btnDiagnostico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Diagnostico d = new Diagnostico();
					d.setVisible(true);
				}
			});
			btnDiagnostico.setFont(new Font("SansSerif", Font.PLAIN, 20));
			btnDiagnostico.setBorder(null);
			btnDiagnostico.setBackground(Colores.getAzulMedio());
			btnDiagnostico.setBounds(0, 283, 294, 70);
		}
		return btnDiagnostico;
	}
	private JButton getBtnEnfermedades() {
		if (btnEnfermedades == null) {
			btnEnfermedades = new JButton("");
			btnEnfermedades.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int x = btnEnfermedades.getWidth() + 1;
				    int y = 0;
					//popupMenuReportes.show(btnReportes, x, y);
				}
			});
			btnEnfermedades.setFont(new Font("SansSerif", Font.PLAIN, 20));
			btnEnfermedades.setBorder(null);
			btnEnfermedades.setBackground(Colores.getAzulMedio());
			btnEnfermedades.setBounds(0, 350, 294, 70);
		}
		return btnEnfermedades;
	}
	private JButton getBtnReporte() {
		if (btnReporte == null) {
			btnReporte = new JButton("");
			btnReporte.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					labelRuta.setText("Acerca de Nosotros");
//					lblAcercaDe.setForeground(Colores.getCruds());
//					AcercaDe a = new AcercaDe();
					//a.setVisible(true);
					labelRuta.setText("");
					lblAcercaDe.setForeground(Color.BLACK);
				}
			});
			btnReporte.setFont(new Font("SansSerif", Font.PLAIN, 20));
			btnReporte.setBorder(null);
			btnReporte.setBackground(Colores.getAzulMedio());
			btnReporte.setBounds(0, 419, 294, 70);
		}
		return btnReporte;
	}
	private JLabel getLblGestin() {
		if (lblGestin == null) {
			lblGestin = new JLabel("Diagnóstico");
			lblGestin.setForeground(Color.BLACK);
			lblGestin.setFont(new Font("Sylfaen", Font.PLAIN, 30));
			lblGestin.setBounds(70, 300, 159, 40);
		}
		return lblGestin;
	}
	private JLabel getLblReportes() {
		if (lblReportes == null) {
			lblReportes = new JLabel("Reportes");
			lblReportes.setForeground(Color.BLACK);
			lblReportes.setFont(new Font("Sylfaen", Font.PLAIN, 30));
			lblReportes.setBounds(70, 436, 112, 40);
		}
		return lblReportes;
	}
	private JLabel getLblAcercaDe() {
		if (lblAcercaDe == null) {
			lblAcercaDe = new JLabel("Enfermedades");
			lblAcercaDe.setForeground(Color.BLACK);
			lblAcercaDe.setFont(new Font("Sylfaen", Font.PLAIN, 30));
			lblAcercaDe.setBounds(70, 364, 186, 40);
		}
		return lblAcercaDe;
	}
	private JPanel getPanelSup() {
		if (panelSup == null) {
			panelSup = new JPanel();
			panelSup.setBorder(new LineBorder(new Color(0, 0, 0)));
			panelSup.setBackground(Colores.getAzulLogin());
			panelSup.setBounds(294, 0, 1071, 138);
			panelSup.setLayout(null);
			panelSup.add(getLabelFecha());
			panelSup.add(getLabelDia());
			panelSup.add(getLabelRuta());
			panelSup.add(getLblSistemaDeVigilancia());
		}
		return panelSup;
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
	private JMenu getMnNewMenu() {
		if (mnNewMenu == null) {
			mnNewMenu = new JMenu("New menu");
			mnNewMenu.setBounds(0, 238, 339, 70);
			mnNewMenu.add(getMntmUsuario_2());
			mnNewMenu.add(getMntmTrabajador_2());
			mnNewMenu.add(getMntmPublicacin_1());
			mnNewMenu.add(getMntmPrstamo_1());
		}
		return mnNewMenu;
	}
	private JMenuItem getMntmUsuario_2() {
		if (mntmUsuario_2 == null) {
			mntmUsuario_2 = new JMenuItem("Usuario");
		}
		return mntmUsuario_2;
	}
	private JMenuItem getMntmTrabajador_2() {
		if (mntmTrabajador_2 == null) {
			mntmTrabajador_2 = new JMenuItem("Trabajador");
		}
		return mntmTrabajador_2;
	}
	private JMenuItem getMntmPublicacin_1() {
		if (mntmPublicacin_1 == null) {
			mntmPublicacin_1 = new JMenuItem("Publicaci\u00F3n");
		}
		return mntmPublicacin_1;
	}
	private JMenuItem getMntmPrstamo_1() {
		if (mntmPrstamo_1 == null) {
			mntmPrstamo_1 = new JMenuItem("Pr\u00E9stamo");
		}
		return mntmPrstamo_1;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("");
			lblNewLabel_2.setVisible(false);
			//lblNewLabel_2.setBackground(Colores.getBeigetabla());
			lblNewLabel_2.setIcon(new ImageIcon("src/images/iconos/l2.jpg"));
			lblNewLabel_2.setBounds(29, 97, 150, 95);
		}
		return lblNewLabel_2;
	}
	private JPanel getPanelInicio() {
		if (panelInicio == null) {
			panelInicio = new JPanel();
			panelInicio.setBackground(Color.WHITE);
			panelInicio.setBounds(295, 138, 1071, 559);
			panelInicio.setLayout(null);
		}
		return panelInicio;
	}

	private JLabel getLabelFecha() {
		if (labelFecha == null) {
			labelFecha = new JLabel();
			String dia = obtenerDiaEnEspanol(LocalDate.now().getDayOfWeek().toString());
			labelFecha.setText("Hoy es " + dia);
			labelFecha.setFont(new Font("Sylfaen", Font.PLAIN, 27));
			labelFecha.setBounds(682, 9, 317, 57);
		}
		return labelFecha;
	}

	public String obtenerDiaEnEspanol(String dia){
		String diaa = "";

		if(dia.equals("MONDAY"))
			diaa = "lunes";
		else if(dia.equals("TUESDAY"))
			diaa = "martes";
		else if(dia.equals("WEDNESDAY"))
			diaa = "mi�rcoles";
		else if(dia.equals("THURSDAY"))
			diaa = "jueves";
		else if(dia.equals("FRIDAY"))
			diaa = "viernes";
		else if(dia.equals("SATURDAY"))
			diaa = "s�bado";
		else if(dia.equals("SUNDAY"))
			diaa = "domingo";
		return diaa;
	}

	public String obtenerMesEnEspanol(String mess){
		String mes = "";

		if(mess.equals("JANUARY"))
			mes = "enero";
		if(mess.equals("FEBRUARY"))
			mes = "febrero";
		if(mess.equals("MARCH"))
			mes = "marzo";
		if(mess.equals("APRIL"))
			mes = "abril";
		if(mess.equals("MAY"))
			mes = "mayo";
		if(mess.equals("JUNE"))
			mes = "junio";
		if(mess.equals("JULY"))
			mes = "julio";
		if(mess.equals("AUGUST"))
			mes = "agosto";
		if(mess.equals("SEPTEMBER"))
			mes = "septiembre";
		if(mess.equals("OCTOBER"))
			mes = "octubre";
		if(mess.equals("NOVEMBER"))
			mes = "noviembre";
		if(mess.equals("DECEMBER"))
			mes = "diciembre";
		return mes;
	}


	private JLabel getLabelDia() {
		if (labelDia == null) {
			labelDia = new JLabel();
			String mes = obtenerMesEnEspanol(LocalDate.now().getMonth().toString());
			labelDia.setText(LocalDate.now().getDayOfMonth() + " de " + mes + " del " + LocalDate.now().getYear());
			labelDia.setFont(new Font("Segoe UI", Font.ITALIC, 27));
			labelDia.setBounds(717, 55, 301, 57);
		}
		return labelDia;
	}
	private JLabel getLabelRuta() {
		if (labelRuta == null) {
			labelRuta = new JLabel();
			labelRuta.setFont(new Font("Sylfaen", Font.PLAIN, 27));
			labelRuta.setBounds(30, 85, 453, 42);
			labelRuta.setForeground(Color.BLACK);
		}
		return labelRuta;
	}
	private JLabel getLabel_3() {
		if (label_3 == null) {
			label_3 = new JLabel("");
			label_3.setIcon(new ImageIcon("src/Images/Iconos/Home.png"));
			label_3.setBounds(10, 231, 38, 38);
		}
		return label_3;
	}
	private JLabel getLabel_4() {
		if (label_4 == null) {
			label_4 = new JLabel("");
			label_4.setIcon(new ImageIcon("src/Images/Iconos/Edit.png"));
			label_4.setBounds(10, 298, 38, 38);
		}
		return label_4;
	}
	private JLabel getLabel_5() {
		if (label_5 == null) {
			label_5 = new JLabel("");
			label_5.setIcon(new ImageIcon("src/Images/Iconos/book.png"));
			label_5.setBounds(10, 366, 38, 38);
		}
		return label_5;
	}
	private JLabel getLabel_7() {
		if (label_7 == null) {
			label_7 = new JLabel("");
			label_7.setIcon(new ImageIcon("src/Images/Iconos/About.png"));
			label_7.setBounds(10, 438, 38, 38);
		}
		return label_7;
	}
	private JPopupMenu getPopupMenuSesion() {
		if (popupMenuSesion == null) {
			popupMenuSesion = new JPopupMenu();
			popupMenuSesion.setFont(new Font("Segoe UI", Font.PLAIN, 17));
			popupMenuSesion.add(getMntmCerrarSesin());
			popupMenuSesion.add(getMntmSalir());
		}
		return popupMenuSesion;
	}
	private JMenuItem getMntmCerrarSesin() {
		if (mntmCerrarSesin == null) {
			mntmCerrarSesin = new JMenuItem("Cerrar sesi\u00F3n");
			mntmCerrarSesin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
					Login g = new Login();
					g.setVisible(true);
				}
			});
			mntmCerrarSesin.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		}
		return mntmCerrarSesin;
	}
	private JMenuItem getMntmSalir() {
		if (mntmSalir == null) {
			mntmSalir = new JMenuItem("Salir");
			mntmSalir.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			mntmSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
		}
		return mntmSalir;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private JLabel getLblSistemaDeVigilancia() {
		if (lblSistemaDeVigilancia == null) {
			lblSistemaDeVigilancia = new JLabel("Consultorio #");
			lblSistemaDeVigilancia.setFont(new Font("Segoe UI", Font.PLAIN, 32));
			lblSistemaDeVigilancia.setBounds(26, 9, 367, 58);
		}
		return lblSistemaDeVigilancia;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("Sesión");
			label.setForeground(Color.BLACK);
			label.setFont(new Font("Sylfaen", Font.PLAIN, 30));
			label.setBounds(70, 231, 112, 40);
		}
		return label;
	}
}
