package co.edu.unbosque.ElecSys.HistorialActividad.DetalleActividad.DTODetalleActividad;

public class DetalleActividadDTO {

    private int idDetalleActividad;
    private int idHistorial;
    private String campoAfectado;
    private String valorAnterior;
    private String valorNuevo;

    public DetalleActividadDTO(int idDetalleActividad, int idHistorial, String campoAfectado, String valorAnterior, String valorNuevo) {
        this.idDetalleActividad = idDetalleActividad;
        this.idHistorial = idHistorial;
        this.campoAfectado = campoAfectado;
        this.valorAnterior = valorAnterior;
        this.valorNuevo = valorNuevo;
    }

    public DetalleActividadDTO() {

    }

    public int getIdDetalleActividad() {
        return idDetalleActividad;
    }

    public void setIdDetalleActividad(int idDetalleActividad) {
        this.idDetalleActividad = idDetalleActividad;
    }

    public int getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    public String getCampoAfectado() {
        return campoAfectado;
    }

    public void setCampoAfectado(String campoAfectado) {
        this.campoAfectado = campoAfectado;
    }

    public String getValorAnterior() {
        return valorAnterior;
    }

    public void setValorAnterior(String valorAnterior) {
        this.valorAnterior = valorAnterior;
    }

    public String getValorNuevo() {
        return valorNuevo;
    }

    public void setValorNuevo(String valorNuevo) {
        this.valorNuevo = valorNuevo;
    }
}
