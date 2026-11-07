package Unidad_1.Tarea.Ej_1;

public class main {

    public static void main(String[] args) {

        alumnos grupo = new alumnos(30);

        grupo.llenar_arreglo();

        grupo.aprobados();

        grupo.ordenar();

        System.out.println("===== RESULTADOS DEL GRUPO =====");
        System.out.println("Promedio del grupo: " + String.format("%.2f", grupo.promedio()));
        System.out.println("Calificación mayor: " + grupo.mayor());
        System.out.println("Calificación menor: " + grupo.menor());
        System.out.println("Estudiantes que aprobaron: " + grupo.aprobaron());
        System.out.println("Estudiantes que reprobaron: " + grupo.reprobaron());
        System.out.println("Estudiantes con calificación superior al promedio: " + grupo.mayPromedio());
        System.out.println("Posiciones con calificación máxima:");
        System.out.println(grupo.maximos());
        System.out.println("Calificaciones aprobatorias ordenadas:");
        System.out.println(grupo.mostrarAprobados());
    }
}