package projeto.java.API.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto.java.API.entity.Cliente;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    List<Cliente> findByNomeContainingIgnoreCase(String nome);
    Optional<Cliente> findByCpf(String cpf);
    boolean existsByCpf(String cpf);
}
