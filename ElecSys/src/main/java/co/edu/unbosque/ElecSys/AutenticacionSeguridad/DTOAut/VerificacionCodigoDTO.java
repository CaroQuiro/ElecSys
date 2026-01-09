package co.edu.unbosque.ElecSys.AutenticacionSeguridad.DTOAut;

import lombok.Data;

@Data
public class VerificacionCodigoDTO {
    private String correo;
    private String codigo;
}

