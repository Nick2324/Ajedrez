package Controlador;

import Modelo.Campo;
import Modelo.Ficha;
import Modelo.Reloj;
import Vista.Principal;
import Vista.TableroMaquina;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class EventosTableroMaquina implements MouseListener,ActionListener{
    private TableroMaquina tab;
    private Campo camp=new Campo();
    private ArrayList<Ficha> muertas=new ArrayList<Ficha>();
    static int resfila=0,rescolumna=0,clickfila=0,clickcolumna=0,punt=0;
    private Reloj r=new Reloj();

    public EventosTableroMaquina(TableroMaquina etab, Campo ecm){
        this.tab=etab;
        this.camp=ecm;
    }

    public void mousePressed(MouseEvent e) {
         clickfila=tab.getCoordenadas((JPanel)e.getComponent())[0];
         clickcolumna=tab.getCoordenadas((JPanel)e.getComponent())[1];
            //System.out.println(clickfila+" "+clickcolumna);
    }

    public void mouseReleased(MouseEvent e) {
        boolean movido=false;
        Ficha[][] campoAlterno=new Ficha[8][8];
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                campoAlterno[i][j]=camp.get_Ficha(i,j);

        if(camp.get_Ficha(clickfila, clickcolumna)!=null){
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
                this.tab.get_LblImgFicha()[resfila][rescolumna].setIcon(this.tab.get_LblImgFicha()[clickfila][clickcolumna].getIcon());
                this.tab.get_LblImgFicha()[clickfila][clickcolumna].setIcon(null);
                movido=true;
                camp.imprimir_mapa();
            }
        }else
             JOptionPane.showConfirmDialog(null,"Movimiento inválido", 
                     "Error", JOptionPane.DEFAULT_OPTION);
        if(movido)// MOV MAQUINA
            cambiarficha();
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

    /**
     * Método para simular mov del jugador y enviar al modelo para correr fichas
     */

    public  void cambiarficha(){
        System.out.println("Cambiar Ficha");
        // MOV MAQUINA
        int fil=0;
        int col=0;
        int movfil=(int) (Math.random() * 8);
        int movcol=(int) (Math.random() * 8);
        while(camp.get_Ficha(fil, col)==null || 
                camp.get_Ficha(fil, col).getBando()==0 ){
            fil=(int) (Math.random() * 8);
            col=(int) (Math.random() * 8);
            if(camp.get_Ficha(fil, col)!=null &&
                    camp.get_Ficha(fil, col).getBando()==1){
                System.out.println("Ficha "+ fil+" " +col);
                Ficha[][] temptab=this.camp.get_Ficha();
                int i=0;
                while(temptab[fil][col].evaluarMovimiento(temptab, movfil,
                        movcol, muertas)==false && i!=1000){
                    temptab=this.camp.get_Ficha();
                    if(temptab[fil][col].evaluarMovimiento(temptab, movfil, 
                            movcol, muertas)==true){
                        System.out.println("Se puede mover a "+movfil+""
                                + " "+movcol);
                    }
                    i++;
                    if(i>500){
                        cambiarficha();
                        i=1000;
                    }
                    movfil=(int) (Math.random() * 8);
                    movcol=(int) (Math.random() * 8);
                }
            }
        }
        Ficha[][] temptab2=this.camp.get_Ficha();
        if(temptab2[fil][col].evaluarMovimiento(temptab2, movfil,
                movcol, muertas)){
            this.camp.get_Ficha(fil, col).mover(this.camp.get_Ficha(),
                    movfil, movcol, muertas);
            this.tab.get_LblImgFicha()[movfil][movcol].setIcon(
                    this.tab.get_LblImgFicha()[fil][col].getIcon());
            this.tab.get_LblImgFicha()[fil][col].setIcon(null);
            this.camp.imprimir_mapa();
        }
        fil=0;
        col=0;
    }

}
