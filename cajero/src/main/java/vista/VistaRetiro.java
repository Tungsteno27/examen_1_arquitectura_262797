package vista;

import controlador.Controlador;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import modelo.EstadoCuenta;
import modelo.EtapaRetiro;
import modelo.IConsultaModelo;
import utilerias.Observador;


public class VistaRetiro extends JFrame implements Observador {

    private static final NumberFormat MONEDA =
            NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-MX"));

    private Controlador controlador;
    private IConsultaModelo modelo;

    private JLabel lblMensaje;
    private JPanel pnlMontos;
    private final Map<Double, JToggleButton> botonesMontos =new LinkedHashMap<>();
    private JPanel pnlEstado;
    private JLabel lblEstNombre;
    private JLabel lblEstSaldo;
    private JLabel lblEstCantidad;
    private JLabel lblEstComision;
    private JLabel lblEstFinal;
    private JButton btnCancelar;
    private JButton btnRetirar;

    public VistaRetiro() {
        setTitle("Retirar efectivo");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        crearComponentes();
    }

    private void crearComponentes() {

        setLayout(new BorderLayout(10, 10));

        JPanel pnlNorte = new JPanel(new GridLayout(2, 1));
        pnlNorte.add(new JLabel("Retirar efectivo"));
        pnlNorte.add(new JLabel("Seleccione la cantidad que desea retirar"));
        add(pnlNorte, BorderLayout.NORTH);

        pnlMontos = new JPanel(new GridLayout(0, 2, 5, 5));
        pnlMontos.setBorder(BorderFactory.createTitledBorder("Cantidad en MXN"));
        add(pnlMontos, BorderLayout.WEST);

        pnlEstado = new JPanel(new GridLayout(5, 2, 5, 5));
        pnlEstado.setBorder(BorderFactory.createTitledBorder("Estado de cuenta"));

        lblEstNombre = new JLabel();
        lblEstSaldo = new JLabel();
        lblEstCantidad = new JLabel();
        lblEstComision = new JLabel();
        lblEstFinal = new JLabel();

        pnlEstado.add(new JLabel("Nombre:"));
        pnlEstado.add(lblEstNombre);
        pnlEstado.add(new JLabel("Monto actual:"));
        pnlEstado.add(lblEstSaldo);
        pnlEstado.add(new JLabel("Cantidad a retirar:"));
        pnlEstado.add(lblEstCantidad);
        pnlEstado.add(new JLabel("Comisión por retiro:"));
        pnlEstado.add(lblEstComision);
        pnlEstado.add(new JLabel("Saldo final después del retiro:"));
        pnlEstado.add(lblEstFinal);

        add(pnlEstado, BorderLayout.CENTER);

        JPanel pnlSur = new JPanel(new BorderLayout());

        lblMensaje = new JLabel(" ");
        pnlSur.add(lblMensaje, BorderLayout.WEST);

        JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnCancelar = new JButton("Cancelar");
        btnRetirar = new JButton("Retirar efectivo");
        pnlBotones.add(btnCancelar);
        pnlBotones.add(btnRetirar);
        pnlSur.add(pnlBotones, BorderLayout.EAST);

        add(pnlSur, BorderLayout.SOUTH);

        btnCancelar.addActionListener(e -> controlador.cancelar());
        btnRetirar.addActionListener(e -> controlador.retirarEfectivo());
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void setModelo(IConsultaModelo modelo) {
        this.modelo = modelo;
        crearBotonesMontos();
    }

    private void crearBotonesMontos() {

        for (Double monto : modelo.getMontosDisponibles()) {

            JToggleButton boton = new JToggleButton(MONEDA.format(monto));
            boton.addActionListener(e -> controlador.seleccionarMonto(monto));

            botonesMontos.put(monto, boton);
            pnlMontos.add(boton);
        }
    }

    @Override
    public void actualizar() {
        if (modelo.getEtapa() != EtapaRetiro.SELECCION) {
            setVisible(false);
            return;
        }

        EstadoCuenta estado = modelo.getEstadoCuenta();
        Double seleccionado = modelo.getMontoSeleccionado();

        String mensaje = modelo.getMensaje();
        lblMensaje.setText(mensaje == null || mensaje.isEmpty() ? " " : mensaje);

        for (Map.Entry<Double, JToggleButton> par : botonesMontos.entrySet()) {
            par.getValue().setSelected(seleccionado != null && par.getKey().equals(seleccionado));
        }
        pnlEstado.setVisible(estado != null);
        btnCancelar.setEnabled(estado != null);
        btnRetirar.setEnabled(estado != null);
        if (estado != null) {
            lblEstNombre.setText(estado.getNombre());
            lblEstSaldo.setText(MONEDA.format(estado.getSaldoActual()));
            lblEstCantidad.setText(MONEDA.format(estado.getCantidadRetirar()));
            lblEstComision.setText(MONEDA.format(estado.getComision()));
            lblEstFinal.setText(MONEDA.format(estado.getSaldoFinal()));
        }
        setVisible(true);
    }
}
