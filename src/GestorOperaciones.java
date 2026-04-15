import java.util.ArrayList;

public class GestorOperaciones {
    private ArrayList<Operacion> operaciones;

    public GestorOperaciones() {
        this.operaciones = new ArrayList<Operacion>();
    }

    public void depositar(Cliente cliente, double monto) {
        if (cliente.getCuenta() == null) {
            System.out.println("El cliente no tiene cuenta.");
        } else if (monto <= 0) {
            System.out.println("Monto invalido.");
        } else {
            cliente.getCuenta().sumarSaldo(monto);

            Operacion operacion = new Operacion(
                    "DEPOSITO",
                    cliente.getDni(),
                    "-",
                    cliente.getCuenta().getNumeroCuenta(),
                    "-",
                    monto
            );

            operaciones.add(operacion);

            System.out.println("Deposito realizado. Saldo actual: $" + cliente.getCuenta().getSaldo());
        }
    }

    public void extraer(Cliente cliente, double monto) {
        if (cliente.getCuenta() == null) {
            System.out.println("El cliente no tiene cuenta.");
        } else if (monto <= 0) {
            System.out.println("Monto invalido.");
        } else if (monto > cliente.getCuenta().getSaldo()) {
            System.out.println("Saldo insuficiente.");
        } else {
            cliente.getCuenta().restarSaldo(monto);

            Operacion operacion = new Operacion(
                    "EXTRACCION",
                    cliente.getDni(),
                    "-",
                    cliente.getCuenta().getNumeroCuenta(),
                    "-",
                    monto
            );

            operaciones.add(operacion);

            System.out.println("Extraccion realizada. Saldo actual: $" + cliente.getCuenta().getSaldo());
        }
    }

    public void transferir(Cliente origen, Cliente destino, double monto) {
        if (origen.getCuenta() == null) {
            System.out.println("El cliente origen no tiene cuenta.");
        } else if (destino == null || destino.getCuenta() == null) {
            System.out.println("El cliente destino no existe o no tiene cuenta.");
        } else if (monto <= 0) {
            System.out.println("Monto invalido.");
        } else if (monto > origen.getCuenta().getSaldo()) {
            System.out.println("Saldo insuficiente.");
        } else {
            origen.getCuenta().restarSaldo(monto);
            destino.getCuenta().sumarSaldo(monto);

            Operacion operacion = new Operacion(
                    "TRANSFERENCIA",
                    origen.getDni(),
                    destino.getDni(),
                    origen.getCuenta().getNumeroCuenta(),
                    destino.getCuenta().getNumeroCuenta(),
                    monto
            );

            operaciones.add(operacion);

            System.out.println("Transferencia realizada con exito.");
        }
    }

    public void mostrarHistorialCliente(Cliente cliente) {
        System.out.println("=== HISTORIAL DEL CLIENTE " + cliente.getNombre() + " ===");

        boolean encontro = false;
        int i = 0;

        while (i < operaciones.size()) {
            Operacion operacion = operaciones.get(i);

            if (operacion.getDniOrigen().equals(cliente.getDni()) ||
                    operacion.getDniDestino().equals(cliente.getDni())) {
                operacion.mostrarOperacion();
                encontro = true;
            }

            i = i + 1;
        }

        if (!encontro) {
            System.out.println("No hay operaciones.");
        }
    }

    public void mostrarHistorialCompleto() {
        System.out.println("=== HISTORIAL COMPLETO ===");

        if (operaciones.size() == 0) {
            System.out.println("No hay operaciones.");
        } else {
            int i = 0;

            while (i < operaciones.size()) {
                operaciones.get(i).mostrarOperacion();
                i = i + 1;
            }
        }
    }
}