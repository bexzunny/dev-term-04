package Unidad_1.Tarea.Ej_5;

import javax.swing.JOptionPane;

public class main {

    public static void main(String[] args) {

        String opcion;

        String menu = "MENU CINE\n"
                + "1. Mostrar mapa de asientos\n"
                + "2. Reservar asiento\n"
                + "3. Cancelar reserva\n"
                + "4. Consultar asiento\n"
                + "5. Contar asientos disponibles\n"
                + "6. Contar asientos ocupados\n"
                + "7. Mostrar porcentaje de ocupación\n"
                + "8. Mostrar fila con mayor ocupación\n"
                + "9. Mostrar filas completamente ocupadas\n"
                + "10. Salir";

        cinepolis cine = new cinepolis();

        while(true){

            opcion = JOptionPane.showInputDialog(menu);

            switch(opcion){

                case "1":

                    System.out.println("===== MAPA DE ASIENTOS =====");
                    cine.mostrarAsientos();

                    break;

                case "2":

                    int fi = Integer.parseInt(
                            JOptionPane.showInputDialog("Ingresa la fila (1-8)")
                    );

                    int co = Integer.parseInt(
                            JOptionPane.showInputDialog("Ingresa el asiento (1-10)")
                    );

                    cine.reservarAsiento(fi-1,co-1);

                    break;

                case "3":

                    fi = Integer.parseInt(
                            JOptionPane.showInputDialog("Ingresa la fila (1-8)")
                    );

                    co = Integer.parseInt(
                            JOptionPane.showInputDialog("Ingresa el asiento (1-10)")
                    );

                    cine.cancelarReserva(fi-1,co-1);

                    break;

                case "4":

                    fi = Integer.parseInt(
                            JOptionPane.showInputDialog("Ingresa la fila (1-8)")
                    );

                    co = Integer.parseInt(
                            JOptionPane.showInputDialog("Ingresa el asiento (1-10)")
                    );

                    cine.consultarReserva(fi-1,co-1);

                    break;

                case "5":

                    JOptionPane.showMessageDialog(
                            null,
                            "Asientos disponibles: " + cine.asientosDisp()
                    );

                    break;

                case "6":

                    JOptionPane.showMessageDialog(
                            null,
                            "Asientos ocupados: " + cine.asientosOcup()
                    );

                    break;

                case "7":

                    JOptionPane.showMessageDialog(
                            null,
                            "Porcentaje de ocupación: "
                            + String.format("%.2f",cine.porcentajeOcupacion()) + "%"
                    );

                    break;

                case "8":

                    JOptionPane.showMessageDialog(
                            null,
                            "Fila con mayor ocupación: "
                            + (cine.filaMasOcup()+1)
                    );

                    break;

                case "9":

                    JOptionPane.showMessageDialog(
                            null,
                            "Filas completamente ocupadas:\n"
                            + cine.mostrarFilasOcup()
                    );

                    break;

                case "10":

                    System.exit(0);

                    break;

                default:

                    JOptionPane.showMessageDialog(
                            null,
                            "Opción no válida"
                    );

                    break;
            }
        }
    }
}