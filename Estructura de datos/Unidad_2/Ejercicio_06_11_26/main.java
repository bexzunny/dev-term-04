
package Unidad_2.Ejercicio_06_11_26;

import javax.swing.JOptionPane;

public class main {

    public static void main(String[] args) {

        ListaTransportes lista = new ListaTransportes();
        String opcion = "0";

        while (!opcion.equals("13")) {

            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("             GESTIÓN DE TRANSPORTES          ");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("  1. Registrar transporte");
            System.out.println("  2. Mostrar transportes");
            System.out.println("  3. Buscar por código");
            System.out.println("  4. Modificar transporte");
            System.out.println("  5. Eliminar por código");
            System.out.println("  6. Mostrar disponibles");
            System.out.println("  7. Mostrar transporte con mayor capacidad");
            System.out.println("  8. Contar transportes");
            System.out.println("  9. Insertar después de un código");
            System.out.println(" 10. Insertar antes de un código");
            System.out.println(" 11. Ordenar por capacidad");
            System.out.println(" 12. Mostrar transportes pesados");
            System.out.println(" 13. Salir");
            System.out.println("╚══════════════════════════════════════════╝");

            opcion = JOptionPane.showInputDialog("Selecciona una opción:");

            if (opcion == null) {
                opcion = "13";
                continue;
            }

            switch (opcion) {

                case "1":
                    TransporteCarga nuevo = capturarTransporte();
                    lista.registrarTransporte(nuevo);
                    System.out.println("Transporte registrado correctamente");
                    break;

                case "2":
                    lista.mostrarTransportes();
                    break;

                case "3":
                    String codigoBuscar = JOptionPane.showInputDialog("Ingrese el código a buscar:");
                    lista.buscarPorCodigo(codigoBuscar);
                    break;

                case "4":
                    String codigoModificar = JOptionPane.showInputDialog("Ingrese el código del transporte:");
                    lista.modificarTransporte(codigoModificar);
                    break;

                case "5":
                    String codigoEliminar = JOptionPane.showInputDialog("Ingrese el código a eliminar:");
                    lista.eliminarPorCodigo(codigoEliminar);
                    break;

                case "6":
                    lista.mostrarDisponibles();
                    break;

                case "7":
                    lista.mostrarMayorCarga();
                    break;

                case "8":
                    System.out.println("Total de transportes registrados: " + lista.contarTransportes());
                    break;

                case "9":
                    String codigoAfter = JOptionPane.showInputDialog("Ingrese el código de referencia:");
                    TransporteCarga transporteAfter = capturarTransporte();
                    lista.insertarAfterCodigo(codigoAfter, transporteAfter);
                    break;

                case "10":
                    String codigoBefore = JOptionPane.showInputDialog("Ingrese el código de referencia:");
                    TransporteCarga transporteBefore = capturarTransporte();
                    lista.insertarBeforeCodigo(codigoBefore, transporteBefore);
                    break;

                case "11":
                    lista.ordenarPorCapacidad();
                    break;

                case "12":
                    lista.transportesPesados();
                    break;

                case "13":
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    public static TransporteCarga capturarTransporte() {

        String codigo = JOptionPane.showInputDialog("Ingrese el código:");
        String marca = JOptionPane.showInputDialog("Ingrese la marca:");
        String modelo = JOptionPane.showInputDialog("Ingrese el modelo:");
        String tipo = JOptionPane.showInputDialog("Ingrese el tipo:");
        double capacidad = Double.parseDouble(
            JOptionPane.showInputDialog("Ingrese la capacidad en toneladas:")
        );
        String placas = JOptionPane.showInputDialog("Ingrese las placas:");

        int disponibilidad = Integer.parseInt(
            JOptionPane.showInputDialog("Disponibilidad:\n1. Disponible\n2. No disponible")
        );

        boolean disponible = disponibilidad == 1;

        return new TransporteCarga(
            capacidad, codigo, disponible, marca, modelo, placas, tipo
        );
    }
}
