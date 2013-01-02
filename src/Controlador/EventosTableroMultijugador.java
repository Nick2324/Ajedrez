package Controlador;

import Modelo.Campo;
import Modelo.Ficha;
import Modelo.Reloj;
import Vista.Principal;
import Vista.TableroMultijugador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class EventosTableroMultijugador implements MouseListener,ActionListener{
    private TableroMultijugador tab;
    private Campo camp=new Campo();
    private ArrayList<Ficha> muertas=new ArrayList<Ficha>();
    static int resfila=0,rescolumna=0,clickfila=0,clickcolumna=0;
    private Reloj r=new Reloj();


    public EventosTableroMultijugador(TableroMultijugador etab, Campo ecm){
        this.tab=etab;
        this.camp=ecm;
    }

    public void mousePressed(MouseEvent e) {
         clickfila=tab.getCoordenadas((JPanel)e.getComponent())[0];
         clickcolumna=tab.getCoordenadas((JPanel)e.getComponent())[1];
            //System.out.println(clickfila+" "+clickcolumna);

    }

    public void mouseReleased(MouseEvent e) {
        if(camp.get_Ficha(clickfila, clickcolumna)!=null && 
                tab.getReloj().getTurno()%2!=0 && 
                camp.get_Ficha(clickfila, clickcolumna).getBando()==0){
        }else if(camp.get_Ficha(clickfila, clickcolumna)!=null &&
                tab.getReloj().getTurno()%2==0 &&
                camp.get_Ficha(clickfila, clickcolumna).getBando()==1){
                JOptionPane.showConfirmDialog(null,"No es su turno", 
                        "Error", JOptionPane.DEFAULT_OPTION);
        }else{
            Ficha[][] campoAlterno=new Ficha[8][8];
            for(int i=0;i<8;i++)
                for(int j=0;j<8;j++)
                    campoAlterno[i][j]=camp.get_Ficha(i,j);

        if(camp.get_Ficha(clickfila, clickcolumna)!=null ){
            campoAlterno[clickfila][clickcolumna].mover(campoAlterno, 
                    resfila, rescolumna,muertas);
            boolean cambioCampo=false;
            for(int i=0;i<8;i++)
                for(int j=0;j<8;j++)
                    if(campoAlterno[i][j]!=camp.get_Ficha(i, j)){
                        cambioCampo=true;
                        i=8;
                        break;
                    }
            if(cambioCampo){
                camp.setTablero(campoAlterno);
                for(int i=0;i<8;i++)
                    for(int j=0;j<8;j++)
                        if(campoAlterno[i][j]!=null && campoAlterno[i][j].getImagen()!=null)
                            this.tab.get_LblImgFicha()[i][j].setIcon(campoAlterno[i][j].getImagen());
                        else this.tab.get_LblImgFicha()[i][j].setIcon(null);
                camp.imprimir_mapa();
                tab.getReloj().turno_nuevo();
            }
        }else
              JOptionPane.showConfirmDialog(null,"Movimiento inválido",
                      "Error", JOptionPane.DEFAULT_OPTION);
        }
    }

    public void mouseEntered(MouseEvent e) {
        resfila=tab.getCoordenadas((JPanel)e.getComponent())[0];
        rescolumna=tab.getCoordenadas((JPanel)e.getComponent())[1];
    }

    public void mouseExited(MouseEvent e) {}
    
    public void mouseClicked(MouseEvent e) {}

    public void actionPerformed(ActionEvent e) {
        this.tab.dispose();
        new Principal();
    }
    
}
