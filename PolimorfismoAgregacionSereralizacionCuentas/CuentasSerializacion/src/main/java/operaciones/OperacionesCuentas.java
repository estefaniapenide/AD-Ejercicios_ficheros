/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cuentasserializacion;

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
    
    private static ArrayList<Cuenta> listaTodasCuentas;
    private static ArrayList<Cliente> listaTodosClientes;

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
        //Comprobar que la cuenta no exista ya
        CuentaCorriente cuenta;
        
        if (!numeroCuentaValido(numeroCuenta)) {
            cuenta = null;
        } else {
            cuenta = new CuentaCorriente(numeroCuenta,sucursal);
            cuenta.setClientes(clientes);
            listaTodasCuentas.add(cuenta);
        }

        return cuenta;
    }
    
        public static CuentaPlazo altaCuentaPlazo(String numeroCuenta, String sucursal, ArrayList<Cliente> clientes) {

        CuentaPlazo cuenta;
        
        if (!numeroCuentaValido(numeroCuenta)) {
            cuenta = null;
        } else {
            cuenta = new CuentaPlazo(numeroCuenta,sucursal);
            cuenta.setClientes(clientes);
            listaTodasCuentas.add(cuenta);
        }

        return cuenta;
    }
        
        public static void altaCliente(){
        
        }

}
