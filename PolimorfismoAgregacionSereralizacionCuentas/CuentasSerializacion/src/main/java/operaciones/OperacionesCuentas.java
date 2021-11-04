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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author a20estefaniapc
 */
public class OperacionesCuentas {

    public static ArrayList<Cuenta> listaTodasCuentas = new ArrayList<Cuenta>();
    public static ArrayList<Cliente> listaTodosClientes = new ArrayList<Cliente>();

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

    public static CuentaCorriente altaCuentaCorriente(String numeroCuenta, String sucursal, ArrayList<Cliente> clientes) {

        CuentaCorriente cuenta;

        cuenta = new CuentaCorriente(numeroCuenta, sucursal,clientes);
        getListaTodasCuentas().add(cuenta);

        return cuenta;
    }

    public static CuentaPlazo altaCuentaPlazo(String numeroCuenta, String sucursal, ArrayList<Cliente> clientes) {

        CuentaPlazo cuenta;

        cuenta = new CuentaPlazo(numeroCuenta, sucursal,clientes);
        getListaTodasCuentas().add(cuenta);

        return cuenta;

    }

    public static Cliente altaCliente(String dni, String nombre,ArrayList<Cuenta> cuentas) {
        
        Cliente cliente;

        cliente = new Cliente(dni, nombre,cuentas);
        getListaTodosClientes().add(cliente);

        return cliente;
    }

    public static boolean cuentaRegistrada(String numeroCuenta) {

        boolean cuentaCorrienteRegistrada = false;
        for (Cuenta aux : getListaTodasCuentas()) {
            if (aux.getNumero().equals(numeroCuenta)) {
                cuentaCorrienteRegistrada = true;
            }
        }
        return cuentaCorrienteRegistrada;

    }

    public static boolean cuentaPlazoRegistrada(CuentaPlazo cuenta) {

        boolean cuentaPlazoRegistrada = false;
        for (Cuenta aux : getListaTodasCuentas()) {
            if (aux == cuenta) {
                cuentaPlazoRegistrada = true;
            }
        }
        return cuentaPlazoRegistrada;

    }

    public static boolean cuentaCorrienteRegistrada(CuentaCorriente cuenta) {

        boolean cuentaCorrienteRegistrada = false;
        for (Cuenta aux : getListaTodasCuentas()) {
            if (aux == cuenta) {
                cuentaCorrienteRegistrada = true;
            }
        }
        return cuentaCorrienteRegistrada;

    }

    public static boolean clienteRegistrado(String dni) {

        boolean clienteRegistrado = false;
        for (Cliente aux : getListaTodosClientes()) {
            if (aux.getDni().equals(dni)) {
                clienteRegistrado = true;
            }
        }
        return clienteRegistrado;
    }

    public static boolean clienteRegistrado(Cliente cliente) {

        boolean clienteRegistrado = false;
        for (Cliente aux : getListaTodosClientes()) {
            if (aux == cliente) {
                clienteRegistrado = true;
            }
        }
        return clienteRegistrado;
    }

    public static boolean clienteYaEnCuenta(Cuenta cuenta, Cliente cliente) {

        boolean clienteYaEnCuenta = false;
        for (Cliente aux : cuenta.getClientes()) {
            if (aux == cliente) {
                clienteYaEnCuenta = true;
            }
        }
        return clienteYaEnCuenta;
    }

    public static Cliente encontrarCliente(String dni) {

        Cliente cliente = null;
        for (Cliente aux : getListaTodosClientes()) {
            if (aux.getDni().equals(dni)) {
                cliente = aux;
            }
        }
        return cliente;
    }

    public static Cuenta encontrarCuenta(String numeroCuenta) {

        Cuenta cuenta = null;
        for (Cuenta aux : getListaTodasCuentas()) {
            if (aux.getNumero().equals(numeroCuenta)) {
                cuenta = aux;
            }
        }
        return cuenta;
    }
    
        public static CuentaCorriente encontrarCuentaCorriente(String numeroCuenta) {

        CuentaCorriente cuenta = null;
        for (Cuenta aux : getListaTodasCuentas()) {
            if (aux.getNumero().equals(numeroCuenta)) {
                cuenta = (CuentaCorriente)aux;
            }
        }
        return cuenta;
    }

}
