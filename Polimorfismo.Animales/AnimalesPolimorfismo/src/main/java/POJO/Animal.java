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
public abstract class Animal implements Serializable{

    private String nombre;
    private int edad;
    private String tipo;

    //constructores
    public Animal() {
    }

    public Animal(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    //getters y setters
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
     * @return the edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    public abstract String getExpresion();

    public String getTipo() {
        if (this instanceof Perro) {
            tipo = "PERRO";
        } else if (this instanceof Gato) {
            tipo = "GATO";
        }
        return tipo;
    }

}
