import java.util.Random;

public class Cliente extends Persona {
    private String codigoCliente;
    private Cuenta cuenta;

    private Cliente(Builder builder) {
        super(
                builder.nombre,
                builder.dni,
                builder.direccion,
                builder.edad,
                builder.correoElectronico
        );

        this.codigoCliente = generarCodigo();
        this.cuenta = null;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void asignarCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public void mostrarCliente() {
        System.out.println("Codigo de cliente: " + codigoCliente);
        mostrarDatos();

        if (cuenta != null) {
            cuenta.mostrarCuenta();
        } else {
            System.out.println("Cuenta: no asignada");
        }
    }

    // 🔥 Generador de código automático
    private String generarCodigo() {
        Random random = new Random();
        int numero = 100000 + random.nextInt(900000); // 6 dígitos
        return "CL-" + numero;
    }

    // 🔧 BUILDER
    public static class Builder {
        private String nombre;
        private String dni;
        private String direccion;
        private int edad;
        private String correoElectronico;

        public Builder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder setDni(String dni) {
            this.dni = dni;
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

        public Builder setCorreoElectronico(String correoElectronico) {
            this.correoElectronico = correoElectronico;
            return this;
        }

        public Cliente build() {
            return new Cliente(this);
        }
    }
}