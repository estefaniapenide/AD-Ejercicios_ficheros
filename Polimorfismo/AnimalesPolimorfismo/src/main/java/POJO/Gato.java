/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import java.io.Serializable;

/**
 *
 * @author a20estefaniapc
 */
public class Gato extends Animal {

    private boolean bigoteLargo;
    private String expresion = "MIAU";

    public Gato() {
        super();
    }

    public Gato(String nombre, int edad, boolean bigoteLargo) {
        super(nombre, edad);
        this.bigoteLargo = bigoteLargo;
    }

    /**
     * @return the bigoteLargo
     */
    public boolean getBigoteLargo() {
        return bigoteLargo;
    }

    /**
     * @param bigoteLargo the bigoteLargo to set
     */
    public void setBigoteLargo(boolean bigoteLargo) {
        this.bigoteLargo = bigoteLargo;
    }

    @Override
    public String getExpresion() {
        return expresion;
    }

    @Override
    public String toString() {
        String perro = "DATOS "+super.getTipo()+"\n"
                + "\tNombre: " + super.getNombre() + "\n"
                + "\tEdad: " + super.getEdad()+"\n"
                + "\tExpresion: "+expresion;
        return perro;
    }

}
