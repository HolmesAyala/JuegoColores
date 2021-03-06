
package Interfaz;

import Logica.Jugador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Cuchurrumi
 */
public class DialogoCrearCuenta extends JDialog{

    private JLabel jlNombre;    //  Etiqueta para el nombre
    
    private JLabel jlContrasena;    //  Etiqueta para la contraseña
    
    private JLabel jlfoto;  //  Etiqueta para la foto
    
    private JTextField txtNombre;   //  Caja de texto para el nombre
    
    private JPasswordField txtContrasena;   //  Caja de texto para la contraseña
    
    private JButton btnRegistrar;   //  Boton para habilitar el registro
    
    private JButton btnFoto;    //  Boton para habilitar la carga de la foto
    
    private Ventana ventana;    // Objeto de la clase ventana
    
    private FileNameExtensionFilter filtro; //  Filtro para la busqueda de fotos
    
    private String rutaImagen;  //  Ruta de la imagen del usuario
    
    private File archivoImagen; //  Archivo que guarda la imagen
    
    private JFileChooser ventanaImagen; //  Selector de archivos
    
    /**
     * Constructor
     * @param ventana 
     */
    public DialogoCrearCuenta(Ventana ventana) {
        this.ventana = ventana;
        configurar();
    }
    
    /**
     * Configuracion de la ventana
     */
    public void configurar(){
        setTitle("Crear cuenta");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(500, 450);
        setLocationRelativeTo(null);
        setLayout(null);
        
        agregar();
    }
    
    /**
     * Agregar elementos a la ventana
     */
    public void agregar(){
        Font letra = new  Font("Microsoft Sans Serif", Font.PLAIN, 25);
        
        jlNombre = new JLabel("Nombre:");
        jlNombre.setBounds(20, 20, 150, 35);
        jlNombre.setFont(letra);
        add(jlNombre);
        
        jlContrasena = new JLabel("Contraseña:");
        jlContrasena.setBounds(20, 90, 200, 35);
        jlContrasena.setFont(letra);
        add(jlContrasena);
        
        txtNombre = new JTextField();
        txtNombre.setBounds(200, 20, 200, 35);
        txtNombre.setFont(letra);
        add(txtNombre);
        
        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(200, 90, 200, 35);
        txtContrasena.setFont(letra);
        add(txtContrasena);
        
        btnFoto = new JButton("Cargar Foto");
        btnFoto.setBounds(20, 150, 200, 35);
        btnFoto.setFont(letra);
        btnFoto.addActionListener(new EscucharBoton());
        add(btnFoto);
        
        jlfoto = new JLabel();
        jlfoto.setBounds(20, 190, 200, 200);
        jlfoto.setFont(letra);
        ImageIcon imagen = new ImageIcon("Imagenes/AgregarImagen.png");
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(jlfoto.getWidth(), jlfoto.getHeight(), Image.SCALE_DEFAULT));
        jlfoto.setIcon(icono);
        //jlfoto.setHorizontalTextPosition();
        add(jlfoto);
        rutaImagen = "Imagenes/AgregarImagen.png";
        filtro = new FileNameExtensionFilter("Archivo de Imagen", "png", "jpg");
        
        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(230, 150, 200, 35);
        btnRegistrar.setFont(letra);
        btnRegistrar.addActionListener(new EscucharBoton());
        add(btnRegistrar);
    }
    
    /**
     * Clase que esta a la escucha de los botones
     */
    class EscucharBoton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent evento) {
            if(evento.getActionCommand().equals("Registrar")){
                System.out.println("Quiere Registrar");
                if(validarDatos()){
                    Jugador jugador = new Jugador(txtNombre.getText().trim(), String.valueOf(txtContrasena.getPassword()), rutaImagen, 1);
                    ventana.getJugadores().add(jugador);
                    ventana.escribirJugadores();
                    setVisible(false);
                    //  Limpiar dialogo
                    txtNombre.setText("");
                    txtContrasena.setText("");
                    rutaImagen = "Imagenes/AgregarImagen.png";
                    Icon icono = crearIcono(rutaImagen, jlfoto.getWidth(), jlfoto.getWidth());
                    jlfoto.setIcon(icono);
                }
            }
            else{
                //  Agregar la imagen
                ventanaImagen = new JFileChooser();
                ventanaImagen.setFileFilter(filtro);
                int opcion = ventanaImagen.showOpenDialog(null);
                //  Si le dan a abrir a un archivo
                if(opcion == JFileChooser.APPROVE_OPTION){
                    archivoImagen = ventanaImagen.getSelectedFile();
                    try{
                        rutaImagen = archivoImagen.toString();
                        //ImageIcon imagen = new ImageIcon(archivoImagen.toString());
                        //Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(jlfoto.getWidth(), jlfoto.getHeight(), Image.SCALE_DEFAULT));
                        Icon icono = crearIcono(rutaImagen, jlfoto.getWidth(), jlfoto.getWidth());
                        jlfoto.setText("");
                        jlfoto.setIcon(icono);
                    }catch(Exception error){
                        System.out.println("No se pudo cargar la imagen");
                    }
                }
            }
        }
        
    }
    
    /**
     * Metodo para validar los datos
     * @return 
     */
    public boolean validarDatos(){
        jlNombre.setForeground(Color.BLACK);
        jlContrasena.setForeground(Color.BLACK);
        boolean validar = true;
        
        //  Datos
        String nombre = txtNombre.getText().trim();
        
        String errores = "";
        //  Validar Nombre
        if(nombre.length() < 3){
            errores += "- El nombre debe tener almenos 3 caracteres\n";
            validar = false;
            jlNombre.setForeground(Color.RED);
        }
        else if(nombre.length() > 10){
            errores += "- El nombre debe tener mas de 10 caracteres\n";
            validar = false;
            jlNombre.setForeground(Color.RED);
        }
        else {
            if(!soloLetras(nombre)){
                errores += "- El nombre no debe tener caracteres especiales\n";
                validar = false;
                jlNombre.setForeground(Color.RED);
            }
            if(!ventana.getJugadores().isEmpty()){
                for(int i = 0; i < ventana.getJugadores().size(); i++){
                    if(ventana.getJugadores().get(i).getNombre().equals(nombre)){
                        errores += "- El nombre ya esta registrado\n";
                        validar = false;
                        jlNombre.setForeground(Color.RED);
                        break;
                    }
                }
            }
            
        }
        //  Validar Contraseña
        String contrasena = ""+String.valueOf(txtContrasena.getPassword());
        //System.out.println(contrasena+"\n");
        if(contrasena.length() < 5){
            errores += "- La contraseña debe ser de almenos 5 caracteres\n";
            validar = false;
            jlContrasena.setForeground(Color.RED);
        }
        else if(contrasena.length() > 10){
            errores += "- La contraseña no debe tener mas de 10 caracteres\n";
            validar = false;
            jlContrasena.setForeground(Color.RED);
        }
        else{
            for(int i = 0; i < contrasena.length(); i++){
                if(contrasena.charAt(i) == ';'){
                    errores += "- La contraseña no puede tener el caracter ';'\n";
                    validar = false;
                }
            }
        }
        
        if(!errores.equals("")){
            JOptionPane.showMessageDialog(null, errores, "Aviso!", JOptionPane.WARNING_MESSAGE);
        }
        
        return validar;
    }
    
    /**
     * Metodo para ver si solo hay letras en un string
     * @param texto
     * @return 
     */
    public boolean soloLetras(String texto){
        for(int i = 0; i < texto.length(); i++){
            if(texto.charAt(i) < 'A' || texto.charAt(i) > 'z' || (texto.charAt(i) > 'Z' && texto.charAt(i) < 'a')){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Metodo para crear un icono y adecuarlo a un label
     * @param ruta
     * @param ancho
     * @param alto
     * @return 
     */
    public Icon crearIcono(String ruta, int ancho, int alto){
        ImageIcon imagen = new ImageIcon(ruta);
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        return icono;
    }
}
