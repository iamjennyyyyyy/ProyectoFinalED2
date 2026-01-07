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

public class MapaPlaza extends JDialog {
    
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
    
    public MapaPlaza(LinkedList<ConsejoPopular> consejos) {
        super((JFrame)null, "Mapa de Consejos Populares", true);
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
        if (consejos == null || consejos.isEmpty()) {
            return;
        }
        
        // Ajustar las posiciones de texto para dar espacio a los indicadores
        for (int i = 0; i < consejos.size(); i++) {
            ConsejoPopular consejo = consejos.get(i);
            
            Shape forma = crearFormaSVG(i);
            Color color = generarColorSVG(i);
            Point posTexto = calcularPosicionTexto(i);
            
            NodoMapa nodo = new NodoMapa(consejo, forma, color, posTexto);
            nodos.add(nodo);
        }
    }
    
    private Shape crearFormaSVG(int indice) {
        Path2D forma = new Path2D.Double();
        
        // Escala ajustada para que quepa completamente
        double escala = 0.75;
        
        switch (indice) {
            case 7: // Rampa
                forma.moveTo(230 * escala, 40 * escala);
                forma.lineTo(380 * escala, 90 * escala);
                forma.lineTo(380 * escala, 140 * escala);
                forma.lineTo(250 * escala, 210 * escala);
                break;
                
            case 6: // Vedado Malecón
                forma.moveTo(120 * escala, 130 * escala);
                forma.lineTo(230 * escala, 40 * escala);
                forma.lineTo(250 * escala, 210 * escala);
                forma.lineTo(160 * escala, 250 * escala);
                break;
                
            case 3: // Carmelo
                forma.moveTo(70 * escala, 280 * escala);
                forma.lineTo(120 * escala, 130 * escala);
                forma.lineTo(160 * escala, 250 * escala);
                forma.lineTo(140 * escala, 280 * escala);
                forma.lineTo(110 * escala, 330 * escala);
                break;
                
            case 5: // Vedado
                forma.moveTo(250 * escala, 210 * escala);
                forma.lineTo(260 * escala, 180 * escala);
                forma.lineTo(300 * escala, 180 * escala);
                forma.lineTo(290 * escala, 355 * escala);
                forma.lineTo(270 * escala, 380 * escala);
                forma.lineTo(160 * escala, 250 * escala);
                break;
                
            case 4: // Príncipe
                forma.moveTo(300 * escala, 180 * escala);
                forma.lineTo(380 * escala, 140 * escala);
                forma.lineTo(400 * escala, 150 * escala);
                forma.lineTo(380 * escala, 340 * escala);
                forma.lineTo(320 * escala, 320 * escala);
                forma.lineTo(290 * escala, 355 * escala);
                break;
                
            case 2: // Colón Vedado
                forma.moveTo(110 * escala, 330 * escala);
                forma.lineTo(160 * escala, 250 * escala);
                forma.lineTo(270 * escala, 380 * escala);
                forma.lineTo(250 * escala, 430 * escala);
                forma.lineTo(200 * escala, 440 * escala);
                forma.lineTo(150 * escala, 420 * escala);
                forma.lineTo(140 * escala, 370 * escala);
                break;
                
            case 1: // Plaza
                forma.moveTo(270 * escala, 380 * escala);
                forma.lineTo(290 * escala, 355 * escala);
                forma.lineTo(320 * escala, 320 * escala);
                forma.lineTo(380 * escala, 340 * escala);
                forma.lineTo(320 * escala, 530 * escala);
                forma.lineTo(260 * escala, 500 * escala);
                forma.lineTo(250 * escala, 430 * escala);
                break;
                
            case 0: // Puentes Grandes
                forma.moveTo(140 * escala, 370 * escala);
                forma.lineTo(150 * escala, 420 * escala);
                forma.lineTo(200 * escala, 440 * escala);
                forma.lineTo(250 * escala, 430 * escala);
                forma.lineTo(260 * escala, 500 * escala);
                forma.lineTo(240 * escala, 550 * escala);
                forma.lineTo(130 * escala, 520 * escala);
                forma.lineTo(110 * escala, 460 * escala);
                forma.lineTo(60 * escala, 480 * escala);
                forma.lineTo(80 * escala, 430 * escala);
                forma.lineTo(120 * escala, 420 * escala);
                break;
        }
        
        forma.closePath();
        return forma;
    }
    
    private Color generarColorSVG(int indice) {
        // Colores exactos del SVG original
        switch (indice) {
            case 0: return new Color(209, 213, 216); // Rampa: #d1d5d8
            case 1: return new Color(233, 229, 181); // Vedado Malecón: #e9e5b5
            case 2: return new Color(171, 211, 212); // Carmelo: #abd3d4
            case 3: return new Color(164, 179, 209); // Vedado: #a4b3d1
            case 4: return new Color(199, 217, 173); // Príncipe: #c7d9ad
            case 5: return new Color(217, 177, 197); // Colón Vedado: #d9b1c5
            case 6: return new Color(219, 188, 171); // Plaza: #dbbcab
            case 7: return new Color(177, 180, 177); // Puentes Grandes: #b1b4b1
            default: return Color.LIGHT_GRAY;
        }
    }
    
    private Point calcularPosicionTexto(int indice) {
        double escala = 0.75;
        
        // Ajustar las posiciones para dar espacio a los indicadores
        switch (indice) {
            case 7: return new Point((int)(310 * escala) + 10, (int)(110 * escala)); // Rampa - mover a la derecha
            case 6: return new Point((int)(195 * escala) + 10, (int)(152 * escala)); // Vedado Malecón - mover a la derecha
            case 3: return new Point((int)(110 * escala) + 10, (int)(270 * escala)); // Carmelo - mover a la derecha
            case 5: return new Point((int)(235 * escala) + 10, (int)(295 * escala)); // Vedado - mover a la derecha
            case 4: return new Point((int)(345 * escala) + 10, (int)(260 * escala)); // Príncipe - mover a la derecha
            case 2: return new Point((int)(190 * escala) + 10, (int)(367 * escala)); // Colón Vedado - mover a la derecha
            case 1: return new Point((int)(315 * escala) + 10, (int)(440 * escala)); // Plaza - mover a la derecha
            case 0: return new Point((int)(180 * escala) + 10, (int)(520 * escala)); // Puentes Grandes - mover a la derecha
            default: return new Point(200, 200);
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
                String titulo = "MAPA CONSEJOS POPULARES PLAZA DE LA REVOLUCIÓN";
                FontMetrics fm = g2.getFontMetrics();
                int tituloX = (getWidth() - fm.stringWidth(titulo)) / 2;
                g2.drawString(titulo, tituloX, 30);
                
                g2.setFont(new Font("Arial", Font.PLAIN, 11));
                g2.setColor(Color.GRAY);
                String subtitulo = "Total: " + (consejos != null ? consejos.size() : 0) + " consejos";
                int subtituloX = (getWidth() - g2.getFontMetrics().stringWidth(subtitulo)) / 2;
                g2.drawString(subtitulo, subtituloX, 50);
                
                int centroX = getWidth() / 2;
                int centroY = getHeight() / 2;
                int mapaAncho = 600;
                int mapaAlto = 450;
                
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
                return new Dimension(700, 600);
            }
        };
    }
    
    private void agregarListeners(final JPanel mapaPanel) {
        mapaPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int centroX = mapaPanel.getWidth() / 2;
                int centroY = mapaPanel.getHeight() / 2;
                int mapaAncho = 600;
                int mapaAlto = 450;
                
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
                    JOptionPane.showMessageDialog(MapaPlaza.this, 
                        "Selecciona un consejo popular haciendo clic dentro de el", 
                        "Informacion", 
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        mapaPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int centroX = mapaPanel.getWidth() / 2;
                int centroY = mapaPanel.getHeight() / 2;
                int mapaAncho = 600;
                int mapaAlto = 450;
                
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
                DireccionMunicipal d = Inicializar.inicializarPlazaRevolucion();
                LinkedList<ConsejoPopular> c = new LinkedList<>();
                for(Vertex v : d.getGrafo().getGrafo().getVerticesList()){
                    c.addFirst((ConsejoPopular)v.getInfo());
                    
               }
                
                MapaPlaza dialog = new MapaPlaza(c);
                dialog.setVisible(true);
            }
        });
    }
}