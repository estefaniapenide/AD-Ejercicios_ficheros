/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author a20estefaniapc
 */
public class CuentaCorriente extends Cuenta implements Serializable{
    
    public double saldoActual;
    public ArrayList<Movimiento> movimientos;

    public CuentaCorriente() {
        super();
    }

    public CuentaCorriente(String numero, String sucursal) {
        super(numero, sucursal);
    }

    /**
     * @return the saldoActual
     */
    public double getSaldoActual() {
        return saldoActual;
    }

    /**
     * @param saldoActual the saldoActual to set
     */
    public void setSaldoActual(double saldoActual) {
        this.saldoActual = saldoActual;
    }

    /**
     * @return the movimientos
     */
    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }

    /**
     * @param movimientos the movimientos to set
     */
    public void setMovimientos(ArrayList<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

}
