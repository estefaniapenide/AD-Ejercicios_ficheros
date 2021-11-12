/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operaciones;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author a20estefaniapc
 */
public class OperacionesFichero {
    
    //He puesto un tamaño de 140!!!(por eso divido entre 140)
    public static int leerFichero(File fichero) throws FileNotFoundException, IOException {

        int numRegs = 0;

        RandomAccessFile raf = new RandomAccessFile(fichero, "rw");

        numRegs = (int) Math.ceil((float) raf.length() / 140);
        raf.close();

        return numRegs;

    }
}
