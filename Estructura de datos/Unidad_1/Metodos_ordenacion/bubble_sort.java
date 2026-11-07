package Unidad_1.Metodos_ordenacion;

public class bubble_sort {
    public static void bubbleSort(int[] numeros) {
                    System.out.println("Intercambios/comparaciones: ");
        for (int i = 0; i < numeros.length - 1; i++) {
            for (int j = 0; j < numeros.length - 1 - i; j++) {
                if (numeros[j] > numeros[j + 1]) {
                    System.out.print("+");
                        int temporal = numeros[j];
                        numeros[j] = numeros[j + 1];
                        numeros[j + 1] = temporal;
                }
            }
        }
        System.out.println();
        System.out.print("[");
        for(int i=0 ;i<numeros.length;i++){
            System.out.print(numeros[i]+",");
        }
        System.out.print("]");
    }
}
