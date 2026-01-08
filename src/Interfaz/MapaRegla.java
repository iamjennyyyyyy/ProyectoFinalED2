package Interfaz;

import javax.swing.*;

import cu.edu.cujae.ceis.graph.vertex.Vertex;
import Salud.ConsejoPopular;
import Salud.DireccionMunicipal;
import Auxiliar.Inicializar;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MapaRegla extends JDialog {
    
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
    
    public MapaRegla(LinkedList<ConsejoPopular> consejos) {
        //super(padre, "Mapa de Consejos Populares", true);
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
        double escala = 0.6; // Escala ligeramente ajustada para las nuevas formas
        
        if (consejos == null || consejos.isEmpty()) {
            return;
        }
        
        // Ajustar las posiciones de texto para dar espacio a los indicadores
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
        int tipoForma = indice % 7;
        
        switch (tipoForma) {
            case 2: // Forma similar a Casablanca del HTML
                forma.moveTo(180 * escala, 230 * escala);
                forma.curveTo(150 * escala, 220 * escala, 140 * escala, 240 * escala, 160 * escala, 260 * escala);
                forma.lineTo(220 * escala, 280 * escala);
                forma.lineTo(250 * escala, 280 * escala);
                forma.lineTo(280 * escala, 300 * escala);
                forma.lineTo(300 * escala, 290 * escala);
                forma.lineTo(310 * escala, 310 * escala);
                forma.lineTo(330 * escala, 300 * escala);
                forma.lineTo(340 * escala, 315 * escala);
                forma.lineTo(360 * escala, 305 * escala);
                forma.lineTo(480 * escala, 390 * escala);
                forma.lineTo(450 * escala, 320 * escala);
                forma.lineTo(380 * escala, 230 * escala);
                forma.lineTo(300 * escala, 210 * escala);
                forma.lineTo(260 * escala, 210 * escala);
                break;
                
            case 1: // Forma similar a Guaicanamar del HTML
                forma.moveTo(360 * escala, 305 * escala);
                forma.lineTo(480 * escala, 390 * escala);
                forma.lineTo(490 * escala, 510 * escala);
                forma.lineTo(330 * escala, 630 * escala);
                forma.lineTo(270 * escala, 470 * escala);
                forma.lineTo(250 * escala, 430 * escala);
                forma.lineTo(310 * escala, 400 * escala);
                forma.curveTo(320 * escala, 370 * escala, 280 * escala, 360 * escala, 310 * escala, 330 * escala);
                forma.lineTo(360 * escala, 305 * escala);
                break;
                
            case 0: // Forma similar a Loma Modelo del HTML
                forma.moveTo(270 * escala, 470 * escala);
                forma.lineTo(330 * escala, 630 * escala);
                forma.lineTo(100 * escala, 580 * escala);
                forma.curveTo(50 * escala, 500 * escala, 120 * escala, 400 * escala, 140 * escala, 380 * escala);
                forma.lineTo(170 * escala, 460 * escala);
                forma.lineTo(200 * escala, 440 * escala);
                forma.lineTo(250 * escala, 430 * escala);
                break;
                
//            case 3: // Forma alternativa 1 (polígono irregular)
//                forma.moveTo(150 * escala, 200 * escala);
//                forma.lineTo(300 * escala, 150 * escala);
//                forma.lineTo(400 * escala, 200 * escala);
//                forma.lineTo(380 * escala, 300 * escala);
//                forma.lineTo(250 * escala, 350 * escala);
//                forma.lineTo(150 * escala, 280 * escala);
//                break;
//                
//            case 4: // Forma alternativa 2 (con curvas)
//                forma.moveTo(200 * escala, 100 * escala);
//                forma.curveTo(180 * escala, 120 * escala, 170 * escala, 150 * escala, 200 * escala, 170 * escala);
//                forma.curveTo(230 * escala, 190 * escala, 270 * escala, 180 * escala, 300 * escala, 150 * escala);
//                forma.curveTo(280 * escala, 130 * escala, 250 * escala, 110 * escala, 220 * escala, 100 * escala);
//                break;
//                
//            case 5: // Forma alternativa 3 (trapezoidal irregular)
//                forma.moveTo(100 * escala, 300 * escala);
//                forma.lineTo(250 * escala, 250 * escala);
//                forma.lineTo(350 * escala, 300 * escala);
//                forma.lineTo(300 * escala, 400 * escala);
//                forma.lineTo(150 * escala, 380 * escala);
//                break;
//                
//            case 6: // Forma alternativa 4 (similar a un hexágono irregular)
//                forma.moveTo(350 * escala, 100 * escala);
//                forma.lineTo(450 * escala, 150 * escala);
//                forma.lineTo(450 * escala, 250 * escala);
//                forma.lineTo(350 * escala, 300 * escala);
//                forma.lineTo(250 * escala, 250 * escala);
//                forma.lineTo(250 * escala, 150 * escala);
//                break;
        }
        
        forma.closePath();
        return forma;
    }
    
    private Color generarColorSegunIndice(int indice) {
        Color[] colores = {
            new Color(230, 176, 176),   // Rojo suave
            new Color(163, 198, 214),   // Azul suave
            new Color(163, 214, 163),   // Verde suave
            new Color(230, 214, 163),   // Amarillo suave
            new Color(176, 163, 214),   // Púrpura suave
            new Color(214, 181, 163),   // Naranja suave
            new Color(214, 163, 214),   // Rosa suave
            new Color(176, 219, 176),   // Verde claro
            new Color(219, 198, 176),   // Beige
            new Color(176, 198, 219)    // Azul claro
        };
        
        return colores[indice % colores.length];
    }
    
    private Point calcularPosicionTexto(int indice, double escala) {
        int tipoForma = indice % 7;
        
        switch (tipoForma) {
            case 2: return new Point((int)(320 * escala), (int)(270 * escala)); // Casablanca
            case 1: return new Point((int)(390 * escala), (int)(460 * escala)); // Guaicanamar
            case 0: return new Point((int)(230 * escala), (int)(550 * escala)); // Loma Modelo
//            case 3: return new Point((int)(250 * escala), (int)(250 * escala)); // Forma alternativa 1
//            case 4: return new Point((int)(250 * escala), (int)(140 * escala)); // Forma alternativa 2
//            case 5: return new Point((int)(225 * escala), (int)(325 * escala)); // Forma alternativa 3
//            case 6: return new Point((int)(350 * escala), (int)(200 * escala)); // Forma alternativa 4
            default: return new Point((int)(400 * escala), (int)(400 * escala));
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
                String titulo = "MAPA CONSEJOS POPULARES REGLA";
                FontMetrics fm = g2.getFontMetrics();
                int tituloX = (getWidth() - fm.stringWidth(titulo)) / 2;
                g2.drawString(titulo, tituloX, 30);
                
                g2.setFont(new Font("Arial", Font.PLAIN, 11));
                g2.setColor(Color.GRAY);
                String subtitulo = "Total: " + (consejos != null ? consejos.size() : 0) + " consejos";
                int subtituloX = (getWidth() - g2.getFontMetrics().stringWidth(subtitulo)) / 2;
                g2.drawString(subtitulo, subtituloX, 50);
                
                int centroX = getWidth() / 2+100;
                int centroY = getHeight() / 2;
                int mapaAncho = 600;
                int mapaAlto = 500; // Aumentado para las formas más altas
                
                AffineTransform originalTransform = g2.getTransform();
                
                g2.translate(centroX - mapaAncho/2, centroY - mapaAlto/2 + 30);
                
                for (NodoMapa nodo : nodos) {
                    nodo.dibujar(g2);
                }
                
                g2.setTransform(originalTransform);
                
                // Dibujar leyenda actualizada
                dibujarLeyenda(g2);
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
                return new Dimension(700, 650); // Aumentado para acomodar formas más grandes
            }
        };
    }
    
    private void agregarListeners(final JPanel mapaPanel) {
        mapaPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int centroX = mapaPanel.getWidth() / 2+100;
                int centroY = mapaPanel.getHeight() / 2;
                int mapaAncho = 600;
                int mapaAlto = 500; // Actualizado
                
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
                    JOptionPane.showMessageDialog(MapaRegla.this, 
                        "Selecciona un consejo popular haciendo clic dentro de el", 
                        "Informacion", 
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        mapaPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int centroX = mapaPanel.getWidth() / 2+100;
                int centroY = mapaPanel.getHeight() / 2;
                int mapaAncho = 600;
                int mapaAlto = 500; // Actualizado
                
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
        
        JLabel infoLabel = new JLabel("Haz clic en cualquier consejo para ver informacion detallada");
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
               .append("<p><b>Codigo:</b> ").append(consejo.getCodigo()).append("</p>")
               .append("<p><b>Municipio:</b> ").append(consejo.getMunicipio()).append("</p>")
               .append("<p><b>Poblacion Total:</b> ").append(consejo.getTotalPoblacion()).append(" habitantes</p>");
        
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
            "Informacion del Consejo Popular", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                DireccionMunicipal d = Inicializar.inicializarRegla();
                LinkedList<ConsejoPopular> c = new LinkedList<>();
                for(Vertex v : d.getGrafo().getGrafo().getVerticesList()){
                    c.addFirst((ConsejoPopular)v.getInfo());
                    
               }
                
                //MapaRegla dialog = new MapaRegla(c);
                //dialog.setVisible(true);
            }
        });
    }
}