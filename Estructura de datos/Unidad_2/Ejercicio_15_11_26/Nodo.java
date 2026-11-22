
public class Nodo {

    Atraccion atraccion;
    Nodo anterior;
    Nodo siguiente;

    public Nodo(Atraccion atraccion) {
        this.atraccion = atraccion;
        this.anterior = null;
        this.siguiente = null;
    }

}
