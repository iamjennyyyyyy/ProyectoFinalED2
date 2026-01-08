package Interfaz;

import javax.swing.*;

import cu.edu.cujae.ceis.graph.vertex.Vertex;
import Auxiliar.Inicializar;
import Auxiliar.Sistema;
import Salud.ConsejoPopular;
import Salud.DireccionMunicipal;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MapaHabana extends JDialog {
    
    private List<MunicipioShape> municipios = new ArrayList<>();
    private List<TextoMunicipio> textosMunicipios = new ArrayList<>();
    private ArrayList<DireccionMunicipal> nombresMunicipios;
    
    public MapaHabana( ArrayList<DireccionMunicipal> DireccionesMunicipales) {
       // super(parent, "Mapa de La Habana", true);
       this.nombresMunicipios = DireccionesMunicipales;
        
        initComponents();
        crearShapesMunicipios();
        crearTextosMunicipios();
        
        setSize(900, 700);
        //setLocationRelativeTo(parent);
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        
        // Título superior
        JLabel titulo = new JLabel("Mapa de La Habana", SwingConstants.CENTER);
        titulo.setFont(new Font("Tahoma", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);
        
        // Panel del mapa
        final JPanel panelMapa = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fondo
                g2d.setColor(new Color(224, 242, 241));
                g2d.fillRect(0, 0, getWidth(), getHeight());
                
                // Calcular factor de escala
                double scaleX = getWidth() / 800.0;
                double scaleY = getHeight() / 533.0;
                double scale = Math.min(scaleX, scaleY) * 0.9;
                
                // Aplicar transformación para centrar el mapa
                AffineTransform originalTransform = g2d.getTransform();
                AffineTransform at = new AffineTransform();
                at.translate(getWidth()/2 - (800*scale)/2, getHeight()/2 - (533*scale)/2);
                at.scale(scale, scale);
                g2d.transform(at);
                
                // Dibujar los municipios
                for (int i = 0; i < municipios.size(); i++) {
                    MunicipioShape municipio = municipios.get(i);
                    g2d.setColor(municipio.color);
                    g2d.fill(municipio.shape);
                    g2d.setColor(Color.BLACK);
                    g2d.setStroke(new BasicStroke(0.4f));
                    g2d.draw(municipio.shape);
                }
                
                // Dibujar los nombres de los municipios
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("Tahoma", Font.PLAIN, 13));
                
                for (int i = 0; i < textosMunicipios.size(); i++) {
                    TextoMunicipio texto = textosMunicipios.get(i);
                    AffineTransform originalTextTransform = g2d.getTransform();
                    
                    // Aplicar transformación específica del texto
                    if (texto.transform != null) {
                        // Crear una nueva transformación que combine la escala del mapa
                        // con la transformación específica del texto
                        AffineTransform combined = new AffineTransform();
                        combined.concatenate(texto.transform);
                        g2d.transform(combined);
                    }
                    
                    // Dibujar el texto en la posición especificada
                    g2d.drawString(texto.nombre, (float)texto.x, (float)texto.y);
                    g2d.setTransform(originalTextTransform);
                }
                
                g2d.setTransform(originalTransform);
            }
        };
        
        panelMapa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean clickEnMunicipio = false;
                
                // Calcular factor de escala
                double scaleX = panelMapa.getWidth() / 800.0;
                double scaleY = panelMapa.getHeight() / 533.0;
                double scale = Math.min(scaleX, scaleY) * 0.9;
                
                // Calcular desplazamiento
                double offsetX = panelMapa.getWidth()/2 - (800*scale)/2;
                double offsetY = panelMapa.getHeight()/2 - (533*scale)/2;
                
                // Transformar punto del clic a coordenadas del mapa
                double x = (e.getX() - offsetX) / scale;
                double y = (e.getY() - offsetY) / scale;
                
                for (int i = 0; i < municipios.size(); i++) {
                    MunicipioShape municipio = municipios.get(i);
                    if (municipio.shape.contains(x, y)) {
                    	
                    	//buscarMunicipio(municipio.nombre);
                    	crearMapa(buscarMunicipio(municipio.nombre));
//                        JOptionPane.showMessageDialog(MapaHabana.this, 
//                            municipio.nombre, 
//                            "Municipio Seleccionado", 
//                            JOptionPane.INFORMATION_MESSAGE);
                        clickEnMunicipio = true;
                        break;
                    }
                }
                
                if (!clickEnMunicipio) {
                    JOptionPane.showMessageDialog(MapaHabana.this, 
                        "Por favor, haga clic en un municipio para ver su información", 
                        "Ubicación incorrecta", 
                        JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(panelMapa);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);
        
        // Instrucciones inferiores
        JLabel instrucciones = new JLabel(
            "<html><center><font color='#666666'>Haga clic en cualquier municipio para mostrar información de los consejos populares</font></center></html>", 
            SwingConstants.CENTER
        );
        instrucciones.setFont(new Font("Tahoma", Font.PLAIN, 12));
        instrucciones.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(instrucciones, BorderLayout.SOUTH);
    }
    
    private void crearShapesMunicipios() {
        // 1. Arroyo Naranjo
        municipios.add(crearMunicipio("Arroyo Naranjo", new Color(244, 192, 199), new double[][]{
            {460.8233, 456.1094}, {465.7673, 435.466}, {464.8566, 424.0166}, {440.0064, 388.6279},
            {435.4527, 377.0051}, {436.7537, 364.3415}, {446.2514, 346.1267}, {389.3952, 307.4419},
            {382.3695, 298.9416}, {326.0337, 297.7273}, {345.1593, 365.9027}, {357.2591, 391.7504},
            {375.7341, 405.2814}, {400.9746, 414.1286}, {423.4829, 434.4251}, {460.8233, 456.1094}
        }));
        
        // 2. Boyeros
        municipios.add(crearMunicipio("Boyeros", new Color(244, 192, 199), new double[][]{
            {326.0337, 297.7273}, {306.6479, 274.8287}, {272.9505, 304.3193}, {268.2667, 317.6769},
            {252.3938, 339.1877}, {226.6329, 359.1372}, {210.76, 376.8316}, {208.1579, 409.0978},
            {216.2244, 408.5774}, {225.592, 412.5673}, {231.0565, 419.5063}, {237.6919, 435.2925},
            {247.9702, 444.3131}, {281.7977, 462.528}, {293.3772, 473.9773}, {303.5254, 479.5284},
            {312.5027, 479.7019}, {321.2198, 474.4977}, {324.3423, 465.3035}, {322.0004, 443.2723},
            {323.5617, 438.5885}, {334.8809, 435.2925}, {384.8415, 450.2113}, {400.4542, 469.2935},
            {407.8702, 474.3242}, {423.7431, 477.4467}, {456.6599, 478.8345}, {460.8233, 456.1094},
            {423.4829, 434.4251}, {400.9746, 414.1286}, {375.7341, 405.2814}, {357.2591, 391.7504},
            {345.1593, 365.9027}, {326.0337, 297.7273}
        }));
        
        // 3. Centro Habana
        municipios.add(crearMunicipio("Centro Habana", new Color(244, 192, 199), new double[][]{
            {282.5784, 142.6412}, {286.7418, 166.4072}, {288.1729, 174.734}, {308.7296, 158.6009},
            {322.9112, 154.4375}, {321.48, 152.1823}, {317.4467, 154.9579}, {317.0564, 151.6619},
            {329.8068, 144.376}, {333.0594, 128.5898}, {327.9853, 120.7835}, {316.7962, 112.6302},
            {309.12, 120.7835}, {302.6147, 121.3039}, {295.8492, 120.7835}, {278.8053, 112.6302},
            {268.0065, 114.018}, {282.5784, 142.6412}
        }));
        
        // 4. Cerro
        municipios.add(crearMunicipio("Cerro", new Color(244, 192, 199), new double[][]{
            {288.1729, 174.734}, {277.244, 183.5812}, {267.8764, 199.5408}, {319.3983, 226.0824},
            {330.5874, 211.8575}, {329.5466, 150.621}, {324.7326, 154.611}, {308.7296, 158.6009},
            {288.1729, 174.734}
        }));
        
        // 5. Cotorro
        municipios.add(crearMunicipio("Cotorro", new Color(244, 192, 199), new double[][]{
            {460.8233, 456.1094}, {456.6599, 478.8345}, {474.2242, 476.9263}, {479.9488, 472.2425},
            {481.7703, 462.528}, {481.7703, 432.3434}, {518.9806, 404.9345}, {523.6644, 394.1791},
            {520.9322, 376.8316}, {513.9064, 364.168}, {511.1742, 349.4227}, {511.4344, 319.2381},
            {465.2469, 288.3597}, {446.2514, 346.1267}, {436.7537, 364.3415}, {435.4527, 377.0051},
            {440.0064, 388.6279}, {464.8566, 424.0166}, {465.7673, 435.466}, {460.8233, 456.1094}
        }));
        
        // 6. Diez de Octubre
        municipios.add(crearMunicipio("D. de Octubre", new Color(244, 192, 199), new double[][]{
            {382.3695, 298.9416}, {365.716, 259.9099}, {366.6267, 211.684}, {330.5874, 211.8575},
            {319.3983, 226.0824}, {306.6479, 274.8287}, {326.0337, 297.7273}, {382.3695, 298.9416}
        }));
        
        // 7. Guanabacoa
        municipios.add(crearMunicipio("Guanabacoa", new Color(244, 192, 199), new double[][]{
            {446.2514, 346.1267}, {465.2469, 288.3597}, {453.4073, 255.0526}, {447.0321, 245.5115},
            {397.5919, 215.1535}, {366.6267, 211.684}, {365.716, 259.9099}, {382.3695, 298.9416},
            {389.3952, 307.4419}, {446.2514, 346.1267}
        }));
        
        // 8. La Habana del Este
        municipios.add(crearMunicipio("La Habana del Este", new Color(140, 213, 247), new double[][]{
            {381.0684, 71.6902}, {405.7885, 125.1203}, {409.3014, 155.1314}, {448.203, 156.1722},
            {483.3316, 171.7849}, {535.1137, 240.1338}, {545.392, 264.9407}, {545.7824, 290.0944},
            {592.7505, 282.8085}, {609.4041, 288.5332}, {620.9835, 300.6764}, {635.4252, 321.3198},
            {641.5402, 325.8302}, {650.1272, 323.575}, {655.8518, 308.1358}, {659.1045, 278.8186},
            {667.3012, 256.7874}, {677.5795, 242.7359}, {691.6309, 230.5927}, {699.047, 215.6739},
            {701.1287, 192.6018}, {675.4978, 108.8137}, {674.8473, 56.7714}, {667.6915, 56.251},
            {655.0712, 68.7411}, {644.5326, 63.8839}, {625.0168, 67.8738}, {613.4374, 65.4451},
            {604.0697, 71.1698}, {586.7657, 72.2106}, {558.923, 63.8839}, {553.0683, 65.4451},
            {552.4178, 63.0165}, {549.1651, 64.4043}, {526.6568, 58.6796}, {525.616, 61.9756},
            {524.5751, 57.6388}, {517.6795, 56.7714}, {515.2075, 53.3019}, {506.1001, 56.7714},
            {506.1001, 68.2207}, {503.2378, 56.7714}, {483.7219, 53.8223}, {469.6705, 59.2001},
            {471.492, 63.8839}, {468.8898, 66.3125}, {463.4254, 58.6796}, {454.8384, 58.6796},
            {443.259, 63.0165}, {425.5646, 61.4552}, {405.6584, 72.5576}, {402.4058, 76.8944},
            {402.0155, 83.6599}, {400.194, 83.313}, {397.722, 74.1189}, {392.2575, 71.1698},
            {381.0684, 71.6902}
        }));
        
        // 9. La Habana Vieja
        municipios.add(crearMunicipio("La Habana Vieja", new Color(244, 192, 199), new double[][]{
            {342.6872, 122.6917}, {350.3635, 123.2121}, {351.7946, 127.0285}, {356.0881, 121.8243},
            {358.6903, 124.2529}, {354.2667, 128.2429}, {365.3256, 131.0184}, {397.9822, 155.1314},
            {409.3014, 155.1314}, {405.7885, 125.1203}, {381.0684, 71.6902}, {370.5299, 76.8944},
            {359.7311, 77.9353}, {341.2561, 88.5172}, {342.6872, 122.6917}
        }));
        
        // 10. La Lisa
        municipios.add(crearMunicipio("La Lisa", new Color(123, 234, 5), new double[][]{
            {252.3938, 339.1877}, {208.9385, 304.4928}, {206.3364, 280.5533}, {209.9794, 249.5014},
            {157.0263, 270.6653}, {152.8629, 275.6961}, {158.1973, 285.9311}, {156.636, 372.4948},
            {159.7585, 394.3525}, {164.4423, 407.0162}, {171.468, 416.2103}, {181.226, 417.945},
            {208.1579, 409.0978}, {210.76, 376.8316}, {226.6329, 359.1372}, {252.3938, 339.1877}
        }));
        
        // 11. Marianao
        municipios.add(crearMunicipio("Marianao", new Color(140, 213, 247), new double[][]{
            {306.6479, 274.8287}, {319.3983, 226.0824}, {267.8764, 199.5408}, {256.0368, 208.735},
            {245.238, 226.4293}, {260.5905, 255.7465}, {272.9505, 304.3193}, {306.6479, 274.8287}
        }));
        
        // 12. Playa
        municipios.add(crearMunicipio("Playa", new Color(140, 213, 247), new double[][]{
            {272.9505, 304.3193}, {260.5905, 255.7465}, {245.238, 226.4293}, {209.9794, 249.5014},
            {206.3364, 280.5533}, {208.9385, 304.4928}, {252.3938, 339.1877}, {268.2667, 317.6769},
            {272.9505, 304.3193}
        }));
        
        // 13. Plaza de la Revolución
        municipios.add(crearMunicipio("Plaza de la Revolución", new Color(246, 185, 253), new double[][]{
            {268.0065, 114.018}, {251.7433, 127.549}, {251.353, 136.7431}, {247.3197, 138.1309},
            {247.0595, 133.2736}, {242.6359, 134.8349}, {215.1836, 161.7234}, {189.5527, 194.5101},
            {190.9839, 201.2756}, {182.6571, 199.8878}, {180.8357, 202.3164}, {181.8765, 206.1328},
            {168.6057, 204.745}, {165.3531, 208.0411}, {158.4575, 209.0819}, {158.0672, 211.3371},
            {156.636, 207.5206}, {147.9189, 204.2246}, {144.406, 210.4697}, {142.9749, 205.6124},
            {140.7631, 205.092}, {126.9719, 214.2861}, {126.3213, 222.0925}, {128.7934, 224.8681},
            {123.0687, 228.3376}, {120.8569, 224.3477}, {123.7192, 222.4394}, {118.3849, 222.9599},
            {117.9946, 226.4293}, {109.6678, 229.2049}, {99.9099, 238.3991}, {98.869, 269.2775},
            {100.9507, 274.3083}, {110.3183, 277.7778}, {152.8629, 275.6961}, {157.0263, 270.6653},
            {209.9794, 249.5014}, {245.238, 226.4293}, {256.0368, 208.735}, {267.8764, 199.5408},
            {277.244, 183.5812}, {288.1729, 174.734}, {282.5784, 142.6412}, {268.0065, 114.018}
        }));
        
        // 14. Regla
        municipios.add(crearMunicipio("Regla", new Color(245, 252, 152), new double[][]{
            {330.5874, 211.8575}, {366.6267, 211.684}, {397.5919, 215.1535}, {397.9822, 155.1314},
            {365.3256, 131.0184}, {354.0064, 128.5898}, {352.5753, 133.794}, {355.0473, 136.2227},
            {354.657, 139.1717}, {349.9732, 139.1717}, {342.8174, 133.2736}, {343.4679, 139.5187},
            {341.6464, 142.4678}, {343.0776, 146.2842}, {337.3529, 153.0497}, {342.8174, 164.6725},
            {342.427, 171.438}, {338.3938, 170.9175}, {331.8885, 157.0396}, {334.4906, 147.8455},
            {331.8885, 147.325}, {328.5057, 152.8762}, {330.5874, 211.8575}
        }));
        
        // 15. San Miguel del Padrón
        municipios.add(crearMunicipio("San Miguel del Padrón", new Color(245, 252, 152), new double[][]{
            {511.4344, 319.2381}, {512.8656, 301.3703}, {515.2075, 295.1252}, {522.8837, 287.8393},
            {545.7824, 290.0944}, {545.392, 264.9407}, {535.1137, 240.1338}, {483.3316, 171.7849},
            {448.203, 156.1722}, {397.9822, 155.1314}, {397.5919, 215.1535}, {447.0321, 245.5115},
            {453.4073, 255.0526}, {465.2469, 288.3597}, {511.4344, 319.2381}
        }));
    }
    
    private void crearTextosMunicipios() {
        // Usar las transformaciones ORIGINALES del SVG
        // El formato es: matrix(a, b, c, d, e, f)
        // Donde: (e, f) es la traslación, y (a, b, c, d) definen rotación y escala
        
        // Arroyo Naranjo - matriz original del SVG
    	// Si se corta con el polígono, mover posición
    	textosMunicipios.add(new TextoMunicipio("Arroyo Naranjo", 
    	    0, 0,
    	    new AffineTransform(0.7175, 0.6965, -0.6965, 0.7175, 370, 345)));
        
        // Boyeros - sin transformación, posición directa
        textosMunicipios.add(new TextoMunicipio("Boyeros", 
            267.8765, 395.2653, null));
        
     // Centro Habana - con rotación invertida para que descienda
        textosMunicipios.add(new TextoMunicipio("Centro Habana",
            0, 0,
            new AffineTransform(0.6443, 0.7648, -0.7648, 0.6443, 225.8637, 34.4535)));
        
        // Cerro - sin transformación
        textosMunicipios.add(new TextoMunicipio("Cerro",
            288.1729, 195.8977, null));
        
        // Cotorro - sin transformación
        textosMunicipios.add(new TextoMunicipio("Cotorro",
            458.2082, 383.5971, null));
        
     // D. de Octubre - con rotación invertida para que descienda
        textosMunicipios.add(new TextoMunicipio("D. de Octubre",
            0, 0,
            new AffineTransform(0.4012, -0.916, 0.916, 0.4012, 328.5056, 288.3588)));
        
        // Guanabacoa - sin transformación
        textosMunicipios.add(new TextoMunicipio("Guanabacoa",
            380.125, 278.9053, null));
        
        // La Habana del Este - sin transformación
        textosMunicipios.add(new TextoMunicipio("La Habana del Este",
            528.9454, 162.566, null));
        // Habana Vieja
        textosMunicipios.add(new TextoMunicipio("La Habana Vieja",
        	    0, 0,
        	    new AffineTransform(0.6399, 0.7684, -0.7684, 0.6399, 290, 10)));
        
        // La Lisa - sin transformación
        textosMunicipios.add(new TextoMunicipio("La Lisa",
            168.776, 346.1263, null));
        
        // Marianao - sin transformación
        textosMunicipios.add(new TextoMunicipio("Marianao",
            256.0929, 242.0112, null));
        
        // Playa - sin transformación
        textosMunicipios.add(new TextoMunicipio("Playa",
            224.5138, 282.8085, null));
        
     // Plaza de la Revolucion
        textosMunicipios.add(new TextoMunicipio("Plaza de la Revolución",
            0, 0,
            new AffineTransform(0.8668, -0.4987, 0.4987, 0.8668, 144.8215, 242.0114)));
        
        // Regla - sin transformación
        textosMunicipios.add(new TextoMunicipio("Regla",
            347.2986, 171.8716, null));
        
        // San Miguel del Padrón - matriz original del SVG
        textosMunicipios.add(new TextoMunicipio("San Miguel del Padrón",
        	    435, 30,
        	    new AffineTransform(0.95, 0.4, -0.4, 0.95, 0, 0)));
    }
    
    private void crearMapa(DireccionMunicipal mun){
    	JDialog j = null;

    	 LinkedList<ConsejoPopular> c = new LinkedList<>();
         for(Vertex v : mun.getGrafo().getGrafo().getVerticesList()){
             c.addFirst((ConsejoPopular)v.getInfo());
             
        }  	
    	switch(mun.getMunicipio()){
    	case "Regla": j = new MapaRegla(c); j.setVisible(true); System.out.print("jjj"); break;
    	case "Plaza de la Revolución": j = new MapaPlaza(c); j.setVisible(true); break;
    	case "Centro Habana": j = new MapaCentroHabana(c); j.setVisible(true); break;
    	case "Cerro": j = new MapaCerro(c); j.setVisible(true); break;
    	}
    }
    
    private MunicipioShape crearMunicipio(String nombre, Color color, double[][] puntos) {
        Path2D.Double shape = new Path2D.Double();
        
        if (puntos.length > 0) {
            shape.moveTo(puntos[0][0], puntos[0][1]);
            for (int i = 1; i < puntos.length; i++) {
                shape.lineTo(puntos[i][0], puntos[i][1]);
            }
            shape.closePath();
        }
        
        return new MunicipioShape(nombre, shape, color);
    }
    
    private DireccionMunicipal buscarMunicipio(String nombre){
    	DireccionMunicipal mun = null;
    	System.out.print(nombresMunicipios.size());
    	
    	for(int i = 0; i<nombresMunicipios.size() && mun == null ; i++){
    		if(nombresMunicipios.get(i).getMunicipio().equalsIgnoreCase(nombre)){
    			mun = nombresMunicipios.get(i);
    			
    		}
    		
    	}
    	return mun;
    }
    
    // Clase interna para representar un municipio
    private class MunicipioShape {
        String nombre;
        Shape shape;
        Color color;
        
        public MunicipioShape(String nombre, Shape shape, Color color) {
            this.nombre = nombre;
            this.shape = shape;
            this.color = color;
        }
    }
    
    // Clase interna para representar un texto de municipio
    private class TextoMunicipio {
        String nombre;
        double x, y;
        AffineTransform transform;
        
        public TextoMunicipio(String nombre, double x, double y, AffineTransform transform) {
            this.nombre = nombre;
            this.x = x;
            this.y = y;
            this.transform = transform;
        }
    }
    
    // Método main para probar el diálogo directamente
    public static void main(String[] args) {
    	Sistema sis = Sistema.getInstancia();
    	Sistema.setSistema(Inicializar.inicializarArbol());
        ArrayList<DireccionMunicipal> municipios= sis.getMunicipiosHabana();
       
        
        
      MapaHabana dialog = new MapaHabana( municipios);
        dialog.setVisible(true);
    }
}