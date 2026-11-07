package Unidad_2.Clase_12_10_16;

import java.util.Arrays;

import javax.swing.JOptionPane;

import Unidad_1.Clase7_9_26.Menu.xzArreglo;

public class main {
    public static void main(String[] args) {
                String oxion,bucle="s";
                int opcion,dato;
        String menu="MENU\n1. Insertar al frente\n2. Insertar al final\n3. Insertar por posición\n4. Insertar por valor"+
        "\n5. Eliminar al frente\n6. Eliminar al final\n 7. Eliminar por posición\n8. Eliminar por valor\n9. Buscar\n10. Modificar\n11. Mostrar\n12. Sumar elementos\n13. Producto de los elementos\n14. Numeros pares\n15. Numeros primos\n16. Salir";

        
                    ListaSimple listita = new ListaSimple();

        while(true){
            oxion = JOptionPane.showInputDialog(menu);
            switch (oxion) {
                case "1":
                    dato = Integer.parseInt(JOptionPane.showInputDialog("Dato a insertar"));
                    listita.insertarInicio(dato);
                    System.out.println("Dato insertado al inicio");
                    break;
                case "2":
                    dato = Integer.parseInt(JOptionPane.showInputDialog("Dato a insertar"));
                    listita.insertarFinal(dato);
                    System.out.println("Dato insertado al final");
                    break;
                case "3":
                    dato = Integer.parseInt(JOptionPane.showInputDialog("Dato a insertar"));
                    int pos = Integer.parseInt(JOptionPane.showInputDialog("Indice"));
                    listita.insertarPorPosicion(dato, pos);
                    System.out.println("Dato insertado al final");
                    break;
                case "4":
                    dato = Integer.parseInt(JOptionPane.showInputDialog("Dato a insertar"));
                     pos = Integer.parseInt(JOptionPane.showInputDialog("Dato a buscar"));
                    listita.insertarPorValor(dato, pos);
                    System.out.println("Dato insertado al final");
                case "5":
                    listita.eliminarFrente();
                    break;
                case "6":
                    listita.eliminarFinal();
                    break;
                case "7":

                    break;
                case "8":
                     pos = Integer.parseInt(JOptionPane.showInputDialog("Indice a eliminar"));
                    listita.eliminarPorValor(pos);
                    break;
                case "9":
                    int datobuscar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el dato a buscar"));
                    JOptionPane.showMessageDialog(null, "El dato se encuentra en la posición: "+listita.buscar(datobuscar));
                    
                    break;
                case "10":
                    
                    break;
                case "11":
                    listita.mostrar();
                    break;
                case "12":
                    System.out.println("=====================");
                    System.out.println("Suma de los elementos");
                    System.out.println("=====================");
                    listita.sumarLista();
                    break;
                case "13":
                    System.out.println("======================");
                    System.out.println("Multi de los elementos");
                    System.out.println("======================");
                    listita.multiplicarLista();
                    break;
                case "14":
                    System.out.println("=========================");
                    System.out.println("Numeros pares de la lista");
                    System.out.println("=========================");
                    listita.numerosPares();
                    break;
                case "15":
                    System.out.println("==========================");
                    System.out.println("Numeros primos de la lista");
                    System.out.println("==========================");
                    listita.numerosPrimos();
                    break;
                case "16":
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }
}
