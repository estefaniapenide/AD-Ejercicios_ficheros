
package Secuenciales.SeriacionObjetosQReferencianObjetos.SOROFCuentas.sorofcuentas;
import java.io.*;
import java.util.*;
import Secuenciales.SeriacionObjetosQReferencianObjetos.SOROFCuentas.clases.*;
/**
 *
 * @author 
 */
public class Bajas {
    
    public static void bajasCtaCorrientePlazo (BufferedReader lee, ArrayList <Cuenta> cuentas) {
        
        String numero;
        int i; char conf;
        
        try {
            if (!cuentas.isEmpty()) {
                Visualizar.visualizarCta(cuentas);

                System.out.println("Introduce el numero de la cuenta que desea dar de baja");
                numero = lee.readLine();

                for (i = 0; i < cuentas.size(); i++) {
                    if (cuentas.get(i).getNumero().compareToIgnoreCase(numero) == 0) {
                        conf = Menu.confirmacion(lee, "Esta seguro de que desea borrar la cuenta?");
                        if (conf == 'S') {
                            cuentas.remove(i);
                            System.out.println("Cuenta borrada");
                        }
                        return;
                    }  
                }
                System.out.println("La cuenta no esta registrada en la base de datos");
            }
            else
                System.out.println("No hay registros, el fichero esta vacio");
            
        } catch (IOException e) {}
    }
    
    public static void bajasCliente (BufferedReader lee, Cuenta cta, String dni) {
        
        int i; char conf;

        for (i = 0; i < cta.getCli().size(); i++) {
            if (cta.getCli().get(i).getDni().compareToIgnoreCase(dni) == 0) {
                conf = Menu.confirmacion(lee, "Esta seguro de que desea borrar el cliente?");
                if (conf == 'S') {
                    cta.getCli().remove(i);
                    System.out.println("Cliente borrado");
                }
                return;
            }  
        }
        
        System.out.println("La cuenta no tiene registrado el cliente");    
    }
}
