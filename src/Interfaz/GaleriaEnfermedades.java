package Interfaz;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.border.LineBorder;

import Auxiliar.Sistema;
import Salud.Minsap;
import Utiles.Enfermedad;
import Utiles.WrapLayout;

public class GaleriaEnfermedades extends JDialog {
	
	private ArrayList<Enfermedad> enfermedades = Minsap.getEnfermedadesActuales();

    public GaleriaEnfermedades() {
        setUndecorated(true);
        setBounds(296, 164, 1070, 558);

        JPanel contenido = new JPanel(new BorderLayout());
        contenido.setBorder(null);
        contenido.setBackground(Color.WHITE);

        GaleriaEnfermedadesPanel galeria = new GaleriaEnfermedadesPanel();
        JScrollPane scroll = new JScrollPane(galeria);
        scroll.setBackground(Color.WHITE);
        
        JButton btnNewButton = new JButton("Cerrar");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }
        });
        galeria.add(btnNewButton);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setBorder(null);

        contenido.add(scroll, BorderLayout.CENTER);
        setContentPane(contenido);
    }
}

// Clase auxiliar: GaleriaEnfermedadesPanel
class GaleriaEnfermedadesPanel extends JPanel {

	private ArrayList<Enfermedad> enfermedades = Minsap.getEnfermedadesActuales();
	
    public GaleriaEnfermedadesPanel() {
        setLayout(new WrapLayout(FlowLayout.LEFT, 20, 20));
        setBackground(new Color(250, 245, 240));

        for (Enfermedad enfermedad : enfermedades) {
            add(crearTarjeta(enfermedad));
        }
    }

    private JPanel crearTarjeta(final Enfermedad enfermedad) {
        final JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BorderLayout());
        tarjeta.setPreferredSize(new Dimension(220, 320));
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(BorderFactory.createLineBorder(new Color(181, 149, 110), 2, true));

        // Crear icono para la enfermedad (puedes cambiar la imagen según lo que tengas)
        ImageIcon iconoOriginal = enfermedad.getImage(); // Ajusta la ruta
        // Si no tienes imagen, puedes crear un icono simple con un JLabel con texto
        JLabel imagen;
        
        if (iconoOriginal != null && new java.io.File(enfermedad.getRuta()).exists()) {
            Image imgEscalada = iconoOriginal.getImage().getScaledInstance(200, 240, Image.SCALE_SMOOTH);
            imagen = new JLabel(new ImageIcon(imgEscalada));
        } else {
            // Crear un panel simple con el símbolo médico
            imagen = new JLabel("⚕", SwingConstants.CENTER);
            imagen.setFont(new Font("Segoe UI", Font.BOLD, 80));
            imagen.setForeground(new Color(70, 130, 180)); // Azul acero
        }
        
        imagen.setHorizontalAlignment(JLabel.CENTER);

        JLabel nombreEnfermedad = new JLabel(enfermedad.getNombre(), SwingConstants.CENTER);
        nombreEnfermedad.setFont(new Font("Segoe UI", Font.BOLD, 14));
        nombreEnfermedad.setHorizontalAlignment(JLabel.CENTER);
        
        // Panel para información adicional
        JPanel infoPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        JLabel codigoLabel = new JLabel("Nombre: " + enfermedad.getNombre(), SwingConstants.CENTER);
        codigoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        
        // Aquí puedes agregar más información según los métodos que tenga la clase Enfermedad
        // Por ejemplo: tipo, gravedad, síntomas, etc.
        // JLabel tipoLabel = new JLabel("Tipo: " + enfermedad.getTipo(), SwingConstants.CENTER);
        // tipoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        
        infoPanel.add(nombreEnfermedad);
        infoPanel.add(codigoLabel);
        // infoPanel.add(tipoLabel); // Descomentar si existe el método getTipo()

        tarjeta.add(imagen, BorderLayout.CENTER);
        tarjeta.add(infoPanel, BorderLayout.SOUTH);

        tarjeta.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // Crear y mostrar el diálogo con información detallada de la enfermedad
                InfoEnfermedad infoDialog = new InfoEnfermedad(enfermedad);
                infoDialog.setVisible(true);
            }
            
            public void mouseEntered(MouseEvent e) {
                tarjeta.setBackground(new Color(240, 250, 250)); // Color al pasar el mouse
            }
            
            public void mouseExited(MouseEvent e) {
                tarjeta.setBackground(Color.WHITE); // Color normal
            }
        });

        return tarjeta;
    }
}

class InfoEnfermedad extends JDialog {
    
    public InfoEnfermedad(Enfermedad enfermedad) {
        setTitle("Información de Enfermedad");
        setModal(true);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        JPanel contenido = new JPanel();
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Título
        JLabel titulo = new JLabel(enfermedad.getNombre(), SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Información detallada
        JPanel infoPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        infoPanel.add(crearInfoLinea("Nombre:", enfermedad.getNombre()));
        // infoPanel.add(crearInfoLinea("Tipo:", enfermedad.getTipo()));
        // infoPanel.add(crearInfoLinea("Gravedad:", enfermedad.getGravedad()));
        // infoPanel.add(crearInfoLinea("Descripción:", enfermedad.getDescripcion()));
        // infoPanel.add(crearInfoLinea("Tratamiento:", enfermedad.getTratamiento()));
        // infoPanel.add(crearInfoLinea("Síntomas:", enfermedad.getSintomas()));
        
        // Botón cerrar
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        contenido.add(titulo);
        contenido.add(infoPanel);
        contenido.add(btnCerrar);
        
        add(contenido, BorderLayout.CENTER);
    }
    
    private JPanel crearInfoLinea(String etiqueta, String valor) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelEtiqueta = new JLabel(etiqueta);
        labelEtiqueta.setFont(new Font("Segoe UI", Font.BOLD, 12));
        JLabel labelValor = new JLabel(valor != null ? valor : "No disponible");
        labelValor.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        panel.add(labelEtiqueta);
        panel.add(labelValor);
        return panel;
    }
}