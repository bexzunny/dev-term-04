package Unidad_2.Clase_04_11_26;

public class ListaDoble {
    Nodo primero;
    Nodo ultimo;
    
    public void insertaralFrente(int num){
        Nodo nuevo = new Nodo(num);
        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        }else{
            nuevo.siguiente = primero;
            primero.anterior = nuevo;
            primero = nuevo;
        }
    }
    public void insertarAlFinal(int num){
        Nodo nuevo = new Nodo(num);
        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        }else{
            ultimo.siguiente = nuevo;
            nuevo.anterior = ultimo;
            ultimo = nuevo;
        }

    }

    public void insertarPorPosicion(int pos, int num){
        Nodo nuevo = new Nodo(num);
        Nodo actual = primero;
        int posicion = 0;
        if(pos < 0){
            System.out.println("Posición invalida");
        }
        if (pos == 0) {
            if (primero == null) {
                primero = nuevo;
                ultimo = nuevo;
            }else{
                nuevo.siguiente = primero;
                primero.anterior = nuevo;
                primero = nuevo;
            }
        }
        while (actual != null && posicion < pos-1) {
            posicion++;
            actual = actual.siguiente;
        }
        if (actual == null) {
            System.out.println("Posición fuera de rango");
            return;
        }
        if (actual == ultimo) {
            actual.siguiente = nuevo;
            nuevo.anterior = actual;
            ultimo = nuevo;
        }else{
            nuevo.siguiente = actual.siguiente;
            nuevo.anterior = actual;
            actual.siguiente.anterior = nuevo;
            actual.siguiente = nuevo;
        }

    }

    public void eliminaralFrente(){
        if (primero == null) {
            System.out.println("La lista esta vacia");
        }
        if(primero == ultimo){
            primero = null;
            ultimo = null;
        }else{
            primero = primero.siguiente;
            primero.anterior = null;
        }
    }

    public void eliminaralFinal(){
        if (primero == null) {
            System.out.println("La lista esta vacia");
        }
        if(primero == ultimo){
            primero = null;
            ultimo = null;
        }else{
            ultimo = ultimo.anterior;
            ultimo.siguiente = null;
        }
    }

    public void eliminarporPosicion(int pos) {
        Nodo actual = primero;
        int posicion = 0;

        if (primero == null) {
            System.out.println("La lista está vacía");
            return;
        }

        if (pos < 0) {
            System.out.println("Posición inválida");
            return;
        }

        if (pos == 0) {
            if (primero == ultimo) {
                primero = null;
                ultimo = null;
            } else {
                primero = primero.siguiente;
                primero.anterior = null;
            }
            return;
        }

        while (actual != null && posicion < pos) {
            actual = actual.siguiente;
            posicion++;
        }

        if (actual == null) {
            System.out.println("Posición fuera de rango");
            return;
        }

        if (actual == ultimo) {
            ultimo = actual.anterior;
            ultimo.siguiente = null;
        } else {
            actual.anterior.siguiente = actual.siguiente;
            actual.siguiente.anterior = actual.anterior;
        }
    }
// insertar por valor
    public void insertarPorValor(int val, int num){
        Nodo nuevo = new Nodo(num);
        Nodo actual = primero;
        if(val < 0){
            System.out.println("Posición invalida");
        }

        while (actual != null && val != actual.dato) {
            actual = actual.siguiente;
        }
        if (actual== primero) {
            insertaralFrente(num);
            return ;
        }
        if (actual == null) {
            System.out.println("Posición fuera de rango");
            return;
        }
        if (actual == ultimo) {
            actual.siguiente = nuevo;
            nuevo.anterior = actual;
            ultimo = nuevo;
        }else{
            nuevo.siguiente = actual.siguiente;
            nuevo.anterior = actual;
            actual.siguiente.anterior = nuevo;
            actual.siguiente = nuevo;
        }

    }


//eliminar por valor

    public void eliminarPorValor(int val){
        Nodo actual =  primero;
        if (primero == null) {
            System.out.println("La lista esta vacia");
        }

        while (actual != null && val != actual.dato) {
            actual = actual.siguiente;
        }
        if (actual== primero) {
            eliminaralFrente();
        }
        if (actual==null) {
            System.out.println("Lista vacia");
            return ;
        }
        if (actual == ultimo) {
            ultimo = actual.anterior;
            ultimo.siguiente = null;
        } else {
            actual.anterior.siguiente = actual.siguiente;
            actual.siguiente.anterior = actual.anterior;
        }
    }





    public void mostrarIzqDer(){
        Nodo actual = primero;
        if (primero == null) {
            System.out.println("La lista está vacia ");
        }else{
            System.out.println("null <->");
            while (actual!=null) { 
                System.out.println(actual.dato+"<-->");
                actual= actual.siguiente;
            }
            System.out.println("null");
            System.out.println("");
        }
    }

    public void sumarLista(){
        int suma=0;
        Nodo actual = primero;
        if (primero== null) {
            System.out.println("Lista vacía");
        }else{
            while (actual != null) { 
                suma += actual.dato;
                actual = actual.siguiente;
            }
            System.out.println("La suma de la lista es: "+suma);
        }
    }
    //producto
    
        public void multiplicarLista(){
        int multi=0;
        Nodo actual = primero;
        if (primero== null) {
            System.out.println("Lista vacía");
        }else{
            while (actual != null) { 
                multi *= actual.dato;
                actual = actual.siguiente;
            }
            System.out.println("El producto de la lista es: "+multi);
        }
    }
    //impares
        public void mostrarImpares(){
        int multi=0;
        Nodo actual = primero;
        if (primero== null) {
            System.out.println("Lista vacía");
        }else{
            while (actual != null) { 
                multi *= actual.dato;
                actual = actual.siguiente;
            }
            System.out.println("El producto de la lista es: "+multi);
        }
    }



        public void mostrarDerIzq(){
        Nodo actual = ultimo;
        if (primero == null) {
            System.out.println("La lista está vacia ");
        }else{
            System.out.println("null <->");
            while (actual!=null) { 
                System.out.println(actual.dato+"<-->");
                actual= actual.anterior;
            }
            System.out.println("null");
            System.out.println("");
        }
    }
}
