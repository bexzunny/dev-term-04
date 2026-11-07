package Unidad_2.Clase_12_10_16;

public class ListaSimple {
    Nodo primero;
    Nodo actual;
    Nodo cambio;
    public ListaSimple() {
        primero = null;
    }

    public void insertarInicio(int n){
        Nodo nuevo = new Nodo(n);
        nuevo.siguiente = primero;
        primero = nuevo;

        
    }



    public void insertarFinal(int n){
        Nodo nuevo = new Nodo(n);
        if (primero == null){
            primero = nuevo;
            return;
        }
        actual = primero;
        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }
        actual.siguiente = nuevo;
    }

    public void eliminarFrente(){
        if(primero == null){
            System.out.println("La lista esta vacia");
        }else{
            primero=primero.siguiente;
        }
    }

    public void eliminarFinal(){
        if(primero == null){
            System.out.println("La lista esta vacia");
        }else{
            if(primero.siguiente == null){
                primero = null;
            }else{
                actual = primero;
                while (actual.siguiente.siguiente!=null) {
                    actual = actual.siguiente;
                }
                actual = null;
            }
        }
    }
    public int buscar(int dato){
        actual = primero;
        int posicion = 1;
        while (actual != null) {
            if(actual.valor==dato){
                return posicion;
            }else{
                posicion++;
            }
        }
        return -1;
    }

    public void sumarLista(){
        int suma=0;
        actual = primero;
        while (actual!=null) {
            suma+=actual.valor;
            actual=actual.siguiente;
        }
        System.out.println("El resultado de la suma de la lista es; "+ suma);
    }
    public void multiplicarLista(){
        int multi=0;
        actual = primero;
        while (actual!=null) {
            multi*=actual.valor;
            actual=actual.siguiente;
        }
        System.out.println("El resultado de la multiplicacion es; "+ multi);
    }

    public void numerosPares(){
        actual = primero;
        while (actual!=null) {
            if(actual.valor % 2 == 0){
                System.out.println(actual.valor);
            }
        }
        System.out.println("Fin.");
    }

    public void numerosPrimos(){
        actual = primero;
        while (actual!=null) {
            if(esPrimo(actual.valor)){
                System.out.println(actual.valor);
            }
        }
        System.out.println("Fin.");
    }

    public boolean esPrimo(int numero) {
    if (numero < 2) return false;
    if (numero == 2) return true;
    if (numero % 2 == 0) return false;

    for (int i = 3; i <= numero / i; i += 2) {
        if (numero % i == 0) {
            return false;
        }
    }

    return true;
}
    public void insertarPorPosicion(int valor, int posicion) {

        cambio = new Nodo(valor);

        if (posicion == 0) {
            cambio.siguiente = primero;
            primero = cambio;
            return;
        }

        actual = primero;

        for (int i = 0; i < posicion - 1; i++) {
            if (actual == null) {
                System.out.println("Porisión inválida");
                return;
            }

            actual = actual.siguiente;
        }

        if (actual == null) {
                System.out.println("Porisión inválida");
                return;    
        }

        cambio.siguiente = actual.siguiente;
        actual.siguiente = cambio;
    }
    



    public void eliminarPorValor(int valor){
        if(primero == null){
            System.out.println("La lista está vacía");
        }
        if (primero.valor == valor) {
            primero = primero.siguiente;
            System.out.println("Nodo eliminado correctamente");
            return;
        }
        actual = primero;
        while (actual.siguiente != null
            && actual.siguiente.valor  !=valor) { 
            actual = actual.siguiente;
        }
        if (actual.siguiente == null) {
            System.out.println("Valor no encontrado");
            return;
        }
        actual.siguiente = actual.siguiente.siguiente;
        System.out.println("Nodo eliminado correctamente");
    }



public void modificarPorValor(int valorActual, int nuevoValor){
    actual = primero;
    while (actual != null && actual.valor != valorActual) { 
        actual = actual.siguiente; 
    }
    if (actual == null) {
        System.out.println("Valir no encontrado");
    }
    actual.valor = nuevoValor;
    System.out.println("Valor modificado correctamente");
    mostrar();
}

public void insertarPorValor(int valor, int num){
    actual = primero;
    Nodo nuevo = new Nodo(num);
    while (actual != null && actual.valor != valor) { 
        actual = actual.siguiente;
    }
    if(actual == null){
        System.out.println("Valor no encontrado");
    }
    nuevo.siguiente = actual.siguiente;
    actual.siguiente = nuevo;
}

    public void mostrar(){
        if(primero == null){
            System.out.println("╔══════════════════════╗");
            System.out.println("║      Lista vacía     ║");
            System.out.println("╚══════════════════════╝");
            return;
        }

        System.out.println("Lista enlazada simple:");
        System.out.println("   primero");
        System.out.println("      │");
        System.out.println("      ▼");

        // Dibuja la parte superior de cada nodo
        Nodo temp = primero;
        while (temp != null) {
            System.out.print("┌──────────┐");
            if (temp.siguiente != null) {
                System.out.print("   ");
            }
            temp = temp.siguiente;
        }
        System.out.println();

        // Dibuja el valor de cada nodo con la flecha hacia el siguiente
        temp = primero;
        while (temp != null) {
            System.out.printf("│  %6d  │", temp.valor);
            if (temp.siguiente != null) {
                System.out.print(" → ");
            } else {
                System.out.print(" → null");
            }
            temp = temp.siguiente;
        }
        System.out.println();

        // Dibuja la parte inferior de cada nodo
        temp = primero;
        while (temp != null) {
            System.out.print("└──────────┘");
            if (temp.siguiente != null) {
                System.out.print("   ");
            }
            temp = temp.siguiente;
        }
        System.out.println();
    }
}
