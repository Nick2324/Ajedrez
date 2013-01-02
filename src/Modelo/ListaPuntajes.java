package Modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * @author oscar
 */

public class ListaPuntajes {
    private ArrayList<Jugador> lista =new ArrayList<Jugador>();

    /**
     * Escribe a archivo el objeto Jugador
     *
     * @param archivo Nombre de archivo
     * @throws IOException Excepción de entrada/salida
     */
    
    public void serializar (String archivo) throws IOException {
        ObjectOutputStream salida = new ObjectOutputStream(
                new FileOutputStream(archivo));
        salida.writeObject(this.lista);
    }
    
    /**
     *
     * @param archivo Nombre de archivo
     * @return Objeto Deserializado
     * @throws IOException Excepción de entrada/salida
     * @throws ClassNotFoundException Excepción de clase no encontrada
     */
    
    public ArrayList<Jugador> deserializar (String archivo) throws IOException,
            ClassNotFoundException {
        ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo));
        return (ArrayList<Jugador>) entrada.readObject();
    }
    
    public void setLista(ArrayList<Jugador> l){
        this.lista=l;
    }
    
    public ArrayList<Jugador> getLista(){
        return this.lista;
    }
    
}
