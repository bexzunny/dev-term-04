package Unidad_1.Clase21_9_26.act;

import javax.swing.JOptionPane;

public class tiendas {

    int f = 20;
    int c = 50;
    double empresa[][];
    double ventas[][];
        int totventas=0;
    double promventas = 0;

    public tiendas() {
        this.empresa = new double[f][c];
        this.ventas = new double[f][c];
    }  


    public void agregarDatos(int tienda, int posicion){
        if(tienda <= f && posicion<c){
            tienda-=1;
            posicion-=1;
        JOptionPane.showMessageDialog(null, "Bienvenido, empresa: "+ tienda);

        int x = 0;
            if(empresa[tienda][posicion] == 0.0){
            empresa[tienda][posicion] = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del producto en la categpria: "+ posicion));
            totventas+= empresa[tienda][posicion];
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Numero invalido");
        }

    }


    public void venderPr (int tienda, int pr){
                if(tienda <= f && pr<c){
            tienda-=1;
            pr-=1;
        ventas[tienda][pr] += empresa[tienda][pr];
        JOptionPane.showMessageDialog(null, "Producto vendido con éxito");
                }
        else{
            JOptionPane.showMessageDialog(null, "Numero invalido");
        }
    }
    public void calcularVentasTienda(int tienda){
                if(tienda <= f){
            tienda-=1;
        int suma = 0;
        for(int i=0;i<c;i++){
            suma+=ventas[tienda][i];
        }
        JOptionPane.showMessageDialog(null, "El total vendido de la tienda: "+ tienda + " es: $" + suma);
    }
        else{
            JOptionPane.showMessageDialog(null, "Numero invalido");
        }
    }
    public void calcularVentasDeporte(int deporte){
                        if(deporte<c){
            deporte-=1;
        int suma = 0;
        for(int fil=0;fil<c;fil++){
            suma+=ventas[fil][deporte];
        }
        JOptionPane.showMessageDialog(null, "El total vendido de la seccion: "+ deporte + " es: $" + suma);
    }
        else{
            JOptionPane.showMessageDialog(null, "Numero invalido");
        }
    }


    public void obtenerVentaTotal(){
        int sumat=0;
        for(int i=0;i<f;i++){
            sumat = 0;
            for(int o = 0; o<c;o++){
                sumat+=ventas[i][o];
            }
            System.out.println("El total de la tineda: "+(i+1)+" es: $"+sumat);
        }
        JOptionPane.showMessageDialog(null, "El total de las ventas de todos es de: $" + totventas);
    }
}
