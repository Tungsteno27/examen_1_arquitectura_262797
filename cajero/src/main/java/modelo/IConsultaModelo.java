package modelo;

import java.util.List;

public interface IConsultaModelo {

    EtapaRetiro getEtapa();

    List<Double> getMontosDisponibles();

    Double getMontoSeleccionado();

    EstadoCuenta getEstadoCuenta();

    Comprobante getComprobante();

    String getMensaje();

}
