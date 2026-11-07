package Unidad_1.Clase4_9_26;

import javax.swing.JOptionPane;

public class arreglos {

    public static void main(String[] args) {
        int n = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el tamaño del arreglo: "));
        int[] arreglo = new int[n];
        for (int i = 0; i < n; i++) {
            arreglo[i] = Integer
                    .parseInt(JOptionPane.showInputDialog("Ingrese el valor del elemento " + (i + 1) + ": "));
        }
        int suma = 0;
        for (int a = 0; a < n; a += 2) {
            suma += arreglo[a];
        }
        JOptionPane.showMessageDialog(null, "La suma de los elementos en posiciones impares es: " + suma);
        for (int b = 1; b < n; b++) {
            suma += arreglo[b];
        }
        JOptionPane.showMessageDialog(null,
                "El promedio de los elementos en posiciones impares es: " + (double) suma / n);
    }
}
