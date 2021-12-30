
package Secuenciales.SeriacionObjetosQReferencianObjetos.SOROFCuentas.sorofcuentas;
import static Secuenciales.SeriacionObjetosQReferencianObjetos.SOROFCuentas.MisExcepciones.Validar.validarCta;
import static Secuenciales.SeriacionObjetosQReferencianObjetos.SOROFCuentas.MisExcepciones.Validar.validarDni;
import java.io.*;
import java.util.*;
import Secuenciales.SeriacionObjetosQReferencianObjetos.SOROFCuentas.clases.*;
import javax.swing.JOptionPane;
/**
 *
 * @author 
 */
public class Altas {
    
    public static void altas (BufferedReader lee, ArrayList <Cuenta> cuentas) throws IOException {
        
        int op;
        
        do {
            op = Menu.menuAltas(lee, "ALTAS");

            switch (op) {
                case 1: // Altas CtaCorriente
                    altasCtaCorriente(lee, cuentas); 
                break;
                case 2: // Altas CtaPlazo
                    altasCtaPlazo(lee, cuentas);
                break; 
            }
            
        } while (op != 3);
    }
    
    public static void altasCtaCorriente (BufferedReader lee, ArrayList <Cuenta> cuentas) {
        
        Cuenta cta = null; 
        
        String numero, sucursal;
        long saldo; float interes;
        int b = 0, operacion; char conf;
        
        try {
            System.out.println("Introduzca el numero de la cuenta: ");
            numero = lee.readLine();
            
            // Comprobar si la cuenta existe
            cta = comprobar(cuentas, numero);
            
            if (cta == null) {
                
                if ((comprobarNum(cuentas, numero))&& (comprobarSucursal(cuentas, numero))){
                
                System.out.println("Introduce el nombre de la sucursal de la cuenta: ");
                sucursal = lee.readLine();

                System.out.println("Introduce el saldo de la cuenta: ");
                saldo = Long.parseLong(lee.readLine());

                System.out.println("Introduce el interes de la cuenta: ");
                interes = Float.parseFloat(lee.readLine());
                
                System.out.println("Introduce la cantidad que desea retirar o ingresar en la cuenta: ");
                operacion = Integer.parseInt(lee.readLine());

                cta = new CtaCorriente (operacion, interes, numero, sucursal, saldo);
                }
            }
            else {
                if (cta instanceof CtaPlazo) {
                    System.out.println("El numero de la cuenta corresponde a una cuenta a plazo");
                    return;
                }
                else
                    b = 1;
            }
            conf = Menu.confirmacion(lee, "Desea registrar los clientes de la cuenta?");
            if (conf == 'S') 
                Altas.altasCliente(lee, cta);
            
            if (b == 0) // no esta aun en el array
                cuentas.add(cta);
            
        } catch (IOException e) {}
    }
    
    public static void altasCtaPlazo (BufferedReader lee, ArrayList <Cuenta> cuentas) throws IOException {
        
        Cuenta cta = null;
        
        String numero, sucursal, fechaVenc;
        float cuota, retencion;
        long deposito, saldo;
        int b = 0, v; char conf;
        
        try {
            System.out.println("Introduzca el numero de la cuenta");
            numero = lee.readLine();
            
            // Comprobar si la cuenta existe
            cta = Altas.comprobar(cuentas, numero);
            
            if (cta == null) {
            
                //if ((comprobarNum(cuentas, numero))&& (comprobarSucursal(cuentas, numero))){
                
                v=validarCta(numero);
                
                if (v==1){
                    System.out.println("Cuenta incorrecta");
                }
                else{
                    
                System.out.println("Introduzca el nombre de la sucursal de la cuenta: ");
                sucursal = lee.readLine();

                System.out.println("Introduzca la fecha de vencimiento de la cuenta: ");
                fechaVenc = lee.readLine();
                
                System.out.println("Introduzca el saldo de la cuenta: ");
                saldo = Long.parseLong (lee.readLine());
                
                System.out.println("Introduzca el deposito de la cuenta: ");
                deposito = Long.parseLong (lee.readLine());

                System.out.println("Introduzca la cuota de la cuenta: ");
                cuota = Float.parseFloat (lee.readLine());

                System.out.println("Introduzca la retencion de la cuenta: ");
                retencion = Float.parseFloat (lee.readLine());

                cta = new CtaPlazo (fechaVenc, deposito, cuota, retencion, numero, sucursal, saldo);
                }
            }
            else {
                if (cta instanceof CtaCorriente) {
                    System.out.println("El numero de la cuenta corresponde a una cuenta corriente");
                    return;
                }
                else
                    b = 1;
            }
            conf = Menu.confirmacion(lee, "Desea registrar los clientes de la cuenta?");
            if (conf == 'S') 
                Altas.altasCliente(lee, cta);
            
            if (b == 0) // no esta aun en el array
                cuentas.add(cta);
            
        }
        catch (EOFException e) {       
            JOptionPane.showMessageDialog(null, "Autor no encontrado");
                
        }
    }

    
    public static void altasCliente (BufferedReader lee, Cuenta cta) {
        
        String dni, nombre, tlf; 
        char conf = 0;
        int v = 0;
        
        try {
            do {
                System.out.println("Introduzca el dni del cliente: ");
                dni = lee.readLine();
                
                v=validarDni(dni);
                
                if (v==1){
                    System.out.println("DNI incorrecto");
                }
                else{
                System.out.println("Introduce el nombre del cliente: ");
                nombre = lee.readLine();
                
                System.out.println("Introduce el telefono del cliente: ");
                tlf = lee.readLine();
                
                cta.getCli().add(new Cliente (dni, nombre, tlf));
                
                conf = Menu.confirmacion(lee, "Desea registrar mas clientes a la cuenta?");
                }
                
            } while (conf == 'S');
            
        } catch (IOException e) {}
    }
    
    public static Cuenta comprobar (ArrayList <Cuenta> cuentas, String numero) {
        
        for (int i = 0; i < cuentas.size(); i++) {
            if (cuentas.get(i).getNumero().compareToIgnoreCase(numero) == 0)
                return cuentas.get(i);
        }
        
        return null;
    }
    
    public static boolean comprobarNum (ArrayList <Cuenta> cuentas, String numero) {
        boolean b = false;
        for (int i=0;i<numero.length();i++){
            if(Character.isDigit(numero.charAt(i)))
                b=true;
        }        
        return b;
    }
    
    public static boolean comprobarSucursal (ArrayList <Cuenta> cuentas, String sucursal) {
        boolean b = false;
        for (int i=0;i<sucursal.length();i++){
            if(Character.isLetter(sucursal.charAt(i)))
                b=true;
        }  
        return b;
    }
}
