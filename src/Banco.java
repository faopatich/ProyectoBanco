import java.util.ArrayList;

public class Banco {
    private String nombre;
    private ArrayList<Sucursal> sucursales;
    private Admin admin;

    public Banco(String nombre, Admin admin) {
        this.nombre = nombre;
        this.admin = admin;
        this.sucursales = new ArrayList<Sucursal>();
    }

    public String getNombre() {
        return nombre;
    }

    public Admin getAdmin() {
        return admin;
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
            System.out.println(sucursales.get(i).getCodigo() + " - " + sucursales.get(i).getNombre());
            i = i + 1;
        }
    }
}