package co.edu.unbosque.ElecSys.Cotizacion.DTOCot;

import java.util.Date;

public class CotizacionDTO {

    private int id_cotizacion;
    private int referencia;
    private float valor_total;
    private String estado;
    private Date fecha;

    public CotizacionDTO(int id_cotizacion, int referencia, float valor_total, String estado, Date fecha){
        this.id_cotizacion = id_cotizacion;
        this.referencia=referencia;
        this.valor_total=valor_total;
        this.estado=estado;
        this.fecha=fecha;
    }

    public int getId_cotizacion() {
        return id_cotizacion;
    }

    public void setId_cotizacion(int id_cotizacion) {
        this.id_cotizacion = id_cotizacion;
    }

    public int getReferencia() {
        return referencia;
    }

    public void setReferencia(int referencia) {
        this.referencia = referencia;
    }

    public float getValor_total() {
        return valor_total;
    }

    public void setValor_total(float valor_total) {
        this.valor_total = valor_total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


}
