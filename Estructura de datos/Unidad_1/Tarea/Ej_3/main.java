package Unidad_1.Tarea.Ej_3;

public class main {

    public static void main(String[] args) {

        escuela universidad = new escuela();

        universidad.llenar_arreglo();

        System.out.println("===== PROMEDIOS POR GRUPO =====");

        for (int i = 0; i < 4; i++) {
            System.out.println(
                "Grupo " + (i + 1) + ": "
                + String.format("%.2f", universidad.promedioGrupo(i))
            );
        }

        System.out.println("===== PROMEDIO GENERAL =====");
        System.out.println(
            String.format("%.2f", universidad.promedioGeneral())
        );

        universidad.califMax();

        System.out.println("===== APROBADOS Y REPROBADOS =====");
        System.out.println("Aprobados: " + universidad.aprobaron());
        System.out.println("Reprobados: " + universidad.reprobaron());

        System.out.println("===== GRUPO CON MAYOR PROMEDIO =====");
        System.out.println("Grupo: " + (universidad.grupoTop() + 1));

        System.out.println("===== ESTUDIANTES SOBRE EL PROMEDIO GENERAL =====");
        System.out.println(universidad.alumnosDestacados());
    }
}