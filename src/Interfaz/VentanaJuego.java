
package Interfaz;

import Logica.Jugador;
import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author Cuchurrumi
 */
public class VentanaJuego extends JFrame{

    private PanelJuego panelJuego;  //  Panel del juego
    
    private PanelInformacion panelInformacion;  //  Panel que contiene la informacion
    
    private Jugador jugador;    //  Objeto de la clase jugador
    
    private Ventana ventana;    //  Objeto de la clase ventana
    
    /**
     * Constructor
     * @param jugador
     * @param ventana 
     */
    public VentanaJuego(Jugador jugador, Ventana ventana) {
        this.jugador = jugador;
        this.ventana = ventana;
        configurar();
    }
    
    /**
     * Configurar ventana
     */
    public void configurar(){
        setSize(1100, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        
        agregar();
    }
    
    /**
     * Agregar elementos a la ventana
     */
    public void agregar(){
        panelJuego = new PanelJuego(jugador, this, ventana);
        panelJuego.setLocation(25, 10);
        add(panelJuego);
        
        panelInformacion = new PanelInformacion(jugador, this);
        panelInformacion.setLocation(775, 10);
        add(panelInformacion);
    }

    /**
     * Obtener el panel del juego
     * @return 
     */
    public PanelJuego getPanelJuego() {
        return panelJuego;
    }

    /**
     * Cambiar el panel del juego
     * @param panelJuego 
     */
    public void setPanelJuego(PanelJuego panelJuego) {
        this.panelJuego = panelJuego;
    }

    /**
     * Obtener el panel de informacion
     * @return 
     */
    public PanelInformacion getPanelInformacion() {
        return panelInformacion;
    }

    /**
     * Cambiar el panel de informacion
     * @param panelInformacion 
     */
    public void setPanelInformacion(PanelInformacion panelInformacion) {
        this.panelInformacion = panelInformacion;
    }
    
    
}
