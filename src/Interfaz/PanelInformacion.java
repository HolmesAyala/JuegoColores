
package Interfaz;

import Logica.HiloCronometro;
import Logica.Jugador;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Cuchurrumi
 */
public class PanelInformacion extends JPanel{
    
    private JLabel jlNombre;
    
    private JLabel jlFoto;
    
    private JLabel jlCronometro;
    
    private JLabel jlPuntaje;
    
    private Jugador jugador;
    
    private HiloCronometro hiloCronometro;
    
    private VentanaJuego ventanaJuego;

    public PanelInformacion(Jugador jugador, VentanaJuego ventanaJuego) {
        this.ventanaJuego = ventanaJuego;
        this.jugador = jugador;
        configurar();
        agregar();
    }
    
    public void configurar(){
        setBackground(Color.WHITE);
        setLayout(null);
        setSize(300, 750);
    }
    
    public void agregar(){
        Font fuente = new Font("Microsoft Sans Serif", Font.PLAIN, 20);
        
        jlFoto = new JLabel();
        jlFoto.setBounds(50, 20, 200, 200);
        Icon icono = crearIcono(jugador.getFoto(), jlFoto.getWidth(), jlFoto.getHeight());
        jlFoto.setIcon(icono);
        add(jlFoto);
        
        jlNombre = new JLabel(jugador.getNombre());
        jlNombre.setBounds(50, 230, 200, 30);
        jlNombre.setFont(fuente);
        add(jlNombre);
        
        jlCronometro = new JLabel("Tiempo: 00:00");
        jlCronometro.setBounds(50, 280, 200, 30);
        jlCronometro.setFont(fuente);
        add(jlCronometro);
        
        jlPuntaje = new JLabel("Puntaje: 0");
        jlPuntaje.setBounds(50, 330, 200, 30);
        jlPuntaje.setFont(fuente);
        add(jlPuntaje);
        
        hiloCronometro = new HiloCronometro(ventanaJuego);
        hiloCronometro.start();
        
    }
    
    public Icon crearIcono(String ruta, int ancho, int alto){
        ImageIcon imagen = new ImageIcon(ruta);
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        return icono;
    }

    public JLabel getJlCronometro() {
        return jlCronometro;
    }

    public void setJlCronometro(JLabel jlCronometro) {
        this.jlCronometro = jlCronometro;
    }
    
}
