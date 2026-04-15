public class AdminSucursal extends Usuario {
    private String codigoSucursal;

    public AdminSucursal(String nombre, String dni, String username, String password, String codigoSucursal) {
        super(nombre, dni, username, password);
        this.codigoSucursal = codigoSucursal;
    }

    public String getCodigoSucursal() {
        return codigoSucursal;
    }
}