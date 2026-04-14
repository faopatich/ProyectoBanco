import java.util.Random;

public class Cliente extends Usuario {
    private String codigoCliente;
    private String direccion;
    private int edad;
    private Cuenta cuenta;

    private Cliente(Builder builder) {
        super(
                builder.id,
                builder.nombre,
                builder.dni,
                builder.correo,
                builder.username,
                builder.password
        );

        this.direccion = builder.direccion;
        this.edad = builder.edad;
        this.codigoCliente = generarCodigoCliente();
        this.cuenta = null;

        agregarPermiso(Permiso.DEPOSITAR_PROPIO);
        agregarPermiso(Permiso.EXTRAER_PROPIO);
        agregarPermiso(Permiso.TRANSFERIR_PROPIO);
    }

    private String generarCodigoCliente() {
        Random random = new Random();
        int numero = 100000 + random.nextInt(900000);
        return "CL-" + numero;
    }

    public String getCodigoCliente() {
        return codigoCliente;
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

    public void asignarCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
    public void darDeBajaCuenta() {
        this.cuenta = null;
    }

    public void mostrarCliente() {
        System.out.println("Codigo de cliente: " + codigoCliente);
        System.out.println("Nombre: " + getNombre());
        System.out.println("DNI: " + getDni());
        System.out.println("Direccion: " + direccion);
        System.out.println("Edad: " + edad);
        System.out.println("Correo: " + getCorreo());
        System.out.println("Username: " + getUsername());

        if (cuenta != null) {
            cuenta.mostrarCuenta();
        } else {
            System.out.println("Cuenta: no asignada");
        }
    }

    public static class Builder {
        private String id;
        private String nombre;
        private String dni;
        private String correo;
        private String username;
        private String password;
        private String direccion;
        private int edad;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder setDni(String dni) {
            this.dni = dni;
            return this;
        }

        public Builder setCorreo(String correo) {
            this.correo = correo;
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