package Vista;

import Controlador.EventosTableroMultijugador;
import Modelo.Alfil;
import Modelo.Caballo;
import Modelo.Campo;
import Modelo.Contador;
import Modelo.Peon;
import Modelo.Reina;
import Modelo.Reloj;
import Modelo.Rey;
import Modelo.Torre;
import java.awt.*;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TableroMultijugador extends JFrame  {
    private  Reloj c=new Reloj();
    private JPanel tablero[][]=new JPanel[8][8];
    private JPanel panelcentral=new JPanel(new GridLayout(8,8));
    private JPanel panelsuperior=new JPanel(new GridLayout(1,2));
    private JLabel lblimgficha[][]=new JLabel[8][8];
    private JLabel lblturno=new JLabel("");
    private JLabel lbltiempo=new JLabel("");
    private Campo camp=new Campo();
    
    //menu
    
    private JMenuBar menuBar=new JMenuBar();
    private JMenu opcionesarchivoMenu=new JMenu("Archivo");
    private JMenuItem mnusalir=new JMenuItem("Salir");
    private EventosTableroMultijugador eventos =
            new EventosTableroMultijugador(this,camp);
    private int segundos=-1;
    
    public TableroMultijugador(){
        while(segundos<=0){
            try{ 
                segundos=Integer.parseInt(JOptionPane.showInputDialog(
                        "Ingrese el tiempo en segundos por jugada"));
                c.setTiempo(segundos);
            }catch(NumberFormatException e){
                JOptionPane.showConfirmDialog(null,"Debe insertar un tiempo por"
                        + " jugada", "Error", JOptionPane.DEFAULT_OPTION);
            }
        }
        camp.get_Ficha()[7][0]=new Torre(7,0,true,1,
                "imagenes/Torre_negro.png");
        camp.get_Ficha()[7][7]=new Torre(7,7,true,1,
                "imagenes/Torre_negro.png");
        camp.get_Ficha()[7][1]=new Caballo(7,1,true,1,
                "imagenes/Caballo_negro.png");
        camp.get_Ficha()[7][6]=new Caballo(7,6,true,1,
                "imagenes/Caballo_negro.png");
        camp.get_Ficha()[7][4]=new Rey(7,4,true,1,"imagenes/Rey_negro.png");

        for(int i=0;i<8;i++){
            camp.get_Ficha()[6][i]=new Peon(6,i,true,1,
                    "imagenes/Peon_negro.png");
            camp.get_Ficha()[1][i]=new Peon(1,i,true,0,
                    "imagenes/Peon_blanco.png");
        }
        camp.get_Ficha()[7][2]=new Alfil(7,2,true,1,"imagenes/Alfil_negro.png");
        camp.get_Ficha()[7][5]=new Alfil(7,5,true,1,"imagenes/Alfil_negro.png");
        camp.get_Ficha()[7][3]=new Reina(7,3,true,1,"imagenes/Reina_negro.png");
        camp.get_Ficha()[0][0]=new Torre(0,0,true,0,"imagenes/Torre_blanco.png");
        camp.get_Ficha()[0][7]=new Torre(0,7,true,0,"imagenes/Torre_blanco.png");
        camp.get_Ficha()[0][1]=new Caballo(0,1,true,0,
                "imagenes/Caballo_blanco.png");
        camp.get_Ficha()[0][6]=new Caballo(0,6,true,0,
                "imagenes/Caballo_blanco.png");
        camp.get_Ficha()[0][4]=new Rey(0,4,true,0,"imagenes/Rey_blanco.png");
        camp.get_Ficha()[0][2]=new Alfil(0,2,true,0,
                "imagenes/Alfil_blanco.png");
        camp.get_Ficha()[0][5]=new Alfil(0,5,true,0,
                "imagenes/Alfil_blanco.png");
        camp.get_Ficha()[0][3]=new Reina(0,3,true,0,"imagenes/Reina_blanco.png");
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++){
                tablero[i][j]=new JPanel();
                lblimgficha[i][j]=new JLabel();
                    if(j%2!=0 && i%2==0){
                    tablero[i][j].setBackground(Color.BLACK);
                    lblimgficha[i][j].setBackground(Color.BLACK);
                    }
                    if(i%2!=0 && j%2==0){
                    tablero[i][j].setBackground(Color.BLACK);
                    lblimgficha[i][j].setBackground(Color.BLACK);
                    }
                    tablero[i][j].addMouseListener(this.eventos);
            }
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                if(camp.get_Ficha(i, j)!=null)
                    lblimgficha[i][j].setIcon(camp.get_Ficha(i ,j).getImagen());
     
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++){
                tablero[i][j].add(this.lblimgficha[i][j]);
                panelcentral.add(tablero[i][j]);
            }
        panelsuperior.add(lbltiempo);
        panelsuperior.add(lblturno);
        this.add(panelsuperior,BorderLayout.NORTH);
        this.add(panelcentral,BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(new Point(300,60));
        this.setVisible(true);
        this.setTitle("Chess Box");
        //this.setResizable(false);
        this.setSize(800,800);
        //menu
        this.mnusalir.addActionListener(this.eventos);
        this.menuBar.add(this.opcionesarchivoMenu);
        this.opcionesarchivoMenu.add(this.mnusalir);
        this.setJMenuBar(menuBar);
        c.setLabeltiempo(this.lbltiempo);
        c.start();

        panelsuperior.setBackground(new Color(0xCDEB8B));
    } 
    
    /**
     * Método get JLabelFicha
     * @return lblimgficha
     */
        
    public JLabel[][] get_LblImgFicha(){
        return this.lblimgficha;
    }
    
    /**
     * Método get coordenadas de movimiento
     * @return coordenadas
     */
    
    public int[] getCoordenadas(JPanel campo) {
        int [] coordenadas = new int[2];
        for (int i=0; i < 8; i++)
            for (int j=0; j < 8; j++)
                if (this.tablero[i][j] == campo) {
                        coordenadas[0] = i;
                        coordenadas[1] = j;
                }
        return coordenadas;
    }
    
    public Reloj getReloj(){
        return this.c;
    }

}
