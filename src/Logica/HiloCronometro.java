
package Logica;

import Interfaz.VentanaJuego;
import java.util.Date;

/**
 *
 * @author Cuchurrumi
 */
public class HiloCronometro extends Thread{
    
    private VentanaJuego ventanaJuego;
    
    private char seg2 = '0', seg1 = '0';
    
    private char min2 = '0', min1 = '0';
    
    private Date actual;
    
    private Date retardo;
    
    private String tiempo;
    
    private boolean continuar;

    public HiloCronometro(VentanaJuego ventanaJuego) {
        this.ventanaJuego = ventanaJuego;
    }
    
    @Override
    public void run(){
        
        actual = new Date();
        retardo = new Date();
        tiempo = " 00:00:00";
        continuar = true;
        
        while(continuar){
            
            actual = new Date();
            
            if(actual.getTime() - retardo.getTime() > 1000){
                retardo = actual;
                //System.out.println("Paso un segundo");
                //  Cambio de cronometro
                seg1++;
                if(seg1 == ':'){
                    seg1 = '0';
                    seg2++;
                    if(seg2 == '6'){
                        seg2 = '0';
                        min1++;
                        if(min1 == ':'){
                            min1 = '0';
                            min2++;
                            if(min2 == '6'){
                                min2 = '0';
                            }
                        }
                    }
                }

                tiempo = "Tiempo: "+min2+min1+":"+seg2+seg1;
                ventanaJuego.getPanelInformacion().getJlCronometro().setText(tiempo);
            }
            
        }
        
    }

    public boolean isContinuar() {
        return continuar;
    }

    public void setContinuar(boolean continuar) {
        this.continuar = continuar;
    }
}
