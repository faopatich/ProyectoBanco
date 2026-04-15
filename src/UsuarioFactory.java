public class UsuarioFactory {

    public static AdminSucursal crearAdminSucursal(
            String nombre,
            String dni,
            String username,
            String password,
            String codigoSucursal
    ) {
        return new AdminSucursal(nombre, dni, username, password, codigoSucursal);
    }

    public static Cliente crearCliente(
            String nombre,
            String dni,
            String username,
            String password,
            String direccion,
            int edad
    ) {
        return new Cliente.Builder()
                .setNombre(nombre)
                .setDni(dni)
                .setUsername(username)
                .setPassword(password)
                .setDireccion(direccion)
                .setEdad(edad)
                .build();
    }
}