package co.edu.unbosque.ElecSys.AutenticacionSeguridad.ControladorAut;

import co.edu.unbosque.ElecSys.AutenticacionSeguridad.DTOAut.LoginRequestDTO;
import co.edu.unbosque.ElecSys.AutenticacionSeguridad.DTOAut.LoginResponseDTO;
import co.edu.unbosque.ElecSys.AutenticacionSeguridad.DTOAut.VerificacionCodigoDTO;
import co.edu.unbosque.ElecSys.AutenticacionSeguridad.ServicioAut.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/aut")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO dto) {
        return authService.login(dto);
    }

    @PostMapping("/verificar")
    public LoginResponseDTO verificar(@RequestBody VerificacionCodigoDTO dto) {
        return authService.verificarCodigo(dto);
    }
}

