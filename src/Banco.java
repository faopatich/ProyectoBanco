import java.util.ArrayList;

public class Banco {
    private String nombre;
    private ArrayList<Sucursal> sucursales;
    private GestorOperaciones gestorOperaciones;

    public Banco(String nombre) {
        this.nombre = nombre;
        this.sucursales = new ArrayList<Sucursal>();
        this.gestorOperaciones = new GestorOperaciones();
    }

    public String getNombre() {
        return nombre;
    }

    public GestorOperaciones getGestorOperaciones() {
        return gestorOperaciones;
    }

    public void agregarSucursal(Sucursal sucursal) {
        sucursales.add(sucursal);
    }

    public ArrayList<Sucursal> getSucursales() {
        return sucursales;
    }

    public Sucursal buscarSucursalPorCodigo(String codigo) {
        Sucursal sucursalEncontrada = null;
        int i = 0;

        while (i < sucursales.size() && sucursalEncontrada == null) {
            if (sucursales.get(i).getCodigo().equals(codigo)) {
                sucursalEncontrada = sucursales.get(i);
            }
            i = i + 1;
        }

        return sucursalEncontrada;
    }

    public Cliente buscarClientePorUsername(String username) {
        Cliente clienteEncontrado = null;
        int i = 0;

        while (i < sucursales.size() && clienteEncontrado == null) {
            clienteEncontrado = sucursales.get(i).buscarClientePorUsername(username);
            i = i + 1;
        }

        return clienteEncontrado;
    }

    public Cliente buscarClientePorDni(String dni) {
        Cliente clienteEncontrado = null;
        int i = 0;

        while (i < sucursales.size() && clienteEncontrado == null) {
            clienteEncontrado = sucursales.get(i).buscarClientePorDni(dni);
            i = i + 1;
        }

        return clienteEncontrado;
    }
    public Cliente buscarClientePorCuenta(Cuenta cuenta) {
        Cliente clienteEncontrado = null;
        int i = 0;

        while (i < sucursales.size() && clienteEncontrado == null) {
            Sucursal sucursal = sucursales.get(i);

            int j = 0;
            while (j < sucursal.getClientes().size() && clienteEncontrado == null) {
                Cliente cliente = sucursal.getClientes().get(j);

                if (cliente.getCuenta() != null) {
                    if (cliente.getCuenta().equals(cuenta)) {
                        clienteEncontrado = cliente;
                    }
                }

                j = j + 1;
            }

            i = i + 1;
        }

        return clienteEncontrado;
    }

    public Sucursal buscarSucursalPorAdminUsername(String username) {
        Sucursal sucursalEncontrada = null;
        int i = 0;

        while (i < sucursales.size() && sucursalEncontrada == null) {
            if (sucursales.get(i).getAdmin().getUsername().equals(username)) {
                sucursalEncontrada = sucursales.get(i);
            }
            i = i + 1;
        }

        return sucursalEncontrada;
    }

    public double calcularTotalBanco() {
        double total = 0;
        int i = 0;

        while (i < sucursales.size()) {
            total = total + sucursales.get(i).calcularTotalSucursal();
            i = i + 1;
        }

        return total;
    }

    public void mostrarSucursales() {
        int i = 0;

        while (i < sucursales.size()) {
            System.out.println(
                    sucursales.get(i).getCodigo() +
                            " - " + sucursales.get(i).getNombre() +
                            " | Admin: " + sucursales.get(i).getAdmin().getUsername()
            );
            i = i + 1;
        }
    }
    public Cuenta buscarCuentaPorNumero(String numeroCuenta) {
        Cuenta cuentaEncontrada = null;
        int i = 0;

        while (i < sucursales.size() && cuentaEncontrada == null) {
            Sucursal sucursal = sucursales.get(i);

            int j = 0;
            while (j < sucursal.getClientes().size() && cuentaEncontrada == null) {
                Cliente cliente = sucursal.getClientes().get(j);

                if (cliente.getCuenta() != null) {
                    if (cliente.getCuenta().getNumeroCuenta().equals(numeroCuenta)) {
                        cuentaEncontrada = cliente.getCuenta();
                    }
                }

                j = j + 1;
            }

            i = i + 1;
        }

        return cuentaEncontrada;
    }
}