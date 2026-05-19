package projeto.java.API.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.java.API.entity.Cliente;
import projeto.java.API.entity.Veiculo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VeiculoRepository extends JpaRepository<Veiculo, UUID> {
    Optional<Veiculo> findByPlaca (String placa);
    List<Veiculo> findByMarcaContainingIgnoreCase(String marca);
    List<Veiculo> findByModeloContainingIgnoreCase(String modelo);
    boolean existsByPlaca(String placa);
    boolean existsByProprietario (Cliente cliente);
}
