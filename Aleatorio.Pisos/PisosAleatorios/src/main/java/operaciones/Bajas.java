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
    
        public static int bajaPiso (File fichero, int numRegs, Scanner input) throws FileNotFoundException, IOException {
        
        System.out.println("BAJA");

        byte op=0;
        do{
            System.out.println("TIPO DE PISO");
            System.out.println("1.-Atico");
            System.out.println("2.-Duplex\n");
            System.out.println("0.-Salir");
            op = ControlData.leerByte(input);

            switch(op){
                case 1:
                    numRegs = bajaAtico(fichero, numRegs,input);
                    System.out.println(numRegs);
                    break;
                case 2:
                     //numRegs = bajaDuplex(fichero, numRegs,input);
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("No ha escogido ninguna de las opciones.\n");
                    break;
            
            }
        
        }while(op!=0);
        
        return numRegs;

    }
        
        public static int bajaAtico(File fichero, int numRegs, Scanner input) throws FileNotFoundException, IOException{
            
        File temporal=new File("temporal.dat");
        RandomAccessFile rafTemp=new RandomAccessFile(temporal,"rw");
        RandomAccessFile raf=new RandomAccessFile(fichero,"r");
        
        Piso piso;
        String referencia;
        byte b=0;
        
        System.out.println("Referencia ático a borrar:");
        referencia=ControlData.leerString(input);
        
        for(int i=0;i<numRegs;i++)
        {
            raf.seek(i*140);
           
            String ref = raf.readUTF();
            String nom=raf.readUTF();
            char tip = raf.readChar();
            float cuo =raf.readFloat();
            float agua = raf.readFloat();
            float cal = raf.readFloat();
            
            float ot = raf.readFloat();
            float recb = raf.readFloat();
            
            //Lo inicializo como ático por inicializarlo de alguna manera
            piso=new Atico(ref,nom,cuo,agua,cal,ot);
            piso.setTipoPiso(tip);
            piso.totalRbo();
            
            
            if(piso.getReferencia().compareToIgnoreCase(referencia)!=0){
             
                rafTemp.seek(b*140);
                rafTemp.writeUTF(piso.getReferencia());
                rafTemp.writeUTF(piso.getNombrePropietario());
                rafTemp.writeChar(piso.getTipoPiso());
                rafTemp.writeFloat(piso.getCuotaFija());
                rafTemp.writeFloat(piso.getLitrosAguaCaliente());
                rafTemp.writeFloat(piso.getPasosDeCalefaccion());
                if (piso instanceof Atico) {
                    rafTemp.writeFloat(((Atico)piso).getMetrosTerraza());
                } else if (piso instanceof Duplex) {
                    rafTemp.writeFloat(((Duplex) piso).getCuotaExtra());
                }
                //raf.writeFloat(piso.getTotalReciboComunidad());
                b++;
            }
            
        }
        
        raf.close();
        rafTemp.close();
        
        fichero.delete();
        temporal.renameTo(fichero);
        

        if(b==numRegs)
        {
            System.out.println("--- ATICO NO ENCONTRADO ---");
            
        }
        else
        {
            System.out.println("---  ATICO BORRADO  ---");
            numRegs--;
        }
        return numRegs;
        
        }
//        
//        public static int bajaDuplex(File fichero, int numRegs, Scanner input){
//        
//                    
//        File temporal=new File("temporal.ddr");
//        RandomAccessFile rafTemp=new RandomAccessFile(temporal,"rw");
//        RandomAccessFile raf=new RandomAccessFile(fichero,"r");
//        
//        Piso piso;
//        String referencia;
//        byte b=0;
//        
//        System.out.println("Introduzca la referencia del piso a borrar:");
//        referencia=ControlData.leerString(input);
//        
//        for(int i=0;i<numRegs;i++)
//        {
//            raf.seek(i*140);
//            
//            empresa=new C_Empresa(raf.readUTF(),raf.readUTF(),raf.readUTF(),raf.readUTF());
//            
//            if(empresa.getCif().compareToIgnoreCase(cif)!=0)
//            {
//             
//                rafTemp.seek(b*140);
//                rafTemp.writeUTF(empresa.getCif());
//                rafTemp.writeUTF(empresa.getRazonSocial());
//                rafTemp.writeUTF(empresa.getDireccion());
//                rafTemp.writeUTF(empresa.getTelefono());
//                b++;
//            }
//            
//        }
//        
//        raf.close();
//        rafTemp.close();
//        
//        fichero.delete();
//        temporal.renameTo(fichero);
//        
//
//        if(b==numRegs)
//        {
//            System.out.println("--- DUPLEX NO ENCONTRADO ---");
//            
//        }
//        else
//        {
//            System.out.println("---  DUPLEX BORRADO  ---");
//            numRegs--;
//        }
//        return numRegs;
//        }
    
}
