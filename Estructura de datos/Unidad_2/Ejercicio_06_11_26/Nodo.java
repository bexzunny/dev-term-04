package Unidad_2.Ejercicio_06_11_26;

public class Nodo {
    TransporteCarga transporte;
    Nodo siguiente;
    public Nodo(TransporteCarga transporte){
        this.transporte = transporte;
        this.siguiente = null;
    }
}
