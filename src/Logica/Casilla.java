
package Logica;

import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author Cuchurrumi
 */
public class Casilla {
    
    private JButton boton;  //  Boton de la casilla
    
    private Color color;    //  Color de la casilla
    
    private boolean cabeza; //  Para saber si la casilla es cabeza
    
    private boolean caminoCompleto; //  Para saber si ya tiene el camino completado

    /**
     * Constructor
     * @param boton
     * @param color
     * @param cabeza 
     */
    public Casilla(JButton boton, Color color, boolean cabeza) {
        this.boton = boton;
        this.color = color;
        this.cabeza = cabeza;
    }

    /**
     * Metodo para cambiar el color de la casilla
     * @param color 
     */
    public void cambiarColor(Color color){
        this.color = color;
        boton.setBackground(this.color);
    }
    
    /**
     * Obtener el boton
     * @return 
     */
    public JButton getBoton() {
        return boton;
    }

    /**
     * Cambiar el boton
     * @param boton 
     */
    public void setBoton(JButton boton) {
        this.boton = boton;
    }

    /**
     * Obtener el color
     * @return 
     */
    public Color getColor() {
        return color;
    }

    /**
     * Cambiar el color
     * @param color 
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Obtener el estado de la cabeza
     * @return 
     */
    public boolean isCabeza() {
        return cabeza;
    }

    /**
     * Cambiar el estado de la cabeza
     * @param cabeza 
     */
    public void setCabeza(boolean cabeza) {
        this.cabeza = cabeza;
    }

    /**
     * Obtener el estado del camino
     * @return 
     */
    public boolean isCaminoCompleto() {
        return caminoCompleto;
    }

    /**
     * Cambiar el estado del camino
     * @param caminoCompleto 
     */
    public void setCaminoCompleto(boolean caminoCompleto) {
        this.caminoCompleto = caminoCompleto;
    }
    
}
