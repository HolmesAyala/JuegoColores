
package Logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Anggy Arguello
 */
public class Utilitaria {
    
    /**
     * Metodo para leer el texto de un fichero
     * @param direccion
     * @return 
     */
    public static String leer(String direccion) throws FileNotFoundException, IOException {
        //List<Persona> personas = new ArrayList<Persona>();
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String texto = "";

      
        // Apertura del fichero y creacion de BufferedReader para poder
        // hacer una lectura comoda (disponer del metodo readLine()).
        archivo = new File (direccion);
        fr = new FileReader (archivo);
        br = new BufferedReader(fr);

        // Lectura del fichero
        String linea;
        while((linea=br.readLine())!=null){
            if(!linea.equals("")){
                texto += linea + "\n";
               //String [] vector = linea.split(";");
               //Persona persona = new Persona(vector[0], Double.parseDouble(vector[1]), vector[2], EnumProfesion.valueOf(vector[3]), EnumGenero.valueOf(vector[4]), fechaString(vector[5]));
               //personas.add(persona);
               //System.out.println(persona.toString());
            }
        }

        // En el finally cerramos el fichero, para asegurarnos
        // que se cierra tanto si todo va bien como si salta 
        // una excepcion.
        try{                    
           if( null != fr ){   
              fr.close();     
           }                  
        }catch (Exception e2){ 
           e2.printStackTrace();
        }finally{
            return texto;
        }
   }
    
    /**
     * Metodo para escribir en un fichero
     * @param texto
     * @param direccion 
     */
    public static void Escribir(String texto, String direccion) {
        
        FileWriter fichero = null;
        PrintWriter pw = null;
        
        try
        {
            fichero = new FileWriter(direccion,false);
            pw = new PrintWriter(fichero);

            for (int i = 0; i < texto.length(); i++){
                if(texto.charAt(i) != '\n'){
                    pw.print(texto.charAt(i));
                }
                else{
                    pw.println();
                }
                //pw.print(texto.charAt(i));
                //pw.println("Linea " + i);
            }
                

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
    
}

