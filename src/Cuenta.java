public class Cuenta {
    private static int contadorCuentas = 1;
    private String numeroCuenta;
    private String tipoCuenta;
    private double saldo;

    public Cuenta(String tipoCuenta) {
        this.numeroCuenta = "CU-" + contadorCuentas;
        contadorCuentas = contadorCuentas + 1;
        this.tipoCuenta = tipoCuenta;
        this.saldo = 0;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void sumarSaldo(double monto) {
        saldo = saldo + monto;
    }

    public void restarSaldo(double monto) {
        saldo = saldo - monto;
    }

    public void mostrarCuenta() {
        System.out.println("Numero de cuenta: " + numeroCuenta);
        System.out.println("Tipo de cuenta: " + tipoCuenta);
        System.out.println("Saldo: $" + saldo);
    }
}