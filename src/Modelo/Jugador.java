/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.io.Serializable;

/**
 *
 * @author oscar
 */
public class Jugador implements Serializable{
private String nombre;
private int puntaje=0;

public Jugador(String en, int pun){
    this.nombre=en;
    this.puntaje=pun;
}
    public Jugador() {
    }
public void setNombre(String enom){
    this.nombre=enom;
}
public void setPuntaje(int epunt){
this.puntaje=epunt;
}
public String getNombre(){
    return this.nombre;
}
public int getPuntaje(){
return this.puntaje;
}

}
