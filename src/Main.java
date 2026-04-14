import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Banco banco = new Banco("Banco Administrador");
        ValidadorCredenciales validador = new ValidadorCredenciales();

        precargarSucursales(banco);
        precargarUsuariosAdministrativos(banco);

        MenuSistema menuSistema = new MenuSistema(scanner, banco, validador);
        menuSistema.iniciar();

        scanner.close();
    }

    public static void precargarSucursales(Banco banco) {
        banco.agregarSucursal(new Sucursal("S001", "Casa Central", "Av. Corrientes 1000"));
        banco.agregarSucursal(new Sucursal("S002", "Sucursal Palermo", "Av. Santa Fe 3200"));
        banco.agregarSucursal(new Sucursal("S003", "Sucursal Caballito", "Rivadavia 5400"));
    }

    public static void precargarUsuariosAdministrativos(Banco banco) {
        ControladorBanco adminGeneral = new ControladorBanco(
                "U1",
                "Administrador General",
                "11111111",
                "admin@banco.com",
                "admin",
                "1234"
        );

        ControladorSucursal operadorSucursal = new ControladorSucursal(
                "U2",
                "Operador Sucursal",
                "22222222",
                "operador@banco.com",
                "operador",
                "1234"
        );

        banco.agregarUsuarioSistema(adminGeneral);
        banco.agregarUsuarioSistema(operadorSucursal);
    }
}