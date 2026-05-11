package br.com.senac.clientes_api.repositorios;

import br.com.senac.clientes_api.entidades.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepositorio extends JpaRepository<Endereco, Long> {
    List<Endereco> findByRuaContaining(String rua);
    List<Endereco> findByBairroContaining(String bairro);
    List<Endereco> findByCepContaining(String cep);
}
