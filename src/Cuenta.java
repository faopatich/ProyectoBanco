public class Cuenta {
    private String numeroCuenta;
    private String tipoCuenta;
    private double saldo;

    public Cuenta(String numeroCuenta, String tipoCuenta) {
        this.numeroCuenta = numeroCuenta;
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

    public void depositar(double monto) {
        if (monto > 0) {
            saldo = saldo + monto;
            System.out.println("Deposito realizado. Saldo actual: $" + saldo);
        } else {
            System.out.println("Monto invalido.");
        }
    }

    public void extraer(double monto) {
        if (monto > 0) {
            if (monto <= saldo) {
                saldo = saldo - monto;
                System.out.println("Extraccion realizada. Saldo actual: $" + saldo);
            } else {
                System.out.println("Saldo insuficiente.");
            }
        } else {
            System.out.println("Monto invalido.");
        }
    }

    public void transferir(Cuenta destino, double monto) {
        if (destino == null) {
            System.out.println("Cuenta destino inexistente.");
        } else if (monto <= 0) {
            System.out.println("Monto invalido.");
        } else if (monto > saldo) {
            System.out.println("Saldo insuficiente.");
        } else {
            saldo = saldo - monto;
            destino.saldo = destino.saldo + monto;
            System.out.println("Transferencia realizada con exito.");
        }
    }

    public void mostrarCuenta() {
        System.out.println("Numero de cuenta: " + numeroCuenta);
        System.out.println("Tipo de cuenta: " + tipoCuenta);
        System.out.println("Saldo: $" + saldo);
    }
}