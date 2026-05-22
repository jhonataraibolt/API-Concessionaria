package projeto.java.API.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.java.API.entity.Cliente;
import projeto.java.API.exception.ArgumentNotValidException;
import projeto.java.API.exception.DuplicateResourceException;
import projeto.java.API.exception.ResourceNotFoundException;
import projeto.java.API.model.ClienteRequestDTO;
import projeto.java.API.model.ClienteResponseDTO;
import projeto.java.API.repository.ClienteRepository;
import projeto.java.API.repository.VeiculoRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;
    @Autowired
    private VeiculoRepository repositoryVeic;

    public ClienteResponseDTO salvar(ClienteRequestDTO dto){
        if (repository.existsByCpf(dto.cpf())) {
            throw new DuplicateResourceException("O CPF " + dto.cpf() + " já está cadastrado no sistema");
        }
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(dto,cliente); //usei o BeansUtils para copiar de um para outro
        repository.save(cliente);
        return new ClienteResponseDTO(cliente);
    }

    public List<Cliente> listarTodos(){
        return repository.findAll();
    }

    public void Deletar(UUID id){
       Cliente cliente = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Cliente com id " + id + " não encontrado!"));

       boolean possuiVeiculos = repositoryVeic.existsByProprietario(cliente);

        if (possuiVeiculos) {
            throw new ArgumentNotValidException("Cliente possui veiculo, não pode ser deletado!");
        }
        repository.delete(cliente);
    }

    public Cliente buscarPorId(UUID id){
        return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Id do cliente não encontrado"));
    }
    public List<Cliente> buscarPorNome(String nome){
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public Cliente buscarPorCpf(String cpf) {
        return repository.findByCpf(cpf).orElseThrow(()-> new ResourceNotFoundException("Cliente não encontrado"));
    }

    public List<ClienteResponseDTO> buscarComFiltros(String nome, String cpf){ //get buscando nome e cpf
        List<Cliente> clientes;

        if (nome != null && !nome.isEmpty()) {
            clientes =  buscarPorNome(nome);
        }else if (cpf != null && !cpf.isEmpty()) {
            clientes = List.of(buscarPorCpf(cpf));
        }else {
            clientes = listarTodos();
        }
        return clientes.stream().map(ClienteResponseDTO::new).toList();
    }
}
