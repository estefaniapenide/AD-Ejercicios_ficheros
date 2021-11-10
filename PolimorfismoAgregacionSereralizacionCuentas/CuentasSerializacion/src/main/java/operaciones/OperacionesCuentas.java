/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operaciones;

import POJO.Cliente;
import POJO.Cuenta;
import POJO.CuentaCorriente;
import POJO.CuentaPlazo;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author a20estefaniapc
 */
public class OperacionesCuentas {
    
    //atributos estáticos
    private static ArrayList<Cuenta> listaTodasCuentas = new ArrayList<Cuenta>();
    private static ArrayList<Cliente> listaTodosClientes = new ArrayList<Cliente>();

    /**
     * @return the listaTodasCuentas
     */
    public static ArrayList<Cuenta> getListaTodasCuentas() {
        return listaTodasCuentas;
    }

    /**
     * @param aListaTodasCuentas the listaTodasCuentas to set
     */
    public static void setListaTodasCuentas(ArrayList<Cuenta> aListaTodasCuentas) {
        listaTodasCuentas = aListaTodasCuentas;
    }

    /**
     * @return the listaTodosClientes
     */
    public static ArrayList<Cliente> getListaTodosClientes() {
        return listaTodosClientes;
    }

    /**
     * @param aListaTodosClientes the listaTodosClientes to set
     */
    public static void setListaTodosClientes(ArrayList<Cliente> aListaTodosClientes) {
        listaTodosClientes = aListaTodosClientes;
    }

    /**
     * Si el número de cuenta se corresponde con el patrón de 5 digitos seguidos
     * de una letra mayúscula, lo considera válido.
     *
     * @param numeroCuenta
     * @return numeroCuentaValido - tipo boolean
     */
    public static boolean numeroCuentaValido(String numeroCuenta) {

        boolean numeroCuentaValido = false;
        Pattern pat = Pattern.compile("[0-9]{5}[A-Z]");
        Matcher mat = pat.matcher(numeroCuenta);
        if (mat.matches()) {
            numeroCuentaValido = true;
        } else {
            numeroCuentaValido = false;
        }
        return numeroCuentaValido;
    }

    /**
     * Dados el número de cuenta, la sucursal y la lista de clientes de esa
     * cuenta, crea una cuenta corriente y la añade a la lista general de
     * cuentas. Devuelve la cuenta ya creada.
     *
     * @param numeroCuenta
     * @param sucursal
     * @param clientes
     * @return cuentaCorriente
     */
    public static CuentaCorriente altaCuentaCorriente(String numeroCuenta, String sucursal, ArrayList<Cliente> clientes) {

        CuentaCorriente cuenta;

        cuenta = new CuentaCorriente(numeroCuenta, sucursal, clientes);
        getListaTodasCuentas().add(cuenta);

        return cuenta;
    }

    /**
     * Dados el número de cuenta, la sucursal y la lista de clientes de esa
     * cuenta, crea una cuenta plazo y la añade a la lista general de cuentas.
     * Devuelve la cuenta ya creada.
     *
     * @param numeroCuenta
     * @param sucursal
     * @param clientes
     * @return cuentaPlazo
     */
    public static CuentaPlazo altaCuentaPlazo(String numeroCuenta, String sucursal, ArrayList<Cliente> clientes) {

        CuentaPlazo cuenta;

        cuenta = new CuentaPlazo(numeroCuenta, sucursal, clientes);
        getListaTodasCuentas().add(cuenta);

        return cuenta;

    }

    /**
     * Dados el dni, el nombre y la lista de cuentas de ese cliente, registra un
     * cliente y lo añade a la lista general de clientes. Devuelve el cliente ya
     * registardo.
     *
     * @param dni
     * @param nombre
     * @param cuentas
     * @return cliente
     */
    public static Cliente altaCliente(String dni, String nombre, ArrayList<Cuenta> cuentas) {

        Cliente cliente;

        cliente = new Cliente(dni, nombre, cuentas);
        getListaTodosClientes().add(cliente);

        return cliente;
    }

    /**
     * Dado un número de cuenta comprueba si esa cuenta ya se encuentra en la
     * lista general de cuentas.
     *
     * @param numeroCuenta
     * @return cuentaCorrienteRegistrada
     */
    public static boolean cuentaRegistrada(String numeroCuenta) {

        boolean cuentaCorrienteRegistrada = false;
        for (Cuenta aux : getListaTodasCuentas()) {
            if (aux.getNumero().equals(numeroCuenta)) {
                cuentaCorrienteRegistrada = true;
            }
        }
        return cuentaCorrienteRegistrada;

    }

    /**
     * Dada una cuenta plazo comprueba si esa cuenta ya se encuentra en la lista
     * general de cuentas.
     *
     * @param cuenta
     * @return cuentaPlazoRegistrada
     */
    public static boolean cuentaPlazoRegistrada(CuentaPlazo cuenta) {

        boolean cuentaPlazoRegistrada = false;
        for (Cuenta aux : getListaTodasCuentas()) {
            if (aux == cuenta) {
                cuentaPlazoRegistrada = true;
            }
        }
        return cuentaPlazoRegistrada;

    }

    /**
     * Dada una cuenta corriente comprueba si esa cuenta ya se encuentra en la
     * lista general de cuentas.
     *
     * @param cuenta
     * @return cuentaCorrienteRegistrada
     */
    public static boolean cuentaCorrienteRegistrada(CuentaCorriente cuenta) {

        boolean cuentaCorrienteRegistrada = false;
        for (Cuenta aux : getListaTodasCuentas()) {
            if (aux == cuenta) {
                cuentaCorrienteRegistrada = true;
            }
        }
        return cuentaCorrienteRegistrada;

    }

    /**
     * Dado el dni de un cliente comprueba si ese cliente ya se encuentra en la
     * lista general de clientes.
     *
     * @param dni
     * @return clienteRegistrado
     */
    public static boolean clienteRegistrado(String dni) {

        boolean clienteRegistrado = false;
        for (Cliente aux : getListaTodosClientes()) {
            if (aux.getDni().equals(dni)) {
                clienteRegistrado = true;
            }
        }
        return clienteRegistrado;
    }

    /**
     * Dado un cliente comprueba si ya se encuentra en la lista general de
     * clientes.
     *
     * @param cliente
     * @return clienteRegistrado
     */
    public static boolean clienteRegistrado(Cliente cliente) {

        boolean clienteRegistrado = false;
        for (Cliente aux : getListaTodosClientes()) {
            if (aux == cliente) {
                clienteRegistrado = true;
            }
        }
        return clienteRegistrado;
    }

    /**
     * Dada una cuenta y un cliente comprueba si ese cliente ya está asignado a
     * esa cuenta.
     *
     * @param cuenta
     * @param cliente
     * @return clienteYaEnCuenta
     */
    public static boolean clienteYaEnCuenta(Cuenta cuenta, Cliente cliente) {

        boolean clienteYaEnCuenta = false;
        for (Cliente aux : cuenta.getClientes()) {
            if (aux == cliente) {
                clienteYaEnCuenta = true;
            }
        }
        return clienteYaEnCuenta;
    }

    /**
     * Dado un cliente lo busca en la lista general de clientes y lo devuelve.
     *
     * @param cliente
     * @return c
     */
    public static Cliente encontrarCliente(Cliente cliente) {

        Cliente c = null;
        for (Cliente aux : getListaTodosClientes()) {
            if (aux == cliente) {
                c = aux;
            }
        }
        return c;
    }

    /**
     * Dado el dni de un cliente busca al cliente en la lista general de
     * clientes y lo devuelve.
     *
     * @param dni
     * @return cliente
     */
    public static Cliente encontrarCliente(String dni) {

        Cliente cliente = null;
        for (Cliente aux : getListaTodosClientes()) {
            if (aux.getDni().equals(dni)) {
                cliente = aux;
            }
        }
        return cliente;
    }

    /**
     * Dada una cuenta la busca en la lista general de cuentas y la devuelve.
     *
     * @param cuenta
     * @return c
     */
    public static Cuenta encontrarCuenta(Cuenta cuenta) {

        Cuenta c = null;
        for (Cuenta aux : getListaTodasCuentas()) {
            if (aux == cuenta) {
                c = aux;
            }
        }
        return c;
    }

    /**
     * Dado el número de cuenta de una cuenta, busca la cuenta en la lista
     * general de cuentas y la devuelve.
     *
     * @param numeroCuenta
     * @return cuenta
     */
    public static Cuenta encontrarCuenta(String numeroCuenta) {

        Cuenta cuenta = null;
        for (Cuenta aux : getListaTodasCuentas()) {
            if (aux.getNumero().equals(numeroCuenta)) {
                cuenta = aux;
            }
        }
        return cuenta;
    }

    /**
     * Dado el número de cuenta de una cuenta corriente, busca la cuenta en la
     * lista general de cuentas y la devuelve.
     *
     * @param numeroCuenta
     * @return cuenta
     */
    public static CuentaCorriente encontrarCuentaCorriente(String numeroCuenta) {

        CuentaCorriente cuenta = null;
        for (Cuenta aux : getListaTodasCuentas()) {
            if (aux.getNumero().equals(numeroCuenta)) {
                cuenta = (CuentaCorriente) aux;
            }
        }
        return cuenta;
    }

    /**
     * Dado el número de cuenta de una cuenta, la borra de las listas de cuentas
     * de los clientes y de la lista de cuentas general.
     *
     * @param numeroCuenta
     */
    public static void borrarCuenta(String numeroCuenta) {

        //Borra la cuenta de las listas de cuentas de los clientes
        for (Cliente aux : encontrarCuenta(numeroCuenta).getClientes()) {
            for (int i = 0; i < encontrarCliente(aux).getCuentas().size(); i++) {
                encontrarCliente(aux).getCuentas().set(i, null);
                encontrarCliente(aux).getCuentas().remove(i);
            }
        }
        //Borra la cuenta de la lista general
        for (int i = 0; i < getListaTodasCuentas().size(); i++) {
            if (getListaTodasCuentas().get(i).getNumero().equals(numeroCuenta)) {
                getListaTodasCuentas().set(i, null);
                getListaTodasCuentas().remove(i);
            }
        }
    }

    /**
     * Devuelve una lista de todos los clientes que no tienen cuentas asociadas.
     *
     * @return clientesSinCuentas
     */
    public static ArrayList<Cliente> encontrarClientesSinCuentas() {

        ArrayList<Cliente> clientesSinCuentas = new ArrayList<Cliente>();

        for (Cliente aux : getListaTodosClientes()) {
            if (encontrarCliente(aux).getCuentas().isEmpty()) {
                clientesSinCuentas.add(aux);
            }
        }
        return clientesSinCuentas;
    }

    /**
     * Dado un cliente, lo borra de la lista general de clientes.
     *
     * @param cliente
     */
    public static void borrarCliente(Cliente cliente) {

        for (int j = 0; j < getListaTodosClientes().size(); j++) {
            if (getListaTodosClientes().get(j) == cliente) {
                getListaTodosClientes().set(j, null);
                getListaTodosClientes().remove(j);
            }
        }

    }


    /**
     * Dados una cuenta y un cliente asociado a ella, borra al cliente de la
     * lista de clientes de la cuenta.
     *
     * @param cliente
     * @param cuenta
     */
    public static void borrarClienteDeUnaCuenta(Cliente cliente, Cuenta cuenta) {

        //Borra el cliente de la lista de clientes de una cuenta.
        for (int i = 0; i < cuenta.getClientes().size(); i++) {
            if (cuenta.getClientes().get(i) == cliente) {
                cuenta.getClientes().set(i, null);
                cuenta.getClientes().remove(i);
            }

        }
    }

    /**
     * Dado un cliente, comprueba si este está asociado a alguna cuenta.
     *
     * @param cliente
     * @return clienteEnCuenta
     */
    public static boolean ClienteExisteEnAlgunaCuenta(Cliente cliente) {

        boolean clienteEnCuenta = false;

        for (Cuenta aux : getListaTodasCuentas()) {
            for (Cliente c : aux.getClientes()) {
                if (c == cliente) {
                    clienteEnCuenta = true;
                }
            }

        }
        return clienteEnCuenta;
    }

    /**
     * Devuelve una lista de todas las cuentas que no tienen clientes asociados.
     *
     * @return cuentasSinClientes
     */
    public static ArrayList<Cuenta> encontrarCuentasSinClientes() {
        ArrayList<Cuenta> cuentasSinClientes = new ArrayList<Cuenta>();
        for (Cuenta aux : getListaTodasCuentas()) {

            if (encontrarCuenta(aux).getClientes().isEmpty()) {
                cuentasSinClientes.add(aux);
            }
        }
        return cuentasSinClientes;
    }

    /**
     * Dada una cuenta, la borra de la lista general de cuentas.
     *
     * @param cuenta
     */
    public static void borrarCuenta(Cuenta cuenta) {

        for (int j = 0; j < getListaTodasCuentas().size(); j++) {
            if (getListaTodasCuentas().get(j) == cuenta) {
                getListaTodasCuentas().set(j, null);
                getListaTodasCuentas().remove(j);
            }
        }

    }

}
