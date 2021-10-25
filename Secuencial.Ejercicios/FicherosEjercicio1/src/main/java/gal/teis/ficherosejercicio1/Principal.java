/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.teis.ficherosejercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;

/**
 *
 * @author Estefania
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        File directorio = new File("directorio");

        File fichero1 = new File(directorio, "fichero1.txt");
        File fichero2 = new File(directorio, "fichero2.txt");
        File fichero3 = new File(directorio, "fichero3.txt");

        directorio.mkdir();

        try {
            fichero1.createNewFile();
            fichero2.createNewFile();
            fichero3.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        verInfoFichero(fichero1);

        String texto = "Esto es una prueba";
        escribirEnFichero(fichero1, texto);
        contenidoTextoSinEspacios(leerFichero(fichero1));

    }

    public static void verInfoFichero(File fichero) {
        System.out.println("INFORMACIÓN DEL FICHERO");

        if (fichero.exists()) {
            System.out.println("Nombre fichero: " + fichero.getName() + "\n"
                    + "Ruta: " + fichero.getPath() + "\n"
                    + "Ruta absoluta: " + fichero.getAbsolutePath() + "\n"
                    + "Se puede leer: " + fichero.canRead() + "\n"
                    + "Se puede escrbir: " + fichero.canWrite() + "\n"
                    + "Tamaño: " + fichero.length() + "\n"
                    + "Última modificación: " + fichero.lastModified() + "\n"
                    + "\nEs un directorio: " + fichero.isDirectory() + "\n"
                    + "Es un fichero: " + fichero.isFile() + "\n"
                    + "Nombre del directorio padre: " + fichero.getParent());
            if (fichero.isDirectory()) {
                System.out.println("Ficheros dentro del fichero: ");
                verDirectorio(fichero);
            } else {
                System.out.println("No es un directorio para listar.");
            }
        }

    }

    public static void verDirectorio(File fichero) {

        File[] listaFicheros = fichero.listFiles();
        System.out.println("Ficheros en el directorio actual: " + listaFicheros.length);
        for (int i = 0; i < listaFicheros.length; i++) {
            File f = listaFicheros[i];
            System.out.println(f.getName());
        }
    }

    public static StringBuilder leerFichero(File fichero) {

        StringBuilder textoFichero = new StringBuilder();

        try {
            Reader lector = new FileReader(fichero, Charset.forName("UTF-8"));
            char[] bufer = new char[1024];
            while (lector.read(bufer) != -1) {
                textoFichero.append(bufer);
            }
            lector.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return textoFichero;

    }

    //Puebras con BuffredReader y BufferedWriter
    public static void leerFicheroConBufferedReaderEscribirloArchivoTemporalBufferedWriter(File fichero) {

        int texto;
        File ficheroTemporal = new File("ficheroTemporal.txt");
        try {

            BufferedReader lector = new BufferedReader(new FileReader(fichero, Charset.forName("UTF-8")));

            BufferedWriter escritor = new BufferedWriter(new FileWriter(ficheroTemporal, Charset.forName("UTF-8")));

            while ((texto = lector.read()) != -1) {
                escritor.write(texto);
            }
            lector.close();
            escritor.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        ficheroTemporal.deleteOnExit();

    }

    public static void contenidoTextoSinEspacios(StringBuilder textoFichero) {

        for (int i = 0; i < textoFichero.length(); i++) {
            if (textoFichero.charAt(i) == 32) {
                textoFichero.replace(i, i + 1, "");
            }
        }
        System.out.println("\nContenido del fichero sin espacios:\n" + textoFichero);

    }

    public static void escribirEnFichero(File fichero, String texto) {

        try {
            Writer escritor = new FileWriter(fichero);
            escritor.write(texto);
            escritor.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
