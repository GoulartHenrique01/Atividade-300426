package br.com.senac.clientes_api.controllers;

import br.com.senac.clientes_api.dtos.EnderecoFiltroDto;
import br.com.senac.clientes_api.dtos.EnderecoRequestDto;
import br.com.senac.clientes_api.entidades.Endereco;
import br.com.senac.clientes_api.services.EnderecoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/endereco")
@CrossOrigin
public class EnderecoController {
    private EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Endereco>> listar(EnderecoFiltroDto filtro) {
        return ResponseEntity
                .ok(enderecoService.listar(filtro));
    }
    @GetMapping("/listar/{id}")
    public ResponseEntity<Endereco> listarPorId(@PathVariable Long id) {
        try {
            return  ResponseEntity.ok(enderecoService.listarPorId(id));
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @PutMapping("/criar")
    public ResponseEntity<Endereco> criar(@RequestBody EnderecoRequestDto endereco) {
        try {
            return  ResponseEntity.ok(enderecoService.criar(endereco));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Endereco> atualizar(@RequestBody EnderecoRequestDto endereco,
                                              @PathVariable Long id) {
        try {
            return  ResponseEntity.ok(enderecoService.atualizar(id, endereco));
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            enderecoService.deletar(id);
            return ResponseEntity.ok(null);
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
