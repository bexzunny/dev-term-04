package Unidad_1.Clase7_9_26;

import javax.swing.JOptionPane;

public class main {
    public static void main(String[] args) {
        int t;
        t=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el tamaño del arreglo"));
        OperArreglos oper = new OperArreglos(t);
        oper.llenarArreglo();
        oper.mostrarArreglo();
        JOptionPane.showMessageDialog(null, "Estadisticas:\nSuma: "+oper.sumarArreglo()+"\nPromedio: "+oper.promedio()+"\nMenor: "+oper.menorPeso());
        JOptionPane.showMessageDialog(null, "Suma de los números por debajo del promedio: " + oper.debajoPromedio());
        int numero = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero a buscar"));
        JOptionPane.showMessageDialog(null, "El resultado de la búscuqeda es: "+oper.siEsta(numero));
    }
}
