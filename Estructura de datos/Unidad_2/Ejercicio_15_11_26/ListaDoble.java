import javax.swing.JOptionPane;

public class ListaDoble {
    Nodo primero;
    Nodo ultimo;
    public ListaDoble(){
        primero = null;
        ultimo =  null;
    }
    public void insertarInicio(Atraccion atr){
        if (verificarCodigo(atr.getCodigo())) {
            System.out.println("Error\n Código repetido");
            return;
        }
        Nodo nuevo = new Nodo(atr);
        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        }else{
            nuevo.siguiente = primero;
            primero.anterior = nuevo;
            primero = nuevo;
        }
    }
    public void insertarFinal(Atraccion atr){
        if (verificarCodigo(atr.getCodigo())) {
            System.out.println("Error\n Código repetido");
            return;
        }
        Nodo nuevo = new Nodo(atr);
        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        }else{
            ultimo.siguiente = nuevo;
            nuevo.anterior = ultimo;
            ultimo = nuevo;
        }
    }
    public void insertarPosicion(int pos, Atraccion atr) {
        if (verificarCodigo(atr.getCodigo())) {
            System.out.println("Error\n Código repetido");
            return;
        }
        if (pos < 0) {
            System.out.println("Posición inválida.");
            return;
        }
        if (pos == 0) {
            insertarInicio(atr);
            return;
        }
        Nodo actual = primero;
        int posicion = 0;
        while (actual != null && posicion < pos) {
            actual = actual.siguiente;
            posicion++;
        }
        if (actual == null && posicion != pos) {
            System.out.println("Índice fuera de rango de la lista.");
            return;
        }
        if (actual == null) {
            insertarFinal(atr);
            return;
        }

        Nodo nuevo = new Nodo(atr);

        nuevo.siguiente = actual;
        nuevo.anterior = actual.anterior;
        actual.anterior.siguiente = nuevo;
        actual.anterior = nuevo;
    }

    public boolean verificarCodigo(String cod){
        Nodo actual = primero;
        boolean hayCopia = false;
        while (actual != null) {
            if (actual.atraccion.getCodigo().equalsIgnoreCase(cod)) {
                hayCopia = true;
                return hayCopia;
            }
            actual = actual.siguiente;
        }
        return hayCopia;
    }



    public void mostrarAdelante() {

        if (primero == null) {
            System.out.println("La lista está vacía.");
            return;
        }

        Nodo actual = primero;

        while (actual != null) {
            actual.atraccion.mostrarDatos();
            actual = actual.siguiente;
        }
    }

    public void mostrarAtras() {

        if (ultimo == null) {
            System.out.println("La lista está vacía.");
            return;
        }

        Nodo actual = ultimo;

        while (actual != null) {
            actual.atraccion.mostrarDatos();
            actual = actual.anterior;
        }
    }
    public void buscarCodigo(String cod) {
        if (primero == null) {
            System.out.println("La lista está vacía.");
            return;
        }

        Nodo actual = primero;
        while (actual != null && !actual.atraccion.getCodigo().equalsIgnoreCase(cod)) {
            actual = actual.siguiente;
        }
        if (actual == null) {
            System.out.println("Código: " + cod + "\tno encontrado.");
            return;
        }
        actual.atraccion.mostrarDatos();
    }


    public void modificarAtraccion(String cod) {
        if (primero == null) {
            JOptionPane.showMessageDialog(null, "La lista está vacía");
            return;
        }

        Nodo actual = primero;
        while (actual != null && !actual.atraccion.getCodigo().equalsIgnoreCase(cod)) {
            actual = actual.siguiente;
        }

        if (actual == null) {
            JOptionPane.showMessageDialog(null, "Código: " + cod + "\tno encontrado.");
            return;
        }

        String opcion = JOptionPane.showInputDialog(
                "¿Qué deseas modificar?\n"
                + "1. Costo\n"
                + "2. Estado"
        );
        if (opcion == null) {
            return;
        }
        switch (opcion) {
            case "1":
                String costo = JOptionPane.showInputDialog("Ingresa el nuevo costo:");
                if (costo == null) {
                    return;
                }
                try {
                    actual.atraccion.setCosto(Double.parseDouble(costo));
                    JOptionPane.showMessageDialog(null, "Costo actualizado correctamente.");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Ingresa un costo válido.");
                }
                break;

            case "2":
                String estado = JOptionPane.showInputDialog(
                        "Ingresa el nuevo estado:\n"
                        + "Disponible\n"
                        + "Mantenimiento\n"
                        + "Fuera de servicio"
                );
                if (estado == null) {
                    return;
                }
                actual.atraccion.setEstado(estado);
                JOptionPane.showMessageDialog(null, "Estado actualizado correctamente.");
                break;

            default:
                JOptionPane.showMessageDialog(null, "Opción inválida.");
                break;
        }

    }
    public void eliminarPorCodigo(String cod) {
        if (primero == null) {
            System.out.println("La lista está vacía.");
            return;
        }

        Nodo actual = primero;
        while (actual != null && !actual.atraccion.getCodigo().equalsIgnoreCase(cod)) {
            actual = actual.siguiente;
        }

        if (actual == null) {
            System.out.println("Código: " + cod + "\tno encontrado.");
            return;
        }
        if (primero == ultimo) {
            primero = null;
            ultimo = null;
            return;
        }

        if (actual == primero) {
            primero = primero.siguiente;
            primero.anterior = null;
            return;
        }
        if (actual == ultimo) {
            ultimo = ultimo.anterior;
            ultimo.siguiente = null;
            return;
        }

        actual.anterior.siguiente = actual.siguiente;
        actual.siguiente.anterior = actual.anterior;
    }



    public void mostrarPorTipo(String tip) {

        if (primero == null) {
            System.out.println("La lista está vacía.");
            return;
        }

        Nodo actual = primero;
        boolean encontrado = false;

        while (actual != null) {
            if (actual.atraccion.getTipo().equalsIgnoreCase(tip)) {
                actual.atraccion.mostrarDatos();
                encontrado = true;
            }
            actual = actual.siguiente;
        }
        if (!encontrado) {
            System.out.println("No existen atracciones de ese tipo.");
            return;
        }
    }
    
    public void mostrarMayorPrecio() {
        if (primero == null) {
            System.out.println("La lista está vacía.");
            return;
        }

        Nodo actual = primero;
        Nodo mayor = primero;
        while (actual != null) {
            if (actual.atraccion.getCosto() > mayor.atraccion.getCosto()) {
                mayor = actual;
            }
            actual = actual.siguiente;
        }
        mayor.atraccion.mostrarDatos();
    }

    public double calcularIngresos() {

        if (primero == null) {
            System.out.println("La lista está vacía.");
            return 0;
        }

        Nodo actual = primero;
        double ingresos = 0;
        while (actual != null) {
            ingresos += (actual.atraccion.getCosto() * actual.atraccion.getCapacidad());
            actual = actual.siguiente;
        }

        return ingresos;
    }

    public int contarAtracciones() {

        if (primero == null) {
            System.out.println("La lista está vacía.");
            return 0;
        }

        Nodo actual = primero;
        int atracciones = 0;
        while (actual != null) {
            atracciones++;
            actual = actual.siguiente;
        }
        return atracciones;
    }

    public void ordenarPorCosto() {
        if (primero == null) {
            System.out.println("La lista está vacía.");
            return;
        }

        boolean cambio;

        do {
            cambio = false;
            Nodo actual = primero;
            while (actual != null && actual.siguiente != null) {
                if (actual.atraccion.getCosto() < actual.siguiente.atraccion.getCosto()) {
                    Nodo siguiente = actual.siguiente;
                    Nodo anterior = actual.anterior;
                    Nodo despues = siguiente.siguiente;

                    if (anterior != null) {
                        anterior.siguiente = siguiente;
                    } else {
                        primero = siguiente;
                    }

                    if (despues != null) {
                        despues.anterior = actual;
                    } else {
                        ultimo = actual;
                    }

                    siguiente.anterior = anterior;
                    siguiente.siguiente = actual;

                    actual.anterior = siguiente;
                    actual.siguiente = despues;

                    cambio = true;

                } else {
                    actual = actual.siguiente;
                }
            }

        } while (cambio);

        System.out.println("Atracciones ordenadas por costo de mayor a menor.");
    }



    public boolean verificar(){
        Nodo actual = primero;
        while (actual != null) {
            if (actual.atraccion.getCosto()< actual.siguiente.atraccion.getCosto()) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }
    public void atraccionesMenores() {
        if (primero == null) {
            System.out.println("La lista está vacía.");
            return;
        }

        Nodo actual = primero;
        boolean encontrado = false;
        while (actual != null) {
            if (actual.atraccion.getEdadMinima() < 18) {
                actual.atraccion.mostrarDatos();
                encontrado = true;
            }
            actual = actual.siguiente;
        }

        if (!encontrado) {
            System.out.println("No hay atracciones aptas para menores de 18 años.");
            return;
        }
    }




    public void insertarFrenteCodigo(String cod, Atraccion atr) {
        if (primero == null) {
            System.out.println("La lista está vacía.");
            return;
        }

        Nodo actual = primero;
        while (actual != null && !actual.atraccion.getCodigo().equalsIgnoreCase(cod)) {
            actual = actual.siguiente;
        }
        if (actual == null) {
            System.out.println("Código: " + cod + "\tno encontrado.");
            return;
        }
        if (actual == ultimo) {
            insertarFinal(atr);
            return;
        }
        Nodo nuevo = new Nodo(atr);

        nuevo.siguiente = actual.siguiente;
        nuevo.anterior = actual;
        actual.siguiente.anterior = nuevo;
        actual.siguiente = nuevo;

    }
    public void insertarDetrasCodigo(String cod, Atraccion atr) {
        if (primero == null) {
            System.out.println("La lista está vacía.");
            return;
        }

        Nodo actual = primero;
        while (actual != null && !actual.atraccion.getCodigo().equalsIgnoreCase(cod)) {
            actual = actual.siguiente;
        }
        if (actual == null) {
            System.out.println("Código: " + cod + "\tno encontrado.");
            return;
        }
        if (actual == primero) {
            insertarInicio(atr);
            return;
        }
        Nodo nuevo = new Nodo(atr);

        nuevo.siguiente = actual;
        nuevo.anterior = actual.anterior;
        actual.anterior.siguiente = nuevo;
        actual.anterior = nuevo;
    }

    public void eliminarFueraServicio() {
        if (primero == null) {
            System.out.println("La lista está vacía.");
            return;
        }

        Nodo actual = primero;
        int eliminadas = 0;
        while (actual != null) {
            Nodo siguiente = actual.siguiente;
            if (actual.atraccion.getEstado().equalsIgnoreCase("Fuera de servicio")) {
                eliminarPorCodigo(actual.atraccion.getCodigo());
                eliminadas++;
            }
            actual = siguiente;
        }
        if (eliminadas == 0) {
            System.out.println("No hay atracciones fuera de servicio.");
            return;
        }

        System.out.println("Atracciones eliminadas: " + eliminadas);
    }
}
