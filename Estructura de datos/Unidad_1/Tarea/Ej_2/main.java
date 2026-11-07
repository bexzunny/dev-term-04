package Unidad_1.Tarea.Ej_2;

public class main {

    public static void main(String[] args) {

        arreglo numeros = new arreglo(20);

        numeros.llenar_arreglo();

        numeros.sinDuplicados();

        numeros.ordenar();

        System.out.println("===== ARREGLO ORIGINAL =====");
        System.out.println(numeros.mostrarOriginal());

        System.out.println("===== NÚMEROS REPETIDOS =====");
        System.out.println(numeros.repetidos());

        System.out.println("===== VECES QUE APARECE CADA NÚMERO =====");
        System.out.println(numeros.contarRepetidos());

        System.out.println("===== NÚMERO MÁS REPETIDO =====");
        System.out.println("Número: " + numeros.numeroFamoso());

        System.out.println("===== ARREGLO SIN DUPLICADOS ORDENADO =====");
        System.out.println(numeros.mostrarUnicos());
    }
}