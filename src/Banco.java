import java.util.ArrayList;

public class Banco {
    private String nombreBanco;
    private ArrayList<Sucursal> sucursales;
    private ArrayList<Usuario> usuariosSistema;

    public Banco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
        this.sucursales = new ArrayList<Sucursal>();
        this.usuariosSistema = new ArrayList<Usuario>();
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void agregarUsuarioSistema(Usuario usuario) {
        usuariosSistema.add(usuario);
    }

    public ArrayList<Usuario> getUsuariosSistema() {
        return usuariosSistema;
    }

    public boolean existeUsernameSistema(String username) {
        boolean existe = false;
        int i = 0;

        while (i < usuariosSistema.size() && !existe) {
            Usuario actual = usuariosSistema.get(i);

            if (actual.getUsername().equals(username)) {
                existe = true;
            }

            i = i + 1;
        }

        return existe;
    }

    public boolean agregarSucursal(Sucursal sucursal) {
        boolean agregada = false;

        if (buscarSucursalPorCodigo(sucursal.getCodigoSucursal()) == null) {
            sucursales.add(sucursal);
            System.out.println("Sucursal agregada correctamente.");
            agregada = true;
        } else {
            System.out.println("Ya existe una sucursal con ese codigo.");
        }

        return agregada;
    }

    public Sucursal buscarSucursalPorCodigo(String codigoSucursal) {
        Sucursal sucursalEncontrada = null;
        int i = 0;

        while (i < sucursales.size() && sucursalEncontrada == null) {
            Sucursal actual = sucursales.get(i);

            if (actual.getCodigoSucursal().equals(codigoSucursal)) {
                sucursalEncontrada = actual;
            }

            i = i + 1;
        }

        return sucursalEncontrada;
    }

    public Cuenta buscarCuentaEnTodoElBanco(String numeroCuenta) {
        Cuenta cuentaEncontrada = null;
        int i = 0;

        while (i < sucursales.size() && cuentaEncontrada == null) {
            cuentaEncontrada = sucursales.get(i).buscarCuentaPorNumero(numeroCuenta);
            i = i + 1;
        }

        return cuentaEncontrada;
    }

    public Cliente buscarClienteGlobalPorDni(String dni) {
        Cliente clienteEncontrado = null;
        int i = 0;

        while (i < sucursales.size() && clienteEncontrado == null) {
            clienteEncontrado = sucursales.get(i).buscarClientePorDni(dni);
            i = i + 1;
        }

        return clienteEncontrado;
    }

    public boolean existeNumeroCuenta(String numeroCuenta) {
        return buscarCuentaEnTodoElBanco(numeroCuenta) != null;
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
        System.out.println("=== SUCURSALES DEL BANCO " + nombreBanco + " ===");

        if (sucursales.size() == 0) {
            System.out.println("No hay sucursales cargadas.");
        } else {
            int i = 0;

            while (i < sucursales.size()) {
                System.out.println("----------------------------");
                sucursales.get(i).mostrarResumenSucursal();
                i = i + 1;
            }
        }
    }

    public ArrayList<Sucursal> getSucursales() {
        return sucursales;
    }
}