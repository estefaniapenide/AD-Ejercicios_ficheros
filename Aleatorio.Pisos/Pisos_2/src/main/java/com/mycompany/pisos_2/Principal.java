/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pisos_2;

import controldata.ControlData;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import operaciones.Altas;
import operaciones.Bajas;
import operaciones.LeerFichero;
import operaciones.Modificaciones;


/**
 *
 * @author a20estefaniapc
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        File fichero = new File("RandomPisos.dat");

        try {
            fichero.createNewFile();
            int numRegs = LeerFichero.contarNumRegistrosFichero(fichero);

            byte op = 0;
            do {
                System.out.println(menuPrincipal());
                op = ControlData.leerByte(input);

                switch (op) {
                    case 1:
                        numRegs = Altas.altaPiso(fichero, numRegs, input);
                        break;
                    case 2:
                        numRegs = Bajas.bajaPiso(fichero, numRegs, input);
                        break;
                    case 3:
                        Modificaciones.modificar(fichero, numRegs, input);
                        break;
                    case 4:
                        LeerFichero.visualizarDatosFichero(fichero, numRegs);
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 0:
                        System.out.println("----PROGRAMA FINALIZADO----");
                        break;
                    default:
                        System.out.println("No ha introducido ninguna de las opciones.");
                        break;

                }

     
            } while (op != 0);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String menuPrincipal() {
        String menuPrincipal = "PISOS\n"
                + "1.-Altas\n"
                + "2.-Bajas\n"
                + "3.-Modificaciones\n"
                + "4.-Listado pisos\n"
                + "5.-Listado recibos\n"
                + "6.-Listado pisos por propietario\n\n"
                + "0.-Salir\n";

        return menuPrincipal;
    }

}
