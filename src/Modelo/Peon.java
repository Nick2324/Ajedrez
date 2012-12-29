package Modelo;
import java.util.ArrayList;
/**
 * 
 * @author nicolas
 */
public class Peon extends Ficha{
    public Peon(int posicionx,int posiciony,boolean viva,int bando,String rutaImagen){
        super(posicionx, posiciony, viva, bando, rutaImagen);
    }
    /**
     * evaluarMovimiento
     * Evalua si el movimiento es valido o no
     * @param campo
     * @param fila
     * @param columna
     * @param muertas
     * @return
     */
    public boolean evaluarMovimiento(Ficha[][] campo,int fila, int columna, ArrayList<Ficha> muertas){
        if(bando==0){
            if((columna==posicionc-1 || columna==posicionc+1) && fila==posicionf+1 && campo[fila][columna]!=null){
                if(campo[fila][columna].bando!=bando && Ficha.evaluarCaminoRey(campo, fila, columna, this)==null){
                    //System.out.println("Movi Peon");
                    super.comer(campo, fila, columna, muertas);
                    return true;
                }
            }else if(columna==posicionc && ((fila==posicionf+1) || (fila==posicionf+2 && posicionf==1))){
                    if(campo[fila][columna]==null && campo[posicionf+1][columna]==null && Ficha.evaluarCaminoRey(campo, fila, columna, this)==null){
                        //System.out.println("Movi Peon");
                        return true;
                    }
                  }
        }else{
            if((columna==posicionc-1 || columna==posicionc+1) && fila==posicionf-1 && campo[fila][columna]!=null){
                if(campo[fila][columna].bando!=bando && Ficha.evaluarCaminoRey(campo, fila, columna, this)==null){
                    //System.out.println("Movi Peon");
                    super.comer(campo, fila, columna, muertas);
                    return true;
                }
            }else if(columna==posicionc && ((fila==posicionf-1) || (fila==posicionf-2 && posicionf==6)))
                    if(campo[fila][columna]==null && campo[posicionf-1][columna]==null && Ficha.evaluarCaminoRey(campo, fila, columna, this)==null){
                        //System.out.println("Movi Peon");
                        return true;
                    }

        }
        //System.out.println("No movi");
        return false;
    }
    /**
     * mover
     * Si se puede mover entonces mueve la ficha
     * @param campo
     * @param fila
     * @param columna
     * @param muertas
     */
     public void mover(Ficha[][] campo, int fila, int columna,ArrayList<Ficha> muertas){
        super.mover(campo, fila, columna,muertas);
    }
    /**
     * coronar
     * Corona el Peon
     * @param campo
     * @param muertas
     * @param pedida
     */

    public void coronar(Ficha[][] campo,ArrayList<Ficha> muertas,int pedida){
                campo[this.posicionf][this.posicionc]=muertas.get(pedida);
                muertas.add(this);
                campo[this.posicionf][this.posicionc].posicionf=this.posicionf;
                campo[this.posicionf][this.posicionc].posicionc=this.posicionc;
    }
}

