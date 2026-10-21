package ensamblador;

import controlador.Controlador;
import modelo.Cuenta;
import modelo.FachadaModeloRetiro;
import vista.VistaComprobante;
import vista.VistaRetiro;

public class Ensamblador {

    public void ensamblar() {
        
        Cuenta cuenta = new Cuenta("5512345678904827","Julián Izaguirre Menchaca",5000.00);
        FachadaModeloRetiro modelo = new FachadaModeloRetiro(cuenta);
        Controlador controlador = new Controlador();

        VistaRetiro vistaRetiro = new VistaRetiro();

        VistaComprobante vistaComprobante = new VistaComprobante();
        

        controlador.setModelo(modelo);

        vistaRetiro.setControlador(controlador);
        vistaComprobante.setControlador(controlador);

        vistaRetiro.setModelo(modelo);
        vistaComprobante.setModelo(modelo);

        modelo.agregarObservador(vistaRetiro);
        modelo.agregarObservador(vistaComprobante);

        vistaRetiro.actualizar();
        vistaComprobante.actualizar();
    }
}
