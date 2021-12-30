
package Secuenciales.SeriacionObjetosQReferencianObjetos.SOROFCuentas.sorofcuentas;
import java.io.*;
/**
 *
 * @author 
 */
public class Menu {
    
    public static int menu (BufferedReader lee) {
        int menu = 0;
        
        do {
            try {
                System.out.println("---****************** BIENVENIDO ******************---"
                        + "\n\033[34m1.- Crear fichero"
                        + "\n\033[34m2.- Altas"
                        + "\n\033[34m3.- Bajas"
                        + "\n\033[34m4.- Modificaciones"
                        + "\n\033[34m5.- Visualizar"
                        + "\n\033[34m6.- Borrar fichero"
                        + "\n\033[34m7.- Salir"
                        + "\n---************************************************---");
                menu = Integer.parseInt(lee.readLine());
                
                if (menu < 1 || menu > 7)
                    System.out.println("\033[31m'Error', elija una opción porfavor");
                
            } catch (IOException | NumberFormatException e) {
                System.out.println("\033[31m"+e.getMessage());
            }
        } while (menu < 1 || menu > 7);
        
        return menu;
    }
    
    public static char confirmacion (BufferedReader lee, String mensaje) {
        
        char menu = 'a';
        
        do {
            try {
                System.out.println("\n\033[34m"+mensaje
                        + "\n\033[34mS.- Si"
                        + "\n\033[34mN.- No");
                menu = lee.readLine().toUpperCase().charAt(0);
            }
            catch (IOException | StringIndexOutOfBoundsException e) {
                System.out.println("\033[31m"+e.getMessage());
            }
            
        } while (menu != 'S' && menu != 'N');
        
        return menu;
    }
    
    public static int menuAltas (BufferedReader lee, String mensaje) {
        
        int menu = 0;
        
        do {
            try {
                System.out.println("---******************** "+mensaje+ " *********************---"
                        + "\n\033[34m1.- Cta. Corriente"
                        + "\n\033[34m2.- Cta. Plazo"
                        + "\n\033[34m3.- Salir"
                        + "\n---************************************************---");
                menu = Integer.parseInt (lee.readLine());
                
                if (menu < 1 || menu > 3)
                    System.out.println("\033[31m'Error', elija una opcion porfavor");
            }
            catch (IOException | NumberFormatException e) {
                System.out.println("\033[31m"+e.getMessage());
            }
            
        } while (menu < 1 || menu > 3);
        
        return menu;
    }
    
    public static int menuModiCtaCoriente (BufferedReader lee) {
        
        int menu = 0;
        
        do {
            try {
                System.out.println("---******************** --- *********************---"
                        + "\n\033[34m1.- Sucursal"
                        + "\n\033[34m2.- Saldo"
                        + "\n\033[34m3.- Interes"
                        + "\n\033[34m4.- Clientes"
                        + "\n\033[34m5.- Salir"
                        + "\n---************************************************---");
                menu = Integer.parseInt (lee.readLine());
                
                if (menu < 1 || menu > 5)
                    System.out.println("\033[31m'Error', elija una opcion porfavor");
            }
            catch (IOException | NumberFormatException e) {
                System.out.println("\033[31m"+e.getMessage());
            }
            
        } while (menu < 1 || menu > 5);
        
        return menu;
    }
    
    public static int menuModiCtaPlazo (BufferedReader lee) {
        
        int menu = 0;
        
        do {
            try {
                System.out.println("---******************** --- *********************---"
                        + "\n\033[34m1.- Sucursal"
                        + "\n\033[34m2.- Saldo"
                        + "\n\033[34m3.- Deposito"
                        + "\n\033[34m4.- Fecha de vencimiento"
                        + "\n\033[34m5.- Cuota"
                        + "\n\033[34m6.- Retencion"
                        + "\n\033[34m7.- Clientes"
                        + "\n\033[34m8.- Salir"
                        + "\n---************************************************---");
                menu = Integer.parseInt (lee.readLine());
                
                if (menu < 1 || menu > 8)
                    System.out.println("\033[31m'Error', elija una opcion porfavor");
            }
            catch (IOException | NumberFormatException e) {
                System.out.println("\033[31m"+e.getMessage());
            }
            
        } while (menu < 1 || menu > 8);
        
        return menu;
    }
     
     public static int menuClientes (BufferedReader lee) {
        
        int menu = 0;
        
        do {
            try {
                System.out.println("---******************** --- *********************---"
                        + "\n\033[34m1.- Añadir clientes"
                        + "\n\033[34m2.- Borrar clientes"
                        + "\n\033[34m3.- Salir"
                        + "\n---************************************************---");
                menu = Integer.parseInt (lee.readLine());
                
                if (menu < 1 || menu > 3)
                    System.out.println("\033[31m'Error', elija una opcion porfavor");
            }
            catch (IOException | NumberFormatException e) {
                System.out.println("\033[31m"+e.getMessage());
            }
            
        } while (menu < 1 || menu > 3);
        
        return menu;
    }
}
