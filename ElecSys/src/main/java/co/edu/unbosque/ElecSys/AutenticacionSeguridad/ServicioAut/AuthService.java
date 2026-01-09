package co.edu.unbosque.ElecSys.AutenticacionSeguridad.ServicioAut;

import co.edu.unbosque.ElecSys.AutenticacionSeguridad.DTOAut.LoginRequestDTO;
import co.edu.unbosque.ElecSys.AutenticacionSeguridad.DTOAut.LoginResponseDTO;
import co.edu.unbosque.ElecSys.AutenticacionSeguridad.DTOAut.VerificacionCodigoDTO;
import co.edu.unbosque.ElecSys.AutenticacionSeguridad.SeguridadAut.JwtUtil;
import co.edu.unbosque.ElecSys.Notificacion.EnvioEmail.ConfiguracionEmail;
import co.edu.unbosque.ElecSys.Usuario.Trabajador.EntidadTra.TrabajadorEntidad;
import co.edu.unbosque.ElecSys.Usuario.Trabajador.ServicioTra.TrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Autowired
    private ConfiguracionEmail configuracionEmail;

    private final Map<String, String> codigosTemporales = new HashMap<>();

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /* =========================
       LOGIN INICIAL
       ========================= */
    public String login(LoginRequestDTO dto) {

        TrabajadorEntidad trabajador =
                trabajadorRepository.findByCorreo(dto.getCorreo());

        if (trabajador == null)
            return "Usuario no encontrado";

        if (!encoder.matches(dto.getPassword(), trabajador.getPassword()))
            return "Contraseña incorrecta";

        // Generar código
        String codigo = generarCodigo();

        codigosTemporales.put(trabajador.getCorreo(), codigo);

        configuracionEmail.crearEmail(
                trabajador.getNombre(),
                trabajador.getCorreo(),
                "Código de acceso",
                "Tu código de verificación es: " + codigo,
                null
        );

        configuracionEmail.envioEmail();

        return "Código enviado al correo";
    }

    /* =========================
       VERIFICAR CÓDIGO
       ========================= */
    public LoginResponseDTO verificarCodigo(VerificacionCodigoDTO dto) {

        String codigoGuardado = codigosTemporales.get(dto.getCorreo());

        if (codigoGuardado == null)
            throw new RuntimeException("No hay código generado");

        if (!codigoGuardado.equals(dto.getCodigo()))
            throw new RuntimeException("Código incorrecto");

        codigosTemporales.remove(dto.getCorreo());

        String token = JwtUtil.generarToken(dto.getCorreo());

        return new LoginResponseDTO(token, "Acceso concedido");
    }

    private String generarCodigo() {
        return String.valueOf(
                (int)(Math.random() * 900000) + 100000
        );
    }
}

