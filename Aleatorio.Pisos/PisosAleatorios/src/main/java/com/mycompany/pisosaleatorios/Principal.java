/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pisosaleatorios;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import operaciones.Altas;
import operaciones.Bajas;
import operaciones.OperacionesFichero;

/**
 *
 * @author a20estefaniapc
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        
        Scanner input = new Scanner(System.in);
        
        File fichero = new File("RandomPisos.dat");
        
        try{
        fichero.createNewFile();
        int numRegs=OperacionesFichero.leerFichero(fichero);
        numRegs=Altas.addPiso(fichero, numRegs, input);
        System.out.println(numRegs);
        numRegs=Bajas.bajaPiso(fichero, numRegs, input);
        System.out.println(numRegs);
        }catch(IOException e){
        e.printStackTrace();
        }
        
        
        
    }
    
}
