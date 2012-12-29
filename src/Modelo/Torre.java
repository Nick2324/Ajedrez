package Modelo;

import java.util.ArrayList;
/**
 * 
 * @author nicolas
 */
public class Torre extends Ficha{
    private boolean paraEnroque=true;

    /**
     *
     * @param posicionf
     * @param posicionc
     * @param viva
     * @param bando
     * @param rutaImagen
     */
    public Torre (int posicionf, int posicionc,boolean viva, int bando,String rutaImagen){
            super(posicionf,posicionc,viva,bando,rutaImagen);
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
    /**
     *
     * @param campo
     * @param fila
     * @param columna
     * @param muertas
     * @return true or false
     */
    public boolean evaluarMovimiento(Ficha[][] campo,int fila, int columna,ArrayList<Ficha> muertas){
        int i=posicionf,j=posicionc;
        if(posicionf==fila && columna!=posicionc){
            if(columna<posicionc)
                while(j!=columna && ((j>0 && campo[i][j-1]==null) || (j==columna+1 && campo[fila][columna]!=null)))j--;
            else
                while(j!=columna && ((j<7 && campo[i][j+1]==null) || (j==columna-1 && campo[fila][columna]!=null)))j++;
        }else if(posicionf!=fila && columna==posicionc){
                if(fila<posicionf)
                    while(i!=fila && ((i>0 && campo[i-1][j]==null) || (i==fila+1 && campo[fila][columna]!=null)))i--;
                else
                    while(i!=fila && ((i<7 && campo[i+1][j]==null) || (i==fila-1 && campo[fila][columna]!=null)))i++;
            }
        if(fila==i && columna==j && campo[fila][columna]!=null){
            if(campo[fila][columna].bando!=this.bando && Ficha.evaluarCaminoRey(campo, fila, columna, this)==null){
                super.comer(campo, fila, columna, muertas);
                if(paraEnroque)paraEnroque=false;
                System.out.println("Movi Torre");
                return true;
            }
        }else if(fila==i && columna==j && Ficha.evaluarCaminoRey(campo, fila, columna, this)==null){
            if(paraEnroque)paraEnroque=false;
            System.out.println("Movi Torre");
            return true;
        }
        return false;
    }
    /**
     * setParaEnroque
     * Pone el valor del atributo enroque
     * @param paraEnroque
     */
    public void setParaEnroque(boolean paraEnroque){
        this.paraEnroque=paraEnroque;
    }
    /**
     * getParaEnroque
     * Obtiene el balor de paraEnroque
     * @return paraEnroque
     */
    public boolean getParaEnroque(){
        return paraEnroque;
    }
}


