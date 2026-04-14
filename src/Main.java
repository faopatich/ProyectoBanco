import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Banco banco = new Banco("Banco Administrador");
        ValidadorCredenciales validador = new ValidadorCredenciales();

        precargarSucursalesYAdmins(banco);
        precargarAdminGeneral(banco);

        MenuSistema menuSistema = new MenuSistema(scanner, banco, validador);
        menuSistema.iniciar();

        scanner.close();
    }

    public static void precargarSucursalesYAdmins(Banco banco) {
        Sucursal casaCentral = new Sucursal("S001", "Casa Central", "Av. Corrientes 1000");
        Sucursal palermo = new Sucursal("S002", "Sucursal Palermo", "Av. Santa Fe 3200");
        Sucursal caballito = new Sucursal("S003", "Sucursal Caballito", "Rivadavia 5400");

        ControladorSucursal adminCasaCentral = new ControladorSucursal(
                "AS1",
                "Admin Casa Central",
                "20111111",
                "casacentral@banco.com",
                "central",
                "1234",
                "S001"
        );

        ControladorSucursal adminPalermo = new ControladorSucursal(
                "AS2",
                "Admin Palermo",
                "20222222",
                "palermo@banco.com",
                "palermo",
                "1234",
                "S002"
        );

        ControladorSucursal adminCaballito = new ControladorSucursal(
                "AS3",
                "Admin Caballito",
                "20333333",
                "caballito@banco.com",
                "caballito",
                "1234",
                "S003"
        );

        casaCentral.asignarAdministradorSucursal(adminCasaCentral);
        palermo.asignarAdministradorSucursal(adminPalermo);
        caballito.asignarAdministradorSucursal(adminCaballito);

        banco.agregarSucursal(casaCentral);
        banco.agregarSucursal(palermo);
        banco.agregarSucursal(caballito);

        banco.agregarUsuarioSistema(adminCasaCentral);
        banco.agregarUsuarioSistema(adminPalermo);
        banco.agregarUsuarioSistema(adminCaballito);
    }

    public static void precargarAdminGeneral(Banco banco) {
        ControladorBanco adminGeneral = new ControladorBanco(
                "U1",
                "Administrador General",
                "11111111",
                "admin@banco.com",
                "admin",
                "1234"
        );

        banco.agregarUsuarioSistema(adminGeneral);
    }
}