package modelo;

public final class EstadoCuenta {

    private final String nombre;
    private final double saldoActual;
    private final double cantidadRetirar;
    private final double comision;
    private final double saldoFinal;

    public EstadoCuenta(String nombre, double saldoActual, double cantidadRetirar, double comision, double saldoFinal) {
        this.nombre = nombre;
        this.saldoActual = saldoActual;
        this.cantidadRetirar = cantidadRetirar;
        this.comision = comision;
        this.saldoFinal = saldoFinal;
    }

    public String getNombre() {
        return nombre;
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public double getCantidadRetirar() {
        return cantidadRetirar;
    }

    public double getComision() {
        return comision;
    }

    public double getSaldoFinal() {
        return saldoFinal;
    }
}
