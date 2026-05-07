package br.com.senac.clientes_api.services;

import br.com.senac.clientes_api.dtos.ClientesFiltroDto;
import br.com.senac.clientes_api.dtos.ClientesRequestDto;
import br.com.senac.clientes_api.entidades.Clientes;
import br.com.senac.clientes_api.repositorios.ClientesRepositorio;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;

@Service
public class ClientesService {
    private ClientesRepositorio clienteRepositorio;

    public ClientesService(ClientesRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    @GetMapping("/listar")
    public List<Clientes> listar(ClientesFiltroDto filtro){
        if (filtro.getNome() != null){
            return clienteRepositorio.findByNomeContaining(filtro.getNome());
        }
        return clienteRepositorio.findAll();
    }

    public Clientes atualizar(Long id,
                              ClientesRequestDto cliente) {
        if (clienteRepositorio.existsById(id)) {
            Clientes clientePersist = this.clientesRequestDtoParaClientes(cliente);
            clientePersist.setId(id);
            return clienteRepositorio.save(clientePersist);
        }
        throw new RuntimeException("Usuário não encontrado");
    }

    public Clientes criar(ClientesRequestDto cliente){
        Clientes clientePersist = this.clientesRequestDtoParaClientes(cliente);

        return clienteRepositorio.save(clientePersist);
    }

    public void deletar(Long id){
        if (clienteRepositorio.existsById(id)) {
            clienteRepositorio.deleteById(id);
        }
        throw new RuntimeException("Cliente não encontrado");
    }

    public Clientes listarPorId(Long id){
        Optional<Clientes> retorno = clienteRepositorio.findById(id);
        if(retorno.isPresent()){
            return retorno.get();
        }

        throw new RuntimeException("Cliente nao encontrado");
    }



    private Clientes clientesRequestDtoParaClientes(ClientesRequestDto entrada){
        Clientes saida = new Clientes();
        saida.setNome(entrada.getNome());
        saida.setEmail(entrada.getEmail());
        saida.setDocumento(entrada.getDocumento());
        saida.setIdade(entrada.getIdade());

        return saida;
    }
}
