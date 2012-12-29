package Modelo;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Ficha[][] tablero=new Ficha[8][8];
    static char[][] prueba=new char[8][8];
    static Scanner scanner=new Scanner(System.in);
    static ArrayList<Ficha> muertas=new ArrayList<Ficha>();
    public static void main(String[] args){
        //llenarTablero();
        tablero[0][0]=new Rey(0,0,true,0,"");
        tablero[1][1]=new Torre(1,1,true,1,"");
        tablero[1][7]=new Torre(1,7,true,1,"");
        tablero[2][1]=new Caballo(2,1,true,1,"");
        tablero[3][1]=new Torre(3,1,true,0,"");
        while(true){
           if(Ficha.isCheckmate(tablero,0)){
                System.out.println("Es jaque mate!");
                break;
            }else System.out.println("No hay jaque mate");
            llenarPrueba();
            mostrarMatriz();
            System.out.print("Ficha a mover; ");
            int fila=scanner.nextInt();
            int columna=scanner.nextInt();
            Ficha fichaMovida=tablero[fila][columna];
            System.out.print("Mover a; ");
            fila=scanner.nextInt();
            columna=scanner.nextInt();
            fichaMovida.mover(tablero, fila, columna, muertas);
        }
    }
    public static void llenarPrueba(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(tablero[i][j]!=null){
                    switch(tablero[i][j].bando){
                        case 0:
                            if(tablero[i][j].getClass()==Torre.class)prueba[i][j]='T';
                            else if(tablero[i][j].getClass()==Reina.class)prueba[i][j]='Y';
                                else if(tablero[i][j].getClass()==Rey.class)prueba[i][j]='R';
                                    else if(tablero[i][j].getClass()==Caballo.class)prueba[i][j]='C';
                                        else if(tablero[i][j].getClass()==Alfil.class)prueba[i][j]='A';
                                            else prueba[i][j]='P';
                            break;
                        case 1:
                            if(tablero[i][j].getClass()==Torre.class)prueba[i][j]='t';
                            else if(tablero[i][j].getClass()==Reina.class)prueba[i][j]='y';
                                else if(tablero[i][j].getClass()==Rey.class)prueba[i][j]='r';
                                    else if(tablero[i][j].getClass()==Caballo.class)prueba[i][j]='c';
                                        else if(tablero[i][j].getClass()==Alfil.class)prueba[i][j]='a';
                                            else prueba[i][j]='p';
                    }
                }else prueba[i][j]='*';
            }
        }
    }
    public static void llenarTablero(){
        for(int i=0;i<8;i++)
            tablero[1][i]=new Peon(1,i,true,0,"");
        for(int i=0;i<8;i++)
            tablero[6][i]=new Peon(6,i,true,1,"");

        tablero[0][0]=new Torre(0,0,true,0,"");
        tablero[0][7]=new Torre(0,7,true,0,"");
        tablero[7][0]=new Torre(7,0,true,1,"");
        tablero[7][7]=new Torre(7,7,true,1,"");

        tablero[0][1]=new Caballo(0,1,true,0,"");
        tablero[0][6]=new Caballo(0,6,true,0,"");
        tablero[7][1]=new Caballo(7,1,true,1,"");
        tablero[7][6]=new Caballo(7,6,true,1,"");

        tablero[0][2]=new Alfil(0,2,true,0,"");
        tablero[0][5]=new Alfil(0,5,true,0,"");
        tablero[7][2]=new Alfil(7,2,true,1,"");
        tablero[7][5]=new Alfil(7,5,true,1,"");

        tablero[0][3]=new Reina(0,3,true,0,"");
        tablero[7][3]=new Reina(7,3,true,1,"");

        tablero[0][4]=new Rey(0,4,true,0,"");
        tablero[7][4]=new Rey(7,4,true,1,"");
    }
    public static void mostrarMatriz(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++)System.out.print(prueba[i][j]+" ");
            System.out.println();
        }
    }
    public static void mostrarMatriz(char[][] prueba){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++)System.out.print(prueba[i][j]+" ");
            System.out.println();
        }
    }
    public static void llenarPrueba(Ficha[][] tablero){
        char[][] prueba=new char[8][8];
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(tablero[i][j]!=null){
                    switch(tablero[i][j].bando){
                        case 0:
                            if(tablero[i][j].getClass()==Torre.class)prueba[i][j]='T';
                            else if(tablero[i][j].getClass()==Reina.class)prueba[i][j]='Y';
                                else if(tablero[i][j].getClass()==Rey.class)prueba[i][j]='R';
                                    else if(tablero[i][j].getClass()==Caballo.class)prueba[i][j]='C';
                                        else if(tablero[i][j].getClass()==Alfil.class)prueba[i][j]='A';
                                            else prueba[i][j]='P';
                            break;
                        case 1:
                            if(tablero[i][j].getClass()==Torre.class)prueba[i][j]='t';
                            else if(tablero[i][j].getClass()==Reina.class)prueba[i][j]='y';
                                else if(tablero[i][j].getClass()==Rey.class)prueba[i][j]='r';
                                    else if(tablero[i][j].getClass()==Caballo.class)prueba[i][j]='c';
                                        else if(tablero[i][j].getClass()==Alfil.class)prueba[i][j]='a';
                                            else prueba[i][j]='p';
                    }
                }else prueba[i][j]='*';
            }
        }
        mostrarMatriz(prueba);
    }
}
