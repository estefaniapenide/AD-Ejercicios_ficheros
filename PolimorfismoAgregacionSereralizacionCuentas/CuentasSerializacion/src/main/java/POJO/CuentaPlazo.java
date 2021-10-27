/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

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

    public CuentaPlazo(String numero, String sucursal) {
        super(numero, sucursal);
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

}
