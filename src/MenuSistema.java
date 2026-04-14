import java.util.Scanner;

public class MenuSistema {

    private Scanner scanner;
    private Banco banco;
    private ValidadorCredenciales validador;

    public MenuSistema(Scanner scanner, Banco banco, ValidadorCredenciales validador) {
        this.scanner = scanner;
        this.banco = banco;
        this.validador = validador;
    }

    public void iniciar() {
        boolean sistemaActivo = true;

        while (sistemaActivo) {
            System.out.println("\n=== LOGIN ===");
            System.out.print("Usuario: ");
            String username = scanner.nextLine();

            System.out.print("Contrasena: ");
            String password = scanner.nextLine();

            Usuario usuarioLogueado = validador.autenticar(username, password, banco.getUsuariosSistema());

            if (usuarioLogueado == null) {
                System.out.println("Credenciales incorrectas.");
            } else {
                System.out.println("Bienvenido/a " + usuarioLogueado.getNombre());

                if (usuarioLogueado instanceof Cliente) {
                    menuCliente((Cliente) usuarioLogueado);
                } else {
                    menuAdministrativo(usuarioLogueado);
                }
            }

            System.out.print("\nDesea cerrar el sistema? (si/no): ");
            String respuesta = scanner.nextLine();

            if (respuesta.equalsIgnoreCase("si")) {
                sistemaActivo = false;
            }
        }
    }

    public void menuAdministrativo(Usuario usuarioLogueado) {
        int opcion = 0;

        while (opcion != 8) {
            System.out.println("\n=== MENU ADMINISTRATIVO ===");

            if (usuarioLogueado instanceof ControladorBanco) {
                System.out.println("1. Ver sucursales");
                System.out.println("2. Ingresar a sucursal");
                System.out.println("3. Ver historial global");
                System.out.println("4. Ver total del banco");
                System.out.println("8. Cerrar sesion");
                System.out.print("Ingrese una opcion: ");

                opcion = leerEntero();

                if (opcion == 1) {
                    banco.mostrarSucursales();
                } else if (opcion == 2) {
                    entrarASucursal(usuarioLogueado);
                } else if (opcion == 3) {
                    Cuenta.mostrarHistorialGlobal();
                } else if (opcion == 4) {
                    System.out.println("Total del banco: $" + banco.calcularTotalBanco());
                } else if (opcion == 8) {
                    System.out.println("Sesion cerrada.");
                } else {
                    System.out.println("Opcion invalida.");
                }

            } else if (usuarioLogueado instanceof ControladorSucursal) {
                System.out.println("1. Ingresar a mi sucursal");
                System.out.println("8. Cerrar sesion");
                System.out.print("Ingrese una opcion: ");

                opcion = leerEntero();

                if (opcion == 1) {
                    entrarASucursal(usuarioLogueado);
                } else if (opcion == 8) {
                    System.out.println("Sesion cerrada.");
                } else {
                    System.out.println("Opcion invalida.");
                }
            }
        }
    }
    public void menuCliente(Cliente clienteLogueado) {
        int opcion = 0;

        while (opcion != 6) {
            System.out.println("\n=== MENU CLIENTE ===");
            System.out.println("1. Ver mi cuenta");
            System.out.println("2. Ver mi historial");
            System.out.println("3. Depositar dinero");
            System.out.println("4. Extraer dinero");
            System.out.println("5. Transferir dinero");
            System.out.println("6. Cerrar sesion");
            System.out.print("Ingrese una opcion: ");

            opcion = leerEntero();

            if (opcion == 1) {
                if (clienteLogueado.getCuenta() != null) {
                    clienteLogueado.getCuenta().mostrarCuenta();
                } else {
                    System.out.println("Todavia no tiene una cuenta asignada.");
                }
            } else if (opcion == 2) {
                if (clienteLogueado.getCuenta() != null) {
                    clienteLogueado.getCuenta().mostrarHistorialPropio();
                } else {
                    System.out.println("Todavia no tiene una cuenta asignada.");
                }
            } else if (opcion == 3) {
                if (clienteLogueado.tienePermiso(Permiso.DEPOSITAR_PROPIO)) {
                    depositarCliente(clienteLogueado);
                } else {
                    System.out.println("No tiene permiso.");
                }
            } else if (opcion == 4) {
                if (clienteLogueado.tienePermiso(Permiso.EXTRAER_PROPIO)) {
                    extraerCliente(clienteLogueado);
                } else {
                    System.out.println("No tiene permiso.");
                }
            } else if (opcion == 5) {
                if (clienteLogueado.tienePermiso(Permiso.TRANSFERIR_PROPIO)) {
                    transferirCliente(clienteLogueado);
                } else {
                    System.out.println("No tiene permiso.");
                }
            } else if (opcion == 6) {
                System.out.println("Sesion cerrada.");
            } else {
                System.out.println("Opcion invalida.");
            }
        }
    }

    public boolean puedeEntrarASucursal(Usuario usuario) {
        return usuario.tienePermiso(Permiso.CREAR_CLIENTE)
                || usuario.tienePermiso(Permiso.CREAR_CUENTA)
                || usuario.tienePermiso(Permiso.DAR_BAJA_CUENTA)
                || usuario.tienePermiso(Permiso.VER_CLIENTES)
                || usuario.tienePermiso(Permiso.VER_CUENTAS);
    }


    public void entrarASucursal(Usuario usuarioLogueado) {
        if (usuarioLogueado instanceof ControladorBanco) {
            System.out.println("\n=== INGRESAR A SUCURSAL ===");
            System.out.print("Codigo de sucursal: ");
            String codigo = scanner.nextLine();

            Sucursal sucursal = banco.buscarSucursalPorCodigo(codigo);

            if (sucursal == null) {
                System.out.println("Sucursal no encontrada.");
            } else {
                menuSucursal(sucursal, usuarioLogueado);
            }

        } else if (usuarioLogueado instanceof ControladorSucursal) {
            ControladorSucursal adminSucursal = (ControladorSucursal) usuarioLogueado;
            Sucursal sucursal = banco.buscarSucursalPorCodigo(adminSucursal.getCodigoSucursalAsignada());

            if (sucursal == null) {
                System.out.println("La sucursal asignada no existe.");
            } else {
                menuSucursal(sucursal, usuarioLogueado);
            }
        }
    }

    public void menuSucursal(Sucursal sucursal, Usuario usuarioLogueado) {
        int opcion = 0;

        if (usuarioLogueado instanceof ControladorSucursal) {
            ControladorSucursal adminSucursal = (ControladorSucursal) usuarioLogueado;

            if (!adminSucursal.getCodigoSucursalAsignada().equals(sucursal.getCodigoSucursal())) {
                System.out.println("No tiene permiso para acceder a esta sucursal.");
                return;
            }
        }

        while (opcion != 6) {
            System.out.println("\n=== MENU SUCURSAL: " + sucursal.getNombreSucursal() + " ===");

            if (usuarioLogueado.tienePermiso(Permiso.CREAR_CLIENTE)) {
                System.out.println("1. Crear cliente");
            }

            if (usuarioLogueado.tienePermiso(Permiso.CREAR_CUENTA)) {
                System.out.println("2. Crear cuenta");
            }

            if (usuarioLogueado.tienePermiso(Permiso.DAR_BAJA_CUENTA)) {
                System.out.println("3. Dar de baja cuenta");
            }

            if (usuarioLogueado.tienePermiso(Permiso.VER_CLIENTES)) {
                System.out.println("4. Ver clientes");
            }

            if (usuarioLogueado.tienePermiso(Permiso.VER_CUENTAS)) {
                System.out.println("5. Ver resumen sucursal");
            }

            System.out.println("6. Volver");
            System.out.print("Ingrese una opcion: ");

            opcion = leerEntero();

            if (opcion == 1) {
                if (usuarioLogueado.tienePermiso(Permiso.CREAR_CLIENTE)) {
                    crearCliente(sucursal);
                } else {
                    System.out.println("No tiene permiso.");
                }
            } else if (opcion == 2) {
                if (usuarioLogueado.tienePermiso(Permiso.CREAR_CUENTA)) {
                    crearCuenta(sucursal);
                } else {
                    System.out.println("No tiene permiso.");
                }
            } else if (opcion == 3) {
                if (usuarioLogueado.tienePermiso(Permiso.DAR_BAJA_CUENTA)) {
                    darDeBajaCuenta(sucursal);
                } else {
                    System.out.println("No tiene permiso.");
                }
            } else if (opcion == 4) {
                if (usuarioLogueado.tienePermiso(Permiso.VER_CLIENTES)) {
                    sucursal.mostrarClientes();
                } else {
                    System.out.println("No tiene permiso.");
                }
            } else if (opcion == 5) {
                if (usuarioLogueado.tienePermiso(Permiso.VER_CUENTAS)) {
                    sucursal.mostrarResumenSucursal();
                } else {
                    System.out.println("No tiene permiso.");
                }
            } else if (opcion == 6) {
                System.out.println("Volviendo...");
            } else {
                System.out.println("Opcion invalida.");
            }
        }
    }

    public void crearCliente(Sucursal sucursal) {
        System.out.println("\n=== CREAR CLIENTE ===");

        System.out.print("ID del usuario: ");
        String id = scanner.nextLine();

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("DNI: ");
        String dni = scanner.nextLine();

        System.out.print("Direccion: ");
        String direccion = scanner.nextLine();

        System.out.print("Edad: ");
        int edad = leerEntero();

        System.out.print("Correo: ");
        String correo = scanner.nextLine();

        System.out.print("Username elegido por el cliente: ");
        String username = scanner.nextLine();

        System.out.print("Contrasena elegida por el cliente: ");
        String password = scanner.nextLine();

        boolean datosValidos = true;

        if (id.equals("") || nombre.equals("") || dni.equals("") || direccion.equals("") || correo.equals("")
                || username.equals("") || password.equals("")) {
            System.out.println("Todos los campos son obligatorios.");
            datosValidos = false;
        }

        if (edad < 18) {
            System.out.println("El cliente debe ser mayor o igual a 18 anios.");
            datosValidos = false;
        }

        if (!correo.contains("@")) {
            System.out.println("Correo invalido.");
            datosValidos = false;
        }

        if (!esDniValido(dni)) {
            System.out.println("El DNI debe contener solo numeros.");
            datosValidos = false;
        }

        if (banco.existeUsernameSistema(username)) {
            System.out.println("Ese username ya existe en el sistema.");
            datosValidos = false;
        }

        if (datosValidos) {
            Cliente cliente = new Cliente.Builder()
                    .setId(id)
                    .setNombre(nombre)
                    .setDni(dni)
                    .setDireccion(direccion)
                    .setEdad(edad)
                    .setCorreo(correo)
                    .setUsername(username)
                    .setPassword(password)
                    .build();

            boolean agregado = sucursal.agregarCliente(cliente);

            if (agregado) {
                banco.agregarUsuarioSistema(cliente);
                System.out.println("Cliente creado con codigo: " + cliente.getCodigoCliente());
            }
        }
    }

    public void crearCuenta(Sucursal sucursal) {
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

                System.out.print("Tipo de cuenta (1. Caja de ahorro / 2. Cuenta corriente): ");
                int opcionTipo = leerEntero();

                TipoCuenta tipoCuenta = null;
                boolean datosValidos = true;

                if (numeroCuenta.equals("")) {
                    System.out.println("El numero de cuenta es obligatorio.");
                    datosValidos = false;
                }

                if (opcionTipo == 1) {
                    tipoCuenta = TipoCuenta.CAJA_AHORRO;
                } else if (opcionTipo == 2) {
                    tipoCuenta = TipoCuenta.CUENTA_CORRIENTE;
                } else {
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

    public void depositarCliente(Cliente clienteLogueado) {
        System.out.println("\n=== DEPOSITAR DINERO ===");

        if (clienteLogueado.getCuenta() == null) {
            System.out.println("No tiene una cuenta asignada.");
        } else {
            System.out.print("Monto a depositar: ");
            double monto = leerDouble();
            clienteLogueado.getCuenta().depositar(monto);
        }
    }

    public void extraerCliente(Cliente clienteLogueado) {
        System.out.println("\n=== EXTRAER DINERO ===");

        if (clienteLogueado.getCuenta() == null) {
            System.out.println("No tiene una cuenta asignada.");
        } else {
            System.out.print("Monto a extraer: ");
            double monto = leerDouble();
            clienteLogueado.getCuenta().extraer(monto);
        }
    }

    public void transferirCliente(Cliente clienteLogueado) {
        System.out.println("\n=== TRANSFERIR DINERO ===");

        if (clienteLogueado.getCuenta() == null) {
            System.out.println("No tiene una cuenta asignada.");
        } else {
            System.out.print("DNI del cliente destino: ");
            String dniDestino = scanner.nextLine();

            Cliente clienteDestino = banco.buscarClienteGlobalPorDni(dniDestino);
            Cuenta cuentaDestino = null;

            if (clienteDestino != null) {
                cuentaDestino = clienteDestino.getCuenta();
            }

            if (cuentaDestino == null) {
                System.out.println("La cuenta destino no existe.");
            } else {
                if (clienteLogueado.getCuenta().getNumeroCuenta().equals(cuentaDestino.getNumeroCuenta())) {
                    System.out.println("No se puede transferir a la misma cuenta.");
                } else {
                    System.out.print("Monto a transferir: ");
                    double monto = leerDouble();
                    clienteLogueado.getCuenta().transferir(cuentaDestino, monto);
                }
            }
        }
    }

    public boolean esDniValido(String dni) {
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
public void darDeBajaCuenta(Sucursal sucursal) {
    System.out.println("\n=== DAR DE BAJA CUENTA ===");

    System.out.print("DNI del cliente: ");
    String dni = scanner.nextLine();

    Cliente cliente = sucursal.buscarClientePorDni(dni);

    if (cliente == null) {
        System.out.println("Cliente no encontrado en esta sucursal.");
    } else {
        if (cliente.getCuenta() == null) {
            System.out.println("El cliente no tiene una cuenta asignada.");
        } else {
            System.out.println("Cuenta encontrada: " + cliente.getCuenta().getNumeroCuenta());
            System.out.print("Confirma la baja de la cuenta? (si/no): ");
            String confirmacion = scanner.nextLine();

            if (confirmacion.equalsIgnoreCase("si")) {
                cliente.darDeBajaCuenta();
                System.out.println("La cuenta fue dada de baja correctamente.");
            } else {
                System.out.println("Operacion cancelada.");
            }
        }
    }
}
}