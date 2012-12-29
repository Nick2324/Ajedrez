/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.awt.Color;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author oscar
 */public class Reloj extends Thread {
  private int i=0;
  private int tiempo;
    private int turno=0;
    private JLabel lbltiempo, lblturno;
    Font f = new Font( "Helvetica",Font.ITALIC,25 );


    public void run() {
         this.lbltiempo.setFont(f);
        while(true){
    int temp=tiempo;
        try {
            while(i<=tiempo){
            this.sleep(1000);
            this.lbltiempo.setText(" |"+temp+"|   "+getjuegan());
            i++;
            temp--;
        }


turno++;
i=0;
        }catch (InterruptedException ex) {
            Logger.getLogger(Contador.class.getName()).log(Level.SEVERE, null, ex);
        }}
    }
    public void setTiempo(int et){
    this.tiempo=et;
   }


    public void setLabeltiempo(JLabel lbltiempo) {
this.lbltiempo=lbltiempo;

 }

    public void setLabelturno(JLabel lblturno) {
this.lblturno=lblturno;
    }

    public int getTurno() {
        return this.turno;
    }
    public void turno_nuevo(){
    this.i=this.tiempo;

    }

    private String getjuegan() {
        String bando="";
if(turno%2==0){
    bando="Juegan las blancas";
}
else{
            bando="Juegan las negras";

}
    return bando;}

    }
