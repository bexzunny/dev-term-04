package Unidad_1.Clase4_9_26;

import javax.swing.JOptionPane;

public class matriz {
    public static void main(String[] args) {
        int f = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de filas"));
        int c = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de columnas"));
        int[][] matriz = new int[f][c]; 
        for(int row=0;row<f;row++){
            for(int col=0;col<c;col++){
                int num = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuemero de la fila:"+(row+1)+ " columna: "+ (col+1)));
            }
        }
        int suma = 0;
        for(int row=0;row<f;row++){
            for(int col=0;col<c;col++){
                suma+=matriz[row][col];
            }
        }
        JOptionPane.showMessageDialog(null, "La suma de las filas y columnas es de: \n"+suma);
    }
}
