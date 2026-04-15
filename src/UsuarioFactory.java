public class UsuarioFactory {

    public static Admin crearAdmin(String nombre, String dni, String username, String password) {
        return new Admin(nombre, dni, username, password);
    }

    public static Cliente crearCliente(String nombre, String dni, String username, String password, String direccion, int edad) {
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