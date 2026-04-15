import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Banco banco = new Banco("Banco Demo");

        AdminSucursal adminCentral = UsuarioFactory.crearAdminSucursal(
                "Admin Central",
                "11111111",
                "central",
                "1234",
                "S001"
        );

        AdminSucursal adminPalermo = UsuarioFactory.crearAdminSucursal(
                "Admin Palermo",
                "22222222",
                "palermo",
                "1234",
                "S002"
        );

        banco.agregarSucursal(new Sucursal("S001", "Casa Central", adminCentral));
        banco.agregarSucursal(new Sucursal("S002", "Palermo", adminPalermo));

        MenuSistema menuSistema = new MenuSistema(scanner, banco);
        menuSistema.iniciar();

        scanner.close();
    }
}