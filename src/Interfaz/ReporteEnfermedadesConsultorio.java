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
    private JPanel panelChartContainer; // Variable para mantener referencia al contenedor del gráfico

    public ReporteEnfermedadesConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
        
        getContentPane().setBackground(Color.WHITE);
        setTitle("Reporte de Pacientes por Enfermedad");
        setBounds(296, 164, 1070, 558);
        setModal(true);
        setUndecorated(true);
        getContentPane().setLayout(new BorderLayout());
        
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(null);
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        inicializarComponentes();
        inicializarGrafico();
    }

    private void inicializarComponentes() {
        // Panel superior con controles
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(null);
        panelSuperior.setBackground(Color.WHITE);
        panelSuperior.setPreferredSize(new Dimension(1026, 100));
        
        // Título
        lblTitulo = new JLabel("REPORTE DE PACIENTES - " + "Consultorio " + consultorio.getNumero());
        lblTitulo.setBounds(20, 10, 600, 30);
        lblTitulo.setFont(new Font("Sylfaen", Font.BOLD, 20));
        lblTitulo.setForeground(Color.BLACK);
        panelSuperior.add(lblTitulo);
        
        // Label para total de pacientes
        lblTotalPacientes = new JLabel("Total pacientes: 0");
        lblTotalPacientes.setBounds(650, 15, 250, 25);
        lblTotalPacientes.setFont(new Font("Segoe UI", Font.PLAIN, 19));
        lblTotalPacientes.setForeground(Color.BLACK);
        panelSuperior.add(lblTotalPacientes);
        
        // Label para combo
        JLabel lblSeleccion = new JLabel("Seleccione período:");
        lblSeleccion.setBounds(20, 50, 150, 25);
        lblSeleccion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblSeleccion.setForeground(Color.BLACK);
        panelSuperior.add(lblSeleccion);
        
        // ComboBox para seleccionar reporte
        comboReportes = new JComboBox();
        comboReportes.setModel(new DefaultComboBoxModel(new String[] {"Pacientes del Día Actual",
                "Pacientes del Mes Actual", 
                "Pacientes del Mes Anterior",
                "Pacientes del Año Actual"}
        ));
        comboReportes.setBounds(180, 50, 250, 25);
        comboReportes.setBackground(Color.WHITE);
        comboReportes.setFont(new Font("Segoe UI", Font.PLAIN, 14));
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
                .height(600)
                .title("Distribución de Pacientes por Enfermedad")
                .build();

        personalizarGrafico(chart);
        
        // Panel para el gráfico
        XChartPanel<PieChart> chartPanel = new XChartPanel<>(chart);
        chartPanel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        chartPanel.setBackground(Color.WHITE);
        
        // Crear contenedor para el gráfico
        panelChartContainer = new JPanel(new BorderLayout());
        panelChartContainer.setBackground(Color.WHITE);
        panelChartContainer.add(chartPanel, BorderLayout.CENTER);
        panelChartContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        contentPanel.add(panelChartContainer, BorderLayout.CENTER);
        
        // Cargar datos iniciales DESPUÉS de crear el contenedor
        actualizarGrafico();
    }

    private void actualizarGrafico() {
        // Obtener datos según la selección
        ArrayList<PacientesPorEnfermedad> datosEnfermedades = null;
        String periodo = (String) comboReportes.getSelectedItem();
        
        // Actualizar título según período seleccionado
        lblTitulo.setText("Consultorio " + consultorio.getNumero() + " (" + periodo + ")");
        
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
                .height(600)
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
        panelChartContainer.removeAll(); // Limpiar el contenedor
        
        XChartPanel<PieChart> chartPanel = new XChartPanel<>(chart);
        chartPanel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        chartPanel.setBackground(Color.WHITE);
        
        panelChartContainer.add(chartPanel, BorderLayout.CENTER);
        
        // Revalidar y repintar
        panelChartContainer.revalidate();
        panelChartContainer.repaint();
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
            btnSalir.setBackground(Color.WHITE);
            btnSalir.setForeground(Color.WHITE);
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
        
        // Paleta de colores pastel similar a Reporte1GraficoMaterias
        Color[] coloresPastel = {
            new Color(173, 216, 230), // Azul pastel
            new Color(144, 238, 144), // Verde pastel
            new Color(255, 182, 193), // Rosa pastel
            new Color(221, 160, 221), // Lavanda
            new Color(255, 215, 0),   // Amarillo dorado
            new Color(152, 251, 152), // Verde menta
            new Color(175, 238, 238), // Turquesa pastel
            new Color(255, 218, 185), // Melocotón
            new Color(216, 191, 216), // Ciruela pastel
            new Color(240, 230, 140)  // Amarillo caqui
        };
        
        styler.setSeriesColors(coloresPastel);
        styler.setLabelsVisible(true);
        styler.setLabelType(PieStyler.LabelType.NameAndValue);
        styler.setLabelsDistance(1.12);
        
        // Fuentes usando Segoe UI como en Reporte1GraficoMaterias
        Font fuente = new Font("Segoe UI", Font.PLAIN, 10);
        Font tituloFuente = new Font("Segoe UI", Font.BOLD, 18);
        Font leyendaFuente = new Font("Segoe UI", Font.PLAIN, 12);
        
        styler.setBaseFont(fuente);
        styler.setChartTitleFont(tituloFuente);
        styler.setLegendFont(leyendaFuente);
        styler.setLabelsFont(fuente.deriveFont(Font.BOLD));
        styler.setLabelsFontColor(Color.BLACK);
        
        styler.setChartFontColor(Color.BLACK);
        styler.setPlotBackgroundColor(Color.WHITE);
        styler.setChartBackgroundColor(Color.WHITE);
        
        styler.setPlotBorderVisible(true);
        styler.setPlotBorderColor(new Color(220, 220, 220));
        styler.setPlotContentSize(0.7);
        styler.setCircular(true);
        
        styler.setLegendPosition(Styler.LegendPosition.OutsideE);
        styler.setLegendLayout(Styler.LegendLayout.Vertical);
        
        try {
            styler.setLegendBackgroundColor(Color.WHITE);
            styler.setLegendBorderColor(new Color(200, 200, 200));
            styler.setLegendPadding(10);
        } catch (NoSuchMethodError e) {
            System.out.println("Usando configuración básica para leyenda");
        }
        
        styler.setToolTipsEnabled(true);
        
        try {
            styler.setToolTipFont(fuente.deriveFont(Font.ITALIC));
        } catch (NoSuchMethodError e) {
            // Ignorar si no existe
        }
        
        styler.setChartTitlePadding(15);
        
        try {
            styler.setChartTitleBoxBackgroundColor(Color.WHITE);
            styler.setChartTitleBoxBorderColor(Color.GRAY);
            styler.setChartTitleBoxVisible(true);
        } catch (NoSuchMethodError e) {
            // Ignorar si no existe
        }
        
        styler.setDecimalPattern("#0");
        styler.setChartPadding(15);
    }
}