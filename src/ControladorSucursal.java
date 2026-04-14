public class ControladorSucursal extends Usuario {

    private String codigoSucursalAsignada;

    public ControladorSucursal(
            String id,
            String nombre,
            String dni,
            String correo,
            String username,
            String password,
            String codigoSucursalAsignada
    ) {
        super(id, nombre, dni, correo, username, password);

        this.codigoSucursalAsignada = codigoSucursalAsignada;

        agregarPermiso(Permiso.CREAR_CLIENTE);
        agregarPermiso(Permiso.CREAR_CUENTA);
        agregarPermiso(Permiso.DAR_BAJA_CUENTA);
        agregarPermiso(Permiso.VER_CLIENTES);
        agregarPermiso(Permiso.VER_CUENTAS);
        agregarPermiso(Permiso.VER_TRANSACCIONES);
    }

    public String getCodigoSucursalAsignada() {
        return codigoSucursalAsignada;
    }
}