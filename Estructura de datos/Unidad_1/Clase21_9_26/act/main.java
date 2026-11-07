package Unidad_1.Clase21_9_26.act;

import javax.swing.JOptionPane;


public class main {
    public static void main(String[] args) {

                String opcion;
        String menu="MENU\n1. Agregar producto\n2. Vender producto\n3. Mostrar ventas de la tienda\n4. Mostrar ventas de un deporte\n5. Mostrar total de ventas\n6. Salir";

        int fil,col;
        tiendas nexia = new tiendas();
        while (true) {
            opcion = JOptionPane.showInputDialog( menu);
            switch (opcion) {
                case "1":
                    fil = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de la tienda (1-50)"));
                    col = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de deporte (1-20("));
                    nexia.agregarDatos(fil,col);
                    break;
                case "2":
                    fil = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de la tienda (1-50)"));
                    col = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero del producto (1-20("));
                    nexia.venderPr(fil, col);
                    break;
                case "3":
                    fil = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de la tienda (1-50)"));
                    nexia.calcularVentasTienda(fil);
                    break;
                case "4":
                    col = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero del deporte (1-2o)"));
                    nexia.calcularVentasDeporte(col);
                    break;
                case "5":
                    nexia.obtenerVentaTotal();  
                    break;
                case "6":
                    
                    break;
                default:
                    break;
            }
        }

    }
}
