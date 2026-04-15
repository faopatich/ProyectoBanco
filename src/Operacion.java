public class Operacion {
    private String tipo;
    private String dniOrigen;
    private String dniDestino;
    private String cuentaOrigen;
    private String cuentaDestino;
    private double monto;

    public Operacion(String tipo, String dniOrigen, String dniDestino, String cuentaOrigen, String cuentaDestino, double monto) {
        this.tipo = tipo;
        this.dniOrigen = dniOrigen;
        this.dniDestino = dniDestino;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.monto = monto;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDniOrigen() {
        return dniOrigen;
    }

    public String getDniDestino() {
        return dniDestino;
    }

    public String getCuentaOrigen() {
        return cuentaOrigen;
    }

    public String getCuentaDestino() {
        return cuentaDestino;
    }

    public double getMonto() {
        return monto;
    }

    public void mostrarOperacion() {
        System.out.println(
                "Tipo: " + tipo +
                        " | Cuenta origen: " + cuentaOrigen +
                        " | Cuenta destino: " + cuentaDestino +
                        " | Monto: $" + monto
        );
    }
}