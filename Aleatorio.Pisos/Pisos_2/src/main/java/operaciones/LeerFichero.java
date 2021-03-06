/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operaciones;

import POO.Piso;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author a20estefaniapc
 */
public class LeerFichero {

    public static int contarNumRegistrosFichero(File fichero) throws FileNotFoundException, IOException {

        int numRegs = 0;

        RandomAccessFile raf = new RandomAccessFile(fichero, "rw");

        numRegs = (int) Math.ceil((float) raf.length() / 140);
        raf.close();

        return numRegs;

    }

    public static void visualizarDatosFichero(File fichero, int numRegs) throws IOException {

        if (fichero.length() == 0) {
            System.out.println("NO HAY PISOS REGISTRADOS\n");
        } else {

            RandomAccessFile raf = new RandomAccessFile(fichero, "r");

            System.out.println("LISTA DE PISOS");

            for (int i = 0; i < numRegs; i++) {

                raf.seek(i * 140);
                String ref = raf.readUTF();
                String nombre = raf.readUTF();
                char tipo = raf.readChar();
                float cuota = raf.readFloat();
                float agua = raf.readFloat();
                float calefaccion = raf.readFloat();

                float otro = raf.readFloat();

                float recibo = raf.readFloat();

                System.out.println(datosPiso(ref, nombre, tipo, cuota, agua, calefaccion, otro, recibo));

            }

            raf.close();

        }

    }

    public static void visulaizarPisosDePropietario(String nombrePropietario, File fichero, int numRegs) throws IOException {

        if (fichero.length() == 0) {
            System.out.println("NO HAY PISOS REGISTRADOS\n");
        } else {

            RandomAccessFile raf = new RandomAccessFile(fichero, "r");

            System.out.println("\nPISOS DE " + nombrePropietario.toUpperCase() + "\n");

            for (int i = 0; i < numRegs; i++) {

                raf.seek(i * 140);
                String ref = raf.readUTF();
                String nombre = raf.readUTF();
                char tipo = raf.readChar();
                float cuota = raf.readFloat();
                float agua = raf.readFloat();
                float calefaccion = raf.readFloat();

                float otro = raf.readFloat();

                float recibo = raf.readFloat();

                if (nombre.compareToIgnoreCase(nombrePropietario) == 0) {
                    System.out.println(datosPiso(ref, nombre, tipo, cuota, agua, calefaccion, otro, recibo));
                }
            }

            raf.close();

        }

    }

    private static String datosPiso(String ref, String nombre, char tipo, float cuota, float agua, float calefaccion, float otro, float recibo) {

        String datos = "PISO " + ref + "\n"
                + "Propietario: " + nombre + "\n"
                + "Tipo de piso: " + tipo + "\n"
                + "Cuota fija(???): " + cuota + "\n"
                + "Agua caliente(L): " + agua + "\n"
                + "Calefacci??n: " + calefaccion + "\n";
        if (tipo == 'A') {
            datos = datos + "Terraza(m2): " + otro + "\n"
                    + "\tTOTAL RECIBO(???): " + recibo + "\n";
        } else if (tipo == 'D') {
            datos = datos + "Cuota extra(???): " + otro + "\n"
                    + "\tTOTAL RECIBO(???): " + recibo + "\n";
        }
        return datos;
    }

}
