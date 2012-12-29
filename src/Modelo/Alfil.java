package Modelo;

import java.util.ArrayList;
/**
 * 
 * @author nicolas
 */
public class Alfil extends Ficha{
    /**
     *
     * @param posicionf
     * @param posicionc
     * @param viva
     * @param bando
     * @param rutaImagen
     */
    public Alfil(int posicionf, int posicionc,boolean viva, int bando,String rutaImagen){
        super(posicionf,posicionc,viva,bando,rutaImagen);
    }
    /**
     *
     * @param campo
     * @param fila
     * @param columna
     * @param muertas
     * @return true or false
     */
    public boolean evaluarMovimiento(Ficha[][] campo, int fila, int columna,ArrayList<Ficha> muertas) {
        int i=posicionf, j=posicionc;
        System.out.println("QUE PASA! "+posicionf+" "+posicionc);
        if(fila<posicionf && columna<posicionc)
            while((fila!=i && columna!=j && campo[i-1][j-1]==null) || (fila==i-1 && columna==j-1 && campo[fila][columna]!=null)){
                i--;
                j--;
            }
        if(fila<posicionf && columna>posicionc){
            while((fila!=i && columna!=j && campo[i-1][j+1]==null) || (fila==i-1 && columna==j+1 && campo[fila][columna]!=null)){
                i--;
                j++;
            }
        }
        if(fila>posicionf && columna>posicionc)
            while((fila!=i && columna!=j && campo[i+1][j+1]==null) || (fila==i+1 && columna==j+1 && campo[fila][columna]!=null)){
                i++;
                j++;
            }
        if(fila>posicionf && columna<posicionc){
            while((fila!=i && columna!=j && campo[i+1][j-1]==null) || (fila==i+1 && columna==j-1 && campo[fila][columna]!=null)){
                i++;
                j--;
            }
        }
        if(columna==j && fila==i && campo[fila][columna]!=null){
            if(campo[fila][columna].bando!=this.bando && Ficha.evaluarCaminoRey(campo, fila, columna, this)==null){
                System.out.println("Movi alfil 1");
                super.comer(campo, fila, columna, muertas);
                return true;
            }
        }else if(columna==j && fila==i && campo[fila][columna]==null && Ficha.evaluarCaminoRey(campo, fila, columna, this)==null){
            System.out.println("Movi alfil 2");
            return true;
        }
        return false;

    }
    /**
     *
     * @param campo
     * @param fila
     * @param columna
     * @param muertas
     */
    public void mover(Ficha[][] campo, int fila, int columna,ArrayList<Ficha> muertas){
        super.mover(campo, fila, columna,muertas);
    }
}

