package projeto.java.API.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.java.API.entity.Veiculo;

import java.util.List;
import java.util.UUID;

public interface VeiculoRepository extends JpaRepository<Veiculo, UUID> {
    List<Veiculo> findByMarca(String marca);
    boolean existsByPlaca(String placa);
}
