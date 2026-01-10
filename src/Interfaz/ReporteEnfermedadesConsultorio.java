package Interfaz;

import org.knowm.xchart.*;
import org.knowm.xchart.style.PieStyler;
import org.knowm.xchart.style.Styler;

import Auxiliar.PacientesPorEnfermedad;
import Salud.Consultorio;
import Utiles.Colores;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.border.LineBorder;

public class ReporteEnfermedadesConsultorio extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private PieChart chart;
    private JButton btnSalir;
    private JComboBox<String> comboReportes;
    private Consultorio consultorio;
    private JLabel lblTitulo;
    private JLabel lblTotalPacientes;

    public ReporteEnfermedadesConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
        
        getContentPane().setBackground(Colores.getAzulLogin());
        setTitle("Reporte de Pacientes por Enfermedad");
        setBounds(338, 159, 1026, 562);
        setModal(true);
        setUndecorated(true);
        getContentPane().setLayout(new BorderLayout());
        
        contentPanel.setBackground(Colores.getAzulLogin());
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(new LineBorder(Colores.getAzulMedio(), 2));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        inicializarComponentes();
        inicializarGrafico();
    }

    private void inicializarComponentes() {
        // Panel superior con controles
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(null);
        panelSuperior.setBackground(Colores.getAzulLogin());
        panelSuperior.setPreferredSize(new Dimension(1026, 100));
        
        // Título
        lblTitulo = new JLabel("REPORTE DE PACIENTES - " + consultorio.getNombre());
        lblTitulo.setBounds(20, 10, 600, 30);
        lblTitulo.setFont(new Font("Sylfaen", Font.BOLD, 20));
        lblTitulo.setForeground(Colores.getAzulOscuro());
        panelSuperior.add(lblTitulo);
        
        // Label para total de pacientes
        lblTotalPacientes = new JLabel("Total pacientes: 0");
        lblTotalPacientes.setBounds(650, 15, 250, 25);
        lblTotalPacientes.setFont(new Font("Sylfaen", Font.BOLD, 16));
        lblTotalPacientes.setForeground(Colores.getAzulOscuro());
        panelSuperior.add(lblTotalPacientes);
        
        // Label para combo
        JLabel lblSeleccion = new JLabel("Seleccione período:");
        lblSeleccion.setBounds(20, 50, 150, 25);
        lblSeleccion.setFont(new Font("Sylfaen", Font.PLAIN, 16));
        lblSeleccion.setForeground(Colores.getAzulOscuro());
        panelSuperior.add(lblSeleccion);
        
        // ComboBox para seleccionar reporte
        comboReportes = new JComboBox<>(new String[]{
            "Pacientes del Día Actual",
            "Pacientes del Mes Actual", 
            "Pacientes del Mes Anterior",
            "Pacientes del Año Actual"
        });
        comboReportes.setBounds(180, 50, 250, 25);
        comboReportes.setBackground(Color.WHITE);
        comboReportes.setFont(new Font("Sylfaen", Font.PLAIN, 14));
        comboReportes.setSelectedIndex(0);
        
        comboReportes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarGrafico();
            }
        });
        panelSuperior.add(comboReportes);
        
        // Botón salir
        panelSuperior.add(getBtnSalir());
        
        contentPanel.add(panelSuperior, BorderLayout.NORTH);
    }

    private void inicializarGrafico() {
        // Crear gráfico circular
        chart = new PieChartBuilder()
                .width(900)
                .height(500)
                .title("Distribución de Pacientes por Enfermedad")
                .build();

        personalizarGrafico(chart);
        actualizarGrafico(); // Cargar datos iniciales
        
        // Panel para el gráfico
        XChartPanel<PieChart> chartPanel = new XChartPanel<>(chart);
        chartPanel.setFont(new Font("Sylfaen", Font.PLAIN, 12));
        chartPanel.setBackground(Colores.getAzulLogin());
        chartPanel.setPreferredSize(new Dimension(1000, 450));
        
        JPanel panelChart = new JPanel(new BorderLayout());
        panelChart.setBackground(Colores.getAzulLogin());
        panelChart.add(chartPanel, BorderLayout.CENTER);
        panelChart.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        contentPanel.add(panelChart, BorderLayout.CENTER);
    }

    private void actualizarGrafico() {
        // Obtener datos según la selección
        ArrayList<PacientesPorEnfermedad> datosEnfermedades = null;
        String periodo = (String) comboReportes.getSelectedItem();
        
        // Actualizar título según período seleccionado
        lblTitulo.setText("REPORTE - " + consultorio.getNombre() + " (" + periodo + ")");
        
        // Obtener datos según la opción seleccionada
        try {
            switch(comboReportes.getSelectedIndex()) {
                case 0: // Día actual
                    datosEnfermedades = consultorio.pacientesEsteDia();
                    break;
                case 1: // Mes actual
                    datosEnfermedades = consultorio.pacientesMesActual();
                    break;
                case 2: // Mes anterior
                    datosEnfermedades = consultorio.pacientesMesAnterior();
                    break;
                case 3: // Año actual
                    datosEnfermedades = consultorio.pacientesAnno();
                    break;
            }
        } catch (Exception e) {
            System.err.println("Error al obtener datos: " + e.getMessage());
            datosEnfermedades = new ArrayList<>();
        }
        
        // Crear un nuevo gráfico en lugar de actualizar (más compatible)
        chart = new PieChartBuilder()
                .width(900)
                .height(500)
                .title("Distribución de Pacientes por Enfermedad")
                .build();
        
        personalizarGrafico(chart);
        
        // Calcular total de pacientes
        int totalPacientes = 0;
        if (datosEnfermedades != null && !datosEnfermedades.isEmpty()) {
            for (PacientesPorEnfermedad ppe : datosEnfermedades) {
                totalPacientes += ppe.getCantPacientes();
            }
            
            // Actualizar label de total
            lblTotalPacientes.setText("Total pacientes: " + totalPacientes);
            
            // Agregar series al gráfico
            for (PacientesPorEnfermedad ppe : datosEnfermedades) {
                String enfermedad = ppe.getEnfermedad();
                int cantidad = ppe.getCantPacientes();
                
                if (cantidad > 0) { // Solo mostrar enfermedades con pacientes
                    // Formatear etiqueta (acortar nombres largos)
                    String nombreCorto = enfermedad;
                    if (enfermedad.length() > 20) {
                        nombreCorto = enfermedad.substring(0, 17) + "...";
                    }
                    
                    // Crear etiqueta con información
                    String label = String.format("%s (%d)", nombreCorto, cantidad);
                    
                    // Agregar serie al gráfico
                    chart.addSeries(label, cantidad);
                }
            }
            
            // Actualizar título del gráfico
            if (totalPacientes > 0) {
                chart.setTitle("Distribución de Pacientes por Enfermedad\nTotal: " + totalPacientes + " pacientes");
            } else {
                chart.setTitle("No hay pacientes registrados en este período");
            }
        } else {
            // Si no hay datos, mostrar mensaje
            lblTotalPacientes.setText("Total pacientes: 0");
            chart.addSeries("Sin datos", 1);
            chart.setTitle("No hay datos disponibles para este período");
        }
        
        // Actualizar el panel del gráfico
        contentPanel.remove(1); // Remover el panel del gráfico actual
        XChartPanel<PieChart> chartPanel = new XChartPanel<>(chart);
        chartPanel.setFont(new Font("Sylfaen", Font.PLAIN, 12));
        chartPanel.setBackground(Colores.getAzulLogin());
        chartPanel.setPreferredSize(new Dimension(1000, 450));
        
        JPanel panelChart = new JPanel(new BorderLayout());
        panelChart.setBackground(Colores.getAzulLogin());
        panelChart.add(chartPanel, BorderLayout.CENTER);
        panelChart.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        contentPanel.add(panelChart, BorderLayout.CENTER);
        
        // Revalidar y repintar
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private JButton getBtnSalir() {
        if (btnSalir == null) {
            btnSalir = new JButton("");
            btnSalir.setBounds(964, 10, 50, 50);
            btnSalir.setBorder(null);
            btnSalir.setHorizontalTextPosition(SwingConstants.CENTER);
            btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnSalir.setIcon(new ImageIcon("src/Images/Iconos/otroLogoBorrar50x50.png"));
            btnSalir.setBackground(Colores.getAzulLogin());
            btnSalir.setForeground(Colores.getAzulLogin());
            btnSalir.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    dispose();
                }
            });
        }
        return btnSalir;
    }

    private void personalizarGrafico(PieChart chart) {
        PieStyler styler = chart.getStyler();
        
        // Paleta de tonos azules que contrastan
        Color[] coloresAzules = {
            new Color(37, 100, 144),   // Azul oscuro principal
            new Color(52, 152, 219),   // Azul brillante
            new Color(93, 173, 226),   // Azul medio claro
            new Color(133, 193, 233),  // Azul claro
            new Color(174, 214, 241),  // Azul muy claro
            new Color(21, 67, 96),     // Azul marino oscuro
            new Color(41, 128, 185),   // Azul eléctrico
            new Color(72, 163, 215),   // Azul cielo
            new Color(127, 179, 213),  // Azul pastel
            new Color(186, 220, 247),  // Azul hielo
            new Color(30, 80, 115),    // Azul noche medio
            new Color(65, 142, 200),   // Azul intermedio
            new Color(100, 165, 215),  // Azul cielo claro
            new Color(140, 185, 225),  // Azul pastel medio
            new Color(180, 205, 235)   // Azul muy claro
        };
        
        styler.setSeriesColors(coloresAzules);
        styler.setLabelsVisible(true);
        styler.setLabelType(PieStyler.LabelType.NameAndPercentage);
        styler.setLabelsDistance(0.8);
        styler.setStartAngleInDegrees(45); // Rotar gráfico para mejor presentación
        
        // Fuentes usando Sylfaen como en tu interfaz
        Font fuenteBase = new Font("Sylfaen", Font.PLAIN, 12);
        Font tituloFuente = new Font("Sylfaen", Font.BOLD, 18);
        Font leyendaFuente = new Font("Sylfaen", Font.PLAIN, 14);
        Font etiquetaFuente = new Font("Sylfaen", Font.BOLD, 11);
        
        styler.setBaseFont(fuenteBase);
        styler.setChartTitleFont(tituloFuente);
        styler.setLegendFont(leyendaFuente);
        styler.setLabelsFont(etiquetaFuente);
        styler.setLabelsFontColor(Color.WHITE);
        
        // Configurar colores de fondo y tema
        styler.setChartFontColor(Colores.getAzulOscuro());
        styler.setPlotBackgroundColor(Color.WHITE);
        styler.setChartBackgroundColor(Colores.getAzulLogin());
        
        // Bordes y estilo
        styler.setPlotBorderVisible(true);
        styler.setPlotBorderColor(Colores.getAzulMedio());
        styler.setPlotContentSize(0.7);
        styler.setCircular(true);
        
        // Configurar leyenda - método compatible
        styler.setLegendPosition(Styler.LegendPosition.OutsideE);
        styler.setLegendLayout(Styler.LegendLayout.Vertical);
        
        // Para versiones antiguas, usar métodos más básicos
        try {
            // Intentar métodos más nuevos
            styler.setLegendBackgroundColor(Color.WHITE);
        } catch (NoSuchMethodError e) {
            // Si falla, usar métodos básicos
            System.out.println("Usando configuración básica para leyenda");
        }
        
        try {
            styler.setLegendBorderColor(Colores.getAzulMedio());
        } catch (NoSuchMethodError e) {
            // Método no disponible
        }
        
        // Tooltips - métodos básicos que deberían funcionar
        styler.setToolTipsEnabled(true);
        
        // Título
        styler.setChartTitlePadding(20);
        
        // Formato
        styler.setDecimalPattern("#,##0");
        styler.setChartPadding(25);
        
        // Métodos alternativos para versiones antiguas
        try {
            // Intentar métodos que pueden no estar disponibles
            styler.setSumVisible(true);
        } catch (NoSuchMethodError e) {
            // Ignorar si no existe
        }
        
        try {
            styler.setAntiAlias(true);
        } catch (NoSuchMethodError e) {
            // Ignorar si no existe
        }
        
        // Configuración mínima que funciona en todas las versiones
        styler.setLegendVisible(true);
        styler.setPlotBackgroundColor(Color.WHITE);
        styler.setChartBackgroundColor(Colores.getAzulLogin());
        styler.setLabelsVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    System.out.println("Ventana de reporte lista para usar");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}