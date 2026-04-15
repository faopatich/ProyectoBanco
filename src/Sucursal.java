import java.util.ArrayList;

public class Sucursal {
    private String codigo;
    private String nombre;
    private ArrayList<Cliente> clientes;

    public Sucursal(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.clientes = new ArrayList<Cliente>();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public Cliente buscarClientePorDni(String dni) {
        Cliente clienteEncontrado = null;
        int i = 0;

        while (i < clientes.size() && clienteEncontrado == null) {
            if (clientes.get(i).getDni().equals(dni)) {
                clienteEncontrado = clientes.get(i);
            }
            i = i + 1;
        }

        return clienteEncontrado;
    }

    public Cliente buscarClientePorUsername(String username) {
        Cliente clienteEncontrado = null;
        int i = 0;

        while (i < clientes.size() && clienteEncontrado == null) {
            if (clientes.get(i).getUsername().equals(username)) {
                clienteEncontrado = clientes.get(i);
            }
            i = i + 1;
        }

        return clienteEncontrado;
    }

    public void mostrarClientes() {
        if (clientes.size() == 0) {
            System.out.println("No hay clientes.");
        } else {
            int i = 0;
            while (i < clientes.size()) {
                System.out.println("-------------------");
                clientes.get(i).mostrarCliente();
                i = i + 1;
            }
        }
    }

    public double calcularTotalSucursal() {
        double total = 0;
        int i = 0;

        while (i < clientes.size()) {
            if (clientes.get(i).getCuenta() != null) {
                total = total + clientes.get(i).getCuenta().getSaldo();
            }
            i = i + 1;
        }

        return total;
    }
}