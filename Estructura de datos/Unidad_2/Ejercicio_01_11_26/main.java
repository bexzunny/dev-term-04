
package Unidad_2.Ejercicio_01_11_26;

import javax.swing.JOptionPane;

public class main {
    public static void main(String[] args) {

        int opcion, codigo, existencia;
        String nombre, marca, tipo, op;
        double precio;

        ListaDispositivos listita = new ListaDispositivos();

        do {
            op = JOptionPane.showInputDialog(
                "SISTEMA DE INVENTARIO DE DISPOSITIVOS" +
                "\n\n1. Registrar dispositivo" +
                "\n2. Buscar dispositivo por código" +
                "\n3. Modificar dispositivo" +
                "\n4. Eliminar dispositivo por código" +
                "\n5. Mostrar dispositivos por tipo" +
                "\n6. Calcular valor del inventario" +
                "\n7. Mostrar dispositivos escasos" +
                "\n8. Mostrar dispositivo más barato" +
                "\n9. Mostrar reporte de códigos" +
                "\n10. Salir"
            );

            if (op == null) {
                break;
            }

            opcion = Integer.parseInt(op);

            switch (opcion) {

                case 1:
                    codigo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el código del dispositivo"));
                    nombre = JOptionPane.showInputDialog("Ingrese el nombre");
                    marca = JOptionPane.showInputDialog("Ingrese la marca");
                    tipo = JOptionPane.showInputDialog("Ingrese el tipo de dispositivo");
                    precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio"));
                    existencia = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la existencia"));




                    DispositivoElectronico disp = new DispositivoElectronico(codigo, existencia, marca, nombre, precio, tipo);

                    JOptionPane.showMessageDialog(null, listita.registrarDispositivo(disp));
                    break;

                case 2:
                    codigo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el código a buscar"));

                    listita.buscasDospositivos(codigo);
                    break;

                case 3:
                    codigo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el código del dispositivo a modificar"));

                    listita.modificarDispositivo(codigo);
                    break;

                case 4:
                    codigo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el código del dispositivo a eliminar"));

                    listita.eliminarPorCodigo(codigo);
                    break;

                case 5:
                    tipo = JOptionPane.showInputDialog("Ingrese el tipo de dispositivo");

                    listita.mostrarTipo(tipo);
                    break;

                case 6:
                    JOptionPane.showMessageDialog(null,"El valor total del inventario es: $" + listita.calcularValor());
                    break;

                case 7:
                    System.out.println("Dispositivos escasos: ");
                    listita.dispositivosEscasos();
                    break;

                case 8:
                    System.out.println("Dispositivo más barato: ");
                    listita.dispositivoBarato();
                    break;

                case 9:
                    System.out.println(listita.mostrarCodigos());
                    
                    break;

                case 10:
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                    System.exit(0);
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida","Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }

        } while (opcion != 0);
    }
}
