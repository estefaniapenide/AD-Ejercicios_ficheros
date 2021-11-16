/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POO;

/**
 *
 * @author a20estefaniapc
 */
public class Duplex extends Piso {

    //atributos
    private float cuotaExtra;

    //constructores
    /**
     * Cosntructor vacío
     */
    public Duplex() {
        super();
    }

    public Duplex(String referencia) {
        super(referencia);
    }

    public Duplex(String referencia, String nombrePropietario, float cuotaFija, float litrosAguaCaliente, float pasosDeCalefaccion, float cuotaExtra) {
        super(referencia, nombrePropietario, cuotaFija, litrosAguaCaliente, pasosDeCalefaccion);
        this.cuotaExtra = cuotaExtra;
        //totalRbo();

    }

    /**
     * @return the cuotaExtra
     */
    public float getCuotaExtra() {
        return cuotaExtra;
    }

    /**
     * @param cuotaExtra the cuotaExtra to set
     */
    public void setCuotaExtra(float cuotaExtra) {
        this.cuotaExtra = cuotaExtra;
    }

    @Override
    public float totalRbo() {
        float totalRbo = getCuotaFija() + getLitrosAguaCaliente() * 0.4f + getPasosDeCalefaccion() * 0.7f + getCuotaExtra();
        setTotalReciboComunidad(totalRbo);
        return totalRbo;
    }

    @Override
    public int getTamReal() {
        //TamañoReal Piso + float cuotaExtra(4)
        return super.getTamReal() + 4;
    }

    @Override
    public String toString() {
        String duplex = super.toString()
                + "Cuota extra(€): " + getCuotaExtra() + "\n"
                + "\tTOTAL RECIBO(€): " + totalRbo() + "\n";
        return duplex;
    }

}
