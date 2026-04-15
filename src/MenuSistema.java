import java.util.Scanner;

public class MenuSistema {
    private Scanner scanner;
    private Banco banco;

    public MenuSistema(Scanner scanner, Banco banco) {
        this.scanner = scanner;
        this.banco = banco;
    }

    public void iniciar() {
        int opcionPrincipal = 0;

        while (opcionPrincipal != 3) {
            System.out.println("\n=== SISTEMA BANCARIO ===");
            System.out.println("1. Ingresar como admin");
            System.out.println("2. Ingresar como cliente");
            System.out.println("3. Salir");
            System.out.print("Opcion: ");
            opcionPrincipal = leerEntero();

            if (opcionPrincipal == 1) {
                loginAdmin();
            } else if (opcionPrincipal == 2) {
                loginCliente();
            } else if (opcionPrincipal == 3) {
                System.out.println("Saliendo...");
            } else {
                System.out.println("Opcion invalida.");
            }
        }
    }

    public void loginAdmin() {
        System.out.print("Username admin: ");
        String username = scanner.nextLine();

        System.out.print("Password admin: ");
        String password = scanner.nextLine();

        if (banco.getAdmin().getUsername().equals(username) && banco.getAdmin().getPassword().equals(password)) {
            menuAdmin();
        } else {
            System.out.println("Credenciales incorrectas.");
        }
    }

    public void loginCliente() {
        System.out.print("Username cliente: ");
        String username = scanner.nextLine();

        System.out.print("Password cliente: ");
        String password = scanner.nextLine();

        Cliente cliente = banco.buscarClientePorUsername(username);

        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
        } else if (cliente.getPassword().equals(password)) {
            menuCliente(cliente);
        } else {
            System.out.println("Password incorrecta.");
        }
    }

    public void menuAdmin() {
        int opcion = 0;

        while (opcion != 7) {
            System.out.println("\n=== MENU ADMIN ===");
            System.out.println("1. Ver sucursales");
            System.out.println("2. Crear cliente");
            System.out.println("3. Crear cuenta");
            System.out.println("4. Dar de baja cuenta");
            System.out.println("5. Ver clientes de una sucursal");
            System.out.println("6. Ver total del banco");
            System.out.println("7. Cerrar sesion");
            System.out.print("Opcion: ");
            opcion = leerEntero();

            if (opcion == 1) {
                banco.mostrarSucursales();
            } else if (opcion == 2) {
                crearCliente();
            } else if (opcion == 3) {
                crearCuenta();
            } else if (opcion == 4) {
                darDeBajaCuenta();
            } else if (opcion == 5) {
                mostrarClientesSucursal();
            } else if (opcion == 6) {
                System.out.println("Total del banco: $" + banco.calcularTotalBanco());
            } else if (opcion == 7) {
                System.out.println("Sesion cerrada.");
            } else {
                System.out.println("Opcion invalida.");
            }
        }
    }

    public void menuCliente(Cliente cliente) {
        int opcion = 0;

        while (opcion != 6) {
            System.out.println("\n=== MENU CLIENTE ===");
            System.out.println("1. Ver mi cuenta");
            System.out.println("2. Depositar");
            System.out.println("3. Extraer");
            System.out.println("4. Transferir");
            System.out.println("5. Ver mis datos");
            System.out.println("6. Cerrar sesion");
            System.out.print("Opcion: ");
            opcion = leerEntero();

            if (opcion == 1) {
                if (cliente.getCuenta() != null) {
                    cliente.getCuenta().mostrarCuenta();
                } else {
                    System.out.println("No tiene cuenta.");
                }
            } else if (opcion == 2) {
                if (cliente.getCuenta() != null) {
                    System.out.print("Monto: ");
                    double monto = leerDouble();
                    cliente.getCuenta().depositar(monto);
                } else {
                    System.out.println("No tiene cuenta.");
                }
            } else if (opcion == 3) {
                if (cliente.getCuenta() != null) {
                    System.out.print("Monto: ");
                    double monto = leerDouble();
                    cliente.getCuenta().extraer(monto);
                } else {
                    System.out.println("No tiene cuenta.");
                }
            } else if (opcion == 4) {
                if (cliente.getCuenta() != null) {
                    System.out.print("DNI del cliente destino: ");
                    String dniDestino = scanner.nextLine();

                    Cliente destino = banco.buscarClientePorDni(dniDestino);

                    if (destino == null || destino.getCuenta() == null) {
                        System.out.println("Cliente destino no encontrado o sin cuenta.");
                    } else {
                        System.out.print("Monto: ");
                        double monto = leerDouble();
                        cliente.getCuenta().transferir(destino.getCuenta(), monto);
                    }
                } else {
                    System.out.println("No tiene cuenta.");
                }
            } else if (opcion == 5) {
                cliente.mostrarCliente();
            } else if (opcion == 6) {
                System.out.println("Sesion cerrada.");
            } else {
                System.out.println("Opcion invalida.");
            }
        }
    }

    public void crearCliente() {
        System.out.print("Codigo de sucursal: ");
        String codigoSucursal = scanner.nextLine();

        Sucursal sucursal = banco.buscarSucursalPorCodigo(codigoSucursal);

        if (sucursal == null) {
            System.out.println("Sucursal no encontrada.");
        } else {
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("DNI: ");
            String dni = scanner.nextLine();

            System.out.print("Username: ");
            String username = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            System.out.print("Direccion: ");
            String direccion = scanner.nextLine();

            System.out.print("Edad: ");
            int edad = leerEntero();

            Cliente cliente = UsuarioFactory.crearCliente(nombre, dni, username, password, direccion, edad);
            sucursal.agregarCliente(cliente);

            System.out.println("Cliente creado correctamente.");
        }
    }

    public void crearCuenta() {
        System.out.print("DNI del cliente: ");
        String dni = scanner.nextLine();

        Cliente cliente = banco.buscarClientePorDni(dni);

        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
        } else if (cliente.getCuenta() != null) {
            System.out.println("Ya tiene cuenta.");
        } else {
            System.out.print("Numero de cuenta: ");
            String numero = scanner.nextLine();

            System.out.print("Tipo de cuenta: ");
            String tipo = scanner.nextLine();

            Cuenta cuenta = new Cuenta(numero, tipo);
            cliente.setCuenta(cuenta);

            System.out.println("Cuenta creada correctamente.");
        }
    }

    public void darDeBajaCuenta() {
        System.out.print("DNI del cliente: ");
        String dni = scanner.nextLine();

        Cliente cliente = banco.buscarClientePorDni(dni);

        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
        } else if (cliente.getCuenta() == null) {
            System.out.println("El cliente no tiene cuenta.");
        } else {
            cliente.setCuenta(null);
            System.out.println("Cuenta dada de baja.");
        }
    }

    public void mostrarClientesSucursal() {
        System.out.print("Codigo de sucursal: ");
        String codigo = scanner.nextLine();

        Sucursal sucursal = banco.buscarSucursalPorCodigo(codigo);

        if (sucursal == null) {
            System.out.println("Sucursal no encontrada.");
        } else {
            sucursal.mostrarClientes();
        }
    }

    public int leerEntero() {
        int numero = -1;
        boolean valido = false;

        while (!valido) {
            String texto = scanner.nextLine();

            try {
                numero = Integer.parseInt(texto);
                valido = true;
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un numero entero valido: ");
            }
        }

        return numero;
    }

    public double leerDouble() {
        double numero = -1;
        boolean valido = false;

        while (!valido) {
            String texto = scanner.nextLine();

            try {
                numero = Double.parseDouble(texto);
                valido = true;
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un numero valido: ");
            }
        }

        return numero;
    }
}