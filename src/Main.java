public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco("Banco Demo");

        Persona persona1 = new Persona("Franco", "12345678", "Calle 1", 25, "franco@gmail.com");
        Persona persona2 = new Persona("Juan", "87654321", "Calle 2", 30, "juan@gmail.com");

        Cuenta cuenta1 = new Cuenta("001", "Caja de ahorro", persona1);
        Cuenta cuenta2 = new Cuenta("002", "Cuenta corriente", persona2);

        banco.registrarCuenta(cuenta1);
        banco.registrarCuenta(cuenta2);

        cuenta1.depositar(0);
        cuenta1.depositar(10000);
        cuenta2.depositar(5000);

        banco.transferirEntreCuentas(cuenta1, cuenta2, 2000);
        banco.transferirEntreCuentas(cuenta1, cuenta1, 1000);

        banco.mostrarReporteDeDosCuentas(cuenta1, cuenta2);
        cuenta1.mostrarHistorialTransferencias();
        cuenta2.mostrarHistorialTransferencias();
    }
}
