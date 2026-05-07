package br.com.senac.clientes_api.repositorios;

import br.com.senac.clientes_api.entidades.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientesRepositorio
        extends JpaRepository<Clientes, Long> {
    List<Clientes> findByNomeContaining(String  nome);
}
