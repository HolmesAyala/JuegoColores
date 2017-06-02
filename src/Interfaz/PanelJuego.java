
package Interfaz;

import Logica.Casilla;
import Logica.Jugador;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Cuchurrumi
 */
public class PanelJuego extends JPanel{
    
    private Map<String, Casilla> casillas;  //  Mapa con las casillas del juego
    
    private GridLayout grilla;  //  Disposicion del panel
    
    private String cabezas; //  Guarda la configuracion de casillas
    
    private Map<String, Color> colores; //  Mapa con los colores disponibles
    
    private Color colorActual;  //  Color actual, (el que va a ir pintando)
    
    private Jugador jugador;    //  Objeto de la clase jugador
    
    private List<String> camino;    //  Guarda el camino de un color
    
    private VentanaJuego ventanaJuego;  //  Objeto de la ventana del juego
    
    private Ventana ventana;    //  Objeto de la ventana de ingreso

    /**
     * Constructor
     * @param jugador
     * @param ventanaJuego
     * @param ventana 
     */
    public PanelJuego(Jugador jugador, VentanaJuego ventanaJuego, Ventana ventana) {
        this.ventana = ventana;
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
        if(jugador.getNivelActual() == 1){
            grilla = new GridLayout(5, 5);
            nivel1();
        }
        else if(jugador.getNivelActual() == 2){
             grilla = new GridLayout(7, 7);
             nivel2();
        }
        else if(jugador.getNivelActual() == 3){
             grilla = new GridLayout(9, 9);
             nivel3();
        }
        
        
        setLayout(grilla);
        setSize(750, 750);
    }
    
    /**
     * Agregar elementos a el panel
     */
    public void agregar(){
        crearColores();
        colorActual = Color.WHITE;
        
        casillas = new HashMap<String, Casilla>();
        camino = new ArrayList<String>();
        
        EscucharBoton escucharBoton = new EscucharBoton();
        EscucharMouse escucharMouse = new EscucharMouse();
        
        //setName("panel");
        //addMouseListener(escucharMouse);
        
        for(int i = 0; i < grilla.getRows()*grilla.getColumns(); i++){
            JButton boton = new JButton();
            boton.setName(""+(i+1));
            boton.addActionListener(escucharBoton);
            boton.addMouseListener(escucharMouse);
            boton.setBackground(Color.WHITE);
            boton.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 50));
            
            boolean cabeza = false;
            Color color = Color.WHITE;
            if(cabezas.charAt(i) != '0'){
                boton.setText(String.valueOf(cabezas.charAt(i)));
                cabeza = true;
                color = colores.get(String.valueOf(cabezas.charAt(i)));
            }
            Casilla casilla = new Casilla(boton, color, cabeza);
            casilla.cambiarColor(color);
            
            casillas.put(boton.getName(), casilla);
            add(casillas.get(boton.getName()).getBoton());
            //casillas.add(casilla);
            //add(casillas.get(i).getBoton());
        }
    }
    
    /**
     * Configuracion del primer nivel
     */
    public void nivel1(){
        cabezas = "1202300000403500001000045";
    }
    
    /**
     * Configuracion del segundo nivel
     */
    public void nivel2(){
        cabezas = "0000000001203000403505000000004016002006000000000";
    }
    
    /**
     * Configuracion del tercer nivel
     */
    public void nivel3(){
        cabezas = "000000000000000000003002010004000020000000000080100005000000840070000070360650000";
    }
    
    /**
     * Metodo para crear los colores
     */
    public void crearColores(){
        int nivel = jugador.getNivelActual();
        colores = new HashMap<String, Color>();
        
        colores.put("1", Color.GREEN);
        colores.put("2", Color.YELLOW);
        colores.put("3", Color.ORANGE);
        colores.put("4", Color.RED);
        colores.put("5", Color.LIGHT_GRAY);
        
        if(nivel == 2 || nivel == 3){
            colores.put("6", Color.MAGENTA);
            if(nivel == 3){
                colores.put("7", Color.PINK);
                colores.put("8", Color.CYAN);
            }
        }
        
    }
    
    /**
     * Clase que esta a la escucha de los botones
     */
    class EscucharBoton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent evento) {
            
        }
        
    }
    
    /**
     * Clase que esta a la escucha de los eventos del mouse sobre los botones
     */
    class EscucharMouse implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent evento) {
        }

        @Override
        public void mousePressed(MouseEvent evento) {
        }

        @Override
        public void mouseReleased(MouseEvent evento) {
        }

        @Override
        public void mouseEntered(MouseEvent evento) {
            logicaPintado(evento);
        }

        @Override
        public void mouseExited(MouseEvent evento) {
            
        }
        
    }
    
    /**
     * Metodo que hace la logica de pintado de las casillas
     * @param evento 
     */
    public void logicaPintado(MouseEvent evento){
        //  Si la casilla es cabeza
        if(casillas.get(evento.getComponent().getName()).isCabeza()){
            //  Si es diferente al color actual
            if(!casillas.get(evento.getComponent().getName()).getColor().equals(colorActual)){
                //  Si la casilla no tiene el camino completo cambie el color actual
                if(!casillas.get(evento.getComponent().getName()).isCaminoCompleto()){
                    
                    resetearCasillas(colorActual);
                    
                    colorActual = casillas.get(evento.getComponent().getName()).getColor();
                    resetearCasillas(casillas.get(evento.getComponent().getName()).getColor());
                    camino.clear();
                    agregarCamino(evento.getComponent().getName());
                }
                else{
                    
                    resetearCasillas(colorActual);
                    
                    //  Si la casilla tiene el camino completo
                    colorActual = Color.WHITE;
                }
            }
            else{
                //  Si no es diferente al color actual
                colorActual = Color.WHITE;
                //  Si es la cabeza de inicio
                if(evento.getComponent().getName().equals(camino.get(0))){
                    resetearCasillas(casillas.get(evento.getComponent().getName()).getColor());
                    camino.clear();
                }
                else{
                    //  Si no es la cabeza de inicio, osea la final
                    //  Si la casilla de cabeza es valida
                    if(validarCasilla(evento)){
                        caminoCompletado(casillas.get(evento.getComponent().getName()).getColor(), true);
                        camino.clear();
                        //  Si gano
                        gano();
                    }
                    else{
                        //  Si a la casilla que llego no es valida
                        resetearCasillas(colorActual);
                        camino.clear();
                        colorActual = Color.WHITE;
                    }
                    
                }
            }
            
        }
        else{
            //  Si no era cabeza
            
            //  Si el color actual es diferente de blanco
            if(!colorActual.equals(Color.WHITE)){
                //  Si la casilla no es de color blanco
                if(!casillas.get(evento.getComponent().getName()).getColor().equals(Color.WHITE)){
                    //  Si la casilla es diferente al color actual
                    if(!casillas.get(evento.getComponent().getName()).getColor().equals(colorActual)){
                        resetearCasillas(casillas.get(evento.getComponent().getName()).getColor());
                        agregarCamino(evento.getComponent().getName());
                    }
                    else{
                        //  Si la casilla encontrada es igual al color actual
                        retroceder(evento.getComponent().getName());
                    }
                }
                else{
                    //  Si la casilla es de color blanco
                    //  Si la casilla a la que llego es valida
                    if(validarCasilla(evento)){
                        //System.out.println("La casilla a la que llego, es valida");
                        agregarCamino(evento.getComponent().getName());
                    }
                    else{
                        //  Si la casilla a la que llego no es valida
                        resetearCasillas(colorActual);
                        camino.clear();
                        colorActual = Color.WHITE;
                    }
                    
                }
            }
        }
        
        if(!colorActual.equals(Color.WHITE)){
            casillas.get(evento.getComponent().getName()).cambiarColor(colorActual);
        }
    }
    
    /**
     * Metodo para verificar si el usuario ya gano
     */
    public void gano(){
        boolean validar = true;
        
        Iterator iterador = casillas.entrySet().iterator();
        while(iterador.hasNext()){
            Map.Entry auxiliar = (Map.Entry)iterador.next();
            if(!casillas.get(auxiliar.getKey()).isCaminoCompleto() && casillas.get(auxiliar.getKey()).isCabeza()){
                validar = false;
            }
        }
        
        if(validar){
            
            ventanaJuego.getPanelInformacion().getHiloCronometro().setContinuar(false);
            JOptionPane.showMessageDialog(null, "Muy Bien! Paso el nivel "+jugador.getNivelActual()+ ", Puntaje obtenido: "+ventanaJuego.getPanelInformacion().getJlPuntaje().getText(), "Juego Colores", JOptionPane.INFORMATION_MESSAGE);
            jugador.getPuntajes()[jugador.getNivelActual()-1] = Integer.valueOf(ventanaJuego.getPanelInformacion().getJlPuntaje().getText());
            int nivel = jugador.getNivelActual();
            if(nivel == 3){
                int puntajeTotal = jugador.getPuntajes()[0] + jugador.getPuntajes()[1] + jugador.getPuntajes()[2];
                JOptionPane.showMessageDialog(null, jugador.getNombre()+", Ya ha pasado todos los niveles! \nSu puntaje total fue: "+puntajeTotal, "Juego Colores", JOptionPane.INFORMATION_MESSAGE);
                ventana.setVisible(true);
                ventanaJuego.setVisible(false);
                jugador.setNivelActual(nivel+1);
                ventana.escribirJugadores();
            }
            else{
                jugador.getPuntajes()[jugador.getNivelActual()-1] = Integer.valueOf(ventanaJuego.getPanelInformacion().getJlPuntaje().getText());
                jugador.setNivelActual(nivel+1);
                ventana.escribirJugadores();
                eliminarCasillas();
                configurar();
                agregar();
                ventanaJuego.getPanelInformacion().iniciarCronometro();
            }
            
            
        }
        
    }
    
    /**
     * Metodo para validar si la casilla es valida
     * @param evento
     * @return 
     */
    public boolean validarCasilla(MouseEvent evento){
        int botonAnalizar = Integer.valueOf(evento.getComponent().getName());
        int botonCamino = Integer.valueOf(camino.get(camino.size()-1));
        
        //System.out.println("Botones analizados: "+botonAnalizar+"--"+botonCamino);
        
        if(botonAnalizar + 1 == botonCamino){
            return true;
        }
        else if(botonAnalizar - 1 == botonCamino){
            return true;
        }
        else if(botonAnalizar + grilla.getRows() == botonCamino){
            return true;
        }
        else if(botonAnalizar - grilla.getRows() == botonCamino){
            return true;
        }
        
        return false;
    }
    
    /**
     * Metodo para agregar elementos al camino
     * @param casilla 
     */
    public void agregarCamino(String casilla){
        camino.add(casilla);
        //System.out.println("Camino:");
        /*
        for(int i = 0; i < camino.size(); i++){
            System.out.println(camino.get(i));
        }
        */
    }
    
    /**
     * Metodo para retroceder en el camino
     * @param casilla 
     */
    public void retroceder(String casilla){
        while(!camino.get(camino.size()-1).equals(casilla)){
            
            Iterator iterador = casillas.entrySet().iterator();
            while(iterador.hasNext()){
                Map.Entry auxiliar = (Map.Entry)iterador.next();
                if(casillas.get(auxiliar.getKey()).getBoton().getName().equals(camino.get(camino.size()-1))){
                    casillas.get(auxiliar.getKey()).cambiarColor(Color.WHITE);
                    camino.remove(camino.size()-1);
                    break;
                    //casillas.get(auxiliar.getKey()).cambiarColor(Color.WHITE);
                }
            }
            
        }
    }
    
    /**
     * Metodo para cambiar el estado del camino de las cabezas
     * @param color
     * @param estado 
     */
    public void caminoCompletado(Color color, boolean estado){
        Iterator iterador = casillas.entrySet().iterator();
        while(iterador.hasNext()){
            Map.Entry auxiliar = (Map.Entry)iterador.next();
            if(casillas.get(auxiliar.getKey()).getColor().equals(color) && casillas.get(auxiliar.getKey()).isCabeza()){
                casillas.get(auxiliar.getKey()).setCaminoCompleto(estado);
                //casillas.get(auxiliar.getKey()).cambiarColor(Color.WHITE);
            }
        }
    }
    
    /**
     * Metodo para resetear el color de las casillas
     * @param color 
     */
    public void resetearCasillas(Color color){
        Iterator iterador = casillas.entrySet().iterator();
        while(iterador.hasNext()){
            Map.Entry auxiliar = (Map.Entry)iterador.next();
            if(casillas.get(auxiliar.getKey()).getColor().equals(color) && !casillas.get(auxiliar.getKey()).isCabeza()){
                casillas.get(auxiliar.getKey()).cambiarColor(Color.WHITE);
            }
            else if(casillas.get(auxiliar.getKey()).isCabeza() && casillas.get(auxiliar.getKey()).getColor().equals(color)){
                casillas.get(auxiliar.getKey()).setCaminoCompleto(false);
            }
        }
    }
    
    /**
     * Metodo para remover los botones del panel
     */
    public void eliminarCasillas(){
        Iterator iterador = casillas.entrySet().iterator();
        while(iterador.hasNext()){
            Map.Entry auxiliar = (Map.Entry)iterador.next();
            remove(casillas.get(auxiliar.getKey()).getBoton());
        }
        casillas.clear();
    }
}
