package Modelo;
import java.util.ArrayList;
/**
 * 
 * @author nicolas
 */
public class Reina extends Ficha{
    public Reina(int posicionf, int posicionc, boolean viva, int bando, String rutaImagen){
        super(posicionf,posicionc,viva,bando,rutaImagen);
    }
    /**
     *
     * @param campo
     * @param fila
     * @param columna
     * @param muertas
     * @return
     */
    public boolean evaluarMovimiento(Ficha[][] campo,int fila, int columna,ArrayList<Ficha> muertas){
        int i=posicionf, j=posicionc;
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
            }else if(fila<posicionf && columna<posicionc){
                    while((fila!=i && columna!=j && campo[i-1][j-1]==null) || (fila==i-1 && columna==j-1 && campo[fila][columna]!=null)){
                        i--;
                        j--;
                    }
                  }else if(fila<posicionf && columna>posicionc){
                            while((fila!=i && columna!=j && campo[i-1][j+1]==null) || (fila==i-1 && columna==j+1 && campo[fila][columna]!=null)){
                                i--;
                                j++;
                            }
                        }else if(fila>posicionf && columna>posicionc){
                                while((fila!=i && columna!=j && campo[i+1][j+1]==null) || (fila==i+1 && columna==j+1 && campo[fila][columna]!=null)){
                                    i++;
                                    j++;
                                }
                        }else  if(fila>posicionf && columna<posicionc)
                                while((fila!=i && columna!=j && campo[i+1][j-1]==null) || (fila==i+1 && columna==j-1 && campo[fila][columna]!=null)){
                                    i++;
                                    j--;
                                }
        if(fila==i && columna==j && campo[fila][columna]!=null){
            if(campo[fila][columna].bando!=this.bando && Ficha.evaluarCaminoRey(campo, fila, columna, this)==null){
                //System.out.println("Movi Reina");
                super.comer(campo, fila, columna,muertas);
                return true;
            }
        }else if(fila==i && columna==j && Ficha.evaluarCaminoRey(campo, fila, columna, this)==null){
            //System.out.println("Movi Reina");
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

