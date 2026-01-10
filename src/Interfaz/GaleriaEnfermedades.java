package Interfaz;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
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

        // Crear icono para la enfermedad
        ImageIcon iconoOriginal = enfermedad.getImage();
        JLabel imagen;
        
        if (iconoOriginal != null) {
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
        JPanel infoPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        JLabel nombreLabel = new JLabel(enfermedad.getNombre(), SwingConstants.CENTER);
        nombreLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        JLabel codigoLabel = new JLabel("Nombre: " + enfermedad.getNombre(), SwingConstants.CENTER);
        codigoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        
        infoPanel.add(nombreLabel);
        infoPanel.add(codigoLabel);

        tarjeta.add(imagen, BorderLayout.CENTER);
        tarjeta.add(infoPanel, BorderLayout.SOUTH);

        tarjeta.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // Crear y mostrar el diálogo con información detallada de la enfermedad
                // Asumo que InfoEnfermedad ya existe en tu proyecto
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