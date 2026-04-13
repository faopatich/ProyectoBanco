public class Cuenta {
    private String numeroCuenta;
    private String tipoCuenta;
    private double saldo;
    private Persona titular;

    public Cuenta(String numeroCuenta, String tipoCuenta, Persona titular) {
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.titular = titular;
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

    public Persona getTitular() {
        return titular;
    }

    public void depositar(double monto) {
        if (monto > 0) {
            saldo = saldo + monto;
            System.out.println("Deposito realizado.");
        } else {
            System.out.println("Monto invalido.");
        }
    }

    public void transferir(Cuenta destino, double monto) {
        if (monto <= 0) {
            System.out.println("El monto debe ser mayor a 0.");
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
        titular.mostrarPersona();
        System.out.println("----------------------------");
    }
}
