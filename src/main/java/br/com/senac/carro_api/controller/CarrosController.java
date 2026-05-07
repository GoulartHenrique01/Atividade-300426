package br.com.senac.carro_api.controller;

import br.com.senac.carro_api.dtos.CarrosFiltroDto;
import br.com.senac.carro_api.dtos.CarrosRequestDto;
import br.com.senac.carro_api.entidades.Carros;
import br.com.senac.carro_api.services.CarrosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/carros")
public class CarrosController {
    private CarrosService carroServices;

    public CarrosController(CarrosService carroServices) {
        this.carroServices = carroServices;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Carros>> listar(CarrosFiltroDto filtro) {
        return ResponseEntity.ok(carroServices.listar(filtro));
    }

    @PostMapping("/criar")
    public ResponseEntity<Carros> criar(@RequestBody CarrosRequestDto carro) {
        try {
            return ResponseEntity.ok(carroServices.criar(carro));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Carros> atualizar(
            @RequestBody CarrosRequestDto carro,
            @PathVariable Long id) {
        try {
            return ResponseEntity.ok(carroServices.atualizar(id, carro));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(null);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            carroServices.deletar(id);
            return ResponseEntity.ok(null);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(null);
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Carros> listarById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(carroServices.listarById(id));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(null);
        }
    }
}