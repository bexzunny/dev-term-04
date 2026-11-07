package Unidad_1.Tarea.Ej_2;

import javax.swing.JOptionPane;

public class arreglo {
    int NUMEROS = 20;
    int num[];
    int unicos[];
    int cantidadUnicos;
    public arreglo(int nUMEROS) {
        NUMEROS = nUMEROS;
        this.num = new int[NUMEROS];
    }
    public void llenar_arreglo(){
        for (int i = 0; i < NUMEROS; i++) {
            int v = 0;
            do {
                String n = JOptionPane.showInputDialog("Ingresa le numero de la posición: " + (i + 1));
                v = validar(n);
            } while (v == -1);
            num[i] = v;
        } 
    }

    public int validar(String n) {
        try {
            int x = Integer.parseInt(n);
            return x;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Solo números");
            return -1;
        }
    }

    // no puedo pensar en uno que no sea n²
    public String repetidos(){
        String rep="";
        for(int i=0;i< NUMEROS;i++){
            for(int o=i+1; o<NUMEROS;o++){
                if(num[i]==num[o]){
                    rep+= num[i]+" ";
                }
            }
        }
        return rep;
    }

    public String contarRepetidos(){
        String rep="";
        
        for(int i=0;i< NUMEROS;i++){
            int repe=0;
            for(int o=0; o<NUMEROS;o++){
                if(num[i]==num[o]){
                    repe++;
                }
            }
            rep+="Numero: " + num[i] + " : " + repe + " vececs\n";
        }
        return rep;
    }
    public void sinDuplicados(){
        this.unicos = new int[NUMEROS];
        int x=0;
        for(int i=0;i<NUMEROS;i++){
            boolean yaEsta=false;

            for(int o=0;o<i;o++){
                if(num[i]==num[o]){
                    yaEsta=true;
                    break;
                }
            }

            if(yaEsta){
                continue;
            }else{
                unicos[x] = num[i];
                x++;
            }

        }
        cantidadUnicos = x;
    }
    public int numeroFamoso(){
        int numero;
        int veces;
        int vecesmax = 0;
        int nummax = 0;
        for(int i=0;i< NUMEROS;i++){
            numero = num[i];
            veces=0;
            for(int o=i; o<NUMEROS;o++){
                if(num[i]==num[o]){
                    veces++;
                }
            }
            if(veces>vecesmax){
                vecesmax = veces;
                nummax = numero;
            }
        }
        return nummax;
    }

    public void ordenar(){
                int n = unicos.length;
        for (int i = 1; i < n; ++i) {
            int key = unicos[i];
            int j = i - 1;
            while (j >= 0 && unicos[j] > key) {
                unicos[j + 1] = unicos[j];
                j = j - 1;
            }
            unicos[j + 1] = key;
        }
    }
    public String mostrarOriginal() {

        String m = "";

        for (int i = 0; i < num.length; i++) {
            m += num[i] + " ";
        }

        return m;
    }
    public String mostrarUnicos() {

        String m = "";

        for (int i = 0; i < cantidadUnicos; i++) {
            m += unicos[i] + " ";
        }

        return m;
    }
}
