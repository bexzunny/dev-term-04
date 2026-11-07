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
