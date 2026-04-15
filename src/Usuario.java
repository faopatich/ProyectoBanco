public class Usuario {
    private String nombre;
    private String dni;
    private String username;
    private String password;

    public Usuario(String nombre, String dni, String username, String password) {
        this.nombre = nombre;
        this.dni = dni;
        this.username = username;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}