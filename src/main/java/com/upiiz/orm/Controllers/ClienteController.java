package com.upiiz.orm.Controllers;

import com.upiiz.orm.Models.ClienteEntity;
import com.upiiz.orm.Services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteController {
    // Inyectar una dependencia de ClienteService
    @Autowired
    private ClienteService clienteService;

    // Listar todos los clientes
    @GetMapping
    public ResponseEntity<List<ClienteEntity>> getAllClientes() {
        return ResponseEntity.ok(clienteService.getAllClientes());
    }

    // Guardar un cliente
    @PostMapping
    public ResponseEntity<ClienteEntity> saveCliente(@RequestBody ClienteEntity cliente) {
        ClienteEntity savedCliente = clienteService.saveCliente(cliente);
        URI location = URI.create("/api/v1/cliente/" + savedCliente.getId());
        return ResponseEntity.created(location).body(savedCliente);
    }

    // Buscar un cliente por id
    @GetMapping("/{id}")
    public ResponseEntity<ClienteEntity> getClienteById(@PathVariable Long id) {
        ClienteEntity cliente = clienteService.getClienteById(id);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }

    // Actualizar un cliente por id
    @PutMapping("/{id}")
    public ResponseEntity<ClienteEntity> updateCliente(@PathVariable Long id, @RequestBody ClienteEntity clienteUpdate) {
        ClienteEntity updatedCliente = clienteService.updateClient(id, clienteUpdate);
        return updatedCliente != null ? ResponseEntity.ok(updatedCliente) : ResponseEntity.notFound().build();
    }

    // Eliminar un cliente por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        if (clienteService.deleteCliente(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
