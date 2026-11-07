package Unidad_1.Tarea.Ej_4;

import javax.swing.JOptionPane;

public class tiendas {

    int SUCURSALES = 5;
    int DIAS = 7;
    double ventas[][];
    double reporte[][];

    public tiendas() {
        this.SUCURSALES = 5;
        this.DIAS = 7;
        this.ventas = new double[SUCURSALES][DIAS];
        this.reporte = new double[SUCURSALES][3];
    }

    public void llenar_arreglo(){
        for(int i=0;i<SUCURSALES;i++){
            for(int o=0;o<DIAS;o++){
                double v=0;
                do{
                    String n = JOptionPane.showInputDialog("Ingresa las ventas de la sucursal: "+ (i+1) + " del día: " + (o+1));
                    v = validar(n);
                }while(v==-1);
                ventas[i][o] = v;
            }
        }
    }

    public double validar(String n){
        try{
            double x = Double.parseDouble(n);
            if(x>=0){
                return x;
            }else{
                return -1;
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Solo números positivos");
            return -1;
        }
    }

    public double totalSucursal(int s){
        double total=0;
        for(int i=0;i<DIAS;i++){
            total+=ventas[s][i];
        }
        return total;
    }
    public double totalDia(int d){
        double total=0;
        for(int i=0;i<SUCURSALES;i++){
            total+=ventas[i][d];
        }
        return total;
    }

    public int sucursalTop(){
        int s=0;
        double max=0;
        for(int i=0;i<SUCURSALES;i++){
            double total = totalSucursal(i);
            if(max<total){
                max=total;
                s=i;
            }
        }
        return s;
    }

    public int diaTop(){
        int d=0;
        double max=0;
        for(int i=0;i<DIAS;i++){
            double total = totalDia(i);
            if(max<total){
                max=total;
                d=i;
            }
        }
        return d;
    }


    public String mostrarTotalSucursal(){
        String s="";
        for(int i=0;i<SUCURSALES;i++){
            s+="Sucursal " + (i+1) + ": "
                    + String.format("%.2f",totalSucursal(i)) + "\n";
        }
        return s;
    }

    public String mostrarTotalDia(){
        String s="";
        for(int i=0;i<DIAS;i++){
            s+="Día " + (i+1) + ": "
                    + String.format("%.2f",totalDia(i)) + "\n";
        }
        return s;
    }



    
    public double promedio(){
        double total=0;
        for(int i=0;i<SUCURSALES;i++){
            total+=totalSucursal(i);
        }
        return total/(SUCURSALES*DIAS);
    }

    public String sucursalesSobrePromedio(){
        String s="";
        double prom=promedio();
        for(int i=0;i<SUCURSALES;i++){
            double total=totalSucursal(i);
            double promSucursal=total/DIAS;
            if(promSucursal>prom){
                s+="Sucursal: " + (i+1)+ " | Promedio: " + String.format("%.2f",promSucursal)+ "\n";
            }
        }
        return s;
    }

    public int diaMenor(){
        int d=0;
        double menor=totalDia(0);
        for(int i=1;i<DIAS;i++){
            double total=totalDia(i);
            if(total<menor){
                menor=total;
                d=i;
            }
        }
        return d;
    }
    public void llenarReporte(){
        for(int i = 0; i < SUCURSALES; i++){
            double totals = totalSucursal(i);
            reporte[i][0] = i;
            reporte[i][1] = totals;
            reporte[i][2] = totals/DIAS;

        }
    }
public void mostrarReporte(){

    System.out.println("Sucursal | Total | Promedio");

    for(int i=0;i<SUCURSALES;i++){

        System.out.println( 
                (int)(reporte[i][0]+1) + " | "+ String.format("%.2f",reporte[i][1])+ " | "+ String.format("%.2f",reporte[i][2]));

    }

}
}