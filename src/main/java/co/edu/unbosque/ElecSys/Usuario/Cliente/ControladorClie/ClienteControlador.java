package co.edu.unbosque.ElecSys.Usuario.Cliente.ControladorClie;

import co.edu.unbosque.ElecSys.Usuario.Cliente.DTOClie.ClienteDTO;
import co.edu.unbosque.ElecSys.Usuario.Cliente.ServicioClie.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteControlador {

    @Autowired
    private ClienteServiceImpl clienteService;

    @GetMapping("/listar")
    public List<ClienteDTO> listarClientes(){
        return clienteService.listarClientes();
    }

    @PostMapping("/agregar")
    public String agregarCliente(@RequestBody ClienteDTO dto){
        return clienteService.agregarCliente(dto);
    }

    @DeleteMapping("/borrar/{id}")
    public String borrarCliente(@PathVariable int id){
        return  clienteService.borrarCliente(id);
    }

    @PutMapping("/actualizar/{id}")
    public String actualizarCliente(@PathVariable int id, @RequestBody ClienteDTO dto){
        return  clienteService.actualizarCliente(id, dto);
    }
}
