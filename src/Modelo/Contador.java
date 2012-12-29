/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author oscar
 */
public class Contador implements Runnable{
private Thread ti=new Thread(this);
private int tiempo;
private JLabel lbltiempo, lblturno;
private int turno=0;
private int i=1;

public void setLabeltiempo(JLabel etlbl){
this.lbltiempo=etlbl;
}
public void setLabelturno(JLabel etlbl){
this.lblturno=etlbl;
}


public void setTiempo(int et){
this.tiempo=et;}
    public void run() {
                    int temp=tiempo;

        try {
            calcularturno();
            while(i<=tiempo){
            ti.sleep(1000);
            i++;
            temp--;
        }
        turno++;
        run();
        }catch (InterruptedException ex) {
            Logger.getLogger(Contador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public void calcularturno(){
}
public void setTurno(int et){
this.turno=et;}
public int getTurno(){
return this.turno;}
public void turno_blancas(){
   
}
public void turno_negras(){
 turno++;
 this.lblturno.setText("Juegan las negras");
 i=tiempo;

}

}

