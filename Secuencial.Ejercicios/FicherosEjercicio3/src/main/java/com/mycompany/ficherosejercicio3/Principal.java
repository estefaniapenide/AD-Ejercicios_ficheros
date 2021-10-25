/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 /*BYTES
    3)Crea una aplicación que copie un fichero binario a otra localización. 
    En lugar de leer y escribir byte a byte, crea un array de bytes con el 
    tamaño del fichero de origen (utiliza el método available()), 
    copia el contenido del fichero a este array y escribe a partir de este array.

    Recuerda que debes controlar las excepciones que puedan aparecer. 
    En caso de error, mostrar una ventana de dialogo con información del error.
 */
package com.mycompany.ficherosejercicio3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author a20estefaniapc
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        File ficheroBinario = new File("");
        ficheroBinario = buscarFichero(ficheroBinario);

        File localizacionDestino = new File("");
        localizacionDestino = buscarDirectorio(localizacionDestino);

        File ficheroCopiado = new File("");
        ficheroCopiado = copiarFichero(ficheroBinario, localizacionDestino);

        JOptionPane.showMessageDialog(null, ficheroBinario.getName() + " ha sido copiado a " + ficheroCopiado.getName() + "\n"
                + "Ruta: " + ficheroCopiado.getAbsolutePath(), "Archivos copiado", JOptionPane.INFORMATION_MESSAGE);

        /*Si en lugar de usar JOptionPane, se imprime por consola*/
//        System.out.println("\n"+ficheroBinario.getName()+" ha sido copiado a "+ficheroCopiado.getName()+"\n"
//        +"Ruta: "+ficheroCopiado.getAbsolutePath());
    }

    public static File buscarFichero(File fichero) {

        Scanner input = new Scanner(System.in);

        do {
            String rutaOrigen1 = JOptionPane.showInputDialog(null, "Indique la ruta del fichero binario: ", "Dirección fichero", JOptionPane.QUESTION_MESSAGE);

            /*Si en lugar de usar JOptionPane, se imprime por consola*/
//            System.out.println("Indique la ruta del fichero binario: ");
//            String rutaOrigen1 = ControlData.leerString(input);
            if (rutaOrigen1 != null) {
                fichero = new File(rutaOrigen1);

                if (!fichero.exists()) {
                    JOptionPane.showMessageDialog(null, "El fichero no existe.\nAsegúrese de incluir la extensión.", "¡Atención!", JOptionPane.ERROR_MESSAGE);
                    /*Si en lugar de usar JOptionPane, se imprime por consola*/
//                    System.out.println("El fichero no existe.");
                } else if (!fichero.isFile()) {
                    JOptionPane.showMessageDialog(null, "No es un archivo.", "¡Atención!", JOptionPane.ERROR_MESSAGE);
                    /*Si en lugar de usar JOptionPane, se imprime por consola*/
//                    System.out.println("No es un archivo.");
                }
            } else {
                System.exit(0);
            }

        } while (!fichero.exists() || !fichero.isFile());

        return fichero;

    }

    public static File buscarDirectorio(File directorio) {

        Scanner input = new Scanner(System.in);

        do {
            String rutaDestino = JOptionPane.showInputDialog(null, "Indique la ruta de la carpeta de destino:", "Ruta de destino", JOptionPane.QUESTION_MESSAGE);
            /*Si en lugar de usar JOptionPane, se imprime por consola*/
//            System.out.println("Indique la ruta de la carpeta de destino: ");
//            String rutaDestino = ControlData.leerString(input);
            if (rutaDestino != null) {
                directorio = new File(rutaDestino);

                if (!directorio.exists()) {
                    JOptionPane.showMessageDialog(null, "El directorio no existe.", "¡Atención!", JOptionPane.ERROR_MESSAGE);
                    /*Si en lugar de usar JOptionPane, se imprime por consola*/
//                    System.out.println("El directorio no existe.");
                } else if (!directorio.isDirectory()) {
                    JOptionPane.showMessageDialog(null, "No es una carpeta.", "¡Atención!", JOptionPane.ERROR_MESSAGE);
                    /*Si en lugar de usar JOptionPane, se imprime por consola*/
//                    System.out.println("No es una carpeta.");
                }
            } else {
                System.exit(0);
            }

        } while (!directorio.exists() || !directorio.isDirectory());

        return directorio;
    }

    public static File copiarFichero(File fichero, File localizacionDestino) {

//        String nombreFichero = quitarExtension(fichero.getName());
//        nombreFichero = nombreFichero + "copia";
        String nombreFichero = "copia_" + fichero.getName();

        File ficheroCopiado = new File(localizacionDestino, nombreFichero);
        try {
            DataInputStream lector = new DataInputStream(new FileInputStream(fichero));
            DataOutputStream escritor = new DataOutputStream(new FileOutputStream(ficheroCopiado));

            int tamano = 0;
            try {
                tamano = lector.available();

                byte[] buffer = new byte[tamano];
                while (lector.read(buffer) != -1) {
                    escritor.write(buffer);
                    escritor.flush();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        return ficheroCopiado;
    }

    public static String quitarExtension(String nombreFichero) {

        int punto = nombreFichero.lastIndexOf(".");
        nombreFichero = nombreFichero.substring(0, punto);

        return nombreFichero;

    }

}
