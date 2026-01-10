package Interfaz;

import org.knowm.xchart.*;
import org.knowm.xchart.style.AxesChartStyler;
import org.knowm.xchart.style.Styler;

import Salud.Consultorio;
import Utiles.Enfermedad;
import Salud.Minsap;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReporteEnfermedadPorMeses extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private CategoryChart chart;
    private JButton btnSalir;
    private JComboBox<String> comboEnfermedades;
    private Consultorio consultorio;
    private JLabel lblTitulo;
    private JLabel lblTotalPacientes;
    private JPanel panelChartContainer;

    public ReporteEnfermedadPorMeses(Consultorio consultorio) {
        this.consultorio = consultorio;
        
        getContentPane().setBackground(Color.WHITE);
        setTitle("Reporte de Pacientes por Enfermedad por Meses");
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
        lblTitulo = new JLabel("PACIENTES POR MESES - Consultorio " + consultorio.getNumero());
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
        JLabel lblSeleccion = new JLabel("Seleccione enfermedad:");
        lblSeleccion.setBounds(20, 50, 150, 25);
        lblSeleccion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblSeleccion.setForeground(Color.BLACK);
        panelSuperior.add(lblSeleccion);
        
        // ComboBox para seleccionar enfermedad
        comboEnfermedades = new JComboBox<String>();
        ArrayList<Enfermedad> enfermedades = Minsap.getEnfermedadesActuales();
        String[] nombresEnfermedades = new String[enfermedades.size()];
        for (int i = 0; i < enfermedades.size(); i++) {
            nombresEnfermedades[i] = enfermedades.get(i).getNombre();
        }
        comboEnfermedades.setModel(new DefaultComboBoxModel<String>(nombresEnfermedades));
        comboEnfermedades.setBounds(180, 50, 250, 25);
        comboEnfermedades.setBackground(Color.WHITE);
        comboEnfermedades.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        if (comboEnfermedades.getItemCount() > 0) {
            comboEnfermedades.setSelectedIndex(0);
        }
        
        comboEnfermedades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarGrafico();
            }
        });
        panelSuperior.add(comboEnfermedades);
        
        // Botón salir
        panelSuperior.add(getBtnSalir());
        
        contentPanel.add(panelSuperior, BorderLayout.NORTH);
    }

    private void inicializarGrafico() {
        // Crear gráfico de barras
        chart = new CategoryChartBuilder()
                .width(900)
                .height(600)
                .title("Pacientes por Mes - Año Actual")
                .xAxisTitle("Meses")
                .yAxisTitle("Cantidad de Pacientes")
                .build();

        personalizarGrafico(chart);
        
        // Panel para el gráfico
        XChartPanel<CategoryChart> chartPanel = new XChartPanel<CategoryChart>(chart);
        chartPanel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        chartPanel.setBackground(Color.WHITE);
        
        // Crear contenedor para el gráfico
        panelChartContainer = new JPanel(new BorderLayout());
        panelChartContainer.setBackground(Color.WHITE);
        panelChartContainer.add(chartPanel, BorderLayout.CENTER);
        panelChartContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        contentPanel.add(panelChartContainer, BorderLayout.CENTER);
        
        // Cargar datos iniciales
        if (comboEnfermedades.getItemCount() > 0) {
            actualizarGrafico();
        }
    }

    private void actualizarGrafico() {
        if (comboEnfermedades.getSelectedIndex() == -1) {
            return;
        }
        
        String enfermedadSeleccionada = (String) comboEnfermedades.getSelectedItem();
        
        // Actualizar título
        lblTitulo.setText("PACIENTES POR MESES - " + enfermedadSeleccionada + " - Consultorio " + consultorio.getNumero());
        
        // Obtener datos de pacientes por meses para la enfermedad seleccionada
        int[] pacientesPorMesArray = consultorio.pacientesPorMeses(enfermedadSeleccionada);
        
        // Crear un nuevo gráfico
        chart = new CategoryChartBuilder()
                .width(900)
                .height(600)
                .title("Pacientes por Mes - " + enfermedadSeleccionada)
                .xAxisTitle("Meses")
                .yAxisTitle("Cantidad de Pacientes")
                .build();
        
        personalizarGrafico(chart);
        
        // En XChart 3.8.5 para CategoryChart, necesitamos usar double[] para ambos ejes
        // Los meses se representan como números (0-11) y luego se personalizan las etiquetas
        
        // Datos para el gráfico - eje X: números del 0 al 11 (representan los meses)
        double[] mesesNumeros = new double[12];
        double[] datosPacientes = new double[12];
        int totalPacientes = 0;
        
        // Inicializar arrays
        for (int i = 0; i < 12; i++) {
            mesesNumeros[i] = i; // 0 = Enero, 1 = Febrero, etc.
        }
        
        if (pacientesPorMesArray != null && pacientesPorMesArray.length >= 12) {
            for (int i = 0; i < 12; i++) {
                datosPacientes[i] = pacientesPorMesArray[i];
                totalPacientes += datosPacientes[i];
            }
            
            // Actualizar label de total
            lblTotalPacientes.setText("Total pacientes año: " + totalPacientes);
            
            // Agregar serie al gráfico - EN XChart 3.8.5: addSeries(String, double[], double[])
            chart.addSeries(enfermedadSeleccionada, mesesNumeros, datosPacientes);
            
            // Configurar etiquetas personalizadas para los meses
            // En XChart 3.8.5, podemos usar setCustomXAxisTickLabels para personalizar las etiquetas
            String[] mesesLabels = {"Ene", "Feb", "Mar", "Abr", "May", "Jun", 
                                   "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"};
            
            // Intentar usar setCustomXAxisTickLabels si existe
            try {
                java.lang.reflect.Method method = chart.getClass().getMethod("setCustomXAxisTickLabels", String[].class);
                method.invoke(chart, (Object) mesesLabels);
            } catch (Exception e) {
                // Si no existe el método, usar un enfoque alternativo
                System.out.println("Método setCustomXAxisTickLabels no disponible en XChart 3.8.5");
                // Intentar otra forma
                try {
                    // Otra forma: configurar manualmente las etiquetas
                    chart.getStyler().setxAxisTickLabelsFormattingFunction(new java.util.function.Function<Double, String>() {
                        @Override
                        public String apply(Double d) {
                            int mes = d.intValue();
                            String[] meses = {"Ene", "Feb", "Mar", "Abr", "May", "Jun", 
                                             "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"};
                            if (mes >= 0 && mes < meses.length) {
                                return meses[mes];
                            }
                            return String.valueOf(mes);
                        }
                    });
                } catch (Exception ex) {
                    // Si tampoco funciona esta forma, dejar números
                    System.out.println("No se pueden personalizar etiquetas en esta versión de XChart");
                }
            }
            
            // Actualizar título del gráfico
            if (totalPacientes > 0) {
                chart.setTitle("Pacientes por Mes - " + enfermedadSeleccionada + "\nTotal año: " + totalPacientes + " pacientes");
            } else {
                chart.setTitle("No hay pacientes registrados para " + enfermedadSeleccionada);
            }
        } else {
            // Si no hay datos
            for (int i = 0; i < 12; i++) {
                datosPacientes[i] = 0;
            }
            
            lblTotalPacientes.setText("Total pacientes año: 0");
            chart.addSeries(enfermedadSeleccionada, mesesNumeros, datosPacientes);
            chart.setTitle("No hay datos disponibles para " + enfermedadSeleccionada);
        }
        
        // Actualizar el panel del gráfico
        panelChartContainer.removeAll();
        
        XChartPanel<CategoryChart> chartPanel = new XChartPanel<CategoryChart>(chart);
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

    private void personalizarGrafico(CategoryChart chart) {
        Styler styler = chart.getStyler();
        
        // Configuración MÍNIMA que debería funcionar en XChart 3.8.5
        Font tituloFuente = new Font("Segoe UI", Font.BOLD, 18);
        Font ejeFuente = new Font("Segoe UI", Font.PLAIN, 12);
        Font leyendaFuente = new Font("Segoe UI", Font.PLAIN, 12);
        
        // Métodos básicos que existen en XChart 3.8.5
        styler.setChartTitleFont(tituloFuente);
        
        try {
            styler.setLegendFont(leyendaFuente);
            ((AxesChartStyler) styler).setAxisTitleFont(ejeFuente);
            ((AxesChartStyler) styler).setAxisTickLabelsFont(ejeFuente);
        } catch (Exception e) {
            // Algunos métodos pueden no existir
        }
        
        // Colores básicos
        styler.setChartBackgroundColor(Color.WHITE);
        styler.setPlotBackgroundColor(Color.WHITE);
        
        // Gridlines
        ((AxesChartStyler) styler).setPlotGridLinesVisible(true);
        
        // Leyenda
        styler.setLegendPosition(Styler.LegendPosition.InsideNW);
        
        // Tooltips
        try {
            styler.setToolTipsEnabled(true);
        } catch (Exception e) {
            // Método puede no existir
        }
        
        // Configurar que el eje Y empiece en 0
        try {
            ((AxesChartStyler) styler).setYAxisMin(0.0);
        } catch (Exception e) {
            // Método puede no existir
        }
    }
}