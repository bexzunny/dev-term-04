package Unidad_2.clase_26_10_26;

import javax.swing.JOptionPane;

public class ListaEmpleados {
    Nodo primero;
    Nodo actual;

    public ListaEmpleados() {
        primero = null;
    }

    public void insertarAlFinal(Empleado trabajador) {
        //El nuevo nodo se convierte en el úlitmo
        Nodo nuevo = new Nodo(trabajador);
        if (primero == null) {
            primero = nuevo;
            return;
        }
        actual = primero;
        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }
        actual.siguiente = nuevo;
    }

    public void insertarAlFrente(Empleado trabajador) {
        //El nuevo nodo se convierte en el inicio
        Nodo nuevo = new Nodo(trabajador);
        nuevo.siguiente = primero;
        primero = nuevo;
    }

    public void insertarPorPosicion(int pos, Empleado trabajador) {
        int posicion = 1;
        Nodo nuevo = new Nodo(trabajador);
        if (pos < 1) {
            System.out.println("Posición inválida");
            return;
        }
        if (pos == 1) {
            insertarAlFrente(trabajador);
            return;
        }
        actual = primero;
        while (actual != null && posicion < pos - 1) {
            actual = actual.siguiente;
            posicion++;
        }
        if (actual == null) {
            System.out.println("Pisición fuera de rango");
            return;
        }
        nuevo.siguiente = actual.siguiente;
        actual.siguiente = nuevo;
        System.out.println("Nodo insertado correctamente");
    }

    /*public void insertarPorValor(int val, int num){
        actual = primero;
        while(actual != null){
            if(actual.valor == val){
                actual.valor = num;
            }
            actual = actual.siguiente; //Este se requiere para avanzar al siguiente nodo y no sea infinito el bucle
        }        
        
    }*/
    public void insertarPorValor(int valor, Empleado trabajador) {
        actual = primero;
        Nodo nuevo = new Nodo(trabajador);
        while (actual != null && actual.dato.idempleado != valor) {
            actual = actual.siguiente;
        }
        if (actual == null) {
            System.out.println("Valor no encontrado");
            return;
        }
        nuevo.siguiente = actual.siguiente;
        actual.siguiente = nuevo;

    }

    public void eliminarAlFrente() {
        if (primero == null) {
            System.out.println("Lista vacía");
        } else {
            primero = primero.siguiente;
        }
    }

    public void eliminarAlFinal() {
        if (primero == null) {
            System.out.println("Lista vacía");
        }
        if (primero.siguiente == null) {
            primero = null;
        } else {
            actual = primero;
            while (actual.siguiente.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = null;
        }
    }

    public void eliminarPorPosicion(int pos) {
        int posicion = 1;
        if (pos < 1 || primero == null) {
            System.out.println("La acción no se puede realizar");
        }
        if (pos == 1) {
            eliminarAlFrente();
        }

        actual = primero;
        while (actual.siguiente != null && posicion < pos - 1) {
            actual = actual.siguiente;
            posicion++;
        }
        if (actual.siguiente == null) {
            System.out.println("Final de la lista");
        }
        actual.siguiente = actual.siguiente.siguiente;
    }

    public int buscar(int id) {
        int posicion = 1;
        if (primero == null) {
            System.out.println("La lista está vacía");
        } else {
            actual = primero;
            while (actual.siguiente != null) {
                if (actual.dato.idempleado == id) {
                    System.out.println("El valor de " + id + "fue encontrado en la posición " + posicion);
                }
                posicion++;
                actual = actual.siguiente;
            }
        }
        return id;
    }

    public void eliminarPorValor(int valor) {
        if (primero == null) {
            System.out.println("La lista está vacía");
            return;
        }
        if (primero.dato.idempleado == valor) {
            primero = primero.siguiente;
            System.out.println("Nodo eliminado correctamente");
            return;
        }
        actual = primero;
        while (actual.siguiente != null && actual.siguiente.dato.idempleado != valor) {
            actual = actual.siguiente;
        }
        if (actual.siguiente == null) {
            System.out.println("Valor no encontrado");
            return;
        }
        actual.siguiente = actual.siguiente.siguiente;
        System.out.println("Nodo eliminado correctamente");
    }

    public double sumarSueldos() {
        int suma = 0;
        if (primero == null) {
            System.out.println("La lista está vacía");
        } else {
            actual = primero;
            while (actual.siguiente != null) {
                suma += actual.siguiente.dato.sueldoneto;
                actual = actual.siguiente;
            }
            suma += actual.dato.sueldoneto;
        }
        return suma;
    }
    
    //Requerimos contabilizar todos nuestros elementos de la lista para sacar el promedio
    public int contarNodos() {
        int contador = 0;
        actual = primero;
        while (actual != null) {
            contador++;
            actual = actual.siguiente;
        }
        return contador;
    }

    public double calcularPromedio() {
        if (contarNodos() == 0) {
            System.out.println("La lista está vacía");
        }         
        return sumarSueldos() / contarNodos();
    }

    public void modificar(int idActual) {
        actual = primero;
        int op;
        while (actual.siguiente != null && actual.dato.idempleado != idActual) {
            actual = actual.siguiente;
        }
        if (actual == null) {
            System.out.println("Valor no encontrado");
            return;
        }
        op = Integer.parseInt(JOptionPane.showInputDialog("""
                                                          1. Nombre
                                                          2. Departamento
                                                          3. Sueldo neto
                                                          4. Antigüedad"""));
        if (op == 1) {
            String nom;
            nom = JOptionPane.showInputDialog("Teclea el nuevo nombre");
            actual.dato.nombre = nom;
        }
        if (op == 2) {
            String depa;
            depa = JOptionPane.showInputDialog("Teclea el nuevo departamento");
            actual.dato.departamento = depa;
        }
        if (op == 3) {
            double sueldo;
            sueldo = Double.parseDouble(JOptionPane.showInputDialog("Teclea el nuevo sueldo"));
            actual.dato.sueldoneto = sueldo;
        }
        if (op == 4) {
            int antig;
            antig = Integer.parseInt(JOptionPane.showInputDialog("Teclea la nueva antigüedad"));
            actual.dato.antiguedad = antig;
        }
        System.out.println("Valor modificado correctamente");
        mostrar();
    }
    
    public void mostrarEmpleadoConMayorSueldo(){
        if (primero == null) {
            System.out.println("La lista está vacía");
            return;
        }
        actual = primero.siguiente;
        while (actual != null) {
            if (actual.dato.sueldoneto > primero.dato.sueldoneto) {
                primero = actual;
            }
            actual = actual.siguiente;
        }
        System.out.println("El empleado con mayor sueldo neto es:" + primero.dato.toString());
    }

    public void mostrar() {
        if (primero == null) {
            System.out.println("La lista está vacía");
        } else {
            actual = primero;
            while (actual != null) {
                System.out.print(actual.dato.toString() + " ---> \n");
                actual = actual.siguiente;
            }
            System.out.print("null");
            System.out.println("");
        }
    }

}