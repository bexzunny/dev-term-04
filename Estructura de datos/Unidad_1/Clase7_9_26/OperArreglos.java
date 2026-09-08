package Unidad_1.Clase7_9_26;

import javax.swing.JOptionPane;

public class OperArreglos {
    int pesos[];
    public OperArreglos(int tamaño){
        pesos = new int[tamaño];
        
    }
    public void llenarArreglo(){
        for(int i=0; i<pesos.length;i++){
            pesos[i] = Integer.parseInt(JOptionPane.showInputDialog("Dato de la posición: "+(i+1)));
        }
    }
        public void mostrarArreglo(){
        for(int i=0; i<pesos.length;i++){
            JOptionPane.showMessageDialog(null, "\nPosición: "+(i+1)+"\nValor: "+pesos[i]);
        }
    }
    public int sumarArreglo(){
        int suma=0;
        for(int i=0; i<pesos.length;i++){
        suma+=pesos[i];
        }
        return suma;
    }
    public double promedio(){
        return (sumarArreglo()/pesos.length);
    }

    public int menorPeso(){
        int menor=pesos[0];
        for(int i=1;i<pesos.length;i++){
            if(menor<pesos[i]){
                menor=pesos[i];
            }
        }
        return menor;
    }
    public int debajoPromedio(){
        int suma=0;
        double prom= promedio();
        for(int i=0;i<pesos.length;i++){
            if(pesos[i]<prom){
                suma+=pesos[i];
            }
        }
        return suma;
    }
    public boolean siEsta(int peso){
        for(int i=0;i<pesos.length;i++){
            if(pesos[i] == peso){
                return true;
            }
        }
        return false;
    }
}
