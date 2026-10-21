package controlador;

import modelo.IOperacionesModelo;


public class Controlador {

    private IOperacionesModelo modelo;

    public Controlador() {
    }

    public void setModelo(IOperacionesModelo modelo) {
        this.modelo = modelo;
    }
    
    public void seleccionarMonto(double monto) {
        modelo.seleccionarMonto(monto);
    }

    public void cancelar() {
        modelo.cancelar();
    }

    public void retirarEfectivo() {
        modelo.retirarEfectivo();
    }

    public void aceptar() {
        modelo.aceptar();
    }
}
