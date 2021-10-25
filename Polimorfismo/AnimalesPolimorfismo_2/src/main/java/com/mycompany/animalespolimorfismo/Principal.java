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
import java.io.FileNotFoundException;
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
        String directorio = fichero.getAbsolutePath();
        try {
            fichero.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        OperacionesAnimales.addPerro(new Perro("aaa", 7, true));
        OperacionesAnimales.addGato(new Gato("hhh", 8, true));
        OperacionesAnimales.addPerro(new Perro("ddd", 7, true));
        OperacionesAnimales.addPerro(new Perro("eee", 7, true));
        OperacionesAnimales.addPerro(new Perro("fff", 7, true));
        OperacionesAnimales.addGato(new Gato("ccc", 9, false));

        try {
            if (fichero.length() == 0) {
                OperacionesAnimales.guardarPrimerosAnimalesADisco(directorio);
            } else {
                OperacionesAnimales.guardarOtrosAnimalesADisco(directorio);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        try {
            Animal[] arrayAnimales = OperacionesAnimales.leerAnimalesDisco(directorio);
            for (Animal aux : arrayAnimales) {
                System.out.println(aux);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
