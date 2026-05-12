package br.com.senac.clientes_api.services;

import br.com.senac.clientes_api.dtos.EnderecoFiltroDto;
import br.com.senac.clientes_api.dtos.EnderecoRequestDto;
import br.com.senac.clientes_api.entidades.Endereco;
import br.com.senac.clientes_api.repositorios.EnderecoRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

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

    public Endereco atualizar(Long id,
                              EnderecoRequestDto endereco){
        if (enderecoRepositorio.existsById(id)){
            Endereco enderecoPersist = this.enderecoRequestDtoParaEndereco(endereco);
            enderecoPersist.setId(id);
            return enderecoRepositorio.save(enderecoPersist);
        }
        throw new RuntimeException("Endereço não encontrado");
    }

    public Endereco criar(EnderecoRequestDto endereco){
        Endereco enderecoPersist = this.enderecoRequestDtoParaEndereco(endereco);

        return enderecoRepositorio.save(enderecoPersist);
    }

    public void deletar(Long id){
        if (enderecoRepositorio.existsById(id)){
            enderecoRepositorio.deleteById(id);
        }
        throw new RuntimeException("Endereço não encontrado");
    }

    public Endereco listarPorId(Long id){
        Optional<Endereco> retorno = enderecoRepositorio.findById(id);
        if(retorno.isPresent()){
            return retorno.get();
        }
        throw new RuntimeException("Endereço não encontrado");
    }

    public Endereco enderecoRequestDtoParaEndereco(EnderecoRequestDto entrada){
        Endereco saida = new Endereco();
        saida.setRua(entrada.getRua());
        saida.setBairro(entrada.getBairro());
        saida.setCep(entrada.getCep());
        saida.setCidade(entrada.getCidade());
        saida.setEstado(entrada.getEstado());
        saida.setComplemento(entrada.getComplemento());
        return saida;
    }
}

