import java.util.HashSet;
import java.util.Set;

public abstract class Usuario {
    private String id;
    private String nombre;
    private String dni;
    private String correo;
    private String username;
    private String password;
    private Set<Permiso> permisos;

    public Usuario(String id, String nombre, String dni, String correo, String username, String password) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.correo = correo;
        this.username = username;
        this.password = password;
        this.permisos = new HashSet<Permiso>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getCorreo() {
        return correo;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void agregarPermiso(Permiso permiso) {
        permisos.add(permiso);
    }

    public boolean tienePermiso(Permiso permiso) {
        return permisos.contains(permiso);
    }
}