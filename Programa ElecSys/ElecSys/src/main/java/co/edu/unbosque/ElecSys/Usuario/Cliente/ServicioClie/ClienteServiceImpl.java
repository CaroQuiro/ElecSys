package co.edu.unbosque.ElecSys.Usuario.Cliente.ServicioClie;

import co.edu.unbosque.ElecSys.Usuario.Cliente.DTOClie.ClienteDTO;
import co.edu.unbosque.ElecSys.Usuario.Cliente.EntidadClie.ClienteEntidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteInterface{

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public String agregarCliente(ClienteDTO cliente) {
        ClienteEntidad nuevoCliente = new ClienteEntidad(
          cliente.getId_cliente(),
          cliente.getNombre(),
          cliente.getTelefono(),
          cliente.getDireccion(),
          cliente.getCorreo()
        );
        try{
            clienteRepository.save(nuevoCliente);
            return "Cliente creado exitosamente";
        } catch (Exception e) {
            return "Error al crear el cliente";
        }
    }

    @Override
    public ClienteDTO buscarCliente(int id) {
        return null;
    }

    @Override
    public String borrarCliente(int id) {
        try{
            clienteRepository.deleteById(id);
            return "Cliente elimnado exitosamente";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public List<ClienteDTO> listarClientes() {
        List<ClienteEntidad> cliente = clienteRepository.findAll();
        List<ClienteDTO> clienteDTOS = new ArrayList<>();

        for (ClienteEntidad clientes : cliente){
            clienteDTOS.add(new ClienteDTO(
                    clientes.getId_cliente(),
                    clientes.getNombre(),
                    clientes.getTelefono(),
                    clientes.getDireccion(),
                    clientes.getCorreo()
            ));
        }

        return clienteDTOS;
    }

    @Override
    public String actualizarCliente(int id, ClienteDTO cliente) {

        Optional<ClienteEntidad> clienteExit = clienteRepository.findById(id);
        if (clienteExit.isEmpty()){
            return "Cliente no encontrado para actualizar";
        }else {
            ClienteEntidad entidad = clienteExit.get();

            entidad.setNombre(cliente.getNombre());
            entidad.setTelefono(cliente.getTelefono());
            entidad.setDireccion(cliente.getDireccion());
            entidad.setCorreo(cliente.getCorreo());

            clienteRepository.save(entidad);
            return "Cliente Actualizado Correctamente";
        }
    }
}
