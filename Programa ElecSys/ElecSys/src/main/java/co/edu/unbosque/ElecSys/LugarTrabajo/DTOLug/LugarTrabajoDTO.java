package co.edu.unbosque.ElecSys.LugarTrabajo.DTOLug;

public class LugarTrabajoDTO {

    private int idLugar;
    private String nombreLugar;
    private String direccion;

    public LugarTrabajoDTO(int idLugar, String nombreLugar, String direccion) {
        this.idLugar = idLugar;
        this.nombreLugar = nombreLugar;
        this.direccion = direccion;
    }

    public int getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(int idLugar) {
        this.idLugar = idLugar;
    }

    public String getNombreLugar() {
        return nombreLugar;
    }

    public void setNombreLugar(String nombreLugar) {
        this.nombreLugar = nombreLugar;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
