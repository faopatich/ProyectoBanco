import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Admin admin = UsuarioFactory.crearAdmin(
                "Administrador General",
                "11111111",
                "admin",
                "1234"
        );

        Banco banco = new Banco("Banco Demo", admin);

        banco.agregarSucursal(new Sucursal("S001", "Casa Central"));
        banco.agregarSucursal(new Sucursal("S002", "Palermo"));

        MenuSistema menuSistema = new MenuSistema(scanner, banco);
        menuSistema.iniciar();

        scanner.close();
    }
}