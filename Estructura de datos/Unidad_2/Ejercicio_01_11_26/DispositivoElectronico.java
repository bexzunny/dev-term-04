package Unidad_2.Ejercicio_01_11_26;

public class DispositivoElectronico {
    int codigo;
    String nombre;
    String marca;
    String tipo;
    double precio;
    int existencia;

    public DispositivoElectronico(int codigo, int existencia, String marca, String nombre, double precio, String tipo) {
        this.codigo = codigo;
        this.existencia = existencia;
        this.marca = marca;
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public int getExistencia() {
        return existencia;
    }
}
