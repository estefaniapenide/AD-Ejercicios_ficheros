/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /*
  2)  Crea una aplicación que pida la ruta de dos ficheros de texto y de una ruta de destino (solo la ruta, sin fichero al final). 
    Debes copiar el contenido de los dos ficheros en uno, este tendrá el nombre de los dos ficheros separados por un guion bajo,
    este se guardara en la ruta donde le hayamos indicado por teclado.
    Para unir los ficheros en uno, crea un método donde le pases como parámetro todas las rutas. En este método, aparte de copiar 
    debe comprobar que si existe el fichero de destino, nos muestre un mensaje informándonos de si queremos sobrescribir el fichero. 
    Te recomiendo usar la clase File y JOptionPane.
    Por ejemplo, si tengo un fichero A.txt con “ABC” como contenido, un fichero B.txt con “DEF” y una 
    ruta de destino D:\, el resultado sera un fichero llamado A_B.txt en la ruta D:\ o la actual con el contenido “ABCDEF”.
 */
package gal.teis.ficherosejercicio2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 *
 * @author Estefania
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);

        File fichero1 = new File("");
        fichero1 = buscarFichero(fichero1, "primer");

        File fichero2 = new File("");
        fichero2 = buscarFichero(fichero2, "segundo");

        File directorio = new File("");
        directorio = buscarDirectorio(directorio);

        String nombreFichero1 = fichero1.getName();
        String nombreFichero2 = fichero2.getName();

        nombreFichero1 = quitarExtension(nombreFichero1);
        nombreFichero2 = quitarExtension(nombreFichero2);

        String nombreFicheroResultante = nombreFichero1 + "_" + nombreFichero2 + ".txt";

        File ficheroResultante = combinarDosFicheros(fichero1, fichero2, nombreFicheroResultante, directorio);

//        StringBuilder textoFicheroResultante = new StringBuilder();
//
//        textoFicheroResultante.append(leerFichero(fichero1));
//        textoFicheroResultante.append("\n");
//        textoFicheroResultante.append(leerFichero(fichero2));
//
//        File ficheroResultante = new File(directorio, nombreFicheroResultante);
//        escribirEnFichero(ficheroResultante, textoFicheroResultante.toString());
//        
        System.out.println("\n"+nombreFichero1+" y "+nombreFichero2+" han sido combinados en "+nombreFicheroResultante+"\n"
                + "Ruta: "+ficheroResultante.getAbsolutePath());
    }

    public static File buscarFichero(File fichero, String orden) {

        Scanner input = new Scanner(System.in);

        do {

            System.out.println("Indique la ruta del " + orden + " fichero de texto: ");
            String rutaOrigen1 = ControlData.leerString(input);

            fichero = new File(rutaOrigen1);

            if (!fichero.exists()) {
                System.out.println("El fichero no existe.");
            } else if (!fichero.isFile()) {
                System.out.println("No es un archivo.");
            }

        } while (!fichero.exists() || !fichero.isFile());

        return fichero;

    }

    public static File buscarDirectorio(File directorio) {

        Scanner input = new Scanner(System.in);

        do {
            System.out.println("Indique la ruta de la carpeta de destino: ");
            String rutaDestino = ControlData.leerString(input);

            directorio = new File(rutaDestino);

            if (!directorio.exists()) {
                System.out.println("El directorio no existe.");
            } else if (!directorio.isDirectory()) {
                System.out.println("No es una carpeta.");
            }

        } while (!directorio.exists() || !directorio.isDirectory());

        return directorio;
    }

    public static StringBuilder leerFichero(File fichero) throws IOException {

        StringBuilder textoFichero = new StringBuilder();

        Reader lector = new FileReader(fichero, Charset.forName("UTF-8"));
        char[] bufer = new char[1024];
        while (lector.read(bufer) != -1) {
            textoFichero.append(bufer);
        }
        lector.close();

        return textoFichero;

    }

    public static void escribirEnFichero(File fichero, String texto) throws IOException {

        Writer escritor = new FileWriter(fichero);
        escritor.write(texto);
        escritor.close();
    }

    public static String quitarExtension(String nombreFichero) {

        int punto = nombreFichero.lastIndexOf(".");
        nombreFichero = nombreFichero.substring(0, punto);

        return nombreFichero;

    }

    public static File combinarDosFicheros(File fichero1, File fichero2, String nombreFicheroResultante, File directorioFinal) throws IOException {

        File ficheroResultante = new File(directorioFinal, nombreFicheroResultante);

        BufferedReader lector1 = new BufferedReader(new FileReader(fichero1, Charset.forName("UTF-8")));
        BufferedReader lector2 = new BufferedReader(new FileReader(fichero2, Charset.forName("UTF-8")));

        BufferedWriter escritor = new BufferedWriter(new FileWriter(ficheroResultante, Charset.forName("UTF-8")));

        lector1.transferTo(escritor);
        escritor.write("\n");
        lector2.transferTo(escritor);
        
        escritor.close();

        return ficheroResultante;
    }

}
