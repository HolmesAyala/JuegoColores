
package Logica;

import Interfaz.VentanaJuego;
import java.util.Date;

/**
 *
 * @author Cuchurrumi
 */
public class HiloCronometro extends Thread{
    
    private VentanaJuego ventanaJuego;  //  Objeto de la ventana de juego
    
    private char seg2 = '0', seg1 = '0';    //  Segundos de cronometro
    
    private char min2 = '0', min1 = '0';    //  minutos de cronometro
    
    private Date actual;    //  Se actualiza para tener la fecha actual
    
    private Date retardo;   //  Retardo de 1 segundo
    
    private String tiempo;  //  tiempo transcurrido del cronometro
    
    private boolean continuar;  //  Habilitar la ejecucion del hilo
    
    private Jugador jugador;    // objeto de la clase jugador
    
    private int puntaje;    //  Puntaje que lleva el jugador
    
    private int resta;  //  Puntaje que se va restando

    /**
     * Constructor
     * @param ventanaJuego
     * @param jugador 
     */
    public HiloCronometro(VentanaJuego ventanaJuego, Jugador jugador) {
        this.jugador = jugador;
        this.ventanaJuego = ventanaJuego;
    }
    
    /**
     * Metodo que le da vida a el hilo
     */
    @Override
    public void run(){
        if(jugador.getNivelActual() == 1){
            puntaje = 500;
            resta = 25;
        }
        else if(jugador.getNivelActual() == 2){
            puntaje = 1000;
            resta = 15;
        }
        else if(jugador.getNivelActual() == 3){
            puntaje = 1500;
            resta = 25;
        }
        
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
                puntaje = puntaje - resta;
                ventanaJuego.getPanelInformacion().getJlPuntaje().setText(""+puntaje);
            }
            
        }
        
    }

    /**
     * Obtener el estado de continuar
     * @return 
     */
    public boolean isContinuar() {
        return continuar;
    }

    /**
     * Cambiar el estado de continuar
     * @param continuar 
     */
    public void setContinuar(boolean continuar) {
        this.continuar = continuar;
    }
}
