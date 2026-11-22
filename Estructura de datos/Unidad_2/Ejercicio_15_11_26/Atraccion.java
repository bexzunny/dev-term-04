
public class Atraccion {

    private String codigo;
    private String nombre;
    private String tipo;
    private int edadMinima;
    private int capacidad;
    private double costo;
    private String estado;

    public Atraccion(String codigo, String nombre, String tipo,
            int edadMinima, int capacidad,
            double costo, String estado) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.edadMinima = edadMinima;
        this.capacidad = capacidad;
        this.costo = costo;
        this.estado = estado;
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(int edadMinima) {
        this.edadMinima = edadMinima;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Mostrar información de la atracción

    public void mostrarDatos() {
        System.out.println("Código: " + codigo);
        System.out.println("Nombre: " + nombre);
        System.out.println("Tipo: " + tipo);
        System.out.println("Edad mínima: " + edadMinima);
        System.out.println("Capacidad: " + capacidad);
        System.out.println("Costo: $" + costo);
        System.out.println("Estado: " + estado);
        System.out.println("--------------------------------");
    }

}
