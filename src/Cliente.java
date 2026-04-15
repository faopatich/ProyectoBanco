public class Cliente extends Usuario {

    private String direccion;
    private int edad;
    private Cuenta cuenta;

    private Cliente(Builder builder) {
        super(builder.nombre, builder.dni, builder.username, builder.password);
        this.direccion = builder.direccion;
        this.edad = builder.edad;
        this.cuenta = null;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getEdad() {
        return edad;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public void mostrarCliente() {
        System.out.println("Nombre: " + getNombre());
        System.out.println("DNI: " + getDni());
        System.out.println("Direccion: " + direccion);
        System.out.println("Edad: " + edad);
        System.out.println("Username: " + getUsername());

        if (cuenta != null) {
            cuenta.mostrarCuenta();
        } else {
            System.out.println("No tiene cuenta asignada.");
        }
    }

    public static class Builder {
        private String nombre;
        private String dni;
        private String username;
        private String password;
        private String direccion;
        private int edad;

        public Builder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder setDni(String dni) {
            this.dni = dni;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setDireccion(String direccion) {
            this.direccion = direccion;
            return this;
        }

        public Builder setEdad(int edad) {
            this.edad = edad;
            return this;
        }

        public Cliente build() {
            return new Cliente(this);
        }
    }
}