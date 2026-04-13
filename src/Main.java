public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco("Banco Demo");

        Persona persona1 = new Persona("Franco Opatich", 12345678, "Calle 1", 25, "franco@gmail.com");
        Persona persona2 = new Persona("Juan Perez", 87654321, "Calle 2", 30, "juan@gmail.com");
        Persona persona3 = new Persona("Jose Lopez", 34567890, "Calle 3", 22, "jose@outlook.com");

        Cuenta cuenta1 = new Cuenta(1, "Caja de ahorro", persona1);
        Cuenta cuenta2 = new Cuenta(2, "Cuenta corriente", persona2);
        Cuenta cuenta3 = new Cuenta (3, "Caja de ahorro", persona3);

        banco.registrarCuenta(cuenta1);
        persona1.mostrarPersona();
        banco.registrarCuenta(cuenta2);
        persona2.mostrarPersona();
        banco.registrarCuenta(cuenta3);
        persona3.mostrarPersona();


        cuenta1.depositar(0);
        cuenta1.depositar(10000);
        cuenta2.depositar(5000);
        cuenta3.depositar(7000);

        banco.transferirEntreCuentas(cuenta1, cuenta2, 2000);
        banco.transferirEntreCuentas(cuenta1, cuenta1, 1000);
        banco.transferirEntreCuentas(cuenta3, cuenta2, 1500);

        banco.mostrarReporteDeTresCuentas(cuenta1, cuenta2, cuenta3);
        cuenta1.mostrarHistorialTransferencias();
        cuenta2.mostrarHistorialTransferencias();
        cuenta3.mostrarHistorialTransferencias();
    }
}
