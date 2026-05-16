package projeto.java.API.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteRequestDTO(
   @NotBlank(message = "Nome obrigatório")
   String nome,

   @NotBlank(message = "Telefone obrigatório")
   String telefone,

   @NotBlank(message = "CPF obrigatório")
   String cpf,

   @NotBlank(message = "Email obrigatório")
   @Email(message = "Email invalido")
   String email
) {}
