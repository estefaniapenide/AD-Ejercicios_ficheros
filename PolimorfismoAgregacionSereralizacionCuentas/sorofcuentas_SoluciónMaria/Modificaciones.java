
package Secuenciales.SeriacionObjetosQReferencianObjetos.SOROFCuentas.sorofcuentas;
import java.io.*;
import java.util.*;
import Secuenciales.SeriacionObjetosQReferencianObjetos.SOROFCuentas.clases.*;
/**
 *
 * @author 
 */
public class Modificaciones {
    
    public static void modificar (BufferedReader lee, ArrayList <Cuenta> cuentas) {
        
        Cuenta cta;
        
        String numero;
        
        try {
            if (!cuentas.isEmpty()) {
                Visualizar.visualizarCta(cuentas);

                System.out.println("Introduzca el numero de cuenta de la cuenta que desea modificar: ");
                numero = lee.readLine();

                // Comprobar si existe la cuenta
                cta = Altas.comprobar(cuentas, numero);

                if (cta == null) 
                    System.out.println("La cuenta no esta registrada en la base de datos");
                else {
                    if (cta instanceof CtaCorriente)
                        modificarCtaCorriente(lee, cta);
                    else
                        modificarCtaPlazo(lee, cta);
                }
            }
            else
                System.out.println("No hay registros, el fichero esta vacio");
        } catch (IOException e) {}
    }
    
    public static void modificarCtaCorriente (BufferedReader lee, Cuenta cta) {
        
        int op, op2;
        String sucursal, dni;
        float saldo, interes;
        
        try {
            do {
                op = Menu.menuModiCtaCoriente(lee);
                
                switch (op) {
                    case 1: // Sucursal
                        System.out.println("Introduzca la nueva sucursal: ");
                        sucursal = lee.readLine();
                        cta.setSucursal(sucursal);
                        System.out.println("Sucursal modificada");
                    break;
                    case 2: // Saldo
                        System.out.println("Introduzca el nuevo saldo: ");
                        saldo = Long.parseLong(lee.readLine());
                        cta.getSaldo();
                        System.out.println("Saldo modificado");
                    break;
                    case 3: // Interes
                        System.out.println("Introduzca el nuevo interes: ");
                        interes = Float.parseFloat(lee.readLine());
                        ((CtaCorriente)cta).setInteres(interes);
                        System.out.println("Interes modificado");
                    break;
                    case 4: // Clientes
                        do {
                            op2 = Menu.menuClientes(lee);

                            switch (op2) {
                                case 1:
                                    Altas.altasCliente(lee, cta);
                                break;
                                case 2:
                                    Visualizar.visualizarClientes(cta);
                                    System.out.println("Introduce el dni del cliente que desea dar de baja: ");
                                    dni = lee.readLine();
                                    Bajas.bajasCliente(lee, cta, dni);
                                break;
                            }
                        } while (op2 != 3);
                    break;
                }
            } while (op != 5);
            
        } catch (Exception e) {}
    }
    
    public static void modificarCtaPlazo (BufferedReader lee, Cuenta cta) {
        
        int op, op2;
        String sucursal, dni, fecha;
        long deposito, saldo;
        float cuota, retencion;
        
        try {
            do {
                op = Menu.menuModiCtaPlazo(lee);
                
                switch (op) {
                    case 1: // Sucursal
                        System.out.println("Introduzca la nueva sucursal: ");
                        sucursal = lee.readLine();
                        cta.setSucursal(sucursal);
                        System.out.println("Sucursal modificada");
                    break;
                    case 2: // Saldo
                        System.out.println("Introduzca el nuevo saldo: ");
                        saldo = Long.parseLong(lee.readLine());
                        cta.setSaldo(saldo);
                        System.out.println("Saldo modificado");
                    break;
                    case 3: // Deposito
                        System.out.println("Introduzca el nuevo deposito: ");
                        deposito = Long.parseLong(lee.readLine());
                        ((CtaPlazo)cta).setDeposito(deposito);
                        System.out.println("Deposito modificado");
                    break;
                    case 4: // Fecha vencimiento
                        System.out.println("Introduzca la nueva fecha de vencimiento: ");
                        fecha = lee.readLine();
                        ((CtaPlazo)cta).getFechaVencimiento();
                        System.out.println("Fecha de vencimiento modificada");
                    break;
                    case 5: // Cuota
                        System.out.println("Introduzca la nueva cuota: ");
                        cuota = Float.parseFloat(lee.readLine());
                        ((CtaPlazo)cta).setCuota(cuota);
                        System.out.println("Cuota modificado");
                    break;
                    case 6: // Retencion
                        System.out.println("Introduzca la nueva retencion: ");
                        retencion = Float.parseFloat(lee.readLine());
                        ((CtaPlazo)cta).setRetencion(retencion);
                        System.out.println("Retencion modificado");
                    break;
                    case 7: // Clientes
                        do {
                            op2 = Menu.menuClientes(lee);

                            switch (op2) {
                                case 1:
                                    Altas.altasCliente(lee, cta);
                                break;
                                case 2:
                                    Visualizar.visualizarClientes(cta);
                                    System.out.println("Introduce el dni del cliente que desea dar de baja: ");
                                    dni = lee.readLine();
                                    Bajas.bajasCliente(lee, cta, dni);
                                break;
                            }
                        } while (op2 != 3);
                    break;
                    case 8: break;
                }
            } while (op != 8);
            
        } catch (Exception e) {}
    }
}
