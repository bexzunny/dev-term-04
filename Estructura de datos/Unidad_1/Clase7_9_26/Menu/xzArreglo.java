package Unidad_1.Clase7_9_26.Menu;

import javax.swing.JOptionPane;

public class xzArreglo {
    int arr[];

    public xzArreglo(int tamaño) {
        arr = new int[tamaño];

    }

    public void llenar_arreglo() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(JOptionPane.showInputDialog("Dato de la posición: " + (i + 1)));
        }
    }
        public void mostrarArreglo(){
        for(int i=0; i<arr.length;i++){
            JOptionPane.showMessageDialog(null, "\nPosición: "+(i+1)+"\nValor: "+arr[i]);
        }
    }
    public int sumarArreglo() {
        int suma = 0;
        for (int i = 0; i < arr.length; i++) {
            suma += arr[i];
        }
        return suma;
    }

    public double promedio() {
        return (sumarArreglo() / arr.length);
    }

    public double producto() {
        int pr = 1;
        for (int i = 0; i < arr.length; i++) {
            pr *= arr[i];
        }
        return pr;
    }

    public int[] numPares() {

        int pares[] = new int[arr.length];

        for (int i = 0, a = 0; i < arr.length; i++) {
            if (arr.length % 2 == 0) {
                pares[a] = arr[i];
                a++;
            }
        }
        return pares;
    }

    public int[] numImpares() {

        int pares[] = new int[arr.length];

        for (int i = 0, a = 0; i < arr.length; i++) {
            if (arr.length % 2 == 1) {
                pares[a] = arr[i];
                a++;
            }
        }
        return pares;
    }

    public int[] numPrimos() {

        int primos[] = new int[arr.length];
        
        for (int i = 0, a=0; i < arr.length; i++) {
            if (esPrimo(arr[i])) {
                primos[a]= arr[i];
                a++;
            }
        }
        return primos;
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

    public int numMayor() {
        int menor = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (menor < arr[i]) {
                menor = arr[i];
            }
        }
        return menor;
    }

    public int numMenor() {
        int menor = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (menor > arr[i]) {
                menor = arr[i];
            }
        }
        return menor;
    }
}
