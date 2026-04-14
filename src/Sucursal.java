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

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public boolean agregarCliente(Cliente cliente) {
        boolean agregado = false;

        if (buscarClientePorDni(cliente.getDni()) == null) {
            if (buscarClientePorCodigo(cliente.getCodigoCliente()) == null) {
                clientes.add(cliente);
                System.out.println("Cliente agregado correctamente a la sucursal " + nombreSucursal + ".");
                agregado = true;
            } else {
                System.out.println("Ya existe un cliente con ese codigo en esta sucursal.");
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
            Cliente clienteActual = clientes.get(i);

            if (clienteActual.getDni().equals(dni)) {
                clienteEncontrado = clienteActual;
            }

            i = i + 1;
        }

        return clienteEncontrado;
    }

    public Cliente buscarClientePorCodigo(String codigoCliente) {
        Cliente clienteEncontrado = null;
        int i = 0;

        while (i < clientes.size() && clienteEncontrado == null) {
            Cliente clienteActual = clientes.get(i);

            if (clienteActual.getCodigoCliente().equals(codigoCliente)) {
                clienteEncontrado = clienteActual;
            }

            i = i + 1;
        }

        return clienteEncontrado;
    }

    public Cuenta buscarCuentaPorNumero(String numeroCuenta) {
        Cuenta cuentaEncontrada = null;
        int i = 0;

        while (i < clientes.size() && cuentaEncontrada == null) {
            Cliente clienteActual = clientes.get(i);

            if (clienteActual.getCuenta() != null) {
                if (clienteActual.getCuenta().getNumeroCuenta().equals(numeroCuenta)) {
                    cuentaEncontrada = clienteActual.getCuenta();
                }
            }

            i = i + 1;
        }

        return cuentaEncontrada;
    }

    public double calcularTotalSucursal() {
        double total = 0;
        int i = 0;

        while (i < clientes.size()) {
            Cliente clienteActual = clientes.get(i);

            if (clienteActual.getCuenta() != null) {
                total = total + clienteActual.getCuenta().getSaldo();
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
        System.out.println("=== DATOS DE SUCURSAL ===");
        System.out.println("Codigo: " + codigoSucursal);
        System.out.println("Nombre: " + nombreSucursal);
        System.out.println("Direccion: " + direccion);
        System.out.println("Cantidad de clientes: " + clientes.size());
        System.out.println("Dinero total en sucursal: $" + calcularTotalSucursal());
    }
    public Cuenta buscarCuentaPorDni(String dni) {
        Cuenta cuenta = null;
        Cliente cliente = buscarClientePorDni(dni);

        if (cliente != null) {
            cuenta = cliente.getCuenta();
        }

        return cuenta;
    }
}