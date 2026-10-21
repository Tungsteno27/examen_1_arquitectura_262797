package modelo;

import java.util.List;
import utilerias.SujetoObservado;

public class FachadaModeloRetiro extends SujetoObservado implements IOperacionesModelo, IConsultaModelo {

    private final ControlRetiro control;

    public FachadaModeloRetiro(Cuenta cuenta) {
        control = new ControlRetiro(cuenta);
    }

    @Override
    public void seleccionarMonto(double monto) {
        control.seleccionarMonto(monto);
        notificarObservadores();
    }

    @Override
    public void cancelar() {
        control.cancelar();
        notificarObservadores();
    }

    @Override
    public void retirarEfectivo() {
        control.retirarEfectivo();
        notificarObservadores();
    }

    @Override
    public void aceptar() {
        control.aceptar();
        notificarObservadores();
    }


    @Override
    public EtapaRetiro getEtapa() {
        return control.getEtapa();
    }

    @Override
    public List<Double> getMontosDisponibles() {
        return control.getMontosDisponibles();
    }

    @Override
    public Double getMontoSeleccionado() {
        return control.getMontoSeleccionado();
    }

    @Override
    public EstadoCuenta getEstadoCuenta() {
        return control.getEstadoCuenta();
    }

    @Override
    public Comprobante getComprobante() {
        return control.getComprobante();
    }

    @Override
    public String getMensaje() {
        return control.getMensaje();
    }
}
