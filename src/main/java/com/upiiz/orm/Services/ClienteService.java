package com.upiiz.orm.Services;

import com.upiiz.orm.Models.ClienteEntity;
import com.upiiz.orm.Repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    // Inyectar una dependencia de ClienteRepository
    @Autowired
    private ClienteRepository clienteRepository;

    // Listar todos los clientes
    public List<ClienteEntity> getAllClientes() {
        return clienteRepository.findAll();
    }

    // Guardar un cliente
    public ClienteEntity saveCliente(ClienteEntity cliente) {
        return clienteRepository.save(cliente);
    }

    // Buscar un cliente por id
    public ClienteEntity getClienteById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    // Actualizar un cliente por id
    public ClienteEntity updateClient(Long id, ClienteEntity clienteUpdate) {
        Optional<ClienteEntity> clienteOpt = clienteRepository.findById(id);
        if (clienteOpt.isPresent()) {
            // Si el valor esta presente retorna el valor com .get()
            ClienteEntity cliente = clienteOpt.get();
            cliente.setNombre(clienteUpdate.getNombre());
            cliente.setApellido(clienteUpdate.getApellido());
            cliente.setEmail(clienteUpdate.getEmail());
            cliente.setTelefono(clienteUpdate.getTelefono());
            cliente.setDireccion(clienteUpdate.getDireccion());
            cliente.setEdad(clienteUpdate.getEdad());
            cliente.setRfc(clienteUpdate.getRfc());

            return clienteRepository.save(cliente);
        }
        return null;
    }

    // Eliminar un cliente por id
    public boolean deleteCliente(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
