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
public abstract class Piso {
    
     public String referencia;
     public char tipoPiso;
     public String nombrePropietario;
     public float cuotaFija;
     public float litrosAguaCaliente;
     public float pasosDeCalefaccion;
     public float TotalReciboComunidad;
     
     public Piso(){
     
     }

    /**
     * @return the referencia
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * @param referencia the referencia to set
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    /**
     * @return the tipoPiso
     */
    public char getTipoPiso() {
        return tipoPiso;
    }

    /**
     * @param tipoPiso the tipoPiso to set
     */
    public void setTipoPiso(char tipoPiso) {
        this.tipoPiso = tipoPiso;
    }

    /**
     * @return the nombrePropietario
     */
    public String getNombrePropietario() {
        return nombrePropietario;
    }

    /**
     * @param nombrePropietario the nombrePropietario to set
     */
    public void setNombrePropietario(String nombrePropietario) {
        this.nombrePropietario = nombrePropietario;
    }

    /**
     * @return the cuotaFija
     */
    public float getCuotaFija() {
        return cuotaFija;
    }

    /**
     * @param cuotaFija the cuotaFija to set
     */
    public void setCuotaFija(float cuotaFija) {
        this.cuotaFija = cuotaFija;
    }

    /**
     * @return the litrosAguaCaliente
     */
    public float getLitrosAguaCaliente() {
        return litrosAguaCaliente;
    }

    /**
     * @param litrosAguaCaliente the litrosAguaCaliente to set
     */
    public void setLitrosAguaCaliente(float litrosAguaCaliente) {
        this.litrosAguaCaliente = litrosAguaCaliente;
    }

    /**
     * @return the pasosDeCalefaccion
     */
    public float getPasosDeCalefaccion() {
        return pasosDeCalefaccion;
    }

    /**
     * @param pasosDeCalefaccion the pasosDeCalefaccion to set
     */
    public void setPasosDeCalefaccion(float pasosDeCalefaccion) {
        this.pasosDeCalefaccion = pasosDeCalefaccion;
    }

    /**
     * @return the TotalReciboComunidad
     */
    public float getTotalReciboComunidad() {
        return TotalReciboComunidad;
    }

    /**
     * @param TotalReciboComunidad the TotalReciboComunidad to set
     */
    public void setTotalReciboComunidad(float TotalReciboComunidad) {
        this.TotalReciboComunidad = TotalReciboComunidad;
    }
     
    public abstract float totalRbo();
    
}
