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
public class Perro extends Animal{

    private boolean raboLargo;
    private String expresion = "GUAU";

    public Perro() {
        super();
    }

    public Perro(String nombre, int edad, boolean raboLargo) {
        super(nombre, edad);
        this.raboLargo = raboLargo;

    }

    /**
     * @return the raboLargo
     */
    public boolean getRaboLargo() {
        return raboLargo;
    }

    /**
     * @param raboLargo the raboLargo to set
     */
    public void setRaboLargo(boolean raboLargo) {
        this.raboLargo = raboLargo;
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
                + "\tRabo largo: " + raboLargo+"\n"
                 + "\tExpresion: "+expresion;;
        return perro;
    }

}
