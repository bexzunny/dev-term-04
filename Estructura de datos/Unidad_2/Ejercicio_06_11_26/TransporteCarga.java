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
        if(codigo != null && !codigo.trim().isEmpty()){
            this.codigo = codigo;
        }else{
            System.out.println("Error: El código no puede estar vacío.");
        }
    }

    public void setMarca(String marca) {
        if(marca != null && !marca.trim().isEmpty()){
            this.marca = marca;
        }else{
            System.out.println("Error: La marca no puede estar vacía.");
        }
    }

    public void setModelo(String modelo) {
        if(modelo != null && !modelo.trim().isEmpty()){
            this.modelo = modelo;
        }else{
            System.out.println("Error: El modelo no puede estar vacío.");
        }
    }

    public void setTipo(String tipo) {
        if(tipo != null && !tipo.trim().isEmpty()){
            this.tipo = tipo;
        }else{
            System.out.println("Error: El tipo no puede estar vacío.");
        }
    }

    public void setCapacidadToneladas(double capacidadToneladas) {
        if(capacidadToneladas > 0){
            this.capacidadToneladas = capacidadToneladas;
        }else{
            System.out.println("Error: La capacidad debe ser mayor a cero.");
        }
    }

    public void setPlacas(String placas) {
        if(placas != null && !placas.trim().isEmpty()){
            this.placas = placas;
        }else{
            System.out.println("Error: Las placas no pueden estar vacías.");
        }
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
