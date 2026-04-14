public class ControladorSucursal extends Usuario {

    public ControladorSucursal(String id, String nombre, String dni, String correo, String username, String password) {
        super(id, nombre, dni, correo, username, password);

        agregarPermiso(Permiso.VER_SUCURSALES);
        agregarPermiso(Permiso.CREAR_CLIENTE);
        agregarPermiso(Permiso.CREAR_CUENTA);
        agregarPermiso(Permiso.VER_CLIENTES);
        agregarPermiso(Permiso.VER_CUENTAS);
        agregarPermiso(Permiso.VER_TRANSACCIONES);
    }
}