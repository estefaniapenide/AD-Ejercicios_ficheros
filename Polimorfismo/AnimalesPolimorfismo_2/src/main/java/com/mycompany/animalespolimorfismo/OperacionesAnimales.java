/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.animalespolimorfismo;

import POJO.Animal;
import POJO.Gato;
import POJO.Perro;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 *
 * @author a20estefaniapc
 */
public class OperacionesAnimales {

    private static Perro[] perros = new Perro[5];
    private static Gato[] gatos = new Gato[5];

    //Arrays donde se guaradrán los perros y gatos que estén en el fichero
    //(creo otros arrays porque el fichero puede guradar más de 5 gatos y 5 perros)
    private static Perro[] perrosFichero;
    private static Gato[] gatosFichero;

    /**
     * Añade un perro al array de perros y devuelve true en caso de haberse
     * añadido y false en caso de no poder ser añadido
     *
     * @param perro
     * @return
     */
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

    /**
     * Cuenta los perros que hay en el array perros.
     *
     * @return
     */
    public static int contarPerros() {
        int numPerros = 0;
        for (Perro aux : perros) {
            if (aux != null) {
                numPerros++;
            }
        }
        return numPerros;
    }

    /**
     * Añade un gato al array de gatos y devuelve true en caso de haberse
     * añadido y false en caso de no poder ser añadido
     *
     * @param gato
     * @return
     */
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

    /**
     * Cuenta los gatos que hay en el array gatos.
     *
     * @return
     */
    public static int contarGatos() {

        int numGatos = 0;
        for (Gato aux : gatos) {
            if (aux != null) {
                numGatos++;
            }
        }
        return numGatos;
    }
    
    /**
     * Pone a null los elementos de los arrays perros y gatos.
     */
    public static void vaciarArraysPerrosGatos() {
        
        for (int i =0; i<perros.length;i++) {
            perros[i] = null;
        }
        
        for (int i =0; i<gatos.length;i++) {
            gatos[i] = null;
        }
    }

    /**
     * Escribe en un fichero los animales sin cabecera
     *
     * @param fichero
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void escribirArrayObjectosFichero(String fichero) throws FileNotFoundException, IOException {

        MiObjectOutputStream moos = new MiObjectOutputStream(new FileOutputStream(fichero, true));

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

    /**
     * Escribe en un fichero vacío el número de animales que se van a introducir
     * y los animales.
     *
     * @param fichero
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void guardarPrimerosAnimalesADisco(String fichero) throws FileNotFoundException, IOException {

        int i = contarPerros() + contarGatos();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero));

        oos.writeObject(i);
        escribirArrayObjectosFichero(fichero);
    }

    /**
     * Cuando el fichero no está vacío y se pretende añadir más animales
     *
     * @param fichero
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
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

    /**
     * Lee el fichero: número de animales en el fichero y los animales. Lo
     * animales los mete en un array de animales de longitud igual al número de
     * animales indicados al principio.
     *
     * @param fichero
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
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

    /**
     * Clasifica los perros y los gatos recuperados del fichero, metiendolos en
     * arrays correspondientes con el tamaño adecuado
     *
     * @param fichero
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     */
    public static void calsificarPerrosGatos(String fichero) throws IOException, FileNotFoundException, ClassNotFoundException {

        Animal[] animales = leerAnimalesDisco(fichero);

        int contadorPerros = 0;
        int contadorGatos = 0;

        //Cuenta los perros y gatos del array animales para poder indicar el tamaño de los
        //arrays perrosFichero y gatosFichero
        for (Animal aux : animales) {
            if (aux instanceof Perro) {
                contadorPerros++;
            } else if (aux instanceof Gato) {
                contadorGatos++;
            }
        }

        perrosFichero = new Perro[contadorPerros];
        gatosFichero = new Gato[contadorGatos];

        //Mete los perros y los gatos en sus respectivos arrays
        for (Animal aux : animales) {
            if (aux instanceof Perro) {
                addPerroDelFichero(aux);
            } else if (aux instanceof Gato) {
                addGatoDelFichero(aux);
            }
        }

    }
    
    /**
     * Cuenta los perros que hay en el array perrosFichero.
     * @return 
     */
    public static int contarPerrosFichero() {

        int numPerros = 0;
        for (Perro aux : perrosFichero) {
            if (aux != null) {
                numPerros++;
            }
        }
        return numPerros;
    }
    
    /**
     * Cuenta los gatos que hay en el array gatosFichero.
     * @return 
     */
    public static int contarGatosFichero() {

        int numGatos = 0;
        for (Gato aux : gatosFichero) {
            if (aux != null) {
                numGatos++;
            }
        }
        return numGatos;
    }

    /**
     * Añade los perros del fichero al array perrosFichero
     *
     * @param perro
     * @return
     */
    public static boolean addPerroDelFichero(Animal perro) {

        boolean perroAnadido = false;
        if (contarPerrosFichero() < perrosFichero.length) {
            perrosFichero[contarPerrosFichero()] = (Perro) perro;
            perroAnadido = true;
        } else {
            perroAnadido = false;
        }
        return perroAnadido;
    }

    /**
     * Añade los gatos del fichero al array gatosFichero
     *
     * @param gato
     * @return
     */
    public static boolean addGatoDelFichero(Animal gato) {

        boolean gatoAnadido = false;
        if (contarGatosFichero() < gatosFichero.length) {
            gatosFichero[contarGatosFichero()] = (Gato) gato;
            gatoAnadido = true;
        } else {
            gatoAnadido = false;
        }
        return gatoAnadido;
    }
    
    /**
     * Comprueba si el fichero está en blanco.
     * @param fichero
     * @return
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static boolean ficheroVacio(String fichero) throws FileNotFoundException, IOException {

        boolean ficheroVacio = false;
        FileInputStream fis = new FileInputStream(fichero);
        if (fis.available() == 0) {
            ficheroVacio = true;
        }
        return ficheroVacio;
    }

}
