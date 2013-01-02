package Vista;

import Controlador.EventosPrincipal;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Principal extends JFrame  {
    //menu
    private JMenuBar menuBar=new JMenuBar();
    private JMenu opcionesarchivoMenu=new JMenu("Archivo");
    private JMenuItem mnumultiplayer=new JMenuItem("Nuevo Juego Multijugador");
    private JMenuItem mnuvsmaquina=new JMenuItem("Nuevo juego vs maquina");
    private JMenuItem mnusalir=new JMenuItem("Salir");
    private JMenu mnuextras=new JMenu("Extras");
    private JMenuItem mnuacerca=new JMenuItem("Acerca de...");
    private JMenuItem mnucaballo=new JMenuItem("Salto del caballo");
    private JMenuItem mnupuntajes=new JMenuItem("Puntajes");
    private ImageIcon fondo=new ImageIcon("imagenes/principal.jpg");
    private JLabel lblfondo=new JLabel();
    private EventosPrincipal eventos=new EventosPrincipal(this);
     
    /**
     * Constructor principal sin parámetros
     */
    public Principal(){
        //listeners
        this.mnumultiplayer.addActionListener(this.eventos);
        this.mnuvsmaquina.addActionListener(this.eventos);
        this.mnusalir.addActionListener(this.eventos);
        this.mnucaballo.addActionListener(this.eventos);
        this.mnuacerca.addActionListener(this.eventos);
        this.mnupuntajes.addActionListener(this.eventos);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(new Point(300,80));
        this.setVisible(true);
        this.setTitle("Chess Box");
        this.setSize(500,500);
        //menu
        this.menuBar.add(this.opcionesarchivoMenu);
        this.opcionesarchivoMenu.add(this.mnumultiplayer);
        this.opcionesarchivoMenu.add(this.mnuvsmaquina);
        this.menuBar.add(this.mnuextras);
        this.mnuextras.add(this.mnucaballo);this.opcionesarchivoMenu.add(this.mnusalir);
        this.menuBar.add(this.mnuacerca);
        this.mnuextras.add(this.mnupuntajes);
        this.setJMenuBar(menuBar);

        //imagen de fondo
        while(lblfondo.getIcon()==null)
            lblfondo.setIcon(fondo);
        this.add(lblfondo);
    }
    
    /**
     * Método get menú acerca de...
     * @return mnuacerca
     */
    
    public JMenuItem getMnuacerca(){
        return this.mnuacerca;
    }
    
    /**
     * Método get menú multijugador
     * @return mnumultiplayer
     */
    
    public JMenuItem getMnumultiplayer(){
        return this.mnumultiplayer;
    }
    
    /**
     * Método get menú vs maquina.
     * @return mnuvsmaquina
     */
    
    public JMenuItem getMnuvsmaquina(){
        return this.mnuvsmaquina;
    }
     
    /**
     * Método get menú salir
     * @return salir
     */
    
    public JMenuItem getMnusalir(){
        return this.mnusalir;
    }
    
    /**
     * Método get menú mov caballo
     * @return mnucaballo
     */
    
    public JMenuItem getMnucaballo(){
        return this.mnucaballo;
    }
    
    /**
     * Método get menú puntajes
     * @return mnupuntajes
     */
    
    public JMenuItem getMnuPuntajes(){
        return this.mnupuntajes;
    }
    
}
