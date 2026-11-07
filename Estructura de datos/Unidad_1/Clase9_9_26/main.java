package Unidad_1.Clase9_9_26;

import java.util.Arrays;

import javax.swing.JOptionPane;


public class main {
    public static void main(String[] args) {
        String opcion,bucle="s";
        String menu="MENU\n1. Agregar datos\n2. Mostrar datos\n3. Suma de valores del arreglo\n4. Suma de la filas\n5. Suma de columnas"+
        "\n6. Numeros Pares\n7. Numeros Impares";

        int f = Integer.parseInt(JOptionPane.showInputDialog("Ingrese las filas del arreglo"));
        int c = Integer.parseInt(JOptionPane.showInputDialog("Ingrese las filas del arreglo"));
        matriz_operaciones matrix = new matriz_operaciones(f, c);

        while(true){
            opcion = JOptionPane.showInputDialog(menu);
            switch (opcion) {
                case "1":
                    matrix.agregarDatos();
                    break;
                case "2":
                    matrix.mostrarDatos();
                    break;
                case "3":
                    matrix.sumarMatriz();
                    break;
                case "4":
                    JOptionPane.showMessageDialog(null,matrix.sumarFilas());
                    break;
                case "5":
                    JOptionPane.showMessageDialog(null,matrix.sumarColumnas());
                    break;
                case "6":
                    JOptionPane.showMessageDialog(null,matrix.obtPares());
                    break;
                case "7":
                    JOptionPane.showMessageDialog(null,matrix.obtImpares());
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no reconocida");
                    break;
            }

        } 
    }
}
