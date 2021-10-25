 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /*DATOS
    4)Crea una aplicación que almacene los datos básicos de un vehículo como 
    la matricula(String), marca (String), tamaño de deposito (double) y modelo (String) 
    en ese orden y de uno en uno usando la clase DataInputStream.

    Los datos anteriores datos se pedirán por teclado y se irán añadiendo al 
    fichero (no se sobrescriben los datos) cada vez que ejecutemos la aplicación.

    El fichero siempre sera el mismo, en todos los casos.

    Muestra todos los datos de cada coche en un cuadro de dialogo, es decir, 
    si tenemos 3 vehículos mostrara 3 cuadros de dialogo con sus respectivos datos. 
    
    Un ejemplo de salida de información puede ser este:
    
    El vehiculo tiene una matrícula 6691PJ, su marca es Opel, el tamaño depósito 
    es de 45.0 litros y su modelo es Astra.
 */
package com.mycompany.ficherosejercicio4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
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

        File fichero = new File("Lista_de_vehiculos.ddr");
        try {
            fichero.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String matricula = "";
        String marca = "";
        double deposito = 0;
        String modelo = "";
        String depos = "";

//        Scanner input = new Scanner(System.in);
//
//        System.out.println("DATOS DEL COCHE\n");
//        System.out.println("Matricula: ");
//        matricula = ControlData.leerString(input);
//
//        System.out.println("Marca: ");
//        marca = ControlData.leerString(input);
//
//        System.out.println("Tamaño del depósito(L): ");
//        deposito = ControlData.leerDouble(input);
//
//        System.out.println("Modelo: ");
//        modelo = ControlData.leerString(input);

        matricula = JOptionPane.showInputDialog(null, "Matricula: ", "DATOS DEL COCHE", JOptionPane.QUESTION_MESSAGE);
        if (matricula != null) {

            marca = JOptionPane.showInputDialog(null, "Marca: ", "DATOS DEL COCHE", JOptionPane.QUESTION_MESSAGE);
            if (marca != null) {

                depos = JOptionPane.showInputDialog(null, "Tamaño del deposito (L): ", "DATOS DEL COCHE", JOptionPane.QUESTION_MESSAGE);
                if (depos != null) {

                    deposito = Double.valueOf(depos);

                    modelo = JOptionPane.showInputDialog(null, "Modelo: ", "DATOS DEL COCHE", JOptionPane.QUESTION_MESSAGE);
                    if (modelo != null) {

                        try {

                            DataOutputStream flujoescritura = new DataOutputStream(new FileOutputStream(fichero, true));

                            escribirFichero(flujoescritura, matricula, marca, deposito, modelo);
                            
                            FileInputStream fis = new FileInputStream(fichero);
                            DataInputStream flujolectura = new DataInputStream(fis);

                            leerFichero(flujolectura, fis);

                        } catch (FileNotFoundException e) {
                            JOptionPane.showMessageDialog(null, e, "ERROR", JOptionPane.ERROR_MESSAGE);
//            e.printStackTrace();
                        } catch (IOException e) {
                            JOptionPane.showMessageDialog(null, e, "ERROR", JOptionPane.ERROR_MESSAGE);
//            e.printStackTrace();
                        }
                    } else {
                        System.exit(0);
                    }
                } else {
                    System.exit(0);
                }
            } else {
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }

    public static void escribirFichero(DataOutputStream flujoescritura, String matricula, String marca, double deposito, String modelo) throws IOException {

        flujoescritura.writeUTF(matricula);
        flujoescritura.writeUTF(marca);
        flujoescritura.writeDouble(deposito);
        flujoescritura.writeUTF(modelo);
    }

    public static void leerFichero(DataInputStream flujolectura, FileInputStream fis) throws IOException {

        while (fis.available() > 0) {
            JOptionPane.showMessageDialog(null, "El vehiculo tiene una matrícula " + flujolectura.readUTF()
                    + ", su marca es " + flujolectura.readUTF() + ", el tamaño depósito es de "
                    + flujolectura.readDouble() + " litros y su modelo es " + flujolectura.readUTF(), "DATOS", JOptionPane.INFORMATION_MESSAGE);
//            System.out.println("El vehiculo tiene una matrícula " + flujolectura.readUTF()
//                    + ", su marca es " + flujolectura.readUTF() + ", el tamaño depósito es de "
//                    + flujolectura.readDouble() + " litros y su modelo es " + flujolectura.readUTF());
        }

    }

}
