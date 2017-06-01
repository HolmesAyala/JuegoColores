
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
import javax.swing.JPanel;

/**
 *
 * @author Cuchurrumi
 */
public class PanelJuego extends JPanel{
    
    //private List<Casilla> casillas;
    
    private Map<String, Casilla> casillas;
    
    private GridLayout grilla;
    
    private String cabezas;
    
    private Map<String, Color> colores;
    
    private Color colorActual;
    
    private Jugador jugador;
    
    private List<String> camino;

    public PanelJuego(Jugador jugador) {
        this.jugador = jugador;
        configurar();
        agregar();
    }
    
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
    
    public void agregar(){
        crearColores();
        colorActual = Color.WHITE;
        
        //casillas = new ArrayList<>();
        casillas = new HashMap<String, Casilla>();
        camino = new ArrayList<String>();
        
        EscucharBoton escucharBoton = new EscucharBoton();
        EscucharMouse escucharMouse = new EscucharMouse();
        
        setName("panel");
        addMouseListener(escucharMouse);
        
        for(int i = 0; i < grilla.getRows()*grilla.getColumns(); i++){
            JButton boton = new JButton();
            boton.setName("Boton "+(i+1));
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
    
    public void nivel1(){
        cabezas = "1202300000403500001000045";
    }
    
    public void nivel2(){
        cabezas = "0000000001203000403505000000004016002006000000000";
    }
    
    public void nivel3(){
        cabezas = "000000000000000000003002010004000020000000000080100005000000840070000070360650000";
    }
    
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
    
    class EscucharBoton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent evento) {
            
        }
        
    }
    
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
            if(!evento.getComponent().getName().equals("panel")){
               logicaPintado(evento);
            }
            
        }

        @Override
        public void mouseExited(MouseEvent evento) {
            if(evento.getComponent().getName().equals("panel")){
                System.out.println("Sali del panel");
            }
        }
        
    }
    
    public void logicaPintado(MouseEvent evento){
        //  Si la casilla es cabeza
        if(casillas.get(evento.getComponent().getName()).isCabeza()){
            //  Si es diferente al color actual
            if(!casillas.get(evento.getComponent().getName()).getColor().equals(colorActual)){
                //  Si la casilla no tiene el camino completo cambie el color actual
                if(!casillas.get(evento.getComponent().getName()).isCaminoCompleto()){
                    colorActual = casillas.get(evento.getComponent().getName()).getColor();
                    resetearCasillas(casillas.get(evento.getComponent().getName()).getColor());
                    camino.clear();
                    agregarCamino(evento.getComponent().getName());
                }
                else{
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
                    caminoCompletado(casillas.get(evento.getComponent().getName()).getColor(), true);
                    camino.clear();
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
                        retroceder(evento.getComponent().getName());
                    }
                }
                else{
                    //  Si la casilla es de color blanco
                    agregarCamino(evento.getComponent().getName());
                }
            }
        }
        
        if(!colorActual.equals(Color.WHITE)){
            casillas.get(evento.getComponent().getName()).cambiarColor(colorActual);
        }
    }
    
    public void agregarCamino(String casilla){
        camino.add(casilla);
        System.out.println("Camino:");
        for(int i = 0; i < camino.size(); i++){
            System.out.println(camino.get(i));
        }
    }
    
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
    
}
