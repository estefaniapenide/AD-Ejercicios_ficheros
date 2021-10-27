/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.animalespolimorfismo;

import POJO.Animal;
import POJO.Gato;
import POJO.Perro;
import controldata.ControlData;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author a20estefaniapc
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Creo el fichero y guardo el path absoluto para usarlo en los métodos estáticos de OperacionesAnimales
        File fichero = new File("fichero.ddr");
        String directorio = fichero.getAbsolutePath();
        try {
            fichero.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner input = new Scanner(System.in);

        int opcion = 0;
        do {
            //Menú princpal
            System.out.println(menuPrincipal());
            opcion = ControlData.leerInt(input);

            switch (opcion) {
                case 1:
                    //Recoje los datos de un perro a añadir al array perros
                    System.out.println("DATOS PERRO");
                    System.out.println("Nombre: ");
                    String nombre = ControlData.leerString(input);
                    System.out.println("Edad: ");
                    int edad = ControlData.leerInt(input);
                    int a = 0;
                    boolean Largo = false;
                    do {
                        System.out.println(menuPerroRabo());
                        a = ControlData.leerInt(input);
                        switch (a) {
                            case 1:
                                Largo = true;
                                break;
                            case 2:
                                Largo = false;
                                break;
                            default:
                                System.out.println("Debe escoger una de las opciones.");
                                break;
                        }
                    } while (a != 1 && a != 2);
                    
                    //Si queda espacio en el array de perros, lo añade. Si no hay espacio, no lo añade y devuelve un mensaje indicándolo
                    boolean perroAnadido = OperacionesAnimales.addPerro(new Perro(nombre, edad, Largo));
                    if (perroAnadido) {
                        System.out.println(nombre + " ha sido añadido al array.\nPuede añadirlo al fichero en la opción 3 del menú principal\no seguir añadiendo otros animales a los arrays.");
                    } else if (!perroAnadido) {
                        System.out.println("No se puede añadir el perro. El array está lleno.\nGuarde los animales en el fichero.");
                    }

                    break;
                case 2:
                    //Recoje los datos de un gato a añadir al array gatos
                    System.out.println("DATOS GATO");
                    System.out.println("Nombre: ");
                    nombre = ControlData.leerString(input);
                    System.out.println("Edad: ");
                    edad = ControlData.leerInt(input);
                    a = 0;
                    Largo = false;
                    do {
                        System.out.println(menuGatoBigote());
                        a = ControlData.leerInt(input);
                        switch (a) {
                            case 1:
                                Largo = true;
                                break;
                            case 2:
                                Largo = false;
                                break;
                            default:
                                System.out.println("Debe escoger una de las opciones.");
                                break;
                        }
                    } while (a != 1 && a != 2);
                    
                    //Si queda espacio en el array de gatos, lo añade. Si no hay espacio, no lo añade y devuelve un mensaje indicándolo
                    boolean gatoAnadido = OperacionesAnimales.addGato(new Gato(nombre, edad, Largo));
                    if (gatoAnadido) {
                        System.out.println(nombre + " ha sido añadido al array.\nPuede añadirlo al fichero en la opción 3 del menú principal\no seguir añadiendo otros animales a los arrays.");
                    } else if (!gatoAnadido) {
                        System.out.println("No se puede añadir el gato. El array está lleno.\nGuarde los animales en el fichero.");
                    }
                    break;

                case 3:
                    //Guarda los animales de los arrays gatos y perros en el fichero
                    guardarAnimalesEnFichero(fichero,directorio);
                    break;
                    
                case 4:
                    //Devuelve por pantalla los animales que están escritos en el fichero y además de 
                    //meterlos en array animales, los clasifica en dos arrays: perrosFichero y gatosFichero(aunque estos no 
                    //se muestran por pantalla).
                    leerFichero(directorio);
                    break;
                    
                case 0:
                    System.out.println("Programa finalizado");
                    break;
                default:
                    System.out.println("No ha introducido ninguna de las opciones");
                    break;
            }
        } while (opcion != 0);

    }
    /**
     * Strings con las opciones del menú principal
     * @return 
     */
    public static String menuPrincipal() {
        String menu = "\nMENU PRINCIPPAL ANIMALES\n"
                + "1.-Nuevo perro\n"
                + "2.-Nuevo gato\n"
                + "3.-Guardar animales en fichero\n"
                + "4.-Leer fichero\n\n"
                + "0.-Salir\n";
        return menu;
    }
    
    /**
     * Strings con las opciones de la longitud del rabo del perro
     * @return 
     */
    public static String menuPerroRabo() {
        String menu = "RABO\n"
                + "1.-Largo\n"
                + "2.-Corto\n";
        return menu;
    }
    
    /**
     * Strings con las opciones de la longitud del bigote del gato
     * @return 
     */
    public static String menuGatoBigote() {
        String menu = "BIGOTE\n"
                + "1.-Largo\n"
                + "2.-Corto\n";
        return menu;
    }
    
    /**
     * Guarda los animales de los arrays perros y gatos en el fichero
     * @param fichero 
     */
    public static void guardarAnimalesEnFichero(File fichero,String directorio) {
        
        System.out.println("GUARDAR ANIMALES EN FICHERO");
        int numGatos = OperacionesAnimales.contarGatos();
        int numPerros = OperacionesAnimales.contarPerros();
        int numAnimales = numGatos + numPerros;
        
        //Si no hay elementos en ninguno de los arrays, no guarda nada.
        if (numAnimales <= 0) {
            System.out.println("No hay animales para guardar en el fichero.\n");
        } else {
            //Si hay elementos en los arrays, los guarda
            try {
                //Si el archivo nunca ha sido escrito, usa un método
                if (fichero.length() == 0) {
                    OperacionesAnimales.guardarPrimerosAnimalesADisco(directorio);
                } else {
                    //Si el fichero ya ha sido escrito, usa otro método
                    OperacionesAnimales.guardarOtrosAnimalesADisco(directorio);
                }
                System.out.println("Los animales han sido guardados en el fichero.\n");
                //Finalmente vacia los arrays cuando los datos ya han sido introducido en el fichero
                OperacionesAnimales.vaciarArraysPerrosGatos();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Lee el fichero y devuelve la lista de animales guardados en él.
     * @param fichero 
     */
    public static void leerFichero(String fichero){
        
        System.out.println("LEER FICHERO");
                    try {
                        boolean ficheroVacio = OperacionesAnimales.ficheroVacio(fichero);
                        //Si el fichero está vacio, no lo lee
                        if (ficheroVacio) {
                            System.out.println("El fichero está vacío\n");
                        //Si el fichero no está vacío, lo lee
                        } else if (!ficheroVacio) {
                            //Devuelve por pantalla los animales del array animales (todos los animales del fichero)
                            Animal[] arrayAnimales = OperacionesAnimales.leerAnimalesDisco(fichero);
                            for (Animal aux : arrayAnimales) {
                                System.out.println(aux);
                            }
                            //Aunque no los devuelve por panatlla clasificados, clasifica los animales del fichero en dos
                            //arrays: perrosFichero y gatosFichero (ya que se pide en el enunciado).
                            OperacionesAnimales.calsificarPerrosGatos(fichero);
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
    
    }

}
