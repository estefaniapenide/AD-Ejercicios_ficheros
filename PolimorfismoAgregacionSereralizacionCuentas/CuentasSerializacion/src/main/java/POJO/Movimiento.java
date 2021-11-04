/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 *
 * @author a20estefaniapc
 */
public abstract class Movimiento implements Serializable{
    
    private CuentaCorriente cuenta;
    private Date fechaOperacion;
    private Time hora;
    private float cantidad;
    
    
    public Movimiento(){
    
    }
    
    public Movimiento(CuentaCorriente cuenta,Date fechaOperacion, Time hora, float cantidad){
        this.cuenta=cuenta;
        this.fechaOperacion=fechaOperacion;
        this.hora=hora;
        this.cantidad=cantidad;
    }


    /**
     * @return the cuenta
     */
    public CuentaCorriente getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(CuentaCorriente cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * @return the fechaOperacion
     */
    public Date getFechaOperacion() {
        return fechaOperacion;
    }

    /**
     * @param fechaOperacion the fechaOperacion to set
     */
    public void setFechaOperacion(Date fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    /**
     * @return the hora
     */
    public Time getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(Time hora) {
        this.hora = hora;
    }

    /**
     * @return the cantidad
     */
    public float getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public abstract double getSaldoActual();
    public abstract void setSaldoActual();
    
    @Override
    public String toString(){
        String tipo="";
        if (this instanceof Ingreso) {
            tipo = "INGRESO";
        } else if (this instanceof Retirada) {
            tipo = "RETIRADA";
        }
        String movimiento="MOVIMIENTO\n"
                +"TIPO: "+tipo+"\n"
                +"Cuenta: "+cuenta.getNumero()+"\n"
                +"Fecha: "+fechaOperacion+"\n"
                +"Hora: "+hora+"\n"
                +"Cantidad(€): "+cantidad+"\n";
    return movimiento;
    }
}
