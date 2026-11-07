package Unidad_1.Clase21_9_26;

import javax.swing.JOptionPane;

import Unidad_1.Clase9_9_26.matriz_operaciones;

public class main {
    public static void main(String[] args) {
        String opcion,bucle="s";
        String menu="MENU\n1. Agregar ventas\n2. Mostrar ventas\n3. Mostrar el reporte\n4. Mostrar nivel de comisión\n5. Suma de columnas";

        int t;
        t = Integer.parseInt(JOptionPane.showInputDialog("Teclea la cantidad de vendedores"));
        ventas vendedor = new ventas(t);
        vendedor.calcularComisiones();
        vendedor.calcularComisiones();
        vendedor.calcularComisiones();
        JOptionPane.showMessageDialog(null, vendedor.mostrar());
        JOptionPane.showMessageDialog(null, vendedor.mayorComision());
        JOptionPane.showMessageDialog(null, vendedor.menorVentas());
    }
}