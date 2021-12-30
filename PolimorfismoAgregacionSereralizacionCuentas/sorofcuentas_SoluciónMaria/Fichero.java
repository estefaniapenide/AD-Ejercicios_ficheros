
package Secuenciales.SeriacionObjetosQReferencianObjetos.SOROFCuentas.sorofcuentas;
import java.io.*;
import java.util.*;
import Secuenciales.SeriacionObjetosQReferencianObjetos.SOROFCuentas.clases.*;
/**
 *
 * @author 
 */
public class Fichero {
    
    public static void crear (BufferedReader lee, File Fcuentas, ArrayList <Cuenta> cuentas) throws IOException {
        
        char conf;
        
        ObjectOutputStream oos = null;
        
        try {
            if (!Fcuentas.exists()) {
                oos = new ObjectOutputStream (new FileOutputStream (Fcuentas));
                System.out.println("Ficheros creados");
            }
            else {
                System.out.println("Los ficheros ya existen");
                
                conf = Menu.confirmacion(lee, "Desea sobrescribir el fichero?");
                
                if (conf == 'S') {
                    oos = new ObjectOutputStream (new FileOutputStream (Fcuentas));
                    cuentas.clear();
                }
            }
            
        } catch (IOException e) {}
        
        finally {
                if (oos != null)
                    oos.close();
        }
    }
    
    public static void borrarFichero (BufferedReader lee, File Fcuentas, ArrayList <Cuenta> cuentas) {

        if (Fcuentas.exists()) {
            char conf = Menu.confirmacion(lee, "Esta seguro de querer borrar el fichero?");
            if (conf == 'S') {
                Fcuentas.delete();
                cuentas.clear();
                System.out.println("Ficheros borrados");
            }
            
        }
        else 
            System.out.println("No existe ningun fichero");
    }
    
    public static ArrayList leer (File Fcuentas, ArrayList <Cuenta> cuentas) throws IOException {
        
        ObjectInputStream ois = null;
        
        try {
            if (Fcuentas.exists()) {
                ois = new ObjectInputStream (new FileInputStream (Fcuentas));
                cuentas = (ArrayList <Cuenta>) ois.readObject();
            }
        }
        catch (IOException | ClassNotFoundException e) {}
        finally {
                if (ois != null)
                    ois.close();
        }
        
        return cuentas;
    }
    
    public static void guardar (File Fcuentas, ArrayList <Cuenta> cuentas) throws IOException {
        
        ObjectOutputStream oos = null;
        
        try {
            oos = new ObjectOutputStream (new FileOutputStream (Fcuentas));
            oos.writeObject(cuentas);
        }
        catch (IOException e) {}
        finally {
                if (oos != null)
                    oos.close();
        }
    }
}

