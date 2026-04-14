import java.util.ArrayList;

public class Banco {
    private String nombreBanco;
    private ArrayList<Sucursal> sucursales;

    public Banco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
        this.sucursales = new ArrayList<Sucursal>();
    }

    public String getNombreBanco() {
        return nombreBanco;
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
            Sucursal sucursalActual = sucursales.get(i);

            if (sucursalActual.getCodigoSucursal().equals(codigoSucursal)) {
                sucursalEncontrada = sucursalActual;
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

    public boolean existeNumeroCuenta(String numeroCuenta) {
        boolean existe = false;

        if (buscarCuentaEnTodoElBanco(numeroCuenta) != null) {
            existe = true;
        }

        return existe;
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
}
