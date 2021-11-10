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
public abstract class Cuenta implements Serializable {
    
    //atributos
    private String numero;
    private String sucursal;
    private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    private String tipo;
    
    //cosntructores
    /**
     * Constructor vacío.
     */
    public Cuenta() {

    }
    
    /**
     * Constructor
     * @param numero
     * @param sucursal
     * @param clientes 
     */
    public Cuenta(String numero, String sucursal, ArrayList<Cliente> clientes) {
        this.numero = numero;
        this.sucursal = sucursal;
        this.clientes = clientes;
    }

    //getters y setters
    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the sucursal
     */
    public String getSucursal() {
        return sucursal;
    }

    /**
     * @param sucursal the sucursal to set
     */
    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    /**
     * @return the clientes
     */
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    /**
     * @param clientes the clientes to set
     */
    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    /**
     * Indica el tipo de cuenta: plazo o corriente.
     * @return tipo
     */
    public String getTipo() {
        if (this instanceof CuentaCorriente) {
            tipo = "CORRIENTE";
        } else if (this instanceof CuentaPlazo) {
            tipo = "PLAZO";
        }
        return tipo;
    }

}
