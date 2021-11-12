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

        RandomAccessFile raf = new RandomAccessFile(fichero, "r");

        System.out.println("----- LISTA DE RECIBOS -----");
        

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

            System.out.println(datosPiso(ref,nombre,tipo,cuota, agua, calefaccion, otro,recibo));

        }

        raf.close();

    }

    private static String datosPiso(String ref, String nombre, char tipo, float cuota, float agua, float calefaccion, float otro, float recibo) {

        String datos = "RECIBO " + ref + "\n"
                + "Propietario: " + nombre + "\n"
                + "Tipo de piso: " + tipo + "\n"
                + "Cuota fija(€): " + cuota + "\n"
                + "Agua caliente(L): " + agua + "\n"
                + "Calefacción: " + calefaccion + "\n";
        if (tipo == 'A') {
            datos = datos + "Terraza(m2): " + otro + "\n"
                    + "\tTOTAL RECIBO(€): " + recibo + "\n";
        } else if (tipo == 'D') {
            datos = datos + "Cuota extra(€): " + otro + "\n"
                    + "\tTOTAL RECIBO(€): " + recibo + "\n";
        }
        return datos;
    }

}
