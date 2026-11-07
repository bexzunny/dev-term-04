package Unidad_1.Tarea.Ej_3;

import javax.swing.JOptionPane;

public class escuela {
    int GRUPOS = 4;
    int ESTUDIANTES = 6;
    double esc[][];
    public escuela() {
        this.GRUPOS = 4;
        this.ESTUDIANTES = 6;
        this.esc = new double[GRUPOS][ESTUDIANTES];
    }
    public void llenar_arreglo(){
        for (int i = 0; i < GRUPOS; i++) {
            for (int o = 0; o < ESTUDIANTES; o++) {
            double v = 0;
            do {
                String n = JOptionPane.showInputDialog("Ingresa la calificación del alumno: " + (o + 1) + " del  grupo: " + (i+1));
                v = validar(n);
            } while (v == -1);
            esc[i][o] = v;
            }

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
    public double promedioGrupo(int g){
        double prom = 0;
        for(int i=0;i<ESTUDIANTES;i++){
            prom+=esc[g][i];
        }
        return (prom/ESTUDIANTES);
    }
    public double promedioGeneral(){
        double prom=0;
        for(int i=0;i<GRUPOS;i++){
            prom+=promedioGrupo(i);
        }
        return (prom/GRUPOS);
    }

    public void califMax(){
        double max = 0;
        int g=0,e=0;
        for(int i=0;i<GRUPOS;i++){
            for (int o = 0; o < ESTUDIANTES; o++) {
                if(max<esc[i][o]){
                    max = esc[i][o];
                    g = i;
                    e = o;
                }
            }
        }

        System.out.println("==== Califiación más alta ===\n" + max);
        System.out.println("Grupo: " + (g+1)+"\nEstudiante: " + (e+1));
    }
    public int aprobaron(){
        int aprobados = 0;
        for(int i=0;i<GRUPOS;i++){
            for (int o = 0; o < ESTUDIANTES; o++) {
                if(esc[i][o]>=7.0){
                    aprobados++;
                }
            }
        }
        return aprobados;
    }

        public int reprobaron(){
        int reprobados = 0;
        for(int i=0;i<GRUPOS;i++){
            for (int o = 0; o < ESTUDIANTES; o++) {
                if(esc[i][o]<7.0){
                    reprobados++;
                }
            }
        }
        return reprobados;
    }
    public int grupoTop(){
        int gr =0;
        double prommax=0;
        
        for(int i=0;i<GRUPOS;i++){
            double p = promedioGrupo(i);
            if(prommax<p){
                prommax = p;
                gr = i;
            }
            }
        return gr;
    }
    public String alumnosDestacados(){
        String al = "";
        double prom = promedioGeneral();
        for(int i=0;i<GRUPOS;i++){
            for (int o = 0; o < ESTUDIANTES; o++) {
                if(esc[i][o]>prom){
                        al += "Grupo: " + (i + 1) + " | Estudiante: " + (o + 1)+ " | Calificación: " + esc[i][o] + "\n";
                }
            }
        }
        return al;
    }
}
