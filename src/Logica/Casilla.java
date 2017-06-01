
package Logica;

import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author Cuchurrumi
 */
public class Casilla {
    
    private JButton boton;
    
    private Color color;
    
    private boolean cabeza;
    
    private boolean caminoCompleto;

    public Casilla(JButton boton, Color color, boolean cabeza) {
        this.boton = boton;
        this.color = color;
        this.cabeza = cabeza;
    }

    public void cambiarColor(Color color){
        this.color = color;
        boton.setBackground(this.color);
    }
    
    public JButton getBoton() {
        return boton;
    }

    public void setBoton(JButton boton) {
        this.boton = boton;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isCabeza() {
        return cabeza;
    }

    public void setCabeza(boolean cabeza) {
        this.cabeza = cabeza;
    }

    public boolean isCaminoCompleto() {
        return caminoCompleto;
    }

    public void setCaminoCompleto(boolean caminoCompleto) {
        this.caminoCompleto = caminoCompleto;
    }
    
}
