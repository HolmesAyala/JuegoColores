
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
    
    private JLabel jlNombre;    //  Etiqueta para el nombre
    
    private JLabel jlFoto;  //  Etiqueta para la foto
    
    private JLabel jlTxtPuntaje;    //  Etiqueta para el puntaje
    
    private JLabel jlCronometro;    //  Etiqueta para el cronometro
    
    private JLabel jlPuntaje;   //  Etiqueta para el puntaje
    
    private Jugador jugador;    //  Objeto de la clase Jugador
    
    private HiloCronometro hiloCronometro;  //  Hilo del cronometro
    
    private VentanaJuego ventanaJuego;  //  Objeto de la clase de la ventana del juego

    /**
     * Contructor
     * @param jugador
     * @param ventanaJuego 
     */
    public PanelInformacion(Jugador jugador, VentanaJuego ventanaJuego) {
        this.ventanaJuego = ventanaJuego;
        this.jugador = jugador;
        configurar();
        agregar();
    }
    
    /**
     * Configurar el panel
     */
    public void configurar(){
        setBackground(Color.WHITE);
        setLayout(null);
        setSize(300, 750);
    }
    
    /**
     * Agregar elementos a el panel
     */
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
        
        jlTxtPuntaje = new JLabel("Puntaje:");
        jlTxtPuntaje.setBounds(50, 330, 150, 30);
        jlTxtPuntaje.setFont(fuente);
        add(jlTxtPuntaje);
        
        jlPuntaje = new JLabel("0");
        jlPuntaje.setBounds(150, 330, 200, 30);
        jlPuntaje.setFont(fuente);
        add(jlPuntaje);
        
         iniciarCronometro();
         iniciarPuntaje();
        
    }
    
    /**
     * Metodo para crear un icono y adecuarlo a un label
     * @param ruta
     * @param ancho
     * @param alto
     * @return 
     */
    public Icon crearIcono(String ruta, int ancho, int alto){
        ImageIcon imagen = new ImageIcon(ruta);
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        return icono;
    }
    
    /**
     * Metodo para iniciar la base del puntaje
     */
    public void iniciarPuntaje(){
        if(jugador.getNivelActual() == 1){
            jlPuntaje.setText("500");
        }
        else if(jugador.getNivelActual() == 2){
            jlPuntaje.setText("1000");
        }
        else if(jugador.getNivelActual() == 3){
            jlPuntaje.setText("1500");
        }
        
    }

    /**
     * Metodo para iniciar el hilo del cronometro
     */
    public void iniciarCronometro(){
        hiloCronometro = new HiloCronometro(ventanaJuego, jugador);
        hiloCronometro.start();
    }
    
    /**
     * Obtener la etiqueta del cronometro
     * @return 
     */
    public JLabel getJlCronometro() {
        return jlCronometro;
    }

    /**
     * Cambiar la etiqueta del cronometro
     * @param jlCronometro 
     */
    public void setJlCronometro(JLabel jlCronometro) {
        this.jlCronometro = jlCronometro;
    }

    /**
     * Obtener la etiqueta del puntaje
     * @return 
     */
    public JLabel getJlPuntaje() {
        return jlPuntaje;
    }

    /**
     * Cambiar la etiqueta del puntaje
     * @param jlPuntaje 
     */
    public void setJlPuntaje(JLabel jlPuntaje) {
        this.jlPuntaje = jlPuntaje;
    }

    /**
     * Obtener el hilo del cronometro
     * @return 
     */
    public HiloCronometro getHiloCronometro() {
        return hiloCronometro;
    }

    /**
     * Cambiar el hilo del cronometro
     * @param hiloCronometro 
     */
    public void setHiloCronometro(HiloCronometro hiloCronometro) {
        this.hiloCronometro = hiloCronometro;
    }
    
}
