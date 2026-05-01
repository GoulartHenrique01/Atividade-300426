package br.com.senac.clientes_api.controllers;

import br.com.senac.clientes_api.dtos.ClientesFiltroDto;
import br.com.senac.clientes_api.dtos.ClientesRequestDto;
import br.com.senac.clientes_api.entidades.Clientes;
import br.com.senac.clientes_api.repositorios.ClientesRepositorio;
import br.com.senac.clientes_api.services.ClientesService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClientesController {
    private ClientesService clientesService;

    public ClientesController(ClientesService clientesService) {
        this.clientesService = clientesService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Clientes>> listar(ClientesFiltroDto filtro) {
        return ResponseEntity
                .ok(clientesService.listar(filtro));
    }

    @PostMapping("/criar")
    public ResponseEntity<Clientes> criar(
            @RequestBody ClientesRequestDto cliente) {
        try {
            return ResponseEntity.ok(clientesService.criar(cliente));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Clientes> atualizar(
            @RequestBody ClientesRequestDto cliente,
            @PathVariable Long id) {
        try {
            return ResponseEntity.ok(clientesService.atualizar(id, cliente));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
    }
}

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id) {
        try {
            clientesService.deletar(id);
            return ResponseEntity.ok(null);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
