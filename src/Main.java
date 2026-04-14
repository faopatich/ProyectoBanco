import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Banco banco = new Banco("Banco Administrador");

        int opcionPrincipal = 0;

        while (opcionPrincipal != 6) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Crear sucursal");
            System.out.println("2. Ingresar a una sucursal");
            System.out.println("3. Mostrar sucursales");
            System.out.println("4. Mostrar total del banco");
            System.out.println("5. Mostrar historial global");
            System.out.println("6. Salir");
            System.out.print("Ingrese una opcion: ");

            opcionPrincipal = leerEntero(scanner);

            if (opcionPrincipal == 1) {
                crearSucursal(scanner, banco);
            } else if (opcionPrincipal == 2) {
                entrarASucursal(scanner, banco);
            } else if (opcionPrincipal == 3) {
                banco.mostrarSucursales();
            } else if (opcionPrincipal == 4) {
                System.out.println("Total del banco: $" + banco.calcularTotalBanco());
            } else if (opcionPrincipal == 5) {
                Cuenta.mostrarHistorialGlobal();
            } else if (opcionPrincipal == 6) {
                System.out.println("Saliendo del sistema...");
            } else {
                System.out.println("Opcion invalida.");
            }
        }

        scanner.close();
    }

    public static void crearSucursal(Scanner scanner, Banco banco) {
        System.out.println("\n=== CREAR SUCURSAL ===");

        System.out.print("Codigo de sucursal: ");
        String codigo = scanner.nextLine();

        System.out.print("Nombre de sucursal: ");
        String nombre = scanner.nextLine();

        System.out.print("Direccion: ");
        String direccion = scanner.nextLine();

        if (codigo.equals("") || nombre.equals("") || direccion.equals("")) {
            System.out.println("Todos los campos son obligatorios.");
        } else {
            Sucursal sucursal = new Sucursal(codigo, nombre, direccion);
            banco.agregarSucursal(sucursal);
        }
    }

    public static void entrarASucursal(Scanner scanner, Banco banco) {
        System.out.println("\n=== INGRESAR A SUCURSAL ===");
        System.out.print("Ingrese codigo de sucursal: ");
        String codigo = scanner.nextLine();

        Sucursal sucursal = banco.buscarSucursalPorCodigo(codigo);

        if (sucursal == null) {
            System.out.println("Sucursal no encontrada.");
        } else {
            menuSucursal(scanner, banco, sucursal);
        }
    }

    public static void menuSucursal(Scanner scanner, Banco banco, Sucursal sucursal) {
        int opcionSucursal = 0;

        while (opcionSucursal != 8) {
            System.out.println("\n=== MENU SUCURSAL: " + sucursal.getNombreSucursal() + " ===");
            System.out.println("1. Crear cliente");
            System.out.println("2. Crear cuenta para cliente");
            System.out.println("3. Depositar");
            System.out.println("4. Extraer");
            System.out.println("5. Transferir");
            System.out.println("6. Mostrar clientes");
            System.out.println("7. Mostrar resumen de sucursal");
            System.out.println("8. Volver al menu principal");
            System.out.print("Ingrese una opcion: ");

            opcionSucursal = leerEntero(scanner);

            if (opcionSucursal == 1) {
                crearCliente(scanner, sucursal);
            } else if (opcionSucursal == 2) {
                crearCuenta(scanner, banco, sucursal);
            } else if (opcionSucursal == 3) {
                depositar(scanner, sucursal);
            } else if (opcionSucursal == 4) {
                extraer(scanner, sucursal);
            } else if (opcionSucursal == 5) {
                transferir(scanner, banco, sucursal);
            } else if (opcionSucursal == 6) {
                sucursal.mostrarClientes();
            } else if (opcionSucursal == 7) {
                sucursal.mostrarResumenSucursal();
            } else if (opcionSucursal == 8) {
                System.out.println("Volviendo al menu principal...");
            } else {
                System.out.println("Opcion invalida.");
            }
        }
    }

    public static void crearCliente(Scanner scanner, Sucursal sucursal) {
        System.out.println("\n=== CREAR CLIENTE ===");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("DNI: ");
        String dni = scanner.nextLine();

        System.out.print("Direccion: ");
        String direccion = scanner.nextLine();

        System.out.print("Edad: ");
        int edad = leerEntero(scanner);

        System.out.print("Correo electronico: ");
        String correo = scanner.nextLine();

        boolean datosValidos = true;

        if (nombre.equals("") || dni.equals("") || direccion.equals("") || correo.equals("")) {
            System.out.println("Todos los campos son obligatorios.");
            datosValidos = false;
        }

        if (edad < 18) {
            System.out.println("El cliente debe ser mayor o igual a 18 años.");
            datosValidos = false;
        }

        if (!correo.contains("@")) {
            System.out.println("El correo electronico no es valido.");
            datosValidos = false;
        }

        if (!esDniValido(dni)) {
            System.out.println("El DNI solo debe contener numeros.");
            datosValidos = false;
        }

        if (datosValidos) {
            Cliente cliente = new Cliente.Builder()
                    .setNombre(nombre)
                    .setDni(dni)
                    .setDireccion(direccion)
                    .setEdad(edad)
                    .setCorreoElectronico(correo)
                    .build();

            boolean agregado = sucursal.agregarCliente(cliente);

            if (agregado) {
                System.out.println("Cliente creado con codigo: " + cliente.getCodigoCliente());
            }
        }
    }

    public static void crearCuenta(Scanner scanner, Banco banco, Sucursal sucursal) {
        System.out.println("\n=== CREAR CUENTA ===");

        System.out.print("DNI del cliente: ");
        String dni = scanner.nextLine();

        Cliente cliente = sucursal.buscarClientePorDni(dni);

        if (cliente == null) {
            System.out.println("Cliente no encontrado en esta sucursal.");
        } else {
            if (cliente.getCuenta() != null) {
                System.out.println("El cliente ya tiene una cuenta.");
            } else {
                System.out.print("Numero de cuenta: ");
                String numeroCuenta = scanner.nextLine();

                System.out.print("Tipo de cuenta (Caja de ahorro / Cuenta corriente): ");
                String tipoCuenta = scanner.nextLine();

                boolean datosValidos = true;

                if (numeroCuenta.equals("") || tipoCuenta.equals("")) {
                    System.out.println("Todos los campos son obligatorios.");
                    datosValidos = false;
                }

                if (!tipoCuenta.equalsIgnoreCase("Caja de ahorro")
                        && !tipoCuenta.equalsIgnoreCase("Cuenta corriente")) {
                    System.out.println("Tipo de cuenta invalido.");
                    datosValidos = false;
                }

                if (banco.existeNumeroCuenta(numeroCuenta)) {
                    System.out.println("Ya existe una cuenta con ese numero.");
                    datosValidos = false;
                }

                if (datosValidos) {
                    Cuenta cuenta = new Cuenta(numeroCuenta, tipoCuenta, cliente);
                    cliente.asignarCuenta(cuenta);
                    System.out.println("Cuenta creada correctamente con saldo inicial $0.");
                }
            }
        }
    }

    public static void depositar(Scanner scanner, Sucursal sucursal) {
        System.out.println("\n=== DEPOSITAR ===");

        System.out.print("Numero de cuenta: ");
        String numeroCuenta = scanner.nextLine();

        Cuenta cuenta = sucursal.buscarCuentaPorNumero(numeroCuenta);

        if (cuenta == null) {
            System.out.println("Cuenta no encontrada en esta sucursal.");
        } else {
            System.out.print("Monto a depositar: ");
            double monto = leerDouble(scanner);
            cuenta.depositar(monto);
        }
    }

    public static void extraer(Scanner scanner, Sucursal sucursal) {
        System.out.println("\n=== EXTRAER ===");

        System.out.print("Numero de cuenta: ");
        String numeroCuenta = scanner.nextLine();

        Cuenta cuenta = sucursal.buscarCuentaPorNumero(numeroCuenta);

        if (cuenta == null) {
            System.out.println("Cuenta no encontrada en esta sucursal.");
        } else {
            System.out.print("Monto a extraer: ");
            double monto = leerDouble(scanner);
            cuenta.extraer(monto);
        }
    }

    public static void transferir(Scanner scanner, Banco banco, Sucursal sucursal) {
        System.out.println("\n=== TRANSFERIR ===");

        System.out.print("Cuenta origen: ");
        String cuentaOrigenNumero = scanner.nextLine();

        System.out.print("Cuenta destino: ");
        String cuentaDestinoNumero = scanner.nextLine();

        Cuenta cuentaOrigen = sucursal.buscarCuentaPorNumero(cuentaOrigenNumero);
        Cuenta cuentaDestino = banco.buscarCuentaEnTodoElBanco(cuentaDestinoNumero);

        if (cuentaOrigen == null) {
            System.out.println("La cuenta origen no existe en esta sucursal.");
        } else {
            if (cuentaDestino == null) {
                System.out.println("La cuenta destino no existe en el banco.");
            } else {
                if (cuentaOrigen.getNumeroCuenta().equals(cuentaDestino.getNumeroCuenta())) {
                    System.out.println("La cuenta origen y destino no pueden ser la misma.");
                } else {
                    System.out.print("Monto a transferir: ");
                    double monto = leerDouble(scanner);
                    cuentaOrigen.transferir(cuentaDestino, monto);
                }
            }
        }
    }

    public static boolean esDniValido(String dni) {
        boolean valido = true;
        int i = 0;

        if (dni.equals("")) {
            valido = false;
        }

        while (i < dni.length()) {
            char caracter = dni.charAt(i);

            if (!Character.isDigit(caracter)) {
                valido = false;
            }

            i = i + 1;
        }

        return valido;
    }

    public static int leerEntero(Scanner scanner) {
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

    public static double leerDouble(Scanner scanner) {
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