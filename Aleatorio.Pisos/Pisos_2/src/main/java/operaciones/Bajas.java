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
public class Bajas {


    public static int bajaPiso(File fichero, int numRegs, Scanner input) throws FileNotFoundException, IOException {

        File temporal = new File("temporal.dat");
        RandomAccessFile rafTemp = new RandomAccessFile(temporal, "rw");
        RandomAccessFile raf = new RandomAccessFile(fichero, "r");

        Piso piso = new Atico();
        String referencia;
        byte b = 0;

        System.out.println("REFERENCIA DEL PISO A BORRAR:");
        referencia = ControlData.leerString(input);

        for (int i = 0; i < numRegs; i++) {
            raf.seek(i * 140);

            String ref = raf.readUTF();
            String nom = raf.readUTF();
            char tip = raf.readChar();
            float cuo = raf.readFloat();
            float agua = raf.readFloat();
            float cal = raf.readFloat();

            float ot = raf.readFloat();
            float recb = raf.readFloat();

            if (tip == 'A') {
                piso = new Atico(ref, nom, cuo, agua, cal, ot);
            } else if (tip == 'D') {
                piso = new Duplex(ref, nom, cuo, agua, cal, ot);
            }

            //piso.setTipoPiso(tip);//No hace falta, se pone solo
            piso.totalRbo();

            if (piso.getReferencia().compareToIgnoreCase(referencia) != 0) {

                rafTemp.seek(b * 140);
                rafTemp.writeUTF(piso.getReferencia());
                rafTemp.writeUTF(piso.getNombrePropietario());
                rafTemp.writeChar(piso.getTipoPiso());
                rafTemp.writeFloat(piso.getCuotaFija());
                rafTemp.writeFloat(piso.getLitrosAguaCaliente());
                rafTemp.writeFloat(piso.getPasosDeCalefaccion());
                if (piso instanceof Atico) {
                    rafTemp.writeFloat(((Atico) piso).getMetrosTerraza());
                } else if (piso instanceof Duplex) {
                    rafTemp.writeFloat(((Duplex) piso).getCuotaExtra());
                }
                rafTemp.writeFloat(piso.getTotalReciboComunidad());
                b++;
            }

        }

        raf.close();
        rafTemp.close();

        fichero.delete();
        temporal.renameTo(fichero);

        if (b == numRegs) {
            System.out.println("\nRECIBO NO ENCONTRADO");

        } else {
            System.out.println("\nRECIBO BORRADO");
            numRegs--;
        }
        return numRegs;

    }

}
