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
public class Cliente implements Serializable {
    
    //atributos
    private String dni = "";
    private String nombre = "";
    private String direccion = "";
    private ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
    
    //conestructores
    /**
     * Constructor vacío.
     */
    public Cliente() {

    }
    
    /**
     * Constructor
     * @param dni
     * @param nombre
     * @param cuentas 
     */
    public Cliente(String dni, String nombre, ArrayList<Cuenta> cuentas) {
        this.dni = dni;
        this.nombre = nombre;
        this.cuentas = cuentas;
    }

    /**
     * @return the dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the cuentas
     */
    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    /**
     * @param cuentas the cuentas to set
     */
    public void setCuentas(ArrayList<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    @Override
    public String toString() {
        String cuentas=" ";
        for(Cuenta aux : getCuentas()){
        cuentas=cuentas+aux.getNumero()+"\n ";
        }
        
        String cliente = "\nCLIENTE"
                + "\nDNI: " + getDni()
                + "\nNombre: " + getNombre()
                + "\nDirección: " + getDireccion()
                + "\nCuentas:\n" + cuentas;

        return cliente;
    }
}
