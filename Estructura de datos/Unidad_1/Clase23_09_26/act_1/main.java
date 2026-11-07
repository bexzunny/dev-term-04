package Unidad_1.Clase23_09_26.act_1;

import javax.swing.JOptionPane;


public class main {
    public static void main(String[] args) {
        int f,c,com;
        f = Integer.parseInt(JOptionPane.showInputDialog("Teclea la cantidad de filas"));
        c = Integer.parseInt(JOptionPane.showInputDialog("Teclea la cantidad de columnas"));
        com = Integer.parseInt(JOptionPane.showInputDialog("Teclea la cantidad de comensales"));
        restaurante rest = new restaurante(f, c, com);
        rest.asignar_valores();
        rest.llenar_comensales();
        rest.llenar_mesa();
    }
}
