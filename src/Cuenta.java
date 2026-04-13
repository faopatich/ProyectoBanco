public class Cuenta {
    private int numeroCuenta;
    private String tipoCuenta;
    private double saldo;
    private Persona titular;
    private String historialTransferencias;

    public Cuenta(int numeroCuenta, String tipoCuenta, Persona titular) {
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.titular = titular;
        this.saldo = 0;
        this.historialTransferencias = "";
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double monto) {
        if (monto > 0) {
            saldo = saldo + monto;

            System.out.println("=== DEPOSITO REALIZADO ===");
            System.out.println("Titular: " + this.titular.getNombre());
            System.out.println("Cuenta: " + this.numeroCuenta);
            System.out.println("Monto depositado: $" + monto);
            System.out.println("Saldo actual: $" + this.saldo);
            System.out.println("--------------------------");

        } else {
            System.out.println("=== DEPOSITO NO REALIZADO ===");
            System.out.println("Titular: " + this.titular.getNombre());
            System.out.println("Cuenta: " + this.numeroCuenta);
            System.out.println("Monto depositado: $" + monto);
            System.out.println("Saldo actual: $" + this.saldo);
            System.out.println("Motivo: monto invalido.");
            System.out.println("Deposite un monto mayor a 0.");
            System.out.println("--------------------------");
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
            String mensaje = "Transferencia de $" + monto +
                    " de cuenta " + this.numeroCuenta +
                    " a cuenta " + destino.numeroCuenta;

            this.historialTransferencias += mensaje + " (ENVIADA)\n";
            destino.historialTransferencias += mensaje + " (RECIBIDA)\n";

            System.out.println("Transferencia realizada con exito.");
            System.out.println(mensaje);
            System.out.println("----------------------------------");
        }
    }
    public void mostrarHistorialTransferencias() {
        System.out.println("=== HISTORIAL DE TRANSFERENCIAS ===");

        if (historialTransferencias.equals("")) {
            System.out.println("No hay transferencias.");
        } else {
            System.out.println(historialTransferencias);
        }
    }

    public void mostrarCuenta() {
        System.out.println("Numero de cuenta: " + numeroCuenta);
        System.out.println("Tipo de cuenta: " + tipoCuenta);
        System.out.println("Saldo: $" + saldo);
        titular.mostrarPersona();

    }
}
