package vista;

import controlador.Controlador;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import modelo.Comprobante;
import modelo.EtapaRetiro;
import modelo.IConsultaModelo;
import utilerias.Observador;

public class VistaComprobante extends JFrame implements Observador {

    private static final NumberFormat MONEDA =
            NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-MX"));

    private Controlador controlador;
    private IConsultaModelo modelo;

    private JLabel lblCuenta;
    private JLabel lblCliente;
    private JLabel lblCantidad;
    private JLabel lblComision;

    private JButton btnAceptar;

    public VistaComprobante() {

        setTitle("Retirar efectivo");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        crearComponentes();
    }

    private void crearComponentes() {

        setLayout(new BorderLayout(10, 10));
        JPanel pnlNorte = new JPanel(new GridLayout(2, 1));
        pnlNorte.add(new JLabel("Retiro realizado"));
        pnlNorte.add(new JLabel("Su operación se completó correctamente"));
        add(pnlNorte, BorderLayout.NORTH);

        JPanel pnlComprobante = new JPanel(new GridLayout(4, 2, 5, 5));
        pnlComprobante.setBorder(
                BorderFactory.createTitledBorder("COMPROBANTE DE RETIRO"));

        lblCuenta = new JLabel();
        lblCliente = new JLabel();
        lblCantidad = new JLabel();
        lblComision = new JLabel();

        pnlComprobante.add(new JLabel("Cuenta:"));
        pnlComprobante.add(lblCuenta);
        pnlComprobante.add(new JLabel("Cliente:"));
        pnlComprobante.add(lblCliente);
        pnlComprobante.add(new JLabel("Cantidad retirada:"));
        pnlComprobante.add(lblCantidad);
        pnlComprobante.add(new JLabel("Comisión por retiro:"));
        pnlComprobante.add(lblComision);

        add(pnlComprobante, BorderLayout.CENTER);

        JPanel pnlSur = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnAceptar = new JButton("Aceptar");
        pnlSur.add(btnAceptar);
        add(pnlSur, BorderLayout.SOUTH);

        btnAceptar.addActionListener(e -> controlador.aceptar());
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void setModelo(IConsultaModelo modelo) {
        this.modelo = modelo;
    }


    @Override
    public void actualizar() {

        if (modelo.getEtapa() != EtapaRetiro.COMPROBANTE) {
            setVisible(false);
            return;
        }

        Comprobante comprobante = modelo.getComprobante();

        lblCuenta.setText("**** " + comprobante.getUltimosDigitos());
        lblCliente.setText(comprobante.getCliente());
        lblCantidad.setText(MONEDA.format(comprobante.getCantidadRetirada()));
        lblComision.setText(MONEDA.format(comprobante.getComision()));

        setVisible(true);
    }
}
