/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operaciones;

import POJO.Cliente;
import POJO.Cuenta;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author a20estefaniapc
 */
public class OperacionesFichero {
    
    /**
     * Lee el fichero recuperendo el arryalist con todas las cuentas.
     * @param fichero
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public static void leerFichero(File fichero) throws IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream(fichero);
        ObjectInputStream ois = new ObjectInputStream(fis);

        while (fis.available() > 0) {
            OperacionesCuentas.setListaTodasCuentas((ArrayList<Cuenta>) ois.readObject());
        }
        ois.close();

    }
    
    /**
     * Guarda todos los clientes asociados a las cuentas de la lista general de cuentas y los guarda en la lista general de clientes.
     */
    public static void guardarClientesEnListaTodosClientes() {

        for (Cuenta cuenta : OperacionesCuentas.getListaTodasCuentas()) {
            for (Cliente cliente : cuenta.getClientes()) {
                if (!OperacionesCuentas.clienteRegistrado(cliente)) {
                    OperacionesCuentas.getListaTodosClientes().add(cliente);
                }
            }

        }
    }
    
    /**
     * Reescribe el fichero con la lista general de cuentas.
     * @param fichero
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void escribirFichero(File fichero) throws FileNotFoundException, IOException {

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero));

        oos.writeObject(OperacionesCuentas.getListaTodasCuentas());
        oos.close();
    }

}
