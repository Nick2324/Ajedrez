package Modelo;

import javax.swing.ImageIcon;
import java.util.ArrayList;
/**
 * 
 * @author nicolas
 */
public abstract class Ficha {
    protected int posicionf;
    protected int posicionc;
    protected boolean viva;
    protected int bando;
    protected ImageIcon imagen;
    /**
     * Constructor
     * @param posicionf
     * @param posicionc
     * @param viva
     * @param bando
     * @param rutaImagen
     */
    public Ficha(int posicionf,int posicionc,boolean viva,int bando,String rutaImagen){
        this.posicionf=posicionf;
        this.posicionc=posicionc;
        this.viva=true;
        this.bando=bando;
        imagen=new ImageIcon(rutaImagen);
    }
    /**
     * mover
     * Mueve la ficha al campo requerido si el movimiento es valido
     * @param campo
     * @param fila
     * @param columna
     * @param muertas
     */
    public void mover(Ficha[][] campo,int fila,int columna,ArrayList<Ficha> muertas){
        if(evaluarMovimiento(campo,fila,columna,muertas)){
            campo[posicionf][posicionc]=null;
            campo[fila][columna]=this;
            posicionf=fila;
            posicionc=columna;
            //System.out.println("Movi ficha");
        }
    }
    /**
     *evaluarMovimiento
     * Comprueba si el movimiento gestionado es valido o no
     * @param campo
     * @param fila
     * @param columna
     * @param muertas
     * @return boolean
     */
    public abstract boolean evaluarMovimiento(Ficha[][] campo,int fila,int columna,ArrayList<Ficha> muertas);
    public void comer(Ficha[][] campo,int fila, int columna,ArrayList<Ficha> muertas){
        campo[fila][columna].viva=false;
        muertas.add(campo[fila][columna]);
        campo[fila][columna]=null;
    }
    /**
     * evaluarCaminoRey
     * Evalua si el rey, segun el campo "abstracto", esta en una posicion valida o no
     * @param Inicialcampo
     * @param fila
     * @param columna
     * @param fichaMovida
     * @return fichaAtacante
     */
    public static Ficha evaluarCaminoRey(Ficha[][] Inicialcampo,int fila,int columna,Ficha fichaMovida){
        boolean seguirEvaluando=false;
        Ficha fichaAtacante=null;
        if(fila>=0 && fila<8 && columna>=0 && columna<8)seguirEvaluando=true;
        if(seguirEvaluando){
        Ficha[][] campo=new Ficha[8][8];
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                campo[i][j]=Inicialcampo[i][j];
        System.out.println("EvaluarCaminoRey");
        System.out.println("Coordenadas de ficha"+fichaMovida.posicionf+" "+fichaMovida.posicionc);
        Main.llenarPrueba(campo);
        int bando=-1;
        if(!(fichaMovida instanceof Rey)){
            for(int i=0;i<8;i++)
                for(int j=0;j<8;j++)
                    if(campo[i][j]!=null && campo[i][j].getClass()==Rey.class && campo[i][j].bando==fichaMovida.bando){
                        System.out.println("Coordenadas del rey "+i+" "+j);
                        System.out.println("Posicion a mover "+fila+" "+columna);
                        System.out.println("Posiciones de ficha "+fichaMovida.posicionf+" "+fichaMovida.posicionc);
                        campo[fichaMovida.posicionf][fichaMovida.posicionc]=null;
                        campo[fila][columna]=fichaMovida;
                        fila=campo[i][j].posicionf;
                        columna=campo[i][j].posicionc;
                        bando=campo[i][j].bando;
                        i=8;
                        break;
                    }
            System.out.println("Matriz modificada");Main.llenarPrueba(campo);
        }else {
            if(fila!=fichaMovida.posicionf && columna!=fichaMovida.posicionc && campo[fila][columna]!=null)
                fichaMovida.comer(campo, fila, columna, new ArrayList<Ficha>());
                campo[fichaMovida.posicionf][fichaMovida.posicionc]=null;
                campo[fila][columna]=fichaMovida;
                bando=fichaMovida.bando;
        }
        //Main.llenarPrueba(Inicialcampo);
        //Main.llenarPrueba(campo);
        
        //Analizando diagonales
        fichaAtacante=evaluandoDiagonal(campo,fila,columna,bando,1,0);
        if(fichaAtacante!=null)return fichaAtacante;
        fichaAtacante=evaluandoDiagonal(campo,fila,columna,bando,2,0);
        if(fichaAtacante!=null)return fichaAtacante;
        fichaAtacante=evaluandoDiagonal(campo,fila,columna,bando,3,0);
        if(fichaAtacante!=null)return fichaAtacante;
        fichaAtacante=evaluandoDiagonal(campo,fila,columna,bando,4,0);
        if(fichaAtacante!=null)return fichaAtacante;
        
        //Evaluando lineas rectas
        fichaAtacante=evaluandoLineas(campo,fila,columna,bando,1,0);
        if(fichaAtacante!=null)return fichaAtacante;
        fichaAtacante=evaluandoLineas(campo,fila,columna,bando,2,0);
        if(fichaAtacante!=null)return fichaAtacante;
        fichaAtacante=evaluandoLineas(campo,fila,columna,bando,3,0);
        if(fichaAtacante!=null)return fichaAtacante;
        fichaAtacante=evaluandoLineas(campo,fila,columna,bando,4,0);
        if(fichaAtacante!=null)return fichaAtacante;
        
        //Movimiento del caballo
        fichaAtacante=evaluandoCaballo(campo,fila,columna,bando);
        if(fichaAtacante!=null)return fichaAtacante;
            //campo=Inicialcampo;
            System.out.println("Se puede mover");
            return fichaAtacante;
        }else return new Rey(-1,-1,false,-1,"");
    }
    /**
     * isCheckmate
     * Comprueba si hay o no jaque mate
     * @param Inicialcampo
     * @param bando
     * @return boolean
     */
    public static boolean isCheckmate(Ficha[][] Inicialcampo,int bando){
        Rey rey=null;
        Ficha fichaAtacante=null,fichaDefensora=null;
        Ficha[][] campo=new Ficha[8][8];
        boolean isCheckmate=false;
        llenarMatriz(campo,Inicialcampo);
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                if(campo[i][j]!=null && campo[i][j].getClass()==Rey.class && campo[i][j].bando==bando){
                    rey=(Rey)(campo[i][j]);
                    i=8;
                    break;
                }
        fichaAtacante=Ficha.evaluarCaminoRey(campo, rey.posicionf, rey.posicionc, rey);
        if(fichaAtacante!=null){
                System.out.println("Paso #1");
                isCheckmate=true;
                if(!evaluarPosicionesRey(rey, campo, Inicialcampo))
                    isCheckmate=false;
            if(isCheckmate){
                System.out.println("Paso #2");
                llenarMatriz(campo,Inicialcampo);
                fichaDefensora=evaluandoTodo(campo,fichaAtacante);
                if(fichaDefensora!=null)isCheckmate=false;
            }
        if(isCheckmate){
            int i=fichaAtacante.posicionf,j=fichaAtacante.posicionc;
            llenarMatriz(campo,Inicialcampo);
            System.out.println("Paso #3");
            if(rey.posicionf>fichaAtacante.posicionf && rey.posicionc>fichaAtacante.posicionc){
                while(i+1<rey.posicionf && j+1<rey.posicionc && isCheckmate){
                    campo[i++][j++]=null;
                    campo[i][j]=new Alfil(i,j,true,fichaAtacante.bando,"");
                    fichaDefensora=evaluandoTodo(campo, campo[i][j]);
                    if(null!=fichaDefensora){
                        campo[fichaDefensora.posicionf][fichaDefensora.posicionc]=null;
                        campo[i][j]=new Alfil(i,j,true,fichaDefensora.bando,"");
                        Main.llenarPrueba(campo);
                        if(!evaluarPosicionesRey(rey, campo)){
                            llenarMatriz(campo,Inicialcampo);
                            isCheckmate=false;
                        }
                    }
                }
            }
            if(fichaDefensora!=null)System.out.println(Ficha.evaluarCaminoRey(campo,fichaDefensora.posicionf,fichaDefensora.posicionc, fichaDefensora)+" 1");
            if(isCheckmate)
            if(rey.posicionf>fichaAtacante.posicionf && rey.posicionc<fichaAtacante.posicionc){
                i=fichaAtacante.posicionf;
                j=fichaAtacante.posicionc;
                llenarMatriz(campo, Inicialcampo);
                while(i+1<rey.posicionf && j-1>rey.posicionc && isCheckmate){
                    campo[i++][j--]=null;
                    campo[i][j]=new Alfil(i,j,true,fichaAtacante.bando,"");
                    fichaDefensora=evaluandoTodo(campo, campo[i][j]);
                    if(null!=fichaDefensora){
                        campo[fichaDefensora.posicionf][fichaDefensora.posicionc]=null;
                        campo[i][j]=new Alfil(i,j,true,fichaDefensora.bando,"");
                        Main.llenarPrueba(campo);
                        if(!evaluarPosicionesRey(rey, campo)){
                            llenarMatriz(campo,Inicialcampo);
                            isCheckmate=false;
                        }
                    }
                }
            }
            if(fichaDefensora!=null)System.out.println(Ficha.evaluarCaminoRey(campo,fichaDefensora.posicionf,fichaDefensora.posicionc, fichaDefensora)+" 2");
            if(isCheckmate)
            if(rey.posicionf<fichaAtacante.posicionf && rey.posicionc>fichaAtacante.posicionc){
                i=fichaAtacante.posicionf;
                j=fichaAtacante.posicionc;
                llenarMatriz(campo, Inicialcampo);
                while(i-1>rey.posicionf && j+1<rey.posicionc && isCheckmate){
                    campo[i--][j++]=null;
                    campo[i][j]=new Alfil(i,j,true,fichaAtacante.bando,"");
                    fichaDefensora=evaluandoTodo(campo, campo[i][j]);
                    if(null!=fichaDefensora){
                        campo[fichaDefensora.posicionf][fichaDefensora.posicionc]=null;
                        campo[i][j]=new Alfil(i,j,true,fichaDefensora.bando,"");
                        Main.llenarPrueba(campo);
                        if(!evaluarPosicionesRey(rey, campo)){
                            llenarMatriz(campo,Inicialcampo);
                            isCheckmate=false;
                        }
                    }
                }
            }
            if(fichaDefensora!=null)System.out.println(Ficha.evaluarCaminoRey(campo,fichaDefensora.posicionf,fichaDefensora.posicionc, fichaDefensora)+" 3");
            if(isCheckmate)
            if(rey.posicionf<fichaAtacante.posicionf && rey.posicionc<fichaAtacante.posicionc){
                i=fichaAtacante.posicionf;
                j=fichaAtacante.posicionc;
                llenarMatriz(campo, Inicialcampo);
                while(i-1>rey.posicionf && j-1>rey.posicionc && isCheckmate){
                    campo[i--][j--]=null;
                    campo[i][j]=new Alfil(i,j,true,fichaAtacante.bando,"");
                    fichaDefensora=evaluandoTodo(campo, campo[i][j]);
                    if(null!=fichaDefensora){
                        campo[fichaDefensora.posicionf][fichaDefensora.posicionc]=null;
                        campo[i][j]=new Alfil(i,j,true,fichaDefensora.bando,"");
                        Main.llenarPrueba(campo);
                        if(!evaluarPosicionesRey(rey, campo)){
                            llenarMatriz(campo,Inicialcampo);
                            isCheckmate=false;
                        }
                    }
                }
            }
           if(fichaDefensora!=null)System.out.println(Ficha.evaluarCaminoRey(campo,fichaDefensora.posicionf,fichaDefensora.posicionc, fichaDefensora)+" 4");
            //EVALUANDO LINEAS RECTAS POR N-ESIMA VEZ!
            if(isCheckmate)
            if(rey.posicionf>fichaAtacante.posicionf && rey.posicionc==fichaAtacante.posicionc){
                i=fichaAtacante.posicionf;
                llenarMatriz(campo, Inicialcampo);
                while(i+1<rey.posicionf && isCheckmate){
                    campo[i++][fichaAtacante.posicionc]=null;
                    campo[i][fichaAtacante.posicionc]=new Torre(i,fichaAtacante.posicionc,true,fichaAtacante.bando,"");
                    fichaDefensora=evaluandoTodo(campo, campo[i][j]);
                   if(null!=fichaDefensora){
                        campo[fichaDefensora.posicionf][fichaDefensora.posicionc]=null;
                        campo[i][j]=new Torre(i,j,true,fichaDefensora.bando,"");
                        Main.llenarPrueba(campo);
                        if(!evaluarPosicionesRey(rey, campo)){
                            llenarMatriz(campo,Inicialcampo);
                            isCheckmate=false;
                        }
                    }
                }
            }
            if(fichaDefensora!=null)System.out.println(Ficha.evaluarCaminoRey(campo,fichaDefensora.posicionf,fichaDefensora.posicionc, fichaDefensora)+" 5");
            if(isCheckmate)
            if(rey.posicionf<fichaAtacante.posicionf && rey.posicionc==fichaAtacante.posicionc){
                i=fichaAtacante.posicionf;
                llenarMatriz(campo, Inicialcampo);
                while(i-1>rey.posicionf && isCheckmate){
                    campo[i--][fichaAtacante.posicionc]=null;
                    campo[i][fichaAtacante.posicionc]=new Torre(i,fichaAtacante.posicionc,true,fichaAtacante.bando,"");
                    fichaDefensora=evaluandoTodo(campo, campo[i][j]);
                    if(null!=fichaDefensora){
                        campo[fichaDefensora.posicionf][fichaDefensora.posicionc]=null;
                        campo[i][j]=new Torre(i,j,true,fichaDefensora.bando,"");
                        Main.llenarPrueba(campo);
                        if(!evaluarPosicionesRey(rey, campo)){
                            llenarMatriz(campo,Inicialcampo);
                            isCheckmate=false;
                        }
                    }
                }
            }
            if(fichaDefensora!=null)System.out.println(Ficha.evaluarCaminoRey(campo,fichaDefensora.posicionf,fichaDefensora.posicionc, fichaDefensora)+" 6");
            if(isCheckmate)
            if(rey.posicionf==fichaAtacante.posicionf && rey.posicionc>fichaAtacante.posicionc){
                j=fichaAtacante.posicionc;
                llenarMatriz(campo, Inicialcampo);
                while(j+1<rey.posicionc && isCheckmate){
                    campo[fichaAtacante.posicionf][j++]=null;
                    campo[fichaAtacante.posicionf][j]=new Torre(fichaAtacante.posicionf,j,true,fichaAtacante.bando,"");
                    fichaDefensora=evaluandoTodo(campo, campo[i][j]);
                    if(null!=fichaDefensora){
                        campo[fichaDefensora.posicionf][fichaDefensora.posicionc]=null;
                        campo[i][j]=new Torre(i,j,true,fichaDefensora.bando,"");
                        Main.llenarPrueba(campo);
                        if(!evaluarPosicionesRey(rey, campo)){
                            llenarMatriz(campo,Inicialcampo);
                            isCheckmate=false;
                        }
                    }
                }
            }
            if(fichaDefensora!=null)System.out.println(Ficha.evaluarCaminoRey(campo,fichaDefensora.posicionf,fichaDefensora.posicionc, fichaDefensora)+" 7");
            if(isCheckmate)
            if(rey.posicionf==fichaAtacante.posicionf && rey.posicionc<fichaAtacante.posicionc){
                j=fichaAtacante.posicionc;
                llenarMatriz(campo, Inicialcampo);
                while(j-1>rey.posicionf && isCheckmate){
                    campo[fichaAtacante.posicionf][j--]=null;
                    campo[fichaAtacante.posicionf][j]=new Torre(fichaAtacante.posicionf,j,true,fichaAtacante.bando,"");
                    fichaDefensora=evaluandoTodo(campo, campo[i][j]);
                    if(null!=fichaDefensora){
                        campo[fichaDefensora.posicionf][fichaDefensora.posicionc]=null;
                        campo[i][j]=new Torre(i,j,true,fichaDefensora.bando,"");
                        Main.llenarPrueba(campo);
                        if(!evaluarPosicionesRey(rey, campo)){
                            llenarMatriz(campo,Inicialcampo);
                            isCheckmate=false;
                        }
                    }
                }
            }
            if(fichaDefensora!=null)System.out.println(Ficha.evaluarCaminoRey(campo,fichaDefensora.posicionf,fichaDefensora.posicionc, fichaDefensora)+" 8");
        }
        }

        if(!isCheckmate){
        System.out.println(fichaAtacante);
        System.out.println(fichaDefensora);
        }//Main.llenarPrueba(campo);
        return isCheckmate;
    }
    /**
     * evaluandoDiagonal
     * Evalua si la diagonal se presenta un movimiento invalido o no (segun
     * la posicion a mover por la ficha definida por la fila y la columna
     * @param campo
     * @param fila
     * @param columna
     * @param bando
     * @param diagonal
     * @param tipoEvaluacion
     * @return Ficha
     */
    private static Ficha evaluandoDiagonal(Ficha[][] campo,int fila,int columna,int bando,int diagonal,int tipoEvaluacion){
        int i,j;
        switch(diagonal){
                case 1:
                    if(fila+1<8 && columna+1<8){
                        i=fila+1;
                        j=columna+1;
                        //System.out.println("Evaluacion de peones y rey 1");
                        if(campo[i][j]!=null && (campo[i][j].getClass()==Rey.class || campo[i][j].getClass()==Peon.class)){
                            if(campo[i][j].getClass()==Peon.class && campo[i][j].bando!=bando && campo[i][j].bando==1)return campo[i][j];
                            else if(campo[i][j].getClass()==Rey.class && bando!=campo[i][j].bando)
                                if(tipoEvaluacion==0)return campo[i][j];
                        }
                        //System.out.println("Evaluando alfil y reina 1");
                        while(i<7 && j<7 && campo[i][j]==null){
                            i++;
                            j++;
                    }
                    if(campo[i][j]!=null && (campo[i][j].getClass()==Reina.class || campo[i][j].getClass()==Alfil.class))
                        if(campo[i][j].bando!=bando)return campo[i][j];
                    }
                    
                    break;
                case 2:
                        if(fila+1<8 && columna-1>=0){
                            i=fila+1;
                            j=columna-1;
                            //System.out.println("Evaluacion de peones y rey 2");
                            if(campo[i][j]!=null && (campo[i][j].getClass()==Rey.class || campo[i][j].getClass()==Peon.class)){          
                                if(campo[i][j].getClass()==Peon.class && campo[i][j].bando!=bando && campo[i][j].bando==1)return campo[i][j];
                                else if(campo[i][j].getClass()==Rey.class && bando!=campo[i][j].bando)
                                        if(tipoEvaluacion==0)return campo[i][j];
                            }           
                            //System.out.println("Evaluando alfil y reina 2");
                                while(i<7 && j>0 && campo[i][j]==null){
                                    i++;
                                    j--;
                            }
                            if(campo[i][j]!=null && (campo[i][j].getClass()==Alfil.class || campo[i][j].getClass()==Reina.class))
                            if(campo[i][j].bando!=bando)return campo[i][j];
                            }
                    break;
            case 3:
                    if(fila-1>=0 && columna-1>=0){
                        i=fila-1;
                        j=columna-1;
                        //System.out.println("Evaluacion de peones y rey 3");
                        if(campo[i][j]!=null && (campo[i][j].getClass()==Rey.class || campo[i][j].getClass()==Peon.class)){
                            if(campo[i][j].getClass()==Peon.class && campo[i][j].bando!=bando && campo[i][j].bando==0)return campo[i][j];
                            else if(campo[i][j].getClass()==Rey.class && bando!=campo[i][j].bando)
                                    if(tipoEvaluacion==0)return campo[i][j];
                                    
                        }
                            //System.out.println("Evaluando alfil y reina 3");
                            while(i>0 && j>0 && campo[i][j]==null){
                                i--;
                                j--;
                            }
                        if(campo[i][j]!=null && (campo[i][j].getClass()==Alfil.class || campo[i][j].getClass()==Reina.class))
                            if(campo[i][j].bando!=bando)return campo[i][j];
                        }
                break;
            case 4:
                    if(fila-1>=0 && columna+1<8){
                        i=fila-1;
                        j=columna+1;
                        //System.out.println("Evaluacion de peones y rey 4");
                        if(campo[i][j]!=null && (campo[i][j].getClass()==Rey.class || campo[i][j].getClass()==Peon.class)){
                            if(campo[i][j].getClass()==Peon.class && campo[i][j].bando!=bando && campo[i][j].bando==0)return campo[i][j];
                            else if(campo[i][j].getClass()==Rey.class && bando!=campo[i][j].bando)
                                if(tipoEvaluacion==0)return campo[i][j];
                        }
                        //System.out.println("Evaluando alfil y reina 4");
                        while(i>0 && j<7 && campo[i][j]==null){
                            i--;
                            j++;
                        }
                        if(campo[i][j]!=null && (campo[i][j].getClass()==Alfil.class || campo[i][j].getClass()==Reina.class))
                            if(campo[i][j].bando!=bando)return campo[i][j];
                        }
                        
        }
        return null;
    }
    /**
     * Evalua si la diagonal se presenta un movimiento invalido o no (segun
     * la posicion a mover por la ficha definida por la fila y la columna)
     * @param campo
     * @param fila
     * @param columna
     * @param bando
     * @param linea
     * @param tipoEvaluacion
     * @return Ficha
     */
    private static Ficha evaluandoLineas(Ficha[][] campo,int fila,int columna,int bando,int linea, int tipoEvaluacion){
        int i,j;
        switch(linea){
            case 1:
                    if(columna+1<8){
                        //System.out.println("Evaluando linea recta 1 rey c++");
                        i=columna+1;
                        if(campo[fila][i]!=null && campo[fila][i].getClass()==Rey.class && campo[fila][i].bando!=bando)
                            if(tipoEvaluacion==0)return campo[fila][i];
                            //System.out.println("Evaluando linea recta 1 TORRRE c++");
                            while(i<7 && campo[fila][i]==null)i++;
                            if(campo[fila][i]!=null && (campo[fila][i].getClass()==Torre.class || campo[fila][i].getClass()==Reina.class))
                                if(campo[fila][i].bando!=bando)return campo[fila][i];
                        }
                break;
            case 2:
                    if(columna-1>=0){
                        j=columna-1;
                        //System.out.println("Evaluando linea recta 2 rey c--");
                        if(campo[fila][j]!=null && campo[fila][j].getClass()==Rey.class && campo[fila][j].bando!=bando)
                            if(tipoEvaluacion==0)return campo[fila][j];
                            //System.out.println("Evaluando linea recta 2 TORRRE c--");
                            while(j>0 && campo[fila][j]==null)j--;
                            if(campo[fila][j]!=null && (campo[fila][j].getClass()==Torre.class || campo[fila][j].getClass()==Reina.class))
                                if(campo[fila][j].bando!=bando)return campo[fila][j];
                    }
                break;
            case 3:
                    if(fila+1<8){
                        i=fila+1;
                        //System.out.println("Evaluando linea recta 3 rey f++");
                        if(campo[i][columna]!=null && campo[i][columna].getClass()==Rey.class  && campo[i][columna].bando!=bando)
                            if(tipoEvaluacion==0)return campo[i][columna];
                            //System.out.println("Evaluando linea recta 3 TORRRE f++");
                            while(i<7 && campo[i][columna]==null)i++;
                            if(campo[i][columna]!=null && (campo[i][columna].getClass()==Torre.class || campo[i][columna].getClass()==Reina.class))
                                if(campo[i][columna].bando!=bando)return campo[i][columna];
                        }
                break;
            case 4:
                    if(fila-1>=0){
                        j=fila-1;
                        //System.out.println("Evaluando linea recta 4 rey f--");
                        if(campo[j][columna]!=null && campo[j][columna].getClass()==Rey.class && campo[j][columna].bando!=bando)
                            if(tipoEvaluacion==0)return campo[j][columna];
                            //System.out.println("Evaluando linea recta 4 TORRRE f--");
                            while(j>0 && campo[j][columna]==null)j--;
                            if(campo[j][columna]!=null && (campo[j][columna].getClass()==Torre.class || campo[j][columna].getClass()==Reina.class))
                                if(campo[j][columna].bando!=bando)return campo[j][columna];
                        }
        }
        return null;
    }
    /**
     * evaluandoCaballo
     * Evalua si, en un movimiento de caballo, se presenta una posicion invalida o no (segun fila y columna ingresada corres-
     * pondinte al campo al que se moverá la ficha)
     * @param campo
     * @param fila
     * @param columna
     * @param bando
     * @return Ficha
     */
    private static Ficha evaluandoCaballo(Ficha[][] campo,int fila,int columna,int bando){
            //System.out.println("Evaluando caballo 1 c+2 f++");
            if(fila<7 && columna<6)
                    if(campo[fila+1][columna+2]!=null && campo[fila+1][columna+2].getClass()==Caballo.class)
                        if(campo[fila+1][columna+2].bando!=bando)return campo[fila+1][columna+2];
        
            //System.out.println("Evaluando caballo 2 c-2 f++");
            if(fila<7 && columna>1)
                    if(campo[fila+1][columna-2]!=null && campo[fila+1][columna-2].getClass()==Caballo.class)
                        if(campo[fila+1][columna-2].bando!=bando)return campo[fila+1][columna-2];
   
            //System.out.println("Evaluando caballo 3 c+2 f--");
            if(fila>0 && columna<6)
                    if(campo[fila-1][columna+2]!=null && campo[fila-1][columna+2].getClass()==Caballo.class)
                        if(campo[fila-1][columna+2].bando!=bando)return campo[fila-1][columna+2];
     
            //System.out.println("Evaluando caballo 4 c-2 f--");
            if(fila>0 && columna>1)
                    if(campo[fila-1][columna-2]!=null && campo[fila-1][columna-2].getClass()==Caballo.class)
                        if(campo[fila-1][columna-2].bando!=bando)return campo[fila-1][columna-2];
        
            //System.out.println("Evaluando caballo 5 c++ f+2");
            if(columna<7 && fila<6)
                    if(campo[fila+2][columna+1]!=null && campo[fila+2][columna+1].getClass()==Caballo.class)
                        if(campo[fila+2][columna+1].bando!=bando)return campo[fila+2][columna+1];
        
            //System.out.println("Evaluando caballo 6 c++ f-2");
            if(columna<7 && fila>1)
                    if(campo[fila-2][columna+1]!=null && campo[fila-2][columna+1].getClass()==Caballo.class)
                        if(campo[fila-2][columna+1].bando!=bando)return campo[fila-2][columna+1];
        
            //System.out.println("Evaluando caballo 7 c-- f+2");
            if(columna>0 && fila<6)
                    if(campo[fila+2][columna-1]!=null && campo[fila+2][columna-1].getClass()==Caballo.class)
                        if(campo[fila+2][columna-1].bando!=bando)return campo[fila+2][columna-1];
        
            //System.out.println("Evaluando caballo 8 c-- f-2");
            if(columna>0 && fila>1)
                    if(campo[fila-2][columna-1]!=null && campo[fila-2][columna-1].getClass()==Caballo.class)
                        if(campo[fila-2][columna-1].bando!=bando)return campo[fila-2][columna-1];
        return null;
    }
    /**
     * evaluandoTodo
     * Evalua lineas, diagonales y movimientos de caballo 
     * @param campo
     * @param fichaAtacante
     * @return Ficha
     */
    private static Ficha evaluandoTodo(Ficha[][] campo,Ficha fichaAtacante){
        Ficha fichaDefensora=null;
        boolean isCheckmate=true;
        fichaDefensora=evaluandoDiagonal(campo,fichaAtacante.posicionf,fichaAtacante.posicionc,fichaAtacante.bando,1,1);
            if(fichaDefensora!=null) isCheckmate=false;
            if(isCheckmate)fichaDefensora=evaluandoDiagonal(campo,fichaAtacante.posicionf,fichaAtacante.posicionc,fichaAtacante.bando,2,1);
            if(fichaDefensora!=null)isCheckmate=false;
            if(isCheckmate)fichaDefensora=evaluandoDiagonal(campo,fichaAtacante.posicionf,fichaAtacante.posicionc,fichaAtacante.bando,3,1);
            if(fichaDefensora!=null)isCheckmate=false;
            if(isCheckmate)fichaDefensora=evaluandoDiagonal(campo,fichaAtacante.posicionf,fichaAtacante.posicionc,fichaAtacante.bando,4,1);
            if(fichaDefensora!=null)isCheckmate=false;

        //Evaluando lineas rectas
            if(isCheckmate)fichaDefensora=evaluandoLineas(campo,fichaAtacante.posicionf,fichaAtacante.posicionc,fichaAtacante.bando,1,1);
            if(fichaDefensora!=null)isCheckmate=false;
            if(isCheckmate)fichaDefensora=evaluandoLineas(campo,fichaAtacante.posicionf,fichaAtacante.posicionc,fichaAtacante.bando,2,1);
            if(fichaDefensora!=null)isCheckmate=false;
            if(isCheckmate)fichaDefensora=evaluandoLineas(campo,fichaAtacante.posicionf,fichaAtacante.posicionc,fichaAtacante.bando,3,1);
            if(fichaDefensora!=null)isCheckmate=false;
            if(isCheckmate)fichaDefensora=evaluandoLineas(campo,fichaAtacante.posicionf,fichaAtacante.posicionc,fichaAtacante.bando,4,1);
            if(fichaDefensora!=null)isCheckmate=false;

        //Movimiento del caballo

            if(isCheckmate)fichaDefensora=evaluandoCaballo(campo,fichaAtacante.posicionf,fichaAtacante.posicionc,fichaAtacante.bando);
        return fichaDefensora;
    }
    /**
     * getBando
     * Obtiene el bando de la ficha
     * @return int
     */
    public int getBando(){
        return bando;
    }
    /**
     * getImagen
     * Obtiene la imagen de la ficha
     * @return ImageIcon
     */
    public ImageIcon getImagen(){
        return imagen;
    }
    /**
     * llenarMatriz
     * Produce una matriz temporal
     * @param campo
     * @param Inicialcampo
     */
    private static void llenarMatriz(Ficha[][] campo ,Ficha[][] Inicialcampo){
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                campo[i][j]=Inicialcampo[i][j];
    }
    /**
     * evaluarPosicionesRey
     * Evalua si el rey puede moverse a cualquier sitio
     * @param rey
     * @param campo
     * @param Inicialcampo
     * @return boolean
     */
    private static boolean evaluarPosicionesRey(Ficha rey,Ficha[][] campo,Ficha[][] Inicialcampo){
        boolean isCheckmate=true;
        System.out.println("Evaluar rey");
        Main.llenarPrueba(campo);
        for(int i=rey.posicionf-1;i<=rey.posicionf+1;i++)
                    for(int j=rey.posicionc-1;j<=rey.posicionc+1;j++)
                        if(Ficha.evaluarCaminoRey(campo, i, j, rey)==null){
                            isCheckmate=false;
                            return isCheckmate;
                        }else llenarMatriz(campo, Inicialcampo);
        return isCheckmate;
    }
    /**
     * evaluarPosicionesRey
     * Evalua si el rey puede moverse a cualquier sitio
     * @param rey
     * @param Inicialcampo
     * @return boolean
     */
    private static boolean evaluarPosicionesRey(Ficha rey,Ficha[][] Inicialcampo){
        Ficha[][] campo=new Ficha[8][8];
        Ficha[][] campo2=new Ficha[8][8];
        Ficha.llenarMatriz(campo, Inicialcampo);
        Ficha.llenarMatriz(campo2, Inicialcampo);
        return Ficha.evaluarPosicionesRey(rey, campo, campo2);
    }
    /**
     * ahogado
     * Si no hay jaque, este evalua si el rey puede moverse u otra ficha lo puede hacer,
     * si no es asi, el juego se detiene (por ahogado)
     * @param campo
     * @param bando
     * @param isCheckmate
     * @return boolean
     */
    public boolean ahogado(Ficha[][] campo,int bando,int isCheckmate){
        if(isCheckmate==-1)return false;
        else if(isCheckmate==1)return false;
            else{
                Rey rey=null;
                for(int i=0;i<8;i++)
                    for(int j=0;j<8;j++)
                        if(campo[i][j]!=null && campo[i][j].getClass()==Rey.class && campo[i][j].bando==bando){
                            rey=(Rey)(campo[i][j]);

                        }
            }
        return true;
    }
}

