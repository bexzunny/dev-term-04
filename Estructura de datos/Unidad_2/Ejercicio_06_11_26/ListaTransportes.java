package Unidad_2.Ejercicio_06_11_26;

import javax.swing.JOptionPane;

import Unidad_2.clase07_10_26.primer;

public class ListaTransportes {
    Nodo primero;
    Nodo actual;
    public ListaTransportes(){
        primero = null;
    }
    public void registrarTransporte(TransporteCarga trans){
        Nodo nuevo = new Nodo(trans);
        if (primero == null) {
            primero = nuevo;
        }
        else{
            actual = primero;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }

    public void mostrarTransportes(){
        actual = primero;
        if (actual == null) {
            System.out.println("La lista está vacía");
        }
        while (actual.siguiente != null) {


            actual = actual.siguiente;
        }
    }

    public void buscarPorCodigo(String cod){
        actual = primero;
        while (actual != null) {
            if (actual.transporte.getCodigo().equalsIgnoreCase(cod)) {
                //mostrar
            }
            actual = actual.siguiente;
        }
    }

    public void modificarTransporte(String cod){
        actual = primero;
        while (actual != null) {
            if (actual.transporte.codigo.equalsIgnoreCase(cod)) {
                String res = "0";
                while (!res.equals("3")) {
                 res = JOptionPane.showInputDialog("Selecciona que desea modificar\n1. Capacidad\n2. Disponibilidad\n3. Ok");
                switch (res) {
                    case "1":
                        double newCod = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la nueva capacidad.\nActual: "+actual.transporte.capacidadToneladas));
                        actual.transporte.capacidadToneladas = newCod;
                        break;
                    case "2":
                        actual.transporte.setDisponible(!actual.transporte.isDisponible());
                        if (actual.transporte.isDisponible()) {
                            System.out.println("El vehículo está disponible ahora");
                        }else{
                            System.out.println("El vehículo no se encuentra disponible ahora");
                        }
                        break;
                    case "3":
                        
                        break;
                    default:
                        System.out.println("Opción no reconocida");
                }                    
                }

            }
            actual = actual.siguiente;
        }
    }


    public void eliminarPorCodigo(String cod){
        actual = primero;
        Nodo anterior = null;
        while (actual != null) {
            if (actual.transporte.getCodigo().equalsIgnoreCase(cod)) {
                anterior.siguiente = actual.siguiente;
                System.out.println("El Transporte: "+ cod+"\nSe elimino correctamente");
                return;
            }
            anterior  = actual;
            actual = actual.siguiente;

        }
        if(actual == null){
            System.out.println("No se encontro ningun Transporte");
        }
    }

    public void mostrarDisponibles(){
        actual = primero;
        if (actual == null) {
            System.out.println("La lista está vacía");
        }
        while (actual.siguiente != null) {
            if (actual.transporte.isDisponible()) {
                //mostrar
            }

            actual = actual.siguiente;
        }
    }
    public void mostrarMayorCarga(){
        double mayor= primero.transporte.getCapacidadToneladas();
        String codigo  = "";
        actual = primero;
        if (actual == null) {
            System.out.println("La lista esta vacia");
        }
        while (actual != null) {
            if (actual.transporte.getCapacidadToneladas()> mayor) {
                mayor = actual.transporte.getCapacidadToneladas();
                codigo = actual.transporte.getCodigo();
            }
        }
        System.out.println("El vehiculo con mayor capacidad es: ");
        buscarPorCodigo(codigo);
    }

    public int contarTransportes(){
        return 0;
    }
}
