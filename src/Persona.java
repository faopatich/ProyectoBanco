public class Persona {
    private String nombre;
    private String dni;
    private String direccion;
    private int edad;
    private String correo;

    public Persona(String nombre, String dni, String direccion, int edad, String correo) {
        this.nombre = nombre;
        this.dni = dni;
        this.direccion = direccion;
        this.edad = edad;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getEdad() {
        return edad;
    }

    public String getCorreo() {
        return correo;
    }

    public void mostrarPersona() {
        System.out.println("Nombre: " + nombre);
        System.out.println("DNI: " + dni);
        System.out.println("Direccion: " + direccion);
        System.out.println("Edad: " + edad);
        System.out.println("Correo: " + correo);
    }
}

