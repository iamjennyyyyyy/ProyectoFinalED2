package Interfaz;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import javax.swing.filechooser.FileNameExtensionFilter;

import Persona.Paciente;
import Utiles.Colores;
import Utiles.Enfermedad;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;



// Importaciones para PDF
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;

public class VerPacientes extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable tablaPacientes;
    private DefaultTableModel modeloTabla;
    private JTextField txtBuscarNombre;
    private JComboBox<String> comboSexo;
    private JComboBox<String> comboEnfermedad;
    private JButton btnLimpiarFiltros;
    private JButton btnBuscarLupa;
    private JButton btnExportarPDF;
    private JLabel lblBuscarNombre;
    private JLabel lblSexo;
    private JLabel lblEnfermedad;
    private ArrayList<Paciente> listaPacientes;
    private ArrayList<Paciente> listaFiltrada;
    private TableRowSorter<DefaultTableModel> sorter;
    
    // Panel para el campo de búsqueda
    private JPanel panelBusqueda;
    
    // Formateador de fechas
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            // Crear lista de prueba
            ArrayList<Paciente> pacientesPrueba = crearListaPrueba();
            
            VerPacientes dialog = new VerPacientes(pacientesPrueba);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static ArrayList<Paciente> crearListaPrueba() {
        ArrayList<Paciente> lista = new ArrayList<>();
        
        // Crear algunos pacientes de prueba
        try {
            // Pacientes antiguos (fechas anteriores)
            Paciente p1 = new Paciente();
            p1.setNombreCompleto("Juan Pérez");
            p1.setId("85010112345");
            p1.setCorreo("juan@ejemplo.com");
            p1.setNumero("12345678");
            p1.setDireccion("Calle 1 #123");
            p1.setFechaDiagnostico(LocalDate.now().minusDays(30));
            p1.agregarEnfermedad( new Enfermedad("Zika"));
            lista.add(p1);
            
            Paciente p2 = new Paciente();
            p2.setNombreCompleto("María García");
            p2.setId("06040468470");
            p2.setCorreo("maria@ejemplo.com");
            p2.setNumero("87654321");
            p2.setDireccion("Calle 2 #456");
            p2.setFechaDiagnostico(LocalDate.now().minusDays(15));
            lista.add(p2);
            
            Paciente p3 = new Paciente();
            p3.setNombreCompleto("Carlos López");
            p3.setId("85030312345");
            p3.setCorreo("carlos@ejemplo.com");
            p3.setNumero("55555555");
            p3.setDireccion("Calle 3 #789");
            p3.setFechaDiagnostico(LocalDate.now().minusDays(5));
            p3.agregarEnfermedad( new Enfermedad("Covid"));
            p3.agregarEnfermedad( new Enfermedad("Dengue"));
            lista.add(p3);
            
            Paciente p4 = new Paciente();
            p4.setNombreCompleto("Ana Rodríguez");
            p4.setId("85040412345");
            p4.setCorreo("ana@ejemplo.com");
            p4.setNumero("44444444");
            p4.setDireccion("Calle 4 #012");
            p4.setFechaDiagnostico(LocalDate.now());
            p4.agregarEnfermedad( new Enfermedad("Covid"));
            p4.agregarEnfermedad( new Enfermedad("Dengue"));
            p4.agregarEnfermedad( new Enfermedad("Zika"));
            lista.add(p4);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return lista;
    }

    /**
     * Create the dialog.
     */
    public VerPacientes(ArrayList<Paciente> pacientes) {
        this.listaPacientes = pacientes;
        this.listaFiltrada = new ArrayList<>(pacientes);
        
        setBounds(296, 164, 1070, 558);
        setUndecorated(true);
        setModal(true);
        getContentPane().setLayout(null);
        
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setBounds(0, 0, 1071, 559);
        contentPanel.setLayout(null);
        getContentPane().add(contentPanel);
        
        contentPanel.add(getLblTitulo());
        contentPanel.add(getBtnSalir());
        contentPanel.add(getLblFiltros());
        
        // Panel de búsqueda (inicialmente oculto)
        contentPanel.add(getPanelBusqueda());
        
        // Botón de lupa para búsqueda
        contentPanel.add(getBtnBuscarLupa());
        
        // Etiquetas y filtros
        contentPanel.add(getLblBuscarNombre());
        contentPanel.add(getLblSexo());
        contentPanel.add(getComboSexo());
        contentPanel.add(getLblEnfermedad());
        contentPanel.add(getComboEnfermedad());
        contentPanel.add(getBtnLimpiarFiltros());
        contentPanel.add(getBtnExportarPDF());
        contentPanel.add(getScrollPaneTabla());
        
        // Invertir el orden de la lista para mostrar del más reciente al más antiguo
        invertirOrdenLista();
        
        // Cargar datos en la tabla
        cargarDatosEnTabla();
    }
    
    private void invertirOrdenLista() {
        ArrayList<Paciente> listaInvertida = new ArrayList<>();
        for (int i = listaPacientes.size() - 1; i >= 0; i--) {
            listaInvertida.add(listaPacientes.get(i));
        }
        listaPacientes = listaInvertida;
        listaFiltrada = new ArrayList<>(listaPacientes);
    }
    
    private void cargarDatosEnTabla() {
        // Limpiar la tabla
        modeloTabla.setRowCount(0);
       
        // Cargar datos de la lista filtrada
        for (Paciente paciente : listaFiltrada) {
            String enfermedades = "";
            for(Enfermedad e : paciente.getEnfermedades()) {
                enfermedades += e.getNombre() + ", ";
            }
            // Eliminar la última coma y espacio si hay enfermedades
            if (!enfermedades.isEmpty()) {
                enfermedades = enfermedades.substring(0, enfermedades.length() - 2);
            }
            
            Object[] fila = {
                paciente.getNombreCompleto(),
                paciente.getId(),
                paciente.getCorreo(),
                paciente.getNumero(),
                paciente.getDireccion(),
                enfermedades,
                Character.getNumericValue(paciente.getId().charAt(9)) % 2 == 0?  "M" : "F",
                paciente.getFechaDiagnostico().format(formatter),
            };
            modeloTabla.addRow(fila);
        }
    }
    
    private void aplicarFiltros() {
        String textoBusqueda = txtBuscarNombre.getText().toLowerCase();
        String sexoSeleccionado = (String) comboSexo.getSelectedItem();
        String enfermedadSeleccionada = (String) comboEnfermedad.getSelectedItem();
        
        // Crear nueva lista filtrada
        listaFiltrada = new ArrayList<>();
        
        for (Paciente paciente : listaPacientes) {
            boolean coincide = true;
            
         // Filtrar por nombre (búsqueda incremental)
            if (!textoBusqueda.isEmpty()) {
                // Normalizar el nombre del paciente (eliminar tildes y diacríticos)
                String nombreNormalizado = normalizarTexto(paciente.getNombreCompleto().toLowerCase());
                String busquedaNormalizada = normalizarTexto(textoBusqueda);
                
                if (!nombreNormalizado.contains(busquedaNormalizada)) {
                    coincide = false;
                }
            }
            
            // Filtrar por sexo
            if (coincide && !sexoSeleccionado.equals("Todos")) {
                char sexoCI = paciente.getId().charAt(9);
                boolean esHombre = Character.getNumericValue(sexoCI) % 2 == 0;
                String sexo = esHombre ? "M" : "F";
                
                if (!sexo.equals(sexoSeleccionado)) {
                    coincide = false;
                }
            }
            
            // Filtrar por enfermedad
            if (coincide && !enfermedadSeleccionada.equals("Todas")) {
                coincide = paciente.tieneEnfermedad(enfermedadSeleccionada);
            }
            
            if (coincide) {
                listaFiltrada.add(paciente);
            }
        }
        
        // Actualizar la tabla con la lista filtrada
        cargarDatosEnTabla();
        
        // Ocultar campo de búsqueda si está vacío y perdió el foco
        if (txtBuscarNombre.getText().isEmpty() && !txtBuscarNombre.hasFocus()) {
            panelBusqueda.setVisible(false);
        }
    }

    private JLabel getLblTitulo() {
        JLabel lblTitulo = new JLabel("Lista de Pacientes");
        lblTitulo.setBounds(40, 30, 300, 35);
        lblTitulo.setFont(new Font("Sylfaen", Font.BOLD, 24));
        lblTitulo.setForeground(Color.BLACK);
        return lblTitulo;
    }
    
    private JButton getBtnSalir() {
        JButton btnSalir = new JButton("");
        btnSalir.setBounds(1021, 0, 50, 50);
        btnSalir.setBorder(null);
        btnSalir.setHorizontalTextPosition(SwingConstants.CENTER);
        btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSalir.setIcon(new ImageIcon("src/Images/Iconos/otroLogoBorrar50x50.png"));
        btnSalir.setBackground(Color.WHITE);
        btnSalir.setForeground(Color.BLACK); // Cambiado a negro
        btnSalir.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 25));
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }
        });
        return btnSalir;
    }
    
    private JLabel getLblFiltros() {
        JLabel lblFiltros = new JLabel("Filtros:");
        lblFiltros.setBounds(40, 85, 100, 27);
        lblFiltros.setFont(new Font("Sylfaen", Font.PLAIN, 19));
        lblFiltros.setForeground(Color.BLACK); // Asegurar que sea negro
        return lblFiltros;
    }
    
    private JPanel getPanelBusqueda() {
        if (panelBusqueda == null) {
            panelBusqueda = new JPanel();
            panelBusqueda.setBounds(40, 110, 250, 45);
            panelBusqueda.setLayout(null);
            panelBusqueda.setBackground(Color.WHITE);
            panelBusqueda.setVisible(false); // Inicialmente oculto
            
            // Campo de texto dentro del panel
            txtBuscarNombre = new JTextField();
            txtBuscarNombre.setBounds(0, 0, 250, 30);
            txtBuscarNombre.setBackground(Color.WHITE);
            txtBuscarNombre.setFont(new Font("Sylfaen", Font.PLAIN, 17));
            txtBuscarNombre.setBorder(null);
            txtBuscarNombre.putClientProperty("JTextField.placeholderText", "Buscar por nombre...");
            
            // Listener para filtrar automáticamente
            txtBuscarNombre.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    aplicarFiltros();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    aplicarFiltros();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    aplicarFiltros();
                }
            });
            
            // Listener para perder el foco
            txtBuscarNombre.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    if (txtBuscarNombre.getText().isEmpty()) {
                        panelBusqueda.setVisible(false);
                    }
                }
            });
            
            // Separador dentro del panel
            JSeparator separator = new JSeparator();
            separator.setBounds(0, 32, 250, 2);
            separator.setForeground(Color.BLACK);
            
            panelBusqueda.add(txtBuscarNombre);
            panelBusqueda.add(separator);
        }
        return panelBusqueda;
    }
    
    private JButton getBtnBuscarLupa() {
        if (btnBuscarLupa == null) {
            btnBuscarLupa = new JButton("");
            btnBuscarLupa.setBounds(40, 120, 40, 30);
            btnBuscarLupa.setBackground(Color.WHITE);
            btnBuscarLupa.setBorder(null);
            // Configurar icono de lupa
            try {
                btnBuscarLupa.setIcon(new ImageIcon("src/Images/Iconos/LupaPequena.png"));
            } catch (Exception e) {
                // Si no hay imagen, usar texto
                btnBuscarLupa.setText("🔍");
                btnBuscarLupa.setFont(new Font("Sylfaen", Font.PLAIN, 16));
                btnBuscarLupa.setForeground(Color.BLACK);
            }
            btnBuscarLupa.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    panelBusqueda.setVisible(true);
                    panelBusqueda.setLocation(40, 120); // Posición justo debajo de la lupa
                    txtBuscarNombre.requestFocus();
                }
            });
        }
        return btnBuscarLupa;
    }
    
    private JLabel getLblBuscarNombre() {
        if (lblBuscarNombre == null) {
            lblBuscarNombre = new JLabel("Buscar por nombre:");
            lblBuscarNombre.setBounds(40, 100, 150, 20);
            lblBuscarNombre.setFont(new Font("Sylfaen", Font.PLAIN, 14));
            lblBuscarNombre.setForeground(Color.BLACK); // Cambiado a negro
        }
        return lblBuscarNombre;
    }
    
    private JLabel getLblSexo() {
        if (lblSexo == null) {
            lblSexo = new JLabel("Sexo:");
            lblSexo.setBounds(300, 100, 150, 20);
            lblSexo.setFont(new Font("Sylfaen", Font.PLAIN, 14));
            lblSexo.setForeground(Color.BLACK); // Cambiado a negro
        }
        return lblSexo;
    }
    
    private JLabel getLblEnfermedad() {
        if (lblEnfermedad == null) {
            lblEnfermedad = new JLabel("Enfermedad:");
            lblEnfermedad.setBounds(470, 100, 150, 20);
            lblEnfermedad.setFont(new Font("Sylfaen", Font.PLAIN, 14));
            lblEnfermedad.setForeground(Color.BLACK); // Cambiado a negro
        }
        return lblEnfermedad;
    }
    
    private JComboBox<String> getComboSexo() {
        if (comboSexo == null) {
            comboSexo = new JComboBox<>();
            comboSexo.setBounds(300, 120, 150, 30);
            comboSexo.setFont(new Font("Sylfaen", Font.PLAIN, 17));
            comboSexo.setBackground(Color.WHITE);
            comboSexo.setForeground(Color.BLACK); // Cambiado a negro
            comboSexo.addItem("Todos");
            comboSexo.addItem("M");
            comboSexo.addItem("F");
            comboSexo.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    aplicarFiltros();
                }
            });
        }
        return comboSexo;
    }
    
    private JComboBox<String> getComboEnfermedad() {
        if (comboEnfermedad == null) {
            comboEnfermedad = new JComboBox<>();
            comboEnfermedad.setBounds(470, 120, 200, 30);
            comboEnfermedad.setFont(new Font("Sylfaen", Font.PLAIN, 17));
            comboEnfermedad.setBackground(Color.WHITE);
            comboEnfermedad.setForeground(Color.BLACK); // Cambiado a negro
            comboEnfermedad.addItem("Todas");
            // Aquí deberías cargar las enfermedades disponibles
            // Por ejemplo: 
            // for (Enfermedad enf : Minsap.getEnfermedadesActuales()) {
            //     comboEnfermedad.addItem(enf.getNombre());
            // }
            comboEnfermedad.addItem("Gripe");
            comboEnfermedad.addItem("COVID-19");
            comboEnfermedad.addItem("Dengue");
            comboEnfermedad.addItem("Zika");
            comboEnfermedad.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    aplicarFiltros();
                }
            });
        }
        return comboEnfermedad;
    }
    
    private JButton getBtnLimpiarFiltros() {
        if (btnLimpiarFiltros == null) {
            btnLimpiarFiltros = new JButton("Limpiar Filtros");
            btnLimpiarFiltros.setBounds(690, 120, 150, 30);
            btnLimpiarFiltros.setFont(new Font("Sylfaen", Font.PLAIN, 16));
            btnLimpiarFiltros.setBackground(Colores.getAzulLogin());
            btnLimpiarFiltros.setForeground(Color.BLACK); // Cambiado a negro para mejor contraste
            btnLimpiarFiltros.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    limpiarFiltros();
                }
            });
        }
        return btnLimpiarFiltros;
    }
    
    private JButton getBtnExportarPDF() {
        if (btnExportarPDF == null) {
            btnExportarPDF = new JButton("");
            btnExportarPDF.setBounds(970, 30, 50, 50);
            //btnExportarPDF.setBackground(Color.PINK);
            btnExportarPDF.setBorder(null);
            btnExportarPDF.setForeground(Color.BLACK);
            btnExportarPDF.setFont(new Font("Sylfaen", Font.PLAIN, 16));
            
            // Configurar icono de PDF
            try {
                btnExportarPDF.setIcon(new ImageIcon("src/Images/Iconos/pDF.png"));
                btnExportarPDF.setText("");
            } catch (Exception e) {
                // Si no hay imagen, usar texto
                btnExportarPDF.setText("PDF");
            }
            
            btnExportarPDF.setToolTipText("Exportar a PDF");
            btnExportarPDF.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    exportarAPDF();
                }
            });
        }
        return btnExportarPDF;
    }
    
    private void exportarAPDF() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Reporte PDF");
        
        // Filtrar solo archivos PDF
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos PDF (*.pdf)", "pdf");
        fileChooser.setFileFilter(filter);
        
        // Sugerir nombre por defecto
        String defaultFileName = "reporte_pacientes_" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + ".pdf";
        fileChooser.setSelectedFile(new File(defaultFileName));
        
        int userSelection = fileChooser.showSaveDialog(this);
        
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            
            // Asegurar que tenga extensión .pdf
            String filePath = fileToSave.getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".pdf")) {
                filePath += ".pdf";
            }
            
            // Verificar si el archivo ya existe
            File archivo = new File(filePath);
            if (archivo.exists()) {
                int respuesta = javax.swing.JOptionPane.showConfirmDialog(this,
                    "El archivo ya existe. ¿Desea reemplazarlo?",
                    "Archivo existente",
                    javax.swing.JOptionPane.YES_NO_OPTION);
                
                if (respuesta != javax.swing.JOptionPane.YES_OPTION) {
                    return;
                }
            }
            
            // Verificar permisos de escritura
            if (archivo.exists() && !archivo.canWrite()) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "No tiene permisos para escribir en este archivo.\n" +
                    "Seleccione otra ubicación o verifique los permisos.",
                    "Error de permisos",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            try {
                // Verificar que el directorio padre exista
                File directorioPadre = archivo.getParentFile();
                if (directorioPadre != null && !directorioPadre.exists()) {
                    int crearDir = javax.swing.JOptionPane.showConfirmDialog(this,
                        "La carpeta no existe. ¿Desea crearla?",
                        "Carpeta no encontrada",
                        javax.swing.JOptionPane.YES_NO_OPTION);
                    
                    if (crearDir == javax.swing.JOptionPane.YES_OPTION) {
                        if (!directorioPadre.mkdirs()) {
                            throw new IOException("No se pudo crear la carpeta: " + directorioPadre.getAbsolutePath());
                        }
                    } else {
                        return;
                    }
                }
                
                crearPDF(filePath);
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Reporte exportado exitosamente a:\n" + filePath, 
                    "Exportación Exitosa", 
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException ex) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Error al crear el archivo PDF:\n" + ex.getMessage(), 
                    "Error de Archivo", 
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            } catch (DocumentException ex) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Error al generar el PDF:\n" + ex.getMessage(), 
                    "Error de Documento", 
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Error inesperado:\n" + ex.getMessage(), 
                    "Error", 
                    javax.swing.JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }
    
    private void crearPDF(String filePath) throws DocumentException, FileNotFoundException {
        Document document = new Document(PageSize.A4.rotate()); // Paisaje para mejor visualización
        
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            PdfWriter.getInstance(document, fos);
            
            document.open();
            
            // Título del documento
            Paragraph titulo = new Paragraph("Reporte de Pacientes", 
                new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 18, com.itextpdf.text.Font.BOLD));
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            titulo.setSpacingAfter(20);
            document.add(titulo);
            
            // Información de filtros aplicados
            String filtrosInfo = "Filtros aplicados: ";
            if (!txtBuscarNombre.getText().isEmpty()) {
                filtrosInfo += "Nombre contiene '" + txtBuscarNombre.getText() + "'";
            }
            if (!comboSexo.getSelectedItem().equals("Todos")) {
                filtrosInfo += (filtrosInfo.endsWith(": ") ? "" : ", ") + "Sexo: " + comboSexo.getSelectedItem();
            }
            if (!comboEnfermedad.getSelectedItem().equals("Todas")) {
                filtrosInfo += (filtrosInfo.endsWith(": ") ? "" : ", ") + "Enfermedad: " + comboEnfermedad.getSelectedItem();
            }
            if (filtrosInfo.equals("Filtros aplicados: ")) {
                filtrosInfo = "Sin filtros aplicados";
            }
            
            Paragraph filtros = new Paragraph(filtrosInfo, 
                new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 10, com.itextpdf.text.Font.ITALIC));
            filtros.setSpacingAfter(15);
            document.add(filtros);
            
            // Información de fecha y cantidad
            Paragraph info = new Paragraph("Fecha: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + 
                                          " | Total de pacientes: " + listaFiltrada.size(), 
                new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 10));
            info.setSpacingAfter(20);
            document.add(info);
            
            // Crear tabla
            PdfPTable tabla = new PdfPTable(modeloTabla.getColumnCount());
            tabla.setWidthPercentage(100);
            
            // Agregar encabezados
            String[] columnas = {"Nombre", "CI", "Correo", "Teléfono", "Dirección", "Enfermedades", "Sexo", "Fecha Diagnóstico"};
            for (String columna : columnas) {
                PdfPCell cell = new PdfPCell(new Phrase(columna));
                cell.setBackgroundColor(new com.itextpdf.text.BaseColor(Colores.getAzulLogin().getRGB()));
                cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                cell.setPadding(5);
                tabla.addCell(cell);
            }
            
            // Agregar datos
            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                for (int j = 0; j < modeloTabla.getColumnCount(); j++) {
                    Object value = modeloTabla.getValueAt(i, j);
                    String cellValue = (value != null) ? value.toString() : "";
                    PdfPCell cell = new PdfPCell(new Phrase(cellValue));
                    cell.setPadding(5);
                    
                    // Alternar colores de filas para mejor legibilidad
                    if (i % 2 == 0) {
                        cell.setBackgroundColor(new com.itextpdf.text.BaseColor(240, 240, 240));
                    }
                    
                    tabla.addCell(cell);
                }
            }
            
            document.add(tabla);
            
            // Pie de página
            Paragraph footer = new Paragraph("\n\nGenerado por Sistema de Gestión de Pacientes", 
                new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 8));
            footer.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(footer);
            
            document.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("No se pudo crear el archivo. Verifique:\n" +
                                           "1. Tiene permisos de escritura en la carpeta\n" +
                                           "2. El archivo no está abierto en otro programa\n" +
                                           "3. La ruta es válida: " + filePath);
        } catch (IOException e) {
            throw new DocumentException("Error de E/S al escribir el PDF: " + e.getMessage());
        }
    }
    
    private void limpiarFiltros() {
        txtBuscarNombre.setText("");
        comboSexo.setSelectedIndex(0);
        comboEnfermedad.setSelectedIndex(0);
        listaFiltrada = new ArrayList<>(listaPacientes);
        cargarDatosEnTabla();
        panelBusqueda.setVisible(false);
    }
    
    private JScrollPane getScrollPaneTabla() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(40, 180, 990, 340);
        scrollPane.setBorder(new LineBorder(Color.BLACK));
        
        // Crear modelo de tabla
        String[] columnas = {"Nombre", "CI", "Correo", "Teléfono", "Dirección", "Enfermedades", "Sexo"," Fecha Diagnóstico"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer la tabla no editable
            }
        };
        
        tablaPacientes = new JTable(modeloTabla);
        tablaPacientes.setFont(new Font("Sylfaen", Font.PLAIN, 14));
        tablaPacientes.setForeground(Color.BLACK); // Texto de la tabla en negro
        tablaPacientes.setRowHeight(25);
        
        // Encabezado de la tabla con letras negras sobre fondo azul
        tablaPacientes.getTableHeader().setFont(new Font("Sylfaen", Font.BOLD, 15));
        tablaPacientes.getTableHeader().setBackground(Colores.getAzulLogin());
        tablaPacientes.getTableHeader().setForeground(Color.BLACK); // Cambiado a negro
        
        tablaPacientes.setSelectionBackground(Colores.getAzulLogin().brighter());
        
        // Configurar el sorter para búsquedas rápidas
        sorter = new TableRowSorter<>(modeloTabla);
        tablaPacientes.setRowSorter(sorter);
        
        scrollPane.setViewportView(tablaPacientes);
        return scrollPane;
    }
    
    private JSeparator getSeparatorBuscar() {
        JSeparator separator = new JSeparator();
        separator.setBounds(40, 155, 250, 2);
        separator.setForeground(Color.BLACK);
        return separator;
    }
    
    private String normalizarTexto(String texto) {
        if (texto == null) return "";
        
        // Normalizar el texto para descomponer los caracteres con diacríticos
        texto = java.text.Normalizer.normalize(texto, java.text.Normalizer.Form.NFD);
        
        // Eliminar los caracteres diacríticos (tildes, diéresis, etc.)
        texto = texto.replaceAll("\\p{M}", "");
        System.out.println("para commit");
        return texto;
    }
}