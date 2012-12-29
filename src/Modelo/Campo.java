/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

/**
 *
 * @author oscar
 */
public class Campo {
        private Ficha [][] tablero =new Ficha[8][8];

       public Campo(){
           // anadiendo fichas


       }
public void setFicha(Ficha fich,int f, int c){
  this.tablero[f][c]=fich;

}
public Ficha get_Ficha(int f, int c){
    return tablero[f][c];

}
    public Ficha[][] get_Ficha() {
return this.tablero;    }


    public void imprimir_mapa(){
        System.out.println("\n\n");
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
        {
            if(tablero[i][j]==null){
            System.out.print("* ");
            }
        else{
            System.out.print("C ");

            }
        }
            System.out.println("");

        }
    }

    public void setTablero(Ficha[][] campoAlterno) {
        this.tablero=campoAlterno;
    }


}
