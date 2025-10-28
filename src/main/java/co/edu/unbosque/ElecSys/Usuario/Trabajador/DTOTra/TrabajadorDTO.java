package co.edu.unbosque.ElecSys.Usuario.Trabajador.DTOTra;

import co.edu.unbosque.ElecSys.Usuario.Trabajador.EntidadTra.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrabajadorDTO {

    private int id_trabajador;
    private String nombre;
    private String telefono;
    private String direccion;
    private String correo;
    private TipoUsuario tipo_usuario;
    private String password;
}
