/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

/**
 *
 * @author a20estefaniapc
 */
public class Vehiculo implements Serializable {

    private String matricula;
    private String marca;
    transient private double deposito;
    private String modelo;

    public Vehiculo() {

    }

    public Vehiculo(String matricula, String marca, double deposito, String modelo) {
        this.matricula = matricula;
        this.marca = marca;
        this.deposito = deposito;
        this.modelo = modelo;
    }

    /**
     * @return the matricula
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @return the deposito
     */
    public double getDeposito() {
        return deposito;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param matricula the matricula to set
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @param deposito the deposito to set
     */
    public void setDeposito(double deposito) {
        this.deposito = deposito;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        String vehiculo = "DATOS DEL COCHE\n"
                + "\tMatricula: " + matricula + "\n"
                + "\tMarca: " + marca + "\n"
                + "\tDepósito (L): " + deposito + "\n"
                + "\tModelo: " + modelo;

        return vehiculo;
    }

}
