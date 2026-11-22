import javax.swing.JOptionPane;

public class main {

    public static void main(String[] args) {

        int opcion;
        String codigo, tipo;

        ListaDoble listita = new ListaDoble();
// REGISTRO DE ATRACCIONES DE PRUEBA

String[] estados = {
    "Disponible",
    "Mantenimiento",
    "Fuera de servicio"
};

int aleatorio;

aleatorio = (int) (Math.random() * estados.length);
listita.insertarInicio(new Atraccion("A001", "Montaña Dragón", "Montaña rusa", 12, 24, 120, estados[aleatorio]));

aleatorio = (int) (Math.random() * estados.length);
listita.insertarInicio(new Atraccion("A002", "Río Salvaje", "Acuática", 8, 30, 90, estados[aleatorio]));

aleatorio = (int) (Math.random() * estados.length);
listita.insertarInicio(new Atraccion("A003", "Carrusel Mágico", "Infantil", 3, 20, 50, estados[aleatorio]));

aleatorio = (int) (Math.random() * estados.length);
listita.insertarFinal(new Atraccion("A004", "Torre Extrema", "Mecánica", 15, 16, 150, estados[aleatorio]));

aleatorio = (int) (Math.random() * estados.length);
listita.insertarFinal(new Atraccion("A005", "Autos Chocones", "Mecánica", 6, 18, 70, estados[aleatorio]));

aleatorio = (int) (Math.random() * estados.length);
listita.insertarFinal(new Atraccion("A006", "Casa del Terror", "Temática", 12, 25, 100, estados[aleatorio]));

System.out.println("Atracciones de prueba registradas.");
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(
                    "SISTEMA DE ADMINISTRACIÓN DE ATRACCIONES" +
                            "\n\n1. Insertar al inicio" +
                            "\n2. Insertar al final" +
                            "\n3. Insertar por posición" +
                            "\n4. Mostrar hacia adelante" +
                            "\n5. Mostrar hacia atrás" +
                            "\n6. Buscar por código" +
                            "\n7. Modificar atracción" +
                            "\n8. Eliminar por código" +
                            "\n9. Mostrar por tipo" +
                            "\n10. Mostrar atracción de mayor costo" +
                            "\n11. Calcular ingresos" +
                            "\n12. Contar atracciones" +
                            "\n13. Ordenar por costo" +
                            "\n14. Mostrar atracciones para menores" +
                            "\n15. Insertar después de un código" +
                            "\n16. Insertar antes de un código" +
                            "\n17. Eliminar fuera de servicio" +
                            "\n18. Salir"));

            switch (opcion) {

                case 1:
                    System.out.println("\n===== INSERTAR AL INICIO =====");
                    listita.insertarInicio(capturarAtraccion());
                    break;

                case 2:
                    System.out.println("\n===== INSERTAR AL FINAL =====");
                    listita.insertarFinal(capturarAtraccion());
                    break;

                case 3:
                    System.out.println("\n===== INSERTAR POR POSICIÓN =====");
                    int posicion = Integer.parseInt(
                            JOptionPane.showInputDialog("Ingresa la posición:"));
                    listita.insertarPosicion(posicion, capturarAtraccion());
                    break;

                case 4:
                    System.out.println("\n===== MOSTRAR HACIA ADELANTE =====");
                    listita.mostrarAdelante();
                    break;

                case 5:
                    System.out.println("\n===== MOSTRAR HACIA ATRÁS =====");
                    listita.mostrarAtras();
                    break;

                case 6:
                    System.out.println("\n===== BUSCAR POR CÓDIGO =====");
                    codigo = JOptionPane.showInputDialog("Ingresa el código:");
                    listita.buscarCodigo(codigo);
                    break;

                case 7:
                    System.out.println("\n===== MODIFICAR ATRACCIÓN =====");
                    codigo = JOptionPane.showInputDialog("Ingresa el código:");
                    listita.modificarAtraccion(codigo);
                    break;

                case 8:
                    System.out.println("\n===== ELIMINAR POR CÓDIGO =====");
                    codigo = JOptionPane.showInputDialog("Ingresa el código:");
                    listita.eliminarPorCodigo(codigo);
                    break;

                case 9:
                    System.out.println("\n===== MOSTRAR POR TIPO =====");
                    tipo = JOptionPane.showInputDialog("Ingresa el tipo:");
                    listita.mostrarPorTipo(tipo);
                    break;

                case 10:
                    System.out.println("\n===== ATRACCIÓN DE MAYOR COSTO =====");
                    listita.mostrarMayorPrecio();
                    break;

                case 11:
                    System.out.println("\n===== CALCULAR INGRESOS =====");
                    System.out.println("Ingresos totales: $" + listita.calcularIngresos());
                    break;

                case 12:
                    System.out.println("\n===== CONTAR ATRACCIONES =====");
                    System.out.println("Total de atracciones: " + listita.contarAtracciones());
                    break;

                case 13:
                    System.out.println("\n===== ORDENAR POR COSTO =====");
                    listita.ordenarPorCosto();
                    break;

                case 14:
                    System.out.println("\n===== ATRACCIONES PARA MENORES =====");
                    listita.atraccionesMenores();
                    break;

                case 15:
                    System.out.println("\n===== INSERTAR DESPUÉS DE UN CÓDIGO =====");
                    codigo = JOptionPane.showInputDialog("Código de referencia:");
                    listita.insertarFrenteCodigo(codigo, capturarAtraccion());
                    break;

                case 16:
                    System.out.println("\n===== INSERTAR ANTES DE UN CÓDIGO =====");
                    codigo = JOptionPane.showInputDialog("Código de referencia:");
                    listita.insertarDetrasCodigo(codigo, capturarAtraccion());
                    break;

                case 17:
                    System.out.println("\n===== ELIMINAR FUERA DE SERVICIO =====");
                    listita.eliminarFueraServicio();
                    break;

                case 18:
                    System.out.println("\n===== SALIENDO DEL SISTEMA =====");
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                    break;

                default:
                    System.out.println("\n===== OPCIÓN NO VÁLIDA =====");
                    JOptionPane.showMessageDialog(null, "Opción no encontrada.");
                    break;
            }

        } while (opcion != 18);

    }

    public static Atraccion capturarAtraccion() {

        String codigo = JOptionPane.showInputDialog("Código:");
        String nombre = JOptionPane.showInputDialog("Nombre:");
        String tipo = JOptionPane.showInputDialog("Tipo:");
        int edadMinima = Integer.parseInt(
                JOptionPane.showInputDialog("Edad mínima:"));
        int capacidad = Integer.parseInt(
                JOptionPane.showInputDialog("Capacidad:"));
        double costo = Double.parseDouble(
                JOptionPane.showInputDialog("Costo:"));
        String estado = JOptionPane.showInputDialog(
                "Estado:\nDisponible\nMantenimiento\nFuera de servicio");

        return new Atraccion(
                codigo, nombre, tipo, edadMinima, capacidad, costo, estado);
    }
}