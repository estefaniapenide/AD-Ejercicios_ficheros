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

    public String dni = "";
    public String nombre = "";
    public String direccion = "";
    public ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();

    public Cliente() {

    }

    public Cliente(String dni, String nombre,ArrayList<Cuenta> cuentas) {
        this.dni = dni;
        this.nombre = nombre;
        this.cuentas=cuentas;
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
        String cliente = "\nCLIENTE"
                + "\nDNI: " + getDni()
                + "\nNombre: " + getNombre()
                + "\nDirección: " + getDireccion() + "\n";

        return cliente;
    }
}
