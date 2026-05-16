package projeto.java.API.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.java.API.entity.Cliente;
import projeto.java.API.entity.Veiculo;
import projeto.java.API.exception.DuplicateResourceException;
import projeto.java.API.model.VeiculoRequestDTO;
import projeto.java.API.repository.VeiculoRepository;

import java.util.List;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository repository;
    @Autowired
    private ClienteService service;

    public Veiculo salvar(VeiculoRequestDTO dto){
        if (repository.existsByPlaca(dto.placa())) {
            throw new DuplicateResourceException("Placa já cadastrada");
        }
        Cliente dono = service.buscarPorId(dto.clienteId());
        Veiculo veiculo = new Veiculo();
        BeanUtils.copyProperties(dto,veiculo);
        veiculo.setProprietario(dono);

        return repository.save(veiculo);
    }

    public List<Veiculo> listarVeiculos(){
        return repository.findAll();
    }
}
