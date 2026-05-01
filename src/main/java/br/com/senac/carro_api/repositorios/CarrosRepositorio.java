package br.com.senac.carro_api.repositorios;

import br.com.senac.carro_api.entidades.Carros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CarrosRepositorio extends JpaRepository<Carros, Long> {

    List<Carros> findByModelo(String modelo);


}
