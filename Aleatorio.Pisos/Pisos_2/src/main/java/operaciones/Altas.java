/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operaciones;

import POO.Atico;
import POO.Duplex;
import POO.Piso;
import controldata.ControlData;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 *
 * @author a20estefaniapc
 */
public class Altas {

    public static int altaPiso(File fichero, int numRegs, Scanner input) throws IOException {

        Piso piso = new Atico();
        byte op = 0;

        do {
            System.out.println("-----ALTA PISO-----");
            System.out.println("TIPO DE PISO");
            System.out.println("1.-Atico");
            System.out.println("2.-Duplex\n");
            System.out.println("0.-Salir");
            op = ControlData.leerByte(input);

            switch (op) {
                case 1:
                    piso = nuevoAtico(input);
                    numRegs = nuevoPiso(fichero, piso, numRegs);
                    break;
                case 2:
                    piso = nuevoDuplex(input);
                    numRegs = nuevoPiso(fichero, piso, numRegs);
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("No ha escogido ninguna de las opciones.\n");
                    break;

            }

        } while (op != 0);

        return numRegs;

    }

    private static int nuevoPiso(File fichero, Piso piso, int numRegs) throws FileNotFoundException, IOException {

        if (piso.getTamReal() <= piso.getTamMax()) {
            RandomAccessFile raf = new RandomAccessFile(fichero, "rw");

            raf.seek(numRegs * piso.getTamMax());
            raf.writeUTF(piso.getReferencia());
            raf.writeUTF(piso.getNombrePropietario());
            raf.writeChar(piso.getTipoPiso());
            raf.writeFloat(piso.getCuotaFija());
            raf.writeFloat(piso.getLitrosAguaCaliente());
            raf.writeFloat(piso.getPasosDeCalefaccion());
            if (piso instanceof Atico) {
                raf.writeFloat(((Atico) piso).getMetrosTerraza());
            } else if (piso instanceof Duplex) {
                raf.writeFloat(((Duplex) piso).getCuotaExtra());
            }
            raf.writeFloat(piso.getTotalReciboComunidad());

            raf.close();
            numRegs++;
        } else {
            System.out.println("Tamaño Excedido. No es posible dar de alta el piso.");
        }
        return numRegs;

    }

    private static Atico nuevoAtico(Scanner input) {

        System.out.println("DATOS DEL ÁTICO");

        String referencia, nombrePropietario;
        float cuotaFija, litrosAguaCaliente, pasosCalefaccion, metrosTerraza;

        System.out.println("Referencia:");
        referencia = ControlData.leerString(input);

        System.out.println("Nombre del propietario:");
        nombrePropietario = ControlData.leerString(input);

        System.out.println("Cuota fija(€):");
        cuotaFija = ControlData.leerFloat(input);

        System.out.println("Litros de agua caliente(L):");
        litrosAguaCaliente = ControlData.leerFloat(input);

        System.out.println("Pasos de caleafcción:");
        pasosCalefaccion = ControlData.leerFloat(input);

        char a = 253;
        System.out.println("Metros terraza(m^2):");
        metrosTerraza = ControlData.leerFloat(input);

        Atico atico = new Atico(referencia, nombrePropietario, cuotaFija, litrosAguaCaliente, pasosCalefaccion, metrosTerraza);
        atico.totalRbo();

        return atico;

    }

    public static Duplex nuevoDuplex(Scanner input) {

        System.out.println("DATOS DEL DUPLEX");

        String referencia, nombrePropietario;
        float cuotaFija, litrosAguaCaliente, pasosCalefaccion, cuotaExtra;

        System.out.println("Referencia:");
        referencia = ControlData.leerString(input);

        System.out.println("Nombre del propietario:");
        nombrePropietario = ControlData.leerString(input);

        System.out.println("Cuota fija(€):");
        cuotaFija = ControlData.leerFloat(input);

        System.out.println("Litros de agua caliente(L):");
        litrosAguaCaliente = ControlData.leerFloat(input);

        System.out.println("Pasos de caleafcción:");
        pasosCalefaccion = ControlData.leerFloat(input);

        System.out.println("Cuota extra(€):");
        cuotaExtra = ControlData.leerFloat(input);

        Duplex duplex = new Duplex(referencia, nombrePropietario, cuotaFija, litrosAguaCaliente, pasosCalefaccion, cuotaExtra);
        duplex.totalRbo();

        return duplex;

    }

}
