public class Persona {
    private String nombre;
    private int dni;
    private String direccion;
    private int edad;
    private String correo;

    public Persona(String nombre, int dni, String direccion, int edad, String correo) {
        this.nombre = nombre;
        this.dni = dni;
        this.direccion = direccion;
        this.edad = edad;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }


    public void mostrarPersona() {
        System.out.println("--------------------------");
        System.out.println("=== DATOS DEL TITULAR ===");
        System.out.println("Nombre: " + nombre);
        System.out.println("DNI: " + dni);
        System.out.println("Direccion: " + direccion);
        System.out.println("Edad: " + edad);
        System.out.println("Correo: " + correo);
        System.out.println("--------------------------");
    }
}

