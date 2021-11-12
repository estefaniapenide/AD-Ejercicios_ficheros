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
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 *
 * @author a20estefaniapc
 */
public class Modificaciones {

    public static void modificar(File fichero, int numRegs, Scanner input) throws IOException {

        RandomAccessFile raf = new RandomAccessFile(fichero, "rw");

        Piso piso = new Atico();
        String referencia;
        byte b = 0;

        System.out.println("INTRODUZCA LA REFERENCIA DEL PISO A MODIFICAR:");
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

            //piso.setTipoPiso(tip);
            piso.totalRbo();

            if (piso.getReferencia().compareToIgnoreCase(referencia) == 0) {

                b = 1;
                //Si se modifica el tipo de piso, tambien se cambia el objeto, por lo que instanceof seguirá funcionando
                modPiso(piso, input);

                if (piso.getTamReal() <= piso.getTamMax()) {

                    raf.seek(i * 140);
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
                } else {
                    System.out.println("Tamaño Excedido  -- PISO NO MODIFICADO --");
                }
            }
        }
        if (b != 1) {
            System.out.println("--- PISO NO ENCONTRADO ---");
        }

    }

    private static void modPiso(Piso piso, Scanner input) throws IOException {

        String nombre;
        char tipo;
        float cuota, agua, calefaccion, otro;
        byte op = 0;

        do {

            System.out.println(piso);

            System.out.println(menuModificar());
            op = ControlData.leerByte(input);

            switch (op) {
                case 1:
                    System.out.println("Nuevo Nombre:");
                    nombre = ControlData.leerString(input);
                    piso.setNombrePropietario(nombre);
                    break;
                case 2:
                    System.out.println("Nuevo Tipo de piso:");
                    tipo = ControlData.leerChar(input);
                    if (piso.getTipoPiso() == tipo) {//Si el tipo ya es el que tenía no habrá que hacer más cambios
                    } else {

                        String ref = piso.getReferencia();
                        String nom = piso.getNombrePropietario();
                        float cuo = piso.getCuotaFija();
                        float ag = piso.getLitrosAguaCaliente();
                        float cal = piso.getPasosDeCalefaccion();
                        float ot = 0;
                        
                        if (piso.getTipoPiso() == 'D') {
                            System.out.println("Los datos de CUOTA EXTRA se han borrado pues se trata de un ÁTICO.\n"
                                    + "Introduzca los metros cuadrados de terraza:");
                            ot = ControlData.leerFloat(input);
                            piso = new Atico(ref, nom, cuo, ag, cal, ot);
                        } else if (piso.getTipoPiso() == 'A') {
                            System.out.println("Los datos de METROS DE TERRAZA se han borrado pues se trata de un DÚPLEX.\n"
                                    + "Introduzca la cuota extra(€):");
                            ot = ControlData.leerFloat(input);
                            piso = new Duplex(ref, nom, cuo, ag, cal, ot);

                        }
                        //Se asigna el nuevo recibo
                        piso.totalRbo();
                    }
                    break;
                case 3:
                    System.out.println("Nueva Cuota Fija(€):");
                    cuota = ControlData.leerFloat(input);
                    piso.setCuotaFija(cuota);
                    piso.totalRbo();
                    break;
                case 4:
                    System.out.println("Nuevos Litros agua caliente:");
                    agua = ControlData.leerFloat(input);
                    piso.setLitrosAguaCaliente(agua);
                    piso.totalRbo();
                    break;
                case 5:
                    System.out.println("Nuevos Pasos de calefacción:");
                    calefaccion = ControlData.leerFloat(input);
                    piso.setPasosDeCalefaccion(calefaccion);
                    piso.totalRbo();
                    break;
                case 6:
                    if (piso instanceof Atico) {
                        System.out.println("Nuevos Metros cuadrados de terraza:");
                        otro = ControlData.leerFloat(input);
                        ((Atico) piso).setMetrosTerraza(otro);
                    } else if (piso instanceof Duplex) {
                        System.out.println("Nueva Cuota Extra(€):");
                        otro = ControlData.leerFloat(input);
                        ((Duplex) piso).setCuotaExtra(otro);
                    }
                    piso.totalRbo();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("No ha introducido ninguna de las opciones.");
                    break;
            }
        } while (op != 0);

    }

    private static String menuModificar() {
        String menuModificar = "MODIFICAR"
                + "\n1.-Nombre del propietario"
                + "\n2.-Tipo de piso"
                + "\n3.-Cuota fija"
                + "\n4.-Litros de agua caliente"
                + "\n5.-Calefacción"
                + "\n6.-Metros de terraza/Cuota extra\n"
                + "\n0.-Volver al menú principal.";
        return menuModificar;
    }

}
