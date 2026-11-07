package Unidad_1.Clase7_9_26.Menu;

import java.util.Arrays;

import javax.swing.JOptionPane;

public class main {
    public static void main(String[] args) {
        String opcion,bucle="s";
        String menu="MENU\n1. Ingresar datos\n2. Mostrar datos\n3. Suma de valores del arreglo\n4. Promedio de los valores del arreglo"+
        "\n5. Producto de los valores\n6. Numeros pares\n 7. Números impares\n8. Números primos\n9. Valor mayor\n10. Valor menor\n11. Salir";

        int t = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el tamaño del arreglo"));
        xzArreglo arr = new xzArreglo(t);

        while(true){
            opcion = JOptionPane.showInputDialog(menu);
            switch (opcion) {
                case "1":
                    arr.llenar_arreglo();
                    break;
                case "2":
                    arr.mostrarArreglo();
                    break;
                case "3":
                    JOptionPane.showMessageDialog(null, "La suma de los valores: "+arr.sumarArreglo());
                    break;
                case "4":
                    JOptionPane.showMessageDialog(null, "El promedio es: "+arr.promedio());                    
                    break;
                case "5":
                    JOptionPane.showMessageDialog(null, "Producto de los valores: "+ arr.producto());
                    break;
                case "6":
                    int[] arrpares = arr.numPares();
                    JOptionPane.showMessageDialog(null, "Números pares: \n"+Arrays.toString(arrpares));
                    break;
                case "7":
                    int[] arrimpares = arr.numImpares();
                    JOptionPane.showMessageDialog(null, "Números pares: \n"+Arrays.toString(arrimpares));
                    break;
                case "8":
                    int[] arrprimos = arr.numPrimos();
                    JOptionPane.showMessageDialog(null, "Números pares: \n"+Arrays.toString(arrprimos));
                    break;
                case "9":
                    JOptionPane.showMessageDialog(null, "Valor mayor: "+arr.numMayor());
                    break;
                case "10":
                    JOptionPane.showMessageDialog(null, "Valor mayor: "+arr.numMenor());
                    break;
                case "11":
                    System.exit(0);
                    break;
                default:
                    break;
            }

        }
        
    }
}
