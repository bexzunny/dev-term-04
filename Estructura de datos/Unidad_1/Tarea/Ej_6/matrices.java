package Unidad_1.Tarea.Ej_6;

import javax.swing.JOptionPane;

public class matrices {

    int FILAS = 6;
    int COLUMNAS = 6;
    int mat[][];

    public matrices(){
        this.FILAS = 6;
        this.COLUMNAS = 6;
        this.mat = new int[FILAS][COLUMNAS];
    }

    public void llenar_arreglo(){
        for(int i=0;i<FILAS;i++){
            for(int o=0;o<COLUMNAS;o++){
                String n = JOptionPane.showInputDialog("Ingresa el número de la posición ["+ i + "][" + o + "]");
                mat[i][o] = Integer.parseInt(n);
            }
        }
    }

    public void mostrarMatriz(){
        for(int i=0;i<FILAS;i++){
            for(int o=0;o<COLUMNAS;o++){
                System.out.print(mat[i][o] + " ");
            }
            System.out.println();
        }
    }

    public int sumaTotal(){
        int suma=0;
        for(int i=0;i<FILAS;i++){
            for(int o=0;o<COLUMNAS;o++){
                suma+=mat[i][o];
            }
        }
        return suma;
    }

    public int sumaFila(int f){
        int suma=0;
        for(int i=0;i<COLUMNAS;i++){
            suma+=mat[f][i];
        }
        return suma;
    }

    public int sumaColumna(int c){
        int suma=0;
        for(int i=0;i<FILAS;i++){
            suma+=mat[i][c];
        }
        return suma;
    }

    public int mayor(){
        int m=mat[0][0];
        for(int i=0;i<FILAS;i++){
            for(int o=0;o<COLUMNAS;o++){
                if(m<mat[i][o]){
                    m=mat[i][o];
                }
            }
        }
        return m;

    }

    public String posicionMayor(){
        String pos="";
        int m=mayor();
        for(int i=0;i<FILAS;i++){
            for(int o=0;o<COLUMNAS;o++){
                if(mat[i][o]==m){
                    pos+="[" + i + "][" + o + "]\n";
                }
            }
        }
        return pos;
    }

    public int menor(){
        int m=mat[0][0];
        for(int i=0;i<FILAS;i++){
            for(int o=0;o<COLUMNAS;o++){
                if(m>mat[i][o]){
                    m=mat[i][o];
                }
            }
        }
        return m;
    }

    public String posicionMenor(){
        String pos="";
        int m=menor();
        for(int i=0;i<FILAS;i++){
            for(int o=0;o<COLUMNAS;o++){
                if(mat[i][o]==m){
                    pos+="[" + i + "][" + o + "]\n";
                }
            }
        }
        return pos;
    }

    
    public int diagonalPrincipal(){
        int sum = 0;
        for(int i = 0; i<FILAS;i++){
            sum+=mat[i][i];
        }
        return sum;

    }

//fucking  -1
    public int diagonalSecundaria(){
        int sum = 0;
        for(int i = FILAS-1; i>=0;i--){
            sum+=mat[i][FILAS-i-1];
        }
        return sum;

    }

    public int pares(){
        int p=0;
        for(int i=0;i<FILAS;i++){
            for(int o=0;o<COLUMNAS;o++){
                if(mat[i][o]%2==0){
                    p++;
                }
            }
        }
        return p;
    }

    public int impares(){

        int p=0;
        for(int i=0;i<FILAS;i++){
            for(int o=0;o<COLUMNAS;o++){
                if(mat[i][o]%2!=0){
                    p++;
                }
            }
        }
        return p;
    }

    public boolean esSimetrica(){

        for(int i=0;i<FILAS;i++){
            for(int o=0;o<COLUMNAS;o++){
                if(mat[i][o]!=mat[o][i]){
                    return false;
                }
            }
        }
        return true;
    }

}