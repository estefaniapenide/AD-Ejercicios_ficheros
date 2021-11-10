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
    public Duplex(){
    super();
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
    public float totalRbo(){
        float totalRbo=getCuotaFija()+getLitrosAguaCaliente()*0.4f+getPasosDeCalefaccion()*0.7f+getCuotaExtra();
    return totalRbo;
    }
    
}
