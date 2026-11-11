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
        if(primero == null){
            System.out.println("La lista esta vacia");
            return;
        }
        while (actual.siguiente != null) {
                System.out.println("\n----------------------------------------------");
                System.out.println("Código:          " + actual.transporte.getCodigo());
                System.out.println("Marca:           " + actual.transporte.getMarca());
                System.out.println("Modelo:          " + actual.transporte.getModelo());
                System.out.println("Tipo:            " + actual.transporte.getTipo());
                System.out.println("Capacidad:       " + actual.transporte.getCapacidadToneladas() + " toneladas");
                System.out.println("Placas:          " + actual.transporte.getPlacas());
                System.out.println("Disponibilidad:  " + (actual.transporte.isDisponible() ? "Disponible" : "No disponible"));
                System.out.println("----------------------------------------------");
            actual = actual.siguiente;
        }
    }

    public void buscarPorCodigo(String cod){
        actual = primero;
        if(primero == null){
            System.out.println("La lista esta vacia");
            return;
        }
        while (actual != null) {
            if (actual.transporte.getCodigo().equalsIgnoreCase(cod)) {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println(  "               Buscar por codigo                     ");
        System.out.println(  "╚══════════════════════════════════════════════════╝");
                System.out.println("\n----------------------------------------------");
                System.out.println("Código:          " + actual.transporte.getCodigo());
                System.out.println("Marca:           " + actual.transporte.getMarca());
                System.out.println("Modelo:          " + actual.transporte.getModelo());
                System.out.println("Tipo:            " + actual.transporte.getTipo());
                System.out.println("Capacidad:       " + actual.transporte.getCapacidadToneladas() + " toneladas");
                System.out.println("Placas:          " + actual.transporte.getPlacas());
                System.out.println("Disponibilidad:  " + (actual.transporte.isDisponible() ? "Disponible" : "No disponible"));
                System.out.println("----------------------------------------------");
            }
            actual = actual.siguiente;
        }
    }

    public void modificarTransporte(String cod){
        actual = primero;
        if(primero == null){
            System.out.println("La lista esta vacia");
            return;
        }
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
        if(primero == null){
            System.out.println("La lista esta vacia");
            return;
        }
        while (actual.siguiente != null) {
            if (actual.transporte.isDisponible()) {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println(  "              Transportes disponibles                 ");
        System.out.println(  "╚══════════════════════════════════════════════════╝");
                System.out.println("\n----------------------------------------------");
                System.out.println("Código:          " + actual.transporte.getCodigo());
                System.out.println("Marca:           " + actual.transporte.getMarca());
                System.out.println("Modelo:          " + actual.transporte.getModelo());
                System.out.println("Tipo:            " + actual.transporte.getTipo());
                System.out.println("Capacidad:       " + actual.transporte.getCapacidadToneladas() + " toneladas");
                System.out.println("Placas:          " + actual.transporte.getPlacas());
                System.out.println("Disponibilidad:  " + (actual.transporte.isDisponible() ? "Disponible" : "No disponible"));
                System.out.println("----------------------------------------------");
            }

            actual = actual.siguiente;
        }
    }
    public void mostrarMayorCarga(){
        double mayor= primero.transporte.getCapacidadToneladas();
        String codigo  = "";
        actual = primero;

        if(primero == null){
            System.out.println("La lista esta vacia");
            return;
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
        int trans =0;
        actual= primero;
        if(primero == null){
            System.out.println("La lista esta vacia");
            return 0;
        }
        while (actual!= null) {
            trans++;
            actual = actual.siguiente;
        }
        return trans;
    }

    public void insertarAfterCodigo(String codigo, TransporteCarga trans){
        Nodo nuevo = new Nodo(trans);
        actual = primero;
        if(primero == null){
            System.out.println("La lista esta vacia");
            return;
        }
        while (actual != null) {
            if (actual.transporte.getCodigo().equals(codigo)) {
                nuevo.siguiente = actual.siguiente;
                actual.siguiente = nuevo;
                System.out.println("Se ha registrado correectamente el vehiculo despupes del codigo: "+ codigo);
                return;
            }
            actual = actual.siguiente;
        }
        if(actual == null){
            System.out.println("La lista esta vacia");
        }
        System.out.println("No se ha encontrado el codigo\nTransporte no insertado en la lista");
        
    }

    public void insertarBeforeCodigo(String codigo, TransporteCarga trans){
        Nodo nuevo = new Nodo(trans);
        actual = primero;
        if(primero == null){
            System.out.println("La lista esta vacia");
            return;
        }
        if(actual.siguiente.transporte.getCodigo().equals(codigo)){
            registrarTransporte(trans);
        }
        while (actual.siguiente != null) {
            if (actual.siguiente.transporte.getCodigo().equals(codigo)) {
                nuevo.siguiente = actual.siguiente;
                actual.siguiente = nuevo;
                System.out.println("Se ha registrado correectamente el vehiculo despupes del codigo: "+ codigo);
                return;
            }
            actual = actual.siguiente;
        }



    }

    public void ordenarPorCapacidad(){
        actual = primero;
        if(primero == null){
            System.out.println("La lista esta vacia");
            return;
        }
        boolean bandera = true;
        while (bandera) {
            if(primero.transporte.getCapacidadToneladas()> primero.siguiente.transporte.getCapacidadToneladas()){
                Nodo cambio = primero;
                primero = primero.siguiente;
                cambio.siguiente = primero.siguiente;
                primero.siguiente = cambio;
            }
            actual = primero.siguiente;
            Nodo anterior = primero;
            while (actual.siguiente != null) {
                if(actual.transporte.getCapacidadToneladas()> actual.siguiente.transporte.getCapacidadToneladas()){
                    Nodo cambio = actual;
                    anterior.siguiente = actual.siguiente;
                    actual.siguiente = actual.siguiente.siguiente;
                    anterior.siguiente.siguiente = cambio;
                    anterior = anterior.siguiente;

                }else{
                anterior = actual;
                actual = actual.siguiente;
                }

            }
            bandera = comprobacion();
        } 
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println(  "                  Lista ordenada                     ");
        System.out.println(  "╚══════════════════════════════════════════════════╝");
        mostrarTransportes();
    }

    public void transportesPesados(){
        actual = primero;

        if(primero == null){
            System.out.println("La lista esta vacia");
            return;
        }

        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("              TRANSPORTES PESADOS                  ");
        System.out.println("╚══════════════════════════════════════════════════╝");

        while (actual != null) {
            if(actual.transporte.getCapacidadToneladas() > 20){
                System.out.println("\n----------------------------------------------");
                System.out.println("Código:          " + actual.transporte.getCodigo());
                System.out.println("Marca:           " + actual.transporte.getMarca());
                System.out.println("Modelo:          " + actual.transporte.getModelo());
                System.out.println("Tipo:            " + actual.transporte.getTipo());
                System.out.println("Capacidad:       " + actual.transporte.getCapacidadToneladas() + " toneladas");
                System.out.println("Placas:          " + actual.transporte.getPlacas());
                System.out.println("Disponibilidad:  " + (actual.transporte.isDisponible() ? "Disponible" : "No disponible"));
                System.out.println("----------------------------------------------");
            }

            actual = actual.siguiente;
        }
    }


    public boolean comprobacion(){
        actual = primero;
        while (actual!= null) {
            if(actual.transporte.getCapacidadToneladas()> actual.siguiente.transporte.getCapacidadToneladas()){
                return false;
            }
        }
        return true;
    }
}
