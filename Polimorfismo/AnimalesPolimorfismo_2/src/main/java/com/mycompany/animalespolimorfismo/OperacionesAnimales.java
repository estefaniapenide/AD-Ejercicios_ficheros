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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author a20estefaniapc
 */
public class OperacionesAnimales {

    private static Perro[] perros = new Perro[5];
    private static Gato[] gatos = new Gato[5];

    public static boolean addPerro(Perro perro) {

        boolean perroAnadido = false;
        if (contarPerros() < perros.length) {
            perros[contarPerros()] = perro;
            perroAnadido = true;
        } else {
            perroAnadido = false;
        }
        return perroAnadido;
    }

    public static int contarPerros() {
        int numPerros = 0;
        for (Perro aux : perros) {
            if (aux != null) {
                numPerros++;
            }
        }
        return numPerros;
    }

    public static boolean addGato(Gato gato) {

        boolean gatoAnadido = false;
        if (contarGatos() < gatos.length) {
            gatos[contarGatos()] = gato;
            gatoAnadido = true;
        } else {
            gatoAnadido = false;
        }
        return gatoAnadido;
    }

    public static int contarGatos() {
        int numGatos = 0;
        for (Gato aux : gatos) {
            if (aux != null) {
                numGatos++;
            }
        }
        return numGatos;
    }
    
        public static void escribirArrayObjectosFichero(String fichero) throws FileNotFoundException, IOException {
        
         MiObjectOutputStream moos = new MiObjectOutputStream(new FileOutputStream(fichero,true));
         
          for (Perro aux : perros) {
            if (aux != null) {
                moos.writeObject(aux);
            }
        }
        for (Gato aux : gatos) {
            if (aux != null) {
                moos.writeObject(aux);
            }
        }

        moos.close();

    }

    public static void guardarPrimerosAnimalesADisco(String fichero) throws FileNotFoundException, IOException {
           
        int i = contarPerros() + contarGatos();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero));

        oos.writeObject(i);
        escribirArrayObjectosFichero(fichero);
    }

    public static void guardarOtrosAnimalesADisco(String fichero) throws FileNotFoundException, IOException, ClassNotFoundException {

        //Leo todo lo que ya estaba escrito en el fichero 
        Animal[] animales = leerAnimalesDisco(fichero);

        //Cuento los animales que había y le sumo los que se van a añadir ahora
        int i = animales.length;
        i = i + contarPerros() + contarGatos();

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero));

        //Reescribo lo que ya estaba escrito con cabecera y cambiando el int inicial
        oos.writeObject(i);
        for (Animal aux : animales) {
            if (aux != null) {
                oos.writeObject(aux);
            }
        }   
        //Añado los perros y gatos nuevos
        escribirArrayObjectosFichero(fichero);


    }

    public static Animal[] leerAnimalesDisco(String fichero) throws FileNotFoundException, IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream(fichero);
        ObjectInputStream ois = new ObjectInputStream(fis);

        int i = (Integer) ois.readObject();

        Animal[] animales = new Animal[i];

        int j = 0;
        while (fis.available() > 0) {
            Animal animal = (Animal) ois.readObject();
            animales[j] = animal;
            j++;
        }
        ois.close();

        return animales;
    }

}
