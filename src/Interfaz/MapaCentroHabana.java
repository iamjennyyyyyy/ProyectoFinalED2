package Interfaz;

import javax.swing.*;

import cu.edu.cujae.ceis.graph.vertex.Vertex;
import Salud.ConsejoPopular;
import Salud.DireccionMunicipal;
import Sistema.Inicializar;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MapaCentroHabana extends JDialog {
    
    // Modificación en la clase NodoMapa para poner los indicadores al lado del nombre
    class NodoMapa {
        ConsejoPopular consejo;
        Shape forma;
        Color color;
        Point posicionTexto;
        
        NodoMapa(ConsejoPopular consejo, Shape forma, Color color, Point posicionTexto) {
            this.consejo = consejo;
            this.forma = forma;
            this.color = color;
            this.posicionTexto = posicionTexto;
        }
        
        boolean contienePunto(Point p) {
            return forma.contains(p);
        }
        
        void dibujar(Graphics2D g2) {
            // Rellenar la forma
            g2.setColor(color);
            g2.fill(forma);
            
            // Dibujar borde
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(1));
            g2.draw(forma);
            
            // Dibujar nombre y estado juntos
            dibujarNombreYEstado(g2);
        }
        
        void dibujarNombreYEstado(Graphics2D g2) {
            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Arial", Font.BOLD, 10));
            
            FontMetrics fm = g2.getFontMetrics();
            String texto = consejo.getNombre();
            int textoAncho = fm.stringWidth(texto);
            int textoAlto = fm.getHeight();
            
            // Calcular la posición del texto (centrado en posicionTexto)
            int nombreX = posicionTexto.x - textoAncho/2;
            int nombreY = posicionTexto.y;
            
            // Calcular posición del indicador basado en el nombre
            int indicadorSize = 8;
            int indicadorX, indicadorY;
            
            if (consejo.getEstado() != null) {
                switch (consejo.getEstado()) {
                    case Epidemia:
                        // Punto rojo para Epidemia - al lado izquierdo del nombre
                        g2.setColor(Color.RED);
                        indicadorX = nombreX - indicadorSize - 4; // 4px de separación
                        indicadorY = nombreY - indicadorSize/2;
                        g2.fillOval(indicadorX, indicadorY, indicadorSize, indicadorSize);
                        g2.setColor(Color.BLACK);
                        g2.setStroke(new BasicStroke(1));
                        g2.drawOval(indicadorX, indicadorY, indicadorSize, indicadorSize);
                        break;
                        
                    case Alerta_Epidemica:
                        // Punto amarillo para Alerta Epidemiológica - al lado izquierdo del nombre
                        g2.setColor(Color.YELLOW);
                        indicadorX = nombreX - indicadorSize - 4; // 4px de separación
                        indicadorY = nombreY - indicadorSize/2;
                        g2.fillOval(indicadorX, indicadorY, indicadorSize, indicadorSize);
                        g2.setColor(Color.BLACK);
                        g2.setStroke(new BasicStroke(1));
                        g2.drawOval(indicadorX, indicadorY, indicadorSize, indicadorSize);
                        break;
                        
                    case Normal:
                        // Para Normal no mostramos indicador
                        break;
                }
            }
            
            // Dibujar el nombre del consejo
            g2.setColor(Color.BLACK);
            g2.drawString(texto, nombreX, nombreY);
        }
    }
    
    private List<NodoMapa> nodos = new ArrayList<>();
    private LinkedList<ConsejoPopular> consejos;
    
    public MapaCentroHabana(LinkedList<ConsejoPopular> consejos) {
        super((JFrame)null, "Mapa de Centro Habana", true);
        this.consejos = consejos;
        
        crearNodosDesdeLista();
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        final JPanel mapaPanel = crearPanelMapa();
        mapaPanel.setBackground(new Color(245, 245, 245));
        
        agregarListeners(mapaPanel);
        
        add(mapaPanel, BorderLayout.CENTER);
        
        JPanel infoPanel = crearPanelInformacion();
        add(infoPanel, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
    }
    
    private void crearNodosDesdeLista() {
        double escala = 0.9;
        
        if (consejos == null || consejos.isEmpty()) {
            return;
        }
        
        // Crear formas basadas en el SVG proporcionado
        for (int i = 0; i < consejos.size(); i++) {
            ConsejoPopular consejo = consejos.get(i);
            
            Shape forma = crearFormaSegunIndice(i, escala);
            Color color = generarColorSegunIndice(i);
            Point posTexto = calcularPosicionTexto(i, escala);
            
            // Ajustar la posición del texto para dar espacio al indicador
            posTexto.x += 10; // Mover 10px a la derecha
            
            NodoMapa nodo = new NodoMapa(consejo, forma, color, posTexto);
            nodos.add(nodo);
        }
    }
    
    private Shape crearFormaSegunIndice(int indice, double escala) {
        Path2D forma = new Path2D.Double();
        
        // Coordenadas originales del SVG (500x600 viewBox)
        // Aplicamos escala y ajustamos posición para centrar en el panel
        
        double offsetX = 50 * escala;  // Margen izquierdo
        double offsetY = 50 * escala;  // Margen superior
        
        // Diferentes formas según el índice (basadas en el SVG)
        switch (indice) {
            case 0: // Cayo Hueso
                forma.moveTo(offsetX + 50 * escala, offsetY + 240 * escala);
                forma.lineTo(offsetX + 230 * escala, offsetY + 255 * escala);
                forma.lineTo(offsetX + 235 * escala, offsetY + 365 * escala);
                forma.lineTo(offsetX + 55 * escala, offsetY + 385 * escala);
                break;
                
            case 1: // Dragones
                forma.moveTo(offsetX + 230 * escala, offsetY + 255 * escala);
                forma.lineTo(offsetX + 380 * escala, offsetY + 245 * escala);
                forma.lineTo(offsetX + 400 * escala, offsetY + 345 * escala);
                forma.lineTo(offsetX + 245 * escala, offsetY + 355 * escala);
                forma.lineTo(offsetX + 235 * escala, offsetY + 365 * escala);
                break;
                
            case 2: // Colón
                forma.moveTo(offsetX + 380 * escala, offsetY + 245 * escala);
                forma.lineTo(offsetX + 485 * escala, offsetY + 190 * escala);
                forma.lineTo(offsetX + 500 * escala, offsetY + 300 * escala);
                forma.lineTo(offsetX + 460 * escala, offsetY + 350 * escala);
                forma.lineTo(offsetX + 465 * escala, offsetY + 420 * escala);
                forma.lineTo(offsetX + 415 * escala, offsetY + 425 * escala);
                forma.lineTo(offsetX + 400 * escala, offsetY + 345 * escala);
                break;
                
            case 3: // Pueblo Nuevo
                forma.moveTo(offsetX + 55 * escala, offsetY + 385 * escala);
                forma.lineTo(offsetX + 235 * escala, offsetY + 365 * escala);
                forma.lineTo(offsetX + 255 * escala, offsetY + 490 * escala);
                forma.lineTo(offsetX + 285 * escala, offsetY + 530 * escala);
                forma.lineTo(offsetX + 150 * escala, offsetY + 595 * escala);
                forma.lineTo(offsetX + 125 * escala, offsetY + 480 * escala);
                break;
                
            case 4: // Los Sitios
                forma.moveTo(offsetX + 235 * escala, offsetY + 365 * escala);
                forma.lineTo(offsetX + 245 * escala, offsetY + 355 * escala);
                forma.lineTo(offsetX + 400 * escala, offsetY + 345 * escala);
                forma.lineTo(offsetX + 415 * escala, offsetY + 425 * escala);
                forma.lineTo(offsetX + 470 * escala, offsetY + 415 * escala);
                forma.lineTo(offsetX + 420 * escala, offsetY + 485 * escala);
                forma.lineTo(offsetX + 320 * escala, offsetY + 560 * escala);
                forma.lineTo(offsetX + 285 * escala, offsetY + 530 * escala);
                forma.lineTo(offsetX + 255 * escala, offsetY + 490 * escala);
                break;
        }
        
        forma.closePath();
        return forma;
    }
    
    private Color generarColorSegunIndice(int indice) {
        Color[] colores = {
            new Color(157, 180, 176),  // #9db4b0 - Cayo Hueso
            new Color(233, 211, 160),  // #e9d3a0 - Dragones
            new Color(233, 192, 166),  // #e9c0a6 - Colón
            new Color(196, 219, 180),  // #c4dbb4 - Pueblo Nuevo
            new Color(205, 193, 214),  // #cdc1d6 - Los Sitios
        };
        
        return colores[indice % colores.length];
    }
    
    private Point calcularPosicionTexto(int indice, double escala) {
        double offsetX = 50 * escala;
        double offsetY = 50 * escala;
        
        // Posiciones basadas en las coordenadas de texto del SVG
        switch (indice) {
            case 0: // Cayo Hueso - (140, 325)
                return new Point((int)(offsetX + 140 * escala), (int)(offsetY + 325 * escala));
            case 1: // Dragones - (315, 325)
                return new Point((int)(offsetX + 315 * escala), (int)(offsetY + 325 * escala));
            case 2: // Colón - (445, 315)
                return new Point((int)(offsetX + 445 * escala), (int)(offsetY + 315 * escala));
            case 3: // Pueblo Nuevo - (160, 500)
                return new Point((int)(offsetX + 160 * escala), (int)(offsetY + 500 * escala));
            case 4: // Los Sitios - (340, 485)
                return new Point((int)(offsetX + 340 * escala), (int)(offsetY + 485 * escala));
            default:
                return new Point((int)(offsetX + 250 * escala), (int)(offsetY + 300 * escala));
        }
    }
    
    private JPanel crearPanelMapa() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                    RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                
                g2.setColor(new Color(245, 245, 245));
                g2.fillRect(0, 0, getWidth(), getHeight());
                
                g2.setColor(new Color(44, 62, 80));
                g2.setFont(new Font("Arial", Font.BOLD, 18));
                String titulo = "MAPA DE CENTRO HABANA";
                FontMetrics fm = g2.getFontMetrics();
                int tituloX = (getWidth() - fm.stringWidth(titulo)) / 2;
                g2.drawString(titulo, tituloX, 30);
                
                g2.setFont(new Font("Arial", Font.PLAIN, 11));
                g2.setColor(Color.GRAY);
                String subtitulo = "Consejos Populares: " + (consejos != null ? consejos.size() : 0);
                int subtituloX = (getWidth() - g2.getFontMetrics().stringWidth(subtitulo)) / 2;
                g2.drawString(subtitulo, subtituloX, 50);
                
                int centroX = getWidth() / 2;
                int centroY = getHeight() / 2-200;
                int mapaAncho = 600;
                int mapaAlto = 500;
                
                AffineTransform originalTransform = g2.getTransform();
                
                // Centrar el mapa en el panel
                g2.translate(centroX - mapaAncho/2, centroY - mapaAlto/2 + 30);
                
                // Dibujar las formas
                for (NodoMapa nodo : nodos) {
                    nodo.dibujar(g2);
                }
                
                // Dibujar líneas de conexión entre consejos (si estuvieran definidas)
                // g2.setColor(new Color(100, 100, 100, 150));
                // g2.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                // Aquí podrías añadir las conexiones si tuvieras información sobre ellas
                
                g2.setTransform(originalTransform);
                
                // Dibujar leyenda actualizada
                dibujarLeyenda(g2);
                
//                // Dibujar borde alrededor del mapa
//                g2.setColor(new Color(200, 200, 200));
//                g2.setStroke(new BasicStroke(2));
//                g2.drawRect(centroX - mapaAncho/2 - 5, centroY - mapaAlto/2 + 25, mapaAncho + 10, mapaAlto + 10);
            }
            
            private void dibujarLeyenda(Graphics2D g2) {
                int leyendaY = getHeight() - 60;
                int leyendaX = 20;
                
                g2.setFont(new Font("Arial", Font.BOLD, 11));
                g2.setColor(Color.DARK_GRAY);
                g2.drawString("Leyenda:", leyendaX, leyendaY - 5);
                
                g2.setFont(new Font("Arial", Font.PLAIN, 10));
                
                // Epidemia (rojo)
                g2.setColor(Color.RED);
                g2.fillOval(leyendaX, leyendaY, 8, 8);
                g2.setColor(Color.BLACK);
                g2.drawOval(leyendaX, leyendaY, 8, 8);
                g2.drawString("Epidemia", leyendaX + 12, leyendaY + 6);
                
                // Alerta Epidemiológica (amarillo)
                g2.setColor(Color.YELLOW);
                g2.fillOval(leyendaX + 80, leyendaY, 8, 8);
                g2.setColor(Color.BLACK);
                g2.drawOval(leyendaX + 80, leyendaY, 8, 8);
                g2.drawString("Alerta Epidemiológica", leyendaX + 92, leyendaY + 6);
                
                // Normal (sin indicador)
                g2.setColor(Color.GRAY);
                g2.drawString("Normal: Sin indicador", leyendaX + 250, leyendaY + 6);
            }
            
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(700, 650);
            }
        };
    }
    
    private void agregarListeners(final JPanel mapaPanel) {
        mapaPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int centroX = mapaPanel.getWidth() / 2;
                int centroY = mapaPanel.getHeight() / 2-200;
                int mapaAncho = 600;
                int mapaAlto = 500;
                
                int relX = e.getX() - (centroX - mapaAncho/2);
                int relY = e.getY() - (centroY - mapaAlto/2 + 30);
                
                Point p = new Point(relX, relY);
                
                for (NodoMapa nodo : nodos) {
                    if (nodo.contienePunto(p)) {
                        mostrarInformacionConsejo(nodo.consejo);
                        return;
                    }
                }
                
                if (relX >= 0 && relX <= mapaAncho && relY >= 0 && relY <= mapaAlto) {
                    JOptionPane.showMessageDialog(MapaCentroHabana.this, 
                        "Selecciona un consejo popular haciendo clic dentro de él", 
                        "Información", 
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        mapaPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int centroX = mapaPanel.getWidth() / 2;
                int centroY = mapaPanel.getHeight() / 2-200;
                int mapaAncho = 600;
                int mapaAlto = 500;
                
                int relX = e.getX() - (centroX - mapaAncho/2);
                int relY = e.getY() - (centroY - mapaAlto/2 + 30);
                
                Point p = new Point(relX, relY);
                
                for (NodoMapa nodo : nodos) {
                    if (nodo.contienePunto(p)) {
                        mapaPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        return;
                    }
                }
                mapaPanel.setCursor(Cursor.getDefaultCursor());
            }
        });
    }
    
    private JPanel crearPanelInformacion() {
        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(new Color(240, 240, 240));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        JLabel infoLabel = new JLabel("Haz clic en cualquier consejo para ver información detallada");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        infoPanel.add(infoLabel);
        
        return infoPanel;
    }
    
    private void mostrarInformacionConsejo(ConsejoPopular consejo) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("<html><div style='width: 350px; padding: 10px;'>")
               .append("<h3 style='color: #2C3E50; text-align: center;'>")
               .append(consejo.getNombre())
               .append("</h3>")
               .append("<hr style='border: 1px solid #3498DB;'>")
               .append("<p><b>Código:</b> ").append(consejo.getCodigo()).append("</p>")
               .append("<p><b>Municipio:</b> ").append(consejo.getMunicipio()).append("</p>")
               .append("<p><b>Población Total:</b> ").append(consejo.getTotalPoblacion()).append(" habitantes</p>");
        
        if (consejo.getEstado() != null) {
            String estadoColor = "";
            switch (consejo.getEstado()) {
                case Epidemia: estadoColor = "color: red; font-weight: bold;"; break;
                case Alerta_Epidemica: estadoColor = "color: orange; font-weight: bold;"; break;
                case Normal: estadoColor = "color: green; font-weight: bold;"; break;
            }
            mensaje.append("<p><b>Estado Actual:</b> <span style='").append(estadoColor).append("'>")
                   .append(consejo.getEstado()).append("</span></p>");
        }
        
        if (consejo.getCasos() != null) {
            mensaje.append("<p><b>Casos Registrados:</b> ").append(consejo.getCasos().size()).append("</p>");
        }
        
        mensaje.append("</div></html>");
        
        JOptionPane.showMessageDialog(this, 
            mensaje.toString(), 
            "Información del Consejo Popular", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Usar la nueva función que creamos para Centro Habana
                DireccionMunicipal d = Inicializar.inicializarCentroHabana();
                LinkedList<ConsejoPopular> c = new LinkedList<>();
                for(Vertex v : d.getGrafo().getGrafo().getVerticesList()){
                    c.add((ConsejoPopular)v.getInfo());
                }
                
                MapaCentroHabana dialog = new MapaCentroHabana(c);
                dialog.setVisible(true);
            }
        });
    }
    
   
}