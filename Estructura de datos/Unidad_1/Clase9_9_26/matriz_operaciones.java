package Unidad_1.Clase9_9_26;

import javax.swing.JOptionPane;

public class matriz_operaciones {
    int numeros[][];
    int filas;
    int columnas;

    public matriz_operaciones(int filas, int columnas) {
        numeros = new int[filas][columnas];
        this.filas = filas;
        this.columnas = columnas;
    }

    public void agregarDatos(){
        for(int f=0; f<filas;f++){
            for(int c=0; c<columnas;c++){
                numeros[f][c] = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero: ["+(f+1)+"]["+(c+1)+"]"));
            }
        }
    }    

    public void mostrarDatos(){
        System.out.println("======Los datps de la matríz son: =====");
        for(int f=0; f<filas;f++){
            for(int c=0; c<columnas;c++){
                System.out.println(numeros[f][c]+" ");
            }
            System.out.println("\n");
        }
    }    
        public int sumarMatriz(){
        int suma=0;
        for(int f=0; f<filas;f++){
            for(int c=0; c<columnas;c++){
                suma+=numeros[f][c];
            }
        }
        return suma;
    }    
    public String sumarFilas(){
        int sumaf = 0;
        String cad="===suma de filas==\n";
        for(int f=0; f<filas;f++){
            for(int c=0; c<columnas;c++){
                sumaf+=numeros[f][c];
            }
            cad+="Fila: "+(f+1)+"="+sumaf;
            sumaf = 0;
        }
        return cad;
    }
    public String sumarColumnas(){
        int sumaf = 0;
        String cad="===suma de columnas==\n";
        for(int c=0; c<columnas;c++){
            for(int f=0; f<filas;f++){
                sumaf+=numeros[c][f];
            }
            cad+="Columnas: "+(c+1)+"="+sumaf;
            sumaf = 0;
        }
        return cad;
    }
    
    public String obtImpares(){
        int sumaf = 0;
        String cad="===Numeros Impares==\n";
        for(int f=0; f<filas;f++){
            for(int c=0; c<columnas;c++){
                if(numeros[f][c]%2 == 0){
                    cad+=numeros[f][c]+" ";
                }
                cad+="\n";
            }

        }
        return cad;
    }
    
    public String obtPares(){
        int sumaf = 0;
        String cad="===Numeros Impares==\n";
        for(int f=0; f<filas;f++){
            for(int c=0; c<columnas;c++){
                if(numeros[f][c]%2 == 1){
                    cad+=numeros[f][c]+" ";
                }
                cad+="\n";
            }

        }
        return cad;
    }

public String obtPrimos(){
    String cad = "===Numeros Primos===\n";

    for(int f = 0; f < filas; f++){
        for(int c = 0; c < columnas; c++){

            if (esPrimo(numeros[f][c])){
                cad+=numeros[f][c];
            }

            cad += "\n";
        }
    }

    return cad;
}

    public static boolean esPrimo(int numero) {
        if (numero < 2)
            return false;

        for (int i = 2; i * i <= numero; i++) {
            if (numero % i  == 0) {
                return false;
            }
        }

        return true;
    }
    
}
