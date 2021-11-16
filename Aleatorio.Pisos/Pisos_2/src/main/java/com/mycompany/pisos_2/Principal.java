/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pisos_2;

import controldata.ControlData;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;
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
                        System.out.println("RECIBOS");
                        if (diaDeHoy() == 25) {
                            LeerFichero.visualizarDatosFichero(fichero, numRegs);
                        } else {
                            System.out.println("Error en fecha.\nLos recibos sólo se pueden listar el día 25 de cada mes.\n");
                        }
                        break;
                    case 6:
                        System.out.println("INTRODUZCA EL NOMBRE DEL PROPIETARIO:");
                        String nombrePropietario = ControlData.leerString(input);
                        LeerFichero.visulaizarPisosDePropietario(nombrePropietario, fichero, numRegs);
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
    
    /**
     * Devuelve el día de mes de hoy
     * @return 
     */
    public static int diaDeHoy() {

        Date fecha1 = new Date(new java.util.Date().getTime());
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(fecha1);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return day;
    }
    
    /**
     * Devuelve el menú principal
     * @return 
     */
    public static String menuPrincipal() {
        String menuPrincipal = "PISOS\n"
                + "1.-Altas\n"
                + "2.-Bajas\n"
                + "3.-Modificaciones\n"
                + "4.-Listado pisos\n"
                + "5.-Listado recibos (sólo se pueden ver el 25 del mes)\n"
                + "6.-Listado pisos por propietario\n\n"
                + "0.-Salir\n";

        return menuPrincipal;
    }

}
