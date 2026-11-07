package Unidad_2.Ejercicio_01_11_26;

import javax.swing.JOptionPane;

public class ListaDispositivos {
    Nodo primero;
    
    Nodo actual;
    public ListaDispositivos() {
        primero = null;
    }
    public String registrarDispositivo(DispositivoElectronico disp) {
        Nodo nuevo = new Nodo(disp);
        actual = primero;

        while (actual != null && actual.dispositivo.codigo != disp.codigo) {
            if (actual.siguiente == null) {
                actual.siguiente = nuevo;
                return "Dispositivo: " + disp.codigo + "\nGuardado con éxito";
            }

            actual = actual.siguiente;
        }

        if (actual == null) {
            primero = nuevo;
            return "Dispositivo: " + disp.codigo + "\nGuardado con éxito";
        }

        return "Error: Código repetido";
    }

    public void buscasDospositivos(int codigo ){
        actual=primero;
        while(actual !=null){
            if(actual.dispositivo.getCodigo() == codigo){
                System.out.println("Código: " + actual.dispositivo.getCodigo());
                System.out.println("Existencia: " + actual.dispositivo.getExistencia());
                System.out.println("Marca: " + actual.dispositivo.getMarca());
                System.out.println("Nombre: " + actual.dispositivo.getNombre());
                System.out.println("Precio: $" + actual.dispositivo.getPrecio());
                System.out.println("Tipo: " + actual.dispositivo.getTipo());
                System.out.println("-----------------------------");
            }
            actual = actual.siguiente;
        }

    }


    public void modificarDispositivo(int codigo){ 
        actual = primero;
        while(actual !=null){
            if(actual.dispositivo.getCodigo() == codigo){
                System.out.println("Código: " + actual.dispositivo.getCodigo());
                System.out.println("Nombre: " + actual.dispositivo.getNombre());
                System.out.println("Existencia: " + actual.dispositivo.getExistencia());
                System.out.println("Precio: $" + actual.dispositivo.getPrecio());
                String op = JOptionPane.showInputDialog("Qué desea actualizar\n1. Existencia\n2. Precio");
                switch (op) {
                    case "1":
                        int ex = Integer.parseInt(JOptionPane.showInputDialog("Ingrese las unidades existentes"+
                        "\nActualmente: "+actual.dispositivo.getExistencia()));
                        if (ex >= 0) {
                            actual.dispositivo.existencia = ex;    
                        }else{
                            JOptionPane.showMessageDialog(null, "No se pueden ingresar numeros negativos","Error",JOptionPane.ERROR_MESSAGE);
                        }
                        
                        break;
                    case "2":
                        double pr = Double.parseDouble(JOptionPane.showInputDialog("Ingres el nuevo precio"+
                            "\nActualmente: $"+actual.dispositivo.getPrecio()));
                        if (pr >= 0) {
                            actual.dispositivo.precio = pr;    
                        }else{
                            JOptionPane.showMessageDialog(null, "No se pueden ingresar numeros negativos","Error",JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    
                    default:
                        throw new AssertionError();
                }
            }
            actual = actual.siguiente; 

        }

    }



    public void eliminarAlFrente() {
        if (primero == null) {
            return;
        }

        primero = primero.siguiente;
    }

    public void eliminarAlFinal() {
        if (primero == null) {
            return;
        }

        if (primero.siguiente == null) {
            primero = null;
            return;
        }

        actual = primero;

        while (actual.siguiente.siguiente != null) {
            actual = actual.siguiente;
        }

        actual.siguiente = null;
    }


    public void eliminarPorCodigo(int codigo) {
        actual = primero;
        Nodo anterior = null;

        while (actual != null) {

            if (actual.dispositivo.getCodigo() == codigo) {

                if (actual == primero) {
                    eliminarAlFrente();
                }
                else if (actual.siguiente == null) {
                    eliminarAlFinal();
                }
                else {
                    anterior.siguiente = actual.siguiente;
                }

                System.out.println("Dispositivo eliminado.");
                return;
            }

            anterior = actual;
            actual = actual.siguiente;
        }

        System.out.println("No se encontró el código.");
    }

    public void mostrarTipo(String tipo){
        actual = primero;
        while (actual != null) { 
            if (actual.dispositivo.getTipo().equalsIgnoreCase(tipo)) {
                System.out.println("Código: " + actual.dispositivo.getCodigo());
                System.out.println("Existencia: " + actual.dispositivo.getExistencia());
                System.out.println("Marca: " + actual.dispositivo.getMarca());
                System.out.println("Nombre: " + actual.dispositivo.getNombre());
                System.out.println("Precio: $" + actual.dispositivo.getPrecio());
                System.out.println("Tipo: " + actual.dispositivo.getTipo());
                System.out.println("-----------------------------");
            }
            actual = actual.siguiente;
        }
    }

    public double calcularValor(){
        actual = primero;
        double valor = 0;
        while (actual != null) { 
            valor += (actual.dispositivo.getExistencia() * actual.dispositivo.getPrecio());
            actual= actual.siguiente;
        }
        return valor;
    }

    public void dispositivosEscasos(){
        actual = primero;
        while (actual != null) { 
            if (actual.dispositivo.getExistencia()<5) {
                System.out.println("Código: " + actual.dispositivo.getCodigo());
                System.out.println("Existencia: " + actual.dispositivo.getExistencia());
                System.out.println("Nombre: " + actual.dispositivo.getNombre());
                System.out.println("Tipo: " + actual.dispositivo.getTipo());
                System.out.println("-----------------------------");
            }
            actual= actual.siguiente;
        }  
    }

    public void dispositivoBarato() {
        if (primero == null) {
            System.out.println("No hay dispositivos registrados.");
            return;
        }

        actual = primero;
        double barato = primero.dispositivo.getPrecio();
        int codigo = primero.dispositivo.getCodigo();

        while (actual != null) {
            if (barato > actual.dispositivo.getPrecio()) {
                barato = actual.dispositivo.getPrecio();
                codigo = actual.dispositivo.getCodigo();
            }

            actual = actual.siguiente;
        }

        buscasDospositivos(codigo);
    }

    public String mostrarCodigos(){
        String reporte = "--------------------------\n"+
                         "          REPORTE         \n"+
                         "--------------------------\n";

        actual = primero;
        if (primero == null) {
            reporte += "\n\n No hay dispositivos guardados.";
        }
        while (actual != null) { 
            reporte += "Código: " + actual.dispositivo.getCodigo() + "\n";
            reporte += "Existencia: " + actual.dispositivo.getExistencia() + "\n";
            reporte += "Marca: " + actual.dispositivo.getMarca() + "\n";
            reporte += "Nombre: " + actual.dispositivo.getNombre() + "\n";
            reporte += "Precio: $" + actual.dispositivo.getPrecio() + "\n";
            reporte += "Tipo: " + actual.dispositivo.getTipo() + "\n";
            reporte += "-----------------------------\n";

            actual = actual.siguiente;
        }
        return reporte;
    }
}
