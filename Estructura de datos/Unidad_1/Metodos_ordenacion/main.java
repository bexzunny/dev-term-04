package Unidad_1.Metodos_ordenacion;

import java.util.Arrays;

import javax.swing.JOptionPane;

public class main {
    public static void main(String[] args) {
        int n = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de elementos de la matriz"));
        int[] x = new int [n];
        
        for(int i=0; i<x.length;i++){
            x[i]  = Integer.parseInt(JOptionPane.showInputDialog(("Ingrese el numero:" +(i+1))));
        }
        System.out.println("Arreglo original");
        for(int t = 0; t<n ; t++){
            System.out.print(x[t]+" ");
        }
        int[] copia = Arrays.copyOf(x, x.length);

        System.out.println("\nBubble sort\n-----------------");
        bubble_sort bubble = new bubble_sort();
        bubble.bubbleSort(copia);
        System.out.println("\n-----------------");



        System.out.println("Quick sort\n-------------------");
        quick_sort quick = new quick_sort();
        copia = Arrays.copyOf(x, x.length);
        System.out.print("Interambios: \n");
        quick.quickSort(copia, 0, n-1);
        System.out.println();
                quick.imprimirArreglo(copia);
        System.out.println("-----------------");



        System.out.println("heap sort\n-------------------");
        copia = Arrays.copyOf(x, x.length);
        heap_sort heap = new heap_sort();
                System.out.print("Interambios: \n");
        heap.sort(copia);
        System.out.println();
        heap.imprimirArreglo(copia);
    }
}
