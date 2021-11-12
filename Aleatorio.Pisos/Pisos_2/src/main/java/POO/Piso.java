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

    private static final int tamMax = 140;

    //atributos
    private String referencia;
    private char tipoPiso;
    private String nombrePropietario;
    private float cuotaFija;
    private float litrosAguaCaliente;
    private float pasosDeCalefaccion;
    private float TotalReciboComunidad;

    //constructores
    /**
     * Constructor vacío
     */
    public Piso() {

    }

    public Piso(String referencia) {
        this.referencia = referencia;
    }

    public Piso(String referencia, String nombrePropietario, float cuotaFija, float litrosAguaCaliente, float pasosDeCalefaccion) {

        this.referencia = referencia;
        this.nombrePropietario = nombrePropietario;
        this.cuotaFija = cuotaFija;
        this.litrosAguaCaliente = litrosAguaCaliente;
        this.pasosDeCalefaccion = pasosDeCalefaccion;
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
        if (this instanceof Atico) {
            this.tipoPiso = 'A';
        } else if (this instanceof Duplex) {
            this.tipoPiso = 'D';
        }
        return tipoPiso;
    }

//    /**
//     * @param tipoPiso the tipoPiso to set
//     */
//    public void setTipoPiso(char tipoPiso) {
//        this.tipoPiso = tipoPiso;
//    }

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

    public int getTamMax() {
        return tamMax;
    }

    public int getTamReal() {
        return this.referencia.length() * 2 + this.nombrePropietario.length() * 2 + 1 + 4 + 4 + 4 + 4;
    }
    
    @Override
    public String toString(){
     String piso= "RECIBO " + getReferencia() + "\n"
                + "Propietario: " + getNombrePropietario() + "\n"
                + "Tipo de piso: " + getTipoPiso() + "\n"
                + "Cuota fija(€): " + getCuotaFija() + "\n"
                + "Agua caliente(L): " + getLitrosAguaCaliente() + "\n"
                + "Calefacción: " + getPasosDeCalefaccion() + "\n";
     return piso;
    }

}
