package Unidad_2.Clase_04_11_26;

import javax.swing.JOptionPane;

public class main {
    public static void main(String[] args) {
    int opcion, num;
    ListaDoble listita = new ListaDoble();
    do{
        opcion = Integer.parseInt(JOptionPane.showInputDialog(         
                   "SISTEMA DE INVENTARIO DE DISPOSITIVOS" +
                "\n\n1. INsertar al frente" +
                "\n2.Insertar al final" +
                "\n3. INsertar por poisición" +
                "\n4. Insertar por valor" +
                "\n5. Eliminar al frente" +
                "\n6. Eliminar al final" +
                "\n7. Elmiinar por posición" +
                "\n8. Eliminar por valor" +
                "\n9. Buscar" +
                "\n10. Modificar"+
                "\n11. Imprimir Izquierda-Derecha"+
                "\n12. Imprimir Derecha-Izquierda"+
                "\n13. Suma"+
                "\n14. Salir"
        ));
        switch (opcion) {
            case 1:
                num = Integer.parseInt(JOptionPane.showInputDialog("Teclea el valor del nodo"));
                listita.insertaralFrente(num);
                break;
            case 2:
                num = Integer.parseInt(JOptionPane.showInputDialog("Teclea el valor del nodo"));
                listita.insertarAlFinal(num);
                break;
            case 11:
                listita.mostrarIzqDer();
                break;
            case 12:
                listita.mostrarDerIzq();
                break;
            default:
                throw new AssertionError();
        }


    }while (true);   
    
    
    }

}
