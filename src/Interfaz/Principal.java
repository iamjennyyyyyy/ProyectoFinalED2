package Interfaz;
import java.awt.EventQueue;
import java.io.InputStream;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.CardLayout;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;

import sun.applet.Main;

import java.awt.SystemColor;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.ComponentOrientation;

import javax.swing.JSeparator;
import javax.swing.border.EtchedBorder;
import javax.swing.border.EmptyBorder;

import Auxiliar.Sistema;
import Persona.Medico;
import Utiles.Colores;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JPanel panelLateral;
	private JButton btnSesion;
	private JButton btnDiagnostico;
	private JButton btnEnfermedades;
	private JButton btnReporte;
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
	private JPopupMenu popupMenuSesion;
	private JMenuItem mntmCerrarSesin;
	private JMenuItem mntmSalir;
	private JLabel lblConsul;
	private JButton btnNewButton;
	private Medico medico;
	private JLabel lblBienvenido;
	private Sistema s = Sistema.getInstancia();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */

	public Principal(Medico m) {
		setBackground(new Color(245, 245, 220));
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/icons8-literature-50.png"));
		setTitle("SAE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		medico = m;
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
			btnSesion = new JButton("   Sesión", new ImageIcon("src/Images/Iconos/Home.png"));
			btnSesion.setFocusTraversalKeysEnabled(false);
			btnSesion.setFocusable(false);
			btnSesion.setRolloverEnabled(false);
			btnSesion.setHorizontalAlignment(SwingConstants.LEFT);
			btnSesion.setFocusCycleRoot(true);
			btnSesion.setForeground(Color.BLACK);
			btnSesion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int x = btnSesion.getWidth() + 1;
				    int y = 0;
					popupMenuSesion.show(btnSesion, x, y);
				}
			});
			btnSesion.setBackground(Colores.getAzulMedio());
			btnSesion.setBorder(null);
			btnSesion.setFont(new Font("Sylfaen", Font.PLAIN, 30));
			btnSesion.setBounds(0, 215, 294, 70);
			addPopup(btnSesion, getPopupMenuSesion());
		}
		return btnSesion;
	}
	private JButton getBtnDiagnostico() {
		if (btnDiagnostico == null) {
			btnDiagnostico = new JButton("   Diagnóstico", new ImageIcon("src/Images/Iconos/Edit.png"));
			btnDiagnostico.setHorizontalAlignment(SwingConstants.LEFT);
			btnDiagnostico.setFocusable(false);
			btnDiagnostico.setFocusTraversalKeysEnabled(false);
			btnDiagnostico.setForeground(Color.BLACK);
			btnDiagnostico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Diagnostico d = new Diagnostico(medico);
					d.setVisible(true);
				}
			});
			btnDiagnostico.setFont(new Font("Sylfaen", Font.PLAIN, 30));
			btnDiagnostico.setBorder(null);
			btnDiagnostico.setBackground(Colores.getAzulMedio());
			btnDiagnostico.setBounds(0, 283, 294, 70);
		}
		return btnDiagnostico;
	}
	private JButton getBtnEnfermedades() {
		if (btnEnfermedades == null) {
			btnEnfermedades = new JButton("   Enfermedades", new ImageIcon("src/Images/Iconos/book.png"));
			btnEnfermedades.setHorizontalAlignment(SwingConstants.LEFT);
			btnEnfermedades.setFocusTraversalKeysEnabled(false);
			btnEnfermedades.setFocusable(false);
			btnEnfermedades.setForeground(Color.BLACK);
			btnEnfermedades.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					InformacionEnfermedades c = new InformacionEnfermedades();
					c.setVisible(true);
				}
			});
			btnEnfermedades.setFont(new Font("Sylfaen", Font.PLAIN, 30));
			btnEnfermedades.setBorder(null);
			btnEnfermedades.setBackground(Colores.getAzulMedio());
			btnEnfermedades.setBounds(0, 350, 294, 70);
		}
		return btnEnfermedades;
	}
	private JButton getBtnReporte() {
		if (btnReporte == null) {
			btnReporte = new JButton("   Reportes", new ImageIcon("src/Images/Iconos/About.png"));
			btnReporte.setHorizontalAlignment(SwingConstants.LEFT);
			btnReporte.setFocusTraversalKeysEnabled(false);
			btnReporte.setFocusable(false);
			btnReporte.setForeground(Color.BLACK);
			btnReporte.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

				}
			});
			btnReporte.setFont(new Font("Sylfaen", Font.PLAIN, 30));
			btnReporte.setBorder(null);
			btnReporte.setBackground(Colores.getAzulMedio());
			btnReporte.setBounds(0, 419, 294, 70);
		}
		return btnReporte;
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
			panelSup.add(getLblConsul());
			panelSup.add(getLblBienvenido());
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
			lblNewLabel_2.setBounds(36, 108, 150, 95);
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
	
	private JLabel getLblConsul() {
		if (lblConsul == null) {
			System.out.println(s.buscarConsultorioPorId(medico.getConsultorio())== null ? "null": "bien");
			lblConsul = new JLabel("Consultorio #" + s.buscarConsultorioPorId(medico.getConsultorio()).getNumero());
			lblConsul.setFont(new Font("Segoe UI", Font.PLAIN, 27));
			lblConsul.setBounds(23, 69, 367, 58);
		}
		return lblConsul;
	}
	private JLabel getLblBienvenido() {
		if (lblBienvenido == null) {
			lblBienvenido = new JLabel("Bienvenido " + medico.getNombreCompleto());
			lblBienvenido.setFont(new Font("Segoe UI", Font.PLAIN, 30));
			lblBienvenido.setBounds(23, 8, 595, 58);
		}
		return lblBienvenido;
	}
}
