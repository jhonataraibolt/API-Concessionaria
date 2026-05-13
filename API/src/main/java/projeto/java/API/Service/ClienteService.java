package projeto.java.API.Service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.java.API.Entity.Cliente;
import projeto.java.API.Repository.ClienteRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    public Cliente salvar(Cliente cliente){
        if (repository.findByCpf(cliente.getCpf()).isPresent()) {
            throw new RuntimeException("CPF já cadastrado");
        }
        return repository.save(cliente);
    }

    public List<Cliente> ListarTodos(){
        return repository.findAll();
    }

    public void Deletar(UUID id){
        repository.deleteById(id);
    }

    public Cliente buscarPorCpf(String cpf) {
        return repository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }
}
