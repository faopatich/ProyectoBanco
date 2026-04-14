public class Cuenta {
    private String numeroCuenta;
    private TipoCuenta tipoCuenta;
    private double saldo;
    private Cliente titular;

    private String historialPropio;
    private static String historialGlobal = "";

    public Cuenta(String numeroCuenta, TipoCuenta tipoCuenta, Cliente titular) {
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.titular = titular;
        this.saldo = 0;
        this.historialPropio = "";
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getTitular() {
        return titular;
    }

    public String getHistorialPropio() {
        return historialPropio;
    }

    public boolean depositar(double monto) {
        boolean exito = false;

        if (monto > 0) {
            saldo = saldo + monto;

            String mensaje = "DEPOSITO | Cliente: " + titular.getNombre()
                    + " | DNI: " + titular.getDni()
                    + " | Cuenta: " + numeroCuenta
                    + " | Monto: $" + monto
                    + " | Saldo actual: $" + saldo;

            historialPropio = historialPropio + mensaje + "\n";
            historialGlobal = historialGlobal + mensaje + "\n";

            System.out.println(mensaje);
            exito = true;
        } else {
            System.out.println("Monto invalido.");
        }

        return exito;
    }

    public boolean extraer(double monto) {
        boolean exito = false;

        if (monto > 0) {
            if (monto <= saldo) {
                saldo = saldo - monto;

                String mensaje = "EXTRACCION | Cliente: " + titular.getNombre()
                        + " | DNI: " + titular.getDni()
                        + " | Cuenta: " + numeroCuenta
                        + " | Monto: $" + monto
                        + " | Saldo actual: $" + saldo;

                historialPropio = historialPropio + mensaje + "\n";
                historialGlobal = historialGlobal + mensaje + "\n";

                System.out.println(mensaje);
                exito = true;
            } else {
                System.out.println("Saldo insuficiente.");
            }
        } else {
            System.out.println("Monto invalido.");
        }

        return exito;
    }

    public boolean transferir(Cuenta destino, double monto) {
        boolean exito = false;

        if (destino != null) {
            if (monto > 0) {
                if (monto <= saldo) {
                    saldo = saldo - monto;
                    destino.saldo = destino.saldo + monto;

                    String mensajeOrigen = "TRANSFERENCIA ENVIADA | Destino: " + destino.titular.getNombre()
                            + " | DNI destino: " + destino.titular.getDni()
                            + " | Monto: $" + monto
                            + " | Saldo actual: $" + saldo;

                    String mensajeDestino = "TRANSFERENCIA RECIBIDA | Origen: " + titular.getNombre()
                            + " | DNI origen: " + titular.getDni()
                            + " | Monto: $" + monto
                            + " | Saldo actual: $" + destino.saldo;

                    String mensajeGlobal = "TRANSFERENCIA | Origen: " + titular.getNombre()
                            + " (" + titular.getDni() + ")"
                            + " -> Destino: " + destino.titular.getNombre()
                            + " (" + destino.titular.getDni() + ")"
                            + " | Monto: $" + monto;

                    historialPropio = historialPropio + mensajeOrigen + "\n";
                    destino.historialPropio = destino.historialPropio + mensajeDestino + "\n";
                    historialGlobal = historialGlobal + mensajeGlobal + "\n";

                    System.out.println(mensajeGlobal);
                    exito = true;
                } else {
                    System.out.println("Saldo insuficiente.");
                }
            } else {
                System.out.println("Monto invalido.");
            }
        } else {
            System.out.println("Cuenta destino inexistente.");
        }

        return exito;
    }

    public void mostrarCuenta() {
        System.out.println("Numero de cuenta: " + numeroCuenta);
        System.out.println("Tipo de cuenta: " + tipoCuenta);
        System.out.println("Saldo: $" + saldo);
    }

    public void mostrarHistorialPropio() {
        System.out.println("=== HISTORIAL DE MI CUENTA ===");

        if (historialPropio.equals("")) {
            System.out.println("No hay movimientos registrados.");
        } else {
            System.out.println(historialPropio);
        }
    }

    public static void mostrarHistorialGlobal() {
        System.out.println("=== HISTORIAL GLOBAL ===");

        if (historialGlobal.equals("")) {
            System.out.println("No hay operaciones registradas.");
        } else {
            System.out.println(historialGlobal);
        }
    }
}