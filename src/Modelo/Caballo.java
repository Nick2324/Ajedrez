package Modelo;
import java.util.ArrayList;
/**
 * 
 * @author nicolas
 */
public class Caballo extends Ficha{
    /**
     * 
     * @param posicionf
     * @param posicionc
     * @param viva
     * @param bando
     * @param rutaImagen
     */
    public Caballo(int posicionf, int posicionc,boolean viva, int bando,String rutaImagen){
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
     * @return resultadoEvaluacion
     */
    public boolean evaluarMovimiento(Ficha[][] campo,int fila, int columna, ArrayList<Ficha> muertas) {
        boolean resultadoEvaluacion=false;
        if(columna==posicionc+1 && (fila==posicionf-2 || fila==posicionf+2))
           resultadoEvaluacion=true;
        else if(columna==posicionc-1 && (fila==posicionf-2 || fila==posicionf+2))
                 resultadoEvaluacion=true;
              else if(fila==posicionf-1 && (columna==posicionc-2 || columna==posicionc+2))
                         resultadoEvaluacion=true;
                    else if(fila==posicionf+1 && (columna==posicionc-2 || columna==posicionc+2))
                             resultadoEvaluacion=true;
        System.out.println(resultadoEvaluacion);
        if(resultadoEvaluacion && campo[fila][columna]!=null && campo[fila][columna].bando!=this.bando && Ficha.evaluarCaminoRey(campo, fila, columna, this)==null)
            super.comer(campo, fila, columna, muertas);
        else if(!(resultadoEvaluacion && campo[fila][columna]==null && Ficha.evaluarCaminoRey(campo, fila, columna, this)==null))resultadoEvaluacion=false;
        return resultadoEvaluacion;
    }
}


