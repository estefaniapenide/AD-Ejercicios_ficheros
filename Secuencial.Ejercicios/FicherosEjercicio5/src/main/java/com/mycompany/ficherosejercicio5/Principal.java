/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.ficherosejercicio5;

import com.mycompany.ficherosejercicio5.POJO.Vehiculo;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 *
 * @author Estefania
 */
/*5)
    Mismo ejercicio que Fich. Binarios - flujo Datos pero con serialización, 
    para ello, crea una simple clase Vehiculo
    con los atributos matricula, marca, tamaño del deposito y modelo, 
    con sus respectivos métodos get y el constructor se invocara con todos los atributos.

    El atributo tamañoDeposito no se incluirá en el fichero (aun así debemos pedirlo), 
    debemos indicarlo en la clase (recuerda el uso de transient).

    Recuerda que al usar la clase ObjectOutputStream, si vamos a añadir varios objetos 
    en distintas ejecuciones, debemos crear nuestra propia versión de ObjectOutputStream. 
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        File fichero = new File("Lista_de_vehiculos.ddr");

        try {

            fichero.createNewFile();

            Vehiculo coche = new Vehiculo();
            pedirDatos(coche);

            MiObjectOutputStream oos = new MiObjectOutputStream(new FileOutputStream(fichero, true));

            escribirFichero(oos, coche);

            FileInputStream fis = new FileInputStream(fichero);
            MiObjectInputStream ois = new MiObjectInputStream(fis);

            leerFichero(ois, fis);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void pedirDatos(Vehiculo coche) {

        Scanner input = new Scanner(System.in);

        String matricula;
        String marca;
        double deposito;
        String modelo;

        System.out.println("DATOS DEL COCHE\n");
        System.out.println("Matricula: ");
        matricula = ControlData.leerString(input);
        coche.setMatricula(matricula);

        System.out.println("Marca: ");
        marca = ControlData.leerString(input);
        coche.setMarca(marca);

        System.out.println("Tamaño del depósito(L): ");
        deposito = ControlData.leerDouble(input);
        coche.setDeposito(deposito);

        System.out.println("Modelo: ");
        modelo = ControlData.leerString(input);
        coche.setModelo(modelo);

    }

    public static void escribirFichero(MiObjectOutputStream oos, Vehiculo coche) throws IOException {

        oos.writeObject(coche);
    }

    public static void leerFichero(MiObjectInputStream ois, FileInputStream fis) throws IOException, ClassNotFoundException {

        System.out.println("LSITA DE VEHÍCULOS:");

        while (fis.available() > 0) {
            Vehiculo vehiculo = (Vehiculo) ois.readObject();
            System.out.println(vehiculo);
        }

    }

}
