package Unidad_1.Tarea.Ej_4;

public class main {

    public static void main(String[] args) {

        tiendas negocio = new tiendas();

        negocio.llenar_arreglo();
        negocio.llenarReporte();



        
        System.out.println("===== TOTAL POR SUCURSAL =====");
        System.out.println(negocio.mostrarTotalSucursal());

        System.out.println("===== TOTAL POR DÍA =====");
        System.out.println(negocio.mostrarTotalDia());

        System.out.println("===== SUCURSAL CON MAYORES VENTAS =====");

        System.out.println("Sucursal: " + (negocio.sucursalTop()+1));

        System.out.println("===== DÍA CON MAYORES VENTAS =====");

        System.out.println("Día: " + (negocio.diaTop()+1));

        System.out.println("===== PROMEDIO DE VENTAS =====");

        System.out.println(String.format("%.2f",negocio.promedio()));

        System.out.println("===== SUCURSALES SOBRE EL PROMEDIO =====");

        System.out.println(negocio.sucursalesSobrePromedio());

        System.out.println("===== DÍA CON MENORES VENTAS =====");

        System.out.println("Día: " + (negocio.diaMenor()+1));

        System.out.println("===== REPORTE =====");

        negocio.mostrarReporte();

    }

}