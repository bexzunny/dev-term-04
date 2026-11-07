package Unidad_1.Clase28_9_2026.act_1;

import javax.swing.JOptionPane;

public class main {
    public static void main(String[] args) {
        int n = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero del factorial"));
        int r =1 ;
        for(int i=1; i<=n;i++){
            r*= i;
        }
        JOptionPane.showMessageDialog(null, "El resultado es: "+ r);
    }
}
