
package Logica;

/**
 *
 * @author Cuchurrumi
 */
public class Jugador {
    
    private String nombre;
    
    private String contrasena;
    
    private String foto;
    
    private int nivelActual;
    
    private int puntajes[] = {0, 0, 0};

    public Jugador(String nombre, String contrasena, String foto, int nivelActual) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.foto = foto;
        this.nivelActual = nivelActual;
    }
    
    @Override
    public String toString(){
        return nombre+";"+contrasena+";"+foto+";"+String.valueOf(nivelActual)+";"+String.valueOf(puntajes[0])+";"+String.valueOf(puntajes[1])+";"+String.valueOf(puntajes[2])+";\n";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getNivelActual() {
        return nivelActual;
    }

    public void setNivelActual(int nivelActual) {
        this.nivelActual = nivelActual;
    }

    public int[] getPuntajes() {
        return puntajes;
    }

    public void setPuntajes(int[] puntajes) {
        this.puntajes = puntajes;
    }
    
}
