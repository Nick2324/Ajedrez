
package Vista;

import Modelo.Jugador;
import Modelo.ListaPuntajes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class Puntajes{
private ListaPuntajes l=new ListaPuntajes();
private ArrayList<Jugador> lista =new ArrayList<Jugador>();
private JLabel lblptjes=new JLabel("");
private String p="";
 /**
    *Constructor puntajes sin parámetros
    */
public Puntajes(){



String puntajes = "";int puntos = 0;
        try {
            lista = l.deserializar("Puntajes.txt");
        } catch (IOException ex) {
            Logger.getLogger(Puntajes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Puntajes.class.getName()).log(Level.SEVERE, null, ex);
        }
            Iterator it=lista.iterator();
            Jugador[] j=new Jugador[lista.size()];
            int i=0;
            while(it.hasNext()){
                j[i]=((Jugador)(it.next()));
            i++;
            }
            for(int h=0;h<lista.size();h++){
p+=j[h].getNombre()+"     "+j[h].getPuntaje()+"\n";
        

            }
JOptionPane.showConfirmDialog(null,p, "Puntajes", JOptionPane.DEFAULT_OPTION);
            System.out.println(puntajes);

    }

    }


