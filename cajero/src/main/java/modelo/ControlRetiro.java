package modelo;

import java.util.ArrayList;
import java.util.List;

public class ControlRetiro {

    private static final double MULTIPLO_MONTO = 200.0;
    private static final int CANTIDAD_MONTOS = 6;
    private static final double PORCENTAJE_COMISION = 0.025;

    private final Cuenta cuenta;
    private final List<Double> montosDisponibles;

    private Double montoSeleccionado;
    private EstadoCuenta estadoCuenta;
    private Comprobante comprobante;
    private String mensaje;

    public ControlRetiro(Cuenta cuentaControl) {
        cuenta = cuentaControl;
        montosDisponibles = generarMontosDisponibles();
        limpiar();
    }

    
    
    private List<Double> generarMontosDisponibles() {

        List<Double> montos = new ArrayList<>();

        for (int i = 1; i <= CANTIDAD_MONTOS; i++) {
            montos.add(MULTIPLO_MONTO * i);
        }
        return montos;
    }

    public void seleccionarMonto(double monto) {
        if (comprobante != null) {
            return;
        }
        if (!esMontoValido(monto)) {
            mensaje = "El monto seleccionado no está disponible.";
            return;
        }
        double comision = calcularComision(monto);
        montoSeleccionado = monto;
        estadoCuenta = new EstadoCuenta(
                cuenta.getTitular(),
                cuenta.getSaldo(),
                monto,
                comision,
                redondear(cuenta.getSaldo() - monto - comision)
        );
        mensaje = "";
    }

    public void cancelar() {
        limpiar();
    }

    public void retirarEfectivo() {
        if (estadoCuenta == null) {
            mensaje = "Seleccione un monto para retirar.";
            return;
        }
        if (estadoCuenta.getSaldoFinal() < 0) {
            mensaje = "Saldo insuficiente para realizar el retiro.";
            return;
        }
        cuenta.descontar(
                estadoCuenta.getCantidadRetirar() + estadoCuenta.getComision()
        );
        comprobante = new Comprobante(
                cuenta.getUltimosCuatroDigitos(),
                cuenta.getTitular(),
                estadoCuenta.getCantidadRetirar(),
                estadoCuenta.getComision()
        );
        mensaje = "";
    }

    public void aceptar() {
        limpiar();
    }

    private void limpiar() {
        montoSeleccionado = null;
        estadoCuenta = null;
        comprobante = null;
        mensaje = "";
    }
    private boolean esMontoValido(double monto) {
        for (double disponible : montosDisponibles) {
            if (disponible == monto) {
                return true;
            }
        }
        return false;
    }
    private double calcularComision(double monto) {
        return redondear(monto * PORCENTAJE_COMISION);
    }

    private double redondear(double valor) {
        return Math.round(valor * 100) / 100.0;
    }

    public EtapaRetiro getEtapa() {
        return comprobante != null? EtapaRetiro.COMPROBANTE : EtapaRetiro.SELECCION;
    }

    public List<Double> getMontosDisponibles() {
        return new ArrayList<>(montosDisponibles);
    }

    public Double getMontoSeleccionado() {
        return montoSeleccionado;
    }

    public EstadoCuenta getEstadoCuenta() {
        return estadoCuenta;
    }

    public Comprobante getComprobante() {
        return comprobante;
    }

    public String getMensaje() {
        return mensaje;
    }
}
