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
    public ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();

    public CuentaCorriente() {
        super();
    }

    public CuentaCorriente(String numero, String sucursal,ArrayList<Cliente> clientes) {
        super(numero, sucursal,clientes);
    }

    /**
     * @return the saldoActual
     */
    public double getSaldoActual() {
        return saldoActual;
    }

    /**
     * 
     */
    public void setSaldoActual() {
        this.saldoActual = this.saldoActual+movimientos.get(movimientos.size()-1).getSaldoActual();
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
    
     @Override
    public String toString() {
        String nombresClientes="";
        for(Cliente aux : getClientes()){
        nombresClientes=nombresClientes+aux.getNombre()+"\n";
        }
        String cuenta = "\nCUENTA CORRIENTE"
                + "\nNúmero de cuenta: " + getNumero()
                + "\nSucursal: " + getSucursal()
                + "\nSaldo(€): " + getSaldoActual()
                + "\nClientes:\n " + nombresClientes + "\n";
       // + "\nClientes:\n " + getClientes() + "\n";

        return cuenta;
    }

}
