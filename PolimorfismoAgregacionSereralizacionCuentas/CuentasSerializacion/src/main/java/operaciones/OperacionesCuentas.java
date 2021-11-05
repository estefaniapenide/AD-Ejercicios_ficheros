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

        cuenta = new CuentaCorriente(numeroCuenta, sucursal, clientes);
        getListaTodasCuentas().add(cuenta);

        return cuenta;
    }

    public static CuentaPlazo altaCuentaPlazo(String numeroCuenta, String sucursal, ArrayList<Cliente> clientes) {

        CuentaPlazo cuenta;

        cuenta = new CuentaPlazo(numeroCuenta, sucursal, clientes);
        getListaTodasCuentas().add(cuenta);

        return cuenta;

    }

    public static Cliente altaCliente(String dni, String nombre, ArrayList<Cuenta> cuentas) {

        Cliente cliente;

        cliente = new Cliente(dni, nombre, cuentas);
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

    public static Cliente encontrarCliente(Cliente cliente) {

        Cliente c = null;
        for (Cliente aux : getListaTodosClientes()) {
            if (aux == cliente) {
                c = aux;
            }
        }
        return c;
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
    
       public static Cuenta encontrarCuenta(Cuenta cuenta) {

        Cuenta c = null;
        for (Cuenta aux : getListaTodasCuentas()) {
            if (aux == cuenta) {
                c = aux;
            }
        }
        return c;
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
                cuenta = (CuentaCorriente) aux;
            }
        }
        return cuenta;
    }

    public static void borrarCuenta(String numeroCuenta) {
        //Borra la cuenta de la lista de cuentas de un cliente
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

    public static void borrarClientesSinCuentas() {
        //Si un cliente no tiene cuentas asocidas, se borrará.
        for (Cliente aux : getListaTodosClientes()) {

            if (encontrarCliente(aux).getCuentas().isEmpty()) {
                String dni = encontrarCliente(aux).getDni();
          
                for (int j = 0; j < getListaTodosClientes().size(); j++) {
                    if (getListaTodosClientes().get(j).getDni().equals(dni)) {
                        getListaTodosClientes().set(j, null);
                    }
                }

            }
        }
        
        limpiarListaClientesDeNulles();
    }

    public static void limpiarListaClientesDeNulles() {
        
        //Al borrar los clientes sin cuentas, este deja un rastro de nulles en la listaTodosClientes. Este método los elimina.
        for (int i = 0; i < getListaTodosClientes().size(); i++) {
            if (Objects.isNull(getListaTodosClientes().get(i))) {
                getListaTodosClientes().remove(i);
            }
        }
    }
    
    
    public static void borrarClienteDeUnaCuenta(String dni){
        
         //Borra el cliente de la lista de clientes de una cuenta
        for (Cuenta aux : encontrarCliente(dni).getCuentas()) {
            for (int i = 0; i < encontrarCuenta(aux).getClientes().size(); i++) {
                encontrarCuenta(aux).getClientes().set(i, null);
                encontrarCuenta(aux).getClientes().remove(i);
            }
        }
        
        //Borra el cliente de la lista general
        for (int i = 0; i < getListaTodosClientes().size(); i++) {
            if (getListaTodosClientes().get(i).getDni().equals(dni)) {
                getListaTodosClientes().set(i, null);
                getListaTodosClientes().remove(i);
            }
        }
    
        
    }
    
       public static void borrarCuentasSinClientes() {
        //Si una cuenta no tiene clientes asociados, se borrará.
        for (Cuenta aux : getListaTodasCuentas()) {

            if (encontrarCuenta(aux).getClientes().isEmpty()) {
                String numeroCuenta = encontrarCuenta(aux).getNumero();

                for (int j = 0; j < getListaTodasCuentas().size(); j++) {
                    if (getListaTodasCuentas().get(j).getNumero().equals(numeroCuenta)) {
                        getListaTodasCuentas().set(j, null);
                    }
                }

            }
        }

    }
       
          public static void limpiarListaCuentasDeNulles() {
        
        //Al borrar las cuentas sin clientes, este deja un rastro de nulles en la listaTodasCuentas. Este método los elimina.
        for (int i = 0; i < getListaTodasCuentas().size(); i++) {
            if (Objects.isNull(getListaTodasCuentas().get(i))) {
                getListaTodasCuentas().remove(i);
            }
        }
    }

}
