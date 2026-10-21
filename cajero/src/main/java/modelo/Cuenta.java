package modelo;

public class Cuenta {

    private final String numero;
    private final String titular;
    private double saldo;

    public Cuenta(String numero, String titular, double saldo) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldo;
    }

    public String getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getUltimosCuatroDigitos() {
        return numero.substring(numero.length() - 4);
    }

    public void descontar(double cantidad) {
        saldo = saldo - cantidad;
    }
}
