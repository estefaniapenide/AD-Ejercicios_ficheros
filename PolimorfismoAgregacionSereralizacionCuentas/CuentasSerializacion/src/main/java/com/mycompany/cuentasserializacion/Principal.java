/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cuentasserializacion;

import ControlData.ControlData;
import POJO.Cliente;
import POJO.Cuenta;
import POJO.CuentaCorriente;
import POJO.Ingreso;
import POJO.Movimiento;
import POJO.Retirada;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import operaciones.OperacionesCuentas;
import operaciones.OperacionesFichero;

/**
 *
 * @author a20estefaniapc
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Lista DNIs válidos para hacer pruebas de clientes:
        //14154502A             
        //48599827E
        //16141145K
        //72532447S
        //44099017J
        //77418480C
        //61552065V
        //05680217E
        //52295068Z
        //12738948F
        Scanner input = new Scanner(System.in);

        File fichero = new File("cuentas.ddr");
        try {
            fichero.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("----------------------GESTIÓN CUENTAS--------------------------------\n");
        System.out.println("Recuperando datos guardados...");
        if (fichero.length() == 0) {
            System.out.println("No hay datos. El fichero se encuentra en blanco.\n");
        } else {
            try {
                OperacionesFichero.leerFichero(fichero);
                OperacionesFichero.guardarClientesEnListaTodosClientes();
                System.out.println("Datos recuperados.\n");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        int opcion = 0;
        do {
            System.out.println(menuPrincipal());
            opcion = ControlData.leerInt(input);
            switch (opcion) {
                case 1:
                    int a = 0;
                    do {
                        System.out.println(menuAltas());
                        a = ControlData.leerInt(input);
                        switch (a) {
                            case 1:
                                cuentaCorriente(input);
                                break;
                            case 2:
                                cuentaPlazo(input);
                                break;
                            case 3:
                                clienteEnCuenta(input);
                                break;
                            case 0:
                                System.out.println("Volviendo al menú anterior...\n");
                                break;
                            default:
                                System.out.println("No ha introducido ninguna de las opciones.");
                                break;
                        }
                    } while (a != 0);
                    break;
                case 2:
                    movimentos(input);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    int d = 0;
                    do {
                        System.out.println(menuConsultas());
                        d = ControlData.leerInt(input);
                        switch (d) {
                            case 1:
                                clientesDeUnaCuenta(input);
                                break;
                            case 2:
                                cuentasDeUnCliente(input);
                                break;
                            case 3:
                                moviminetosCuenta(input);
                                break;
                            case 0:
                                System.out.println("Volviendo al menú anterior...\n");
                                break;
                            default:
                                System.out.println("No ha introducido ninguna de las opciones.");
                                break;
                        }
                    } while (d != 0);
                    break;
                case 0:
                    try {
                    OperacionesFichero.escribirFichero(fichero);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Guardando datos en fichero...\nPrograma finalizado.");
                break;
                default:
                    System.out.println("No ha introducido ninguna de las opciones.");
                    break;
            }

        } while (opcion != 0);

    }

    public static void cuentaCorriente(Scanner input) {
        System.out.println("ALTA CUENTA CORRIENTE");

        String numeroCuenta = "";
        do {
            System.out.println("Número de cuenta:");
            numeroCuenta = ControlData.leerString(input);
            if (!OperacionesCuentas.numeroCuentaValido(numeroCuenta)) {
                System.out.println("El número de cuenta no es válido.");
            }
        } while (!OperacionesCuentas.numeroCuentaValido(numeroCuenta));

        if (OperacionesCuentas.cuentaRegistrada(numeroCuenta)) {
            System.out.println("No es posible dar de alta la cuenta " + numeroCuenta + ".\nYa está registrada.");
        } else {

            System.out.println("Sucursal:");
            String sucursal = ControlData.leerString(input);
 
            String dni = "";
            ArrayList<Cliente> clientesCuenta = new ArrayList<Cliente>();
            OperacionesCuentas.altaCuentaCorriente(numeroCuenta, sucursal, clientesCuenta);

            ArrayList<Cuenta> cuentasCliente = new ArrayList<Cuenta>();
            cuentasCliente.add(OperacionesCuentas.encontrarCuenta(numeroCuenta));

            System.out.println("DNI cliente:");
            dni = ControlData.leerDni(input);
            if (OperacionesCuentas.clienteRegistrado(dni)) {
                OperacionesCuentas.encontrarCuenta(numeroCuenta).getClientes().add(OperacionesCuentas.encontrarCliente(dni));
                OperacionesCuentas.encontrarCliente(dni).getCuentas().add(OperacionesCuentas.encontrarCuenta(numeroCuenta));
            } else {
                System.out.println("Nombre y apellidos: ");
                String nombre = ControlData.leerString(input);
                OperacionesCuentas.encontrarCuenta(numeroCuenta).getClientes().add(OperacionesCuentas.altaCliente(dni, nombre, cuentasCliente));
            }

            System.out.println(OperacionesCuentas.encontrarCuenta(numeroCuenta));
        }

    }

    public static void cuentaPlazo(Scanner input) {
        System.out.println("ALTA CUENTA PLAZO");

        String numeroCuenta = "";
        do {
            System.out.println("Número de cuenta:");
            numeroCuenta = ControlData.leerString(input);
            if (!OperacionesCuentas.numeroCuentaValido(numeroCuenta)) {
                System.out.println("El número de cuenta no es válido.");
            }
        } while (!OperacionesCuentas.numeroCuentaValido(numeroCuenta));

        if (OperacionesCuentas.cuentaRegistrada(numeroCuenta)) {
            System.out.println("No es posible dar de alta la cuenta " + numeroCuenta + "\nYa está registrada.");
        } else {

            System.out.println("Sucursal:");
            String sucursal = ControlData.leerString(input);

            String dni = "";
            ArrayList<Cliente> clientesCuenta = new ArrayList<Cliente>();
            OperacionesCuentas.altaCuentaPlazo(numeroCuenta, sucursal, clientesCuenta);

            ArrayList<Cuenta> cuentasCliente = new ArrayList<Cuenta>();
            cuentasCliente.add(OperacionesCuentas.encontrarCuenta(numeroCuenta));

            System.out.println("DNI cliente:");
            dni = ControlData.leerDni(input);
            if (OperacionesCuentas.clienteRegistrado(dni)) {
                OperacionesCuentas.encontrarCuenta(numeroCuenta).getClientes().add(OperacionesCuentas.encontrarCliente(dni));
                OperacionesCuentas.encontrarCliente(dni).getCuentas().add(OperacionesCuentas.encontrarCuenta(numeroCuenta));
            } else {
                System.out.println("Nombre y apellidos: ");
                String nombre = ControlData.leerString(input);
                OperacionesCuentas.encontrarCuenta(numeroCuenta).getClientes().add(OperacionesCuentas.altaCliente(dni, nombre, cuentasCliente));
            }

            System.out.println(OperacionesCuentas.encontrarCuenta(numeroCuenta));
        }

    }

    public static void clienteEnCuenta(Scanner input) {

        System.out.println("ALTA CLIENTE EN CUENTA EXISTENTE");

        System.out.println("Introduzca el número de cuenta:");
        String numeroCuenta = ControlData.leerString(input);
        if (!OperacionesCuentas.cuentaRegistrada(numeroCuenta)) {
            System.out.println("La cuenta " + numeroCuenta + " no existe.\nPara poder añadirle clientes deberá darla de alta primero.\n");
        } else {

            ArrayList<Cuenta> cuentasCliente = new ArrayList<Cuenta>();
            cuentasCliente.add(OperacionesCuentas.encontrarCuenta(numeroCuenta));

            System.out.println("DNI cliente: ");
            String dni = ControlData.leerDni(input);
            if (OperacionesCuentas.clienteRegistrado(dni)) {
                if (OperacionesCuentas.clienteYaEnCuenta(OperacionesCuentas.encontrarCuenta(numeroCuenta), OperacionesCuentas.encontrarCliente(dni))) {
                    System.out.println("El cliente " + dni + " ya estaba asociado a la cuenta " + numeroCuenta + ".\n");
                } else {
                    OperacionesCuentas.encontrarCuenta(numeroCuenta).getClientes().add(OperacionesCuentas.encontrarCliente(dni));
                    OperacionesCuentas.encontrarCliente(dni).getCuentas().add(OperacionesCuentas.encontrarCuenta(numeroCuenta));
                    System.out.println("El cliente " + dni + " ha sido añadido a la cuenta " + numeroCuenta + ".\n");
                }
            } else {
                System.out.println("Nombre y apellidos: ");
                String nombre = ControlData.leerString(input);
                OperacionesCuentas.encontrarCuenta(numeroCuenta).getClientes().add(OperacionesCuentas.altaCliente(dni, nombre, cuentasCliente));
                System.out.println("El cliente " + dni + " ha sido añadido a la cuenta " + numeroCuenta + ".\n");
            }

            System.out.println(OperacionesCuentas.encontrarCuenta(numeroCuenta));
        }

    }

    public static void movimentos(Scanner input) {
        System.out.println("MOVIMIENTOS CUENTA CORRIENTE");

        System.out.println("Introduzca el número de cuenta:");
        String numeroCuenta = ControlData.leerString(input);
        if (!OperacionesCuentas.cuentaRegistrada(numeroCuenta)) {
            System.out.println("La cuenta " + numeroCuenta + " no existe.\nPara poder realizar movimientos deberá darla de alta primero.\n");
        } else if (!(OperacionesCuentas.encontrarCuenta(numeroCuenta) instanceof CuentaCorriente)) {
            System.out.println("No es posible realizar movimientos.\n" + numeroCuenta + " NO es una CUENTA CORRIENTE.\n");
        } else {
            int mov = 0;
            do {
                System.out.println(menuMovimientos(numeroCuenta));
                mov = ControlData.leerInt(input);
                switch (mov) {
                    case 1:
                        System.out.println("CUENTA " + numeroCuenta);
                        System.out.println("Cantidad a ingresar(€):");
                        float cantidad = ControlData.leerFloat(input);
                        Date fecha = new Date(new java.util.Date().getTime());
                        Time hora = new Time(fecha.getTime());
                        Movimiento ingreso = new Ingreso(OperacionesCuentas.encontrarCuentaCorriente(numeroCuenta), fecha, hora, cantidad);
                        ingreso.setSaldoActual();
                        OperacionesCuentas.encontrarCuentaCorriente(numeroCuenta).getMovimientos().add(ingreso);
                        OperacionesCuentas.encontrarCuentaCorriente(numeroCuenta).setSaldoActual();
                        System.out.println("\nSaldo actualizado(€):" + OperacionesCuentas.encontrarCuentaCorriente(numeroCuenta).getSaldoActual());
                        break;
                    case 2:
                        System.out.println("CUENTA " + numeroCuenta);
                        System.out.println("Saldo disponible(€):" + OperacionesCuentas.encontrarCuentaCorriente(numeroCuenta).getSaldoActual());
                        System.out.println("Cantidad a retirar(€):");
                        cantidad = ControlData.leerFloat(input);
                        if (cantidad > OperacionesCuentas.encontrarCuentaCorriente(numeroCuenta).getSaldoActual()) {
                            System.out.println("\nOPERACIÓN NO PERMITIDA.\nNo peude retirar una cantidad superior al saldo disponible.");
                        } else {
                            fecha = new Date(new java.util.Date().getTime());
                            hora = new Time(fecha.getTime());
                            Movimiento retirada = new Retirada(OperacionesCuentas.encontrarCuentaCorriente(numeroCuenta), fecha, hora, cantidad);
                            retirada.setSaldoActual();
                            OperacionesCuentas.encontrarCuentaCorriente(numeroCuenta).getMovimientos().add(retirada);
                            OperacionesCuentas.encontrarCuentaCorriente(numeroCuenta).setSaldoActual();
                            System.out.println("\nSaldo actualizado(€):" + OperacionesCuentas.encontrarCuentaCorriente(numeroCuenta).getSaldoActual());
                        }
                        break;
                    case 0:
                        System.out.println("Volviendo al menú principal...");
                        break;
                    default:
                        System.out.println("No ha introducido ninguna de las opciones.");
                        break;
                }
            } while (mov != 0);
        }
    }

    public static void clientesDeUnaCuenta(Scanner input) {
        
        System.out.println("CLIENTES DE UNA CUENTA");

        System.out.println("Introduzca el número de cuenta:");
        String numeroCuenta = ControlData.leerString(input);
        if (!OperacionesCuentas.cuentaRegistrada(numeroCuenta)) {
            System.out.println("La cuenta " + numeroCuenta + " no existe.");
        } else {
            System.out.println("Clientes cuenta " + numeroCuenta);
            System.out.println(OperacionesCuentas.encontrarCuenta(numeroCuenta).getClientes());
        }

    }

    public static void cuentasDeUnCliente(Scanner input) {
        
        System.out.println("CUENTAS DE UN CLIENTE");

        System.out.println("DNI cliente: ");
        String dni = ControlData.leerString(input);
        if (!OperacionesCuentas.clienteRegistrado(dni)) {
            System.out.println("El cliente " + dni + " no está registrado.");
        } else {
            System.out.println("Cuentas cliente " + dni + ", " + OperacionesCuentas.encontrarCliente(dni).getNombre());
            System.out.println(OperacionesCuentas.encontrarCliente(dni).getCuentas());
        }

    }

    public static void moviminetosCuenta(Scanner input) {
        
        System.out.println("MOVIMIENTOS DE UNA CUENTA");

        System.out.println("Introduzca el número de cuenta:");
        String numeroCuenta = ControlData.leerString(input);
        if (!OperacionesCuentas.cuentaRegistrada(numeroCuenta)) {
            System.out.println("La cuenta " + numeroCuenta + " no existe.");
        } else if (!(OperacionesCuentas.encontrarCuenta(numeroCuenta) instanceof CuentaCorriente)) {
            System.out.println(numeroCuenta + " NO es una CUENTA CORRIENTE.\nNo se ha podido realizar movimientos en la misma.\n");
        } else {
            System.out.println("Movimientos de la cuenta " + numeroCuenta);
            if (OperacionesCuentas.encontrarCuentaCorriente(numeroCuenta).getMovimientos().isEmpty()) {
                System.out.println("NO hay movimientos.");
            } else {
                System.out.println(OperacionesCuentas.encontrarCuentaCorriente(numeroCuenta).getMovimientos());
            }
        }

    }

    public static String menuPrincipal() {
        String menuPrincipal = "CUENTAS\n"
                + "1.-Altas\n"
                + "2.-Movimientos en CC\n"
                + "3.-Bajas físicas\n"
                + "4.-Modificaciones clientes\n"
                + "5.-Consultas\n\n"
                + "0.-Salir/Guardar datos en fichero\n";
        return menuPrincipal;
    }

    public static String menuAltas() {
        String menuAltas = "ALTAS\n"
                + "1.-Cuenta corriente\n"
                + "2.-Cuenta plazo\n"
                + "3.-Cliente a cuenta existente\n\n"
                + "0.-Volver al menú principal\n";
        return menuAltas;
    }

    public static String menuBajas() {
        String menuBajas = "BAJAS FÍSICAS\n"
                + "1.-Cuenta corriente\n"
                + "2.-Cuenta plazo\n"
                + "3.-Cliente en una cuenta\n\n"
                + "0.-Volver al menú principal\n";
        return menuBajas;
    }

    public static String menuConsultas() {
        String menuBajas = "CONSULTAS\n"
                + "1.-Clientes de una cuenta\n"
                + "2.-Cuentas de un cliente\n"
                + "3.-Movimientos de una cuenta\n\n"
                + "0.-Volver al menú principal\n";
        return menuBajas;
    }

    public static String menuMovimientos(String numeroCuenta) {
        String menuMovimientos = "\nCUENTA " + numeroCuenta + "\n"
                + "1.-Ingresar\n"
                + "2.-Retirar\n\n"
                + "0.-Volver al menú principal\n";
        return menuMovimientos;
    }

}
