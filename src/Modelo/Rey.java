package Modelo;
import java.util.ArrayList;
/**
 * 
 * @author nicolas
 */
public class Rey extends Ficha{
    private boolean paraEnroque=true;
    /**
     * Constructor
     * @param posicionx
     * @param posiciony
     * @param viva
     * @param bando
     * @param rutaImagen
     */
    public Rey (int posicionx, int posiciony,boolean viva, int bando,String rutaImagen){
        super(posicionx,posiciony,viva,bando,rutaImagen);
    }
    /**
     *  mover
     * @param campo
     * @param fila
     * @param columna
     * @param muertas
     */
     public void mover(Ficha[][] campo, int fila, int columna,ArrayList<Ficha> muertas){
        super.mover(campo, fila, columna,muertas);
    }
    /**
     *  evaluarMovimiento
     * @param campo
     * @param fila
     * @param columna
     * @param muertas
     * @return true or false
     */
    public boolean evaluarMovimiento(Ficha[][] campo,int fila, int columna,ArrayList<Ficha> muertas){
        if(columna-posicionc>=-1 && columna-posicionc<=1 && fila-posicionf>=-1 && fila-posicionf<=1){
            if(!(posicionf==fila && columna==posicionc)){
                System.out.println(Ficha.evaluarCaminoRey(campo, fila, columna, this));
                if(Ficha.evaluarCaminoRey(campo,fila,columna,this)==null){
                    if(campo[fila][columna]!=null && campo[fila][columna].bando!=this.bando){
                        System.out.println("Comi fichas");
                        if(paraEnroque)paraEnroque=false;
                        super.comer(campo, fila, columna, muertas);
                    }else if(campo[fila][columna]==null){
                            if(paraEnroque)paraEnroque=false;
                            System.out.println("Movi Rey");
                            return true;
                    }
                }
            }
        }else if(columna==posicionc+2 && paraEnroque && Ficha.evaluarCaminoRey(campo, fila, columna, this)==null){
            if(campo[posicionf][posicionc+3].getClass()==Torre.class){
                Torre torre=(Torre)(campo[posicionf][posicionc+3]);
                if(torre.bando==bando && torre.getParaEnroque()){
                    if(campo[posicionf][posicionc+1]==null && campo[posicionf][posicionc+2]==null){
                        paraEnroque=false;
                        torre.setParaEnroque(false);
                        campo[posicionf][torre.posicionc-2]=torre;
                        campo[posicionf][torre.posicionc]=null;
                        System.out.println("Movi Rey Enroque");
                        return true;
                    }
                }
            }
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
