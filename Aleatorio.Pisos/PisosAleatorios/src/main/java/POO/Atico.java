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
public class Atico extends Piso{
    
    public float metrosTerraza;
    
    public Atico(){
    super();
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
    
    
    public float totalRbo(){
        float totalRbo=getCuotaFija()+getLitrosAguaCaliente()*0.4f+getPasosDeCalefaccion()*0.7f+metrosTerraza*0.45f;
    return totalRbo;
    }
    
}
