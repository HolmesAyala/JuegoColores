
package Interfaz;

import Logica.Jugador;
import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author Cuchurrumi
 */
public class VentanaJuego extends JFrame{

    private PanelJuego panelJuego;
    
    private PanelInformacion panelInformacion;
    
    private Jugador jugador;
    
    public VentanaJuego(Jugador jugador) {
        this.jugador = jugador;
        configurar();
    }
    
    public void configurar(){
        setSize(1100, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        
        agregar();
    }
    
    public void agregar(){
        panelJuego = new PanelJuego(jugador);
        panelJuego.setLocation(25, 10);
        add(panelJuego);
        
        panelInformacion = new PanelInformacion(jugador, this);
        panelInformacion.setLocation(775, 10);
        add(panelInformacion);
    }

    public PanelJuego getPanelJuego() {
        return panelJuego;
    }

    public void setPanelJuego(PanelJuego panelJuego) {
        this.panelJuego = panelJuego;
    }

    public PanelInformacion getPanelInformacion() {
        return panelInformacion;
    }

    public void setPanelInformacion(PanelInformacion panelInformacion) {
        this.panelInformacion = panelInformacion;
    }
    
    
}
