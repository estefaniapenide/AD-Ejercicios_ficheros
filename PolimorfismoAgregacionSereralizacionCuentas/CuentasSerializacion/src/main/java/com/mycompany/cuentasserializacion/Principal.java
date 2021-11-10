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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        
        //Crea el fichero donde se almacenarán los datos
        File fichero = new File("cuentas.ddr");
        try {
            fichero.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("----------------------GESTIÓN CUENTAS--------------------------------\n");
        System.out.println("Recuperando datos guardados...");
        //Si no hay nada en el fichero, no se lee y se indica por pantalla que se parte de un fichero en blanco.
        if (fichero.length() == 0) {
            System.out.println("No se han encontrado datos. El fichero está en blanco.\n");
        } else {
            //Si el fichero ya estaba inicializado y tiene datos...
            try {
                //...lee el fichero que contiene la lista de cuentas y las almacena en la lista general de todas las cuentas
                OperacionesFichero.leerFichero(fichero);
                //...de la lista de todas las cuentas recuperadas, almacena todos los clientes en la lista general de todos los clientes
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
                    int b = 0;
                    do {
                        System.out.println(menuBajas());
                        b = ControlData.leerInt(input);
                        switch (b) {
                            case 1:
                                bajaCuenta(input);
                                break;
                            case 2:
                                bajaCliente(input);
                                break;
                            case 0:
                                System.out.println("Volviendo al menú anterior...\n");
                                break;
                            default:
                                System.out.println("No ha introducido ninguna de las opciones.");
                                break;
                        }
                    } while (b != 0);
                    break;
                case 4:
                    int c = 0;
                    do {
                        System.out.println(menuModificaciones());
                        c = ControlData.leerInt(input);
                        switch (c) {
                            case 1:
                                modificarDniCliente(input);
                                break;
                            case 2:
                                modificarNombreCliente(input);
                                break;
                            case 3:
                                modificarDireccionCliente(input);
                                break;
                            case 0:
                                System.out.println("Volviendo al menú anterior...\n");
                                break;
                            default:
                                System.out.println("No ha introducido ninguna de las opciones.");
                                break;
                        }
                    } while (c != 0);
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
                    //Al finalizar el programa vuelca el arraylist de todas las cuentas en el fichero, sobreescribiendolo.
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
    
    /**
     * Da de alta una cuenta corriente.
     * @param input 
     */
    public static void cuentaCorriente(Scanner input) {
        System.out.println("ALTA CUENTA CORRIENTE");

        String numeroCuenta = "";
        do {
            System.out.println("Número de cuenta:");
            numeroCuenta = ControlData.leerString(input);
            numeroCuenta = numeroCuenta.toUpperCase();
            //Pide el número de cuenta a crear, si no se ajusta al patrón de 5 dígitos seguido de una mayúscula, lo vuelve a pedir hasta que se de un número válido.
            if (!OperacionesCuentas.numeroCuentaValido(numeroCuenta)) {
                System.out.println("El número de cuenta no es válido.");
            }
        } while (!OperacionesCuentas.numeroCuentaValido(numeroCuenta));
        
        //Si la cuenta ya está registrada, imprime un mensaje por panatlla y vuelve al menú principal.
        if (OperacionesCuentas.cuentaRegistrada(numeroCuenta)) {
            System.out.println("No es posible dar de alta la cuenta " + numeroCuenta + ".\nYa está registrada.\n");
        } else {
             //Si la cuenta es nueva pide la sucursal y el dni del cliente que se le va a signar.
            System.out.println("Sucursal:");
            String sucursal = ControlData.leerString(input);

            String dni = "";
            //Se crea un arrayList de clientes de la cuenta para añadirselo a la cuenta.
            ArrayList<Cliente> clientesCuenta = new ArrayList<Cliente>();
            //Se da de alta la cuenta con el número, la sucursal y el arryalist de clientes vacío.
            OperacionesCuentas.altaCuentaCorriente(numeroCuenta, sucursal, clientesCuenta);
            
            //Crea un arrayList de cuentas del cliente para añadirselo al cliente si este aún no está registrado.
            //Añade la cuenta a esa lista.
            ArrayList<Cuenta> cuentasCliente = new ArrayList<Cuenta>();
            cuentasCliente.add(OperacionesCuentas.encontrarCuenta(numeroCuenta));
            
             //Se pide el DNI del cliente
            System.out.println("DNI cliente:");
            dni = ControlData.leerDni(input);
            //Si el cliente ya está registrado...
            if (OperacionesCuentas.clienteRegistrado(dni)) {
                //... se añade el cliente a la lista de clientes de la cuenta 
                OperacionesCuentas.encontrarCuenta(numeroCuenta).getClientes().add(OperacionesCuentas.encontrarCliente(dni));
                 //... se añade la cuenta a la lista de cuentas del cliente
                OperacionesCuentas.encontrarCliente(dni).getCuentas().add(OperacionesCuentas.encontrarCuenta(numeroCuenta));
            } else {
                //Si el cliente no estaba registrado se piden el nombre y apellidos
                System.out.println("Nombre y apellidos: ");
                String nombre = ControlData.leerString(input);
                //Se da de alta al cliente: el dni, nombre y la lista de cuentas del cliente creada antes(cuentasCliente)
                //(con el alta se crea el cliente y se añade a la lista general de clientes).
                //El cliente ya dado de alta se añade a la lista de clientes de la cuenta.
                OperacionesCuentas.encontrarCuenta(numeroCuenta).getClientes().add(OperacionesCuentas.altaCliente(dni, nombre, cuentasCliente));
            }
            
            //Muestra la cuenta creada.
            System.out.println(OperacionesCuentas.encontrarCuenta(numeroCuenta));
        }

    }
    
    /**
     * Da de alta una cuenta plazo
     * @param input 
     */
    public static void cuentaPlazo(Scanner input) {
        System.out.println("ALTA CUENTA PLAZO");

        String numeroCuenta = "";
        do {
            //Pide el número de cuenta a crear, si no se ajusta al patrón de 5 dígitos seguido de una mayúscula, lo vuelve a pedir hasta que se de un número válido.
            System.out.println("Número de cuenta:");
            numeroCuenta = ControlData.leerString(input);
            numeroCuenta = numeroCuenta.toUpperCase();
            if (!OperacionesCuentas.numeroCuentaValido(numeroCuenta)) {
                System.out.println("El número de cuenta no es válido.");
            }
        } while (!OperacionesCuentas.numeroCuentaValido(numeroCuenta));
        
        //Si la cuenta ya está registrada, imprime un mensaje por panatlla y vuelve al menú principal.
        if (OperacionesCuentas.cuentaRegistrada(numeroCuenta)) {
            System.out.println("No es posible dar de alta la cuenta " + numeroCuenta + "\nYa está registrada.\n");
        } else {
            //Si la cuenta es nueva pide la sucursal y el dni del cliente que se le va a signar.
            System.out.println("Sucursal:");
            String sucursal = ControlData.leerString(input);

            String dni = "";
            //Se crea un arrayList de clientes de la cuenta para añadirselo a la cuenta.
            ArrayList<Cliente> clientesCuenta = new ArrayList<Cliente>();
            //Se da de alta la cuenta con el número, la sucursal y el arryalist de clientes vacío.
            OperacionesCuentas.altaCuentaPlazo(numeroCuenta, sucursal, clientesCuenta);
            
            //Crea un arrayList de cuentas del cliente para añadirselo al cliente si este aún no está registrado.
            //Añade la cuenta a esa lista.
            ArrayList<Cuenta> cuentasCliente = new ArrayList<Cuenta>();
            cuentasCliente.add(OperacionesCuentas.encontrarCuenta(numeroCuenta));
            
            //Se pide el DNI del cliente
            System.out.println("DNI cliente:");
            dni = ControlData.leerDni(input);
            //Si el cliente ya está registrado...
            if (OperacionesCuentas.clienteRegistrado(dni)) {
                //... se añade el cliente a la lista de clientes de la cuenta 
                OperacionesCuentas.encontrarCuenta(numeroCuenta).getClientes().add(OperacionesCuentas.encontrarCliente(dni));
                //... se añade la cuenta a la lista de cuentas del cliente
                OperacionesCuentas.encontrarCliente(dni).getCuentas().add(OperacionesCuentas.encontrarCuenta(numeroCuenta));
            } else {
                //Si el cliente no estaba registrado se piden el nombre y apellidos
                System.out.println("Nombre y apellidos: ");
                String nombre = ControlData.leerString(input);
                //Se da de alta al cliente: el dni, nombre y la lista de cuentas del cliente creada antes(cuentasCliente)
                //(con el alta se crea el cliente y se añade a la lista general de clientes).
                //El cliente ya dado de alta se añade a la lista de clientes de la cuenta.
                OperacionesCuentas.encontrarCuenta(numeroCuenta).getClientes().add(OperacionesCuentas.altaCliente(dni, nombre, cuentasCliente));
            }
            //Muestra la cuenta creada.
            System.out.println(OperacionesCuentas.encontrarCuenta(numeroCuenta));
        }

    }
    
    /**
     * Da de alta un cliente en una cuenta ya existente.
     * @param input 
     */
    public static void clienteEnCuenta(Scanner input) {

        System.out.println("ALTA CLIENTE EN CUENTA EXISTENTE");

        System.out.println("Introduzca el número de cuenta:");
        String numeroCuenta = ControlData.leerString(input);
        numeroCuenta = numeroCuenta.toUpperCase();
         //Si la cuenta no está registrada, imprime un mensaje por panatlla y vuelve al menú principal.
        if (!OperacionesCuentas.cuentaRegistrada(numeroCuenta)) {
            System.out.println("La cuenta " + numeroCuenta + " no existe.\nPara poder añadirle clientes deberá darla de alta primero.\n");
        } else {
            
            //Crea un arrayList de cuentas del cliente para añadirselo al cliente si este aún no está registrado.
            //Añade la cuenta a esa lista.
            ArrayList<Cuenta> cuentasCliente = new ArrayList<Cuenta>();
            cuentasCliente.add(OperacionesCuentas.encontrarCuenta(numeroCuenta));

            System.out.println("DNI cliente: ");
            String dni = ControlData.leerDni(input);
            //Si el cliente ya está registrado...
            if (OperacionesCuentas.clienteRegistrado(dni)) {
                //...Si ya se encuenta en asociado a la cuenta, imprime un mensaje por panatlla y se vuelve al menú principal.
                if (OperacionesCuentas.clienteYaEnCuenta(OperacionesCuentas.encontrarCuenta(numeroCuenta), OperacionesCuentas.encontrarCliente(dni))) {
                    System.out.println("El cliente " + dni + " ya estaba asociado a la cuenta " + numeroCuenta + ".\n");
                } else {
                //...En otro caso, añade la cuenta a la lista de cuentas del cliente y el cliente a la lista de clientes de la cuenta.
                    OperacionesCuentas.encontrarCuenta(numeroCuenta).getClientes().add(OperacionesCuentas.encontrarCliente(dni));
                    OperacionesCuentas.encontrarCliente(dni).getCuentas().add(OperacionesCuentas.encontrarCuenta(numeroCuenta));
                    System.out.println("El cliente " + dni + " ha sido añadido a la cuenta " + numeroCuenta + ".\n");
                }
            } else {
            //Si el cliente no esta registrado, pide el nombre y apellidos
                System.out.println("Nombre y apellidos: ");
                String nombre = ControlData.leerString(input);
                //Se da de alta al cliente: el dni, nombre y la lista de cuentas del cliente creada antes(cuentasCliente)
                //(con el alta se crea el cliente y se añade a la lista general de clientes)
                //El cliente ya dado de alta se añade a la lista de clientes de la cuenta.
                OperacionesCuentas.encontrarCuenta(numeroCuenta).getClientes().add(OperacionesCuentas.altaCliente(dni, nombre, cuentasCliente));
                System.out.println("El cliente " + dni + " ha sido añadido a la cuenta " + numeroCuenta + ".\n");
            }
            
            //Muestra la cuenta actualizada.
            System.out.println(OperacionesCuentas.encontrarCuenta(numeroCuenta));
        }

    }
    
    /**
     * Gestiona los movimientos de una cuenta corriente.
     * @param input 
     */
    public static void movimentos(Scanner input) {
        System.out.println("MOVIMIENTOS CUENTA CORRIENTE");

        System.out.println("Introduzca el número de cuenta:");
        String numeroCuenta = ControlData.leerString(input);
        numeroCuenta = numeroCuenta.toUpperCase();
        //Si la cuenta no está registrada, imprime un mensaje por panatlla y vuelve al menú principal.
        if (!OperacionesCuentas.cuentaRegistrada(numeroCuenta)) {
            System.out.println("La cuenta " + numeroCuenta + " no existe.\nPara poder realizar movimientos deberá darla de alta primero.\n");
        //Si la cuenta está registrada pero no es una cuenta corriente, imprime un mensaje por pantalla y vuelve al menú principal.
        } else if (!(OperacionesCuentas.encontrarCuenta(numeroCuenta) instanceof CuentaCorriente)) {
            System.out.println("No es posible realizar movimientos.\n" + numeroCuenta + " NO es una CUENTA CORRIENTE.\n");
        } else {
            //En cualquier otro caso muestra un menú para escoger el tipo de movimiento.
            int mov = 0;
            do {
                System.out.println(menuMovimientos(numeroCuenta));
                mov = ControlData.leerInt(input);
                switch (mov) {
                    case 1:
                        //Pide la catidad a ingresar
                        System.out.println("CUENTA " + numeroCuenta);
                        System.out.println("Cantidad a ingresar(€):");
                        float cantidad = ControlData.leerFloat(input);
                        Date fecha = new Date(new java.util.Date().getTime());
                        Time hora = new Time(fecha.getTime());
                        //Crea un ingreso con el número de cuenta, la cantidad y la fecha y hora autogeneradas.
                        Movimiento ingreso = new Ingreso(OperacionesCuentas.encontrarCuentaCorriente(numeroCuenta), fecha, hora, cantidad);
                        //Actualiza el saldoActual en el movimiento tras el ingreso.
                        ingreso.setSaldoActual();
                        //Añade el movimiento a la lista de movimientos de la cuenta.
                        OperacionesCuentas.encontrarCuentaCorriente(numeroCuenta).getMovimientos().add(ingreso);
                        //Actualiza el saldoActual de la cuenta.
                        OperacionesCuentas.encontrarCuentaCorriente(numeroCuenta).setSaldoActual();
                        System.out.println("\nSaldo actualizado(€):" + OperacionesCuentas.encontrarCuentaCorriente(numeroCuenta).getSaldoActual());
                        break;
                    case 2:
                        //Pide la cantidad a retirar
                        System.out.println("CUENTA " + numeroCuenta);
                        System.out.println("Saldo disponible(€):" + OperacionesCuentas.encontrarCuentaCorriente(numeroCuenta).getSaldoActual());
                        System.out.println("Cantidad a retirar(€):");
                        cantidad = ControlData.leerFloat(input);
                        //Si la cantidad solicitada es mayor que la disponible, imprime un mensaje por panatlla y vuelve al menú de ingreso/retirada.
                        if (cantidad > OperacionesCuentas.encontrarCuentaCorriente(numeroCuenta).getSaldoActual()) {
                            System.out.println("\nOPERACIÓN NO PERMITIDA.\nNo puede retirar una cantidad superior al saldo disponible.\n");
                        } else {
                        //En cualquier otro caso...
                            fecha = new Date(new java.util.Date().getTime());
                            hora = new Time(fecha.getTime());
                            //Crea una retirada con el número de cuenta, la cantidad y la fecha y hora autogeneradas.
                            Movimiento retirada = new Retirada(OperacionesCuentas.encontrarCuentaCorriente(numeroCuenta), fecha, hora, cantidad);
                            //Actualiza el saldoActual en el movimiento tras la retirada.
                            retirada.setSaldoActual();
                            //Añade el movimiento a la lista de movimientos de la cuenta.
                            OperacionesCuentas.encontrarCuentaCorriente(numeroCuenta).getMovimientos().add(retirada);
                            //Actualiza el saldoActual de la cuenta.
                            OperacionesCuentas.encontrarCuentaCorriente(numeroCuenta).setSaldoActual();
                            System.out.println("\nSaldo actualizado(€):" + OperacionesCuentas.encontrarCuentaCorriente(numeroCuenta).getSaldoActual() + "\n");
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
    
    /**
     * Da de baja una cuenta.
     * @param input 
     */
    public static void bajaCuenta(Scanner input) {
        System.out.println("BAJA CUENTA");

        System.out.println("Introduzca el número de cuenta:");
        String numeroCuenta = ControlData.leerString(input);
        numeroCuenta = numeroCuenta.toUpperCase();
        //Si la cuenta no está registrada, imprime un mensaje por panatlla y vuelve al menú principal.
        if (!OperacionesCuentas.cuentaRegistrada(numeroCuenta)) {
            System.out.println("La cuenta " + numeroCuenta + " no existe.\n");
        } else {
            //Borra la cuenta de la lista de cuentas de los clientes y de la lista de cuentas general.
            OperacionesCuentas.borrarCuenta(numeroCuenta);
            System.out.println("La cuenta ha sido dada de baja.\n");
            //Si los clientes de esa cuenta no tenían más cuentas asociadas, se almacenarán en una lista de clientes sin cuentas.
            ArrayList<Cliente> clientesSinCuentas = OperacionesCuentas.encontrarClientesSinCuentas();
            if (!clientesSinCuentas.isEmpty()) {
                for (Cliente aux : clientesSinCuentas) {
                    //Los clientes sin cuentas asociadas, se borrán de la lista de clientes general.
                    OperacionesCuentas.borrarCliente(aux);
                }
                System.out.println("Los clientes que sólo estaban asociados a esta cuenta han sido borrados.\n");
            }
        }
    }
    
    /**
     * Da de baja un cliente de una cuenta.
     * @param input 
     */
    public static void bajaCliente(Scanner input) {
        System.out.println("BAJA CLIENTE DE UNA CUENTA");

        System.out.println("DNI cliente: ");
        String dni = ControlData.leerString(input);
        dni = dni.toUpperCase();
        //Si el cliente no está registrado, imprime un mensaje por panatlla y vuelve al menú principal.
        if (!OperacionesCuentas.clienteRegistrado(dni)) {
            System.out.println("El cliente " + dni + " no está registrado.\n");
        } else {
            //Devuelve las cuentas de ese cliente y pide una de ellas.
            System.out.println("\nEscriba el número de cuenta de la cuenta a darse de baja:");
            System.out.println("CUENTAS DE " + dni);
            for (Cuenta aux : OperacionesCuentas.encontrarCliente(dni).getCuentas()) {
                System.out.println("- CUENTA " + aux.getTipo() + ":" + aux.getNumero());
            }
            String numeroCuenta = ControlData.leerString(input);
            numeroCuenta = numeroCuenta.toUpperCase();
            Cuenta cuenta = OperacionesCuentas.encontrarCuenta(numeroCuenta);
            Cliente cliente = OperacionesCuentas.encontrarCliente(dni);
            //Si la cuenta introducida no existe, imprime un mensaje y vuelve al menú principal.
            if (!OperacionesCuentas.cuentaRegistrada(numeroCuenta)) {
                System.out.println("\nLa cuenta " + numeroCuenta + " no existe.\n");
            //Si la cuenta introducida existe pero el cliente no está asociada a ella, imprime un mensaje y vuelve al menú principal.
            } else if (!OperacionesCuentas.clienteYaEnCuenta(cuenta, cliente)) {
                System.out.println("\nLa cuenta " + numeroCuenta + " no pertenece al cliente " + dni + ".\n");
            } else {
                //En cualquier otro caso, borra al cliente de la lista de clientes de la cuenta.
                OperacionesCuentas.borrarClienteDeUnaCuenta(cliente, cuenta);
                System.out.println("\nEl cliente ha sido dado de baja de la cuenta.\n");
                //Si esa cuenta no tenía más clientes asociados, se almacenará en una lista de cuentas sin clientes.
                ArrayList<Cuenta> cuentasSinClientes = OperacionesCuentas.encontrarCuentasSinClientes();
                if (!cuentasSinClientes.isEmpty()) {
                    for (Cuenta aux : cuentasSinClientes) {
                        //Si la cuenta se encuenta en la lista de cuentas sin clientes, se borrará.
                        OperacionesCuentas.borrarCuenta(aux);
                    }
                    System.out.println("\nLa cuenta no tenía más clientes, por lo que ha sido dada de baja.\n");
                }
                //Se busca si ese cliente esta asociado a alguna otra cuenta. Si no lo está, se borrrá de la lista de clientes general.
                if (!OperacionesCuentas.ClienteExisteEnAlgunaCuenta(cliente)) {
                    OperacionesCuentas.borrarCliente(cliente);
                    System.out.println("\n" + numeroCuenta + " era la única cuenta del cliente " + dni + ".\nPor lo tanto los datos del cliente han sido borrados.\n");
                }
            }
        }

    }
    
    /**
     * Modifica el DNI de un cliente.
     * @param input 
     */
    public static void modificarDniCliente(Scanner input) {
        System.out.println("MODIFICAR DNI CLIENTE");

        System.out.println("DNI cliente: ");
        String dni = ControlData.leerString(input);
        dni = dni.toUpperCase();
        //Si el cliente no está registrado, imprime un mensaje por panatlla y vuelve al menú principal.
        if (!OperacionesCuentas.clienteRegistrado(dni)) {
            System.out.println("El cliente " + dni + " no está registrado.\n");
        } else {
            //Muestra los datos del cliente y pide el nuevo DNI.
            System.out.println(OperacionesCuentas.encontrarCliente(dni));
            System.out.println("Nuevo DNI: ");
            String dniNuevo = ControlData.leerDni(input);
            //Modifica el DNI.
            OperacionesCuentas.encontrarCliente(dni).setDni(dniNuevo);
            //Devuelve los datos del cliente actualizados.
            System.out.println(OperacionesCuentas.encontrarCliente(dniNuevo));
        }

    }
    
    /**
     * Modifica el nombre de un cliente.
     * @param input 
     */
    public static void modificarNombreCliente(Scanner input) {
        System.out.println("MODIFICAR NOMBRE CLIENTE");

        System.out.println("DNI cliente: ");
        String dni = ControlData.leerString(input);
        dni = dni.toUpperCase();
        //Si el cliente no está registrado, imprime un mensaje por panatlla y vuelve al menú principal.
        if (!OperacionesCuentas.clienteRegistrado(dni)) {
            System.out.println("El cliente " + dni + " no está registrado.\n");
        } else {
            //Muestra los datos del cliente y pide el nuevo nombre.
            System.out.println(OperacionesCuentas.encontrarCliente(dni));
            System.out.println("Nuevo nombre: ");
            String nombre = ControlData.leerString(input);
            //Modifica el nombre.
            OperacionesCuentas.encontrarCliente(dni).setNombre(nombre);
             //Devuelve los datos del cliente actualizados.
            System.out.println(OperacionesCuentas.encontrarCliente(dni));
        }

    }
    
    /**
     * Modifica la direcciónd de un cliente.
     * @param input 
     */
    public static void modificarDireccionCliente(Scanner input) {
        System.out.println("MODIFICAR DIRECCIÓN CLIENTE");

        System.out.println("DNI cliente: ");
        String dni = ControlData.leerString(input);
        dni = dni.toUpperCase();
        //Si el cliente no está registrado, imprime un mensaje por panatlla y vuelve al menú principal.
        if (!OperacionesCuentas.clienteRegistrado(dni)) {
            System.out.println("El cliente " + dni + " no está registrado.\n");
        } else {
            //Muestra los datos del cliente y pide la nueva dirección.
            System.out.println(OperacionesCuentas.encontrarCliente(dni));
            System.out.println("Nueva Dirección: ");
            String direccion = ControlData.leerString(input);
            //Modifica la dirección.
            OperacionesCuentas.encontrarCliente(dni).setDireccion(direccion);
            //Devuelve los datos del cliente actualizados.
            System.out.println(OperacionesCuentas.encontrarCliente(dni));
        }

    }
    
    /**
     * Devuelve los clientes de una cuenta.
     * @param input 
     */
    public static void clientesDeUnaCuenta(Scanner input) {

        System.out.println("CLIENTES DE UNA CUENTA");

        System.out.println("Introduzca el número de cuenta:");
        String numeroCuenta = ControlData.leerString(input);
        numeroCuenta = numeroCuenta.toUpperCase();
        //Si la cuenta no está registrada, imprime un mensaje por panatlla y vuelve al menú principal.
        if (!OperacionesCuentas.cuentaRegistrada(numeroCuenta)) {
            System.out.println("La cuenta " + numeroCuenta + " no existe.\n");
        } else {
            //Devuelve todos los clientes de esa cuenta.
            System.out.println("CLIENTES CUENTA " + numeroCuenta + ":");
            for (Cliente aux : OperacionesCuentas.encontrarCuenta(numeroCuenta).getClientes()) {
                System.out.println(aux);
            }
        }

    }
    
    /**
     * Devuelve las cuentas de un cliente.
     * @param input 
     */
    public static void cuentasDeUnCliente(Scanner input) {

        System.out.println("CUENTAS DE UN CLIENTE");

        System.out.println("DNI cliente: ");
        String dni = ControlData.leerString(input);
        dni = dni.toUpperCase();
        //Si el cliente no está registrado, imprime un mensaje por panatlla y vuelve al menú principal.
        if (!OperacionesCuentas.clienteRegistrado(dni)) {
            System.out.println("El cliente " + dni + " no está registrado.\n");
        } else {
            //Devuelve todas las cuentas de ese cliente
            System.out.println("CUENTAS CLIENTE " + dni + ", " + OperacionesCuentas.encontrarCliente(dni).getNombre() + ":");
            for (Cuenta aux : OperacionesCuentas.encontrarCliente(dni).getCuentas()) {
                System.out.println(aux);
            }
        }

    }
    
    /**
     * Devuelve los movientos de una cuenta.
     * @param input 
     */
    public static void moviminetosCuenta(Scanner input) {

        System.out.println("MOVIMIENTOS DE UNA CUENTA");

        System.out.println("Introduzca el número de cuenta:");
        String numeroCuenta = ControlData.leerString(input);
        numeroCuenta = numeroCuenta.toUpperCase();
        //Si la cuenta no está registrada, imprime un mensaje y vuelve al menú principal.
        if (!OperacionesCuentas.cuentaRegistrada(numeroCuenta)) {
            System.out.println("La cuenta " + numeroCuenta + " no existe.\n");
        //Si la cuenta está registrada pero no es una cuenta corriente, imprime un mensaje y vuelve al menú principal.
        } else if (!(OperacionesCuentas.encontrarCuenta(numeroCuenta) instanceof CuentaCorriente)) {
            System.out.println(numeroCuenta + " NO es una CUENTA CORRIENTE.\nNo se han podido realizar movimientos en la misma.\n");
        } else {
            //La cuenta está registrada y es una cuenta corriente.
            System.out.println("\nMOVIMIENTOS CUENTA " + numeroCuenta + ":");
            //Si nunca se han realizado movimientos, imprime un mensaje y vuelve al menú princiapl.
            if (OperacionesCuentas.encontrarCuentaCorriente(numeroCuenta).getMovimientos().isEmpty()) {
                System.out.println("NO se han realizado movimientos en esta cuenta.\n");
            } else {
                //Pide el intervalo de fechas de los movimientos en bucle hasta que se den fechas válidas.
                try {
                    Date inicio = null;
                    Date fin = null;
                    do {
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        System.out.println("Desde (dd/mm/yyyy):");
                        String desde = ControlData.leerFecha(input);
                        inicio = formato.parse(desde);

                        System.out.println("Hasta* (dd/mm/yyyy):\n*(los movimentos de este día NO se mostrarán)");
                        String hasta = ControlData.leerFecha(input);
                        fin = formato.parse(hasta);

                        if (inicio.compareTo(fin) >= 0) {
                            System.out.println("ERROR. La fecha inicio debe ser anterior a la de fin.\nNUNCA IGUAL ni POSTERIOR.\n");
                        }
                    } while (inicio.compareTo(fin) >= 0);

                    System.out.println("\nMOVIMIENTOS CUENTA " + numeroCuenta + ":");
                    //Inicializa un ArrayList para los movientos realizados en esas fechas
                    ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
                    //Devuelve los movimientos realizados en esas fechas
                    for (Movimiento aux : OperacionesCuentas.encontrarCuentaCorriente(numeroCuenta).getMovimientos()) {
                        if (inicio.before(aux.getFechaOperacion()) && fin.after(aux.getFechaOperacion())) {
                            //Almacena los movientos de esas fechas
                            movimientos.add(aux);
                            System.out.println(aux);
                        }
                    }
                    //Si no hay movientos en esas fechas, en lugar de mostrar una lista en blanco, se indica con un mensaje.
                    if (movimientos.isEmpty()) {
                        System.out.println("NO se han producido movimientos entre esas fechas.\n");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    
    //Menús
    
    /**
     * Opciones del menú de CUENTAS
     * @return menuPrincipal
     */
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
    
    /**
     * Opciones del menú de ALTAS
     * @return menuAltas
     */
    public static String menuAltas() {
        String menuAltas = "ALTAS\n"
                + "1.-Cuenta corriente\n"
                + "2.-Cuenta plazo\n"
                + "3.-Cliente a cuenta existente\n\n"
                + "0.-Volver al menú principal\n";
        return menuAltas;
    }
    
    /**
     * Opciones del menú de BAJAS FÍSCAS
     * @return menuBajas
     */
    public static String menuBajas() {
        String menuBajas = "BAJAS FÍSICAS\n"
                + "1.-Cuenta\n"
                + "2.-Cliente en una cuenta\n\n"
                + "0.-Volver al menú principal\n";
        return menuBajas;
    }
    
    /**
     * Opciones del menú de CONSULTAS
     * @return menuConsultas
     */
    public static String menuConsultas() {
        String menuConsultas = "CONSULTAS\n"
                + "1.-Clientes de una cuenta\n"
                + "2.-Cuentas de un cliente\n"
                + "3.-Movimientos de una cuenta\n\n"
                + "0.-Volver al menú principal\n";
        return menuConsultas;
    }
    
    /**
     * Opciones del menú de MODIFICACIONES
     * @return menuModificaciones
     */
    public static String menuModificaciones() {
        String menuModificaciones = "MODIFICACIONES\n"
                + "1.-Modificar DNI\n"
                + "2.-Modificar nombre\n"
                + "3.-Añadir/Modificar dirección\n\n"
                + "0.-Volver al menú principal\n";
        return menuModificaciones;
    }
    
    /**
     * Opciones del menú de MOVIMIENTOS de una cuenta.
     * @param numeroCuenta
     * @return menuMovimientos
     */
    public static String menuMovimientos(String numeroCuenta) {
        String menuMovimientos = "\nCUENTA " + numeroCuenta + "\n"
                + "1.-Ingresar\n"
                + "2.-Retirar\n\n"
                + "0.-Volver al menú principal\n";
        return menuMovimientos;
    }

}
