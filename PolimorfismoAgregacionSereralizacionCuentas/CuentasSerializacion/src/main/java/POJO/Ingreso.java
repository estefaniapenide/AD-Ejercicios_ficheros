/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Estefania
 */
public class Ingreso extends Movimiento {
    
    //atributos
    private double saldoActual;
    
    //constructores
    /**
     * Contructor vacío
     */
    public Ingreso() {
        super();
    }
    
    /**
     * Constructor
     * @param cuenta
     * @param fechaOperacion
     * @param hora
     * @param cantidad 
     */
    public Ingreso(CuentaCorriente cuenta, Date fechaOperacion, Time hora, float cantidad) {
        super(cuenta, fechaOperacion, hora, cantidad);
    }
    
     /**
     * @return the saldoActual
     */
    @Override
    public double getSaldoActual() {
        return saldoActual;
    }

    @Override
    public void setSaldoActual() {
        saldoActual = getSaldoActual() + getCantidad();
    }

}
