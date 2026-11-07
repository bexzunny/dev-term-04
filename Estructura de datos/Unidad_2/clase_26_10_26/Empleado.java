package Unidad_2.clase_26_10_26;

public class Empleado {
    int idempleado;
    String nombre;
    String departamento;
    double sueldoneto;
    int antiguedad;

    public Empleado(int antiguedad, String departamento, int idempleado, String nombre, double sueldoneto) {
        this.antiguedad = antiguedad;
        this.departamento = departamento;
        this.idempleado = idempleado;
        this.nombre = nombre;
        this.sueldoneto = sueldoneto;
    }

    public int getIdempleado() {
        return idempleado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public double getSueldoneto() {
        return sueldoneto;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

}
