package projeto.java.API.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.java.API.entity.Cliente;
import projeto.java.API.exception.DuplicateResourceException;
import projeto.java.API.model.ClienteRequestDTO;
import projeto.java.API.repository.ClienteRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    public Cliente salvar(ClienteRequestDTO dto){
        if (repository.existsByCpf(dto.cpf())) {
            throw new DuplicateResourceException("O CPF " + dto.cpf() + " já está cadastrado no sistema");
        }
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(dto,cliente);
        return repository.save(cliente);
    }

    public List<Cliente> ListarTodos(){
        return repository.findAll();
    }

    public void Deletar(UUID id){
        repository.deleteById(id);
    }

    public Cliente buscarPorId(UUID id){
        return repository.findById(id).orElseThrow(()-> new RuntimeException("Id do cliente não encontrado"));
    }

    //ainda fora de uso
    public Cliente buscarPorCpf(String cpf) {
        return repository.findByCpf(cpf).orElseThrow(()-> new RuntimeException("Cliente não encontrado"));
    }
}
