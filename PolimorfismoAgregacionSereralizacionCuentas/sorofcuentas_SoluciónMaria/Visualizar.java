
package Secuenciales.SeriacionObjetosQReferencianObjetos.SOROFCuentas.sorofcuentas;
import java.util.*;
import Secuenciales.SeriacionObjetosQReferencianObjetos.SOROFCuentas.clases.*;
/**
 *
 * @author 
 */
public class Visualizar {
    
    public static void visualizar (ArrayList <Cuenta> cuentas) {
        
        int i, j;
        
        if (!cuentas.isEmpty()) {
            System.out.println("\033[34m                                                        LISTA DE CUENTAS");
                System.out.println(" ────────────────────────────────────────────────────────────────────────────────");
                System.out.printf(" \033[34m%s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%n", "|", "-----CUENTA-----", "-----NUMERO-----", "----SUCURSAL----", "-----SALDO------", "----DEPOSITO----", "---GANANCIAS----", "----CLIENTES----");
            for (i = 0; i < cuentas.size(); i++) {
                if (cuentas.get(i) instanceof CtaCorriente) {
                    System.out.printf(" \033[35m%s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16d%1$s%1$s%-16s%1$s%1$s%-16.2f%1$s%1$s%-16d%1$s%n", "|", "   Corriente    ", cuentas.get(i).getNumero(), cuentas.get(i).getSucursal(), cuentas.get(i).getSaldo(), "       ---      ", cuentas.get(i).ganancia(), cuentas.get(i).getCli().size());
                    if (!cuentas.get(i).getCli().isEmpty()) {
                        System.out.println(" |-----------------------------------------------------LISTA DE CLIENTES------------------------------------------------------|");
                        System.out.printf(" \033[34m%s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%n", "|", "----------------", "-------DNI------", "-----NOMBRE-----", "----DIRECCION----", "----------------", "----------------", "----------------");
                        for (j = 0; j < cuentas.get(i).getCli().size(); j++) {
                            System.out.printf(" \033[35m%s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%n", "|", "    Cliente     ", cuentas.get(i).getCli().get(j).getDni(), cuentas.get(i).getCli().get(j).getNombre(), cuentas.get(i).getCli().get(j).getDir(), "       ---      ", "       ---      ", "       ---      ");
                        }
                        System.out.println(" ────────────────────────────────────────────────────────────────────────────────");
                        System.out.printf(" \033[34m%s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%n", "|", "-----CUENTA-----", "-----NUMERO-----", "----SUCURSAL----", "-----SALDO------", "----DEPOSITO----", "---GANANCIAS----", "----CLIENTES----");
                    }
                }
                else {
                    System.out.printf(" \033[35m%s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16d%1$s%1$s%-16.2f%1$s%1$s%-16d%1$s%n", "|", "     Plazo      ", cuentas.get(i).getNumero(), cuentas.get(i).getSucursal(), "       ---      ", ((CtaPlazo)cuentas.get(i)).getDeposito(), cuentas.get(i).ganancia(), cuentas.get(i).getCli().size());
                    if (!cuentas.get(i).getCli().isEmpty()) {
                        System.out.println(" |------------------------------------------------------LISTA DE CLIENTES-----------------------------------------------------|");
                        System.out.printf(" \033[34m%s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%n", "|", "----------------", "-------DNI------", "-----NOMBRE-----", "----TELEFONO----", "----------------", "----------------", "----------------");
                        for (j = 0; j < cuentas.get(i).getCli().size(); j++) {
                            System.out.printf(" \033[35m%s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%n", "|", "    Cliente     ", cuentas.get(i).getCli().get(j).getDni(), cuentas.get(i).getCli().get(j).getNombre(), cuentas.get(i).getCli().get(j).getDir(), "       ---      ", "       ---      ", "       ---      ");
                        }
                        System.out.println(" ────────────────────────────────────────────────────────────────────────────────");
                        System.out.printf(" \033[34m%s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%n", "|", "-----CUENTA-----", "-----NUMERO-----", "----SUCURSAL----", "-----SALDO------", "----DEPOSITO----", "---GANANCIAS----", "----CLIENTES----");
                    }
                }
            }
            System.out.println(" ────────────────────────────────────────────────────────────────────────────────");
        }
        else
            System.out.println("No hay registros, el fichero esta vacio");
    }
    
    public static void visualizarCta (ArrayList <Cuenta> cuentas) {
        
        int i, j;
        
        if (!cuentas.isEmpty()) {
            System.out.println("\033[34m                                                        LISTA DE CUENTAS");
                System.out.println(" ────────────────────────────────────────────────────────────────────────────────");
                System.out.printf(" \033[34m%s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%n", "|", "-----CUENTA-----", "-----NUMERO-----", "----SUCURSAL----", "-----SALDO------", "----DEPOSITO----", "---GANANCIAS----", "----CLIENTES----");
            for (i = 0; i < cuentas.size(); i++) {
                if (cuentas.get(i) instanceof CtaCorriente) {
                    System.out.printf(" \033[35m%s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16d%1$s%1$s%-16s%1$s%1$s%-16.2f%1$s%1$s%-16d%1$s%n", "|", "   Corriente    ", cuentas.get(i).getNumero(), cuentas.get(i).getSucursal(), cuentas.get(i).getSaldo(), "       ---      ", cuentas.get(i).ganancia(), cuentas.get(i).getCli().size());
                }
                else {
                    System.out.printf(" \033[35m%s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16d%1$s%1$s%-16.2f%1$s%1$s%-16d%1$s%n", "|", "     Plazo      ", cuentas.get(i).getNumero(), cuentas.get(i).getSucursal(), "       ---      ", ((CtaPlazo)cuentas.get(i)).getDeposito(), cuentas.get(i).ganancia(), cuentas.get(i).getCli().size());
                }
            }
            System.out.println(" ────────────────────────────────────────────────────────────────────────────────");
        }
        else
            System.out.println("No hay registros en la base de datos");
    }
    
    public static void visualizarClientes (Cuenta cta) {
        
        int j;
        

        System.out.println("\033[34m                                                        LISTA DE CLIENTES");
        System.out.println(" ────────────────────────────────────────────────────────────────────────────────");
        System.out.printf(" \033[34m%s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%n", "|", "-----CUENTA-----", "-----NUMERO-----", "----SUCURSAL----", "-----SALDO------", "----DEPOSITO----", "---GANANCIAS----", "----CLIENTES----");

        if (cta instanceof CtaCorriente) {
            System.out.printf(" \033[35m%s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16d%1$s%1$s%-16s%1$s%1$s%-16.2f%1$s%1$s%-16d%1$s%n", "|", "   Corriente    ", cta.getNumero(), cta.getSucursal(), cta.getSaldo(), "       ---      ", cta.ganancia(), cta.getCli().size());
            if (!cta.getCli().isEmpty()) {
                System.out.println(" |-----------------------------------------------------LISTA DE CLIENTES------------------------------------------------------|");
                System.out.printf(" \033[34m%s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%n", "|", "----------------", "-------DNI------", "-----NOMBRE-----", "----------------", "----------------", "----------------", "----------------");
                for (j = 0; j < cta.getCli().size(); j++) {
                    System.out.printf(" \033[35m%s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%n", "|", "    Cliente     ", cta.getCli().get(j).getDni(), cta.getCli().get(j).getNombre(), "       ---      ", "       ---      ", "       ---      ", "       ---      ");
                }
            }
            System.out.println(" ────────────────────────────────────────────────────────────────────────────────");
        }
        else {
            System.out.printf(" \033[35m%s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16d%1$s%1$s%-16.2f%1$s%1$s%-16d%1$s%n", "|", "     Plazo      ", cta.getNumero(), cta.getSucursal(), "       ---      ", ((CtaPlazo)cta).getDeposito(), cta.ganancia(), cta.getCli().size());
            if (!cta.getCli().isEmpty()) {
                System.out.println(" |------------------------------------------------------LISTA DE CLIENTES-----------------------------------------------------|");
                System.out.printf(" \033[34m%s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%n", "|", "----------------", "-------DNI------", "-----NOMBRE-----", "----------------", "----------------", "----------------", "----------------");
                for (j = 0; j < cta.getCli().size(); j++) {
                    System.out.printf(" \033[35m%s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%1$s%-16s%1$s%n", "|", "    Cliente     ", cta.getCli().get(j).getDni(), cta.getCli().get(j).getNombre(), "       ---      ", "       ---      ", "       ---      ", "       ---      ");
                }
            }
            System.out.println(" ────────────────────────────────────────────────────────────────────────────────");
        }

    }
}
