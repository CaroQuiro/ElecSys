package co.edu.unbosque.ElecSys.AutenticacionSeguridad.DTOAut;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String usuario;
    private String correo;
    private String password;
}

