package Interfaz;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Salud.Minsap;
import Utiles.Colores;
import Utiles.Enfermedad;
import Utiles.Enfermedad.Categoria;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InformacionEnfermedades extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblTitulo;
	private JLabel lblFiltro;
	private JComboBox<Categoria> comboFiltro;
	private JScrollPane scrollInfo;
	private JTextArea textAreaInfo;
	private JButton btnSalir;
	private JSeparator separator;

	public static void main(String[] args) {
		try {
			InformacionEnfermedades dialog = new InformacionEnfermedades();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public InformacionEnfermedades() {
		// Configuración de la ventana
		setBounds(296, 162, 1070, 560);
		setUndecorated(true);
		setModal(true);
		getContentPane().setLayout(null);
		
		// Panel principal con colores personalizados
		contentPanel.setBackground(Colores.getAzulLogin()); // Fondo azul claro
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBounds(0, 0, 1071, 559);
		contentPanel.setLayout(null);
		getContentPane().add(contentPanel);
		
		// Agregar componentes
		contentPanel.add(getLblTitulo());
		contentPanel.add(getLblFiltro());
		contentPanel.add(getComboFiltro());
		contentPanel.add(getScrollInfo());
		contentPanel.add(getBtnSalir());
		contentPanel.add(getSeparator());
		
		// Cargar enfermedades por defecto
		cargarEnfermedades(null);
	}

	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("INFORMACIÓN DE ENFERMEDADES");
			lblTitulo.setBounds(274, 30, 476, 40);
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setFont(new Font("Sylfaen", Font.BOLD, 24));
			lblTitulo.setForeground(Colores.getAzulOscuro()); // Azul oscuro
		}
		return lblTitulo;
	}

	private JLabel getLblFiltro() {
		if (lblFiltro == null) {
			lblFiltro = new JLabel("Filtrar por categoría:");
			lblFiltro.setBounds(100, 100, 200, 27);
			lblFiltro.setFont(new Font("Sylfaen", Font.PLAIN, 19));
			lblFiltro.setForeground(Colores.getAzulOscuro());
		}
		return lblFiltro;
	}

	private JComboBox<Categoria> getComboFiltro() {
		if (comboFiltro == null) {
			DefaultComboBoxModel<Categoria> model = new DefaultComboBoxModel<>();
			model.addElement(null); // Para "Todas"
			for (Categoria cat : Categoria.values()) {
				model.addElement(cat);
			}
			
			comboFiltro = new JComboBox<>(model);
			comboFiltro.setBounds(300, 100, 300, 30);
			comboFiltro.setBackground(Color.WHITE);
			comboFiltro.setFont(new Font("Sylfaen", Font.PLAIN, 17));
			comboFiltro.setSelectedItem(null); // Seleccionar "Todas" por defecto
			
			// Renderer personalizado para mostrar nombre de categoría
			comboFiltro.setRenderer(new javax.swing.DefaultListCellRenderer() {
				@Override
				public Component getListCellRendererComponent(javax.swing.JList<?> list, Object value,
						int index, boolean isSelected, boolean cellHasFocus) {
					super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
					if (value == null) {
						setText("Todas las enfermedades");
					} else if (value instanceof Categoria) {
						setText(((Categoria) value).getNombre());
					}
					return this;
				}
			});
			
			comboFiltro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Categoria catSeleccionada = (Categoria) comboFiltro.getSelectedItem();
					cargarEnfermedades(catSeleccionada);
				}
			});
		}
		return comboFiltro;
	}

	private JScrollPane getScrollInfo() {
		if (scrollInfo == null) {
			textAreaInfo = new JTextArea();
			textAreaInfo.setForeground(Color.BLACK);
			textAreaInfo.setEditable(false);
			textAreaInfo.setLineWrap(true);
			textAreaInfo.setWrapStyleWord(true);
			textAreaInfo.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			textAreaInfo.setBackground(Color.WHITE);
			textAreaInfo.setBorder(new EmptyBorder(10, 10, 10, 10));
			
			scrollInfo = new JScrollPane(textAreaInfo);
			scrollInfo.setBounds(100, 170, 870, 340);
			scrollInfo.setBorder(new LineBorder(Colores.getAzulMedio(), 2));
			scrollInfo.getViewport().setBackground(Color.WHITE);
		}
		return scrollInfo;
	}

	private JButton getBtnSalir() {
		if (btnSalir == null) {
			btnSalir = new JButton("");
			btnSalir.setBounds(1021, 0, 50, 50);
			btnSalir.setBorder(null);
			btnSalir.setHorizontalTextPosition(SwingConstants.CENTER);
			btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnSalir.setIcon(new ImageIcon("src/Images/Iconos/otroLogoBorrar50x50.png"));
			btnSalir.setBackground(Colores.getAzulLogin());
			btnSalir.setForeground(Colores.getAzulLogin());
			btnSalir.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 25));
			btnSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
		}
		return btnSalir;
	}

	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setBounds(100, 150, 870, 2);
			separator.setForeground(Colores.getAzulMedio());
		}
		return separator;
	}

	private void cargarEnfermedades(Categoria filtro) {
		StringBuilder sb = new StringBuilder();
		
		// Obtener enfermedades según filtro
		List<Enfermedad> enfermedades;
		if (filtro == null) {
			enfermedades = Minsap.getEnfermedadesActuales();
		} else {
			enfermedades = filtrarEnfermedadesPorCategoria(filtro);
		}
		
		if (enfermedades.isEmpty()) {
			sb.append("No hay enfermedades registradas");
			if (filtro != null) {
				sb.append(" en la categoría: ").append(filtro.getNombre());
			}
			sb.append(".\n");
		} else {
			// Título según filtro
			if (filtro == null) {
				sb.append("TODAS LAS ENFERMEDADES REGISTRADAS\n");
				sb.append("***********************************\n\n");
			} else {
				sb.append("ENFERMEDADES DE CATEGORÍA: ").append(filtro.getNombre()).append("\n");
				sb.append("***********************************\n\n");
			}
			
			// Información de cada enfermedad
			for (int i = 0; i < enfermedades.size(); i++) {
				Enfermedad enf = enfermedades.get(i);
				
				sb.append(enf.getNombre()).append(" 】 \n");
				sb.append("*************************************\n");
				//sb.append("- Nombre: ").append(enf.getNombre()).append("\n");
				sb.append("- Categoría: ").append(enf.getCategoria().getNombre()).append("\n");
				sb.append("- Gravedad típica: ").append(enf.getGravedadTipica().getNivel()).append("\n");
				sb.append("- Agente etiológico: ").append(enf.getAgenteEtiologico()).append("\n");
				sb.append("- Duración estimada: ").append(enf.getDuracion()).append("\n");
				
				// Síntomas
				sb.append("- Síntomas comunes:\n");
				List<Utiles.Sintomas> sintomas = enf.getSintomasComunes();
				if (sintomas.isEmpty()) {
					sb.append("  - No hay síntomas registrados\n");
				} else {
					for (Utiles.Sintomas sintoma : sintomas) {
						sb.append("  >  ").append(sintoma.getDescripcion()).append("\n");
					}
				}
				
				// Recomendación
				sb.append("• Recomendación básica: ").append(enf.getRecomendacionBasica()).append("\n\n");
				
				// Separador entre enfermedades
				if (i < enfermedades.size() - 1) {
					sb.append("***********************************\n\n");
				}
			}
			
			// Resumen
			sb.append("*****************************************\n");
			sb.append("Total de enfermedades: ").append(enfermedades.size()).append("\n");
			if (filtro != null) {
				sb.append("Categoría: ").append(filtro.getNombre()).append("\n");
			}
		}
		
		textAreaInfo.setText(sb.toString());
		textAreaInfo.setCaretPosition(0); // Ir al inicio
	}

	private List<Enfermedad> filtrarEnfermedadesPorCategoria(Categoria categoria) {
		List<Enfermedad> enfermedadesFiltradas = new ArrayList<>();
		List<Enfermedad> todas = Minsap.getEnfermedadesActuales();
		
		for (Enfermedad enf : todas) {
			if (enf.getCategoria() == categoria) {
				enfermedadesFiltradas.add(enf);
			}
		}
		
		return enfermedadesFiltradas;
	}
	
	// Clase auxiliar para los colores (si no existe ya)
	public static class ColoresEnfermedades {
		private static final Color azulLogin = new Color(222, 240, 253);
		private static final Color azulOscuro = new Color(37, 100, 144);
		private static final Color azulMedio = new Color(128, 171, 200);
		
		public static Color getAzulLogin() { return azulLogin; }
		public static Color getAzulOscuro() { return azulOscuro; }
		public static Color getAzulMedio() { return azulMedio; }
	}
}