public class Cuenta {
    private String numeroCuenta;
    private String tipoCuenta;
    private double saldo;
    private Cliente titular;

    private static String historialGlobal = "";

    public Cuenta(String numeroCuenta, String tipoCuenta, Cliente titular) {
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

    public Cliente getTitular() {
        return titular;
    }

    public boolean depositar(double monto) {
        boolean operacionExitosa = false;

        if (monto > 0) {
            saldo = saldo + monto;

            String mensaje = "DEPOSITO | Titular: " + titular.getNombre()
                    + " | Cuenta: " + numeroCuenta
                    + " | Monto: $" + monto
                    + " | Saldo actual: $" + saldo;

            historialGlobal = historialGlobal + mensaje + "\n";
            System.out.println(mensaje);
            operacionExitosa = true;
        } else {
            System.out.println("Monto invalido. Debe ser mayor a 0.");
        }

        return operacionExitosa;
    }

    public boolean extraer(double monto) {
        boolean operacionExitosa = false;

        if (monto > 0) {
            if (monto <= saldo) {
                saldo = saldo - monto;

                String mensaje = "EXTRACCION | Titular: " + titular.getNombre()
                        + " | Cuenta: " + numeroCuenta
                        + " | Monto: $" + monto
                        + " | Saldo actual: $" + saldo;

                historialGlobal = historialGlobal + mensaje + "\n";
                System.out.println(mensaje);
                operacionExitosa = true;
            } else {
                System.out.println("Saldo insuficiente.");
            }
        } else {
            System.out.println("Monto invalido. Debe ser mayor a 0.");
        }

        return operacionExitosa;
    }

    public boolean transferir(Cuenta destino, double monto) {
        boolean operacionExitosa = false;

        if (destino != null) {
            if (monto > 0) {
                if (monto <= saldo) {
                    saldo = saldo - monto;
                    destino.saldo = destino.saldo + monto;

                    String mensaje = "TRANSFERENCIA | De: " + titular.getNombre()
                            + " (" + numeroCuenta + ")"
                            + " -> A: " + destino.titular.getNombre()
                            + " (" + destino.numeroCuenta + ")"
                            + " | Monto: $" + monto;

                    historialGlobal = historialGlobal + mensaje + "\n";
                    System.out.println(mensaje);
                    operacionExitosa = true;
                } else {
                    System.out.println("Saldo insuficiente.");
                }
            } else {
                System.out.println("Monto invalido. Debe ser mayor a 0.");
            }
        } else {
            System.out.println("La cuenta destino no existe.");
        }

        return operacionExitosa;
    }

    public void mostrarCuenta() {
        System.out.println("Numero de cuenta: " + numeroCuenta);
        System.out.println("Tipo de cuenta: " + tipoCuenta);
        System.out.println("Saldo: $" + saldo);
    }

    public static void mostrarHistorialGlobal() {
        System.out.println("=== HISTORIAL GLOBAL DE OPERACIONES ===");

        if (historialGlobal.equals("")) {
            System.out.println("No hay operaciones registradas.");
        } else {
            System.out.println(historialGlobal);
        }
    }

}