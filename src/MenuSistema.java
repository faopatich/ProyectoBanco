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

        while (opcionPrincipal != 4) {
            System.out.println("\n=== SISTEMA BANCARIO ===");
            System.out.println("1. Ingresar como admin general");
            System.out.println("2. Ingresar como admin de sucursal");
            System.out.println("3. Ingresar como cliente");
            System.out.println("4. Salir");
            System.out.print("Opcion: ");
            opcionPrincipal = leerEntero();

            if (opcionPrincipal == 1) {
                loginAdminGeneral();
            } else if (opcionPrincipal == 2) {
                loginAdminSucursal();
            } else if (opcionPrincipal == 3) {
                loginCliente();
            } else if (opcionPrincipal == 4) {
                System.out.println("Saliendo...");
            } else {
                System.out.println("Opcion invalida.");
            }
        }
    }

    public void loginAdminGeneral() {
        System.out.print("Username admin general: ");
        String username = scanner.nextLine();

        System.out.print("Password admin general: ");
        String password = scanner.nextLine();

        if (username.equals("admin") && password.equals("1234")) {
            menuAdminGeneral();
        } else {
            System.out.println("Credenciales incorrectas.");
        }
    }

    public void loginAdminSucursal() {
        System.out.print("Username admin sucursal: ");
        String username = scanner.nextLine();

        System.out.print("Password admin sucursal: ");
        String password = scanner.nextLine();

        Sucursal sucursal = banco.buscarSucursalPorAdminUsername(username);

        if (sucursal == null) {
            System.out.println("Admin de sucursal no encontrado.");
        } else if (sucursal.getAdmin().getPassword().equals(password)) {
            menuAdminSucursal(sucursal);
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

    public void menuAdminGeneral() {
        int opcion = 0;

        while (opcion != 7) {
            System.out.println("\n=== MENU ADMIN GENERAL ===");
            System.out.println("1. Ver sucursales");
            System.out.println("2. Crear cliente");
            System.out.println("3. Crear cuenta");
            System.out.println("4. Dar de baja cuenta");
            System.out.println("5. Ver clientes de una sucursal");
            System.out.println("6. Ver historial de todas las operaciones");
            System.out.println("7. Cerrar sesion");
            System.out.print("Opcion: ");
            opcion = leerEntero();

            if (opcion == 1) {
                banco.mostrarSucursales();
            } else if (opcion == 2) {
                crearClienteGeneral();
            } else if (opcion == 3) {
                crearCuentaGeneral();
            } else if (opcion == 4) {
                darDeBajaCuentaGeneral();
            } else if (opcion == 5) {
                mostrarClientesSucursal();
            } else if (opcion == 6) {
                banco.getGestorOperaciones().mostrarHistorialCompleto();
            } else if (opcion == 7) {
                System.out.println("Sesion cerrada.");
            } else {
                System.out.println("Opcion invalida.");
            }
        }
    }

    public void menuAdminSucursal(Sucursal sucursal) {
        int opcion = 0;

        while (opcion != 6) {
            System.out.println("\n=== MENU ADMIN SUCURSAL " + sucursal.getNombre() + " ===");
            System.out.println("1. Crear cliente");
            System.out.println("2. Crear cuenta");
            System.out.println("3. Dar de baja cuenta");
            System.out.println("4. Ver clientes");
            System.out.println("5. Ver historial de todas las operaciones");
            System.out.println("6. Cerrar sesion");
            System.out.print("Opcion: ");
            opcion = leerEntero();

            if (opcion == 1) {
                crearClienteEnSucursal(sucursal);
            } else if (opcion == 2) {
                crearCuentaEnSucursal(sucursal);
            } else if (opcion == 3) {
                darDeBajaCuentaEnSucursal(sucursal);
            } else if (opcion == 4) {
                sucursal.mostrarClientes();
            } else if (opcion == 5) {
                banco.getGestorOperaciones().mostrarHistorialCompleto();
            } else if (opcion == 6) {
                System.out.println("Sesion cerrada.");
            } else {
                System.out.println("Opcion invalida.");
            }
        }
    }

    public void menuCliente(Cliente cliente) {
        int opcion = 0;

        while (opcion != 7) {
            System.out.println("\n=== MENU CLIENTE ===");
            System.out.println("1. Ver mi cuenta");
            System.out.println("2. Depositar");
            System.out.println("3. Extraer");
            System.out.println("4. Transferir");
            System.out.println("5. Ver mis datos");
            System.out.println("6. Ver mi historial");
            System.out.println("7. Cerrar sesion");
            System.out.print("Opcion: ");
            opcion = leerEntero();

            if (opcion == 1) {
                if (cliente.getCuenta() != null) {
                    cliente.getCuenta().mostrarCuenta();
                } else {
                    System.out.println("No tiene cuenta.");
                }
            } else if (opcion == 2) {
                System.out.print("Monto: ");
                double monto = leerDouble();
                banco.getGestorOperaciones().depositar(cliente, monto);
            } else if (opcion == 3) {
                System.out.print("Monto: ");
                double monto = leerDouble();
                banco.getGestorOperaciones().extraer(cliente, monto);
            } else if (opcion == 4) {
                System.out.print("Numero de cuenta destino: ");
                String numeroCuentaDestino = scanner.nextLine();

                Cuenta cuentaDestino = banco.buscarCuentaPorNumero(numeroCuentaDestino);

                System.out.print("Monto: ");
                double monto = leerDouble();

                if (cuentaDestino == null) {
                    System.out.println("Cuenta destino no encontrada.");
                } else {
                    Cliente clienteDestino = banco.buscarClientePorCuenta(cuentaDestino);

                    banco.getGestorOperaciones().transferir(cliente, clienteDestino, monto);
                }
            } else if (opcion == 5) {
                cliente.mostrarCliente();
            } else if (opcion == 6) {
                banco.getGestorOperaciones().mostrarHistorialCliente(cliente);
            } else if (opcion == 7) {
                System.out.println("Sesion cerrada.");
            } else {
                System.out.println("Opcion invalida.");
            }
        }
    }

    public void crearClienteGeneral() {
        System.out.print("Codigo de sucursal: ");
        String codigoSucursal = scanner.nextLine();

        Sucursal sucursal = banco.buscarSucursalPorCodigo(codigoSucursal);

        if (sucursal == null) {
            System.out.println("Sucursal no encontrada.");
        } else {
            crearClienteEnSucursal(sucursal);
        }
    }

    public void crearClienteEnSucursal(Sucursal sucursal) {
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

        System.out.println("Cliente creado correctamente");
    }

    public void crearCuentaGeneral() {
        System.out.print("DNI del cliente: ");
        String dni = scanner.nextLine();

        Cliente cliente = banco.buscarClientePorDni(dni);

        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
        } else if (cliente.getCuenta() != null) {
            System.out.println("Ya tiene cuenta.");
        } else {
            System.out.print("Tipo de cuenta: ");
            String tipo = scanner.nextLine();

            cliente.setCuenta(new Cuenta(tipo));
            System.out.println("Cuenta creada con numero: " + cliente.getCuenta().getNumeroCuenta());
        }
    }
    public void crearCuentaEnSucursal(Sucursal sucursal) {
        System.out.print("DNI del cliente: ");
        String dni = scanner.nextLine();

        Cliente cliente = sucursal.buscarClientePorDni(dni);

        if (cliente == null) {
            System.out.println("Cliente no encontrado en esta sucursal.");
        } else if (cliente.getCuenta() != null) {
            System.out.println("Ya tiene cuenta.");
        } else {
            System.out.print("Tipo de cuenta: ");
            String tipo = scanner.nextLine();

            cliente.setCuenta(new Cuenta(tipo));
            System.out.println("Cuenta creada con numero: " + cliente.getCuenta().getNumeroCuenta());
        }
    }

    public void darDeBajaCuentaGeneral() {
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

    public void darDeBajaCuentaEnSucursal(Sucursal sucursal) {
        System.out.print("DNI del cliente: ");
        String dni = scanner.nextLine();

        Cliente cliente = sucursal.buscarClientePorDni(dni);

        if (cliente == null) {
            System.out.println("Cliente no encontrado en esta sucursal.");
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