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

    public static boolean busquedaPerro(Perro perro) {

        boolean perroEnLista = false;
        for (int i = 0; i < perros.length; i++) {
            if (perros[i] == perro) {
                perroEnLista = true;
            }
        }
        return perroEnLista;
    }

    public static boolean addPerro(Perro perro) {

        boolean perroAnadido = false;
        for (int i = 0; i < perros.length; i++) {
            if (perros[i] == null && !busquedaPerro(perro)) {
                perros[i] = perro;
                perroAnadido = true;
            } else {
                perroAnadido = false;
            }
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

    public static boolean busquedaGato(Gato gato) {

        boolean gatoEnLista = false;
        for (Gato gato1 : gatos) {
            if (gato1 == gato) {
                gatoEnLista = true;
            }
        }
        return gatoEnLista;
    }

    public static boolean addGato(Gato gato) {

        boolean gatoAnadido = false;
        for (int i = 0; i < gatos.length; i++) {
            if (gatos[i] == null && !busquedaGato(gato)) {
                gatos[i] = gato;
                gatoAnadido = true;
            } else {
                gatoAnadido = false;
            }
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

    public static void guardarAnimalesADisco(String fichero) throws FileNotFoundException, IOException {
        //Tener en cuenta lo de escribir la cabecera sólo si no existe el archivo antes.Modificar!!!

        int i = contarPerros() + contarGatos();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero, true));

        oos.writeObject(i);
        for (Perro aux : perros) {
            if (aux != null) {
                oos.writeObject(aux);
            }
        }
        for (Gato aux : gatos) {
            if (aux != null) {
                oos.writeObject(aux);
            }
        }

        oos.close();

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
        return animales;
    }

}
