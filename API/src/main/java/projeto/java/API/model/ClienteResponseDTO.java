package projeto.java.API.model;

import projeto.java.API.entity.Cliente;

import java.util.UUID;

public record ClienteResponseDTO (
        UUID id,
        String nome,
        String telefone,
        String cpf,
        String email
) {
    public ClienteResponseDTO(Cliente cliente){
        this(
                cliente.getId(),
                cliente.getNome(),
                cliente.getTelefone(),
                cliente.getCpf(),
                cliente.getEmail()
        );
    }
}
