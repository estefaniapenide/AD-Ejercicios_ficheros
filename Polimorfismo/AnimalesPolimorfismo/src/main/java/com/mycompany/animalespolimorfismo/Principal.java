/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.animalespolimorfismo;

import POJO.Animal;
import POJO.Gato;
import POJO.Perro;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author a20estefaniapc
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        File fichero = new File("fichero.ddr");
        try {
            fichero.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        OperacionesAnimales.addPerro(new Perro("aaa", 7, true));
        OperacionesAnimales.addPerro(new Perro("bbb", 7, true));
        OperacionesAnimales.addGato(new Gato("ccc", 8, false));
        try {
            OperacionesAnimales.guardarAnimalesADisco(fichero.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Animal[] arrayAnimales= OperacionesAnimales.leerAnimalesDisco(fichero.getName());
            for(Animal aux : arrayAnimales){
            System.out.println(aux);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
