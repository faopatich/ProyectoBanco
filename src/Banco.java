public class Banco {
    private String nombreBanco;

    public Banco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public void registrarCuenta(Cuenta cuenta) {
        System.out.println("Cuenta registrada en " + nombreBanco + ": " + cuenta.getNumeroCuenta());
    }

    public void transferirEntreCuentas(Cuenta origen, Cuenta destino, double monto) {
        if (origen == destino) {
            System.out.println("No es posible transferirse a uno mismo.");
        } else {
            origen.transferir(destino, monto);
        }


    }

    public void mostrarCuenta(Cuenta cuenta) {
        cuenta.mostrarCuenta();
    }

    public void mostrarReporteDeDosCuentas(Cuenta cuenta1, Cuenta cuenta2) {
        System.out.println("=== REPORTE DEL BANCO " + nombreBanco + " ===");
        cuenta1.mostrarCuenta();
        cuenta2.mostrarCuenta();
        System.out.println("Total en el banco: $" + (cuenta1.getSaldo() + cuenta2.getSaldo()));
    }

    public void mostrarReporteDeTresCuentas(Cuenta cuenta1, Cuenta cuenta2, Cuenta cuenta3) {
        System.out.println("=== REPORTE DEL BANCO " + nombreBanco + " ===");
        cuenta1.mostrarCuenta();
        cuenta2.mostrarCuenta();
        cuenta3.mostrarCuenta();
        System.out.println("Total en el banco: $" + (cuenta1.getSaldo() + cuenta2.getSaldo() + cuenta3.getSaldo()));
    }
}
