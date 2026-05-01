package br.com.senac.clientes_api.services;

import br.com.senac.clientes_api.dtos.ClientesFiltroDto;
import br.com.senac.clientes_api.dtos.ClientesRequestDto;
import br.com.senac.clientes_api.entidades.Clientes;
import br.com.senac.clientes_api.repositorios.ClientesRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientesService {
    private ClientesRepositorio clienteRepositorio;

    public ClientesService(ClientesRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
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

    public List<Clientes> listar(ClientesFiltroDto filtro) {

        if (filtro.getId() != null) {
            return clienteRepositorio.findById(filtro.getId())
                    .map(List::of)
                    .orElse(List.of());
        }

        if (filtro.getNome() != null) {
            return clienteRepositorio
                    .findByNomeContainingIgnoreCase(filtro.getNome());
        }

        if (filtro.getEmail() != null) {
            return clienteRepositorio.findByEmail(filtro.getEmail());
        }

        return clienteRepositorio.findAll();
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





    private Clientes clientesRequestDtoParaClientes(ClientesRequestDto entrada){
        Clientes saida = new Clientes();
        saida.setNome(entrada.getNome());
        saida.setEmail(entrada.getEmail());
        saida.setDocumento(entrada.getDocumento());
        saida.setIdade(entrada.getIdade());

        return saida;
    }
}
