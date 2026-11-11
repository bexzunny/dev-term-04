package Unidad_2.clase_26_10_26;

public class Empleado {
    int idEmpleado;
    String nombre;
    String departamento;
    double sueldoNeto;
    int antiguedad;

    public Empleado(int antiguedad, String departamento, int idEmpleado, String nombre, double sueldoNeto) {
        this.antiguedad = antiguedad;
        this.departamento = departamento;
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.sueldoNeto = sueldoNeto;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public double getSueldoNeto() {
        return sueldoNeto;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

}
