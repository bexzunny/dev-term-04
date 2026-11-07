package Unidad_1.Tarea.Ej_1;

import javax.swing.JOptionPane;

public class alumnos {
    int ALUMNOS = 30;
    double cal[];
    double salvados[];
    public alumnos(int aLUMNOS) {
        ALUMNOS = aLUMNOS;
        this.cal = new double[ALUMNOS];
    }
    
    public void llenar_arreglo(){
        for (int i = 0; i < ALUMNOS; i++) {
            double v = 0;
            do {
                String n = JOptionPane.showInputDialog("Ingresa la calificación del alumno: " + (i + 1));
                v = validar(n);
            } while (v == -1);
            cal[i] = v;
        } 
    }

    public double validar(String n) {
        try {
            double x = Double.parseDouble(n);
            if (x <= 10.0 && x >= 0.0) {
                return x;
            } else {
                return -1;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Solo números entre 0 y 10");
            return -1;
        }
    }
    public double mayor(){
        double m=0;
        for(int i=0;i<ALUMNOS;i++){
            if(m<cal[i]){
                m=cal[i];
            }
        }
        return m;
    }
    public double menor(){
        double m=cal[0];
        for(int i=0;i<ALUMNOS;i++){
            if(m>cal[i]){
                m=cal[i];
            }
        }
        return m;
    }
    public int aprobaron(){
        int m=0;
        for(int i=0;i<ALUMNOS;i++){
            if(cal[i]>=7.0){
                m++;
            }
        }
        return m;
    }

    public int reprobaron(){
        int m=0;
        for(int i=0;i<ALUMNOS;i++){
            if(cal[i]<7.0){
                m++;
            }
        }
        return m;
    }
    public double promedio(){
        double m=0;
        for(int i=0;i<ALUMNOS;i++){
            m+=cal[i];
        }
        return (m/ALUMNOS);
    }
    public int mayPromedio(){
        int m=0;
        double prom = promedio();
        for(int i=0;i<ALUMNOS;i++){
            if(cal[i]>prom){
                m++;
            }
        }
        return m;
    }

        public String maximos(){
        String m="";
        double prom = promedio();
        for(int i=0;i<ALUMNOS;i++){
            if(cal[i]==10){
                m+= " ["+ i +"] \n";
            }
        }
        return m;
    }
    public void aprobados(){
        this.salvados = new double[aprobaron()];
        for (int i = 0, x = 0; i < ALUMNOS; i++) {
            if (cal[i] >= 7.0) {
                salvados[x] = cal[i];
                x++;
            }
        }
    }

    public void ordenar(){
                int n = salvados.length;
        for (int i = 1; i < n; ++i) {
            double key = salvados[i];
            int j = i - 1;
            while (j >= 0 && salvados[j] > key) {
                salvados[j + 1] = salvados[j];
                j = j - 1;
            }
            salvados[j + 1] = key;
        }
    }
    public String mostrarAprobados() {

    String m = "";

    for (int i = 0; i < salvados.length; i++) {
        m += salvados[i] + " ";
    }

    return m;
}
}
