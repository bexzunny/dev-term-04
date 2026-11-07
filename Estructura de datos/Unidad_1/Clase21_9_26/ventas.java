package Unidad_1.Clase21_9_26;

import javax.swing.JOptionPane;

public class ventas {
    int tamaño;
    String vendedores[];
    int ventas[];
    double comisiones[];
    double promventas = 0;

    public ventas(int tamaño) {
        this.tamaño = tamaño;
        vendedores = new String[tamaño];
        ventas =new int[tamaño];
        comisiones = new double[tamaño];
    }
    public void agregarDatos(){
        int totventas=0;
        for(int i=0; i<tamaño;i++){
            vendedores[i] = JOptionPane.showInputDialog("Teclea nombre del vendedor: "+ (i+1));
            ventas[i] = Integer.parseInt(JOptionPane.showInputDialog("Teclea la ventas de: "+vendedores[i]));
            totventas += ventas[i];
        }
        promventas = totventas / tamaño;
    }
    public void calcularComisiones (){
        double nivel_comision = (2/3) * promventas;
        for(int i = 0 ; i<tamaño; i++){
            comisiones[i] = (ventas[i]-nivel_comision) * 0.5;
        }
    }
    public String mostrar(){
        String cad = "Comisiones de vendedores\n==========================\n "+
        "Vendedor\t\tVentas\t\tComision\n";
        for(int i=0;i < tamaño;i++){
            cad+= vendedores[i]+"\t"+ventas[i]+"\t"+comisiones[i];
        }
        return cad;
    }
    public String mayorComision(){
        String strin = "";
        double mayor = 0;
        int id = 0;
        for(int i=0;i<tamaño;i++){
            if(mayor<comisiones[i]){
                mayor = comisiones[i];
                id = i;
            }
        }
        strin = "La mayor comisión es del empleado: " + vendedores[id] + " con un total de: " + mayor;
        return strin;
    }

    public String menorVentas(){
        String strin = "";
        double menor = 0;
        int id = 0;
        for(int i=0;i<tamaño;i++){
            if(menor>ventas[i]){
                menor = ventas[i];
                id = i;
            }
        }
        strin = "La mayor venta es del empleado: " + vendedores[id] + " con un total de: " + menor;
        return strin;
    }
}