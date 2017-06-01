
package Interfaz;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import Logica.Jugador;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cuchurrumi
 */
public class Ventana extends JFrame{

    private PanelIngreso panelIngreso;
    
    private List<Jugador> jugadores;
    
    public Ventana() {
        configurar();
    }
    
    public void configurar(){
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Juego de Colores");
        setLayout(new BorderLayout(10, 10));
        setResizable(false);
        
        agregar();
        
        setVisible(true);
    }
    
    public void agregar(){
        jugadores = new ArrayList<Jugador>();
        
        Jugador jugador = new Jugador("holmes", "12345", "C:\\Users\\Cuchurrumi\\Pictures\\bichito.jpg", 1);
        jugadores.add(jugador);
        
        panelIngreso = new PanelIngreso(this);
        add(panelIngreso, BorderLayout.CENTER);
        
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
    
}
