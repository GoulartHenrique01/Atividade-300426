package br.com.senac.clientes_api.services;

import br.com.senac.clientes_api.dtos.EnderecoFiltroDto;
import br.com.senac.clientes_api.dtos.EnderecoRequestDto;
import br.com.senac.clientes_api.entidades.Endereco;
import br.com.senac.clientes_api.repositorios.EnderecoRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Service
public class EnderecoService {
    private EnderecoRepositorio enderecoRepositorio;

    public EnderecoService(EnderecoRepositorio enderecoRepositorio) {
        this.enderecoRepositorio = enderecoRepositorio;
    }

    @GetMapping("/listar")
    public List<Endereco> listar(EnderecoFiltroDto filtro){
        if (filtro.getRua() != null){
            return enderecoRepositorio.findByRuaContaining(filtro.getRua());
        }
        if (filtro.getBairro() != null){
            return enderecoRepositorio.findByBairroContaining(filtro.getBairro());
        }
        if (filtro.getCep() != null){
            return enderecoRepositorio.findByCepContaining(filtro.getCep());
        }
        return enderecoRepositorio.findAll();
    }
}

