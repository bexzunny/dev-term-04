package Unidad_1.Clase23_09_26.act_1;

import javax.swing.JOptionPane;

public class restaurante {
    int f;
    int c;
    int com;
    int mesas[][];
    boolean mesas_llenas[][];
    int comensales[];

    public restaurante(int f, int c, int com) {
        this.f = f;
        this.c = c;
        this.com = com;
        this.mesas = new int[f][c];
        this.mesas_llenas = new boolean[f][c];
        this.comensales = new int[com];
    }

    public void asignar_valores() {
        for (int fil = 0; fil < f; fil++) {
            for (int col = 0; col < c; col++) {
                mesas[fil][col] = Integer.parseInt(JOptionPane.showInputDialog(
                        "Ingrese la cantidad de comensales para la mesa: [" + (fil + 1) + "][" + (col + 1) + "]"));
            }
        }
    }

    public void llenar_comensales() {
        for (int fil = 0; fil < com; fil++) {
            comensales[fil] = Integer.parseInt(JOptionPane
                    .showInputDialog("Ingrese la cantidad de comensales de la posicion: [" + (fil + 1) + "]"));

        }
    }

    public void llenar_mesa() {
        for (int comen = 0; comen < com; comen++) {
            for (int fil = 0; fil < f; fil++) {
                for (int col = 0; col < c; col++) {
                    if (mesas_llenas[fil][col]) {
                        if (mesas[fil][col] == comensales[comen]) {
                            JOptionPane.showMessageDialog(null, "A los comensales de la posición: " + comen
                                    + "\nSe les asignó la mesa: [" + (fil + 1) + "][" + (col + 1) + "]");
                            mesas_llenas[fil][col] = false;
                        }
                    }

                }
            }
        }

    }

}
