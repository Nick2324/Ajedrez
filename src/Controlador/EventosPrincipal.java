package Controlador;

import Vista.Principal;
import Vista.Puntajes;
import Vista.TableroMaquina;
import Vista.TableroMultijugador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class EventosPrincipal implements ActionListener{
    Principal ventanaprincipal;
    
    public EventosPrincipal(Principal eprin){
        this.ventanaprincipal=eprin;
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==ventanaprincipal.getMnuacerca())
            JOptionPane.showConfirmDialog(null,"Desarrollado por: \n\n Oscar "
                    + "Ardila \n Mauricio Garcia", "Acerca de...", 
                    JOptionPane.DEFAULT_OPTION);
        if(e.getSource()==ventanaprincipal.getMnumultiplayer()){
            ventanaprincipal.dispose();
            new TableroMultijugador();
        }
        if(e.getSource()==ventanaprincipal.getMnuvsmaquina()){
            ventanaprincipal.dispose();
            new TableroMaquina();
        }
        if(e.getSource()==ventanaprincipal.getMnusalir())
            System.out.println("Salir");
        if(e.getSource()==ventanaprincipal.getMnucaballo())
            System.out.println("Caballo");
        if(e.getSource()==ventanaprincipal.getMnuPuntajes())
            new Puntajes();
    }
    
}