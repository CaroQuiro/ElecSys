package co.edu.unbosque.ElecSys.AutenticacionSeguridad.DTOAut;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
    private String token;
    private String mensaje;
}

