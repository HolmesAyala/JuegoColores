
package Interfaz;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import Logica.Jugador;
import Logica.Utilitaria;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cuchurrumi
 */
public class Ventana extends JFrame{

    private PanelIngreso panelIngreso;  //  Objeto del panel de ingreso
    
    private List<Jugador> jugadores;    //  Lista de jugadores
    
    /**
     * Constructor
     */
    public Ventana() {
        configurar();
    }
    
    /**
     * Configurar la ventana
     */
    public void configurar(){
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Juego de Colores");
        setLayout(new BorderLayout(10, 10));
        setResizable(false);
        
        agregar();
        
        setVisible(true);
    }
    
    /**
     * Agregar elementos a la ventana
     */
    public void agregar(){
        jugadores = new ArrayList<Jugador>();
        traerJugadores();
        
        //Jugador jugador = new Jugador("holmes", "12345", "C:\\Users\\Cuchurrumi\\Pictures\\bichito.jpg", 1);
        //jugadores.add(jugador);
        
        panelIngreso = new PanelIngreso(this);
        add(panelIngreso, BorderLayout.CENTER);
        
    }
    
    /**
     * Traer jugadores guardados en un acrhivo de texto
     */
    public void traerJugadores(){
        try {
            String texto = Utilitaria.leer("Documentos/Jugadores.txt");
            
            int contador = 0;
            for(int i = 0; i < texto.length(); i++){
                if(texto.charAt(i) == '*'){
                    System.out.println("Entre al for");
                    String linea = texto.substring(contador, i);
                    
                    String vector[] = linea.split(";");

                    Jugador jugador = new Jugador(vector[0], vector[1], vector[2], Integer.valueOf(vector[3]));
                    jugador.getPuntajes()[0] = Integer.valueOf(vector[4]);
                    jugador.getPuntajes()[1] = Integer.valueOf(vector[5]);
                    jugador.getPuntajes()[2] = Integer.valueOf(vector[6]);
                    
                    jugadores.add(jugador);
                    //System.out.println(persona.toString());
                    contador = i + 2;
                }
            }
            System.out.println("Se cargaron jugadores\n");
            for(int i = 0; i < jugadores.size(); i++){
                System.out.print(jugadores.get(i).toString());
            }
            
        } catch (IOException ex) {
            System.out.println("No se pudo cargar el archivo");
        }
    }
    
    /**
     * Guardar jugadores en un archivo de texto
     */
    public void escribirJugadores(){
        String texto = "";
        for(int i = 0; i < jugadores.size(); i++){
            texto += jugadores.get(i);
        }
        
        Utilitaria.Escribir(texto, "Documentos/Jugadores.txt");
    }

    /**
     * Obtener la lista de jugadores
     * @return 
     */
    public List<Jugador> getJugadores() {
        return jugadores;
    }

    /**
     * Cambiar la lista de jugadores
     * @param jugadores 
     */
    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    /**
     * Obtener el panel de ingreso
     * @return 
     */
    public PanelIngreso getPanelIngreso() {
        return panelIngreso;
    }

    /**
     * Cambiar el panel de ingreso
     * @param panelIngreso 
     */
    public void setPanelIngreso(PanelIngreso panelIngreso) {
        this.panelIngreso = panelIngreso;
    }
    
}
