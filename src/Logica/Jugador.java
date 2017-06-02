
package Logica;

/**
 *
 * @author Cuchurrumi
 */
public class Jugador {
    
    private String nombre;  //  Nombre del jugador
    
    private String contrasena;  //  Contraseña
    
    private String foto;    //  Ruta de la foto
    
    private int nivelActual;    //  Nivel en el que va
    
    private int puntajes[] = {0, 0, 0}; //  Puntajes de cada nivel

    /**
     * Constructor
     * @param nombre
     * @param contrasena
     * @param foto
     * @param nivelActual 
     */
    public Jugador(String nombre, String contrasena, String foto, int nivelActual) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.foto = foto;
        this.nivelActual = nivelActual;
    }
    
    /**
     * Obtener la informacion del jugador
     * @return 
     */
    @Override
    public String toString(){
        return nombre+";"+contrasena+";"+foto+";"+String.valueOf(nivelActual)+";"+String.valueOf(puntajes[0])+";"+String.valueOf(puntajes[1])+";"+String.valueOf(puntajes[2])+";*\n";
    }

    /**
     * Obtener el nombre
     * @return 
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Cambiar el nombre
     * @param nombre 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtener la contraseña
     * @return 
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Cambiar la contraseña
     * @param contrasena 
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Obtener la ruta de la foto
     * @return 
     */
    public String getFoto() {
        return foto;
    }

    /**
     * Cambiar la ruta de la foto
     * @param foto 
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * Obtener el nivel actual
     * @return 
     */
    public int getNivelActual() {
        return nivelActual;
    }

    /**
     * Cambiar el nivel actual
     * @param nivelActual 
     */
    public void setNivelActual(int nivelActual) {
        this.nivelActual = nivelActual;
    }

    /**
     * Obtener los puntajes
     * @return 
     */
    public int[] getPuntajes() {
        return puntajes;
    }

    /**
     * Cambiar los puntajes
     * @param puntajes 
     */
    public void setPuntajes(int[] puntajes) {
        this.puntajes = puntajes;
    }
    
}
