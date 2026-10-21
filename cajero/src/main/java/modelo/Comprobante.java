package modelo;

public final class Comprobante {

    private final String ultimosDigitos;
    private final String cliente;
    private final double cantidadRetirada;
    private final double comision;

    public Comprobante(String ultimosDigitos, String cliente,
            double cantidadRetirada, double comision) {
        this.ultimosDigitos = ultimosDigitos;
        this.cliente = cliente;
        this.cantidadRetirada = cantidadRetirada;
        this.comision = comision;
    }

    public String getUltimosDigitos() {
        return ultimosDigitos;
    }

    public String getCliente() {
        return cliente;
    }

    public double getCantidadRetirada() {
        return cantidadRetirada;
    }

    public double getComision() {
        return comision;
    }
}
