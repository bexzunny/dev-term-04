package Unidad_2.Ejercicio_06_11_26;

public class TransporteCarga {
    String codigo;
    String marca;
    String modelo;
    String tipo;
    double capacidadToneladas;
    String placas;
    boolean disponible;

    public TransporteCarga(double capacidadToneladas, String codigo, boolean disponible, String marca, String modelo, String placas, String tipo) {
        this.capacidadToneladas = capacidadToneladas;
        this.codigo = codigo;
        this.disponible = disponible;
        this.marca = marca;
        this.modelo = modelo;
        this.placas = placas;
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public double getCapacidadToneladas() {
        return capacidadToneladas;
    }

    public String getPlacas() {
        return placas;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCapacidadToneladas(double capacidadToneladas) {
        this.capacidadToneladas = capacidadToneladas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
