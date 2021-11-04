/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author a20estefaniapc
 */
public class CuentaPlazo extends Cuenta {

    public float interes;
    public Date fechaVencimiento;
    public long depositoPlazo;

    public CuentaPlazo() {

    }

    public CuentaPlazo(String numero, String sucursal,ArrayList<Cliente> clientes) {
        super(numero, sucursal,clientes);
    }
    
        /**
     * @return the interes
     */
    public float getInteres() {
        return interes;
    }

    /**
     * @param interes the interes to set
     */
    public void setInteres(float interes) {
        this.interes = interes;
    }

    /**
     * @return the fechaVencimiento
     */
    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * @param fechaVencimiento the fechaVencimiento to set
     */
    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * @return the depositoPlazo
     */
    public long getDepositoPlazo() {
        return depositoPlazo;
    }

    /**
     * @param depositoPlazo the depositoPlazo to set
     */
    public void setDepositoPlazo(long depositoPlazo) {
        this.depositoPlazo = depositoPlazo;
    }
    
     @Override
    public String toString() {
        String nombresClientes="";
        for(Cliente aux : getClientes()){
        nombresClientes=nombresClientes+aux.getNombre()+"\n";
        }
        String cuenta = "\nCUENTA PLAZO"
                + "\nNúmero de cuenta: " + getNumero()
                + "\nSucursal: " + getSucursal()
                + "\nDepósito(€): " + getDepositoPlazo()
                + "\nClientes:\n" + nombresClientes + "\n";

        return cuenta;
    }

}
