
package Secuenciales.SeriacionObjetosQReferencianObjetos.SOROFCuentas.sorofcuentas;
import java.io.*;
import java.util.*;
import Secuenciales.SeriacionObjetosQReferencianObjetos.SOROFCuentas.clases.*;
/**
 *
 * @author 
 */
public class SOROFCuentas {

    public static void main(String[] args) {
        
        BufferedReader lee = new BufferedReader (new InputStreamReader (System.in));
        
        File Fcuentas = new File ("fcuentas.dat");
        
        ArrayList <Cuenta> cuentas = new ArrayList <Cuenta> ();
        
        int menu;
        
        try {
            cuentas = Fichero.leer(Fcuentas, cuentas);
            
            do {
                menu = Menu.menu(lee);
                
                switch (menu) {
                    case 1:
                        Fichero.crear(lee, Fcuentas, cuentas);
                    break;
                    case 2:
                        Altas.altas(lee, cuentas);
                    break;
                    case 3:
                        Bajas.bajasCtaCorrientePlazo(lee, cuentas);
                    break;
                    case 4:
                        Modificaciones.modificar(lee, cuentas);
                    break;
                    case 5:
                        Visualizar.visualizar(cuentas);
                    break;
                    case 6:
                        Fichero.borrarFichero(lee, Fcuentas, cuentas);
                    break;
                }
            } while (menu != 7);
            
            Fichero.guardar(Fcuentas, cuentas);
            
        } catch (IOException e) {}
        
        System.out.println("---*************** FIN DEL PROGRAMA ***************---");
    }
    
}
