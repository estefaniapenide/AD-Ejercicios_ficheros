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
public class Atico extends Piso {

    //atributos
    private float metrosTerraza;

    //cosntructores
    /**
     * Constructor vacío
     */
    public Atico() {
        super();
    }
    
    public Atico(String referencia){
    super(referencia);
    }

    public Atico(String referencia, String nombrePropietario, float cuotaFija, float litrosAguaCaliente, float pasosDecalefaccion, float metrosTerraza) {
        super(referencia, nombrePropietario, cuotaFija, litrosAguaCaliente, pasosDecalefaccion);
        this.metrosTerraza=metrosTerraza;
        //totalRbo();
    }

    /**
     * @return the metrosTerraza
     */
    public float getMetrosTerraza() {
        return metrosTerraza;
    }

    /**
     * @param metrosTerraza the metrosTerraza to set
     */
    public void setMetrosTerraza(float metrosTerraza) {
        this.metrosTerraza = metrosTerraza;
    }

    @Override
    public float totalRbo() {
        float totalRbo = getCuotaFija() + getLitrosAguaCaliente() * 0.4f + getPasosDeCalefaccion() * 0.7f + metrosTerraza * 0.45f;
        setTotalReciboComunidad(totalRbo);
        return totalRbo;
    }
    
    @Override
    public int getTamReal() {
        return super.getTamReal() + 4;
    }
    
     @Override
    public String toString() {
        String duplex = super.toString()
                + "Terraza(m2): " + getMetrosTerraza() + "\n"
                + "\tTOTAL RECIBO(€): " + totalRbo() + "\n";
        return duplex;
    }
    

}
