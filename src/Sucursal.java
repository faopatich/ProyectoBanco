import java.util.ArrayList;

public class Sucursal {
    private String codigoSucursal;
    private String nombreSucursal;
    private String direccion;
    private ArrayList<Cliente> clientes;

    public Sucursal(String codigoSucursal, String nombreSucursal, String direccion) {
        this.codigoSucursal = codigoSucursal;
        this.nombreSucursal = nombreSucursal;
        this.direccion = direccion;
        this.clientes = new ArrayList<Cliente>();
    }

    public String getCodigoSucursal() {
        return codigoSucursal;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public String getDireccion() {
        return direccion;
    }

    public boolean agregarCliente(Cliente cliente) {
        boolean agregado = false;

        if (buscarClientePorDni(cliente.getDni()) == null) {
            if (buscarClientePorUsername(cliente.getUsername()) == null) {
                clientes.add(cliente);
                System.out.println("Cliente agregado correctamente a la sucursal " + nombreSucursal + ".");
                agregado = true;
            } else {
                System.out.println("Ya existe un cliente con ese username en esta sucursal.");
            }
        } else {
            System.out.println("Ya existe un cliente con ese DNI en esta sucursal.");
        }

        return agregado;
    }

    public Cliente buscarClientePorDni(String dni) {
        Cliente clienteEncontrado = null;
        int i = 0;

        while (i < clientes.size() && clienteEncontrado == null) {
            Cliente actual = clientes.get(i);

            if (actual.getDni().equals(dni)) {
                clienteEncontrado = actual;
            }

            i = i + 1;
        }

        return clienteEncontrado;
    }

    public Cliente buscarClientePorUsername(String username) {
        Cliente clienteEncontrado = null;
        int i = 0;

        while (i < clientes.size() && clienteEncontrado == null) {
            Cliente actual = clientes.get(i);

            if (actual.getUsername().equals(username)) {
                clienteEncontrado = actual;
            }

            i = i + 1;
        }

        return clienteEncontrado;
    }

    public Cuenta buscarCuentaPorNumero(String numeroCuenta) {
        Cuenta cuentaEncontrada = null;
        int i = 0;

        while (i < clientes.size() && cuentaEncontrada == null) {
            Cliente actual = clientes.get(i);

            if (actual.getCuenta() != null) {
                if (actual.getCuenta().getNumeroCuenta().equals(numeroCuenta)) {
                    cuentaEncontrada = actual.getCuenta();
                }
            }

            i = i + 1;
        }

        return cuentaEncontrada;
    }

    public Cuenta buscarCuentaPorDni(String dni) {
        Cuenta cuenta = null;
        Cliente cliente = buscarClientePorDni(dni);

        if (cliente != null) {
            cuenta = cliente.getCuenta();
        }

        return cuenta;
    }

    public double calcularTotalSucursal() {
        double total = 0;
        int i = 0;

        while (i < clientes.size()) {
            Cliente actual = clientes.get(i);

            if (actual.getCuenta() != null) {
                total = total + actual.getCuenta().getSaldo();
            }

            i = i + 1;
        }

        return total;
    }

    public void mostrarClientes() {
        System.out.println("=== CLIENTES DE LA SUCURSAL " + nombreSucursal + " ===");

        if (clientes.size() == 0) {
            System.out.println("No hay clientes cargados.");
        } else {
            int i = 0;

            while (i < clientes.size()) {
                System.out.println("----------------------------");
                clientes.get(i).mostrarCliente();
                i = i + 1;
            }
        }
    }

    public void mostrarResumenSucursal() {
        System.out.println("=== RESUMEN SUCURSAL ===");
        System.out.println("Codigo: " + codigoSucursal);
        System.out.println("Nombre: " + nombreSucursal);
        System.out.println("Direccion: " + direccion);
        System.out.println("Cantidad de clientes: " + clientes.size());
        System.out.println("Total en sucursal: $" + calcularTotalSucursal());
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }
}