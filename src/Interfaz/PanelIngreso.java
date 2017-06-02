
package Interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Cuchurrumi
 */
public class PanelIngreso extends JPanel{
    
    private JLabel jlNombre;    //  Etiqueta para el nombre
    
    private JLabel jlContrasena;  //  Etiqueta para la contraseña
    
    private JTextField txtNombre;   //  Texto para el nombre
    
    private JPasswordField txtContrasena;   //Texto para la contraseña
    
    private JButton btnIngresar;    //  Boton para habilitar el ingreso
    
    private JButton btnRegistrarse; //  Boton para habilitar el registro

    private Ventana ventana;    //  Objeto de la clase ventana
    
    private DialogoCrearCuenta dialogoCrearCuenta;  //  Objeto del dialogo para crear una cuenta
    
    private VentanaJuego ventanaJuego;  //  Objeto de la ventana de juego
    
    /**
     * Constructor
     * @param ventana 
     */
    public PanelIngreso(Ventana ventana) {
        
        this.ventana = ventana;
        dialogoCrearCuenta = new DialogoCrearCuenta(ventana);
        configurar();
    }
    
    /**
     * Configurar el panel
     */
    public void configurar(){
        setLayout(null);
        setBackground(Color.WHITE);
        agregar();
    }
    
    /**
     * Agregar elementos a el panel
     */
    public void agregar(){
        Font letra = new Font("Microsoft Sans Serif", Font.PLAIN, 30);
        
        int ancho = (int) ventana.getSize().getWidth();
        int alto = 40;
        
        jlNombre = new JLabel("Nombre:");
        jlNombre.setBounds(ancho/6, 100, 150, alto);
        jlNombre.setFont(letra);
        add(jlNombre);
        
        jlContrasena = new JLabel("Contraseña:");
        jlContrasena.setBounds((int)jlNombre.getBounds().getX(), (int)jlNombre.getBounds().getY()+60, 200, alto);
        jlContrasena.setFont(letra);
        add(jlContrasena);
        
        txtNombre = new JTextField();
        txtNombre.setBounds(ancho/2, 100, 200, alto);
        txtNombre.setFont(letra);
        add(txtNombre);
        
        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(ancho/2,  (int)jlContrasena.getBounds().getY(), 200, alto);
        txtContrasena.setFont(letra);
        add(txtContrasena);
        
        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(ancho/2, (int)jlContrasena.getBounds().getY()+60, 150, alto);
        btnIngresar.setFont(letra);
        btnIngresar.addActionListener(new EscucharBoton());
        add(btnIngresar);
        
        btnRegistrarse = new JButton("Crear Cuenta");
        btnRegistrarse.setBounds(ancho/2-75, (int)btnIngresar.getBounds().getY()+80, 150, alto-10);
        btnRegistrarse.setFont(new Font("Calibri", Font.ITALIC, 20));
        btnRegistrarse.addActionListener(new EscucharBoton());
        add(btnRegistrarse);
    }
    
    /**
     * Clase para escuchar los botones
     */
    class EscucharBoton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent evento) {
            if(evento.getActionCommand().equals("Ingresar")){
                System.out.println("Ingresar");
                if(validarIngreso()){
                    for(int i = 0; i < ventana.getJugadores().size(); i++){
                        if(ventana.getJugadores().get(i).getNombre().equals(txtNombre.getText().trim())){
                            if(ventana.getJugadores().get(i).getNivelActual() == 4){
                                int puntajeTotal = ventana.getJugadores().get(i).getPuntajes()[0] + ventana.getJugadores().get(i).getPuntajes()[1] + ventana.getJugadores().get(i).getPuntajes()[2];
                                JOptionPane.showMessageDialog(null, ventana.getJugadores().get(i).getNombre()+", Ya ha pasado todos los niveles! \nSu record es de: "+puntajeTotal, "Juego Colores", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else{
                                ventana.setVisible(false);
                                ventanaJuego = new VentanaJuego(ventana.getJugadores().get(i), ventana);
                                ventanaJuego.setVisible(true);
                                break;
                            }
                            
                        }
                    }
                    
                    txtNombre.setText("");
                    txtContrasena.setText("");
                }
            }
            else if (evento.getActionCommand().equals("Crear Cuenta")){
                txtNombre.setText("");
                txtContrasena.setText("");
                System.out.println("Crear");
                dialogoCrearCuenta.setVisible(true);
            }
        }
    }
    
    /**
     * Validar los datos para el ingreso
     * @return 
     */
    public boolean validarIngreso(){
        jlNombre.setForeground(Color.BLACK);
        jlContrasena.setForeground(Color.BLACK);
        
        String errores = "";
        boolean validar = true;
        
        String nombre = txtNombre.getText().trim();
        String contrasena = String.valueOf(txtContrasena.getPassword());
        if(!ventana.getJugadores().isEmpty()){
            for(int i = 0; i < ventana.getJugadores().size(); i++){
                if(ventana.getJugadores().get(i).getNombre().equals(nombre)){
                    if(!ventana.getJugadores().get(i).getContrasena().equals(contrasena)){
                        errores += "- Combinacion de nombre y contraseña no encontrado";
                        jlNombre.setForeground(Color.RED);
                        jlContrasena.setForeground(Color.RED);
                        validar = false;
                    }
                    break;
                }
                if(i == ventana.getJugadores().size()-1){
                    jlNombre.setForeground(Color.RED);
                    jlContrasena.setForeground(Color.RED);
                    errores += "- Combinacion de nombre y contraseña no encontrado";
                    validar = false;
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "No hay usuarios registrados", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            validar = false;
        }
        
        
        if(!errores.equals("")){
            JOptionPane.showMessageDialog(null, errores, "Aviso!", JOptionPane.WARNING_MESSAGE);
        }
        return validar;
    }
    
}
